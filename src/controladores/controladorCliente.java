/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.clienteDAO;
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
import modelos.Cliente;
import vistas.vistaClientes;
import vistas.vistaClientesNM;
/**
 *
 * @author Jhunior
 */
public class controladorCliente implements ActionListener, KeyListener, MouseListener{
    vistaClientes vistaC = new vistaClientes();
    clienteDAO daoC = new clienteDAO();
    Cliente modeloC = new Cliente();
    
    public controladorCliente(vistaClientes vistaC, clienteDAO daoC){
        this.vistaC = vistaC;
        this.daoC = daoC;
        this.vistaC.btnnuevo.addActionListener(this);
        this.vistaC.btnbuscar.addActionListener(this);
        this.vistaC.btnborrar.addActionListener(this);
        this.vistaC.btnsalir.addActionListener(this);
        this.vistaC.txtbuscardni.addKeyListener(this);
        this.vistaC.txtfiltrarape.addKeyListener(this);
        this.vistaC.tblmostrarclientes.addMouseListener(this);
        
    }
    
    public void inicializarCliente(){
        LLenarTabla(vistaC.tblmostrarclientes);
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
        
        modeloT.addColumn("DNI");
        modeloT.addColumn("A.PATERNO");
        modeloT.addColumn("A.MATERNO");
        modeloT.addColumn("NOMBRES");
        modeloT.addColumn("TELEFONO");
        
        Object[] columna = new Object[5];

        int numRegistros = daoC.listarClientes().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = daoC.listarClientes().get(i).getDni();
            columna[1] = daoC.listarClientes().get(i).getApaterno();
            columna[2] = daoC.listarClientes().get(i).getAmaterno();
            columna[3] = daoC.listarClientes().get(i).getNombres();
            columna[4] = daoC.listarClientes().get(i).getTelefono();
            modeloT.addRow(columna);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()== vistaC.btnnuevo){
            vistaClientesNM vistaCNM = new vistaClientesNM();
            vistaCNM.lbltitulo.setText("NUEVO CLIENTE");
            vistaCNM.btnmodificar.setVisible(false);
            vistaCNM.btnguardarcambio.setVisible(false);
            controladorClienteNM controladorCN = new controladorClienteNM(vistaCNM, daoC);
            vistaCNM.setLocationRelativeTo(null);
            vistaCNM.setVisible(true);
            vistaC.dispose();
        }
        if(e.getSource() == vistaC.btnbuscar){
            String dni = vistaC.txtbuscardni.getText();
            modeloC = daoC.buscarxDni(dni);
            if(!modeloC.getDni().equals("")){
                vistaClientesNM vistaCNM = new vistaClientesNM();
                controladorClienteNM controladorCNM = new controladorClienteNM(vistaCNM, daoC);
                vistaCNM.lbltitulo.setText("CLIENTE");
                vistaCNM.btnregistrar.setVisible(false);
                vistaCNM.btnguardarcambio.setEnabled(false);
                controladorCNM.inicializarClienteNM(modeloC, false);
                vistaCNM.setLocationRelativeTo(null);
                vistaCNM.setVisible(true);
                vistaC.dispose();
            }else{
                JOptionPane.showMessageDialog(vistaC, "DNI no encontrado");
            }
        }
        if(e.getSource() == vistaC.btnborrar){
            int filInicio = vistaC.tblmostrarclientes.getSelectedRow();
            int numfilas = vistaC.tblmostrarclientes.getSelectedRowCount();
            ArrayList<String> listaDni = new ArrayList<>();
            String dni;
            if(filInicio>=0){
                for(int i = 0; i<numfilas; i++){
                    dni = String.valueOf(vistaC.tblmostrarclientes.getValueAt(i+filInicio, 0));
                    listaDni.add(i, dni);
                }

                for(int j = 0; j<numfilas; j++){
                    int rpta = JOptionPane.showConfirmDialog(null, "Desea eliminar registro con dni: "+listaDni.get(j)+"? ");
                    if(rpta==0){
                        daoC.eliminarCliente(listaDni.get(j));
                    }
                }
                LLenarTabla(vistaC.tblmostrarclientes);
            }else{
                JOptionPane.showMessageDialog(null, "Elija al menos un registro para eliminar.");
            }
        }
        if(e.getSource() == vistaC.btnsalir){
            vistaC.dispose();
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            String dni = vistaC.txtbuscardni.getText();
            modeloC = daoC.buscarxDni(dni);
            if(!modeloC.getDni().equals("")){
                vistaClientesNM vistaCNM = new vistaClientesNM();
                controladorClienteNM controladorCNM = new controladorClienteNM(vistaCNM, daoC);
                vistaCNM.lbltitulo.setText("CLIENTE");
                vistaCNM.btnregistrar.setVisible(false);
                vistaCNM.btnguardarcambio.setEnabled(false);
                controladorCNM.inicializarClienteNM(modeloC, false);
                vistaCNM.setLocationRelativeTo(null);
                vistaCNM.setVisible(true);
                vistaC.dispose();
            }else{
                JOptionPane.showMessageDialog(vistaC, "DNI no encontrado");
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource()== vistaC.txtfiltrarape){
            
            String apellido = vistaC.txtfiltrarape.getText();
            
            DefaultTableModel  modeloT = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
                }
            };
            vistaC.tblmostrarclientes.setModel(modeloT);
        
            modeloT.addColumn("DNI");
            modeloT.addColumn("A.PATERNO");
            modeloT.addColumn("A.MATERNO");
            modeloT.addColumn("NOMBRES");
            modeloT.addColumn("TELEFONO");
        
            Object[] columna = new Object[5];

            int numRegistros = daoC.filtrarCliente(apellido).size();

            for (int i = 0; i < numRegistros; i++) {
                columna[0] = daoC.filtrarCliente(apellido).get(i).getDni();
                columna[1] = daoC.filtrarCliente(apellido).get(i).getApaterno();
                columna[2] = daoC.filtrarCliente(apellido).get(i).getAmaterno();
                columna[3] = daoC.filtrarCliente(apellido).get(i).getNombres();
                columna[4] = daoC.filtrarCliente(apellido).get(i).getTelefono();
                modeloT.addRow(columna);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){
            String dni = (String) vistaC.tblmostrarclientes.getValueAt(vistaC.tblmostrarclientes.getSelectedRow(), 0);
            modeloC = daoC.buscarxDni(dni);
            vistaClientesNM vistaCNM = new vistaClientesNM();
            controladorClienteNM controladorCNM = new controladorClienteNM(vistaCNM, daoC);
            vistaCNM.lbltitulo.setText("CLIENTE");
            vistaCNM.btnregistrar.setVisible(false);
            vistaCNM.btnguardarcambio.setEnabled(false);
            controladorCNM.inicializarClienteNM(modeloC, false);
            vistaCNM.setLocationRelativeTo(null);
            vistaCNM.setVisible(true);
            vistaC.dispose();
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
