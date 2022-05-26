/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MySQL;
import static Vista.FrmSpaMascotas.Alto;
import static Vista.FrmSpaMascotas.Ancho;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ANDRES FELIPE VIVEROS ALBAN - S3AN
 */
public class Login {
    public static Connection Conexion;
    public String msm;
    public ResultSet rs;

    public void RecognizeDATABASE(String user, String pass, String db_name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //public boolean login(int ID, String Nombre)
        public boolean login(String ID, String Password) throws SQLException {
        
    try {
            String Query = "SELECT ID, Password, ROL FROM Usuarios WHERE ID = '" + ID + "' AND Password = '" + Password + "'";
            ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/Question.png"));
            ImageIcon mitad_2 = new ImageIcon(icono2.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(null,"ID: "+ ID +", Password: "+ Password,"CONFIRMACIÓN",JOptionPane.PLAIN_MESSAGE,mitad_2);
            Statement st = Conexion.createStatement();
             rs = st.executeQuery(Query);// ejecuta la sentencia sQL 
            rs.last();
            if (rs.getRow() > 0) {
                ImageIcon icono = new ImageIcon(getClass().getResource("/img/Check.png"));
                ImageIcon mitad_0 = new ImageIcon(icono.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
                JOptionPane.showMessageDialog(null, "Acceso Concedido!!!","VALIDACIÓN",JOptionPane.PLAIN_MESSAGE, mitad_0);
                msm="Encontrado";
                return true;
            }else{
             msm="No Encontrado";
             ImageIcon icono1 = new ImageIcon(getClass().getResource("/img/Error1.png"));
             ImageIcon mitad_1 = new ImageIcon(icono1.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
             JOptionPane.showMessageDialog(null, "Usuario no encontrado","ERROR",JOptionPane.PLAIN_MESSAGE,mitad_1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        return false;
       
    }
}
