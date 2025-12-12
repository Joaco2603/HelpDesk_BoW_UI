# 🎓 ARQUITECTURA DEL SISTEMA BoW - EXPLICACIÓN COMPLETA

## ¿Qué es `Tockenizer`?

Es simplemente un **objeto Java** que representa una palabra del diccionario:

```java
public class Tockenizer {
    private String palabra;         // Ejemplo: "urgente"
    private String clasificacion;   // Ejemplo: "Emocional"
    private String categoria;       // Ejemplo: "Urgencia"
}
```

**Ejemplo real:**
```java
Tockenizer palabra1 = new Tockenizer("urgente", "Emocional", "Urgencia");
Tockenizer palabra2 = new Tockenizer("wifi", "Técnico", "Redes");
```

## 📊 Flujo Completo del Sistema

### 1️⃣ BASE DE DATOS (MySQL)
```sql
-- Tabla: diccionario_emocional
┌────┬──────────┬────────────┬──────────────┐
│ id │ palabra  │ tipo       │ categoria    │
├────┼──────────┼────────────┼──────────────┤
│ 1  │ urgente  │ Emocional  │ Urgencia     │
│ 2  │ ahora    │ Emocional  │ Urgencia     │
│ 3  │ frustrado│ Emocional  │ Frustración  │
└────┴──────────┴────────────┴──────────────┘

-- Tabla: diccionario_tecnico
┌────┬──────────┬────────────┬──────────────┐
│ id │ palabra  │ tipo       │ categoria    │
├────┼──────────┼────────────┼──────────────┤
│ 1  │ wifi     │ Técnico    │ Redes        │
│ 2  │ router   │ Técnico    │ Redes        │
│ 3  │ impresora│ Técnico    │ Impresoras   │
└────┴──────────┴────────────┴──────────────┘
```

### 2️⃣ DAOs (Data Access Objects)
**Responsabilidad:** Ejecutar queries SQL y convertir ResultSet a objetos Java

```java
public class DiccionarioEmocionalDAO {
    public ArrayList<Tockenizer> obtenerPorCategoria(String categoria) {
        // 1. Ejecuta SQL: SELECT * FROM diccionario_emocional WHERE categoria = 'Urgencia'
        // 2. Por cada fila del ResultSet, crea un objeto Tockenizer
        // 3. Retorna ArrayList de Tockenizers
    }
}
```

### 3️⃣ DataDiccionario (Capa de Datos en Memoria)
**Responsabilidad:** Cargar TODOS los datos desde BD y mantenerlos en memoria

```java
public class DataDiccionarioEmocional {
    // 4 listas en MEMORIA (RAM)
    private ArrayList<Tockenizer> frustracion;   // palabras de frustración
    private ArrayList<Tockenizer> urgencia;      // palabras de urgencia
    private ArrayList<Tockenizer> neutralidad;   // palabras neutrales
    private ArrayList<Tockenizer> positivo;      // palabras positivas
    
    // Constructor: SE EJECUTA UNA SOLA VEZ
    public DataDiccionarioEmocional(String driver, String url, String user, String pass) {
        // 1. Carga TODAS las palabras desde MySQL
        frustracion = dao.obtenerPorCategoria("Frustración");
        urgencia = dao.obtenerPorCategoria("Urgencia");
        // ... etc
        
        // 2. Ahora TODO está en MEMORIA (súper rápido)
    }
}
```

### 4️⃣ ClasificadorBoW (Lógica de Procesamiento)
**Responsabilidad:** Analizar texto usando las palabras en memoria

```java
public class ClasificadorBoW {
    // Al crear el clasificador, carga TODO desde BD
    public ClasificadorBoW(String driver, String url, String user, String pass) {
        diccionarioEmocional = new DataDiccionarioEmocional(driver, url, user, pass);
        diccionarioTecnico = new DataDiccionarioTecnico(driver, url, user, pass);
        // Ahora TODO está en memoria ✅
    }
    
    // Clasifica texto usando las palabras en memoria (NO accede a BD)
    public String clasificarEmocional(String texto) {
        // Cuenta cuántas palabras de cada categoría aparecen
        for (Tockenizer palabra : diccionarioEmocional.getUrgencia()) {
            if (texto.contains(palabra.getPalabra())) {
                scoreUrgencia++;
            }
        }
        // Retorna la categoría con más coincidencias
    }
}
```

## 🔄 Diagrama de Flujo Completo

```
INICIALIZACIÓN (se hace UNA SOLA VEZ):
┌─────────────────────────────────────────────────────────┐
│  1. new ClasificadorBoW(driver, url, user, pass)        │
└─────────────────┬───────────────────────────────────────┘
                  │
                  ├──► new DataDiccionarioEmocional()
                  │         │
                  │         └──► DiccionarioEmocionalDAO.obtenerPorCategoria("Frustración")
                  │                   │
                  │                   └──► SELECT * FROM diccionario_emocional WHERE categoria = 'Frustración'
                  │                             │
                  │                             └──► Crea ArrayList<Tockenizer> en MEMORIA
                  │
                  └──► new DataDiccionarioTecnico()
                            │
                            └──► DiccionarioTecnicoDAO.obtenerPorCategoria("Redes")
                                      │
                                      └──► SELECT * FROM diccionario_tecnico WHERE categoria = 'Redes'
                                                │
                                                └──► Crea ArrayList<Tockenizer> en MEMORIA

RESULTADO: Todas las palabras están cargadas en MEMORIA (RAM)
```

