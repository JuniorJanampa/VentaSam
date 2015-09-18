/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.clienteDAO;
import DAO.detalleventaDAO;
import DAO.productoDAO;
import DAO.ventaDAO;
import com.itextpdf.text.DocumentException;
import impresora.ImpTicket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Detalleventa;
import modelos.Productos;
import modelos.Ventas;
import vistas.vistaVentas;
import vistas.vistaVentasA;
import vistas.vistaVentasV;

/**
 *
 * @author Jhunior
 */
public class controladorVenta implements ActionListener,KeyListener,MouseListener{
    vistaVentas vistaV = new vistaVentas();
    vistaVentasA vistaVA = new vistaVentasA(vistaV,true);
    vistaVentasV vistaVV = new vistaVentasV(vistaV, true);
    
    ventaDAO daoV = new ventaDAO();
    detalleventaDAO daoDV = new detalleventaDAO();
    
    Ventas modeloV = new Ventas();
    Detalleventa modeloDV;
    Productos modeloProd;
    
    clienteDAO daoC = new clienteDAO();
    productoDAO daoProd = new productoDAO();
    
    ArrayList<Detalleventa> listaDV;
    
    String dni,dnie,codC;
    
    double total;
    //String pagm, vuelm, totalm;
    double aux=0.0;
    
    DefaultTableModel modeloTV = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    
    public controladorVenta (vistaVentas vistaV,ventaDAO daoV, detalleventaDAO daoDV,String dnie,String codC){
        this.vistaV = vistaV;
        this.daoV = daoV;
        this.daoDV = daoDV;
        this.dnie = dnie;
        this.codC = codC;
        this.listaDV = new ArrayList<Detalleventa>();
        this.vistaV.btnacliente.addActionListener(this);
        this.vistaV.btnventa.addActionListener(this);
        this.vistaV.btnedit.addActionListener(this);
        this.vistaV.btndele.addActionListener(this);
        this.vistaV.btncancelar.addActionListener(this);
        this.vistaV.txtfiltrarproducto.addKeyListener(this);
        this.vistaV.tblprod.addMouseListener(this);
        this.vistaVA.txtcant.addKeyListener(this);
        this.vistaVA.btnagregar.addActionListener(this);
        this.vistaVA.btncancelar.addActionListener(this);
        this.vistaVV.btnaceptar.addActionListener(this);
    }
    
    public void inicializarVenta(){
        vistaV.txtnombres.setText("Publico en General");
        vistaV.txtnombres.setEditable(false);
        LLenarTablaProd(vistaV.tblprod); 
        TablaVen(vistaV.tblventa);
        dni = "1";
        total = 0.0;
        
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        vistaV.txtfecha.setText(String.valueOf(date.format(now)));
        vistaV.txtfecha.setEditable(false);
    }
    
    public void TablaVen(JTable tablaD){
        tablaD.setModel(modeloTV);
        modeloTV.addColumn("CANT.");
        modeloTV.addColumn("DESCRIPCION");
        modeloTV.addColumn("P. UNIT.");
        modeloTV.addColumn("IMPORTE");
    }
    
