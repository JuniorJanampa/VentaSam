/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import com.itextpdf.text.DocumentException;
import impresora.ImprimirTicket;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Jhunior
 */
public class vistaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form vistaPrincipal
     */
    public vistaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblbienvenida = new javax.swing.JLabel();
        lblrol = new javax.swing.JLabel();
        pgeneral = new javax.swing.JPanel();
        btnnuevaventa = new javax.swing.JButton();
        btnclientes = new javax.swing.JButton();
        btnfaltantes = new javax.swing.JButton();
        btnproductos = new javax.swing.JButton();
        btnproveedores = new javax.swing.JButton();
        padministrador = new javax.swing.JPanel();
        btncaja = new javax.swing.JButton();
        btncompras = new javax.swing.JButton();
        btninventarios = new javax.swing.JButton();
        btnempleados = new javax.swing.JButton();
        btnhistoricoven = new javax.swing.JButton();
        btnhistoricocom = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btncambiarusuario = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnconf = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal-SCAMVentas1.1");
        setIconImage(getIconImage());
        setResizable(false);

        lblbienvenida.setFont(new java.awt.Font("Faktos", 3, 18)); // NOI18N
        lblbienvenida.setText("USUARIO");
        lblbienvenida.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblrol.setFont(new java.awt.Font("Faktos", 0, 18)); // NOI18N
        lblrol.setText("ROL DE USUARIO");
        lblrol.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pgeneral.setBorder(javax.swing.BorderFactory.createTitledBorder("General"));

        btnnuevaventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sale.png"))); // NOI18N
        btnnuevaventa.setText("Nueva Venta");
        btnnuevaventa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user.png"))); // NOI18N
        btnclientes.setText("Clientes");

        btnfaltantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/remove_from_shopping_cart.png"))); // NOI18N
        btnfaltantes.setText("Faltantes");

        btnproductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tag_blue.png"))); // NOI18N
        btnproductos.setText("Productos");

        btnproveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/users.png"))); // NOI18N
        btnproveedores.setText("Proveedores");

        javax.swing.GroupLayout pgeneralLayout = new javax.swing.GroupLayout(pgeneral);
        pgeneral.setLayout(pgeneralLayout);
        pgeneralLayout.setHorizontalGroup(
            pgeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pgeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pgeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnproductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnfaltantes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnclientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnuevaventa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(btnproveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pgeneralLayout.setVerticalGroup(
            pgeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pgeneralLayout.createSequentialGroup()
                .addComponent(btnnuevaventa, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnfaltantes, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnproveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(btnproductos, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        padministrador.setBorder(javax.swing.BorderFactory.createTitledBorder("Administrador"));

        btncaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dollar_currency_sign.png"))); // NOI18N
        btncaja.setText("Caja");

        btncompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/shopping_cart.png"))); // NOI18N
        btncompras.setText("Nueva Compra");

        btninventarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/refresh.png"))); // NOI18N
        btninventarios.setText("Movimiento Inventarios");

        btnempleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user.png"))); // NOI18N
        btnempleados.setText("Empleado");

        btnhistoricoven.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendar.png"))); // NOI18N
        btnhistoricoven.setText("Historico Ventas");

        btnhistoricocom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendar.png"))); // NOI18N
        btnhistoricocom.setText("Historico Compras");

        javax.swing.GroupLayout padministradorLayout = new javax.swing.GroupLayout(padministrador);
        padministrador.setLayout(padministradorLayout);
        padministradorLayout.setHorizontalGroup(
            padministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(padministradorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(padministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btncompras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btninventarios, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(btnempleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncaja, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnhistoricoven, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(btnhistoricocom, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
                .addContainerGap())
        );
        padministradorLayout.setVerticalGroup(
            padministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, padministradorLayout.createSequentialGroup()
                .addComponent(btncaja, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btncompras, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnempleados, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnhistoricocom, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnhistoricoven, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btninventarios, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete.png"))); // NOI18N
        btnsalir.setText("SALIR");

        btncambiarusuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search_user.png"))); // NOI18N
        btncambiarusuario.setText("Cambiar Usuario");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/scam2.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        btnconf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/process.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(pgeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(padministrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(btncambiarusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(204, 204, 204)
                        .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblbienvenida)
                                .addComponent(lblrol))
                            .addComponent(btnconf, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pgeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(padministrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncambiarusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblbienvenida)
                        .addGap(18, 18, 18)
                        .addComponent(lblrol)
                        .addGap(187, 187, 187)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnconf)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        vistaInfo vinfo = new vistaInfo(this, rootPaneCheckingEnabled);
        vinfo.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vistaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btncaja;
    public javax.swing.JButton btncambiarusuario;
    public javax.swing.JButton btnclientes;
    public javax.swing.JButton btncompras;
    public javax.swing.JButton btnconf;
    public javax.swing.JButton btnempleados;
    public javax.swing.JButton btnfaltantes;
    public javax.swing.JButton btnhistoricocom;
    public javax.swing.JButton btnhistoricoven;
    public javax.swing.JButton btninventarios;
    public javax.swing.JButton btnnuevaventa;
    public javax.swing.JButton btnproductos;
    public javax.swing.JButton btnproveedores;
    public javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel lblbienvenida;
    public javax.swing.JLabel lblrol;
    public javax.swing.JPanel padministrador;
    public javax.swing.JPanel pgeneral;
    // End of variables declaration//GEN-END:variables
}
