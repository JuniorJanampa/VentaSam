/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.productoDAO;
import DAO.proveedoresDAO;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelos.Productos;
import vistas.vistaProductos;
import vistas.vistaProductosNM;

/**
 *
 * @author Jhunior
 */
public class controladorProductoNM implements ActionListener{
    vistaProductosNM vistaProdNM = new vistaProductosNM();
    productoDAO daoProd = new productoDAO();

    Productos modeloProd;
    String razons;

    double  stok,pcom,pven;
    int unm;

    String[] cod_prov;
    FileInputStream fis;
    InputStream isaux = null;
    
    public controladorProductoNM(vistaProductosNM vistaProdNM, productoDAO daoProd){
        this.vistaProdNM = vistaProdNM;
        this.daoProd = daoProd;
        this.vistaProdNM.btnregistrar.addActionListener(this);
        this.vistaProdNM.btnmodificar.addActionListener(this);
        this.vistaProdNM.btnguardarcambio.addActionListener(this);
        this.vistaProdNM.btncancelar.addActionListener(this);
        this.vistaProdNM.btnsubirimagen.addActionListener(this);
    }
    
    public void inicializarProdNM(Productos modeloProd,boolean vf){
        iniciarCMB();
        vistaProdNM.txtcodigo.setText(modeloProd.getCodigo());
        vistaProdNM.txtnombre.setText(modeloProd.getNombre());
        vistaProdNM.txtCategoria.setText(modeloProd.getCategoria());
        vistaProdNM.txtPreciocompra.setText(String.valueOf(modeloProd.getPrecio_compra()));
        vistaProdNM.txtstock.setText(String.valueOf(modeloProd.getStok()));
        vistaProdNM.cmbunidadm.setSelectedItem(modeloProd.getUnidad_medida());
        vistaProdNM.txtPrecioventa.setText(String.valueOf(modeloProd.getPrecio_venta()));
        vistaProdNM.cmbprov.setSelectedItem(modeloProd.getRazons());
        if (modeloProd.getMimagen()== null){
            vistaProdNM.lblimagen.setIcon(new CustomImageIcon(getClass().getResource("/imagenes/nodisponible.png")));
            this.fis = null;
        }else{
            mostrarImagen(modeloProd.getMimagen());
        }
        
        edit(vf);
    }
    
