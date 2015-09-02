/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.empleadoDAO;
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
import modelos.Empleado;
import vistas.vistaEmpleados;
import vistas.vistaEmpleadosNM;


/**
 *
 * @author Jhunior
 */
public class controladorEmpleado implements ActionListener,KeyListener,MouseListener{
    vistaEmpleados vistaE = new vistaEmpleados();
    empleadoDAO daoE = new empleadoDAO();
    Empleado modeloE = new Empleado();
    
    public controladorEmpleado(vistaEmpleados vistaE, empleadoDAO daoE){
        this.vistaE = vistaE;
        this.daoE = daoE;
        this.vistaE.btnnuevo.addActionListener(this);
        this.vistaE.btnbuscar.addActionListener(this);
        this.vistaE.btnborrar.addActionListener(this);
        this.vistaE.btnsalir.addActionListener(this);
        this.vistaE.txtbuscardni.addKeyListener(this);
        this.vistaE.txtfiltrarape.addKeyListener(this);
        this.vistaE.tblmostrarclientes.addMouseListener(this);
    }
    
    public void inicializarEmpleado(){
        LLenarTabla(vistaE.tblmostrarclientes);
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

        int numRegistros = daoE.listarEmpleado().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = daoE.listarEmpleado().get(i).getDni();
            columna[1] = daoE.listarEmpleado().get(i).getApaterno();
            columna[2] = daoE.listarEmpleado().get(i).getAmaterno();
            columna[3] = daoE.listarEmpleado().get(i).getNombres();
            columna[4] = daoE.listarEmpleado().get(i).getTelefono();
            modeloT.addRow(columna);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== vistaE.btnnuevo){
            vistaEmpleadosNM vistaENM = new vistaEmpleadosNM();
            vistaENM.lbltitulo.setText("NUEVO EMPLEADO");
            vistaENM.btnmodificar.setVisible(false);
            vistaENM.btnguardarcambio.setVisible(false);
            controladorEmpleadoNM controladorENM = new controladorEmpleadoNM(vistaENM, daoE);
            vistaENM.setLocationRelativeTo(null);
            vistaENM.setVisible(true);
            vistaE.dispose();
        }
        if(e.getSource() == vistaE.btnbuscar){
            String dni = vistaE.txtbuscardni.getText();
            modeloE = daoE.buscarxDni(dni);
            if(!modeloE.getDni().equals("")){
                vistaEmpleadosNM vistaENM = new vistaEmpleadosNM();
                controladorEmpleadoNM controladorENM = new controladorEmpleadoNM(vistaENM, daoE);
                vistaENM.lbltitulo.setText("EMPLEADO");
                vistaENM.btnregistrar.setVisible(false);
                vistaENM.btnguardarcambio.setEnabled(false);
                if (!modeloE.getPas().equals("")){
                    vistaENM.btnccon.setText("Modificar Contraseña");
                }
                controladorENM.inicializarEmpleadoNM(modeloE, false);
                vistaENM.setLocationRelativeTo(null);
                vistaENM.setVisible(true);
                vistaE.dispose();
            }else{
                JOptionPane.showMessageDialog(vistaE, "DNI no encontrado");
            }
        }
        if(e.getSource() == vistaE.btnborrar){
            int filInicio = vistaE.tblmostrarclientes.getSelectedRow();
            int numfilas = vistaE.tblmostrarclientes.getSelectedRowCount();
            ArrayList<String> listaDni = new ArrayList<>();
            String dni;
            if(filInicio>=0){
                for(int i = 0; i<numfilas; i++){
                    dni = String.valueOf(vistaE.tblmostrarclientes.getValueAt(i+filInicio, 0));
                    listaDni.add(i, dni);
                }

                for(int j = 0; j<numfilas; j++){
                    int rpta = JOptionPane.showConfirmDialog(null, "Desea eliminar registro con dni: "+listaDni.get(j)+"? ");
                    if(rpta==0){
                        daoE.eliminarEmpleado(listaDni.get(j));
                    }
                }
                LLenarTabla(vistaE.tblmostrarclientes);
            }else{
                JOptionPane.showMessageDialog(null, "Elija al menos un registro para eliminar.");
            }
        }
        if(e.getSource() == vistaE.btnsalir){
            vistaE.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            String dni = vistaE.txtbuscardni.getText();
            modeloE = daoE.buscarxDni(dni);
            if(!modeloE.getDni().equals("")){
                vistaEmpleadosNM vistaENM = new vistaEmpleadosNM();
                controladorEmpleadoNM controladorENM = new controladorEmpleadoNM(vistaENM, daoE);
                vistaENM.lbltitulo.setText("EMPLEADO");
                vistaENM.btnregistrar.setVisible(false);
                vistaENM.btnguardarcambio.setEnabled(false);
                if (!modeloE.getPas().equals("")){
                    vistaENM.btnccon.setText("Modificar Contraseña");
                }
                controladorENM.inicializarEmpleadoNM(modeloE, false);
                vistaENM.setLocationRelativeTo(null);
                vistaENM.setVisible(true);
                vistaE.dispose();
            }else{
                JOptionPane.showMessageDialog(vistaE, "DNI no encontrado");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource()== vistaE.txtfiltrarape){
            
            String apellido = vistaE.txtfiltrarape.getText();
            
            DefaultTableModel  modeloT = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
                }
            };
            vistaE.tblmostrarclientes.setModel(modeloT);
        
            modeloT.addColumn("DNI");
            modeloT.addColumn("A.PATERNO");
            modeloT.addColumn("A.MATERNO");
            modeloT.addColumn("NOMBRES");
            modeloT.addColumn("TELEFONO");
        
            Object[] columna = new Object[5];

            int numRegistros = daoE.filtrarEmpleado(apellido).size();

            for (int i = 0; i < numRegistros; i++) {
                columna[0] = daoE.filtrarEmpleado(apellido).get(i).getDni();
                columna[1] = daoE.filtrarEmpleado(apellido).get(i).getApaterno();
                columna[2] = daoE.filtrarEmpleado(apellido).get(i).getAmaterno();
                columna[3] = daoE.filtrarEmpleado(apellido).get(i).getNombres();
                columna[4] = daoE.filtrarEmpleado(apellido).get(i).getTelefono();
                modeloT.addRow(columna);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){
            String dni = (String) vistaE.tblmostrarclientes.getValueAt(vistaE.tblmostrarclientes.getSelectedRow(), 0);
            modeloE = daoE.buscarxDni(dni);
            vistaEmpleadosNM vistaENM = new vistaEmpleadosNM();
            controladorEmpleadoNM controladorENM = new controladorEmpleadoNM(vistaENM, daoE);
            vistaENM.lbltitulo.setText("EMPLEADO");
            vistaENM.btnregistrar.setVisible(false);
            vistaENM.btnguardarcambio.setEnabled(false);
            if (!modeloE.getPas().equals("")){
                    vistaENM.btnccon.setText("Modificar Contraseña");
            }
            controladorENM.inicializarEmpleadoNM(modeloE, false);
            vistaENM.setLocationRelativeTo(null);
            vistaENM.setVisible(true);
            vistaE.dispose();
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
