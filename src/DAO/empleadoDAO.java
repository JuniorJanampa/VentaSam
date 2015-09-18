/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelos.Empleado;
/**
 *
 * @author Jhunior
 */
public class empleadoDAO {
    conexion conec;
    
    public empleadoDAO(){
        conec = new conexion();
    }
    
    public String insertarEmpleado(String dni,String nombres,String apaterno,String amaterno,String direccion,String telefono,String correo,String pas,String rol){
        String rpta = null;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement ps = accesoBD.prepareCall("{call emp_ins(?,?,?,?,?,?,?,?,?)}");
            ps.setString(1, dni);
            ps.setString(2, nombres);
            ps.setString(3, apaterno);
            ps.setString(4, amaterno);
            ps.setString(5, direccion);
            ps.setString(6, telefono);
            ps.setString(7, correo);
            ps.setString(8, pas);
            ps.setString(9, rol);
            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas>0){
                rpta = "Regitro Exitoso";
            }
        } catch (Exception e) {
            rpta = "No se hizo Registro";
             JOptionPane.showMessageDialog(null,"ErrorEmpleadoDAO"+ e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return rpta;
    }
    
    public ArrayList<Empleado> listarEmpleado(){
        ArrayList listarE = new ArrayList();
        Empleado modeloE;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement ps = accesoBD.prepareCall("{call emp_lis()}");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                modeloE = new Empleado();
                modeloE.setDni(rs.getString(1));
                modeloE.setNombres(rs.getString(2));
                modeloE.setApaterno(rs.getString(3));
                modeloE.setAmaterno(rs.getString(4));
                modeloE.setDireccion(rs.getString(5));
                modeloE.setTelefono(rs.getString(6));
                modeloE.setCorreo(rs.getString(7));
                modeloE.setPas(rs.getString(8));
                modeloE.setRol(rs.getString(9));
                listarE.add(modeloE);
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"ErrorEmpleadoDAO"+ e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return listarE;
    }
    
    public int modificarEmpleado(String dni,String nombres,String apaterno,String amaterno,String direccion,String telefono,String correo,String dnio){
        int filasAfectadas=0;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement ps = accesoBD.prepareCall("{call emp_upd(?,?,?,?,?,?,?,?)}");
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
             JOptionPane.showMessageDialog(null,"ErrorEmpleadoDAO"+ e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return filasAfectadas;
    }
    
    public int eliminarEmpleado(String dni){
        int filasAfectadas=0;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement ps = accesoBD.prepareCall("{call emp_del(?)}");
            ps.setString(1,dni);
            filasAfectadas = ps.executeUpdate();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"ErrorEmpleadoDAO"+ e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return filasAfectadas;
    }
    
    //Busqueda Avanzada
    public Empleado buscarxDni(String dni){
        Empleado modeloE = new Empleado();
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement ps = accesoBD.prepareCall("{call emp_bdni(?)}");
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                modeloE.setDni(rs.getString(1));
                modeloE.setNombres(rs.getString(2));
                modeloE.setApaterno(rs.getString(3));
                modeloE.setAmaterno(rs.getString(4));
                modeloE.setDireccion(rs.getString(5));
                modeloE.setTelefono(rs.getString(6));
                modeloE.setCorreo(rs.getString(7));
                modeloE.setPas(rs.getString(8));
                modeloE.setRol(rs.getString(9));
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"ErrorEmpleadoDAO"+ e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return modeloE;
    }
       
    public ArrayList<Empleado> filtrarEmpleado(String dni){
        ArrayList filtraCliente = new ArrayList();
        Empleado modeloE;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call emp_bape(?)}");
            cs.setString(1, dni);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloE = new Empleado();
                modeloE.setDni(rs.getString(1));
                modeloE.setNombres(rs.getString(2));
                modeloE.setApaterno(rs.getString(3));
                modeloE.setAmaterno(rs.getString(4));
                modeloE.setDireccion(rs.getString(5));
                modeloE.setTelefono(rs.getString(6));
                modeloE.setCorreo(rs.getString(7));
                filtraCliente.add(modeloE);
            }
        } catch (Exception e) {
        }
        return filtraCliente;
    }
    
    public String crearContra(String dni,String pas,String rol){
        String rpta=null;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call emp_ccon(?,?,?)}");
            cs.setString(1, dni);
            cs.setString(2, pas);
            cs.setString(3, rol);
            int fila = cs.executeUpdate();
            if(fila>0){
                rpta = "Creacion Exitosa";
            }
        } catch (Exception e) {
            rpta = "No se puedo crear COntraseña";
             JOptionPane.showMessageDialog(null,"ErrorEmpleadoDAO"+ e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return rpta;
    }
   public Empleado VerEmpresa(){
        Empleado modeloE = new Empleado();
        try {
            Connection accesoBD = conec.getConexion();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT * FROM empleado WHERE DNI = '1234'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                modeloE.setDni(rs.getString(1));
                modeloE.setNombres(rs.getString(2));
                modeloE.setApaterno(rs.getString(3));
                modeloE.setAmaterno(rs.getString(4));
                modeloE.setDireccion(rs.getString(5));
                modeloE.setTelefono(rs.getString(6));
                modeloE.setCorreo(rs.getString(7));
                modeloE.setPas(rs.getString(8));
                modeloE.setRol(rs.getString(9));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ErrorEmpleadoDAO"+ e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return modeloE;
    }
    
    
    public int ActualizarEmpresa(String nombres,String apaterno,String amaterno,String direccion,String telefono,String correo){
        int filasAfectadas=0;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement ps = accesoBD.prepareCall("{call mod_emp(?,?,?,?,?,?)}");
           
            ps.setString(1, nombres);
            ps.setString(2, apaterno);
            ps.setString(3, amaterno);
            ps.setString(4, direccion);
            ps.setString(5, telefono);
            ps.setString(6, correo);
           
            filasAfectadas = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(""+e);
             JOptionPane.showMessageDialog(null,"ErrorEmpleadoDAO"+ e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return filasAfectadas;
    }
}
