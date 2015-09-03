/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.cajaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import modelos.Caja;
import vistas.vistaCaja;
import vistas.vistaCajaC;
import vistas.vistaCajaH;

/**
 *
 * @author Jhunior
 */
public class controladorCaja implements ActionListener{
    vistaCaja vistaCaj = new vistaCaja();
    vistaCajaC vistaCaC = new vistaCajaC(vistaCaj, true);
    vistaCajaH vistaCaH = new vistaCajaH(vistaCaj, true);
    
    cajaDAO daoCaj = new cajaDAO();
    
    Caja modeloCa;
    
    double vt,vp,vc,ct,tot;
    
    public controladorCaja(vistaCaja vistaCaj, cajaDAO daoCaj){
        this.vistaCaj = vistaCaj;
        this.daoCaj = daoCaj;
        
        this.vistaCaj.btncerrar.addActionListener(this);
        this.vistaCaj.btncierre.addActionListener(this);
        this.vistaCaj.btnhistorial.addActionListener(this);
        
        this.vistaCaC.btncerrarcaja.addActionListener(this);
        this.vistaCaC.btncerrar.addActionListener(this);
    }
    
    public void incializarCaja(){
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        
        String fecha = String.valueOf(f.format(now));
        
        vistaCaj.txtfecha.setText(fecha);
        vistaCaj.txtfecha.setEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaCaj.btncerrar){
            vistaCaj.dispose();
        }
        if(e.getSource() == vistaCaj.btncierre){
            modeloCa = new Caja();
            
            vt = daoCaj.ventasF(vistaCaj.txtfecha.getText());
            vp = daoCaj.ventasP(vistaCaj.txtfecha.getText(), "1");
            vc = vt - vp;
            ct = daoCaj.comprasF(vistaCaj.txtfecha.getText());
            tot = vt - ct;            
            
            vistaCaC.txttotcli.setText(String.valueOf(vc));
            vistaCaC.txttotcli.setEditable(false);
            vistaCaC.txttotgen.setText(String.valueOf(vp));
            vistaCaC.txttotgen.setEditable(false);
            vistaCaC.txtsubtot.setText(String.valueOf(vt));
            vistaCaC.txtsubtot.setEditable(false);
            
            vistaCaC.txttotgas.setText(String.valueOf(ct));
            vistaCaC.txttotal.setText(String.valueOf(tot));
            
            String cod_c = daoCaj.codigoCaja();
            modeloCa = daoCaj.buscarCaja(cod_c);
            
            if(modeloCa.getE_din() != 0.0){
                vistaCaC.btncerrarcaja.setEnabled(false);
                vistaCaC.btncerrarcaja.setText("Caja Cerrada");
            }
            
            vistaCaC.setVisible(true);
        }
        
        if(e.getSource() == vistaCaC.btncerrarcaja){
            if(JOptionPane.showConfirmDialog(vistaCaC,"Seguro de Cerrar Caja?","Scam Ventas",JOptionPane.OK_CANCEL_OPTION)==0){
                String cod_c = daoCaj.codigoCaja();
                String rpta = daoCaj.actualizarCierre(cod_c, vt, ct);
                JOptionPane.showMessageDialog(vistaCaC, rpta);
                vistaCaC.dispose();
            }
            
        }
        if(e.getSource() == vistaCaC.btncerrar){
            vistaCaC.dispose();
        }
    }
}
