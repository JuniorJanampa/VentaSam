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
import vistas.vistaEmpresa;

/**
 *
 * @author edson
 */
public class controladorEmpresa implements ActionListener{
    
    vistaEmpresa vistaEm = new vistaEmpresa();
    empleadoDAO daoEm = new empleadoDAO();
    String nomemp,tipoorg,ruc,dir,tel,cor;
    
    Empleado modeloEm;

    public controladorEmpresa(vistaEmpresa vistaEm, empleadoDAO daoEm) {
        this.vistaEm=vistaEm;
        this.daoEm=daoEm;
        this.vistaEm.btnguardar.addActionListener(this);
        this.vistaEm.btnsalir.addActionListener(this);
        
    }
    public void inicializarEmpresa(){
        modeloEm= new Empleado();
        modeloEm=daoEm.VerEmpresa();
        vistaEm.txtnomemp.setText(modeloEm.getNombres());
        vistaEm.txttiporg.setText(modeloEm.getApaterno());
        vistaEm.txtruc.setText(modeloEm.getAmaterno());
        vistaEm.txtdirec.setText(modeloEm.getDireccion());
        vistaEm.txttel.setText(modeloEm.getTelefono());
        vistaEm.txtcorreo.setText(modeloEm.getCorreo());
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==vistaEm.btnguardar){
          nomemp=vistaEm.txtnomemp.getText();
          tipoorg=vistaEm.txttiporg.getText();
          ruc=vistaEm.txtruc.getText();
          dir=vistaEm.txtdirec.getText();
          tel=vistaEm.txttel.getText();
          cor=vistaEm.txtcorreo.getText();
          int r=daoEm.ActualizarEmpresa(nomemp, tipoorg, ruc, dir, tel, cor);
          if (r>0){
              JOptionPane.showMessageDialog(vistaEm, "Actualización Existosa");
              vistaEm.dispose();
          } else {
              JOptionPane.showMessageDialog(vistaEm, "No se puede actualizar");
          }
      }
      if (e.getSource()==vistaEm.btnsalir) {
                vistaEm.dispose();
            }
    }
    
}
