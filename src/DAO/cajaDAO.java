/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
/**
 *
 * @author Jhunior
 */
public class cajaDAO {
    conexion conec;
    
    public cajaDAO(){
        conec = new conexion();
    }
    
    public String codigoCaja(){
        String cod="";
        try {
            Connection accesoBD = conec.getConexion();
            Statement st = accesoBD.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(Codigo) AS Codigo FROM caja");
            if(rs.next()){
                cod = rs.getString("Codigo");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return cod;
    }
}
