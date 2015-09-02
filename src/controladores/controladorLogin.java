/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.LoginDAO;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelos.Login;
import vistas.vistaLogin;
import vistas.vistaPrincipal;

/**
 *
 * @author Jhunior
 */
public class controladorLogin implements ActionListener , KeyListener, FocusListener{
    vistaLogin vistalogin = new vistaLogin();
    LoginDAO logindao = new LoginDAO();
    Login modelologin = new Login();
    
    public controladorLogin(vistaLogin vistalogin, LoginDAO logindao){
        this.vistalogin = vistalogin;
        this.logindao = logindao;
        this.vistalogin.btningresar.addActionListener(this);
        this.vistalogin.btnsalir.addActionListener(this);
        this.vistalogin.txtcontra.addKeyListener(this);
        this.vistalogin.txtcontra.addFocusListener(this);
    }
    
    public void InicializarLogin(){
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //AL HACER CLICK EN SALIR
        if (e.getSource() == vistalogin.btnsalir){
            System.exit(0);
        }
        //AL HACER CLICK EN INGRESAR
        if (e.getSource() == vistalogin.btningresar){
            String dni = "";
            String usuario = vistalogin.txtusuario.getText();
            String contra = String.valueOf(vistalogin.txtcontra.getPassword());
            modelologin = logindao.verificaUsuario(usuario, contra);
            if(modelologin == null){
                JOptionPane.showMessageDialog(vistalogin, "Usuario y/o Contraseña incorrecto.");
            }else{
                JOptionPane.showMessageDialog(vistalogin, "Datos Correctos");
                vistaPrincipal vistaP = new vistaPrincipal();
                vistaP.lblbienvenida.setText("BIENVENIDO: "+modelologin.getNombres()+", "+modelologin.getApaterno());
                vistaP.lblrol.setText("ROL: " + modelologin.getRol());
                dni = modelologin.getDni();
                controladorPrincipal controladorP = new controladorPrincipal(vistaP,dni);
                vistaP.setVisible(true);
                vistaP.setExtendedState(JFrame.MAXIMIZED_BOTH);
                vistalogin.setVisible(false);
                controladorP.inicio();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
         if(e.getKeyCode() == KeyEvent.VK_ENTER){
            String dni = "";
            String usuario = vistalogin.txtusuario.getText();
            String contra = String.valueOf(vistalogin.txtcontra.getPassword());
            modelologin = logindao.verificaUsuario(usuario, contra);
            if(modelologin == null){
                JOptionPane.showMessageDialog(vistalogin, "Usuario y/o Contraseña incorrecto.");
            }else{
                JOptionPane.showMessageDialog(vistalogin, "Datos Correctos");
                vistaPrincipal vistaP = new vistaPrincipal();
                vistaP.lblbienvenida.setText("BIENVENIDO: "+modelologin.getNombres()+", "+modelologin.getApaterno());
                vistaP.lblrol.setText("ROL: " + modelologin.getRol());
                dni = modelologin.getDni();
                controladorPrincipal controladorP = new controladorPrincipal(vistaP,dni);
                vistaP.setVisible(true);
                vistaP.setExtendedState(JFrame.MAXIMIZED_BOTH);
                vistalogin.setVisible(false);
                controladorP.inicio();
            }
        }       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void focusGained(FocusEvent e) {
        vistalogin.txtcontra.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        
    }
}
