/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import modelos.Cliente;

/**
 *
 * @author Jhunior
 */
public class clienteDAO {
    conexion conec;
    
    public clienteDAO(){
        conec = new conexion();
    }
    public String insertarCliente(String dni,String nombres,String apaterno,String amaterno,String direccion,String telefono,String correo){
        String rpta = null;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement ps = accesoBD.prepareCall("{call cli_ins(?,?,?,?,?,?,?)}");
            ps.setString(1, dni);
            ps.setString(2, nombres);
            ps.setString(3, apaterno);
            ps.setString(4, amaterno);
            ps.setString(5, direccion);
            ps.setString(6, telefono);
            ps.setString(7, correo);
            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas>0){
                rpta = "Regitro Exitoso";
            }
        } catch (Exception e) {
            rpta = "No se hizo Registro";
        }
        conec.desconectar();
        return rpta;
    }
    
    public ArrayList<Cliente> listarClientes(){
        ArrayList listarC = new ArrayList();
        Cliente modeloC;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement ps = accesoBD.prepareCall("{call cli_lis()}");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                modeloC = new Cliente();
                modeloC.setDni(rs.getString(1));
                modeloC.setNombres(rs.getString(2));
                modeloC.setApaterno(rs.getString(3));
                modeloC.setAmaterno(rs.getString(4));
                modeloC.setDireccion(rs.getString(5));
                modeloC.setTelefono(rs.getString(6));
                modeloC.setCorreo(rs.getString(7));
                listarC.add(modeloC);
            }
        } catch (Exception e) {
        }
        conec.desconectar();
        return listarC;
    }
    
    public int modificarCliente(String dni,String nombres,String apaterno,String amaterno,String direccion,String telefono,String correo,String dnio){
        int filasAfectadas=0;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement ps = accesoBD.prepareCall("{call cli_upd(?,?,?,?,?,?,?,?)}");
            ps.setString(1, dni);
            ps.setString(2, nombres);
            ps.setString(3, apaterno);
            ps.setString(4, amaterno);
            ps.setString(5, direccion);
            ps.setString(6, telefono);
            ps.setString(7, correo);
            ps.setString(8, dnio);
            filasAfectadas = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(""+e);
        }
        conec.desconectar();
        return filasAfectadas;
    }
    
    public int eliminarCliente(String dni){
        int filasAfectadas=0;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement ps = accesoBD.prepareCall("{call cli_del(?)}");
            ps.setString(1,dni);
            filasAfectadas = ps.executeUpdate();
        } catch (Exception e) {
        }
        conec.desconectar();
        return filasAfectadas;
    }
    
    //Busqueda Avanzada
    public Cliente buscarxDni(String dni){
        Cliente modeloC = new Cliente();
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement ps = accesoBD.prepareCall("{call cli_bdni(?)}");
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                modeloC.setDni(rs.getString(1));
                modeloC.setNombres(rs.getString(2));
                modeloC.setApaterno(rs.getString(3));
                modeloC.setAmaterno(rs.getString(4));
                modeloC.setDireccion(rs.getString(5));
                modeloC.setTelefono(rs.getString(6));
                modeloC.setCorreo(rs.getString(7));
            }
        } catch (Exception e) {
        }
        conec.desconectar();
        return modeloC;
    }
       
    public ArrayList<Cliente> filtrarCliente(String dni){
        ArrayList filtraCliente = new ArrayList();
        Cliente modeloC;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call cli_bape(?)}");
            cs.setString(1, dni);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloC = new Cliente();
                modeloC.setDni(rs.getString(1));
                modeloC.setNombres(rs.getString(2));
                modeloC.setApaterno(rs.getString(3));
                modeloC.setAmaterno(rs.getString(4));
                modeloC.setDireccion(rs.getString(5));
                modeloC.setTelefono(rs.getString(6));
                modeloC.setCorreo(rs.getString(7));
                filtraCliente.add(modeloC);
            }
        } catch (Exception e) {
        }
        conec.desconectar();
        return filtraCliente;
    }
}


















