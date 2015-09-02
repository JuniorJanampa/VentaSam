/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

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
import modelos.Proveedores;
import vistas.vistaProveedores;
import vistas.vistaProveedoresNM;

/**
 *
 * @author Jhunior
 */
public class controladorProveedor implements ActionListener,KeyListener,MouseListener{
    vistaProveedores vistaProv = new vistaProveedores();
    proveedoresDAO daoProv = new proveedoresDAO();
    Proveedores modeloProv = new Proveedores();
    
    public controladorProveedor(vistaProveedores vistaProv, proveedoresDAO daoProv){
        this.vistaProv = vistaProv;
        this.daoProv = daoProv;
        this.vistaProv.btnnuevo.addActionListener(this);
        this.vistaProv.btnbuscar.addActionListener(this);
        this.vistaProv.btnborrar.addActionListener(this);
        this.vistaProv.btnsalir.addActionListener(this);
        this.vistaProv.txtbuscarruc.addKeyListener(this);
        this.vistaProv.txtfiltrarrazon.addKeyListener(this);
        this.vistaProv.tbldatos.addMouseListener(this);
    }
    
    public void inicializarProveedor(){
        LLenarTabla(vistaProv.tbldatos);
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
        
        modeloT.addColumn("R.U.C.");
        modeloT.addColumn("RAZON SOCIAL");
        modeloT.addColumn("TELEFONO");
        modeloT.addColumn("CONTACTO");
        modeloT.addColumn("TELEFONO CONTACTO");
        
        Object[] columna = new Object[5];

        int numRegistros = daoProv.listarProveedores().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = daoProv.listarProveedores().get(i).getRuc();
            columna[1] = daoProv.listarProveedores().get(i).getRazonsocial();
            columna[2] = daoProv.listarProveedores().get(i).getTelefono();
            columna[3] = daoProv.listarProveedores().get(i).getContacto();
            columna[4] = daoProv.listarProveedores().get(i).getTele_contacto();
            modeloT.addRow(columna);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaProv.btnnuevo){
            vistaProveedoresNM vistaProvNM = new vistaProveedoresNM();
            vistaProvNM.lbltitutlo.setText("NUEVO PROVEEDOR");
            vistaProvNM.jLabel1.setVisible(false);
            vistaProvNM.txtcodigo.setVisible(false);
            vistaProvNM.btnmodificar.setVisible(false);
            vistaProvNM.btnguardarcambio.setVisible(false);
            controladorProveedorNM controladorCNM = new controladorProveedorNM(vistaProvNM, daoProv);
            vistaProvNM.setLocationRelativeTo(null);
            vistaProvNM.setVisible(true);
            vistaProv.dispose();
        }
        if(e.getSource() == vistaProv.btnbuscar){
            String ruc = vistaProv.txtbuscarruc.getText();
            modeloProv = daoProv.buscarRuc(ruc);
            if(!modeloProv.getCodigo().equals("")){
                vistaProveedoresNM vistaProvNM = new vistaProveedoresNM();
                controladorProveedorNM controladorProvNM = new controladorProveedorNM(vistaProvNM, daoProv);
                vistaProvNM.lbltitutlo.setText("PROVEEDOR");
                vistaProvNM.btnregistrar.setVisible(false);
                vistaProvNM.btnguardarcambio.setEnabled(false);
                vistaProvNM.txtcodigo.setEditable(false);
                controladorProvNM.inicializarProveedoreNM(modeloProv, false);
                vistaProvNM.setLocationRelativeTo(null);
                vistaProvNM.setVisible(true);
                vistaProv.dispose();
            }else{
                JOptionPane.showMessageDialog(vistaProv, "R.U.C. no encontrado");
            }
        }
        
        if(e.getSource() == vistaProv.btnborrar){
            int filInicio = vistaProv.tbldatos.getSelectedRow();
            int numfilas = vistaProv.tbldatos.getSelectedRowCount();
            ArrayList<String> listaruc = new ArrayList<>();
            String ruc;
            if(filInicio>=0){
                for(int i = 0; i<numfilas; i++){
                    ruc = String.valueOf(vistaProv.tbldatos.getValueAt(i+filInicio, 0));
                    listaruc.add(i, ruc);
                }

                for(int j = 0; j<numfilas; j++){
                    int rpta = JOptionPane.showConfirmDialog(null, "Desea eliminar registro con R.U.C.: "+listaruc.get(j)+"? ");
                    if(rpta==0){
                        daoProv.eliminarProveedor(listaruc.get(j));
                    }
                }
                LLenarTabla(vistaProv.tbldatos);
            }else{
                JOptionPane.showMessageDialog(null, "Elija al menos un registro para eliminar.");
            }
        }
        if(e.getSource() == vistaProv.btnsalir){
            vistaProv.dispose();
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            String ruc = vistaProv.txtbuscarruc.getText();
            modeloProv = daoProv.buscarRuc(ruc);
            if(!modeloProv.getRuc().equals("")){
                vistaProveedoresNM vistaProvNM = new vistaProveedoresNM();
                controladorProveedorNM controladorProvNM = new controladorProveedorNM(vistaProvNM, daoProv);
                vistaProvNM.lbltitutlo.setText("PROVEEDOR");
                vistaProvNM.txtcodigo.setEditable(false);
                vistaProvNM.btnregistrar.setVisible(false);
                vistaProvNM.btnguardarcambio.setEnabled(false);
                controladorProvNM.inicializarProveedoreNM(modeloProv, false);
                vistaProvNM.setLocationRelativeTo(null);
                vistaProvNM.setVisible(true);
                vistaProv.dispose();
            }else{
                JOptionPane.showMessageDialog(vistaProv, "R.U.C. no encontrado");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == vistaProv.txtfiltrarrazon){
            String rsocial = vistaProv.txtfiltrarrazon.getText();
            DefaultTableModel  modeloT = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                return false;
                }
            };
            vistaProv.tbldatos.setModel(modeloT);

            modeloT.addColumn("R.U.C.");
            modeloT.addColumn("RAZON SOCIAL");
            modeloT.addColumn("TELEFONO");
            modeloT.addColumn("CONTACTO");
            modeloT.addColumn("TELEFONO CONTACTO");

            Object[] columna = new Object[5];

            int numRegistros = daoProv.buscarRazon(rsocial).size();

            for (int i = 0; i < numRegistros; i++) {
                columna[0] = daoProv.buscarRazon(rsocial).get(i).getRuc();
                columna[1] = daoProv.buscarRazon(rsocial).get(i).getRazonsocial();
                columna[2] = daoProv.buscarRazon(rsocial).get(i).getTelefono();
                columna[3] = daoProv.buscarRazon(rsocial).get(i).getContacto();
                columna[4] = daoProv.buscarRazon(rsocial).get(i).getTele_contacto();
                modeloT.addRow(columna);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            String ruc = (String)vistaProv.tbldatos.getValueAt(vistaProv.tbldatos.getSelectedRow(), 0);
            modeloProv = daoProv.buscarRuc(ruc);
            vistaProveedoresNM vistaProvNM = new vistaProveedoresNM();
            controladorProveedorNM controladorProvNM = new controladorProveedorNM(vistaProvNM, daoProv);
            vistaProvNM.lbltitutlo.setText("PROVEEDOR");
            vistaProvNM.txtcodigo.setEditable(false);
            vistaProvNM.btnregistrar.setVisible(false);
            vistaProvNM.btnguardarcambio.setEnabled(false);
            controladorProvNM.inicializarProveedoreNM(modeloProv, false);
            vistaProvNM.setLocationRelativeTo(null);
            vistaProvNM.setVisible(true);
            vistaProv.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
