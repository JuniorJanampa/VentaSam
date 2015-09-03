/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.cajaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Caja;
import vistas.vistaCaja;
import vistas.vistaCajaC;
import vistas.vistaCajaH;

/**
 *
 * @author Jhunior
 */
public class controladorCaja implements ActionListener, MouseListener{
    vistaCaja vistaCaj = new vistaCaja();
    vistaCajaC vistaCaC = new vistaCajaC(vistaCaj, true);
    vistaCajaH vistaCaH = new vistaCajaH(vistaCaj, true);
    
    cajaDAO daoCaj = new cajaDAO();
    
    Caja modeloCa;
    
    double vt,vp,vc,ct,tot;
    
    double t_ent,t_sal;
    
    public controladorCaja(vistaCaja vistaCaj, cajaDAO daoCaj){
        this.vistaCaj = vistaCaj;
        this.daoCaj = daoCaj;
        
        this.vistaCaj.btncerrar.addActionListener(this);
        this.vistaCaj.btncierre.addActionListener(this);
        this.vistaCaj.btnhistorial.addActionListener(this);
        
        this.vistaCaC.btncerrarcaja.addActionListener(this);
        this.vistaCaC.btncerrar.addActionListener(this);
        
        this.vistaCaH.btncerrar.addActionListener(this);
        this.vistaCaH.btnfiltrar.addActionListener(this);
        this.vistaCaH.btntodos.addActionListener(this);
        this.vistaCaH.rbdia.addActionListener(this);
        this.vistaCaH.rbentre.addActionListener(this);
        this.vistaCaH.tblcaja.addMouseListener(this);
    }
    
    public void incializarCaja(){
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        
        String fecha = String.valueOf(f.format(now));
        
        vistaCaj.txtfecha.setText(fecha);
        vistaCaj.txtfecha.setEditable(false);
    }
    
    public void iniciaHistorial(){
        vistaCaH.rbdia.setSelected(true);
        vistaCaH.lbly.setVisible(false);
        vistaCaH.dtfecha2.setVisible(false);
        LLenarTablaProd(vistaCaH.tblcaja);
    }
    
    public void LLenarTablaProd(JTable tablaD){
        t_ent = 0.0;
        t_sal = 0.0;
        DefaultTableModel  modeloT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
        };
        tablaD.setModel(modeloT);
        
        modeloT.addColumn("COD. CAJA");
        modeloT.addColumn("FECHA");
        modeloT.addColumn("DINERO INICIAL");
        
        Object[] columna = new Object[3];

        int numRegistros = daoCaj.listarCaja().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = daoCaj.listarCaja().get(i).getCodigo();
            columna[1] = daoCaj.listarCaja().get(i).getFecha();
            columna[2] = daoCaj.listarCaja().get(i).getD_ini();
            t_ent = t_ent + daoCaj.listarCaja().get(i).getE_din();
            t_sal = t_sal + daoCaj.listarCaja().get(i).getS_dni();
            modeloT.addRow(columna);
        }
        
        vistaCaH.txttote.setText(String.valueOf(t_ent));
        vistaCaH.txttots.setText(String.valueOf(t_sal));
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
            vistaCaC.setLocationRelativeTo(null);
            vistaCaC.setVisible(true);
        }
        
        if(e.getSource() == vistaCaj.btnhistorial){
            iniciaHistorial();
            vistaCaH.setLocationRelativeTo(null);
            vistaCaH.setVisible(true);
        }
        
        /*CIERRE DE CAJA*/
        
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
        
        /*HISTORIAL CAJA*/
        if(e.getSource() == vistaCaH.btncerrar){
            vistaCaH.dispose();
        }
        
        if(e.getSource() == vistaCaH.rbdia){
            vistaCaH.lblfiltrar.setText("Filtrar día:");
            vistaCaH.lbly.setVisible(false);
            vistaCaH.dtfecha2.setVisible(false);
        }
        
        if(e.getSource() == vistaCaH.rbentre){
            vistaCaH.lblfiltrar.setText("Filtrar desde:");
            vistaCaH.lbly.setVisible(true);
            vistaCaH.dtfecha2.setVisible(true);
        }
        
        if(e.getSource() == vistaCaH.btntodos){
            LLenarTablaProd(vistaCaH.tblcaja);
        }
        
        if(e.getSource() == vistaCaH.btnfiltrar){
            t_ent = 0.0;
            t_sal = 0.0;
            DefaultTableModel  modeloT = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
                }
            };
            vistaCaH.tblcaja.setModel(modeloT);

            modeloT.addColumn("COD. CAJA");
            modeloT.addColumn("FECHA");
            modeloT.addColumn("DINERO INICIAL");

            Object[] columna = new Object[3];
            if(vistaCaH.rbdia.isSelected()){
                String fec1 = new SimpleDateFormat("yyyy-MM-dd").format(vistaCaH.dtfecha1.getDate());
                int numRegistros = daoCaj.porDia(fec1).size();

                for (int i = 0; i < numRegistros; i++) {
                    columna[0] = daoCaj.porDia(fec1).get(i).getCodigo();
                    columna[1] = daoCaj.porDia(fec1).get(i).getFecha();
                    columna[2] = daoCaj.porDia(fec1).get(i).getD_ini();
                    t_ent = t_ent + daoCaj.porDia(fec1).get(i).getE_din();
                    t_sal = t_sal + daoCaj.porDia(fec1).get(i).getS_dni();
                    modeloT.addRow(columna);
                }

                vistaCaH.txttote.setText(String.valueOf(t_ent));
                vistaCaH.txttots.setText(String.valueOf(t_sal));
            }
            if(vistaCaH.rbentre.isSelected()){
                String fec1 = new SimpleDateFormat("yyyy-MM-dd").format(vistaCaH.dtfecha1.getDate());
                String fec2 = new SimpleDateFormat("yyyy-MM-dd").format(vistaCaH.dtfecha2.getDate());
                
                int numRegistros = daoCaj.entreDias(fec1, fec2).size();

                for (int i = 0; i < numRegistros; i++) {
                    columna[0] = daoCaj.entreDias(fec1, fec2).get(i).getCodigo();
                    columna[1] = daoCaj.entreDias(fec1, fec2).get(i).getFecha();
                    columna[2] = daoCaj.entreDias(fec1, fec2).get(i).getD_ini();
                    t_ent = t_ent + daoCaj.entreDias(fec1, fec2).get(i).getE_din();
                    t_sal = t_sal + daoCaj.entreDias(fec1, fec2).get(i).getS_dni();
                    modeloT.addRow(columna);
                }

                vistaCaH.txttote.setText(String.valueOf(t_ent));
                vistaCaH.txttots.setText(String.valueOf(t_sal));
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vistaCaH.tblcaja){
            if(e.getClickCount() == 2){                                
                String cod_ven = (String)vistaCaH.tblcaja.getValueAt(vistaCaH.tblcaja.getSelectedRow(),0);
                
                modeloCa = new Caja();
                
                modeloCa = daoCaj.buscarCaja(cod_ven);
                
                vistaCaH.txtdini.setText(String.valueOf(modeloCa.getD_ini()));
                vistaCaH.txtedin.setText(String.valueOf(modeloCa.getE_din()));
                vistaCaH.txtsdin.setText(String.valueOf(modeloCa.getS_dni()));
                
                double total = (modeloCa.getD_ini()+modeloCa.getE_din()) - modeloCa.getS_dni();
                vistaCaH.txttotal.setText(String.valueOf(total));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
