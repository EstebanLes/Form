package TPI_Empleados;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.DropMode.INSERT;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class DatabaseConection {
    
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String USER="root";
    private static final String PASSWORD="Sole2021";
    private static final String URL="jdbc:mysql://localhost:3306/TPI_Java_Intermedio";
    
    private Connection con;

    //Constructor
    public DatabaseConection() {
        con =null;
    }
   
    //Método que conecta con la Base de Datos
    public Connection getConectar() throws SQLException{
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("**************************");
            System.out.println("ÉXITO AL CONECTARSE...!!! ");
            System.out.println("**************************");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseConection.class.getName()).log(Level.SEVERE, null,ex);
        }
        return con;
    }// FIN metodo getConectar
    
    //Metodo para cerrar la conexion
    public void Cerrar(){
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error al cerrar la conexión con la Base de Datos", JOptionPane.ERROR);
        }
    }
    
    
   //Método buscar empleado por Legajo
    public Empleados getEmpleado(int legajo) {

        Empleados emple= null;
        
        try {
             // Hago la conexion con la BD    
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM empleados WHERE legajo = '" + legajo + "'");
             
             if(rs.next()){

            emple = new Empleados( rs.getString("apellido"),
                                   rs.getString("nombre"),
                                   rs.getInt("legajo"),
                                   rs.getInt("dni"),
                                   rs.getString("fechaNacimiento"),
                                   rs.getString("Genero"),
                                   rs.getString("Cargo"),
                                   rs.getDouble("SueldoBruto")
                                  );
                 
             }else {JOptionPane.showMessageDialog(null, "El Legajo ingresado no existe en la Base de Datos","Error",JOptionPane.ERROR_MESSAGE);
                   return null;}
             
             rs.close();
             
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return emple;
    }
    
    //metodo que me retorna el proximo Legajo a agregar
    public Empleados getLegajoIncremental(){

        Empleados emple= null;
        int legajo;
        
        try {
             // Hago la conexion con la BD    
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT max(LAST_INSERT_ID (legajo))+1 FROM empleados");
            //ResultSet rs = s.executeQuery("SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='tpi_java_intermedio' AND TABLE_NAME='empleados'");

             if(rs.next()){
               emple = new Empleados( rs.getInt(1));
             }
             rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return emple;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//------------------------------------------------------------------------------------------------------------------------------------    
    

    //Método que retorna los datos cargados en tabla Usuarios - MySQL Workbench
//    public boolean consultarUsuarios(String uss) {
//        boolean resUss=false;
//        try {
//            Statement s = con.createStatement();
//            ResultSet rs = s.executeQuery("SELECT Usuario FROM usuarios");
//            
//            while (rs.next()) {
//                String usuario = rs.getString(1);
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseConection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return resUss;
//    }
    
     
     
      //Metodo que INSERTA DATOS en Tmp_Usuario
    public boolean insertarTmpUsuario(String usuario, String pass) {
         
         boolean insertarUss=false;
        try {
            String sqlInsert = "INSERT INTO tmpusuario ( usuario, pass) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sqlInsert);
            ps.setString(1, usuario);
            ps.setString(2, pass);
            
            boolean res = ps.execute(); //---> devuelve un boolean true: cuando devuelve valores - false: no devuelve valores
            if(res = true){
                System.out.println("Se cargò el uss en TMPUSUARIO");
               insertarUss=false;
            }else{System.out.println("No cargò el uss en TMPUSUARIO- salio por el else");
                  insertarUss=true;
                 }
            
        } catch (SQLException ex) {
                //1062 - hace referencia al codigo de error de registros duplicados - DUPLICATE ENTRY
                 if (ex.getErrorCode() == 1062){
                    JOptionPane.showMessageDialog (null, "El usuario ingresado ya existe en la base de datos", "Usuario Existente", 
                    JOptionPane.ERROR_MESSAGE);
                    insertarUss=true;
                 }
                 else{
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al insertar datos", JOptionPane.ERROR_MESSAGE);
                    insertarUss=true;
                     }
        }    
    return insertarUss;
    }//FIN metodo InsertarTmpUsuario
    
   //Metodo que eliminaTMP
    public boolean eliminarTmpUsuario() {
         
         boolean eliminarUss=false;
        try {
            String sqlInsert = "delete from tmpusuario";
            PreparedStatement ps = con.prepareStatement(sqlInsert);

            boolean res = ps.execute(); 
            
            if(res = true){
                System.out.println("Se elimino correctamente de tmpusuario");
                eliminarUss=false;
            }else{System.out.println("No elimino nada - salio por el ELSE");
                  eliminarUss=true;
                 }
            
        } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al eliminar datos", JOptionPane.ERROR_MESSAGE);
                eliminarUss=true;
        }    
    return eliminarUss;
    }//FIN metodo elimina TMP
    
       //Método consultar tmp_usuario
    public void consultarTmpUsuario() {

        boolean resul=false;
        String uss="";
        
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT Usuario, pass FROM tmpusuario");
            
   
                String usuario = rs.getString(1);
                String pass = rs.getString(2);
                
                System.out.println("Usuarios y pass cargos en tmp_usuario" + usuario +  pass);
                resul=false;
    
  

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConection.class.getName()).log(Level.SEVERE, null, ex);
            resul=true;
        }
  //return resul;

  
    }
}
