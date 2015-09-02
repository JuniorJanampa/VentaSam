/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Detalleventa;

/**
 *
 * @author Jhunior
 */
public class detalleventaDAO {
    conexion conec;
    
    public detalleventaDAO(){
        conec = new conexion();
    }
    
    public String insertarDetalle(String cod_ven,String cod_prod,int cant,int unm,double p_base, double p_cant){
        String rpta="";
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call detv_ins(?,?,?,?,?,?)}");
            cs.setString(1, cod_ven);
            cs.setString(2, cod_prod);
            cs.setInt(3, cant);
            cs.setInt(4, unm);
            cs.setDouble(5, p_base);
            cs.setDouble(6, p_cant);
            int filas = cs.executeUpdate();
            if(filas>0){
                rpta = "Registro Exitoso";
            }
        } catch (Exception e) {
            rpta = "Registro Fallido";
            System.out.println(""+e);
        }
        return rpta;
    }
    
    public ArrayList<Detalleventa> listaDetVen(String cod_ven){
        ArrayList listaDetVen = new ArrayList();
        Detalleventa modeloDV;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call detv_lis(?)}");
            cs.setString(1, cod_ven);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloDV = new Detalleventa();
                modeloDV.setCodigo(rs.getString(1));
                modeloDV.setCod_ven(rs.getString(2));
                modeloDV.setCod_prod(rs.getString(3));
                modeloDV.setCantidad(rs.getInt(4));
                modeloDV.setUm(rs.getString(5));
                modeloDV.setP_base(rs.getDouble(6));
                modeloDV.setP_cant(rs.getDouble(7));
                listaDetVen.add(modeloDV);
            }
        } catch (Exception e) {
            System.out.println(""+e);
        }
        return listaDetVen;
    }
}
