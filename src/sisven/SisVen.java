/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisven;

import DAO.LoginDAO;
import controladores.controladorLogin;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import vistas.vistaLogin;

/**
 *
 * @author Jhunior
 */
public class SisVen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        vistaLogin vistalogin = new vistaLogin();
        LoginDAO logindao = new LoginDAO();
        controladorLogin controladorlogin = new controladorLogin(vistalogin, logindao);
        try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
        vistalogin.setVisible(true);
        vistalogin.setLocationRelativeTo(null);
    }
    
}
