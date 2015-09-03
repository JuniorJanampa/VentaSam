/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.clienteDAO;
import DAO.detalleventaDAO;
import DAO.empleadoDAO;
import DAO.productoDAO;
import DAO.ventaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Empleado;
import modelos.Productos;
import vistas.vistaHistoricoVentas;

/**
 *
 * @author Jhunior
 */
public class controladorHVentas implements ActionListener,MouseListener{
    vistaHistoricoVentas vistaHV = new vistaHistoricoVentas();
    ventaDAO daoV = new ventaDAO();
    
    Empleado modeloE;
    Cliente modeloC = new Cliente();
    Productos modeloProd = new Productos();
    
    productoDAO daoProd = new productoDAO();
    empleadoDAO daoE = new empleadoDAO();
    clienteDAO daoC = new clienteDAO();
    detalleventaDAO daoDV = new detalleventaDAO();
    
    public controladorHVentas(vistaHistoricoVentas vistaVH,ventaDAO daoV){
        this.vistaHV = vistaVH;
        this.daoV = daoV;
        this.vistaHV.btncerrar.addActionListener(this);
        this.vistaHV.tblventas.addMouseListener(this);
        this.vistaHV.rbdia.addActionListener(this);
        this.vistaHV.rbentre.addActionListener(this);
        this.vistaHV.btnfiltrar.addActionListener(this);
        this.vistaHV.btntodo.addActionListener(this);
    }
    
    public void inicializarHVentas(){
        vistaHV.rbdia.setSelected(true);
        vistaHV.lbly.setVisible(false);
        vistaHV.dtfecha2.setVisible(false);
        vistaHV.txtcliente.setEditable(false);
        vistaHV.txtfecha.setEditable(false);
        vistaHV.txtempleado.setEditable(false);
        vistaHV.txttotal.setEditable(false);       
        LLenarTablaProd(vistaHV.tblventas);
        
    }
    
    public void LLenarTablaProd(JTable tablaD){
        
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
        modeloT.addColumn("DNI CLIENTE");
        modeloT.addColumn("CAJA");
        
        Object[] columna = new Object[6];

        int numRegistros = daoV.listarVentas().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = daoV.listarVentas().get(i).getCodigo();
            columna[1] = daoV.listarVentas().get(i).getFecha();
            columna[2] = daoV.listarVentas().get(i).getImp_tot();
            columna[3] = daoV.listarVentas().get(i).getEmpleado();
            columna[4] = daoV.listarVentas().get(i).getCliente();
            columna[5] = daoV.listarVentas().get(i).getCaja();
            modeloT.addRow(columna);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaHV.rbentre){
            if(vistaHV.rbentre.isSelected()){
                vistaHV.lblfiltrar.setText("Filtrar desde:");
                vistaHV.lbly.setVisible(true);
                vistaHV.dtfecha2.setVisible(true);
            }
        }
        
        if(e.getSource() == vistaHV.btntodo){
            LLenarTablaProd(vistaHV.tblventas);
        }
        
        if(e.getSource() == vistaHV.rbdia){
            if(vistaHV.rbdia.isSelected()){
                vistaHV.lblfiltrar.setText("Filtrar día:");
                vistaHV.lbly.setVisible(false);
                vistaHV.dtfecha2.setVisible(false);
            }
        }
        
        if(e.getSource() == vistaHV.btnfiltrar){
            DefaultTableModel  modeloT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
            };
            vistaHV.tblventas.setModel(modeloT);

            modeloT.addColumn("COD. VENTA");
            modeloT.addColumn("FECHA");
            modeloT.addColumn("IMPORTE TOTAL");
            modeloT.addColumn("DNI EMPLEADO");
            modeloT.addColumn("DNI CLIENTE");
            modeloT.addColumn("CAJA");

            Object[] columna = new Object[6];
            
