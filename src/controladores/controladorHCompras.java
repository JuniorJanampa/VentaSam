/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.compraDAO;
import DAO.detallecompraDAO;
import DAO.empleadoDAO;
import DAO.productoDAO;
import DAO.proveedoresDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Empleado;
import modelos.Productos;
import modelos.Proveedores;
import vistas.vistaHistoricoCompras;

/**
 *
 * @author Jhunior
 */
public class controladorHCompras implements ActionListener,MouseListener{
    vistaHistoricoCompras vistaHC = new vistaHistoricoCompras();
    compraDAO daoCo = new compraDAO();
    detallecompraDAO daoDCo = new  detallecompraDAO();
    
    Empleado modeloE = new Empleado();
    Productos modeloProd = new Productos();
    Proveedores modeloProv = new Proveedores();
    
    empleadoDAO daoE = new empleadoDAO();
    productoDAO daoProd = new productoDAO();
    proveedoresDAO daoProv = new proveedoresDAO();
    
    public controladorHCompras(vistaHistoricoCompras vistaHC,compraDAO daoCo,detallecompraDAO daoDCo){
        this.vistaHC = vistaHC;
        this.daoCo = daoCo;
        this.daoDCo = daoDCo;
        this.vistaHC.btncerrar.addActionListener(this);
        this.vistaHC.tblcompras.addMouseListener(this);
        this.vistaHC.rbdia.addActionListener(this);
        this.vistaHC.rbentre.addActionListener(this);
        this.vistaHC.btnfiltrar.addActionListener(this);
        this.vistaHC.btntodo.addActionListener(this);
    }
    
    public void inicializarHVentas(){
        vistaHC.rbdia.setSelected(true);
        vistaHC.lbly.setVisible(false);
        vistaHC.dtfecha2.setVisible(false);
        vistaHC.txtprov.setEditable(false);
        vistaHC.txtfecha.setEditable(false);
        vistaHC.txtempleado.setEditable(false);
        vistaHC.txttotal.setEditable(false);       
        LLenarTablaCom(vistaHC.tblcompras);
    }
    public void LLenarTablaCom(JTable tablaD){
        
        DefaultTableModel  modeloT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
        };
        tablaD.setModel(modeloT);
        
        modeloT.addColumn("COD. VENTA");
        modeloT.addColumn("FECHA");
        modeloT.addColumn("IMPORTE TOTAL");
        modeloT.addColumn("DNI EMPLEADO");
        modeloT.addColumn("COD. PROV.");
        modeloT.addColumn("CAJA");
        
        Object[] columna = new Object[6];

