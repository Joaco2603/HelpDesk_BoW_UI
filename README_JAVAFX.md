# HelpDesk BoW - JavaFX UI

Sistema de gestión de tickets con interfaz JavaFX y clasificación Bag of Words (BoW).

## 📋 Descripción

Este proyecto implementa una interfaz gráfica moderna usando JavaFX para el sistema HelpDesk BoW. Incluye:

- ✅ Sistema de autenticación (login)
- ✅ Dashboard interactivo con navegación
- ✅ Gestión completa de tickets con clasificación BoW
- ✅ Gestión de usuarios (para administradores)
- ✅ Gestión de departamentos
- ✅ Clasificador emocional y técnico integrado
- ✅ Interfaz responsive y moderna

## 🚀 Estructura del Proyecto

```
HelpDesk_BoW_UI/
├── src/
│   ├── cr/ac/ucenfotec/ui/javafx/
│   │   ├── MainApp.java              # Aplicación principal JavaFX
│   │   ├── LoginController.java      # Controlador de login
│   │   ├── DashboardController.java  # Controlador del dashboard
│   │   ├── TicketViewController.java # Gestión de tickets
│   │   ├── UserViewController.java   # Gestión de usuarios
│   │   └── DepartmentViewController.java # Gestión de departamentos
│   │
│   └── resources/
│       ├── fxml/
│       │   ├── LoginView.fxml
│       │   ├── DashboardView.fxml
│       │   ├── TicketView.fxml
│       │   ├── UserView.fxml
│       │   └── DepartmentView.fxml
│       │
│       └── css/
│           └── styles.css            # Estilos personalizados
│
└── HelpDesk_BoW_backend/              # Lógica de negocio
```

## 🛠️ Requisitos Previos

1. **Java JDK 11 o superior**
   ```powershell
   javac --version
   ```

2. **JavaFX SDK 17 o superior**
   - Descargar desde: https://gluonhq.com/products/javafx/
   - Extraer en una carpeta (ej: `C:\javafx-sdk-17`)

3. **Configuración de variables de entorno (opcional)**
   ```powershell
   $env:PATH_TO_FX = "C:\javafx-sdk-17\lib"
   ```

## 📦 Compilación

### Opción 1: Usando JavaFX SDK

```powershell
# Navegar al directorio del proyecto
cd "C:\Users\joaco\Documents\Programming\cenfotec\HelpDesk"

# Compilar el backend primero
cd HelpDesk_BoW_backend\src
javac -d ..\out cr\ac\ucenfotec\bl\entities\*.java cr\ac\ucenfotec\bl\logic\*.java cr\ac\ucenfotec\dl\*.java

# Compilar la UI JavaFX
cd ..\..\HelpDesk_BoW_UI\src
javac --module-path "C:\javafx-sdk-17\lib" --add-modules javafx.controls,javafx.fxml -cp "..\..\HelpDesk_BoW_backend\out" -d ..\out cr\ac\ucenfotec\ui\javafx\*.java

# Copiar recursos FXML y CSS
xcopy /E /I resources ..\out\resources
```

### Opción 2: Usando Maven (Recomendado)

Crear un archivo `pom.xml` en la raíz:

```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>cr.ac.ucenfotec</groupId>
    <artifactId>helpdesk-bow</artifactId>
    <version>1.0</version>
    
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>17.0.2</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>17.0.2</version>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.openjfx</groupId>
                <artifactId>javafx-maven-plugin</artifactId>
                <version>0.0.8</version>
                <configuration>
                    <mainClass>cr.ac.ucenfotec.ui.javafx.MainApp</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

Luego ejecutar:
```powershell
mvn clean compile
mvn javafx:run
```

## 🎮 Ejecución

### Usando el SDK de JavaFX:

```powershell
cd HelpDesk_BoW_UI\out
java --module-path "C:\javafx-sdk-17\lib" --add-modules javafx.controls,javafx.fxml -cp ".;..\..\HelpDesk_BoW_backend\out" cr.ac.ucenfotec.ui.javafx.MainApp
```

### Usando Maven:

```powershell
mvn javafx:run
```

## 👥 Usuarios de Prueba

El sistema incluye usuarios predefinidos para testing:

| Correo | Contraseña | Rol |
|--------|------------|-----|
| admin@helpdesk.com | admin123 | admin |
| juan@example.com | 1234 | usuario |
| maria@example.com | 1234 | soporte |

## 🎨 Características de la UI

### Login
- Autenticación con email y contraseña
- Validación de credenciales
- Mensaje de error amigable
- Link a registro (placeholder)

### Dashboard
- Navegación por módulos (Tickets, Usuarios, Departamentos)
- Sidebar con información del usuario actual
- Cierre de sesión
- Permisos basados en roles

### Gestión de Tickets
- Tabla interactiva con todos los tickets
- Formulario de creación/edición
- Clasificación BoW emocional y técnica
- Asignación a departamentos
- Estados: Abierto, En Proceso, Cerrado
- Prioridades: Baja, Media, Alta

### Gestión de Usuarios (Solo Admin)
- CRUD completo de usuarios
- Asignación de roles
- Validación de emails
- Protección contra auto-eliminación

### Gestión de Departamentos
- CRUD completo de departamentos
- Información de contacto
- Descripción de funciones

## 🔧 Troubleshooting

### Error: "package javafx.* does not exist"
**Solución**: Asegúrate de incluir `--module-path` y `--add-modules` al compilar/ejecutar.

### Error: "cannot find symbol: class Application"
**Solución**: Verifica que JavaFX SDK esté correctamente descargado e instalado.

### Error: "Location is not set"
**Solución**: Los archivos FXML deben estar en `resources/fxml/` y el classpath debe incluir la carpeta `resources`.

### La UI no se ve correctamente
**Solución**: Verifica que el archivo CSS esté en `resources/css/styles.css`.

## 📝 Notas de Desarrollo

- **Backend**: Ubicado en `HelpDesk_BoW_backend/src`
- **UI**: Ubicado en `HelpDesk_BoW_UI/src`
- **Patrón**: MVC (Model-View-Controller)
- **Arquitectura**: Tres capas (BL, DL, UI)

## 🔄 Próximas Mejoras

- [ ] Integración con base de datos MySQL
- [ ] Sistema de notificaciones
- [ ] Reportes y estadísticas
- [ ] Búsqueda y filtros avanzados
- [ ] Exportación a PDF
- [ ] Sistema de comentarios en tickets
- [ ] Historial de cambios

## 📄 Licencia

Proyecto académico - UCenfotec

## 👨‍💻 Autor

Equipo HelpDesk - UCenfotec 2025
