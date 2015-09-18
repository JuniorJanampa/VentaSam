/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.compraDAO;
import DAO.detallecompraDAO;
import DAO.faltantesDAO;
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
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import modelos.Detallecompra;
import modelos.Productos;
import vistas.vistaCompraA;
import vistas.vistaCompras;

/**
 *
 * @author Jhunior
 */
public class controladorCompra implements ActionListener,MouseListener,KeyListener{
    vistaCompras vistaCo = new vistaCompras();
    compraDAO daoCo = new compraDAO();
    detallecompraDAO daoDCo = new detallecompraDAO();
    vistaCompraA vistaCoA = new vistaCompraA(vistaCo, true);
    
    String dnie,codC;
    String[] cod_prov;
    
    /*PARA LOS METODOS*/
    faltantesDAO daoF = new faltantesDAO();
    productoDAO daoProd = new productoDAO();
    
    
    ArrayList<Detallecompra> listaDC;
    
    Detallecompra modeloDC;
    Productos modeloProd;
    
    double total;
    
    DefaultTableModel  modeloTDC = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    
    /*CONSTRUCTOR*/
    public controladorCompra(vistaCompras vistaCo,compraDAO daoCo,detallecompraDAO daoDCo,String dnie,String codC){
        this.vistaCo = vistaCo;
        this.daoCo = daoCo;
        this.daoDCo = daoDCo;
        this.dnie = dnie;
        this.codC = codC;
        this.listaDC = new ArrayList<Detallecompra>();
        this.vistaCo.cmbprov.addActionListener(this);
        this.vistaCo.btncompra.addActionListener(this);
        this.vistaCo.btncancelar.addActionListener(this);
        this.vistaCoA.btnagregar.addActionListener(this);
        this.vistaCoA.btncancelar.addActionListener(this);
        this.vistaCo.tblfal.addMouseListener(this);
        this.vistaCo.tblprod.addMouseListener(this);
        this.vistaCo.txtfiltrarproducto.addKeyListener(this);
    }
    
    public void inicializarCompra(){
        /*INICIAR FALTANTES*/
        total=0.0;
        TablaVen(vistaCo.tbldetalle);
        iniciarCMB();
        mostrarDatosFaltantes("");
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        vistaCo.txtfecha.setText(String.valueOf(date.format(now)));
        vistaCo.txtfecha.setEditable(false);
    }
    
