/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.*;
import java.util.ArrayList;
import modelos.Ventas;
/**
 *
 * @author Jhunior
 */
public class ventaDAO {
    conexion conec;
    
    public ventaDAO(){
        conec = new conexion();
    }
    
    public String insertarVenta(double total,String emp,String cli,String caja){
        String rpta="";
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call ven_ins(?,?,?,?)}");
            cs.setDouble(1, total);
            cs.setString(2, emp);
            cs.setString(3, cli);
            cs.setString(4, caja);
            int filas = cs.executeUpdate();
            if(filas > 0){
                rpta = "Registro Exitoso";
            }
        } catch (Exception e) {
            rpta = "Registro Fallido";
            System.out.println(""+e);
        }
        return rpta;
    }
    
    public ArrayList<Ventas> listarVentas(){
        ArrayList listaV = new ArrayList();
        Ventas modeloV;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call ven_lis()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloV = new Ventas();
                modeloV.setCodigo(rs.getString(1));
                modeloV.setFecha(rs.getDate(2));
                modeloV.setImp_tot(rs.getDouble(3));
                modeloV.setEmpleado(rs.getString(4));
                modeloV.setCliente(rs.getString(5));
                modeloV.setCaja(rs.getString(6));
                listaV.add(modeloV);
            }
        } catch (Exception e) {
        }
        return listaV;
    }
    
    public String codVenta(){
        String cod="";
        try {
            Connection accesoBD = conec.getConexion();
            Statement st = accesoBD.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(Codigo) AS Codigo FROM venta");
            if(rs.next()){
                cod = rs.getString("Codigo");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return cod;
    }
    
        public ArrayList<Ventas> filtrarDia(String fec1){
        ArrayList listaV = new ArrayList();
        Ventas modeloV;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call v_filtrar_dia(?)}");
            cs.setString(1, fec1);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloV = new Ventas();
                modeloV.setCodigo(rs.getString(1));
                modeloV.setFecha(rs.getDate(2));
                modeloV.setImp_tot(rs.getDouble(3));
                modeloV.setEmpleado(rs.getString(4));
                modeloV.setCliente(rs.getString(5));
                modeloV.setCaja(rs.getString(6));
                listaV.add(modeloV);
            }
        } catch (Exception e) {
        }
        return listaV;
    }
        
        public ArrayList<Ventas> filtrarEntre(String fec1,String fec2){
        ArrayList listaV = new ArrayList();
        Ventas modeloV;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call v_filtrar_entre(?,?)}");
            cs.setString(1, fec1);
            cs.setString(2, fec2);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloV = new Ventas();
                modeloV.setCodigo(rs.getString(1));
                modeloV.setFecha(rs.getDate(2));
                modeloV.setImp_tot(rs.getDouble(3));
                modeloV.setEmpleado(rs.getString(4));
                modeloV.setCliente(rs.getString(5));
                modeloV.setCaja(rs.getString(6));
                listaV.add(modeloV);
            }
        } catch (Exception e) {
        }
        return listaV;
    }
}
