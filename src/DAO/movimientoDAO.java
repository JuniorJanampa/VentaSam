/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import modelos.Movimiento;

/**
 *
 * @author Jhunior
 */
public class movimientoDAO {
    conexion conec;
    
    public movimientoDAO(){
        conec = new conexion();
    }
    
    public ArrayList<Movimiento> listaSalida(){
        ArrayList listaSal = new ArrayList();
        Movimiento modeloM;
        try {
            Connection accesoBD = conec.getConexion();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT *FROM mov_almacen_venta");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                modeloM = new Movimiento();
                modeloM.setCod_caja(rs.getString("codigo_caja"));
                modeloM.setFec_caja(rs.getDate("fecha_caja"));
                modeloM.setCod_vc(rs.getString("codigo"));
                modeloM.setFec_vc(rs.getDate("fecha_venta"));
                modeloM.setCod_pro(rs.getString("codigo_producto"));
                modeloM.setNom_pro(rs.getString("nombre"));
                modeloM.setTotal(rs.getInt("total"));
                listaSal.add(modeloM);
            }
        } catch (Exception e) {
        }
        return listaSal;
    }
    
    public ArrayList<Movimiento> listaEntrada(){
        ArrayList listaSal = new ArrayList();
        Movimiento modeloM;
        try {
            Connection accesoBD = conec.getConexion();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT *FROM mov_almacen_compra");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                modeloM = new Movimiento();
                modeloM.setCod_caja(rs.getString("codigo_caja"));
                modeloM.setFec_caja(rs.getDate("fecha_caja"));
                modeloM.setCod_vc(rs.getString("codigo"));
                modeloM.setFec_vc(rs.getDate("fecha_compra"));
                modeloM.setCod_pro(rs.getString("codigo_producto"));
                modeloM.setNom_pro(rs.getString("nombre"));
                modeloM.setTotal(rs.getInt("total"));
                listaSal.add(modeloM);
            }
        } catch (Exception e) {
        }
        return listaSal;
    }
    
    public ArrayList<Movimiento> filtrarDiaE(String f1){
        ArrayList listaSal = new ArrayList();
        Movimiento modeloM;
        try {
            Connection accesoBD = conec.getConexion();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT *FROM mov_almacen_compra WHERE cast(fecha_caja as DATE) = ?");
            ps.setString(1, f1);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                modeloM = new Movimiento();
                modeloM.setCod_caja(rs.getString("codigo_caja"));
                modeloM.setFec_caja(rs.getDate("fecha_caja"));
                modeloM.setCod_vc(rs.getString("codigo"));
                modeloM.setFec_vc(rs.getDate("fecha_compra"));
                modeloM.setCod_pro(rs.getString("codigo_producto"));
                modeloM.setNom_pro(rs.getString("nombre"));
                modeloM.setTotal(rs.getInt("total"));
                listaSal.add(modeloM);
            }
        } catch (Exception e) {
        }
        return listaSal;
    }
    
    public ArrayList<Movimiento> filtrarEntreE(String f1,String f2){
        ArrayList listaSal = new ArrayList();
        Movimiento modeloM;
        try {
            Connection accesoBD = conec.getConexion();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT * FROM mov_almacen_compra WHERE fecha_caja between ? and DATE_ADD(?, INTERVAL 1 DAY)");
            ps.setString(1, f1);
            ps.setString(2, f2);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                modeloM = new Movimiento();
                modeloM.setCod_caja(rs.getString("codigo_caja"));
                modeloM.setFec_caja(rs.getDate("fecha_caja"));
                modeloM.setCod_vc(rs.getString("codigo"));
                modeloM.setFec_vc(rs.getDate("fecha_compra"));
                modeloM.setCod_pro(rs.getString("codigo_producto"));
                modeloM.setNom_pro(rs.getString("nombre"));
                modeloM.setTotal(rs.getInt("total"));
                listaSal.add(modeloM);
            }
        } catch (Exception e) {
        }
        return listaSal;
    }
    
    public ArrayList<Movimiento> filtrarDiaS(String f1){
        ArrayList listaSal = new ArrayList();
        Movimiento modeloM;
        try {
            Connection accesoBD = conec.getConexion();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT *FROM mov_almacen_venta WHERE cast(fecha_caja as DATE) = ?");
            ps.setString(1, f1);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                modeloM = new Movimiento();
                modeloM.setCod_caja(rs.getString("codigo_caja"));
                modeloM.setFec_caja(rs.getDate("fecha_caja"));
                modeloM.setCod_vc(rs.getString("codigo"));
                modeloM.setFec_vc(rs.getDate("fecha_compra"));
                modeloM.setCod_pro(rs.getString("codigo_producto"));
                modeloM.setNom_pro(rs.getString("nombre"));
                modeloM.setTotal(rs.getInt("total"));
                listaSal.add(modeloM);
            }
        } catch (Exception e) {
        }
        return listaSal;
    }
    
    public ArrayList<Movimiento> filtrarEntreS(String f1,String f2){
        ArrayList listaSal = new ArrayList();
        Movimiento modeloM;
        try {
            Connection accesoBD = conec.getConexion();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT * FROM mov_almacen_venta WHERE fecha_caja between ? and DATE_ADD(?, INTERVAL 1 DAY)");
            ps.setString(1, f1);
            ps.setString(2, f2);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                modeloM = new Movimiento();
                modeloM.setCod_caja(rs.getString("codigo_caja"));
                modeloM.setFec_caja(rs.getDate("fecha_caja"));
                modeloM.setCod_vc(rs.getString("codigo"));
                modeloM.setFec_vc(rs.getDate("fecha_compra"));
                modeloM.setCod_pro(rs.getString("codigo_producto"));
                modeloM.setNom_pro(rs.getString("nombre"));
                modeloM.setTotal(rs.getInt("total"));
                listaSal.add(modeloM);
            }
        } catch (Exception e) {
        }
        return listaSal;
    }
}



