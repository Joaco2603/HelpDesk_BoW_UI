# Funcionalidad de Registro de Usuarios

## Descripción
Se ha agregado una funcionalidad completa de registro de nuevos usuarios en la interfaz de usuario de JavaFX.

## Archivos Creados

### 1. RegisterView.fxml
- **Ubicación**: `src/resources/fxml/RegisterView.fxml`
- **Descripción**: Interfaz gráfica del formulario de registro
- **Campos**:
  - Nombre completo
  - Correo electrónico
  - Teléfono
  - Contraseña
  - Confirmar contraseña
  - Rol (usuario, soporte, admin)

### 2. RegisterController.java
- **Ubicación**: `src/cr/ac/ucenfotec/ui/javafx/RegisterController.java`
- **Descripción**: Controlador que maneja la lógica del registro
- **Validaciones**:
  - Campos obligatorios
  - Formato de correo electrónico
  - Longitud mínima de contraseña (4 caracteres)
  - Coincidencia de contraseñas
  - Selección de rol

### 3. MainApp.java (Actualizado)
- **Cambio**: Se actualizó el método `showRegisterView()` para cargar la vista completa de registro
- **Antes**: Mostraba un diálogo placeholder
- **Ahora**: Carga la vista RegisterView.fxml con todas las funcionalidades

## Características

✅ **Validación de formulario**
- Verifica que todos los campos estén completos
- Valida formato de correo electrónico
- Asegura que las contraseñas coincidan
- Requiere selección de rol

✅ **Integración con backend**
- Utiliza `GestorUsuario.addUsuario()` para crear usuarios
- Genera ID único automáticamente con UUID

✅ **Experiencia de usuario**
- Mensajes de error claros en color rojo
- Mensaje de éxito en color verde
- Redirección automática al login después del registro exitoso
- Navegación fluida entre registro y login
- Soporte para tecla Enter en el último campo

✅ **Estilos consistentes**
- Utiliza los estilos CSS existentes del proyecto
- Diseño coherente con la pantalla de login
- Responsive y profesional

## Uso

1. En la pantalla de login, hacer clic en "Registrarse"
2. Completar todos los campos del formulario
3. Seleccionar un rol (por defecto: usuario)
4. Hacer clic en "Registrarse" o presionar Enter
5. Después del registro exitoso, el usuario será redirigido automáticamente al login

## Navegación

- Desde **Login** → Clic en "Registrarse" → **Registro**
- Desde **Registro** → Clic en "Iniciar Sesión" → **Login**
- Desde **Registro** → Botón "Cancelar" → Limpia el formulario

## Roles Disponibles

- **usuario**: Usuario estándar del sistema
- **soporte**: Personal de soporte técnico
- **admin**: Administrador con permisos completos
