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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import vistas.vistaFaltantes;

/**
 *
 * @author Jhunior
 */
public class controladorFaltantes implements ActionListener{
    vistaFaltantes vistaF = new vistaFaltantes();
    faltantesDAO daoF = new faltantesDAO();
    
    
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
        for (int i = 0; i < filas; i++) {
            vistaF.cmbprov.addItem(daoProv.listarProveedores().get(i).getRazonsocial());
        }
    }
    
    public CustomImageIcon mostrarImagen(InputStream is){
        CustomImageIcon imagen = null;
        BufferedImage bi;
        try {
            bi = ImageIO.read(is);
            imagen = new CustomImageIcon(bi);
        } catch (IOException ex) {
            Logger.getLogger(controladorProductoNM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imagen;
    }
    
    public void centrar(JTable tablaD){
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        tablaD.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
        tablaD.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
        tablaD.getColumnModel().getColumn(3).setCellRenderer(modelocentrar);
        tablaD.getColumnModel().getColumn(4).setCellRenderer(modelocentrar);
        tablaD.getColumnModel().getColumn(5).setCellRenderer(modelocentrar);
        tablaD.getColumnModel().getColumn(6).setCellRenderer(modelocentrar);
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
            vistaF.tbldatos.setDefaultRenderer(Object.class, new IconCellRender());
            vistaF.tbldatos.setRowHeight(80);
            
            modeloT.addColumn("IMAGEN");
            modeloT.addColumn("COD. PRODUCTO");
            modeloT.addColumn("NOMBRE");
            modeloT.addColumn("STOK");
            modeloT.addColumn("UND.MED.");
            modeloT.addColumn("PREC. COM.");
            modeloT.addColumn("PROVEEDOR");

            Object[] columna = new Object[7];

            int numRegistros = daoF.filtrarProv(razon).size();

            for (int i = 0; i < numRegistros; i++) {
                if(daoF.filtrarProv(razon).get(i).getMimagen() == null){
                    columna[0] = new JLabel(new CustomImageIcon(getClass().getResource("/imagenes/nodisponible.png")));
                }else{
                    columna[0] = new JLabel(mostrarImagen(daoF.filtrarProv(razon).get(i).getMimagen()));
                }
                columna[1] = daoF.filtrarProv(razon).get(i).getCodigo();
                columna[2] = daoF.filtrarProv(razon).get(i).getNombre();
                columna[3] = daoF.filtrarProv(razon).get(i).getStok();
                columna[4] = daoF.filtrarProv(razon).get(i).getUnidad_medida();
                columna[5] = daoF.filtrarProv(razon).get(i).getPrecio_compra();
                columna[6] = daoF.filtrarProv(razon).get(i).getRazons();
                modeloT.addRow(columna);
                centrar(vistaF.tbldatos);
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