```
USO (procesamiento rápido - NO accede a BD):
┌─────────────────────────────────────────────────────────┐
│  clasificador.analizarTicket(asunto, descripcion)       │
└─────────────────┬───────────────────────────────────────┘
                  │
                  ├──► clasificarEmocional(texto)
                  │         │
                  │         └──► Recorre ArrayList en MEMORIA (súper rápido)
                  │                   - Compara texto con cada Tockenizer
                  │                   - NO hace queries a MySQL
                  │
                  ├──► clasificarTecnico(texto)
                  │         │
                  │         └──► Recorre ArrayList en MEMORIA (súper rápido)
                  │
                  └──► Retorna AnalisisTicket (resultado)
```

## 🧠 ¿Por qué simula Machine Learning?

### Modelo de ML Real:
1. **Entrenamiento:** Carga datos desde archivos/BD → Crea modelo en memoria
2. **Predicción:** Usa el modelo en memoria (rápido, sin acceder a datos)

### Nuestro Sistema BoW:
1. **"Entrenamiento":** Carga diccionarios desde MySQL → Guarda en memoria
2. **"Predicción":** Usa diccionarios en memoria (rápido, sin acceder a BD)

## 📝 Ejemplo Práctico

```java
// PASO 1: Crear clasificador (se ejecuta UNA VEZ)
// Esto carga TODAS las palabras desde MySQL a memoria
ClasificadorBoW clasificador = new ClasificadorBoW(
    "com.mysql.cj.jdbc.Driver",
    "jdbc:mysql://localhost:3306/mydatabase",
    "root",
    "root"
);

// CONSOLA MUESTRA:
// ✓ Diccionario Emocional cargado desde BD:
//   - Frustración: 10 palabras
//   - Urgencia: 10 palabras
//   - Neutralidad: 10 palabras
//   - Positivo: 10 palabras
// ✓ Diccionario Técnico cargado desde BD:
//   - Redes: 10 palabras
//   - Impresoras: 10 palabras
//   - Cuentas: 10 palabras
//   - Hardware: 10 palabras

// PASO 2: Analizar tickets (TODO EN MEMORIA - RÁPIDO)
String texto1 = "Urgente! No puedo conectarme al wifi";
AnalisisTicket analisis1 = clasificador.analizarTicket("Problema", texto1);
// Resultado: Emocional=Urgencia, Técnico=Redes, Prioridad=Alta

String texto2 = "La impresora no imprime";
AnalisisTicket analisis2 = clasificador.analizarTicket("Problema", texto2);
// Resultado: Emocional=Neutralidad, Técnico=Impresoras, Prioridad=Media

// PASO 3 (Opcional): Si agregas palabras a MySQL, recarga
// INSERT INTO diccionario_emocional VALUES (...);
clasificador.diccionarioEmocional.recargar(); // Actualiza sin reiniciar app
```

## 🎯 Arquitectura en Capas

```
┌──────────────────────────────────────────────────┐
│  CAPA 4: APLICACIÓN                              │
│  - MainApp.java                                  │
│  - Controllers (DashboardController, etc.)       │
└────────────────┬─────────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────────┐
│  CAPA 3: LÓGICA DE NEGOCIO (Business Logic)      │
│  - ClasificadorBoW.java  ← PROCESA EN MEMORIA    │
│  - GestorTicket.java                             │
│  - GestorUsuario.java                            │
└────────────────┬─────────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────────┐
│  CAPA 2: DATOS EN MEMORIA (Data Layer)           │
│  - DataDiccionarioEmocional.java ← CACHE EN RAM  │
│  - DataDiccionarioTecnico.java   ← CACHE EN RAM  │
└────────────────┬─────────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────────┐
│  CAPA 1: ACCESO A DATOS (Data Access)            │
│  - DiccionarioEmocionalDAO.java ← QUERIES SQL    │
│  - DiccionarioTecnicoDAO.java   ← QUERIES SQL    │
│  - Conector.java                                 │
└────────────────┬─────────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────────┐
│  CAPA 0: BASE DE DATOS                           │
│  - MySQL (diccionario_emocional, etc.)           │
└──────────────────────────────────────────────────┘
```

## ✅ Ventajas de esta Arquitectura

| Aspecto | Ventaja |
|---------|---------|
| **Velocidad** | Procesamiento en memoria = milisegundos |
| **Escalabilidad** | Agregar palabras sin recompilar |
| **Mantenibilidad** | Código limpio y separado en capas |
| **Flexibilidad** | Cambiar diccionarios sin tocar código |
| **Realismo ML** | Simula perfectamente cómo funciona ML |

## 📚 Resumen Simple

1. **MySQL** guarda TODAS las palabras
2. **DAOs** traen las palabras de MySQL
3. **DataDiccionario** las guarda en **memoria (RAM)**
4. **ClasificadorBoW** las usa para clasificar (súper rápido)
5. **Tockenizer** es solo un objeto que representa una palabra

**NO HAY CÓDIGO HARDCODED** - TODO viene de la base de datos! 🎉