    public void mostrarImagen(InputStream is){
        CustomImageIcon imagen;
        BufferedImage bi;
        isaux = is;
        try {
            bi = ImageIO.read(is);
            imagen = new CustomImageIcon(bi);
            vistaProdNM.lblimagen.setIcon(imagen);
        } catch (IOException ex) {
            Logger.getLogger(controladorProductoNM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void limpiar(){
        iniciarCMB();
        vistaProdNM.txtcodigo.setText("");
        vistaProdNM.txtnombre.setText("");
        vistaProdNM.txtPreciocompra.setText("");
        vistaProdNM.txtstock.setText("");
        vistaProdNM.txtPrecioventa.setText("");
    }
    
    public void edit(boolean vf){
        vistaProdNM.txtcodigo.setEditable(vf);
        vistaProdNM.txtnombre.setEditable(vf);
        vistaProdNM.txtCategoria.setEditable(vf);
        vistaProdNM.txtPreciocompra.setEditable(vf);
        vistaProdNM.txtstock.setEditable(vf);
        vistaProdNM.cmbunidadm.setEditable(vf);
        vistaProdNM.txtPrecioventa.setEditable(vf);
        vistaProdNM.cmbprov.setEditable(vf);
    }
    
    public void iniciarCMB(){
        vistaProdNM.cmbunidadm.removeAllItems();
        vistaProdNM.cmbprov.removeAllItems();
        vistaProdNM.cmbunidadm.addItem("GR");
        vistaProdNM.cmbunidadm.addItem("KG");
        vistaProdNM.cmbunidadm.addItem("OZ");
        vistaProdNM.cmbunidadm.addItem("ONZA");
        vistaProdNM.cmbunidadm.addItem("UND");
        vistaProdNM.cmbunidadm.addItem("RAMO");
        proveedoresDAO daoProv = new proveedoresDAO();
        int filas = daoProv.listarProveedores().size();
        cod_prov = new String[filas];
        for (int i = 0; i < filas; i++) {
            cod_prov[i] = daoProv.listarProveedores().get(i).getCodigo();
            vistaProdNM.cmbprov.addItem(daoProv.listarProveedores().get(i).getRazonsocial());
        }
    }
    
    public String obtenerCOD (String razons){
        String codi = "";
        int tam = vistaProdNM.cmbprov.getItemCount();
        for (int i = 0; i < tam; i++) {
            if(razons == vistaProdNM.cmbprov.getItemAt(i)){
                codi = cod_prov[i];
            }
        }
        return codi;
    }
    
    
    
    public void salir(){
        vistaProductos vistaProd = new vistaProductos();
        controladorProducto controladorProd = new controladorProducto(vistaProd, daoProd);
        controladorProd.inicializarProducto();
        vistaProd.setLocationRelativeTo(null);
        vistaProd.setVisible(true);
        vistaProdNM.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaProdNM.btnregistrar){

            modeloProd = new Productos();
            modeloProd.setNombre(vistaProdNM.txtnombre.getText());
            modeloProd.setCategoria(vistaProdNM.txtCategoria.getText());
            modeloProd.setPrecio_compra(Double.parseDouble(vistaProdNM.txtPreciocompra.getText()));
            modeloProd.setStok(Double.parseDouble(vistaProdNM.txtstock.getText()));
            modeloProd.setUnidad_med(vistaProdNM.cmbunidadm.getSelectedIndex()+1);
            modeloProd.setPrecio_venta(Double.parseDouble(vistaProdNM.txtPrecioventa.getText()));
            razons = String.valueOf(vistaProdNM.cmbprov.getSelectedItem());
            modeloProd.setCod_prov(obtenerCOD(razons));
            modeloProd.setImagen(fis);
            String rpta = daoProd.insertarProducto(modeloProd);
            JOptionPane.showMessageDialog(vistaProdNM, rpta);
            salir();
        }
        if(e.getSource() == vistaProdNM.btnsubirimagen){
            JFileChooser selIma = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG,PNG & GIF Images", "jpg", "gif","png");
            selIma.setFileFilter(filtro);
            int estado = selIma.showOpenDialog(null);
            if(estado == JFileChooser.APPROVE_OPTION)
            {
                try {
                    fis = new FileInputStream(selIma.getSelectedFile());

                    Image icono = ImageIO.read(selIma.getSelectedFile()).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                    vistaProdNM.lblimagen.setIcon(new ImageIcon(icono));
                    vistaProdNM.lblimagen.updateUI(); 
                }catch (FileNotFoundException ex) {}
                catch (IOException ex){}
            }else{
                fis = null;
            }
        }
        if(e.getSource() == vistaProdNM.btnmodificar){
            edit(true);
            vistaProdNM.btnguardarcambio.setEnabled(true);
            vistaProdNM.btnmodificar.setEnabled(false);
            vistaProdNM.btnsubirimagen.setEnabled(true);
            fis= null;
            
        }
        if(e.getSource() == vistaProdNM.btnguardarcambio){

            modeloProd = new Productos();
            modeloProd.setCodigo(vistaProdNM.txtcodigo.getText());
            modeloProd.setNombre(vistaProdNM.txtnombre.getText());
            modeloProd.setCategoria(vistaProdNM.txtCategoria.getText());
            modeloProd.setPrecio_compra(Double.parseDouble(vistaProdNM.txtPreciocompra.getText()));
            modeloProd.setStok(Double.parseDouble(vistaProdNM.txtstock.getText()));
            modeloProd.setUnidad_med(vistaProdNM.cmbunidadm.getSelectedIndex()+1);
            modeloProd.setPrecio_venta(Double.parseDouble(vistaProdNM.txtPrecioventa.getText()));
            razons = String.valueOf(vistaProdNM.cmbprov.getSelectedItem());
            modeloProd.setCod_prov(obtenerCOD(razons));
            modeloProd.setImagen(fis);
            String rpta="";
            if(fis == null){
                rpta = daoProd.modificarProductoS(modeloProd);
            }else{
                rpta = daoProd.modificarProductoC(modeloProd);
            }
            
            JOptionPane.showMessageDialog(vistaProdNM, rpta);
            edit(false);
            vistaProdNM.btnguardarcambio.setEnabled(false);
            vistaProdNM.btnmodificar.setEnabled(true);
            vistaProdNM.btncancelar.setText("Cerrar");
            salir();
        }
        if(e.getSource() == vistaProdNM.btncancelar){
            salir();
        }
    }
    
    
}
