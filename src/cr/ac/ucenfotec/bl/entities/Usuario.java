package cr.ac.ucenfotec.bl.entities;

public class Usuario {
    private String id;
    private String nombre;
    private String correo;
    private String password;
    private String telefono;
    private String rol;

    public Usuario() {
    }

    public Usuario(String id, String nombre, String correo, String password, String telefono, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return id != null ? id.equals(usuario.id) : usuario.id == null;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}



