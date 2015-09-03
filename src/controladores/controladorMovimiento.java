/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.movimientoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vistas.vistaMovimientoIventario;

/**
 *
 * @author Jhunior
 */
public class controladorMovimiento implements ActionListener{
    vistaMovimientoIventario vistaMI = new vistaMovimientoIventario();
    movimientoDAO daoMI = new movimientoDAO();
    
    public controladorMovimiento(vistaMovimientoIventario vistaMI,movimientoDAO daoMI){
        this.vistaMI = vistaMI;
        this.daoMI = daoMI;
        this.vistaMI.btncerrar.addActionListener(this);
        this.vistaMI.btntodo.addActionListener(this);
        this.vistaMI.rbdia.addActionListener(this);
        this.vistaMI.rbentre.addActionListener(this);
        this.vistaMI.btnfiltrar.addActionListener(this);
    }
    
    public void inicializarMovI(){
        vistaMI.rbdia.setSelected(true);
        vistaMI.lbly.setVisible(false);
        vistaMI.dtfecha2.setVisible(false);
        LlenaSalida(vistaMI.tblsalida);
        LlenaEntrada(vistaMI.tblentrada);
    }
    
    public void LlenaSalida(JTable tablaD){
        DefaultTableModel  modeloTS = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
        };
        tablaD.setModel(modeloTS);
        
        modeloTS.addColumn("COD. CAJA.");
        modeloTS.addColumn("FECHA");
        modeloTS.addColumn("COD. VENTA.");
        modeloTS.addColumn("COD. PROD.");
        modeloTS.addColumn("NOM. PROD.");
        modeloTS.addColumn("TOTAL SALIDA");
        
        Object[] columnaS = new Object[6];
        
        int tam = daoMI.listaSalida().size();
        
