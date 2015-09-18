/*
    NO ES NECESARIO CREAR ROLES EN LA BASE DE DATOS YA QUE LIMITAREMOS
    EL ACCESO A LAS MISMAS CON LOS VISTAS
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelos.Login;

/**
 *
 * @author Jhunior
 */
public class LoginDAO {
    conexion conec;
    public LoginDAO(){
        conec = new conexion();
    }
    
    public Login verificaUsuario(String usuario,String contra){
        Login login = null;
        Connection acceso = conec.getConexion();
        try {
            PreparedStatement ps = acceso.prepareStatement("SELECT *FROM empleado WHERE DNI = ? and contra = ?");
            ps.setString(1, usuario);
            ps.setString(2, contra);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                login = new Login();
                login.setDni(rs.getString(1));
                login.setNombres(rs.getString(2));
                login.setApaterno(rs.getString(3));
                login.setAmaterno(rs.getString(4));
                login.setDireccion(rs.getString(5));
                login.setTelefono(rs.getString(6));
                login.setCorreo(rs.getString(7));
                login.setPas(rs.getString(8));
                login.setRol(rs.getString(9));
            }
            
        } catch (Exception e) {
        }
        conec.desconectar(); 
        return login;
    }
    
}
