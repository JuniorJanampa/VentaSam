/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import modelos.Compras;

/**
 *
 * @author Jhunior
 */
public class compraDAO {
    conexion conec;
    
    public compraDAO(){
        conec = new conexion();
    }
    
    public String insertarCompra(String dnie,String cod_prov,String cod_caja){
        String rpta="";
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call com_ins(?,?,?)}");
            cs.setString(1, dnie);
            cs.setString(2, cod_prov);
            cs.setString(3, cod_caja);
            int filas = cs.executeUpdate();
            if(filas>0){
                rpta = "Registro Exitoso";
            }
        } catch (Exception e) {
        }
        conec.desconectar();
        return rpta;
    }
    
    public ArrayList<Compras> listarCompras(){
        ArrayList listaC = new ArrayList();
        Compras modeloCo;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call com_lis()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloCo = new Compras();
                modeloCo.setCodigo(rs.getString(1));
                modeloCo.setFecha(rs.getDate(2));
                modeloCo.setImpt(rs.getDouble(3));
                modeloCo.setDni_e(rs.getString(4));
                modeloCo.setCod_prov(rs.getString(5));
                modeloCo.setCod_caja(rs.getString(6));
                listaC.add(modeloCo);
            }
        } catch (Exception e) {
        }
        conec.desconectar();
        return listaC;
    }
    
    public String codVenta(){
        String cod="";
        try {
            Connection accesoBD = conec.getConexion();
            Statement st = accesoBD.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(Codigo) AS Codigo FROM compra");
            if(rs.next()){
                cod = rs.getString("Codigo");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        conec.desconectar();
        return cod;
    }
    
    public ArrayList<Compras> filtrarDia(String fec1){
        ArrayList listaC = new ArrayList();
        Compras modeloCo;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call c_filtrar_dia(?)}");
            cs.setString(1, fec1);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloCo = new Compras();
                modeloCo.setCodigo(rs.getString(1));
                modeloCo.setFecha(rs.getDate(2));
                modeloCo.setImpt(rs.getDouble(3));
                modeloCo.setDni_e(rs.getString(4));
                modeloCo.setCod_prov(rs.getString(5));
                modeloCo.setCod_caja(rs.getString(6));
                listaC.add(modeloCo);
            }
        } catch (Exception e) {
        }
        conec.desconectar();
        return listaC;
    }
    
    public ArrayList<Compras> filtrarEntre(String fec1,String fec2){
        ArrayList listaC = new ArrayList();
        Compras modeloCo;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call c_filtrar_entre(?,?)}");
            cs.setString(1, fec1);
            cs.setString(2, fec2);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloCo = new Compras();
                modeloCo.setCodigo(rs.getString(1));
                modeloCo.setFecha(rs.getDate(2));
                modeloCo.setImpt(rs.getDouble(3));
                modeloCo.setDni_e(rs.getString(4));
                modeloCo.setCod_prov(rs.getString(5));
                modeloCo.setCod_caja(rs.getString(6));
                listaC.add(modeloCo);
            }
        } catch (Exception e) {
        }
        conec.desconectar();
        return listaC;
    }
}
