/*
    LA CLASE MODELO CONTIENE TODOS LOS CAMPOS DE UNA TABLA, ES DECIR,
    UNA TABLA UN MODELO
    AL CUAL LE DAREMOS LA PROPIEDAD DE ENCAPSULAMIENTO
 */
package modelos;


public class Login {
    private String dni;
    private String nombres;
    private String apaterno;
    private String amaterno;
    private String direccion;
    private String telefono;
    private String correo;
    private String pas;
    private String rol;

    public Login() {
        dni = "";
        nombres = "";
        apaterno = "";
        amaterno = "";
        direccion = "";
        telefono = "";
        correo = "";
        pas = "";
        rol=""; 
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
        
    
}
