/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.proveedoresDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.Proveedores;
import vistas.vistaProveedores;
import vistas.vistaProveedoresNM;

/**
 *
 * @author Jhunior
 */
public class controladorProveedorNM implements ActionListener{
    vistaProveedoresNM vistaProvNM = new vistaProveedoresNM();
    proveedoresDAO daoProv = new proveedoresDAO();
    
    String cod,ruc,rs,dir,tele,corr,nroc,ban,cont,telec,correoc;
    
    public controladorProveedorNM(vistaProveedoresNM vistaProvNM,proveedoresDAO daoProv){
        this.vistaProvNM = vistaProvNM;
        this.daoProv = daoProv;
        this.vistaProvNM.btnregistrar.addActionListener(this);
        this.vistaProvNM.btnmodificar.addActionListener(this);
        this.vistaProvNM.btnguardarcambio.addActionListener(this);
        this.vistaProvNM.btncancelar.addActionListener(this);
    }
    
    public void inicializarProveedoreNM(Proveedores modeloProv,boolean vf){
        vistaProvNM.txtcodigo.setText(modeloProv.getCodigo());
        vistaProvNM.txtruc.setText(modeloProv.getRuc());
        vistaProvNM.txtrazonsocial.setText(modeloProv.getRazonsocial());
        vistaProvNM.txtdireccion.setText(modeloProv.getDireccion());
        vistaProvNM.txttelefono.setText(modeloProv.getTelefono());
        vistaProvNM.txtcorreo.setText(modeloProv.getCorreo());
        vistaProvNM.txtnro_cuenta.setText(modeloProv.getNrocuenta());
        vistaProvNM.txtbanco.setText(modeloProv.getBanco());
        vistaProvNM.txtcontacto.setText(modeloProv.getContacto());
        vistaProvNM.txttelefono_contacto.setText(modeloProv.getTele_contacto());
        vistaProvNM.txtcorreo_contacto.setText(modeloProv.getCorreo_contacto());
        edit(vf);
    }
    
    public void edit(boolean vf){
        vistaProvNM.txtruc.setEditable(vf);
        vistaProvNM.txtrazonsocial.setEditable(vf);
        vistaProvNM.txtdireccion.setEditable(vf);
        vistaProvNM.txttelefono.setEditable(vf);
        vistaProvNM.txtcorreo.setEditable(vf);
        vistaProvNM.txtnro_cuenta.setEditable(vf);
        vistaProvNM.txtbanco.setEditable(vf);
        vistaProvNM.txtcontacto.setEditable(vf);
        vistaProvNM.txttelefono_contacto.setEditable(vf);
        vistaProvNM.txtcorreo_contacto.setEditable(vf);
    }
    
    
    public void salir(){
        vistaProveedores vistaProv = new vistaProveedores();
        controladorProveedor controladorProv = new controladorProveedor(vistaProv, daoProv);
        vistaProv.setLocationRelativeTo(null);
        vistaProv.setVisible(true);
        controladorProv.inicializarProveedor();
        vistaProvNM.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaProvNM.btnregistrar){
            ruc = vistaProvNM.txtruc.getText();
            rs = vistaProvNM.txtrazonsocial.getText();
            dir = vistaProvNM.txtdireccion.getText();
            tele = vistaProvNM.txttelefono.getText();
            corr = vistaProvNM.txtcorreo.getText();
            nroc = vistaProvNM.txtnro_cuenta.getText();
            ban = vistaProvNM.txtbanco.getText();
            cont = vistaProvNM.txtcontacto.getText();
            telec = vistaProvNM.txttelefono_contacto.getText();
            correoc = vistaProvNM.txtcorreo_contacto.getText();
            String rpta = daoProv.insertaProveedor(ruc, rs, dir, tele, corr, nroc, ban, cont, telec, correoc);
            if(rpta != null){
                JOptionPane.showMessageDialog(vistaProvNM, rpta);
                salir();
            }
        }
        if(e.getSource() == vistaProvNM.btnmodificar){
            edit(true);
            vistaProvNM.btnguardarcambio.setEnabled(true);
            vistaProvNM.btnmodificar.setEnabled(false);
        }
        if(e.getSource() == vistaProvNM.btnguardarcambio){
            cod = vistaProvNM.txtcodigo.getText();
            ruc = vistaProvNM.txtruc.getText();
            rs = vistaProvNM.txtrazonsocial.getText();
            dir = vistaProvNM.txtdireccion.getText();
            tele = vistaProvNM.txttelefono.getText();
            corr = vistaProvNM.txtcorreo.getText();
            nroc = vistaProvNM.txtnro_cuenta.getText();
            ban = vistaProvNM.txtbanco.getText();
            cont = vistaProvNM.txtcontacto.getText();
            telec = vistaProvNM.txttelefono_contacto.getText();
            correoc = vistaProvNM.txtcorreo_contacto.getText();
            int filas = daoProv.modificarProveedor(cod,ruc, rs, dir, tele, corr, nroc, ban, cont, telec, correoc);
            if (filas>0){
                JOptionPane.showMessageDialog(vistaProvNM, "Actualizacion Exitosa");
            }else{
                JOptionPane.showMessageDialog(vistaProvNM, "No se pudo Actualizar");
            }
            edit(false);
            vistaProvNM.btncancelar.setText("Cerrar");
            vistaProvNM.btnguardarcambio.setEnabled(false);
            vistaProvNM.btnmodificar.setEnabled(true);
        }
        if(e.getSource() == vistaProvNM.btncancelar){
            salir();
        }
    }
    
}
