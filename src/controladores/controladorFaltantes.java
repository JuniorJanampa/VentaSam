/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.faltantesDAO;
import DAO.proveedoresDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import vistas.vistaFaltantes;

/**
 *
 * @author Jhunior
 */
public class controladorFaltantes implements ActionListener{
    vistaFaltantes vistaF = new vistaFaltantes();
    faltantesDAO daoF = new faltantesDAO();
    
    String[] cod_prov;
    
    public controladorFaltantes(vistaFaltantes vistaF, faltantesDAO daoF){
        this.vistaF = vistaF;
        this.daoF = daoF;
        this.vistaF.btncerrar.addActionListener(this);
        this.vistaF.cmbprov.addActionListener(this);
    }
    
    
    public void incializarFaltantes(){
        iniciarCMB();
        mostrarDatos("");
    }
    
    
    public void iniciarCMB(){
        vistaF.cmbprov.removeAllItems();
        vistaF.cmbprov.addItem("");
        proveedoresDAO daoProv = new proveedoresDAO();
        int filas = daoProv.listarProveedores().size();
        cod_prov = new String[filas];
        for (int i = 0; i < filas; i++) {
            cod_prov[i] = daoProv.listarProveedores().get(i).getCodigo();
            vistaF.cmbprov.addItem(daoProv.listarProveedores().get(i).getRazonsocial());
        }
    }
    
    public void mostrarDatos(String razon){
        DefaultTableModel  modeloT = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                return false;
                }
            };
            vistaF.tbldatos.setModel(modeloT);

            modeloT.addColumn("COD. PRODUCTO");
            modeloT.addColumn("NOMBRE");
            modeloT.addColumn("STOK");
            modeloT.addColumn("UND.MED.");
            modeloT.addColumn("PREC. COM.");
            modeloT.addColumn("PROVEEDOR");

            Object[] columna = new Object[6];

            int numRegistros = daoF.filtrarProv(razon).size();

            for (int i = 0; i < numRegistros; i++) {
                columna[0] = daoF.filtrarProv(razon).get(i).getCodigo();
                columna[1] = daoF.filtrarProv(razon).get(i).getNombre();
                columna[2] = daoF.filtrarProv(razon).get(i).getStok();
                columna[3] = daoF.filtrarProv(razon).get(i).getUnidad_medida();
                columna[4] = daoF.filtrarProv(razon).get(i).getPrecio_compra();
                columna[5] = daoF.filtrarProv(razon).get(i).getRazons();
                modeloT.addRow(columna);
            }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaF.cmbprov){
            String razon = (String) vistaF.cmbprov.getSelectedItem();
            mostrarDatos(razon);            
        }
        
        if(e.getSource() == vistaF.btncerrar){
            vistaF.dispose();
        }
    }
    
}