            if(vistaHV.rbdia.isSelected()){
                String fec1 = new SimpleDateFormat("yyyy-MM-dd").format(vistaHV.dtfecha1.getDate());

                int numRegistros = daoV.filtrarDia(fec1).size();

                for (int i = 0; i < numRegistros; i++) {
                    columna[0] = daoV.filtrarDia(fec1).get(i).getCodigo();
                    columna[1] = daoV.filtrarDia(fec1).get(i).getFecha();
                    columna[2] = daoV.filtrarDia(fec1).get(i).getImp_tot();
                    columna[3] = daoV.filtrarDia(fec1).get(i).getEmpleado();
                    columna[4] = daoV.filtrarDia(fec1).get(i).getCliente();
                    columna[5] = daoV.filtrarDia(fec1).get(i).getCaja();
                    modeloT.addRow(columna);
                } 
            }
            if(vistaHV.rbentre.isSelected()){
                String fec1 = new SimpleDateFormat("yyyy-MM-dd").format(vistaHV.dtfecha1.getDate());
                String fec2 = new SimpleDateFormat("yyyy-MM-dd").format(vistaHV.dtfecha2.getDate());

                int numRegistros = daoV.filtrarEntre(fec1, fec2).size();

                for (int i = 0; i < numRegistros; i++) {
                    columna[0] = daoV.filtrarEntre(fec1, fec2).get(i).getCodigo();
                    columna[1] = daoV.filtrarEntre(fec1, fec2).get(i).getFecha();
                    columna[2] = daoV.filtrarEntre(fec1, fec2).get(i).getImp_tot();
                    columna[3] = daoV.filtrarEntre(fec1, fec2).get(i).getEmpleado();
                    columna[4] = daoV.filtrarEntre(fec1, fec2).get(i).getCliente();
                    columna[5] = daoV.filtrarEntre(fec1, fec2).get(i).getCaja();
                    modeloT.addRow(columna);
                }
            }
            
        }
        
        if(e.getSource() == vistaHV.btncerrar){
            vistaHV.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vistaHV.tblventas){
            if(e.getClickCount() == 2){                                
                String cod_ven = (String)vistaHV.tblventas.getValueAt(vistaHV.tblventas.getSelectedRow(),0);
                Object fecha = vistaHV.tblventas.getValueAt(vistaHV.tblventas.getSelectedRow(),1);
                Object importe = vistaHV.tblventas.getValueAt(vistaHV.tblventas.getSelectedRow(),2);
                String cod_emp = (String)vistaHV.tblventas.getValueAt(vistaHV.tblventas.getSelectedRow(),3);
                String cod_cli = (String)vistaHV.tblventas.getValueAt(vistaHV.tblventas.getSelectedRow(),4);
                String nom_cli="";
                
                modeloE = new Empleado();
                
                modeloE = daoE.buscarxDni(cod_emp);
                if(cod_cli.equals("1")){nom_cli="Publico en General";}
                else{
                    modeloC = daoC.buscarxDni(cod_cli);
                    nom_cli = modeloC.getNombres()+modeloC.getApaterno();
                }
                
                DefaultTableModel  modeloT = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                //all cells false
                    return false;
                    }
                };
                vistaHV.tbldetalle.setModel(modeloT);

                modeloT.addColumn("CANT.");
                modeloT.addColumn("DESCRIPCION");
                modeloT.addColumn("PREC. UND.");
                modeloT.addColumn("IMPORTE");

                Object[] columna = new Object[4];

                int numRegistros = daoDV.listaDetVen(cod_ven).size();

                for (int i = 0; i < numRegistros; i++) {
                    columna[0] = daoDV.listaDetVen(cod_ven).get(i).getCantidad();
                    modeloProd = daoProd.buscarP(daoDV.listaDetVen(cod_ven).get(i).getCod_prod());
                    columna[1] = modeloProd.getNombre();
                    columna[2] = daoDV.listaDetVen(cod_ven).get(i).getP_base();
                    columna[3] = daoDV.listaDetVen(cod_ven).get(i).getP_cant();
                    modeloT.addRow(columna);
                }
                
                vistaHV.txtcliente.setText(nom_cli);
                vistaHV.txtempleado.setText(modeloE.getNombres()+ modeloE.getApaterno());
                vistaHV.txtfecha.setText(String.valueOf(fecha));
                vistaHV.txttotal.setText(String.valueOf(importe));
                
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
