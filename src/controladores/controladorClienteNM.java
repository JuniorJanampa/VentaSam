/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.clienteDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.Cliente;
import vistas.vistaClientes;
import vistas.vistaClientesNM;

/**
 *
 * @author Jhunior
 */
public class controladorClienteNM implements ActionListener{
    vistaClientesNM vistaCNM = new vistaClientesNM();
    clienteDAO daoC = new clienteDAO();
    String dni,nombre,paterno,materno,direccion,telefono,email,dnio;
        
    public controladorClienteNM(vistaClientesNM vistaCN, clienteDAO daoC){
        this.vistaCNM = vistaCN;
        this.daoC = daoC;
        this.vistaCNM.btnregistrar.addActionListener(this);
        this.vistaCNM.btnmodificar.addActionListener(this);
        this.vistaCNM.btnguardarcambio.addActionListener(this);
        this.vistaCNM.btncancelar.addActionListener(this);
    }
    
    public void inicializarClienteNM(Cliente modeloC,boolean vf){
        vistaCNM.txtdni.setText(modeloC.getDni());
        dnio = vistaCNM.txtdni.getText();
        vistaCNM.txtpaterno.setText(modeloC.getApaterno());
        vistaCNM.txtmaterno.setText(modeloC.getAmaterno());
        vistaCNM.txtnombres.setText(modeloC.getNombres());
        vistaCNM.txtdireccion.setText(modeloC.getDireccion());
        vistaCNM.txttelefono.setText(modeloC.getTelefono());
        vistaCNM.txtemail.setText(modeloC.getCorreo());
        edit(vf);
    }
    
    
    public void edit(boolean vf){
        vistaCNM.txtdni.setEditable(vf);
        vistaCNM.txtpaterno.setEditable(vf);
        vistaCNM.txtmaterno.setEditable(vf);
        vistaCNM.txtnombres.setEditable(vf);
        vistaCNM.txtdireccion.setEditable(vf);
        vistaCNM.txttelefono.setEditable(vf);
        vistaCNM.txtemail.setEditable(vf);
    }
    
    public void salir(){
        vistaClientes vistaC = new vistaClientes();
        controladorCliente controladorC = new controladorCliente(vistaC, daoC);
        vistaC.setLocationRelativeTo(null);
        vistaC.setVisible(true);
        controladorC.inicializarCliente();
        vistaCNM.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaCNM.btnregistrar){
            dni = vistaCNM.txtdni.getText();
            paterno = vistaCNM.txtpaterno.getText();
            materno = vistaCNM.txtmaterno.getText();
            nombre = vistaCNM.txtnombres.getText();
            direccion = vistaCNM.txtdireccion.getText();
            telefono = vistaCNM.txttelefono.getText();
            email = vistaCNM.txtemail.getText();
            String rpta = daoC.insertarCliente(dni,nombre,paterno,materno,direccion,telefono,email);
            JOptionPane.showMessageDialog(vistaCNM, rpta);
            salir();
        }
        if(e.getSource() == vistaCNM.btnmodificar){
            edit(true);
            vistaCNM.btnguardarcambio.setEnabled(true);
            vistaCNM.btnmodificar.setEnabled(false);
        }
        if(e.getSource() == vistaCNM.btnguardarcambio){
            dni = vistaCNM.txtdni.getText();
            paterno = vistaCNM.txtpaterno.getText();
            materno = vistaCNM.txtmaterno.getText();
            nombre = vistaCNM.txtnombres.getText();
            direccion = vistaCNM.txtdireccion.getText();
            telefono = vistaCNM.txttelefono.getText();
            email = vistaCNM.txtemail.getText();
            int filas = daoC.modificarCliente(dni, nombre, paterno, materno, direccion, telefono, email,dnio);
            if (filas>0){
                JOptionPane.showMessageDialog(vistaCNM, "Actualizacion Exitosa");
            }else{
                JOptionPane.showMessageDialog(vistaCNM, "No se pudo Actualizar");
            }
            edit(false);
            vistaCNM.btnguardarcambio.setEnabled(false);
            vistaCNM.btnmodificar.setEnabled(true);
        }
        
        if(e.getSource() == vistaCNM.btncancelar){
            salir();
        }
        
    }
}
