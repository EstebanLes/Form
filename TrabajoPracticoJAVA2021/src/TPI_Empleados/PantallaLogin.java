package TPI_Empleados;

import java.awt.Color;
import static java.awt.Frame.NORMAL;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PantallaLogin extends javax.swing.JFrame{
  
    //Variables
    private Usuarios usuario;
    private DatabaseConection c;
    
    //constructor
    public PantallaLogin() { 
        
         initComponents();
         
         usuario = new Usuarios();
         
         //coloco un titulo al jFrame
         this.setTitle("Login");
         
        //para que el formulario parezca en el centro
        this.setLocationRelativeTo(null);
        
        //desactiva el boton maximizar
        this.setResizable(false);
    
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPass = new javax.swing.JPasswordField();
        txtUss = new javax.swing.JTextField();
        btnLogin = new javax.swing.JToggleButton();
        btnAltaUsuario = new javax.swing.JButton();
        btnCancelar = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 180, 30));
        getContentPane().add(txtUss, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 180, 30));

        btnLogin.setBackground(new java.awt.Color(153, 102, 255));
        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 470, 90, -1));

        btnAltaUsuario.setBackground(new java.awt.Color(255, 255, 255));
        btnAltaUsuario.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnAltaUsuario.setText("Create Account");
        btnAltaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltaUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnAltaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, -1, 30));

        btnCancelar.setBackground(new java.awt.Color(153, 102, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnCancelar.setText("Cancel");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 470, 90, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoLogin.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAltaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaUsuarioActionPerformed
        
        //llamar a la pantalla Alta de Usuario
        PantallaAltaUsuario dg = null;

        try {
            dg = new PantallaAltaUsuario();
        } catch (Exception ex) {
            Logger.getLogger(PantallaInicial.class.getName()
            ).log(Level.SEVERE, null, ex);
        }
        //coloca a la ventana en el centro de la pantalla
        dg.setLocationRelativeTo(null);
        //hace visible la pantalla Alta Usuario
        dg.setVisible(true);
        //oculta la pantalla Login
        this.setVisible(false);
        //---------------------------------------------------------------//
   
    }//GEN-LAST:event_btnAltaUsuarioActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       //crea el objeto Pantalla Inicial 
       PantallaInicial verPantallaInicial = new PantallaInicial();
       //llama a la pantallla Inicial
       verPantallaInicial.setVisible(true); 
       //Oculta la pantalla LOgin
       this.setVisible(false);

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed

       //capturo los valores de las cajas de texto
         String uss = txtUss.getText();
         String pass = txtPass.getText();
     
       boolean validaDatos = usuario.validarCajasUss(uss, pass);
       
        // si tienen cargado los datos ambas cajas de texto
        if(validaDatos == false) {
           //llamo al metodo consultarLogin de la clase usuarios
           boolean resul = usuario.consultarUsuario(uss, pass);
        
           // pregunto si el usuario ingresado NO EXISTE en la base de datos
            if(resul == true){
               //limpio ambas cajas de texto
               txtUss.setText("");
               txtPass.setText("");
               txtUss.requestFocus(true);
               
            }else{ //si existe el usuario logeado...
                 try {
                     //creo un objeto de Empleados
                     PantallaEmpleado verPantallaEmple = new PantallaEmpleado();
                     //llamo al Form de empleados
                     verPantallaEmple.setVisible(true);
                     this.setVisible(false);
                     } catch (ParseException ex) {
                             Logger.getLogger(PantallaLogin.class.getName()).log(Level.SEVERE, null, ex);
                             }
                   }
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
   
    }//GEN-LAST:event_formWindowActivated


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAltaUsuario;
    private javax.swing.JToggleButton btnCancelar;
    private javax.swing.JToggleButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUss;
    // End of variables declaration//GEN-END:variables
}
