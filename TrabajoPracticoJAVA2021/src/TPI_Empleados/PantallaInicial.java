
package TPI_Empleados;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class PantallaInicial extends javax.swing.JFrame {

    //Constructor
    public PantallaInicial() {
        initComponents();
         //coloco un titulo al jFrame
         this.setTitle("Software Empleados");
         
         
        //para que el formulario parezca en el centro
        this.setLocationRelativeTo(null);
        
        //desactiva el boton maximizar
        this.setResizable(false);
        
        //llamo al metodo setIcono: que me devuelve la imagen icono adapatada al tamaño del boton
        btnSignUp.setIcon(setIcono("/Imagenes/Crear.png",btnSignUp));
        
        btnLogin.setIcon(setIcono("/Imagenes/Login.png",btnLogin));
        
    }
    
    //Creo un metodo para que la imagen se adapte al tamaño del boton
    public Icon setIcono(String url, JButton boton){
        
        ImageIcon icon = new ImageIcon(getClass().getResource(url));
        
        int ancho = boton.getWidth();
        int alto = boton.getHeight();
        
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        return icono;
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLogin = new javax.swing.JButton();
        btnSignUp = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login.png"))); // NOI18N
        btnLogin.setBorder(null);
        btnLogin.setBorderPainted(false);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setFocusPainted(false);
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoginMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLoginMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLoginMouseReleased(evt);
            }
        });
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(634, 363, 170, 80));

        btnSignUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Crear.png"))); // NOI18N
        btnSignUp.setBorder(null);
        btnSignUp.setBorderPainted(false);
        btnSignUp.setContentAreaFilled(false);
        btnSignUp.setFocusPainted(false);
        btnSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSignUpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSignUpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSignUpMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSignUpMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSignUpMouseReleased(evt);
            }
        });
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });
        getContentPane().add(btnSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 470, 170, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
  
       //creo un objeto Pantalla 
       PantallaLogin verPantalla = new PantallaLogin();
       //llamo al objeto
       verPantalla.setVisible(true); 
       //Oculto la pantalla: Pantalla Principal
       this.setVisible(false);
        
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked

    }//GEN-LAST:event_btnLoginMouseClicked

    private void btnLoginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMousePressed
        // Evento cuando presiono el boton
        //Cambia a una imagen mas oscura
       btnLogin.setIcon(setIcono("/Imagenes/LoginOscuro.png",btnLogin));
    }//GEN-LAST:event_btnLoginMousePressed

    private void btnLoginMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseReleased
        //Evento cuando suelto el boton
        //Vuelve a la imagen inicial
        btnLogin.setIcon(setIcono("/Imagenes/Login.png",btnLogin));
    }//GEN-LAST:event_btnLoginMouseReleased

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseEntered
        //Evento entrar en un componente con el puntero / cuando paso el puntero sobre el boton
        // Cambia a una imagen mas clara
         btnLogin.setIcon(setIcono("/Imagenes/LoginClaro.png",btnLogin));
    }//GEN-LAST:event_btnLoginMouseEntered

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseExited
      //Evento salir en un componente con el puntero / Cuando saco el puntero fuera del boton
      //Vuelve a la imagen inicial
        btnLogin.setIcon(setIcono("/Imagenes/Login.png",btnLogin));
    }//GEN-LAST:event_btnLoginMouseExited

    private void btnSignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignUpMouseClicked

    }//GEN-LAST:event_btnSignUpMouseClicked

    private void btnSignUpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignUpMouseEntered

        //Evento entrar en un componente con el puntero / cuando paso el puntero sobre el boton
        // Cambia a una imagen mas clara
         btnSignUp.setIcon(setIcono("/Imagenes/Crear_roll.png",btnSignUp));
    }//GEN-LAST:event_btnSignUpMouseEntered

    private void btnSignUpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignUpMouseExited
      //Evento salir en un componente con el puntero / Cuando saco el puntero fuera del boton
      //Vuelve a la imagen inicial
        btnSignUp.setIcon(setIcono("/Imagenes/Crear.png",btnSignUp));   
    }//GEN-LAST:event_btnSignUpMouseExited

    private void btnSignUpMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignUpMousePressed
               
        // Evento cuando presiono el boton
        //Cambia a una imagen mas oscura
       btnSignUp.setIcon(setIcono("/Imagenes/CrearOscuro.png",btnSignUp));
       
    }//GEN-LAST:event_btnSignUpMousePressed

    private void btnSignUpMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignUpMouseReleased
        //Evento cuando suelto el boton
        //Vuelve a la imagen inicial
        btnSignUp.setIcon(setIcono("/Imagenes/Crear.png",btnSignUp));
    }//GEN-LAST:event_btnSignUpMouseReleased

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
       //creo el objeto Pantalla Alta Usuario 
       PantallaAltaUsuario verPantallaAltaUsuario = new PantallaAltaUsuario();
       //Muestra la pantalla: PantallaAltaUsuario
       verPantallaAltaUsuario.setVisible(true); 
       //Saco la pantalla Principal
       this.setVisible(false);
        
    }//GEN-LAST:event_btnSignUpActionPerformed

    //Main
    public static void main(String args[]) throws SQLException {
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
            java.util.logging.Logger.getLogger(PantallaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaInicial().setVisible(true);
            }
        });
        
    }//FIN main

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
