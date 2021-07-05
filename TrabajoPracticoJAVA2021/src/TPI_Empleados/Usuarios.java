
package TPI_Empleados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Usuarios {
    
  //Atributos
    private String apellido;
    private String nombre;
    private String uss;
    private String pass;
    private String cargo;
    private String email;
    
    private final String SQL_INSERT = "INSERT INTO usuarios (nombre, apellido, usuario, pass,cargo, mail) VALUES (?, ?, ?, ?,?,?)";

    //Primer constructor
    public Usuarios(String apellido, String nombre, String uss, String pass, String cargo, String email) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.uss = uss;
        this.pass = pass;
        this.cargo = cargo;
        this.email = email;
    }
    
     //Variables
    private DatabaseConection CN; 
    private PreparedStatement ps;

     //Segundo CONSTRUCTOR
     public Usuarios(){
         CN = new DatabaseConection();
         ps = null;
     }
    
    //getters an setters
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUss() {
        return uss;
    }

    public void setUss(String uss) {
        this.uss = uss;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 
    //Método que retorna los datos cargados en tabla Usuarios - MySQL Workbench
    public boolean consultarUsuario(String uss, String pass) {
        
        try {          
            Statement s = CN.getConectar().createStatement();
            ResultSet rs = s.executeQuery("SELECT Usuario, Pass FROM usuarios where Usuario = '" + uss  + "' and Pass = '" + pass +  "'");
         
            //si encuentra datos
            if (rs.next() == true) {
                return false;
            }else { JOptionPane.showMessageDialog(null, "Verificar Usuario o Contraseña incorrectos","Error",JOptionPane.ERROR_MESSAGE); 
                        return true;} 
        } catch (SQLException ex) {
                Logger.getLogger(DatabaseConection.class.getName()).log(Level.SEVERE, null, ex);
                return true;
        }
   }    
      
   // metodo validar cajas de Usuario
   public boolean validarCajasUss(String uss, String pass){
    
         if (uss.trim().equals("") || pass.trim().equals("")) {  
             JOptionPane.showMessageDialog(null, "Faltan datos de cargar","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE); 
             return true;
         }
         return false;
   }
   
   
   public boolean validarCajasTexto2(String apellido, String nombre, String mail, String cargo, String usuario, String pass, String confPass){
          try{
               // pregunto si alguna de las cajas esta vacia
              if( apellido.trim().equals("") || nombre.trim().equals("") || mail.trim().equals("") || 
                  cargo.equals("**Seleccione Opción**") || usuario.trim().equals("") || pass.trim().equals("") ||
                  confPass.trim().equals("") ) {
                    
                  JOptionPane.showMessageDialog(null, "Por favor verificar. Faltan de cargar Datos!!!","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);
                  return true;  
                }else return false;
            }catch(Exception e){
                   JOptionPane.showMessageDialog(null, e.getMessage(), "Error en el Método validarCajasTexto", JOptionPane.ERROR_MESSAGE);
                   return true;
            }
   }
   // creo metodo valida cajas de texto no esten vacias
    public boolean validarCajasTexto(String apellido, String nombre, String mail, String cargo, String usuario, String pass, String confPass){
        
            try{
                //valido Apellido - isEmpty ---> valida si la caja esta vacia
                if( !apellido.trim().equals("")){
                   //valido Nombre
                   if(!nombre.trim().equals("")){
                      //valido mail
                      if(!mail.trim().equals("")){
                         //validar que la mascara de mail ingresada sea correcta...-- llamo a un metodo
                         boolean validarEmail =  validarMail(mail);
                         // pregunto si es =false, quiere decir que es correcto el mail ingresado
                         if(validarEmail == false){
                         //valido Perfil o cargo de usuario
                         if(!cargo.equals("**Seleccione Opción**")){
                           //Valido Usuario
                           if(!usuario.trim().equals("")){
                              //Valido Pasword
                              if(!pass.trim().equals("")){
                                 //valido confirmacion de password
                                 if(!confPass.trim().equals("")){
                                    //valido que la confirmacion de pass, sea = al pass ingresado
                                    if(pass.equals(confPass)){//las contraseñas son iguales
                                       return false;
                                   }else{JOptionPane.showMessageDialog(null, "Las contraseñas ingresadas no coinciden!!","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE); return true;} 
                                 }else {JOptionPane.showMessageDialog(null, "Por favor, debe ingresar Confirmacion de Password","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);
                                        return true;}
                              }else {JOptionPane.showMessageDialog(null, "Por favor, debe ingresar un Password Válido","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);
                                      return true;}
                           }else{JOptionPane.showMessageDialog(null, "Por favor, debe ingresar un Usuario","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);return true;}
                         }else {JOptionPane.showMessageDialog(null, "Por favor, debe seleccionar un Cargo","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);return true;}
                         }else {return true;}
                      }else{JOptionPane.showMessageDialog(null, "Por favor, debe ingresar el e-mail","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);return true;}
                   }else {JOptionPane.showMessageDialog(null, "Por favor, debe ingresar el Nombre","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE); return true;}
                }else { JOptionPane.showMessageDialog(null, "Por favor, debe ingresar el Apellido","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE); 
                         return true;
                       }
            }catch(Exception e){
                   JOptionPane.showMessageDialog(null, e.getMessage(), "Error en el Método validarCajasTexto", JOptionPane.ERROR_MESSAGE);
                   return true;
            }
    }//fin validar cajas de texto
   
   //metodo que valida e-mail
    public boolean validarMail(String mail){

        try{
              String t = mail;
        
              Pattern p1 = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
              Matcher m1 = p1.matcher(t);
              
              if (m1.find() == true) {
                    return false;
                } else {JOptionPane.showMessageDialog(null, "El e-mail ingresado es incorrecto!","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);
                         return true;
                        }    
        }
        catch(Exception e){JOptionPane.showMessageDialog(null, e.getMessage());
                           return true;
                          }
    }
    
    //Metodo que INSERTA DATOS DE USUARIO
    public boolean insertarUsuario(String apellido, String nombre,String usuario, String pass, String cargo, String mail) {
       
        try {
             //Me aseguro que si pasa algo no se comitee nada hasta que se lo indique
            //Desactivo el commit automatico = false
            CN.getConectar().setAutoCommit(false);
 
            //llamo al SQL_INSERT
            ps = CN.getConectar().prepareStatement(SQL_INSERT);
            ps.setString(1, apellido);
            ps.setString(2, nombre);
            ps.setString(3, usuario);
            ps.setString(4, pass);
            ps.setString(5, cargo);
            ps.setString(6, mail);
            
            int res = ps.executeUpdate(); //---> devuelve un int, cantidad de filas que procesa
            if(res > 0){
               JOptionPane.showMessageDialog(null,"Registro Guardado con Exito!");
               return false;
            }
            
        CN.getConectar().commit();  
        
        } catch (SQLException ex) {
                //1062 - hace referencia al codigo de error de registros duplicados - DUPLICATE ENTRY
                 if (ex.getErrorCode() == 1062){
                    JOptionPane.showMessageDialog (null, "El usuario ingresado ya existe en la base de datos, cargar otro", "Usuario Existente",JOptionPane.ERROR_MESSAGE);
                    CN.getConectar().rollback();
                    return true;
                 }
                 else{
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al insertar datos", JOptionPane.ERROR_MESSAGE);
                    CN.getConectar().rollback();
                    return true;
                     }
         } finally { try {
                        //Esto siempre se va a ejecutar
                        //Libero recurso
                        ps.close();
                     } catch (SQLException ex) {
                                Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
                                return true;
                                }

                        //Cierro la conexion
                        CN.Cerrar();
                        return false;
                }      
    }//FIN metodo InsertarUsuario
    
    
    
    
    
}//fin clase Usuarios
