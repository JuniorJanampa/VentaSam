/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jhunior
 */
public class conexion {
    Connection con;
    public conexion() {
    }
    //una nueva coneccion
    public Connection getConexion(){
        
        try{
            /* Carga|Registra el driver JDBC */
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            /* Obtener la conexion */
	    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemaventas","root","1234");
        }catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex){
            JOptionPane.showMessageDialog(null,"Error: "+ ex,"Error de Conexion",JOptionPane.ERROR_MESSAGE);
        }
        return con;
    } 
    
    
    //cierra la conecion 
    public void desconectar(){
        con = null;
    }
    
}
