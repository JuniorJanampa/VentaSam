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
    

    public String insertarProducto(Productos modeloProd){
        String rpta = null;
        try {
            Connection accesoBD = conec.getConexion();

            CallableStatement cs = accesoBD.prepareCall("{call prod_ins(?,?,?,?,?,?,?,?)}");
            cs.setString(1, modeloProd.getNombre());
            cs.setString(2, modeloProd.getCategoria());
            cs.setDouble(3, modeloProd.getPrecio_compra());
            cs.setDouble(4, modeloProd.getStok());
            cs.setInt(5, modeloProd.getUnidad_med());
            cs.setDouble(6, modeloProd.getPrecio_venta());
            cs.setString(7, modeloProd.getCod_prov());
            cs.setBinaryStream(8, modeloProd.getImagen());
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
                modeloProd.setMimagen(rs.getBinaryStream(10));
                listarC.add(modeloProd);
            }
        } catch (Exception e) {
        }
        return listarC;
    }
    

    public String modificarProductoC(Productos modeloProd){
        String rpta=null;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call prod_updc(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, modeloProd.getCodigo());
            cs.setString(2, modeloProd.getNombre());
            cs.setString(3, modeloProd.getCategoria());
            cs.setDouble(4, modeloProd.getPrecio_compra());
            cs.setDouble(5, modeloProd.getStok());
            cs.setInt(6, modeloProd.getUnidad_med());
            cs.setDouble(7, modeloProd.getPrecio_venta());
            cs.setString(8, modeloProd.getCod_prov());
            cs.setBinaryStream(9, modeloProd.getImagen());
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
    
    public String modificarProductoS(Productos modeloProd){
        String rpta=null;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call prod_upds(?,?,?,?,?,?,?,?)}");
            cs.setString(1, modeloProd.getCodigo());
            cs.setString(2, modeloProd.getNombre());
            cs.setString(3, modeloProd.getCategoria());
            cs.setDouble(4, modeloProd.getPrecio_compra());
            cs.setDouble(5, modeloProd.getStok());
            cs.setInt(6, modeloProd.getUnidad_med());
            cs.setDouble(7, modeloProd.getPrecio_venta());
            cs.setString(8, modeloProd.getCod_prov());
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
                modeloProd.setMimagen(rs.getBinaryStream(10));
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
                modeloProd.setMimagen(rs.getBinaryStream(8));
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
                modeloProd.setMimagen(rs.getBinaryStream(10));
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
                modeloProd.setMimagen(rs.getBinaryStream(10));
                listaProd.add(modeloProd);
            }
        } catch (Exception e) {
        }
        return listaProd;
    }
}
