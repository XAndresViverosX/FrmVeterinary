/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.MySQL;
import static Vista.FrmModificar.lblUsuarioLogin;
import static Vista.FrmSpaMascotas.Alto;
import static Vista.FrmSpaMascotas.Ancho;
import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANDRES FELIPE VIVEROS ALBAN - S3AN
 */
public class FrmSearch extends javax.swing.JInternalFrame {
private DefaultTableModel Modelo;
    int contador = 0;
    MySQL db = new MySQL();
    private static Connection Conexion;
    Color color,color1;
    /**
     * Creates new form FrmSearch
     */
    public FrmSearch() {
        setTitle("BUSCAR");
        initComponents();
        
        FrmLogin usuariologin = new FrmLogin();
        lblUsuarioLogin.setText(usuariologin.usuario);
        lblfrm.setText(this.getTitle());
        lblName.setText("Usuario: ");
        lblName.setForeground(Color.black);
        lblFormulario.setText("Formulario Actual: ");
        lblFormulario.setForeground(Color.black);
        
        String Datos[][] = {};
        String Columnas[] = {"ID", "Nombre", "Apellido", "CC", "Genero", "FecNac", "TipoMasc", "NomMasc", "TiempMac", "CantVac", "Peso"};
        Modelo = new DefaultTableModel(Datos, Columnas);
        
        ImageIcon foto_0 = new ImageIcon(getClass().getResource("/img/return.png"));
        ImageIcon mitad_0 = new ImageIcon(foto_0.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
        BtnSalir.setIcon(mitad_0);
        BtnSalir.setToolTipText("Click para volver al formulario de registro");
        BtnSalir.setText("Salir");
        
        ImageIcon foto_1 = new ImageIcon(getClass().getResource("/img/Actua.png"));
        ImageIcon mitad_1 = new ImageIcon(foto_1.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
        BtnActualizarTabla.setIcon(mitad_1);
        BtnActualizarTabla.setToolTipText("Click actualizar el contenido de la tabla");
        
        ImageIcon foto_29 = new ImageIcon(getClass().getResource("/img/Buscar.png"));
        ImageIcon mitad_27 = new ImageIcon(foto_29.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
        BtnBuscar.setIcon(mitad_27);
        BtnBuscar.setText("Buscar");
        BtnBuscar.setToolTipText("Click para buscar un registro por medio de los filtros");
        
        color = new Color(192, 192, 192);
        txtID.setText("Ingrese el dato del filtro que desea buscar");
        txtID.setForeground(color);
        lblTexto.setText("SISTEMA DE LA BASE DE DATOS");
        rdoID.setText("ID");
        rdoCC.setText("CC");
        rdoGenero.setText("Genero");
        rdoNombre.setText("Nombre");
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ "Spa_MascotasAV", "root","");
            Statement s = Conexion.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM RegistrosUsu");
            
            //Bucle para cada resultado en la consulta
            while (rs.next()){
                //Se crea un array que sera una de las filas de la tabla
                Object [] fila = new Object[11]; // Hay tres columnas en la tabla
                
                //se rellena cada posicion del array con una de las columnas de la tabla de la base de datos
                for (int i = 0; i < 11; i++) {
                    fila [i] = rs.getObject(i + 1); // el primer indice en rs es el 1, no el cero.
                }
            // se a?ade el modelo de la fila completa
            Modelo.addRow(fila);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TablaDatos.setModel(Modelo);
        //Reloj 
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("hh:mm:ss a");// se determina el fomato de la hora //Horas:Minutos:Segundos y tipo de hora PM o AM
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);// duerme durante 1000 milisegundos = 1 segundo y que de esta manera se este actualizando la hora
                        Reloj.setText(formateador.format(LocalDateTime.now())); //se pasa el texto [la hora] al jlabel = Reloj
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Bg = new javax.swing.ButtonGroup();
        PanelStatusBar3 = new javax.swing.JPanel();
        Linea3 = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblUsuarioLogin = new javax.swing.JLabel();
        lblFormulario = new javax.swing.JLabel();
        lblfrm = new javax.swing.JLabel();
        Reloj = new javax.swing.JLabel();
        BtnBuscar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        rdoID = new javax.swing.JRadioButton();
        rdoNombre = new javax.swing.JRadioButton();
        rdoCC = new javax.swing.JRadioButton();
        rdoGenero = new javax.swing.JRadioButton();
        txtID = new javax.swing.JTextField();
        lblTexto = new javax.swing.JLabel();
        BtnActualizarTabla = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaDatos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        PanelStatusBar3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Linea3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout Linea3Layout = new javax.swing.GroupLayout(Linea3);
        Linea3.setLayout(Linea3Layout);
        Linea3Layout.setHorizontalGroup(
            Linea3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Linea3Layout.setVerticalGroup(
            Linea3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblName.setText("jLabel1");

        lblUsuarioLogin.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblUsuarioLogin.setText("jLabel2");

        lblFormulario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblFormulario.setText("jLabel1");

        lblfrm.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblfrm.setText("jLabel1");

        Reloj.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Reloj.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Reloj.setText("jLabel1");

        javax.swing.GroupLayout PanelStatusBar3Layout = new javax.swing.GroupLayout(PanelStatusBar3);
        PanelStatusBar3.setLayout(PanelStatusBar3Layout);
        PanelStatusBar3Layout.setHorizontalGroup(
            PanelStatusBar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStatusBar3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuarioLogin)
                .addGap(72, 72, 72)
                .addComponent(Linea3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(lblFormulario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblfrm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Reloj, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelStatusBar3Layout.setVerticalGroup(
            PanelStatusBar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Linea3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStatusBar3Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(PanelStatusBar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStatusBar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblName)
                        .addComponent(lblUsuarioLogin))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStatusBar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFormulario)
                        .addComponent(lblfrm)
                        .addComponent(Reloj)))
                .addContainerGap())
        );

        BtnBuscar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros de busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        Bg.add(rdoID);

        Bg.add(rdoNombre);

        Bg.add(rdoCC);

        Bg.add(rdoGenero);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdoID)
                .addGap(30, 30, 30)
                .addComponent(rdoNombre)
                .addGap(28, 28, 28)
                .addComponent(rdoCC)
                .addGap(42, 42, 42)
                .addComponent(rdoGenero)
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoID)
                    .addComponent(rdoNombre)
                    .addComponent(rdoCC)
                    .addComponent(rdoGenero))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        txtID.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtID.setText("jTextField1");
        txtID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIDFocusLost(evt);
            }
        });
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        lblTexto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblTexto.setText("jLabel1");

        BtnActualizarTabla.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BtnActualizarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarTablaActionPerformed(evt);
            }
        });

        BtnSalir.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });

        TablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TablaDatos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelStatusBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnActualizarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblTexto)
                            .addGap(18, 18, 18)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnActualizarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PanelStatusBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        DefaultTableModel ModeloB = new DefaultTableModel();
        String DatosB[][] = {};
        String ColumnasB[] = {"ID", "Nombre", "Apellido", "CC", "Genero", "FecNac", "TipoMasc", "NomMasc", "TiempMac", "CantVac", "Peso"};
        ModeloB = new DefaultTableModel(DatosB, ColumnasB);

        if (txtID.getText().equals("Ingrese el dato del filtro que desea buscar")) {
            ImageIcon foto_1 = new ImageIcon(getClass().getResource("/img/Error1.png"));
            ImageIcon mitad_1 = new ImageIcon(foto_1.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(null, "Campo vacio","ERROR", JOptionPane.PLAIN_MESSAGE,mitad_1);
        }else if(rdoID.isSelected()==false && rdoNombre.isSelected()==false && rdoGenero.isSelected()==false && rdoCC.isSelected()==false){
            ImageIcon foto_2 = new ImageIcon(getClass().getResource("/img/Error1.png"));
            ImageIcon mitad_12 = new ImageIcon(foto_2.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun filtro de busqueda","ERROR", JOptionPane.PLAIN_MESSAGE,mitad_12);
        }else{

            if (rdoID.isSelected()) {
                String ID = txtID.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ "Spa_MascotasAV", "root","");
                    Statement s = Conexion.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM RegistrosUsu WHERE ID LIKE '%" + ID + "%'");
                    while (rs.next()){
                        Object [] fila = new Object[11];
                        for (int i = 0; i < 11; i++) {
                            fila [i] = rs.getObject(i + 1);} // el primer indice en rs es el 1, no el cero.
                        // se a?ade el modelo de la fila completa
                        ModeloB.addRow(fila);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
                }
                TablaDatos.setVisible(false);
                TablaDatos.setModel(ModeloB);
                ModeloB.fireTableDataChanged();
                TablaDatos.setVisible(true);
            }
            if (rdoNombre.isSelected()) {
                String Nombre = txtID.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ "Spa_MascotasAV", "root","");
                    Statement s = Conexion.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM RegistrosUsu WHERE Nombre LIKE '%" + Nombre + "%'");
                    while (rs.next()){
                        Object [] fila = new Object[11];
                        for (int i = 0; i < 11; i++) {
                            fila [i] = rs.getObject(i + 1);} // el primer indice en rs es el 1, no el cero.
                        // se a?ade el modelo de la fila completa
                        ModeloB.addRow(fila);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
                }
                TablaDatos.setVisible(false);
                TablaDatos.setModel(ModeloB);
                ModeloB.fireTableDataChanged();
                TablaDatos.setVisible(true);
            }
            if (rdoCC.isSelected()) {
                String CC = txtID.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ "Spa_MascotasAV", "root","");
                    Statement s = Conexion.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM RegistrosUsu WHERE CC LIKE '%" + CC + "%'");
                    while (rs.next()){
                        Object [] fila = new Object[11];
                        for (int i = 0; i < 11; i++) {
                            fila [i] = rs.getObject(i + 1);} // el primer indice en rs es el 1, no el cero.
                        // se a?ade el modelo de la fila completa
                        ModeloB.addRow(fila);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
                }
                TablaDatos.setVisible(false);
                TablaDatos.setModel(ModeloB);
                ModeloB.fireTableDataChanged();
                TablaDatos.setVisible(true);
            }
            if (rdoGenero.isSelected()) {
                String Genero = txtID.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ "Spa_MascotasAV", "root","");
                    Statement s = Conexion.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM RegistrosUsu WHERE Genero LIKE '%" + Genero + "%'");
                    while (rs.next()){
                        Object [] fila = new Object[11];
                        for (int i = 0; i < 11; i++) {
                            fila [i] = rs.getObject(i + 1);} // el primer indice en rs es el 1, no el cero.
                        // se a?ade el modelo de la fila completa
                        ModeloB.addRow(fila);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
                }
                TablaDatos.setVisible(false);
                TablaDatos.setModel(ModeloB);
                ModeloB.fireTableDataChanged();
                TablaDatos.setVisible(true);
            }
        }
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void txtIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIDFocusGained
        if (txtID.getText().equals("Ingrese el dato del filtro que desea buscar")) {
            txtID.setText("");
            txtID.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtIDFocusGained

    private void txtIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIDFocusLost
        if (txtID.getText().equals("")) {
            txtID.setText("Ingrese el dato del filtro que desea buscar");
            txtID.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtIDFocusLost

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void BtnActualizarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarTablaActionPerformed
        //        this.dispose();
        //        FrmCRUD t = new FrmCRUD();
        //        t.setVisible(true);
        DefaultTableModel ModeloB = new DefaultTableModel();
        String DatosB[][] = {};
        String ColumnasB[] = {"ID", "Nombre", "Apellido", "CC", "Genero", "FecNac", "TipoMasc", "NomMasc", "TiempMac", "CantVac", "Peso"};
        ModeloB = new DefaultTableModel(DatosB, ColumnasB);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "Spa_MascotasAV", "root", "");
            Statement s = Conexion.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM RegistrosUsu");
            while (rs.next()) {
                Object[] fila = new Object[11];
                for (int i = 0; i < 11; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                ModeloB.addRow(fila);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        TablaDatos.setVisible(false);
        TablaDatos.setModel(ModeloB);
        ModeloB.fireTableDataChanged();
        TablaDatos.setVisible(true);
        txtID.setText("");
        Bg.clearSelection();
    }//GEN-LAST:event_BtnActualizarTablaActionPerformed

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_BtnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(FrmSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmSearch().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Bg;
    private javax.swing.JButton BtnActualizarTabla;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JPanel Linea3;
    private javax.swing.JPanel PanelStatusBar3;
    private javax.swing.JLabel Reloj;
    private javax.swing.JTable TablaDatos;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFormulario;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblTexto;
    public static javax.swing.JLabel lblUsuarioLogin;
    private javax.swing.JLabel lblfrm;
    private javax.swing.JRadioButton rdoCC;
    private javax.swing.JRadioButton rdoGenero;
    private javax.swing.JRadioButton rdoID;
    private javax.swing.JRadioButton rdoNombre;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}
