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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
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
        tablaD.setDefaultRenderer(Object.class, new IconCellRender());
        
        tablaD.setRowHeight(80);
        
        modeloT.addColumn("IMAGEN");
        modeloT.addColumn("COD. PRODUCTO");
        modeloT.addColumn("NOMBRE");
        modeloT.addColumn("STOK");
        modeloT.addColumn("UND.MED.");
        modeloT.addColumn("PROVEEDOR");
        
        Object[] columna = new Object[6];

        int numRegistros = daoProd.listarProductos().size();

        for (int i = 0; i < numRegistros; i++) {
            if(daoProd.listarProductos().get(i).getMimagen() == null){
                columna[0] = new JLabel(new CustomImageIcon(getClass().getResource("/imagenes/nodisponible.png")));
            }else{
                columna[0] = new JLabel(mostrarImagen(daoProd.listarProductos().get(i).getMimagen()));
            }
            columna[1] = daoProd.listarProductos().get(i).getCodigo();
            columna[2] = daoProd.listarProductos().get(i).getNombre();
            columna[3] = daoProd.listarProductos().get(i).getStok();
            columna[4] = daoProd.listarProductos().get(i).getUnidad_medida();
            columna[5] = daoProd.listarProductos().get(i).getRazons();
            modeloT.addRow(columna);
            centrar(tablaD);
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
            ArrayList<String> listacod = new ArrayList<>();
            String cod;
            if(filInicio>=0){
                for(int i = 0; i<numfilas; i++){
                    cod = String.valueOf(vistaProd.tbldatos.getValueAt(i+filInicio, 1));
                    listacod.add(i, cod);
                }

                for(int j = 0; j<numfilas; j++){
                    int rpta = JOptionPane.showConfirmDialog(null, "Desea eliminar registro: "+listacod.get(j)+"? ");
                    if(rpta==0){
                        daoProd.eliminarProducto(listacod.get(j));
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
            vistaProd.tbldatos.setDefaultRenderer(Object.class, new IconCellRender());
        
            vistaProd.tbldatos.setRowHeight(80);
        
            modeloT.addColumn("IMAGEN");
            modeloT.addColumn("COD. PRODUCTO");
            modeloT.addColumn("NOMBRE");
            modeloT.addColumn("STOK");
            modeloT.addColumn("UND.MED.");
            modeloT.addColumn("PROVEEDOR");

            Object[] columna = new Object[6];

            int numRegistros = daoProd.buscarProdp(razon).size();

            for (int i = 0; i < numRegistros; i++) {
                if(daoProd.buscarProdp(razon).get(i).getMimagen() == null){
                    columna[0] = new JLabel(new CustomImageIcon(getClass().getResource("/imagenes/nodisponible.png")));
                }else{
                    columna[0] = new JLabel(mostrarImagen(daoProd.buscarProdp(razon).get(i).getMimagen()));
                }
                columna[1] = daoProd.buscarProdp(razon).get(i).getCodigo();
                columna[2] = daoProd.buscarProdp(razon).get(i).getNombre();
                columna[3] = daoProd.buscarProdp(razon).get(i).getStok();
                columna[4] = daoProd.buscarProdp(razon).get(i).getUnidad_medida();
                columna[5] = daoProd.buscarProdp(razon).get(i).getRazons();
                modeloT.addRow(columna);
                centrar(vistaProd.tbldatos);
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
            vistaProd.tbldatos.setDefaultRenderer(Object.class, new IconCellRender());
        
            vistaProd.tbldatos.setRowHeight(80);
        
            modeloT.addColumn("IMAGEN");
            modeloT.addColumn("COD. PRODUCTO");
            modeloT.addColumn("NOMBRE");
            modeloT.addColumn("STOK");
            modeloT.addColumn("UND.MED.");
            modeloT.addColumn("PROVEEDOR");

            Object[] columna = new Object[6];

            int numRegistros = daoProd.buscarProdn(nombre).size();

            for (int i = 0; i < numRegistros; i++) {
                if(daoProd.buscarProdn(nombre).get(i).getMimagen() == null){
                    columna[0] = new JLabel(new CustomImageIcon(getClass().getResource("/imagenes/nodisponible.png")));
                }else{
                    columna[0] = new JLabel(mostrarImagen(daoProd.buscarProdn(nombre).get(i).getMimagen()));
                }
                columna[1] = daoProd.buscarProdn(nombre).get(i).getCodigo();
                columna[2] = daoProd.buscarProdn(nombre).get(i).getNombre();
                columna[3] = daoProd.buscarProdn(nombre).get(i).getStok();
                columna[4] = daoProd.buscarProdn(nombre).get(i).getUnidad_medida();
                columna[5] = daoProd.buscarProdn(nombre).get(i).getRazons();
                modeloT.addRow(columna);
                centrar(vistaProd.tbldatos);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vistaProd.tbldatos){
            if(e.getClickCount() == 2){
                String codprod = (String)vistaProd.tbldatos.getValueAt(vistaProd.tbldatos.getSelectedRow(),1);
                String codprov = (String) codsprov.get(vistaProd.tbldatos.getSelectedRow());
                modeloProd = daoProd.buscarProd(codprod,codprov);
                vistaProductosNM vistaProdNM = new vistaProductosNM();
                controladorProductoNM controladorProdNM = new controladorProductoNM(vistaProdNM, daoProd);
                vistaProdNM.lbltitulo.setText("PRODUCTO");
                vistaProdNM.btnregistrar.setVisible(false);
                vistaProdNM.btnguardarcambio.setEnabled(false);
                vistaProdNM.btnsubirimagen.setEnabled(false);
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
