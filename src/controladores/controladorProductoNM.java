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
import javax.swing.JOptionPane;
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
    String cod, nom, cate,codp,razons;
    double  stok,pcom,pven;
    int unm;
    String[] cod_prov;
    
    public controladorProductoNM(vistaProductosNM vistaProdNM, productoDAO daoProd){
        this.vistaProdNM = vistaProdNM;
        this.daoProd = daoProd;
        this.vistaProdNM.btnregistrar.addActionListener(this);
        this.vistaProdNM.btnmodificar.addActionListener(this);
        this.vistaProdNM.btnguardarcambio.addActionListener(this);
        this.vistaProdNM.btncancelar.addActionListener(this);
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
        edit(vf);
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
            nom = vistaProdNM.txtnombre.getText();
            cate = vistaProdNM.txtCategoria.getText();
            pcom = Double.parseDouble(vistaProdNM.txtPreciocompra.getText());
            stok = Double.parseDouble(vistaProdNM.txtstock.getText());
            unm = vistaProdNM.cmbunidadm.getSelectedIndex()+1;
            pven = Double.parseDouble(vistaProdNM.txtPrecioventa.getText());
            razons = String.valueOf(vistaProdNM.cmbprov.getSelectedItem());
            codp = obtenerCOD(razons);
            String rpta = daoProd.insertarProducto(nom, cate, pcom, stok, unm, pven,codp);
            JOptionPane.showMessageDialog(vistaProdNM, rpta);
            salir();
        }
        if(e.getSource() == vistaProdNM.btnmodificar){
            edit(true);
            vistaProdNM.btnguardarcambio.setEnabled(true);
            vistaProdNM.btnmodificar.setEnabled(false);
        }
        if(e.getSource() == vistaProdNM.btnguardarcambio){
            cod = vistaProdNM.txtcodigo.getText();
            nom = vistaProdNM.txtnombre.getText();
            cate = vistaProdNM.txtCategoria.getText();
            pcom = Double.parseDouble(vistaProdNM.txtPreciocompra.getText());
            stok = Double.parseDouble(vistaProdNM.txtstock.getText());
            unm = vistaProdNM.cmbunidadm.getSelectedIndex()+1;
            pven = Double.parseDouble(vistaProdNM.txtPrecioventa.getText());
            razons = String.valueOf(vistaProdNM.cmbprov.getSelectedItem());
            codp = obtenerCOD(razons);
            String rpta = daoProd.modificarProducto(cod, nom, cate, pcom, stok, unm, pven, codp);
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
