package sample;

public class Usuario_clase {

    String usuario;
    String nombre;
    String apellido;
    String password;
    String email;

    public Usuario_clase(String usuario, String nombre, String apellido, String password, String email) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setEdad(String edad) {
        this.password = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String telefono) {
        this.email = telefono;
    }
}
