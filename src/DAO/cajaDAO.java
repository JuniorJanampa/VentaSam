/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import modelos.Caja;
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
    
    public String fechaCaja(String cod){
        Date fecha;
        String rpta="";
        try {
            Connection accesoBD = conec.getConexion();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT *FROM caja WHERE Codigo=?");
            ps.setString(1, cod);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                fecha = rs.getDate("fecha");
                rpta = String.valueOf(fecha);
            }
        } catch (Exception e) {
        }
        return rpta;
    }
    
    public double cajaDiaria(String cod_c){
        double cant_ini=0.0;
        try {
            Connection accesoBD = conec.getConexion();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT Dinero_Inicial,Entrada_dinero,Salida_Dinero FROM caja WHERE Codigo=?");
            ps.setString(1, cod_c);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                double d_i,ent_d,sal_d;
                d_i = rs.getDouble("Dinero_Inicial");
                ent_d = rs.getDouble("Entrada_dinero");
                sal_d = rs.getDouble("Salida_Dinero");
                cant_ini = (d_i + ent_d) - sal_d;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return cant_ini;
    }
    
    public String insertarNCaja(){
        String rpta="";
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call caj_ins()}");
            int filas = cs.executeUpdate();
            if(filas>0){
                rpta = "Se inicio La Caja";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return rpta;
    }
    
    public void actualizarCI(String cod_c,double imp){
        try {
            Connection accesaBD = conec.getConexion();
            CallableStatement cs = accesaBD.prepareCall("{call caj_umi(?,?)}");
            cs.setString(1, cod_c);
            cs.setDouble(2, imp);
            int r = cs.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public double ventasP(String fecha , String dni){
        double total=0.0;
        try {
            Connection accesoBD = conec.getConexion();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT sum(Importe_Total) as Total from venta where cast(fecha as DATE) = ? and DNI_cli =?");
            ps.setString(1, fecha);
            ps.setString(2, dni);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                total = rs.getDouble("Total");
            }
        } catch (Exception e) {
        }
        return total;
    }
    
        public double ventasF(String fecha){
        double total=0.0;
        try {
            Connection accesoBD = conec.getConexion();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT sum(Importe_Total) as Total from venta where cast(fecha as DATE) = ?");
            ps.setString(1, fecha);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                total = rs.getDouble("Total");
            }
        } catch (Exception e) {
        }
        return total;
    }
        
        public double comprasF(String fecha){
        double total=0.0;
        try {
            Connection accesoBD = conec.getConexion();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT sum(Importe_Total) as Total from compra where cast(fecha as DATE) = ?");
            ps.setString(1, fecha);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                total = rs.getDouble("Total");
            }
        } catch (Exception e) {
        }
        return total;
    }
        public String actualizarCierre(String cod_c,double d_e, double d_s){
            String rpta = "";
            try {
                Connection accesoBD = conec.getConexion();
                PreparedStatement ps = accesoBD.prepareStatement("UPDATE caja SET Entrada_dinero = ?, Salida_Dinero = ? WHERE Codigo = ?");
                ps.setDouble(1, d_e);
                ps.setDouble(2, d_s);
                ps.setString(3, cod_c);
                int filas = ps.executeUpdate();
                if(filas>0){
                    rpta = "Cierra de Caja Exitoso";
                }
            } catch (Exception e) {
            }
            return rpta;
        }
        
        public Caja buscarCaja(String cod_c){
            Caja modeloCa = new Caja();
            try {
                Connection accesoBD = conec.getConexion();
                PreparedStatement ps = accesoBD.prepareStatement("SELECT *FROM caja WHERE Codigo=?");
                ps.setString(1, cod_c);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    modeloCa.setCodigo(rs.getString(1));
                    modeloCa.setFecha(rs.getDate(2));
                    modeloCa.setD_ini(rs.getDouble(3));
                    modeloCa.setE_din(rs.getDouble(4));
                    modeloCa.setS_dni(rs.getDouble(5));
                }
            } catch (Exception e) {
            }
            return modeloCa;
        }
        
        public ArrayList<Caja> listarCaja(){
            ArrayList listaCa = new ArrayList();
            Caja modeloCa;
            try {
                Connection accesoBD = conec.getConexion();
                PreparedStatement ps = accesoBD.prepareStatement("SELECT *FROM caja");
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    modeloCa = new Caja();
                    modeloCa.setCodigo(rs.getString(1));
                    modeloCa.setFecha(rs.getDate(2));
                    modeloCa.setD_ini(rs.getDouble(3));
                    modeloCa.setE_din(rs.getDouble(4));
                    modeloCa.setS_dni(rs.getDouble(5));
                    listaCa.add(modeloCa);
                }
            } catch (Exception e) {
            }
            return listaCa;
        }
        
        public ArrayList<Caja> porDia(String fecha){
            ArrayList listaCa = new ArrayList();
            Caja modeloCa;
            try {
                Connection accesoBD = conec.getConexion();
                PreparedStatement ps = accesoBD.prepareStatement("SELECT *FROM caja WHERE cast(fecha as DATE) = ?");
                ps.setString(1, fecha);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    modeloCa = new Caja();
                    modeloCa.setCodigo(rs.getString(1));
                    modeloCa.setFecha(rs.getDate(2));
                    modeloCa.setD_ini(rs.getDouble(3));
                    modeloCa.setE_din(rs.getDouble(4));
                    modeloCa.setS_dni(rs.getDouble(5));
                    listaCa.add(modeloCa);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return listaCa;
        }
        
        public ArrayList<Caja> entreDias(String f1,String f2){
            ArrayList listaCa = new ArrayList();
            Caja modeloCa;
            try {
                Connection accesoBD = conec.getConexion();
                PreparedStatement ps = accesoBD.prepareStatement("SELECT * FROM venta WHERE fecha between ? and DATE_ADD(?, INTERVAL 1 DAY)");
                ps.setString(1, f1);
                ps.setString(2, f2);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    modeloCa = new Caja();
                    modeloCa.setCodigo(rs.getString(1));
                    modeloCa.setFecha(rs.getDate(2));
                    modeloCa.setD_ini(rs.getDouble(3));
                    modeloCa.setE_din(rs.getDouble(4));
                    modeloCa.setS_dni(rs.getDouble(5));
                    listaCa.add(modeloCa);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return listaCa;
        }
        
}
