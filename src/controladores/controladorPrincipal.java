/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.LoginDAO;
import DAO.cajaDAO;
import DAO.clienteDAO;
import DAO.compraDAO;
import DAO.detallecompraDAO;
import DAO.detalleventaDAO;
import DAO.empleadoDAO;
import DAO.faltantesDAO;
import DAO.movimientoDAO;
import DAO.productoDAO;
import DAO.proveedoresDAO;
import DAO.ventaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import vistas.vistaCaja;
import vistas.vistaCajaI;
import vistas.vistaClientes;
import vistas.vistaCompras;
import vistas.vistaEmpleados;
import vistas.vistaEmpresa;
import vistas.vistaFaltantes;
import vistas.vistaHistoricoCompras;
import vistas.vistaHistoricoVentas;
import vistas.vistaLogin;
import vistas.vistaMovimientoIventario;
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
    
    JPopupMenu hisven = new JPopupMenu("Historico Ventas");
        JPopupMenu hiscom = new JPopupMenu("Historico Compras");
    
    public controladorPrincipal(vistaPrincipal vistaP,String dni){
        this.vistaP = vistaP;
        this.dni = dni;
        this.vistaP.btnnuevaventa.addActionListener(this);
        this.vistaP.btnclientes.addActionListener(this);
        this.vistaP.btnfaltantes.addActionListener(this);
        this.vistaP.btnempleados.addActionListener(this);
        this.vistaP.btnproductos.addActionListener(this);
        this.vistaP.btncaja.addActionListener(this);
        this.vistaP.btnproveedores.addActionListener(this);
        this.vistaP.btnhistoricoven.addActionListener(this);
        this.vistaP.btnhistoricocom.addActionListener(this);
        this.vistaP.btncompras.addActionListener(this);
        this.vistaP.btninventarios.addActionListener(this);
        this.vistaP.btnsalir.addActionListener(this);
        this.vistaP.btncambiarusuario.addActionListener(this);
        this.vistaP.btnconf.addActionListener(this);
        
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
        
        if(e.getSource() == vistaP.btncompras){
            vistaCompras vistaCo = new vistaCompras();
            compraDAO daoCo = new compraDAO();
            detallecompraDAO daoDCo = new detallecompraDAO();
            controladorCompra controladorCo = new controladorCompra(vistaCo, daoCo,daoDCo,dni,codC);
            controladorCo.inicializarCompra();
            vistaCo.setLocationRelativeTo(null);
            vistaCo.setVisible(true);            
        }
        
        if(e.getSource() == vistaP.btninventarios){
            vistaMovimientoIventario vistaMI = new vistaMovimientoIventario();
            movimientoDAO daoMI = new movimientoDAO();
            controladorMovimiento controladorMI = new controladorMovimiento(vistaMI, daoMI);
            controladorMI.inicializarMovI();
            vistaMI.setLocationRelativeTo(null);
            vistaMI.setVisible(true);
        }
        
        if(e.getSource() == vistaP.btnhistoricocom){
            vistaHistoricoCompras vistaHCo = new vistaHistoricoCompras();
            compraDAO daoV = new compraDAO();
            detallecompraDAO daoDV = new detallecompraDAO();
            controladorHCompras controladorCo = new controladorHCompras(vistaHCo, daoV,daoDV);
            controladorCo.inicializarHVentas();
            vistaHCo.setVisible(true);
            vistaHCo.setLocationRelativeTo(null);
        }
        
        if(e.getSource() == vistaP.btnhistoricoven){
            vistaHistoricoVentas vistaHV = new vistaHistoricoVentas();
            ventaDAO daoV = new ventaDAO();
            detalleventaDAO daoDV = new detalleventaDAO();
            controladorHVentas controladorV = new controladorHVentas(vistaHV, daoV,daoDV);
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
            double imp = Double.parseDouble(vistaCa.txtcajainicial.getText());
            daoCa.actualizarCI(codC,imp);
            JOptionPane.showMessageDialog(vistaCa, rpta);
            vistaCa.dispose();
        }
        
        if(e.getSource() == vistaCa.btncancelar){
            System.exit(0);
        }
    }
}
