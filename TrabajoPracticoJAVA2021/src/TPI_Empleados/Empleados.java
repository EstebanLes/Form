package TPI_Empleados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Empleados{
    
    //Atributos
    private String apellido;
    private String nombre;
    private int legajo;
    private int dni;
    private String fechaNac;
    private String genero;
    private String cargo;
    private double montoBruto;
    
     //Variables
    private DatabaseConection CN; 
    private PreparedStatement ps;
    private final String SQL_INSERT = "INSERT INTO empleados (Apellido, Nombre, dni, FechaNacimiento, Genero, Cargo, SueldoBruto ) VALUES (?, ?, ?, ?, ?, ?, ?)";

     //Primer CONSTRUCTOR
     public Empleados(){
         CN = new DatabaseConection();
         ps = null;
     }


    //Segundo CONSTRUCTOR
    public Empleados(String apellido, String nombre, int legajo, int dni, String fechaNac, String genero, String cargo, double montoBruto) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.legajo = legajo;
        this.dni = dni;
        this.fechaNac = fechaNac;
        this.genero = genero;
        this.cargo = cargo;
        this.montoBruto = montoBruto;
    }
    
    //Tercer Constructor
     public Empleados(int legajo) {
        this.legajo = legajo;
    }

    //Metodos getters and setters... 
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

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getMontoBruto() {
        return montoBruto;
    }

    public void setMontoBruto(double montoBruto) {
        this.montoBruto = montoBruto;
    }

    public DatabaseConection getCN() {
        return CN;
    }

    public void setCN(DatabaseConection CN) {
        this.CN = CN;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

 //Metodo que INSERTA EMPLEADOS
  public boolean insertarEmpleados(String apellido, String nombre, int dni, String fechaNac, String genero, String cargo, double sueldoBruto) {  

    try {   //Me aseguro que si pasa algo no se comitee nada hasta que se lo indique
            //Desactivo el commit automatico = false
            CN.getConectar().setAutoCommit(false);
 
            //llamo al SQL_INSERT
            ps = CN.getConectar().prepareStatement(SQL_INSERT);
            ps.setString(1, apellido);
            ps.setString(2, nombre);
            ps.setInt(3, dni);
            ps.setString(4, fechaNac);
            ps.setString(5, genero);
            ps.setString(6, cargo);
            ps.setDouble(7, sueldoBruto);
            
            int res = ps.executeUpdate(); // executeUpdate()---> Retorna un entero (INT), el nro. de filas afectadas en la consulta
                                          //execute() -->Retorna (BOOLEAN)FALSE: cuando la consulta se ejecuta pero no devuelve valores
            
            // si a obtenido algun registro                                   
            if(res > 0){
               //Grabo los cambios
               JOptionPane.showMessageDialog(null,"Registro Guardado con Exito!");
            //   CN.getConectar().commit();
               return true;
            }
        CN.getConectar().commit();
        
        } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al insertar datos en la Base de Datos", JOptionPane.ERROR_MESSAGE);
               CN.getConectar().rollback();
               return false;
        }finally{try {
                        //Esto siempre se va a ejecutar
                        //Libero recurso
                        ps.close();
                     } catch (SQLException ex) {
                        Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
                        return false;}

                        //Cierro la conexion
                        CN.Cerrar();
                        return true;
                }
    }//FIN metodo Insertar Empleados
 
  //metodo que valida la Carga de Datos
  public boolean ValidarDatos(String apellido, String nombre, int dni, String fechaNac, String genero, String cargo, double sueldoBruto){

        try{    //valido Apellido
                if( !apellido.trim().equals("")){
                   //valido Nombre
                   if(!nombre.trim().equals("")){
                      //llamo al método que valida el DNI
                      boolean validar = ValidarDNI(dni);
                      if(validar == false){
                      //valido Fecha Nacimiento
                    //  if(!fechaNac.trim().equals("")){
//                          boolean validarFecha = validarFecha(fechaNac);
//                          if(validarFecha == true){
                          //valido Gènero
                          if(!genero.trim().equals("")){
                             //valido Cargo del Empleado
                            if(!cargo.equals("**Seleccione Opción**") ){
                               //Valido Sueldo Bruto
                               String sueldoBrutoPars= Double.toString(sueldoBruto);
                               if(!sueldoBrutoPars.trim().equals("")){
                                 return false;
                                 }else{JOptionPane.showMessageDialog(null, "No se ingresó el Sueldo Bruto del Empleado","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);return true;} 
                               }else {JOptionPane.showMessageDialog(null, "No se seleccionó Cargo del Empleado","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);return true;}
                             }else{JOptionPane.showMessageDialog(null, "No se seleccionó Género del Empleado","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);return true;}
                         // } else {JOptionPane.showMessageDialog(null, "La Fecha ingresada es Incorrecta","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);return true;}
                        //  }else {JOptionPane.showMessageDialog(null, "No se ingresó el Fecha de Nacimiento del Empleado","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);return true;}
                      } else return true;
                      }else {JOptionPane.showMessageDialog(null, "No se ingresó el Nombre del Empleado","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);return true;}
                  }else{JOptionPane.showMessageDialog(null, "No se ingresó el Apellido del Empleado","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);return true;} 
                
                }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
                return true;
               }
  }

//metodo que valida si el DNI ya no existe en la Base de Datos
public boolean ValidarDNI ( Integer dni){
    
    //Valido que se haya cargado un DNI
    if(dni != null){
        //Parseo el valor de DNI a un String
        String dniParseo = Integer.toString(dni);
        //   if(!dniParseo.trim().equals("") || !dniParseo ==null){
        //valido que contenga 8 dígitos
        if( dniParseo.length() == 8){
            
        }else {JOptionPane.showMessageDialog(null, "El DNI debe contener un mínimo de 8 dígitos","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);
        //System.out.println("cantidad de caracteres DNI: "+dni.intValue());
        return true;
        }
    }else {JOptionPane.showMessageDialog(null, "No se ingresó el DNI del Empleado","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);
            return true;
            }
    return false;  
}

//metodo que valida si el DNI ya esta registrado en la base de datos
public boolean validarDNIExist( Integer dni, String sql, Integer legajo){
      
        try{
            // Hago la conexion con la BD
            Statement s = CN.getConectar().createStatement();
            ResultSet rs = s.executeQuery(sql);

             while (rs.next()) {
                    Integer dniKCPro = rs.getInt("dni");

                    //pregunto si DNI estan en la base de datos
                    if (dni.equals(dniKCPro))
                    {
                        JOptionPane.showMessageDialog(null, "El DNI ingresado ya existe en la Base de Datos!!!","Validación Carga de Datos",JOptionPane.ERROR_MESSAGE);
                        return true;
                    } else return false;

             }
             rs.close();
             CN.Cerrar();
             return false;
       } catch (SQLException ex) {
               Logger.getLogger(DatabaseConection.class.getName()).log(Level.SEVERE, null, ex);
               return true;
              } 
}

//metodo modificar datos
public boolean modificarEmpleados(int legajo, String apellido, String nombre, int dni, String fechaNac, String genero, String cargo, double sueldoBruto){
    
    try{//Me aseguro que si pasa algo no se comitee nada hasta que se lo indique
        //Desactivo el commit automatico = false
        CN.getConectar().setAutoCommit(false);

        ps = CN.getConectar().prepareStatement("UPDATE EMPLEADOS set apellido='" + apellido +  "',nombre='" + nombre + "',dni='" + dni + "',fechaNacimiento='" + fechaNac + "',genero='" + genero + "',cargo='" + cargo + "',sueldobruto='" + sueldoBruto + "'WHERE legajo='" + legajo + "'");
        int res = ps.executeUpdate(); // executeUpdate()---> Retorna un entero (INT), el nro. de filas afectadas en la consulta
                                      //execute() -->Retorna (BOOLEAN)FALSE: cuando la consulta se ejecuta pero no devuelve valores
            
        // si a obtenido algun registro                                   
        if(res > 0){
            //Grabo los cambios
            JOptionPane.showMessageDialog(null,"Registro Modificado con Exito!");
            return true;
            }
        CN.getConectar().commit();
        
        } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al Modificar datos en la Base de Datos", JOptionPane.ERROR_MESSAGE);
               CN.getConectar().rollback();
               return false;
        }finally{try {
                        //Esto siempre se va a ejecutar
                        //Libero recurso
                        ps.close();
                     } catch (SQLException ex) {
                        Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
                        return false;}

                        //Cierro la conexion
                        CN.Cerrar();
                        return true;
                }
}

//metodo Eliminar empleados
public boolean eliminarEmpleado(int legajo){
    
    try{//Me aseguro que si pasa algo no se comitee nada hasta que se lo indique
        //Desactivo el commit automatico = false
        CN.getConectar().setAutoCommit(false);

        ps = CN.getConectar().prepareStatement("DELETE FROM EMPLEADOS WHERE legajo = '" + legajo + "'");

        int res = ps.executeUpdate(); 
        
        // si a obtenido algun registro                                   
        if(res > 0){
            //Grabo los cambios
            JOptionPane.showMessageDialog(null,"Registro Eliminado con Exito!");
            return true;
            }
        CN.getConectar().commit();
        
        } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al Eliminar datos en la Base de Datos", JOptionPane.ERROR_MESSAGE);
               CN.getConectar().rollback();
               return false;
        }finally{try {
                        //Esto siempre se va a ejecutar
                        //Libero recurso
                        ps.close();
                     } catch (SQLException ex) {
                        Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
                        return false;}

                        //Cierro la conexion
                        CN.Cerrar();
                        return true;
                }
}


}//FIN CLASE EMPLEADOS