    public void LLenarTablaProd(JTable tablaD){
        productoDAO daoProd = new productoDAO();
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
        modeloT.addColumn("CATEGORIA");
        modeloT.addColumn("STOK");
        modeloT.addColumn("UND.MED.");
        modeloT.addColumn("PRECIO");
        
        Object[] columna = new Object[6];

        int numRegistros = daoProd.listarProductos().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = daoProd.listarProductos().get(i).getCodigo();
            columna[1] = daoProd.listarProductos().get(i).getNombre();
            columna[2] = daoProd.listarProductos().get(i).getCategoria();
            columna[3] = daoProd.listarProductos().get(i).getStok();
            columna[4] = daoProd.listarProductos().get(i).getUnidad_medida();
            columna[5] = daoProd.listarProductos().get(i).getPrecio_venta();
            modeloT.addRow(columna);
        }
    }
    
    public int unm(String u_m){
        int um=0;
        if(u_m.equals("GR")){um=1;}
        if(u_m.equals("KG")){um=2;}
        if(u_m.equals("OZ")){um=3;}
        if(u_m.equals("QUINTAL")){um=4;}
        if(u_m.equals("UND")){um=5;}
        if(u_m.equals("RAMO")){um=6;}
        return um;
    }
    
    private void agregarItem(){
        modeloDV = new Detalleventa();
        modeloDV.setCodigo(vistaVA.txtnombre.getText());
        modeloDV.setCod_prod(modeloProd.getCodigo());
        modeloDV.setCantidad(Double.parseDouble(vistaVA.txtcant.getText()));
        
        int un_m = unm(modeloProd.getUnidad_medida());
        modeloDV.setUnd_med(un_m);
        modeloDV.setP_base(Double.parseDouble(vistaVA.txtprecio.getText()));
        double importe = Double.parseDouble(vistaVA.txtcant.getText())*Double.parseDouble(vistaVA.txtprecio.getText());
        modeloDV.setP_cant(importe);
        
            
        Object[] columna = new Object[4];
        columna[0] = modeloDV.getCantidad();
        columna[1] = modeloProd.getNombre();
        columna[2] = modeloDV.getP_base();
        columna[3] = modeloDV.getP_cant();
               
        total = total + importe;
        
        modeloTV.addRow(columna);
        vistaV.txttotal.setText(String.valueOf(total));
        
        listaDV.add(modeloDV);
           
        vistaVA.dispose();
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaV.btnacliente){
            dni = JOptionPane.showInputDialog("Ingrese DNI: ");
            Cliente modeloC = new Cliente();
            modeloC = daoC.buscarxDni(dni);
            if (!modeloC.getDni().equals("")){
                vistaV.txtnombres.setText(modeloC.getNombres()+" "+modeloC.getApaterno());
                vistaV.txtnombres.setEditable(false);
            }else{JOptionPane.showMessageDialog(vistaV, "Cliente no Registrado"); dni = "1";}
        }
        if(e.getSource() == vistaVA.btnagregar){
            agregarItem();
        }
        
        if(e.getSource() == vistaV.btnedit){
            int filas = vistaV.tblventa.getSelectedRow();
            if(filas>=0){
                int tam = listaDV.size();
                String nom = String.valueOf(vistaV.tblventa.getValueAt(vistaV.tblventa.getSelectedRow(),1));
                double imp = Double.parseDouble(String.valueOf(vistaV.tblventa.getValueAt(vistaV.tblventa.getSelectedRow(),3)));
                for (int i = 0; i < tam; i++) {
                    if(listaDV.get(i).getCodigo().equals(nom) || listaDV.get(i).getP_cant() == imp){
                        vistaVA.txtnombre.setText(nom);
                        vistaVA.txtprecio.setText(String.valueOf(listaDV.get(i).getP_base()));
                        vistaVA.txtcant.setText(String.valueOf(listaDV.get(i).getCantidad()));
                        total = total - listaDV.get(i).getP_cant();
                        listaDV.remove(i);
                        i=tam;
                    }
                }
                modeloTV.removeRow(vistaV.tblventa.getSelectedRow());
                vistaV.txttotal.setText(String.valueOf(total));
                vistaVA.txtcant.requestFocus();
                vistaVA.txtcant.selectAll();
                vistaVA.txtnombre.setEditable(false);
                vistaVA.setLocationRelativeTo(null);
                vistaVA.setVisible(true);
            }
            else{JOptionPane.showMessageDialog(vistaV, "Elija al menos una fila");}
        }
            
                
        if(e.getSource() == vistaV.btndele){
            int tam = listaDV.size();
            String nom = String.valueOf(vistaV.tblventa.getValueAt(vistaV.tblventa.getSelectedRow(),1));
            double imp = Double.parseDouble(String.valueOf(vistaV.tblventa.getValueAt(vistaV.tblventa.getSelectedRow(),3)));
            for (int i = 0; i < tam; i++) {
                if(listaDV.get(i).getCodigo().equals(nom)|| listaDV.get(i).getP_cant() == imp){
                    total = total - listaDV.get(i).getP_cant();
                    listaDV.remove(i);
                    modeloTV.removeRow(vistaV.tblventa.getSelectedRow());
                    vistaV.txttotal.setText(String.valueOf(total));
                    i=tam;
                }
            }
            
        }
        
        if(e.getSource() == vistaV.btnventa){
            DecimalFormat decf = new DecimalFormat("###.##");
            double pago = Double.parseDouble(JOptionPane.showInputDialog("Monto con el que va a pagar"));
            String pagm="", vuelm="", totalm="";
            if (pago >= total){
                Date now = new Date(System.currentTimeMillis());
                SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String fecha = String.valueOf(forma.format(now));
                

                String rpta = daoV.insertarVenta(total, dnie,dni ,codC);
                String codV = daoV.codVenta();
                if(!codV.equals("")){
                    int tam = listaDV.size();

                    for (int i = 0; i < tam; i++) {
                        String codProd = listaDV.get(i).getCod_prod();
                        double cant = listaDV.get(i).getCantidad();
                        int unm = listaDV.get(i).getUnd_med();
                        double p_base = listaDV.get(i).getP_base();
                        double importe = listaDV.get(i).getP_cant();
                        rpta = daoDV.insertarDetalle(codV,codProd , cant, unm, p_base, importe);
                    }
                    JOptionPane.showMessageDialog(vistaV, rpta);
                }
            
                
                double vuelto = pago - total;
                pagm=decf.format(pago);
                vuelm=decf.format(vuelto);
                totalm=decf.format(total);
                vistaVV.txtpago.setText(pagm);
                vistaVV.txttotal.setText(totalm);
                vistaVV.txttotal.setEditable(false);
                vistaVV.txtvuelto.setText(vuelm);
                vistaVV.txtvuelto.setEditable(false);
                vistaVV.setLocationRelativeTo(null);
                vistaVV.setVisible(true);
               
                ImpTicket impt;
                impt = new ImpTicket(codV, dnie, fecha, listaDV, totalm, pagm, vuelm);
                try {
                    impt.Imprimir();
                } catch (FileNotFoundException | DocumentException ex) {
                    Logger.getLogger(controladorVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{JOptionPane.showMessageDialog(vistaVV, "Monto es menor al total");}

        }
        
        if (e.getSource() == vistaVV.btnaceptar){
            vistaVV.dispose();
            vistaV.dispose();
        }
        if(e.getSource() == vistaV.btncancelar){
            vistaV.dispose();
        }
        if(e.getSource() == vistaVA.btncancelar){
            vistaVA.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vistaV.tblprod){
            if(e.getClickCount() == 2){
                String codprod = (String)vistaV.tblprod.getValueAt(vistaV.tblprod.getSelectedRow(),0);
                
                modeloProd = new Productos();
                modeloProd = daoProd.buscarP(codprod);
                
                vistaVA.txtnombre.setText(modeloProd.getNombre());
                vistaVA.txtprecio.setText(String.valueOf(modeloProd.getPrecio_venta()));
                vistaVA.txtstok.setText(String.valueOf(modeloProd.getStok()));
                vistaVA.txtstok.setEditable(false);
                vistaVA.txtcant.setText("0");
                vistaVA.txtcant.requestFocus();
                vistaVA.txtcant.selectAll();
                vistaVA.txtnombre.setEditable(false);
                vistaVA.setLocationRelativeTo(null);
                vistaVA.setVisible(true);
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getSource() == vistaVA.txtcant){
           if(e.getKeyCode() == KeyEvent.VK_ENTER){
               agregarItem();
          }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == vistaV.txtfiltrarproducto){
            String nombre = vistaV.txtfiltrarproducto.getText();
            DefaultTableModel  modeloT = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                return false;
                }
            };
            vistaV.tblprod.setModel(modeloT);

            modeloT.addColumn("COD. PRODUCTO");
            modeloT.addColumn("NOMBRE");
            modeloT.addColumn("CATEGORIA");
            modeloT.addColumn("STOK");
            modeloT.addColumn("UND.MED.");
            modeloT.addColumn("PRECIO");

            Object[] columna = new Object[6];

            int numRegistros = daoProd.buscarProdn(nombre).size();

            for (int i = 0; i < numRegistros; i++) {
                columna[0] = daoProd.buscarProdn(nombre).get(i).getCodigo();
                columna[1] = daoProd.buscarProdn(nombre).get(i).getNombre();
                columna[2] = daoProd.buscarProdn(nombre).get(i).getCategoria();
                columna[3] = daoProd.buscarProdn(nombre).get(i).getStok();
                columna[4] = daoProd.buscarProdn(nombre).get(i).getUnidad_medida();
                columna[5] = daoProd.buscarProdn(nombre).get(i).getPrecio_venta();
                modeloT.addRow(columna);
            }
        }
    }
    
    
}
