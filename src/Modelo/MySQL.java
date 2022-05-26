/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static Vista.FrmSpaMascotas.Alto;
import static Vista.FrmSpaMascotas.Ancho;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 *
 * @author ANDRES FELIPE VIVEROS ALBAN - S3AN
 */
public class MySQL {
    ArrayList sql_1 = new ArrayList();
    ArrayList sql_2 = new ArrayList();
    public int existe = 0;
    public int existe1 = 0;
    private static Connection Conexion;
    
    public void MySQLConnection(String user, String pass, String db_name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name, user, pass);
            JOptionPane.showMessageDialog(null, "Se ha establecido correctamente la conexion");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion \nVerifica que el servidor de la base de datos esta encendido");
        }
    }
    
    public void RecognizeDATABASE(String user, String pass, String db_name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public  void Verificar(){ // genera la consulata atravez de un vector que lee los campos de la tabla databases
        try {
            int i = 0;
            String db_name = null;
            String dbcname = null;
            String Sentencia = "SHOW DATABASES";//Comando SQL para consultar bases de datos
            Statement st = Conexion.createStatement();
            ResultSet rs = st.executeQuery(Sentencia); //Ejecuta la sentencia SQL
            while (rs.next()){
            db_name = rs.getString(1);
            sql_1.add(db_name);
            //JOptionPane.showMessageDialog(null, "Las Bases de datos encontradas son: " +db_name);
                if (db_name.equalsIgnoreCase("Spa_MascotasAV")) {
                    existe=1;
                    dbcname=rs.getString(1);
                    JOptionPane.showMessageDialog(null, existe);
                }
            }
            if (existe==1) {
                //JOptionPane.showMessageDialog(null, "La BD " + dbcname + " Existe", "AVISO", JOptionPane.WARNING_MESSAGE);
            }else if (existe != 1){
                JOptionPane.showMessageDialog(null, "La BD Solicitada No Existe " + 1, "CONFIRMACION", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, "La BD son: " +sql_1);
            i++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Se encontro un error en  \n" + ex ,"AVISO", JOptionPane.ERROR_MESSAGE);
        }
    
    
    }
    
    public  void VerificarUsuarios(){ // genera la consulata atravez de un vector que lee los campos de la tabla databases
        try {
            int i = 0;
            String tb_name = null;
            String tbcname = null;
            String Sentencia = "SHOW TABLES";//Comando SQL para consultar las tablas
            Statement st = Conexion.createStatement();
            ResultSet rs = st.executeQuery(Sentencia); //Ejecuta la sentencia SQL
            while (rs.next()){
            tb_name = rs.getString(1);
            sql_2.add(tb_name);
                if (tb_name.equalsIgnoreCase("Usuarios")) {
                    existe1=1;
                    tbcname=rs.getString(1);
                }
            }
            if (existe1==1) {
            }else if (existe1 != 1){}
        } catch (SQLException ex) {
        }
    }
    
    public void UsarDatabase(){
        try {
            String Query = "USE DATABASE Spa_MascotasAV";
            Statement str = Conexion.createStatement();
            str.execute(Query);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection() {
        try {
            Conexion.close();
            ImageIcon icono = new ImageIcon(getClass().getResource("/img/Close.png"));
            ImageIcon mitad_0 = new ImageIcon(icono.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(null, "Se ha finalizado correctamente la conexion","AVISO",JOptionPane.PLAIN_MESSAGE,mitad_0);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createDB(String name) {
        try {
            String Query = "CREATE DATABASE " + name;
            Statement st = Conexion.createStatement();
            st.execute(Query);
            MySQLConnection("root", "", name);
            JOptionPane.showMessageDialog(null, "Se ha creado la base de datos: " + name + " de forma exitosa");
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createTable(String name) {
        try {
            String Query = "CREATE TABLE " + name + "" + "(ID VARCHAR(25) PRIMARY KEY,Nombre VARCHAR(50),Apellido VARCHAR(50),"
                    + "CC VARCHAR(100),Genero VARCHAR(50),FechaNac DATE,TipoMasc VARCHAR(25), NomMasc VARCHAR(50), TiempMac VARCHAR(100),"
                    +"CantVac VARCHAR(100),Peso VARCHAR(100))";
            JOptionPane.showMessageDialog(null, "Se ha creado la table: " + name + " de forma exitosa");
            Statement st = Conexion.createStatement();
            st.execute(Query);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createTable1(String name) {
        try {
            String Query = "CREATE TABLE " + name + "" + "(CODIGO VARCHAR(25) NOT NULL PRIMARY KEY,NombreC VARCHAR(100),CC VARCHAR(100),"
                    + "Nacionalidad VARCHAR(100),Nivel_Educativo VARCHAR(50),FechaNac DATE,Telefono VARCHAR(25), Correo VARCHAR(50), Genero VARCHAR(100),"
                    +"EstadoCivil VARCHAR(100),Direccion VARCHAR(100),FechaIngreso DATE,NumerodeCuenta VARCHAR(100),Salario INT, TipoContrato VARCHAR(50), Estado VARCHAR(50),"
                    + "FormaPago VARCHAR(50), Turno VARCHAR(50), NOTA VARCHAR(100))";
            JOptionPane.showMessageDialog(null, "Se ha creado la table: " + name + " de forma exitosa");
            Statement st = Conexion.createStatement();
            st.execute(Query);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createTableRegistro(String name) {
        try {
            String Query = "CREATE TABLE " + name + "" + "(ID INT NOT NULL PRIMARY KEY,Nombre VARCHAR(100),Apellido VARCHAR(100),"
                    +" Edad VARCHAR(4),Sexo VARCHAR(20),Password VARCHAR(50) NOT NULL,ROL VARCHAR(100) NOT NULL)";
            JOptionPane.showMessageDialog(null, "Se ha creado la table: " + name + " de forma exitosa");
            Statement st = Conexion.createStatement();
            st.execute(Query);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void InsertData1(String table_name, String ID, String name, String cc, String nacionalidad, String nivel, String fechanac, String telefono, String correo, String genero, String estadocivil, String direccion
            ,String fechain, String numerocuenta, String Salario, String tipocontrato, String estado, String formapago, String turno, String nota) {
        try {
            String Query = "INSERT INTO " + table_name + " VALUES ("
                    + "\"" + ID + "\","
                    + "\"" + name + "\","
                    + "\"" + cc + "\","
                    + "\"" + nacionalidad + "\","
                    + "\"" + nivel + "\","
                    + "\"" + fechanac + "\","
                    + "\"" + telefono + "\","
                    + "\"" + correo + "\","
                    + "\"" + genero + "\","
                    + "\"" + estadocivil + "\","
                    + "\"" + direccion + "\","
                    + "\"" + fechain + "\","
                    + "\"" + numerocuenta + "\","
                    + "\"" + Salario + "\","
                    + "\"" + tipocontrato + "\","
                    + "\"" + estado + "\","
                    + "\"" + formapago + "\","
                    + "\"" + turno + "\","
                    + "\"" + nota + "\")";
            Statement st = Conexion.createStatement();
            st.execute(Query);
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }
    
    public void UpdateData1(String table_name, String ID, String name, String cc, String nacionalidad, String nivel, String fechanac, String telefono, String correo, String genero, String estadocivil, String direccion
            ,String fechain, String numerocuenta, String Salario, String tipocontrato, String estado, String formapago, String turno, String nota) {
        try {
            String Query = "UPDATE " + table_name + " SET NombreC = '"+name+"', CC = '"+cc+"',"
                    + " Nacionalidad = '"+nacionalidad+"', Nivel_Educativo = '"+nivel+"', FechaNac = '"+fechanac+"', Telefono = '"+telefono+"',"
                    + " Correo = '"+correo+"', Genero = '"+genero+"', EstadoCivil = '"+estadocivil+"', Direccion = '"+direccion+"', FechaIngreso = '"+fechain+"', NumerodeCuenta = '"+numerocuenta+"',"
                    + " Salario = '"+Salario+"', TipoContrato = '"+tipocontrato+"', Estado = '"+estado+"', FormaPago = '"+formapago+"', Turno = '"+turno+"', NOTA = '"+nota+"' WHERE CODIGO = '"+ID+"'";
            Statement st = Conexion.createStatement();
            st.execute(Query);
            JOptionPane.showMessageDialog(null, "Datos modificados de forma exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en la modificacion de datos");
        }
    }
    
    public void InsertData(String table_name, String ID, String name, String lastname, String cc, String genero, String fechanac, String tipomasc, String nommasc, String tiempmac, String cantvac, String peso) {
        try {
            String Query = "INSERT INTO " + table_name + " VALUES ("
                    + "\"" + ID + "\","
                    + "\"" + name + "\","
                    + "\"" + lastname + "\","
                    + "\"" + cc + "\","
                    + "\"" + genero + "\","
                    + "\"" + fechanac + "\","
                    + "\"" + tipomasc + "\","
                    + "\"" + nommasc + "\","
                    + "\"" + tiempmac + "\","
                    + "\"" + cantvac + "\","
                    + "\"" + peso + "\")";
            Statement st = Conexion.createStatement();
            st.execute(Query);
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }
    
    public void UpdateData(String table_name, String ID, String name, String lastname, String cc, String genero, String fechanac, String tipomasc, String nommasc, String tiempmac, String cantvac, String peso) {
        try {
            String Query = "UPDATE " + table_name + " SET Nombre = '"+name+"', Apellido = '"+lastname+"',"
                    +" CC = '"+cc+"', Genero = '"+genero+"', FechaNac = '"+fechanac+"', TipoMasc = '"+tipomasc+"',"
                    +" NomMasc = '"+nommasc+"', TiempMac = '"+tiempmac+"', CantVac = '"+cantvac+"', Peso = '"+peso+"' WHERE ID = '"+ID+"'";
            Statement st = Conexion.createStatement();
            st.execute(Query);
            JOptionPane.showMessageDialog(null, "Datos modificados de forma exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en la modificacion de datos");
        }
    }
    
    public void getValues(String table_name) {
        try {
            String Query = "SELECT * FROM " + table_name;
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);

            while (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "ID: " + resultSet.getString("ID") + " "
                        + "Nombre: " + resultSet.getString("Nombre") + " "
                        + "Apellido: " + resultSet.getString("Apellido") + " "
                        + "Edad: " + resultSet.getString("Edad") + " "
                        + "Sexo: " + resultSet.getString("Sexo") + " ");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos" + ex);
        }
    }
    
    public void Buscar(String table_name, String ID) {

    }
    
    public void deleteRecord(String table_name, String ID) {
        try {
            String Query = "DELETE FROM " + table_name + " WHERE ID = \"" + ID + "\"";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "El registro con el ID: "+ID+" Ha sido eliminado correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
        }
    }
    
        public void deleteRecord1(String table_name, String Codigo) {
        try {
            String Query = "DELETE FROM " + table_name + " WHERE CODIGO = \"" + Codigo + "\"";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "El registro con el CODIGO: "+Codigo+" Ha sido eliminado correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
        }
    }
    
    
    public void ObtenerID(String table_name, String ID) {
        try {
            String Query = "SELECT * FROM " + table_name + " WHERE ID = \"" + ID + "\"";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Se mostrara el registro con el ID: "+ID+" correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
        }
    }
}
