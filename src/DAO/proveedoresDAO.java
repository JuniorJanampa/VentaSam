/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import modelos.Proveedores;
/**
 *
 * @author Jhunior
 */
public class proveedoresDAO {
    conexion conec;
    
    public proveedoresDAO(){
        conec = new conexion();
    }
    
    /*BASICO*/
    public String insertaProveedor(String ruc,String rsoc,String dir,String tel,String corr,String nroc,String ban,String cont,String tele_c,String corr_c){
        String rpta=null;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call prov_ins(?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1,ruc);
            cs.setString(2,rsoc);
            cs.setString(3,dir);
            cs.setString(4,tel);
            cs.setString(5,corr);
            cs.setString(6,nroc);
            cs.setString(7,ban);
            cs.setString(8,cont);
            cs.setString(9,tele_c);
            cs.setString(10,corr_c);
            int filasA = cs.executeUpdate();
            if (filasA > 0){
                rpta="Registro Exitoso";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        conec.desconectar();
        return rpta;
    }
    
    public ArrayList<Proveedores> listarProveedores(){
        ArrayList listaProv = new ArrayList();
        Proveedores modeloProv;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement ps = accesoBD.prepareCall("{call prov_lis()}");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                modeloProv = new Proveedores();
                modeloProv.setCodigo(rs.getString(1));
                modeloProv.setRuc(rs.getString(2));
                modeloProv.setRazonsocial(rs.getString(3));
                modeloProv.setDireccion(rs.getString(4));
                modeloProv.setTelefono(rs.getString(5));
                modeloProv.setCorreo(rs.getString(6));
                modeloProv.setNrocuenta(rs.getString(7));
                modeloProv.setBanco(rs.getString(8));
                modeloProv.setContacto(rs.getString(9));
                modeloProv.setTele_contacto(rs.getString(10));
                modeloProv.setCorreo_contacto(rs.getString(11));
                listaProv.add(modeloProv);
            }
        } catch (Exception e) {
        }
        conec.desconectar();
        return listaProv;
    }
    
    public int modificarProveedor(String codigo,String ruc,String rsoc,String dir,String tel,String corr,String nroc,String ban,String cont,String tele_c,String corr_c){
        int filasA = 0;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement ps = accesoBD.prepareCall("{call prov_upd(?,?,?,?,?,?,?,?,?,?,?)}");
            ps.setString(1,ruc);
            ps.setString(2,rsoc);
            ps.setString(3,dir);
            ps.setString(4,tel);
            ps.setString(5,corr);
            ps.setString(6,nroc);
            ps.setString(7,ban);
            ps.setString(8,cont);
            ps.setString(9,tele_c);
            ps.setString(10,corr_c);
            ps.setString(11,codigo);
            filasA = ps.executeUpdate();
        } catch (Exception e) {
        }
        conec.desconectar();
        return filasA;
    }
    
    public int eliminarProveedor(String ruc){
        int filasA=0;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement ps = accesoBD.prepareCall("{call prov_del(?)}");
            ps.setString(1, ruc);
            filasA = ps.executeUpdate();
        } catch (Exception e) {
        }
        conec.desconectar();
        return filasA;
    }
    
    /*AVANZADO Y/O ESPECIFICO*/
    
    public Proveedores buscarRuc(String ruc){
        Proveedores modeloProv = new Proveedores();
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement ps = accesoBD.prepareCall("{call prov_busr(?)}");
            ps.setString(1, ruc);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                modeloProv.setCodigo(rs.getString(1));
                modeloProv.setRuc(rs.getString(2));
                modeloProv.setRazonsocial(rs.getString(3));
                modeloProv.setDireccion(rs.getString(4));
                modeloProv.setTelefono(rs.getString(5));
                modeloProv.setCorreo(rs.getString(6));
                modeloProv.setNrocuenta(rs.getString(7));
                modeloProv.setBanco(rs.getString(8));
                modeloProv.setContacto(rs.getString(9));
                modeloProv.setTele_contacto(rs.getString(10));
                modeloProv.setCorreo_contacto(rs.getString(11));
            }
        } catch (Exception e) {
        }
        conec.desconectar();
        return modeloProv;
    }
    
    public Proveedores buscarCod(String cod){
        Proveedores modeloProv = new Proveedores();
        try {
            Connection accesoBD = conec.getConexion();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT *FROM proveedores WHERE codigo = ?");
            ps.setString(1, cod);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                modeloProv.setCodigo(rs.getString(1));
                modeloProv.setRuc(rs.getString(2));
                modeloProv.setRazonsocial(rs.getString(3));
                modeloProv.setDireccion(rs.getString(4));
                modeloProv.setTelefono(rs.getString(5));
                modeloProv.setCorreo(rs.getString(6));
                modeloProv.setNrocuenta(rs.getString(7));
                modeloProv.setBanco(rs.getString(8));
                modeloProv.setContacto(rs.getString(9));
                modeloProv.setTele_contacto(rs.getString(10));
                modeloProv.setCorreo_contacto(rs.getString(11));
            }
        } catch (Exception e) {
        }
        conec.desconectar();
        return modeloProv;
    }
    
    
    
    public ArrayList<Proveedores> buscarRazon(String razon){
        ArrayList listaProv = new ArrayList();
        Proveedores modeloProv;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call prov_busrs(?)}");
            cs.setString(1, razon);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloProv = new Proveedores();
                modeloProv.setCodigo(rs.getString(1));
                modeloProv.setRuc(rs.getString(2));
                modeloProv.setRazonsocial(rs.getString(3));
                modeloProv.setDireccion(rs.getString(4));
                modeloProv.setTelefono(rs.getString(5));
                modeloProv.setCorreo(rs.getString(6));
                modeloProv.setNrocuenta(rs.getString(7));
                modeloProv.setBanco(rs.getString(8));
                modeloProv.setContacto(rs.getString(9));
                modeloProv.setTele_contacto(rs.getString(10));
                modeloProv.setCorreo_contacto(rs.getString(11));
                listaProv.add(modeloProv);
            }
        } catch (Exception e) {
        }
        conec.desconectar();
        return listaProv;
    }
}
