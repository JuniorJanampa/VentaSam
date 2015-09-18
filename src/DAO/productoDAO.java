/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import modelos.Productos;

/**
 *
 * @author Jhunior
 */
public class productoDAO {
    conexion conec;
    
    public productoDAO(){
        conec = new conexion();
    }
    
    public String insertarProducto(String nom,String cate,double pcom, double stok, int unm, double pven,String codp){
        String rpta = null;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call prod_ins(?,?,?,?,?,?,?)}");
            cs.setString(1, nom);
            cs.setString(2, cate);
            cs.setDouble(3, pcom);
            cs.setDouble(4, stok);
            cs.setInt(5, unm);
            cs.setDouble(6, pven);
            cs.setString(7, codp);
            
            int filasAfectadas = cs.executeUpdate();
            if(filasAfectadas>0){
                rpta = "Regitro Exitoso";
            }
        } catch (Exception e) {
            rpta = "No se hizo Registro";
            System.out.println(""+e);
        }
        return rpta;
    }
    
    public ArrayList<Productos> listarProductos(){
        ArrayList listarC = new ArrayList();
        Productos modeloProd;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call prod_lis()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloProd = new Productos();
                modeloProd.setCodigo(rs.getString(1));
                modeloProd.setNombre(rs.getString(2));
                modeloProd.setCategoria(rs.getString(3));
                modeloProd.setPrecio_compra(rs.getDouble(4));
                modeloProd.setStok(rs.getDouble(5));
                modeloProd.setUnidad_medida(rs.getString(6));
                modeloProd.setPrecio_venta(rs.getDouble(7));
                modeloProd.setCod_prov(rs.getString(8));
                modeloProd.setRazons(rs.getString(9));
                listarC.add(modeloProd);
            }
        } catch (Exception e) {
        }
        return listarC;
    }
    
    public String modificarProducto(String cod,String nom,String cate,double pcom,double stok, int unm,double pven,String codp){
        String rpta=null;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call prod_upd(?,?,?,?,?,?,?,?)}");
            cs.setString(1, cod);
            cs.setString(2, nom);
            cs.setString(3, cate);
            cs.setDouble(4, pcom);
            cs.setDouble(5, stok);
            cs.setInt(6, unm);
            cs.setDouble(7, pven);
            cs.setString(8, codp);
            int filasAfectadas = cs.executeUpdate();
            if(filasAfectadas>0){
                rpta = "Actuzalizacion Exitosa";
            }
        } catch (Exception e) {
            rpta = "No hubo actualizacion";
            System.out.println(""+e);
        }
        return rpta;
    }
    
    public int eliminarProducto(String codprod){
        int filasAfectadas=0;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call prod_del(?)}");
            cs.setString(1,codprod);
            filasAfectadas = cs.executeUpdate();
        } catch (Exception e) {
        }
        return filasAfectadas;
    }
    
    //Busqueda Avanzada
    public Productos buscarProd(String codprod,String codprov){
        Productos modeloProd = new Productos();
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call prod_busp(?,?)}");
            cs.setString(1, codprod);
            cs.setString(2, codprov);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloProd.setCodigo(rs.getString(1));
                modeloProd.setNombre(rs.getString(2));
                modeloProd.setCategoria(rs.getString(3));
                modeloProd.setPrecio_compra(rs.getDouble(4));
                modeloProd.setStok(rs.getInt(5));
                modeloProd.setUnidad_medida(rs.getString(6));
                modeloProd.setPrecio_venta(rs.getDouble(7));
                modeloProd.setCod_prov(rs.getString(8));
                modeloProd.setRazons(rs.getString(9));
            }
        } catch (Exception e) {
        }
        return modeloProd;
    }
    
    public Productos buscarP(String codprod){
        Productos modeloProd = new Productos();
        try {
            Connection accesoBD = conec.getConexion();
            PreparedStatement cs = accesoBD.prepareStatement("SELECT *FROM productos WHERE codigo = ?");
            cs.setString(1, codprod);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloProd.setCodigo(rs.getString(1));
                modeloProd.setNombre(rs.getString(2));
                modeloProd.setCategoria(rs.getString(3));
                modeloProd.setPrecio_compra(rs.getDouble(4));
                modeloProd.setStok(rs.getInt(5));
                modeloProd.setUnidad_medida(rs.getString(6));
                modeloProd.setPrecio_venta(rs.getDouble(7));
            }
        } catch (Exception e) {
        }
        return modeloProd;
    }
    
    
    public ArrayList<Productos> buscarProdn(String nom){
        ArrayList listaProd = new ArrayList();
        Productos modeloProd;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call prod_busnp(?)}");
            cs.setString(1, nom);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloProd = new Productos();
                modeloProd.setCodigo(rs.getString(1));
                modeloProd.setNombre(rs.getString(2));
                modeloProd.setCategoria(rs.getString(3));
                modeloProd.setPrecio_compra(rs.getDouble(4));
                modeloProd.setStok(rs.getInt(5));
                modeloProd.setUnidad_medida(rs.getString(6));
                modeloProd.setPrecio_venta(rs.getDouble(7));
                modeloProd.setCod_prov(rs.getString(8));
                modeloProd.setRazons(rs.getString(9));
                listaProd.add(modeloProd);
            }
        } catch (Exception e) {
        }
        return listaProd;
    }
    public ArrayList<Productos> buscarProdp(String razon){
        ArrayList listaProd = new ArrayList();
        Productos modeloProd;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call prod_busnpv(?)}");
            cs.setString(1, razon);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloProd = new Productos();
                modeloProd.setCodigo(rs.getString(1));
                modeloProd.setNombre(rs.getString(2));
                modeloProd.setCategoria(rs.getString(3));
                modeloProd.setPrecio_compra(rs.getDouble(4));
                modeloProd.setStok(rs.getInt(5));
                modeloProd.setUnidad_medida(rs.getString(6));
                modeloProd.setPrecio_venta(rs.getDouble(7));
                modeloProd.setCod_prov(rs.getString(8));
                modeloProd.setRazons(rs.getString(9));
                listaProd.add(modeloProd);
            }
        } catch (Exception e) {
        }
        return listaProd;
    }
}