    public void iniciarCMB(){
        vistaCo.cmbprov.removeAllItems();
        vistaCo.cmbprov.addItem("");
        proveedoresDAO daoProv = new proveedoresDAO();
        int filas = daoProv.listarProveedores().size();
        cod_prov = new String[filas];
        for (int i = 0; i < filas; i++) {
            cod_prov[i] = daoProv.listarProveedores().get(i).getCodigo();
            vistaCo.cmbprov.addItem(daoProv.listarProveedores().get(i).getRazonsocial());
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
    
    public void centrarP(JTable tablaD){
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        tablaD.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
        tablaD.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
        tablaD.getColumnModel().getColumn(3).setCellRenderer(modelocentrar);
        tablaD.getColumnModel().getColumn(4).setCellRenderer(modelocentrar);
        tablaD.getColumnModel().getColumn(5).setCellRenderer(modelocentrar);
    }
     
    public void TablaVen(JTable tablaD){
        tablaD.setModel(modeloTDC);
        modeloTDC.addColumn("CANT.");
        modeloTDC.addColumn("DESCRIPCION");
        modeloTDC.addColumn("P. UNIT.");
        modeloTDC.addColumn("IMPORTE");
    }
    
    public void mostrarDatosFaltantes(String razon){
            DefaultTableModel  modeloTF = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
            }
            };
        
            vistaCo.tblfal.setModel(modeloTF);
            vistaCo.tblfal.setDefaultRenderer(Object.class, new IconCellRender());
            vistaCo.tblfal.setRowHeight(80);

            modeloTF.addColumn("IMAGEN");
            modeloTF.addColumn("COD. PRODUCTO");
            modeloTF.addColumn("NOMBRE");
            modeloTF.addColumn("STOK");
            modeloTF.addColumn("UND.MED.");
            modeloTF.addColumn("PREC. COM.");
            modeloTF.addColumn("PROVEEDOR");

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
                modeloTF.addRow(columna);
                centrar(vistaCo.tblfal);
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
        modeloDC = new Detallecompra();
        modeloDC.setCodigo(vistaCoA.txtnombre.getText());
        modeloDC.setCod_prod(modeloProd.getCodigo());
        modeloDC.setCant(Integer.parseInt(vistaCoA.txtcant.getText()));
        int un_m = unm(modeloProd.getUnidad_medida());
        modeloDC.setUnm(un_m);
        modeloDC.setP_com(Double.parseDouble(vistaCoA.txtprecio.getText()));
        double importe = Integer.parseInt(vistaCoA.txtcant.getText())*Double.parseDouble(vistaCoA.txtprecio.getText());
        modeloDC.setImp(importe);
            
        Object[] columna = new Object[4];
        columna[0] = modeloDC.getCant();
        columna[1] = modeloProd.getNombre();
        columna[2] = modeloDC.getP_com();
        columna[3] = modeloDC.getImp();
        
        total = total + importe;
        
        modeloTDC.addRow(columna);
        vistaCo.txttotal.setText(String.valueOf(total));
        
        listaDC.add(modeloDC);
           
        vistaCoA.dispose();
    }    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaCo.cmbprov){
            String razon = String.valueOf(vistaCo.cmbprov.getSelectedItem());
            /*TABLA PRODUCTOS*/
            vistaCo.tblprod.removeAll();
            DefaultTableModel  modeloTP = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };
            vistaCo.tblprod.setModel(modeloTP);
            vistaCo.tblprod.setDefaultRenderer(Object.class, new IconCellRender());
            vistaCo.tblprod.setRowHeight(80);

            modeloTP.addColumn("IMAGEN");
            modeloTP.addColumn("COD. PRODUCTO");
            modeloTP.addColumn("NOMBRE");
            modeloTP.addColumn("STOK");
            modeloTP.addColumn("UND.MED.");
            modeloTP.addColumn("PROVEEDOR");
    
            Object[] columna = new Object[6];

            int numRegistros = daoProd.buscarProdp(razon).size();

            for (int i = 0; i < numRegistros; i++) {
                if(daoProd.listarProductos().get(i).getMimagen() == null){
                    columna[0] = new JLabel(new CustomImageIcon(getClass().getResource("/imagenes/nodisponible.png")));
                }else{
                    columna[0] = new JLabel(mostrarImagen(daoProd.listarProductos().get(i).getMimagen()));
                }
                columna[1] = daoProd.buscarProdp(razon).get(i).getCodigo();
                columna[2] = daoProd.buscarProdp(razon).get(i).getNombre();
                columna[3] = daoProd.buscarProdp(razon).get(i).getStok();
                columna[4] = daoProd.buscarProdp(razon).get(i).getUnidad_medida();
                columna[5] = daoProd.buscarProdp(razon).get(i).getRazons();
                modeloTP.addRow(columna);
                centrarP(vistaCo.tblprod);
            }
            
            /*TABLA FALTANTES*/
            mostrarDatosFaltantes(razon);
        }
        
        if(e.getSource() == vistaCoA.btnagregar){
            agregarItem();
        }
        
        if(e.getSource() == vistaCo.btncompra){
            if(JOptionPane.showConfirmDialog(vistaCo,"Importe Total: "+total,"Scam Ventas",JOptionPane.OK_CANCEL_OPTION)==0){
                int ind = vistaCo.cmbprov.getSelectedIndex() - 1;
                String c_com = cod_prov[ind];
                String rpta = daoCo.insertarCompra(dnie, c_com, codC);
                String codCo = daoCo.codVenta();
                if(!codCo.equals("")){
                    int tam = listaDC.size();

                    for (int i = 0; i < tam; i++) {
                        String codProd = listaDC.get(i).getCod_prod();
                        int cant = listaDC.get(i).getCant();
                        int unm = listaDC.get(i).getUnm();
                        rpta = daoDCo.insertarDetalle(codCo,codProd , cant, unm);
                    }
                    JOptionPane.showMessageDialog(vistaCo, rpta);
                }
                vistaCo.dispose();
            }
        }
        
        if(e.getSource() == vistaCo.btnedit){
            int filas = vistaCo.tbldetalle.getSelectedRow();
            if(filas>=0){
                int tam = listaDC.size();
                String nom = String.valueOf(vistaCo.tbldetalle.getValueAt(vistaCo.tbldetalle.getSelectedRow(),1));
                double imp = Double.parseDouble(String.valueOf(vistaCo.tbldetalle.getValueAt(vistaCo.tbldetalle.getSelectedRow(),3)));
                for (int i = 0; i < tam; i++) {
                    if(listaDC.get(i).getCodigo().equals(nom) || listaDC.get(i).getImp() == imp){
                        vistaCoA.txtnombre.setText(nom);
                        vistaCoA.txtprecio.setText(String.valueOf(listaDC.get(i).getP_com()));
                        vistaCoA.txtcant.setText(String.valueOf(listaDC.get(i).getCant()));
                        total = total - listaDC.get(i).getImp();
                        listaDC.remove(i);
                        i=tam;
                    }
                }
                modeloTDC.removeRow(vistaCo.tbldetalle.getSelectedRow());
                vistaCo.txttotal.setText(String.valueOf(total));
                vistaCoA.txtcant.requestFocus();
                vistaCoA.txtcant.selectAll();
                vistaCoA.txtnombre.setEditable(false);
                vistaCoA.setLocationRelativeTo(null);
                vistaCoA.setVisible(true);
            }
            else{JOptionPane.showMessageDialog(vistaCo, "Elija al menos una fila");}
        }
            
                
        if(e.getSource() == vistaCo.btndele){
            int tam = listaDC.size();
            String nom = String.valueOf(vistaCo.tbldetalle.getValueAt(vistaCo.tbldetalle.getSelectedRow(),1));
            double imp = Double.parseDouble(String.valueOf(vistaCo.tbldetalle.getValueAt(vistaCo.tbldetalle.getSelectedRow(),3)));
            for (int i = 0; i < tam; i++) {
                if(listaDC.get(i).getCodigo().equals(nom)|| listaDC.get(i).getImp() == imp){
                    total = total - listaDC.get(i).getImp();
                    listaDC.remove(i);
                    modeloTDC.removeRow(vistaCo.tbldetalle.getSelectedRow());
                    vistaCo.txttotal.setText(String.valueOf(total));
                    i=tam;
                }
            }
            
        }
        
        if(e.getSource() == vistaCo.btncancelar){
            vistaCo.dispose();
        }
        if(e.getSource() == vistaCoA.btncancelar){
            vistaCoA.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vistaCo.tblprod){
            if(e.getClickCount() == 2){
                String codprod = (String)vistaCo.tblprod.getValueAt(vistaCo.tblprod.getSelectedRow(),1);
                
                modeloProd = new Productos();
                modeloProd = daoProd.buscarP(codprod);
                
                vistaCoA.txtnombre.setText(modeloProd.getNombre());
                vistaCoA.txtprecio.setText(String.valueOf(modeloProd.getPrecio_compra()));
                vistaCoA.txtstok.setText(String.valueOf(modeloProd.getStok()));
                vistaCoA.txtstok.setEditable(false);
                vistaCoA.txtcant.setText("0");
                vistaCoA.txtcant.requestFocus();
                vistaCoA.txtcant.selectAll();
                vistaCoA.txtnombre.setEditable(false);
                vistaCoA.setLocationRelativeTo(null);
                vistaCoA.setVisible(true);
            }
        }
        if(e.getSource() == vistaCo.tblfal){
            if(e.getClickCount() == 2){
                String codprod = (String)vistaCo.tblfal.getValueAt(vistaCo.tblfal.getSelectedRow(),1);
                
                modeloProd = new Productos();
                modeloProd = daoProd.buscarP(codprod);
                
                vistaCoA.txtnombre.setText(modeloProd.getNombre());
                vistaCoA.txtprecio.setText(String.valueOf(modeloProd.getPrecio_compra()));
                vistaCoA.txtstok.setText(String.valueOf(modeloProd.getStok()));
                vistaCoA.txtstok.setEditable(false);
                vistaCoA.txtcant.setText("0");
                vistaCoA.txtcant.requestFocus();
                vistaCoA.txtcant.selectAll();
                vistaCoA.txtnombre.setEditable(false);
                vistaCoA.setLocationRelativeTo(null);
                vistaCoA.setVisible(true);
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == vistaCo.txtfiltrarproducto){
            String nombre = vistaCo.txtfiltrarproducto.getText();
            DefaultTableModel  modeloT = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                return false;
                }
            };
            vistaCo.tblprod.setModel(modeloT);
            vistaCo.tblprod.setDefaultRenderer(Object.class, new IconCellRender());

            modeloT.addColumn("IMAGEN");
            modeloT.addColumn("COD. PRODUCTO");
            modeloT.addColumn("NOMBRE");
            modeloT.addColumn("CATEGORIA");
            modeloT.addColumn("STOK");
            modeloT.addColumn("UND.MED.");
            modeloT.addColumn("PRECIO");

            Object[] columna = new Object[7];

            int numRegistros = daoProd.buscarProdn(nombre).size();

            for (int i = 0; i < numRegistros; i++) {
                if(daoProd.buscarProdn(nombre).get(i).getMimagen() == null){
                    columna[0] = new JLabel(new CustomImageIcon(getClass().getResource("/imagenes/nodisponible.png")));
                }else{
                    columna[0] = new JLabel(mostrarImagen(daoProd.buscarProdn(nombre).get(i).getMimagen()));
                }
                columna[1] = daoProd.buscarProdn(nombre).get(i).getCodigo();
                columna[2] = daoProd.buscarProdn(nombre).get(i).getNombre();
                columna[3] = daoProd.buscarProdn(nombre).get(i).getCategoria();
                columna[4] = daoProd.buscarProdn(nombre).get(i).getStok();
                columna[5] = daoProd.buscarProdn(nombre).get(i).getUnidad_medida();
                columna[6] = daoProd.buscarProdn(nombre).get(i).getPrecio_venta();
                modeloT.addRow(columna);
                centrar(vistaCo.tblprod);
            }
        }
    }
    
    
    
}