        for (int i = 0; i < tam; i++) {
            columnaS[0] = daoMI.listaSalida().get(i).getCod_caja();
            columnaS[1] = daoMI.listaSalida().get(i).getFec_caja();
            columnaS[2] = daoMI.listaSalida().get(i).getCod_vc();
            columnaS[3] = daoMI.listaSalida().get(i).getCod_pro();
            columnaS[4] = daoMI.listaSalida().get(i).getNom_pro();
            columnaS[5] = daoMI.listaSalida().get(i).getTotal();
            modeloTS.addRow(columnaS);
        }
    }
    public void LlenaEntrada(JTable tablaD){
        DefaultTableModel  modeloTE = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
        };
        tablaD.setModel(modeloTE);
        
        modeloTE.addColumn("COD. CAJA.");
        modeloTE.addColumn("FECHA");
        modeloTE.addColumn("COD. COMP.");
        modeloTE.addColumn("COD. PROD.");
        modeloTE.addColumn("NOM. PROD.");
        modeloTE.addColumn("TOTAL SALIDA");
        
        Object[] columnaE = new Object[6];
        
        int tam = daoMI.listaEntrada().size();
        
        for (int i = 0; i < tam; i++) {
            columnaE[0] = daoMI.listaEntrada().get(i).getCod_caja();
            columnaE[1] = daoMI.listaEntrada().get(i).getFec_caja();
            columnaE[2] = daoMI.listaEntrada().get(i).getCod_vc();
            columnaE[3] = daoMI.listaEntrada().get(i).getCod_pro();
            columnaE[4] = daoMI.listaEntrada().get(i).getNom_pro();
            columnaE[5] = daoMI.listaEntrada().get(i).getTotal();
            modeloTE.addRow(columnaE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaMI.btncerrar){
            vistaMI.dispose();
        }
        
        if(e.getSource() == vistaMI.rbentre){
            if(vistaMI.rbentre.isSelected()){
                vistaMI.lblfiltrar.setText("Filtrar desde:");
                vistaMI.lbly.setVisible(true);
                vistaMI.dtfecha2.setVisible(true);
            }
        }
        
        if(e.getSource() == vistaMI.btntodo){
            LlenaSalida(vistaMI.tblsalida);
            LlenaEntrada(vistaMI.tblentrada);
        }
        
        if(e.getSource() == vistaMI.rbdia){
            if(vistaMI.rbdia.isSelected()){
                vistaMI.lblfiltrar.setText("Filtrar día:");
                vistaMI.lbly.setVisible(false);
                vistaMI.dtfecha2.setVisible(false);
            }
        }
        
        if(e.getSource() == vistaMI.btnfiltrar){
            /*TABLA SALIDA*/
            DefaultTableModel  modeloTS = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
            };
            vistaMI.tblsalida.setModel(modeloTS);

            modeloTS.addColumn("COD. CAJA.");
            modeloTS.addColumn("FECHA");
            modeloTS.addColumn("COD. VENTA.");
            modeloTS.addColumn("COD. PROD.");
            modeloTS.addColumn("NOM. PROD.");
            modeloTS.addColumn("TOTAL SALIDA");

            Object[] columnaS = new Object[6];
            
            /*TABLA ENTRADA*/
            DefaultTableModel  modeloTE = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
            };
            vistaMI.tblentrada.setModel(modeloTE);

            modeloTE.addColumn("COD. CAJA.");
            modeloTE.addColumn("FECHA");
            modeloTE.addColumn("COD. COMP.");
            modeloTE.addColumn("COD. PROD.");
            modeloTE.addColumn("NOM. PROD.");
            modeloTE.addColumn("TOTAL SALIDA");

            Object[] columnaE = new Object[6];
            
            if(vistaMI.rbdia.isSelected()){
                String fec1 = new SimpleDateFormat("yyyy-MM-dd").format(vistaMI.dtfecha1.getDate());
                /*TABLA SALIDA*/
                int tamsd = daoMI.filtrarDiaS(fec1).size();
        
                for (int i = 0; i < tamsd; i++) {
                    columnaS[0] = daoMI.filtrarDiaS(fec1).get(i).getCod_caja();
                    columnaS[1] = daoMI.filtrarDiaS(fec1).get(i).getFec_caja();
                    columnaS[2] = daoMI.filtrarDiaS(fec1).get(i).getCod_vc();
                    columnaS[3] = daoMI.filtrarDiaS(fec1).get(i).getCod_pro();
                    columnaS[4] = daoMI.filtrarDiaS(fec1).get(i).getNom_pro();
                    columnaS[5] = daoMI.filtrarDiaS(fec1).get(i).getTotal();
                    modeloTS.addRow(columnaS);
                }
                /*TABLA ENTRADA*/
                int tamed = daoMI.filtrarDiaE(fec1).size();
        
                for (int i = 0; i < tamed; i++) {
                    columnaE[0] = daoMI.filtrarDiaE(fec1).get(i).getCod_caja();
                    columnaE[1] = daoMI.filtrarDiaE(fec1).get(i).getFec_caja();
                    columnaE[2] = daoMI.filtrarDiaE(fec1).get(i).getCod_vc();
                    columnaE[3] = daoMI.filtrarDiaE(fec1).get(i).getCod_pro();
                    columnaE[4] = daoMI.filtrarDiaE(fec1).get(i).getNom_pro();
                    columnaE[5] = daoMI.filtrarDiaE(fec1).get(i).getTotal();
                    modeloTE.addRow(columnaE);
                }
            }
            if(vistaMI.rbentre.isSelected()){
                String fec1 = new SimpleDateFormat("yyyy-MM-dd").format(vistaMI.dtfecha1.getDate());
                String fec2 = new SimpleDateFormat("yyyy-MM-dd").format(vistaMI.dtfecha2.getDate());
                /*TABLA SALIDA*/
                int tamse = daoMI.filtrarEntreS(fec1, fec2).size();
        
                for (int i = 0; i < tamse; i++) {
                    columnaS[0] = daoMI.filtrarEntreS(fec1, fec2).get(i).getCod_caja();
                    columnaS[1] = daoMI.filtrarEntreS(fec1, fec2).get(i).getFec_caja();
                    columnaS[2] = daoMI.filtrarEntreS(fec1, fec2).get(i).getCod_vc();
                    columnaS[3] = daoMI.filtrarEntreS(fec1, fec2).get(i).getCod_pro();
                    columnaS[4] = daoMI.filtrarEntreS(fec1, fec2).get(i).getNom_pro();
                    columnaS[5] = daoMI.filtrarEntreS(fec1, fec2).get(i).getTotal();
                    modeloTS.addRow(columnaS);
                }
                /*TABLA ENTRADA*/
                int tamee = daoMI.filtrarEntreE(fec1, fec2).size();
        
                for (int i = 0; i < tamee; i++) {
                    columnaE[0] = daoMI.filtrarEntreE(fec1, fec2).get(i).getCod_caja();
                    columnaE[1] = daoMI.filtrarEntreE(fec1, fec2).get(i).getFec_caja();
                    columnaE[2] = daoMI.filtrarEntreE(fec1, fec2).get(i).getCod_vc();
                    columnaE[3] = daoMI.filtrarEntreE(fec1, fec2).get(i).getCod_pro();
                    columnaE[4] = daoMI.filtrarEntreE(fec1, fec2).get(i).getNom_pro();
                    columnaE[5] = daoMI.filtrarEntreE(fec1, fec2).get(i).getTotal();
                    modeloTE.addRow(columnaE);
                }        
            }
        }
    }
}