        int numRegistros = daoCo.listarCompras().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = daoCo.listarCompras().get(i).getCodigo();
            columna[1] = daoCo.listarCompras().get(i).getFecha();
            columna[2] = daoCo.listarCompras().get(i).getImpt();
            columna[3] = daoCo.listarCompras().get(i).getDni_e();
            columna[4] = daoCo.listarCompras().get(i).getCod_prov();
            columna[5] = daoCo.listarCompras().get(i).getCod_caja();
            modeloT.addRow(columna);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaHC.rbentre){
            if(vistaHC.rbentre.isSelected()){
                vistaHC.lblfiltrar.setText("Filtrar desde:");
                vistaHC.lbly.setVisible(true);
                vistaHC.dtfecha2.setVisible(true);
            }
        }
        
        if(e.getSource() == vistaHC.btntodo){
            LLenarTablaCom(vistaHC.tblcompras);
        }
        
        if(e.getSource() == vistaHC.rbdia){
            if(vistaHC.rbdia.isSelected()){
                vistaHC.lblfiltrar.setText("Filtrar día:");
                vistaHC.lbly.setVisible(false);
                vistaHC.dtfecha2.setVisible(false);
            }
        }
        
        if(e.getSource() == vistaHC.btnfiltrar){
            DefaultTableModel  modeloT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
            };
            vistaHC.tblcompras.setModel(modeloT);

            modeloT.addColumn("COD. VENTA");
            modeloT.addColumn("FECHA");
            modeloT.addColumn("IMPORTE TOTAL");
            modeloT.addColumn("DNI EMPLEADO");
            modeloT.addColumn("COD. PROV.");
            modeloT.addColumn("CAJA");

            Object[] columna = new Object[6];
            
            if(vistaHC.rbdia.isSelected()){
                String fec1 = new SimpleDateFormat("yyyy-MM-dd").format(vistaHC.dtfecha1.getDate());

                int numRegistros = daoCo.filtrarDia(fec1).size();

                for (int i = 0; i < numRegistros; i++) {
                    columna[0] = daoCo.filtrarDia(fec1).get(i).getCodigo();
                    columna[1] = daoCo.filtrarDia(fec1).get(i).getFecha();
                    columna[2] = daoCo.filtrarDia(fec1).get(i).getImpt();
                    columna[3] = daoCo.filtrarDia(fec1).get(i).getDni_e();
                    columna[4] = daoCo.filtrarDia(fec1).get(i).getCod_prov();
                    columna[5] = daoCo.filtrarDia(fec1).get(i).getCod_caja();
                    modeloT.addRow(columna);
                } 
            }
            if(vistaHC.rbentre.isSelected()){
                String fec1 = new SimpleDateFormat("yyyy-MM-dd").format(vistaHC.dtfecha1.getDate());
                String fec2 = new SimpleDateFormat("yyyy-MM-dd").format(vistaHC.dtfecha2.getDate());

                int numRegistros = daoCo.filtrarEntre(fec1, fec2).size();

                for (int i = 0; i < numRegistros; i++) {
                    columna[0] = daoCo.filtrarEntre(fec1, fec2).get(i).getCodigo();
                    columna[1] = daoCo.filtrarEntre(fec1, fec2).get(i).getFecha();
                    columna[2] = daoCo.filtrarEntre(fec1, fec2).get(i).getImpt();
                    columna[3] = daoCo.filtrarEntre(fec1, fec2).get(i).getDni_e();
                    columna[4] = daoCo.filtrarEntre(fec1, fec2).get(i).getCod_prov();
                    columna[5] = daoCo.filtrarEntre(fec1, fec2).get(i).getCod_caja();
                    modeloT.addRow(columna);
                }
            }
            
        }
        
        if(e.getSource() == vistaHC.btncerrar){
            vistaHC.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vistaHC.tblcompras){
            if(e.getClickCount() == 2){                                
                String cod_com = (String)vistaHC.tblcompras.getValueAt(vistaHC.tblcompras.getSelectedRow(),0);
                Object fecha = vistaHC.tblcompras.getValueAt(vistaHC.tblcompras.getSelectedRow(),1);
                Object importe = vistaHC.tblcompras.getValueAt(vistaHC.tblcompras.getSelectedRow(),2);
                String cod_emp = (String)vistaHC.tblcompras.getValueAt(vistaHC.tblcompras.getSelectedRow(),3);
                String cod_prov = (String)vistaHC.tblcompras.getValueAt(vistaHC.tblcompras.getSelectedRow(),4);
                String nom_prov="";
                
                modeloE = new Empleado();
                
                modeloE = daoE.buscarxDni(cod_emp);
                
                modeloProv = daoProv.buscarCod(cod_prov);
                nom_prov = modeloProv.getRazonsocial();
       
                
                DefaultTableModel  modeloT = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                //all cells false
                    return false;
                    }
                };
                vistaHC.tbldetalle.setModel(modeloT);

                modeloT.addColumn("CANT.");
                modeloT.addColumn("DESCRIPCION");
                modeloT.addColumn("PREC. UND.");
                modeloT.addColumn("IMPORTE");

                Object[] columna = new Object[4];

                int numRegistros = daoDCo.listaDetCom(cod_com).size();

                for (int i = 0; i < numRegistros; i++) {
                    columna[0] = daoDCo.listaDetCom(cod_com).get(i).getCant();
                    modeloProd = daoProd.buscarP(daoDCo.listaDetCom(cod_com).get(i).getCod_prod());
                    columna[1] = modeloProd.getNombre();
                    columna[2] = modeloProd.getPrecio_compra();
                    columna[3] = daoDCo.listaDetCom(cod_com).get(i).getCant()*modeloProd.getPrecio_compra();
                    modeloT.addRow(columna);
                }
                
                vistaHC.txtprov.setText(nom_prov);
                vistaHC.txtempleado.setText(modeloE.getNombres()+" "+ modeloE.getApaterno());
                vistaHC.txtfecha.setText(String.valueOf(fecha));
                vistaHC.txttotal.setText(String.valueOf(importe));
                
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
