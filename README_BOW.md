# Clasificador Bag of Words (BoW) con Base de Datos

## Descripción

Este sistema implementa un clasificador Bag of Words para análisis de tickets que:
- ✅ Carga los diccionarios emocionales y técnicos desde la **base de datos**
- ✅ Mantiene los datos **en memoria** para procesamiento rápido
- ✅ Simula el proceso de **machine learning**
- ✅ Permite actualizar diccionarios sin recompilar código
- ✅ Tiene **fallback automático** a datos hardcoded si falla la BD

## Arquitectura

```
┌─────────────────────────────────────────────────┐
│          ClasificadorBoW (Lógica BoW)           │
│  - Análisis emocional                           │
│  - Análisis técnico                             │
│  - Sugerencia de prioridad/departamento         │
└────────────┬────────────────────────────────────┘
             │
             ├─────────────────────┬───────────────────────┐
             │                     │                       │
    ┌────────▼────────┐   ┌───────▼──────────┐   ┌────────▼────────┐
    │ DataDiccionario │   │ DataDiccionario  │   │  DAOs (acceso   │
    │   Emocional     │   │    Técnico       │   │   a base de     │
    │                 │   │                  │   │   datos)        │
    │ - Carga desde BD│   │ - Carga desde BD │   │                 │
    │ - Mantiene en   │   │ - Mantiene en    │   │                 │
    │   memoria       │   │   memoria        │   │                 │
    └────────┬────────┘   └───────┬──────────┘   └────────┬────────┘
             │                    │                       │
             └────────────────────┴───────────────────────┘
                                  │
                         ┌────────▼────────┐
                         │   Base de Datos │
                         │   MySQL         │
                         │                 │
                         │ - diccionario_  │
                         │   emocional     │
                         │ - diccionario_  │
                         │   tecnico       │
                         └─────────────────┘
```

## Tablas de Base de Datos

### diccionario_emocional
```sql
CREATE TABLE diccionario_emocional (
    id INT PRIMARY KEY AUTO_INCREMENT,
    palabra VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    categoria VARCHAR(50) NOT NULL  -- Frustración, Urgencia, Neutralidad, Positivo
);
```

### diccionario_tecnico
```sql
CREATE TABLE diccionario_tecnico (
    id INT PRIMARY KEY AUTO_INCREMENT,
    palabra VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    categoria VARCHAR(50) NOT NULL  -- Redes, Impresoras, Cuentas, Hardware
);
```

## Uso del Clasificador

### Opción 1: Con Base de Datos (Recomendado)

```java
// Parámetros de conexión
String driver = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/mydatabase";
String user = "root";
String pass = "root";

// Crear clasificador con datos desde DB
ClasificadorBoW clasificador = new ClasificadorBoW(driver, url, user, pass);

// Los diccionarios se cargan desde la BD y se mantienen en memoria
// El procesamiento es rápido porque los datos están en memoria
```

### Opción 2: Sin Base de Datos (Fallback)

```java
// Crear clasificador con datos hardcoded
ClasificadorBoW clasificador = new ClasificadorBoW();

// Útil para desarrollo o cuando no hay conexión a BD
```

## Ejemplo Completo

```java
import cr.ac.ucenfotec.bl.entities.ClasificadorBoW;

public class MiAplicacion {
    public static void main(String[] args) {
        // Configuración de BD
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "root";
        String pass = "root";

        // Crear clasificador
        ClasificadorBoW clasificador = new ClasificadorBoW(driver, url, user, pass);

        // Analizar un ticket
        String asunto = "Problema con la red";
        String descripcion = "Urgente! No puedo conectarme al wifi";

        ClasificadorBoW.AnalisisTicket analisis =
                clasificador.analizarTicket(asunto, descripcion);

        System.out.println("Categoría Emocional: " + analisis.categoriaEmocional);
        System.out.println("Categoría Técnica: " + analisis.categoriaTecnica);
        System.out.println("Prioridad: " + analisis.prioridadSugerida);
        System.out.println("Departamento: " + analisis.departamentoSugerido);
    }
}
```

## Métodos Disponibles

### ClasificadorBoW

| Método | Descripción | Retorno |
|--------|-------------|---------|
| `clasificarEmocional(String texto)` | Clasifica el estado emocional | String (Frustración, Urgencia, Neutralidad, Positivo) |
| `clasificarTecnico(String texto)` | Clasifica la categoría técnica | String (Redes, Impresoras, Cuentas, Hardware, General) |
| `sugerirPrioridad(String texto)` | Sugiere la prioridad | String (Alta, Media, Baja) |
| `sugerirDepartamento(String texto)` | Sugiere el departamento | int (ID del departamento) |
| `analizarTicket(String asunto, String desc)` | Análisis completo | AnalisisTicket |

### DataDiccionarioEmocional / DataDiccionarioTecnico

| Método | Descripción |
|--------|-------------|
| `recargar()` | Recarga los datos desde la BD (sin reiniciar la app) |
| `isUsandoBaseDatos()` | Verifica si está usando BD o datos hardcoded |

## Agregar Nuevas Palabras

### Vía SQL
```sql
-- Agregar palabra emocional
INSERT INTO diccionario_emocional (palabra, tipo, categoria) 
VALUES ('angustiado', 'Emocional', 'Frustración');

-- Agregar palabra técnica
INSERT INTO diccionario_tecnico (palabra, tipo, categoria) 
VALUES ('firewall', 'Técnico', 'Redes');
```

### Vía DAO
```java
DiccionarioEmocionalDAO dao = new DiccionarioEmocionalDAO(driver, url, user, pass);
dao.agregar("angustiado", "Emocional", "Frustración");

// Recargar el diccionario en memoria
clasificador.diccionarioEmocional.recargar();
```

## Ventajas del Sistema

1. **Flexibilidad**: Actualiza diccionarios sin recompilar
2. **Rendimiento**: Datos en memoria = procesamiento rápido
3. **Robustez**: Fallback automático si falla la BD
4. **Escalabilidad**: Fácil de extender con nuevas categorías
5. **Mantenibilidad**: Separación clara de responsabilidades

## Inicialización de la Base de Datos

1. Asegúrate de tener MySQL corriendo
2. Ejecuta el script: `init/init_database.sql`
3. Verifica que las tablas se crearon correctamente

```bash
mysql -u root -p < init/init_database.sql
```

## Notas Técnicas

- Los diccionarios se cargan **una sola vez** al crear el clasificador
- Los datos permanecen **en memoria** durante toda la ejecución
- El procesamiento es **rápido** porque no accede a la BD en cada clasificación
- Esto **simula** cómo funciona un modelo de machine learning entrenado:
  - Los datos de entrenamiento (diccionarios) se cargan una vez
  - El modelo (lógica BoW) procesa en memoria
  - Las predicciones son rápidas

## Integración con GestorTicket

```java
// En GestorTicketMySQL o donde crees tickets
ClasificadorBoW clasificador = new ClasificadorBoW(driver, url, user, pass);

// Antes de guardar el ticket, analizarlo
ClasificadorBoW.AnalisisTicket analisis = 
    clasificador.analizarTicket(ticket.getAsunto(), ticket.getDescripcion());

// Usar el análisis para sugerencias
ticket.setPrioridad(analisis.prioridadSugerida);
ticket.setDepartamentoId(analisis.departamentoSugerido);
```

## Autor
Equipo HelpDesk - CENFOTEC

## Versión
2.0 - Sistema BoW con Base de Datos
