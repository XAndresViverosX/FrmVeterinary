/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.MySQL;
import static Vista.FrmSpaMascotas.Alto;
import static Vista.FrmSpaMascotas.Ancho;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANDRES FELIPE VIVEROS ALBAN - S3AN
 */
public class FrmModificar extends javax.swing.JInternalFrame {
    private DefaultTableModel Modelo;
    int contador = 0;
    MySQL db = new MySQL();
    private static Connection Conexion;
    Color color,color1;

    /**
     * Creates new form FrmCRUD
     */
    public FrmModificar() {
        setTitle("MODIFICAR");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        setSize(width/2, height/2);		
//[1590, 630]
        //setLocationRelativeTo(null);
        //asignar icono para el JFRAME
        Image _Icono=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/MySQL.png"));
        //setIconImage(_Icono);
        this.setResizable(false);
        
        initComponents();
        FrmLogin usuariologin = new FrmLogin();
        lblUsuarioLogin.setText(usuariologin.usuario);
        PanelDatosP.setOpaque(false);
        PanelDatosM.setOpaque(false);
        PanelTipoM.setOpaque(false);
        PanelInfoA.setOpaque(false);
        //JPANELS//}++++++++++++++++++++++++++++++++++++++++++
        rdoFelino.setToolTipText("Haga Click");
        rdoFelino.setMnemonic('F');//shortcutkey
        rdoFelino.setText("Felino");
        rdoCanino.setToolTipText("Haga Click");
        rdoCanino.setMnemonic('C');//shortcutkey
        rdoCanino.setText("Canino");
        RdoRoedor.setToolTipText("Haga Click");
        RdoRoedor.setMnemonic('R');//shortcutkey
        RdoRoedor.setText("Roedor");
        
        //Etiquetas
        lblID.setText("ID:");
        lblID.setForeground(Color.black);
        lblGenero.setText("Genero:");
        lblGenero.setForeground(Color.black);
        lblCC.setText("(CC o TI)");
        lblCC.setForeground(Color.black);
        lblNombre.setText("Nombres:");
        lblNombre.setForeground(Color.black);
        lblApellido.setText("Apellidos:");
        lblApellido.setForeground(Color.black);
        lblAño.setText("Fecha De Nacimiento");
        lblAño.setForeground(Color.black);
        lblEdadMas.setText("Tiempo que lleva con la mascota");
        lblEdadMas.setForeground(Color.black);
        lblNomMas.setText("¿Nombre de la mascota?");
        lblEdadMas.setForeground(Color.black);
        lblPeso.setText("¿Peso aproximado?");
        lblPeso.setForeground(Color.black);
        lblVacunas.setText("Cantidad de vacunas aplicadas");
        lblVacunas.setForeground(Color.black);
        lblRuta.setText("Ruta de la imagen: ");
        lblName.setText("Usuario: ");
        lblName.setForeground(Color.black);
        lblFormulario.setText("Formulario Actual: ");
        lblFormulario.setForeground(Color.black);
        lblfrm.setText(this.getTitle());
        
        //JCombo ==> Cuadros Combinados De Lista ==> Listas Dinamicas
        cboGenero.setToolTipText("Click para desplegar las opciones");
        cboGenero.addItem("Seleccione");
        cboGenero.addItem("Masculino");
        cboGenero.addItem("Femenino");
        cboGenero.addItem("Otros");
        cboGenero.setSelectedIndex(0);
        
        cboVacunas.setToolTipText("Click para desplegar las opciones");
        cboVacunas.addItem("Seleccione");
        cboVacunas.addItem("Ninguna");
        cboVacunas.setSelectedIndex(0);
        for (int i = 1; i < 6; i++) {
            cboVacunas.addItem(String.valueOf(i));
        }
        
        
        cboEdadMas.setToolTipText("Click para desplegar las opciones");
        cboEdadMas.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione",
        "Recien nacido",
        "Entre 3 a 6 meses",
        "Entre 7 a 11 meses",
        "1 Año",
        "Entre 1 a 2 años",
        "Mas de 3 años",
        "Mas de 10 años"}));
        
        ImageIcon _img=new ImageIcon(getClass().getResource("/img/Camera.png"));
        btnFoto.setIcon(_img);
        btnFoto.setToolTipText("Haga Click (para subir la foto)");
        
        color1 = new Color(176, 176, 176);
        txtRuta.setForeground(color1);
        
        //JPANELS//+++++++++++++++++++++++
        color = new Color(192, 192, 192);
        lblTexto.setText("SISTEMA DE LA BASE DE DATOS");
        
        String Datos[][] = {};
        String Columnas[] = {"ID", "Nombre", "Apellido", "CC", "Genero", "FecNac", "TipoMasc", "NomMasc", "TiempMac", "CantVac", "Peso"};
        Modelo = new DefaultTableModel(Datos, Columnas);
        
        ImageIcon foto_0 = new ImageIcon(getClass().getResource("/img/return.png"));
        ImageIcon mitad_0 = new ImageIcon(foto_0.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
        BtnSalir.setIcon(mitad_0);
        BtnSalir.setToolTipText("Click para volver al formulario de registro");
        BtnSalir.setText("Salir");
        
        ImageIcon foto_3 = new ImageIcon(getClass().getResource("/img/Guardar.png"));
        BtnModificar.setIcon(foto_3);
        BtnModificar.setToolTipText("Click modificar el registro");
        BtnModificar.setText("Modificar");
        
        InHabilitar();
        
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
            // se añade el modelo de la fila completa
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

        bgrGrup = new javax.swing.ButtonGroup();
        bgrGrupo = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaDatos = new javax.swing.JTable();
        PanelModificar = new javax.swing.JPanel();
        PanelDatosP = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        lblCC = new javax.swing.JLabel();
        txtCC = new javax.swing.JTextField();
        lblGenero = new javax.swing.JLabel();
        cboGenero = new javax.swing.JComboBox<>();
        lblAño = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        txtID1 = new javax.swing.JTextField();
        Fecha = new com.toedter.calendar.JDateChooser();
        PanelDatosM = new javax.swing.JPanel();
        btnFoto = new javax.swing.JButton();
        pnlImagen = new javax.swing.JLabel();
        PanelTipoM = new javax.swing.JPanel();
        rdoCanino = new javax.swing.JRadioButton();
        RdoRoedor = new javax.swing.JRadioButton();
        rdoFelino = new javax.swing.JRadioButton();
        PanelInfoA = new javax.swing.JPanel();
        cboVacunas = new javax.swing.JComboBox<>();
        lblVacunas = new javax.swing.JLabel();
        cboEdadMas = new javax.swing.JComboBox<>();
        lblEdadMas = new javax.swing.JLabel();
        txtNombMasc = new javax.swing.JTextField();
        lblNomMas = new javax.swing.JLabel();
        lblPeso = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        txtRuta = new javax.swing.JTextField();
        lblRuta = new javax.swing.JLabel();
        BtnModificar = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();
        lblTexto = new javax.swing.JLabel();
        PanelStatusBar3 = new javax.swing.JPanel();
        Linea3 = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblUsuarioLogin = new javax.swing.JLabel();
        lblFormulario = new javax.swing.JLabel();
        lblfrm = new javax.swing.JLabel();
        Reloj = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        TablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TablaDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaDatos);

        PanelModificar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modificación de datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 12))); // NOI18N

        PanelDatosP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Personales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(255, 0, 0))); // NOI18N

        lblNombre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblNombre.setText("jLabel1");

        lblApellido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblApellido.setText("jLabel2");

        txtNombres.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNombres.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtApellidos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtApellidos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCC.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblCC.setText("jLabel1");

        txtCC.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCCKeyTyped(evt);
            }
        });

        lblGenero.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblGenero.setText("jLabel1");

        cboGenero.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        lblAño.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblAño.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAño.setText("jLabel1");
        lblAño.setToolTipText("");

        lblID.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblID.setText("jLabel1");

        txtID1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtID1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Fecha.setDateFormatString("MMM-dd-yyyy");

        javax.swing.GroupLayout PanelDatosPLayout = new javax.swing.GroupLayout(PanelDatosP);
        PanelDatosP.setLayout(PanelDatosPLayout);
        PanelDatosPLayout.setHorizontalGroup(
            PanelDatosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosPLayout.createSequentialGroup()
                .addGroup(PanelDatosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelDatosPLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(PanelDatosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAño, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelDatosPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelDatosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDatosPLayout.createSequentialGroup()
                                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelDatosPLayout.createSequentialGroup()
                                .addComponent(lblApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelDatosPLayout.createSequentialGroup()
                                .addComponent(lblCC, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtCC, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelDatosPLayout.createSequentialGroup()
                                .addComponent(lblGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(cboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PanelDatosPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtID1)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        PanelDatosPLayout.setVerticalGroup(
            PanelDatosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDatosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelDatosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PanelDatosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelDatosPLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(PanelDatosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCC)
                    .addComponent(txtCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(PanelDatosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGenero)
                    .addComponent(cboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAño, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        PanelDatosM.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la mascota", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(51, 0, 255))); // NOI18N

        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
            }
        });

        pnlImagen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Foto de la mascota", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        pnlImagen.setRequestFocusEnabled(false);

        PanelTipoM.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de mascota", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        PanelTipoM.setLayout(null);

        bgrGrup.add(rdoCanino);
        rdoCanino.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        PanelTipoM.add(rdoCanino);
        rdoCanino.setBounds(10, 70, 130, 28);

        bgrGrup.add(RdoRoedor);
        RdoRoedor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        PanelTipoM.add(RdoRoedor);
        RdoRoedor.setBounds(140, 30, 130, 28);

        bgrGrup.add(rdoFelino);
        rdoFelino.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        PanelTipoM.add(rdoFelino);
        rdoFelino.setBounds(10, 30, 130, 28);

        PanelInfoA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion Adicional", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        PanelInfoA.setLayout(null);

        cboVacunas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        PanelInfoA.add(cboVacunas);
        cboVacunas.setBounds(20, 150, 170, 25);

        lblVacunas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblVacunas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVacunas.setText("jLabel1");
        PanelInfoA.add(lblVacunas);
        lblVacunas.setBounds(20, 130, 190, 20);

        cboEdadMas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cboEdadMas.setToolTipText("");
        cboEdadMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEdadMasActionPerformed(evt);
            }
        });
        PanelInfoA.add(cboEdadMas);
        cboEdadMas.setBounds(280, 60, 140, 25);

        lblEdadMas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblEdadMas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEdadMas.setText("jLabel1");
        lblEdadMas.setToolTipText("");
        PanelInfoA.add(lblEdadMas);
        lblEdadMas.setBounds(240, 30, 210, 30);

        txtNombMasc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNombMasc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelInfoA.add(txtNombMasc);
        txtNombMasc.setBounds(20, 70, 200, 30);

        lblNomMas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblNomMas.setText("jLabel2");
        PanelInfoA.add(lblNomMas);
        lblNomMas.setBounds(20, 36, 200, 20);

        lblPeso.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblPeso.setText("jLabel1");
        PanelInfoA.add(lblPeso);
        lblPeso.setBounds(280, 120, 150, 15);

        txtPeso.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtPeso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelInfoA.add(txtPeso);
        txtPeso.setBounds(280, 140, 140, 30);

        txtRuta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtRuta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRuta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblRuta.setText("jLabel1");

        javax.swing.GroupLayout PanelDatosMLayout = new javax.swing.GroupLayout(PanelDatosM);
        PanelDatosM.setLayout(PanelDatosMLayout);
        PanelDatosMLayout.setHorizontalGroup(
            PanelDatosMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosMLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PanelDatosMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosMLayout.createSequentialGroup()
                        .addComponent(pnlImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PanelDatosMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDatosMLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(PanelTipoM, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelDatosMLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PanelDatosMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblRuta)
                                    .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(PanelInfoA, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        PanelDatosMLayout.setVerticalGroup(
            PanelDatosMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosMLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(PanelDatosMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelDatosMLayout.createSequentialGroup()
                        .addGroup(PanelDatosMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelDatosMLayout.createSequentialGroup()
                                .addComponent(lblRuta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(PanelTipoM, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(PanelInfoA, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BtnModificar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BtnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModificarActionPerformed(evt);
            }
        });

        BtnSalir.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelModificarLayout = new javax.swing.GroupLayout(PanelModificar);
        PanelModificar.setLayout(PanelModificarLayout);
        PanelModificarLayout.setHorizontalGroup(
            PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelModificarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelDatosP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelDatosM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelModificarLayout.setVerticalGroup(
            PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelModificarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelModificarLayout.createSequentialGroup()
                        .addComponent(PanelDatosP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PanelDatosM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTexto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblTexto.setText("jLabel1");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PanelModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTexto))
                .addContainerGap())
            .addComponent(PanelStatusBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblTexto)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(PanelModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelStatusBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_BtnSalirActionPerformed

    private void TablaDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaDatosMouseClicked
        ImageIcon foto_2 = new ImageIcon(getClass().getResource("/img/Question.png"));
        ImageIcon mitad_2 = new ImageIcon(foto_2.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
        int V = JOptionPane.showConfirmDialog(null, "Seguro que desea modificar este registro?","AVISO",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,mitad_2);
        if (V == JOptionPane.YES_OPTION) {
            Habilitar();
            txtID1.setEditable(false);
            BtnModificar.setEnabled(true);
            Fecha.setEnabled(true);
            //Pasando los datos de la tabla a los campos para posteriormente actualizarlos
            int Seleccion=TablaDatos.getSelectedRow();
            txtID1.setText(TablaDatos.getValueAt(Seleccion, 0).toString());
            txtNombres.setText(TablaDatos.getValueAt(Seleccion, 1).toString());
            txtApellidos.setText(TablaDatos.getValueAt(Seleccion, 2).toString());
            txtCC.setText(TablaDatos.getValueAt(Seleccion, 3).toString());
            String Genero=TablaDatos.getValueAt(Seleccion, 4).toString();
            if (Genero.equals("Masculino")) {
                cboGenero.setSelectedIndex(1);
            }else if (Genero.equals("Femenino")){
                cboGenero.setSelectedIndex(2);
            }else if(Genero.equals("Otros")){
                cboGenero.setSelectedIndex(3);
            } 
            try {
                Date date =new SimpleDateFormat("yyyy-MM-dd").parse((String)TablaDatos.getValueAt(Seleccion, 5).toString());
                Fecha.setDate(date);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            String TipoM=TablaDatos.getValueAt(Seleccion, 6).toString();
            if (TipoM.equals("Canino")) {
                rdoCanino.setSelected(true);
            } else if (TipoM.equals("Felino")) {
                rdoFelino.setSelected(true);
            }else
            if (TipoM.equals("Roedor")) {
                RdoRoedor.setSelected(true);
            }
            txtNombMasc.setText(TablaDatos.getValueAt(Seleccion, 7).toString());
            cboEdadMas.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione",
                "Recien nacido",
                "Entre 3 a 6 meses",
                "Entre 7 a 11 meses",
                "1 Año",
                "Entre 1 a 2 años",
                "Mas de 3 años",
                "Mas de 10 años"}));
            String TiempMac=TablaDatos.getValueAt(Seleccion, 8).toString();
            if (TiempMac.equals("Recien nacido")) {
                cboEdadMas.setSelectedIndex(1);
            } else if (TiempMac.equals("Entre 3 a 6 meses")) {
                cboEdadMas.setSelectedIndex(2);
            }else if (TiempMac.equals("Entre 7 a 11 meses")) {
                cboEdadMas.setSelectedIndex(3);
            }else if (TiempMac.equals("1 Año") || TiempMac.equals("1 A?o")) {
                cboEdadMas.setSelectedIndex(4);
            }else if (TiempMac.equals("Entre 1 a 2 años") || TiempMac.equals("Entre 1 a 2 a?os")) {
                cboEdadMas.setSelectedIndex(5);
            }else if (TiempMac.equals("Mas de 3 años") || TiempMac.equals("Mas de 3 a?os")) {
                cboEdadMas.setSelectedIndex(6);
            }else if (TiempMac.equals("Mas de 10 años") || TiempMac.equals("Mas de 10 a?os")) {
                cboEdadMas.setSelectedIndex(7);
            }
            
            String Vacunas=TablaDatos.getValueAt(Seleccion, 9).toString();
            if(Vacunas.equals("Ninguna")){
                cboVacunas.setSelectedIndex(1);
            }else if (Vacunas.equals("1")) {
                cboVacunas.setSelectedIndex(2);
            } else if (Vacunas.equals("2")) {
                cboVacunas.setSelectedIndex(3);
            }else if (Vacunas.equals("3")) {
                cboVacunas.setSelectedIndex(4);
            }else if (Vacunas.equals("4")) {
                cboVacunas.setSelectedIndex(5);
            }else if (Vacunas.equals("5")) {
                cboVacunas.setSelectedIndex(6);
            }
            
            txtPeso.setText(TablaDatos.getValueAt(Seleccion, 10).toString());
        }
    }//GEN-LAST:event_TablaDatosMouseClicked

    private void txtCCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCCKeyTyped
        char c=evt.getKeyChar();
        if (c<'0'||c>'9') evt.consume();
    }//GEN-LAST:event_txtCCKeyTyped

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed
        String Ruta;
        JFileChooser _BuscarFoto=new JFileChooser();
        FileNameExtensionFilter Filtrado=new FileNameExtensionFilter("JPG, PNG & GIF", "jpg","png","gif");
        _BuscarFoto.setFileFilter(Filtrado);
        _BuscarFoto.setDialogTitle("Buscar Imagen");

        int respuesta=_BuscarFoto.showOpenDialog(this);
        if (respuesta==JFileChooser.APPROVE_OPTION) {
            Ruta=_BuscarFoto.getSelectedFile().getPath();
            txtRuta.setEnabled(true);
            txtRuta.setText(Ruta);
            txtRuta.setEditable(false);
            Image mimagen=new ImageIcon(Ruta).getImage();
            ImageIcon mIcon=new ImageIcon(mimagen.getScaledInstance(145,170, Image.SCALE_DEFAULT));
            pnlImagen.setIcon(mIcon);
        }
    }//GEN-LAST:event_btnFotoActionPerformed

    private void cboEdadMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEdadMasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboEdadMasActionPerformed

    private void BtnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModificarActionPerformed
        //Se pasan los datos de los campos de texto, combobox y rdo a las variables
        String Nombre, Apellido, CC, ID, Nombre_Mascota, Genero, Vacunas, Edades, Tipo_Mas = null, Peso;
            ID = txtID1.getText();
            Nombre = txtNombres.getText();
            Apellido = txtApellidos.getText();
            CC = txtCC.getText();
            Nombre_Mascota = txtNombMasc.getText();
            SimpleDateFormat date =new SimpleDateFormat("yyyy-MM-dd");
            String FecNac=date.format(Fecha.getDate());
            Vacunas = (String) cboVacunas.getSelectedItem();
            Edades = (String) cboEdadMas.getSelectedItem();
            Genero = (String) cboGenero.getSelectedItem();
            Peso = txtPeso.getText();

            if (rdoCanino.isSelected()) {
                Tipo_Mas = "Canino";
            } else if (rdoFelino.isSelected()) {
                Tipo_Mas = "Felino";
            }
            if (RdoRoedor.isSelected()) {
                Tipo_Mas = "Roedor";
            }
            if (cboGenero.getSelectedItem().equals("Seleccione") || cboEdadMas.getSelectedItem().equals("Seleccione") || cboVacunas.getSelectedItem().equals("Seleccione")) {
                ImageIcon foto_1 = new ImageIcon(getClass().getResource("/img/Error1.png"));
                ImageIcon mitad_1 = new ImageIcon(foto_1.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
                JOptionPane.showMessageDialog(null, "Hay campos que no pueden quedar sin llenar","ERROR", JOptionPane.PLAIN_MESSAGE,mitad_1);
            }else{
            ImageIcon icono = new ImageIcon(getClass().getResource("/img/Question.png"));
            ImageIcon mitad_0 = new ImageIcon(icono.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
            int V = JOptionPane.showConfirmDialog(rootPane, "El registro con la ID: "+ID+" se modificara con los siguientes datos:  "
                    + "\n-------------Datos Personales--------------"
                    + "\nID: " + ID
                    + "\nNombre Completo: " + Nombre + " " + Apellido
                    + "\nGenero: " + Genero
                    + "\nDocumento Legal: " + CC
                    + "\nFecha De Nacimiento: " + FecNac
                    + "\n-------------Datos de la Mascota--------------"
                    + "\nNombre: " + Nombre_Mascota
                    + "\nTipo De Mascota: " + Tipo_Mas
                    + "\nVacunas Aplicadas: " + Vacunas
                    + "\nEdad Promedio: " + Edades
                    + "\nPeso Promedio: " + Peso
                    + "\n¿Es correcto?: ","AVISO",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE, mitad_0);
            if (V == JOptionPane.YES_OPTION) {
                //se llama al metodo Update de la clase MySQL.java para que haga la debida sentencia a la base de datos para actualizar el registro
                String Tabla = "RegistrosUsu";
                db.RecognizeDATABASE("root", "", "Spa_MascotasAV");
                db.UpdateData(Tabla, ID, Nombre, Apellido, CC, Genero, FecNac, Tipo_Mas, Nombre_Mascota, Edades, Vacunas, Peso);
                //se llama a un metodo CargarTabla que sirve para que el jtable se actualize a la vez que salte el mensaje de "datos actualizados"
                CargarTabla();
                //se limpian las casillas/campos
                txtNombres.setText("");
                txtApellidos.setText("");
                txtCC.setText("");
                txtID1.setText("");
                txtNombMasc.setText("");
                Fecha.setCalendar(null);
                cboVacunas.setSelectedIndex(0);
                cboEdadMas.setSelectedIndex(0);
                cboGenero.setSelectedIndex(0);
                txtPeso.setText("");
                bgrGrup.clearSelection();
                pnlImagen.setIcon(null);
                txtRuta.setEnabled(false);
                txtRuta.setText("");
                txtRuta.setEditable(false);
                bgrGrupo.clearSelection();
                InHabilitar();
            }
            }
            
    }//GEN-LAST:event_BtnModificarActionPerformed

    /**
     * @param args the command line arguments
     * @return 
     */
     public void CargarTabla(){
         Modelo.setRowCount(0);
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
            // se añade el modelo de la fila completa
            Modelo.addRow(fila);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TablaDatos.setModel(Modelo);
    
//        DefaultTableModel ModeloB = new DefaultTableModel();
//        String DatosB[][] = {};
//        String ColumnasB[] = {"ID", "Nombre", "Apellido", "CC", "Genero", "FecNac", "TipoMasc", "NomMasc", "TiempMac", "CantVac", "Peso"};
//        ModeloB = new DefaultTableModel(DatosB, ColumnasB);
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "Spa_MascotasAV", "root", "");
//            Statement s = Conexion.createStatement();
//            ResultSet rs = s.executeQuery("SELECT * FROM RegistrosUsu");
//            while (rs.next()) {
//                Object[] fila = new Object[11];
//                for (int i = 0; i < 11; i++) {
//                    fila[i] = rs.getObject(i + 1);
//                }
//                ModeloB.addRow(fila);
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        TablaDatos.setModel(ModeloB);
//        ModeloB.fireTableDataChanged();
     }
     
     public void InHabilitar() {
        txtCC.setEnabled(false);
        txtNombres.setEnabled(false);
        txtNombMasc.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtPeso.setEnabled(false);
        txtID1.setEnabled(false);
        txtRuta.setEnabled(false);
        cboEdadMas.setEnabled(false);
        cboGenero.setEnabled(false);
        cboVacunas.setEnabled(false);
        btnFoto.setEnabled(false);
        RdoRoedor.setEnabled(false);
        rdoCanino.setEnabled(false);
        rdoFelino.setEnabled(false);
        BtnModificar.setEnabled(false);
        Fecha.setEnabled(false);

    }
    
        public void Habilitar() {
        txtCC.setEnabled(true);
        txtNombres.setEnabled(true);
        txtNombMasc.setEnabled(true);
        txtApellidos.setEnabled(true);
        txtPeso.setEnabled(true);
        cboEdadMas.setEnabled(true);
        cboGenero.setEnabled(true);
        cboVacunas.setEnabled(true);
        btnFoto.setEnabled(true);
        RdoRoedor.setEnabled(true);
        rdoCanino.setEnabled(true);
        rdoFelino.setEnabled(true);

    }
        
        
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
            java.util.logging.Logger.getLogger(FrmModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//Cambiar apariencia al estilo del sistema 
                new FrmModificar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnModificar;
    private javax.swing.JButton BtnSalir;
    private com.toedter.calendar.JDateChooser Fecha;
    private javax.swing.JPanel Linea3;
    private javax.swing.JPanel PanelDatosM;
    private javax.swing.JPanel PanelDatosP;
    private javax.swing.JPanel PanelInfoA;
    private javax.swing.JPanel PanelModificar;
    private javax.swing.JPanel PanelStatusBar3;
    private javax.swing.JPanel PanelTipoM;
    private javax.swing.JRadioButton RdoRoedor;
    private javax.swing.JLabel Reloj;
    private javax.swing.JTable TablaDatos;
    private javax.swing.ButtonGroup bgrGrup;
    private javax.swing.ButtonGroup bgrGrupo;
    private javax.swing.JButton btnFoto;
    private javax.swing.JComboBox<String> cboEdadMas;
    private javax.swing.JComboBox<String> cboGenero;
    private javax.swing.JComboBox<String> cboVacunas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblAño;
    private javax.swing.JLabel lblCC;
    private javax.swing.JLabel lblEdadMas;
    private javax.swing.JLabel lblFormulario;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNomMas;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblRuta;
    private javax.swing.JLabel lblTexto;
    public static javax.swing.JLabel lblUsuarioLogin;
    private javax.swing.JLabel lblVacunas;
    private javax.swing.JLabel lblfrm;
    private javax.swing.JLabel pnlImagen;
    private javax.swing.JRadioButton rdoCanino;
    private javax.swing.JRadioButton rdoFelino;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCC;
    private javax.swing.JTextField txtID1;
    private javax.swing.JTextField txtNombMasc;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables
    
}
