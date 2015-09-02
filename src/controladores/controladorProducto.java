/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.productoDAO;
import DAO.proveedoresDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Productos;
import vistas.vistaProductos;
import vistas.vistaProductosNM;

/**
 *
 * @author Jhunior
 */
public class controladorProducto implements ActionListener,KeyListener, MouseListener{
    vistaProductos vistaProd = new vistaProductos();
    productoDAO daoProd = new productoDAO();
    Productos modeloProd = new Productos();
    ArrayList codsprov = new ArrayList();
    String[] cod_prov;
    
    public controladorProducto(vistaProductos vistaProd, productoDAO daoProd){
        this.vistaProd = vistaProd;
        this.daoProd = daoProd;
        this.vistaProd.btnnuevo.addActionListener(this);
        this.vistaProd.btnborrar.addActionListener(this);
        this.vistaProd.btnsalir.addActionListener(this);
        this.vistaProd.cmbprov.addActionListener(this);
        this.vistaProd.txtfiltrarprod.addKeyListener(this);
        this.vistaProd.tbldatos.addMouseListener(this);
    }
    
    public void inicializarProducto(){
        iniciarCMB();
        LLenarTabla(vistaProd.tbldatos);
    }
    
    public void LLenarTabla(JTable tablaD){
        DefaultTableModel  modeloT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
        };
        tablaD.setModel(modeloT);
        
        modeloT.addColumn("COD. PRODUCTO");
        modeloT.addColumn("NOMBRE");
        modeloT.addColumn("STOK");
        modeloT.addColumn("UND.MED.");
        modeloT.addColumn("PROVEEDOR");
        
        Object[] columna = new Object[5];

        int numRegistros = daoProd.listarProductos().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = daoProd.listarProductos().get(i).getCodigo();
            columna[1] = daoProd.listarProductos().get(i).getNombre();
            columna[2] = daoProd.listarProductos().get(i).getStok();
            columna[3] = daoProd.listarProductos().get(i).getUnidad_medida();
            columna[4] = daoProd.listarProductos().get(i).getRazons();
            modeloT.addRow(columna);
            codsprov.add(daoProd.listarProductos().get(i).getCod_prov());
        }
    }
    
    public void iniciarCMB(){
        vistaProd.cmbprov.removeAllItems();
        vistaProd.cmbprov.addItem("");
        proveedoresDAO daoProv = new proveedoresDAO();
        int filas = daoProv.listarProveedores().size();
        cod_prov = new String[filas];
        for (int i = 0; i < filas; i++) {
            cod_prov[i] = daoProv.listarProveedores().get(i).getCodigo();
            vistaProd.cmbprov.addItem(daoProv.listarProveedores().get(i).getRazonsocial());
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaProd.btnnuevo){
            vistaProductosNM vistaProdNM = new vistaProductosNM();
            vistaProdNM.lbltitulo.setText("NUEVO PRODUCTO");
            vistaProdNM.btnmodificar.setVisible(false);
            vistaProdNM.btnguardarcambio.setVisible(false);
            vistaProdNM.jLabel1.setVisible(false);
            vistaProdNM.txtcodigo.setVisible(false);
            controladorProductoNM controladorProdNM = new controladorProductoNM(vistaProdNM, daoProd);
            controladorProdNM.inicializarProdNM(modeloProd,true);
            vistaProdNM.setLocationRelativeTo(null);
            vistaProdNM.setVisible(true);
            vistaProd.dispose();
        }
        if(e.getSource() == vistaProd.btnborrar){
            int filInicio = vistaProd.tbldatos.getSelectedRow();
            int numfilas = vistaProd.tbldatos.getSelectedRowCount();
            ArrayList<String> listaruc = new ArrayList<>();
            String ruc;
            if(filInicio>=0){
                for(int i = 0; i<numfilas; i++){
                    ruc = String.valueOf(vistaProd.tbldatos.getValueAt(i+filInicio, 0));
                    listaruc.add(i, ruc);
                }

                for(int j = 0; j<numfilas; j++){
                    int rpta = JOptionPane.showConfirmDialog(null, "Desea eliminar registro: "+listaruc.get(j)+"? ");
                    if(rpta==0){
                        daoProd.eliminarProducto(listaruc.get(j));
                    }
                }
                LLenarTabla(vistaProd.tbldatos);
            }else{
                JOptionPane.showMessageDialog(null, "Elija al menos un registro para eliminar.");
            }
        }
        
        if(e.getSource() == vistaProd.cmbprov){
            String razon = (String) vistaProd.cmbprov.getSelectedItem();
            DefaultTableModel  modeloT = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                return false;
                }
            };
            vistaProd.tbldatos.setModel(modeloT);

            modeloT.addColumn("COD. PRODUCTO");
            modeloT.addColumn("NOMBRE");
            modeloT.addColumn("STOK");
            modeloT.addColumn("UND.MED.");
            modeloT.addColumn("PROVEEDOR");

            Object[] columna = new Object[5];

            int numRegistros = daoProd.buscarProdp(razon).size();

            for (int i = 0; i < numRegistros; i++) {
                columna[0] = daoProd.buscarProdp(razon).get(i).getCodigo();
                columna[1] = daoProd.buscarProdp(razon).get(i).getNombre();
                columna[2] = daoProd.buscarProdp(razon).get(i).getStok();
                columna[3] = daoProd.buscarProdp(razon).get(i).getUnidad_medida();
                columna[4] = daoProd.buscarProdp(razon).get(i).getRazons();
                modeloT.addRow(columna);
            }
            
        }
        if(e.getSource() == vistaProd.btnsalir){
            vistaProd.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == vistaProd.txtfiltrarprod){
            String nombre = vistaProd.txtfiltrarprod.getText();
            DefaultTableModel  modeloT = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                return false;
                }
            };
            vistaProd.tbldatos.setModel(modeloT);

            modeloT.addColumn("COD. PRODUCTO");
            modeloT.addColumn("NOMBRE");
            modeloT.addColumn("STOK");
            modeloT.addColumn("UND.MED.");
            modeloT.addColumn("PROVEEDOR");

            Object[] columna = new Object[5];

            int numRegistros = daoProd.buscarProdn(nombre).size();

            for (int i = 0; i < numRegistros; i++) {
                columna[0] = daoProd.buscarProdn(nombre).get(i).getCodigo();
                columna[1] = daoProd.buscarProdn(nombre).get(i).getNombre();
                columna[2] = daoProd.buscarProdn(nombre).get(i).getStok();
                columna[3] = daoProd.buscarProdn(nombre).get(i).getUnidad_medida();
                columna[4] = daoProd.buscarProdn(nombre).get(i).getRazons();
                modeloT.addRow(columna);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vistaProd.tbldatos){
            if(e.getClickCount() == 2){
                String codprod = (String)vistaProd.tbldatos.getValueAt(vistaProd.tbldatos.getSelectedRow(),0);
                String codprov = (String) codsprov.get(vistaProd.tbldatos.getSelectedRow());
                modeloProd = daoProd.buscarProd(codprod,codprov);
                vistaProductosNM vistaProdNM = new vistaProductosNM();
                controladorProductoNM controladorProdNM = new controladorProductoNM(vistaProdNM, daoProd);
                vistaProdNM.lbltitulo.setText("PRODUCTO");
                vistaProdNM.btnregistrar.setVisible(false);
                vistaProdNM.btnguardarcambio.setEnabled(false);
                controladorProdNM.inicializarProdNM(modeloProd, false);
                vistaProdNM.setLocationRelativeTo(null);
                vistaProdNM.setVisible(true);
                vistaProd.dispose();
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
