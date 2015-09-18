/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.empleadoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.Empleado;
import vistas.vistaEmpleados;
import vistas.vistaEmpleadosNM;

/**
 *
 * @author Jhunior
 */
public class controladorEmpleadoNM implements ActionListener{
    vistaEmpleadosNM vistaENM = new vistaEmpleadosNM();
    empleadoDAO daoE = new empleadoDAO();
    String dni,nombre,paterno,materno,direccion,telefono,email,dnio,pas,rol;
    
    public controladorEmpleadoNM(vistaEmpleadosNM vistaENM,empleadoDAO daoE){
        this.vistaENM = vistaENM;
        this.daoE = daoE;
        this.vistaENM.btnregistrar.addActionListener(this);
        this.vistaENM.btnmodificar.addActionListener(this);
        this.vistaENM.btnguardarcambio.addActionListener(this);
        this.vistaENM.btncancelar.addActionListener(this);
        this.vistaENM.btnccon.addActionListener(this);
        this.vistaENM.btncrear.addActionListener(this);
    }
    
    public void inicializarEmpleadoNM(Empleado modeloE,boolean vf){
        iniciarCMB();
        vistaENM.setSize(550, 385);
        vistaENM.txtdni.setText(modeloE.getDni());
        dnio = vistaENM.txtdni.getText();
        vistaENM.txtpaterno.setText(modeloE.getApaterno());
        vistaENM.txtmaterno.setText(modeloE.getAmaterno());
        vistaENM.txtnombres.setText(modeloE.getNombres());
        vistaENM.txtdireccion.setText(modeloE.getDireccion());
        vistaENM.txttelefono.setText(modeloE.getTelefono());
        vistaENM.txtemail.setText(modeloE.getCorreo());
        vistaENM.txtpass1.setText(modeloE.getPas());
        vistaENM.txtpass2.setText(modeloE.getPas());
        vistaENM.cmbrol.setSelectedItem(modeloE.getRol());
        vistaENM.btnccon.setEnabled(true);
        vistaENM.btncrear.setEnabled(true);
        edit(vf);
    }
    
    public void iniciarCMB(){
        vistaENM.cmbrol.removeAllItems();
        vistaENM.cmbrol.addItem("");
        vistaENM.cmbrol.addItem("Administrador");
        vistaENM.cmbrol.addItem("Cajero");
        vistaENM.cmbrol.addItem("Contador");
        vistaENM.cmbrol.addItem("Tester");
    }
    
    public void edit(boolean vf){
        vistaENM.txtdni.setEditable(vf);
        vistaENM.txtpaterno.setEditable(vf);
        vistaENM.txtmaterno.setEditable(vf);
        vistaENM.txtnombres.setEditable(vf);
        vistaENM.txtdireccion.setEditable(vf);
        vistaENM.txttelefono.setEditable(vf);
        vistaENM.txtemail.setEditable(vf);
        vistaENM.txtpass1.setEditable(vf);
        vistaENM.txtpass2.setEditable(vf);
    }
    
    public void salir(){
        vistaEmpleados vistaE = new vistaEmpleados();
        controladorEmpleado controladorE = new controladorEmpleado(vistaE,daoE);
        vistaE.setLocationRelativeTo(null);
        vistaE.setVisible(true);
        controladorE.inicializarEmpleado();
        vistaENM.dispose();
    }
    
    public void registrar(){
        dni = vistaENM.txtdni.getText();
            paterno = vistaENM.txtpaterno.getText();
            materno = vistaENM.txtmaterno.getText();
            nombre = vistaENM.txtnombres.getText();
            direccion = vistaENM.txtdireccion.getText();
            telefono = vistaENM.txttelefono.getText();
            email = vistaENM.txtemail.getText();
            if (vistaENM.txtpass1.equals(vistaENM.txtpass2)){
                pas = String.valueOf(vistaENM.txtpass1.getPassword());
            }
            rol = String.valueOf(vistaENM.cmbrol.getSelectedItem());
            String rpta = daoE.insertarEmpleado(dni,nombre,paterno,materno,direccion,telefono,email,pas,rol);
            JOptionPane.showMessageDialog(vistaENM, rpta);
    }
    
    public void editar(){
        dni = vistaENM.txtdni.getText();
        paterno = vistaENM.txtpaterno.getText();
        materno = vistaENM.txtmaterno.getText();
        nombre = vistaENM.txtnombres.getText();
        direccion = vistaENM.txtdireccion.getText();
        telefono = vistaENM.txttelefono.getText();
        email = vistaENM.txtemail.getText();
        int filas = daoE.modificarEmpleado(dni, nombre, paterno, materno, direccion, telefono, email,dnio);
        if (filas>0){
            JOptionPane.showMessageDialog(vistaENM, "Actualizacion Exitosa");
        }else{
            JOptionPane.showMessageDialog(vistaENM, "No se pudo Actualizar");
        }
        edit(false);
        vistaENM.btnguardarcambio.setEnabled(false);
        vistaENM.btnmodificar.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaENM.btnregistrar){
            registrar();
                    vistaENM.btncrear.setEnabled(true);
                    vistaENM.btnccon.setEnabled(true);

            
            //salir();
        }
        if(e.getSource() == vistaENM.btnmodificar){
            edit(true);
            vistaENM.btnguardarcambio.setEnabled(true);
            vistaENM.btnmodificar.setEnabled(false);
        }
        if(e.getSource() == vistaENM.btnguardarcambio){
            editar();
        }
        if(e.getSource() == vistaENM.btnccon){
            vistaENM.btnguardarcambio.setVisible(false);
            vistaENM.btnmodificar.setVisible(false);
            vistaENM.btnregistrar.setVisible(false);
            vistaENM.txtpass1.setEditable(true);
            vistaENM.txtpass2.setEditable(true);
            vistaENM.btnccon.setEnabled(false);
            vistaENM.setSize(550, 485);
        }
        if(e.getSource() == vistaENM.btncrear){
            editar();
            if(!vistaENM.txtdni.getText().equals("")){
                dni = vistaENM.txtdni.getText();
            }else{JOptionPane.showMessageDialog(vistaENM, "Ingrese DNI!!");}
            String pas1 = String.valueOf(vistaENM.txtpass1.getPassword());
            String pas2 = String.valueOf(vistaENM.txtpass2.getPassword());
            if (pas1.equals(pas2)){
                pas = String.valueOf(vistaENM.txtpass1.getPassword());
                rol = String.valueOf(vistaENM.cmbrol.getSelectedItem());
                String rpta = daoE.crearContra(dni, pas, rol);
                JOptionPane.showMessageDialog(vistaENM, rpta);
            }else{JOptionPane.showMessageDialog(vistaENM, "Contraseñas no coinciden");}
            
        }
        if(e.getSource() == vistaENM.btncancelar){
            salir();
        }
    }
    
}
