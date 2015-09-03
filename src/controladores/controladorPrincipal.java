/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.LoginDAO;
import DAO.cajaDAO;
import DAO.clienteDAO;
import DAO.detalleventaDAO;
import DAO.empleadoDAO;
import DAO.faltantesDAO;
import DAO.productoDAO;
import DAO.proveedoresDAO;
import DAO.ventaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import vistas.vistaCaja;
import vistas.vistaCajaI;
import vistas.vistaClientes;
import vistas.vistaEmpleados;
import vistas.vistaEmpresa;
import vistas.vistaFaltantes;
import vistas.vistaHistoricoVentas;
import vistas.vistaLogin;
import vistas.vistaPrincipal;
import vistas.vistaProductos;
import vistas.vistaProveedores;
import vistas.vistaVentas;

/**
 *
 * @author Jhunior
 */
public class controladorPrincipal implements ActionListener{
    vistaPrincipal vistaP = new vistaPrincipal();
    vistaCajaI vistaCa = new vistaCajaI(vistaP, true);
    
    cajaDAO daoCa = new cajaDAO();
    String dni;
    String codC;
    double cant_i=0.0;
    
    public controladorPrincipal(vistaPrincipal vistaP,String dni){
        this.vistaP = vistaP;
        this.dni = dni;
        vistaP.btnnuevaventa.addActionListener(this);
        vistaP.btnclientes.addActionListener(this);
        vistaP.btnfaltantes.addActionListener(this);
        vistaP.btnempleados.addActionListener(this);
        vistaP.btnproductos.addActionListener(this);
        vistaP.btncaja.addActionListener(this);
        vistaP.btnproveedores.addActionListener(this);
        vistaP.btnhistorico.addActionListener(this);
        vistaP.btncompras.addActionListener(this);
        vistaP.btninventarios.addActionListener(this);
        vistaP.btnsalir.addActionListener(this);
        vistaP.btncambiarusuario.addActionListener(this);
        vistaP.btnconf.addActionListener(this);
        
        this.vistaCa.btniniciar.addActionListener(this);
        this.vistaCa.btncancelar.addActionListener(this);
    }    
    
    public void inicio(){
        iniciarCaja();
    }
    
    public void iniciarCaja(){
        codC = daoCa.codigoCaja();
        
        if(!codC.equals("")){
            cant_i = daoCa.cajaDiaria(codC);
        }
        
        String rptaf = daoCa.fechaCaja(codC);
        
        Date fec = new Date(System.currentTimeMillis());
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        
        String now = String.valueOf(f.format(fec));
        if(!rptaf.equals(now)){
            vistaCa.txtfecha.setText(now);
            vistaCa.txtfecha.setEditable(false);
            vistaCa.txtcajainicial.setText(String.valueOf(cant_i));
            vistaCa.setLocationRelativeTo(null);
            vistaCa.setVisible(true);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaP.btnclientes){
            vistaClientes vistaC = new vistaClientes();
            clienteDAO daoC = new clienteDAO();
            controladorCliente controladorC = new controladorCliente(vistaC, daoC);
            controladorC.inicializarCliente();
            vistaC.setLocationRelativeTo(null);
            vistaC.setVisible(true);
        }
        
        if(e.getSource() == vistaP.btnproveedores){
            vistaProveedores vistaProv = new vistaProveedores();
            proveedoresDAO daoProv = new proveedoresDAO();
            controladorProveedor controladorProv = new controladorProveedor(vistaProv, daoProv);
            controladorProv.inicializarProveedor();
            vistaProv.setLocationRelativeTo(null);
            vistaProv.setVisible(true);
        }
        if(e.getSource() == vistaP.btnproductos){
            vistaProductos vistaProd = new vistaProductos();
            productoDAO daoProd = new productoDAO();
            controladorProducto controladorProd = new controladorProducto(vistaProd, daoProd);
            controladorProd.inicializarProducto();
            vistaProd.setLocationRelativeTo(null);
            vistaProd.setVisible(true);
        }
        if(e.getSource() == vistaP.btnnuevaventa){
            vistaVentas vistaV = new vistaVentas();
            ventaDAO daoV = new ventaDAO();
            detalleventaDAO daoDV = new detalleventaDAO();
            controladorVenta controladorV = new controladorVenta(vistaV, daoV, daoDV,dni,codC);
            controladorV.inicializarVenta();
            vistaV.setLocationRelativeTo(null);
            vistaV.setVisible(true);
        }
        if(e.getSource() == vistaP.btnempleados){
            vistaEmpleados vistaE = new vistaEmpleados();
            empleadoDAO daoE = new empleadoDAO();
            controladorEmpleado controladorE = new controladorEmpleado(vistaE, daoE);
            controladorE.inicializarEmpleado();
            vistaE.setLocationRelativeTo(null);
            vistaE.setVisible(true);
        }
        
        if(e.getSource() == vistaP.btncambiarusuario){
            vistaLogin vistalogin = new vistaLogin();
            LoginDAO logindao = new LoginDAO();
            controladorLogin controladorlogin = new controladorLogin(vistalogin, logindao);
            vistalogin.setVisible(true);
            vistalogin.setLocationRelativeTo(null);
            vistaP.dispose();
        }
        
        if(e.getSource() == vistaP.btnhistorico){
            vistaHistoricoVentas vistaHV = new vistaHistoricoVentas();
            ventaDAO daoV = new ventaDAO();
            controladorHVentas controladorV = new controladorHVentas(vistaHV, daoV);
            controladorV.inicializarHVentas();
            vistaHV.setVisible(true);
            vistaHV.setLocationRelativeTo(null);
        }
        
        if(e.getSource() == vistaP.btnfaltantes){
            vistaFaltantes vistaF = new vistaFaltantes();
            faltantesDAO daoF = new faltantesDAO();
            controladorFaltantes controladorF = new controladorFaltantes(vistaF, daoF);
            controladorF.incializarFaltantes();
            vistaF.setLocationRelativeTo(null);
            vistaF.setVisible(true);
        }
        
        if (e.getSource()==vistaP.btnconf) {
            vistaEmpresa  vistaEm = new vistaEmpresa();
            empleadoDAO daoEm = new empleadoDAO();
            controladorEmpresa controladorEm= new controladorEmpresa(vistaEm, daoEm);
            controladorEm.inicializarEmpresa();
            vistaEm.setLocationRelativeTo(null);
            vistaEm.setVisible(true);
        }
        if(e.getSource() == vistaP.btncaja){
            vistaCaja vistaCaj = new vistaCaja();
            cajaDAO daoCaj = new cajaDAO();
            controladorCaja controladorCaj = new controladorCaja(vistaCaj, daoCaj);
            controladorCaj.incializarCaja();
            vistaCaj.setLocationRelativeTo(null);
            vistaCaj.setVisible(true);
        }
        
        
        
        if(e.getSource() == vistaP.btnsalir){
            System.exit(0);
        }
                
        if(e.getSource() == vistaCa.btniniciar){
            String rpta = daoCa.insertarNCaja();
            codC = daoCa.codigoCaja();
            daoCa.actualizarCI(codC,Double.parseDouble(vistaCa.txtcajainicial.getText()));
            JOptionPane.showMessageDialog(vistaCa, rpta);
            vistaCa.dispose();
        }
        
        if(e.getSource() == vistaCa.btncancelar){
            System.exit(0);
        }
    }
}
