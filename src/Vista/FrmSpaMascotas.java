package Vista;

import Modelo.MySQL;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import Controlador.Ctrl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ANDRES FELIPE VIVEROS ALBAN - S3AN
 */
public class FrmSpaMascotas extends javax.swing.JInternalFrame {
    public static int Ancho=32, Alto=32;
    Color color, color1;
    MySQL db=new MySQL();
    Ctrl bd = new Ctrl();

    /**
     * Creates new form FrmSpaMascotas
     */
    public FrmSpaMascotas() {//Constructor
        //getContentPane().setLayout(null);
        //Configurando Screen
        
        setTitle("VETERINARIA VIVEROS");
        Dimension _Screen=Toolkit.getDefaultToolkit().getScreenSize();
        setSize(_Screen.width/2,_Screen.height/2);
        
        
        //asignar icono para el JFRAME
        Image _Icono=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/user.png"));
        this.setResizable(false);
        initComponents();
        conectarBD();
        //Traer la variable usuario del FrmLogin aqui Y ponerlaen lblUsuarioLogin
        FrmLogin UsuarioLogin = new FrmLogin();
        lblUsuarioLogin.setText(UsuarioLogin.usuario);
        
        PanelDatosP.setOpaque(false);
        PanelDatosM.setOpaque(false);
        PanelTipoM.setOpaque(false);
        PanelInfoA.setOpaque(false);
        PanelStatusBar.setOpaque(false);
        
        //ver configuracion
        Dimension Pantalla=Toolkit.getDefaultToolkit().getScreenSize();
        ImageIcon foto_10 = new ImageIcon(getClass().getResource("/img/user.png"));
        ImageIcon mitad_20 = new ImageIcon(foto_10.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
        JOptionPane.showMessageDialog(rootPane, "BIENVENIDOS A LA VETERINARIA VIVEROS","Mensaje",JOptionPane.INFORMATION_MESSAGE,mitad_20);
        //JPanel
        //pnlPanel.setPreferredSize(new Dimension(1920, 1080));
        //Manejar o gestionar las propiedades y eventos de los componenetes
        //Radiobutton
        lblCabecera.setText("BIENVENIDO AL SISTEMA DE REGISTRO");
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
        lblAño.setText("Año de nacimiento");
        lblAño.setForeground(Color.black);
        lblMes.setText("Mes de nacimiento");
        lblMes.setForeground(Color.black);
        lblDia.setText("Dia de nacimiento");
        lblDia.setForeground(Color.black);
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
        //lblApellido.setSize(24, 35);
        //lblEstracto.setFont("",ESTILO,TAMAÑO);
        
        //JCombo ==> Cuadros Combinados De Lista ==> Listas Dinamicas
        cboGenero.setToolTipText("Click para desplegar las opciones");
        cboGenero.addItem("Seleccione");
        cboGenero.addItem("Masculino");
        cboGenero.addItem("Femenino");
        cboGenero.addItem("Otros");
        cboGenero.setSelectedIndex(0);
        
        cboAño.setToolTipText("Click para desplegar las opciones");
        cboAño.addItem("Seleccione");
        cboAño.setSelectedIndex(0);
        for (int i = 2021; i > 1920 ; i--) {
            cboAño.addItem(String.valueOf(i));
        }
        
        cboVacunas.setToolTipText("Click para desplegar las opciones");
        cboVacunas.addItem("Seleccione");
        cboVacunas.addItem("Ninguna");
        cboVacunas.setSelectedIndex(0);
        for (int i = 1; i < 6; i++) {
            cboVacunas.addItem(String.valueOf(i));
        }
        
        cboMes.setToolTipText("Click para desplegar las opciones");
        cboMes.addItem("Seleccione");
        cboMes.setSelectedIndex(0);
        for (int i = 1; i <= 12; i++) {
            cboMes.addItem(String.valueOf(i));
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
        
        cboDia.setToolTipText("Click para desplegar las opciones");
        cboDia.addItem("Seleccione");
        cboDia.setSelectedIndex(0);
        for (int i = 1; i <= 31; i++) {
            cboDia.addItem(String.valueOf(i));
        }
        
        //botones
        /* Imagenes En Los Botones*/
        ImageIcon _img=new ImageIcon(getClass().getResource("/img/Camera.png"));
        btnFoto.setIcon(_img);
        btnFoto.setToolTipText("Haga Click (para subir la foto)");
        
        ImageIcon foto_0 = new ImageIcon(getClass().getResource("/img/Registrar1.png"));
        ImageIcon mitad_0 = new ImageIcon(foto_0.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        BtnRegistrar.setIcon(mitad_0);
        BtnRegistrar.setToolTipText("Click para registrar los datos");
        
        ImageIcon foto_2 = new ImageIcon(getClass().getResource("/img/Salir.png"));
        ImageIcon mitad_2 = new ImageIcon(foto_2.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
        BtnSalir.setIcon(mitad_2);
        BtnSalir.setToolTipText("Click para finalizar la conexion y salir");
        
        ImageIcon foto_4 = new ImageIcon(getClass().getResource("/img/Nuevo.png"));
        ImageIcon mitad_4 = new ImageIcon(foto_4.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
        BtnNuevo.setIcon(mitad_4);
        BtnNuevo.setToolTipText("Click para agregar un nuevo registro");
        
        ImageIcon foto_6 = new ImageIcon(getClass().getResource("/img/Siguiente_opt.png"));
        BtnSiguiente.setIcon(foto_6);
        BtnSiguiente.setToolTipText("Navegar al siguiente registro");
        
        ImageIcon foto_7 = new ImageIcon(getClass().getResource("/img/Atras_opt.png"));
        BtnAnterior.setIcon(foto_7);
        BtnAnterior.setToolTipText("Navegar al anterior registro");
        
        ImageIcon foto_8 = new ImageIcon(getClass().getResource("/img/Primero_opt.png"));
        BtnInicio.setIcon(foto_8);
        BtnInicio.setToolTipText("Navegar al Inicio del registro");
        
        ImageIcon foto_9 = new ImageIcon(getClass().getResource("/img/Ultimo_opt.png"));
        BtnUltimo.setIcon(foto_9);
        BtnUltimo.setToolTipText("Navegar al ultimo registro");
        
        ImageIcon fotoR2 = new ImageIcon(getClass().getResource("/img/Cancel.png"));
        ImageIcon mitadR_2 = new ImageIcon(fotoR2.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
        BtnBlockNav.setIcon(mitadR_2);
        BtnBlockNav.setToolTipText("Desactivar la navegacion en los registros");
        
        BtnBlockNav.setEnabled(false);
        
        color = new Color(192, 192, 192);
        color1 = new Color(176, 176, 176);
        
        txtNombres.setText("Ingrese su nombre");
        txtNombres.setForeground(color);
        
        txtApellidos.setText("Ingrese su apellido");
        txtApellidos.setForeground(color);
        
        txtCC.setText("Ingrese su C.C o T.I");
        txtCC.setForeground(color);
        
        txtID.setText("Ingrese un ID");
        txtID.setForeground(color);
        
        txtNombMasc.setText("Digite nombre de su mascota");
        txtNombMasc.setForeground(color);
        
        txtPeso.setText("Peso ejp(23.4kg)");
        txtPeso.setForeground(color);
        txtRuta.setForeground(color1);
        
        BtnNuevo.setEnabled(false);
        
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
        //*********************************************************************************************
        //Conecta con la db y devulve true si se conecto correctamente
        //false cualquier otro caso
        public boolean conectarBD(){
        //especificar cual es la llave primaria de la tabla y su tipo
        bd.setCampoLlave("ID","cadena");
        //bd.ordenarPor("Nombre");
        
        //conecta con el usuario, pass, BD, y db,s (manejador) especificados
        bd.conectarBD("root", "", "spa_mascotasav", "mysql");
        //especifica la tabla sobre la que se va a manipular
        bd.setTabla("registrosusu");
        //dentro del objetp bd rellena un arreglo con las llaves
        //primarias de la tabla que se esta manipulando
        bd.obtenerCamposLlave();
        
        return true;
        } 
        
        //Rellena los campos de txto con los datos del registro actual
        public void rellenarCampos(){
            try {
                //limpia todos los campos del frame
                txtNombres.setText("");
                txtNombres.setForeground(Color.BLACK);
                txtApellidos.setText("");
                txtApellidos.setForeground(Color.BLACK);
                txtCC.setText("");
                txtCC.setForeground(Color.BLACK);
                txtID.setText("");
                txtID.setForeground(Color.BLACK);
                txtNombMasc.setText("");
                txtNombMasc.setForeground(Color.BLACK);
                cboAño.setSelectedIndex(0);
                cboMes.setSelectedIndex(0);
                cboDia.setSelectedIndex(0);
                cboVacunas.setSelectedIndex(0);
                cboEdadMas.setSelectedIndex(0);
                cboGenero.setSelectedIndex(0);
                txtPeso.setText("");
                txtPeso.setForeground(Color.BLACK);
                brGrupo.clearSelection();
                pnlImagen.setIcon(null);
                txtRuta.setEnabled(false);
                txtRuta.setText("");
                txtRuta.setEditable(false);
                
                txtID.setText(bd.rs.getString("ID"));
                txtNombres.setText(bd.rs.getString("Nombre").trim());
                txtApellidos.setText(bd.rs.getString("Apellido").trim());
                txtCC.setText(bd.rs.getString("CC"));
                String Genero=bd.rs.getString("Genero");
                if (Genero.equals("Masculino")) {
                cboGenero.setSelectedIndex(1);
                }else if (Genero.equals("Femenino")){
                cboGenero.setSelectedIndex(2);
                }else if(Genero.equals("Otros")){
                cboGenero.setSelectedIndex(3);
                } 
                String Fecha =bd.rs.getDate("FechaNac").toString();
                String Año = Fecha.substring(0, 4);
                String Mes = Fecha.substring(5, 7);
                String Dia = Fecha.substring(8, 10);
                int Dia1 = Integer.parseInt(Dia);
                int Mes1 = Integer.parseInt(Mes);
                 if (Dia1 >= 1 && Dia1 <= 31){
                    String Dia2 = String.valueOf(Dia1);
                    cboDia.setSelectedItem(Dia2);
                }
                 if (Mes1 >= 1 && Mes1 <= 12){
                    String Mes2 = String.valueOf(Mes1);
                 cboMes.setSelectedItem(Mes2);
                 }
                 cboAño.setSelectedItem(Año);
                txtNombMasc.setText(bd.rs.getString("NomMasc"));
                String TipoM = bd.rs.getString("TipoMasc");
                if (TipoM.equals("Felino")) {
                    rdoFelino.setSelected(true);
                }else if (TipoM.equals("Canino")){
                    rdoCanino.setSelected(true);
                }else if (TipoM.equals("Roedor")){
                    RdoRoedor.setSelected(true);
                }
                String TiempMac=bd.rs.getString("TiempMac");
                if (TiempMac.equals("Recien nacido")) {
                    cboEdadMas.setSelectedIndex(1);
                } else if (TiempMac.equals("Entre 3 a 6 meses")) {
                    cboEdadMas.setSelectedIndex(2);
                } else if (TiempMac.equals("Entre 7 a 11 meses")) {
                    cboEdadMas.setSelectedIndex(3);
                } else if (TiempMac.equals("1 Año") || TiempMac.equals("1 A?o")) {
                    cboEdadMas.setSelectedIndex(4);
                } else if (TiempMac.equals("Entre 1 a 2 años") || TiempMac.equals("Entre 1 a 2 a?os")) {
                    cboEdadMas.setSelectedIndex(5);
                } else if (TiempMac.equals("Mas de 3 años") || TiempMac.equals("Mas de 3 a?os")) {
                    cboEdadMas.setSelectedIndex(6);
                } else if (TiempMac.equals("Mas de 10 años") || TiempMac.equals("Mas de 10 a?os")) {
                    cboEdadMas.setSelectedIndex(7);
                }
                String Vacunas = bd.rs.getString("CantVac");
                if (Vacunas.equals("Ninguna")) {
                    cboVacunas.setSelectedIndex(1);
                } else if (Vacunas.equals("1")) {
                    cboVacunas.setSelectedIndex(2);
                } else if (Vacunas.equals("2")) {
                    cboVacunas.setSelectedIndex(3);
                } else if (Vacunas.equals("3")) {
                    cboVacunas.setSelectedIndex(4);
                } else if (Vacunas.equals("4")) {
                    cboVacunas.setSelectedIndex(5);
                } else if (Vacunas.equals("5")) {
                    cboVacunas.setSelectedIndex(6);
                }
                txtPeso.setText(bd.rs.getString("Peso"));
                
                this.setTitle("Registros "+ (bd.intFilaActual + 1) +" de "+ bd.intTotalRegistros);
                lblfrm.setText(this.getTitle());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        
        } 
        
        //recibe un numero entero y lo devuelve como cadena(String
        private String aCadena(int num){
        return String.valueOf(num);
        }
        
        //recibe un numero decimal y lo devuelve como cadena(String
        private String aCadena(float num){
        return String.valueOf(num);
        }
        
        private void Mostrar(){
            this.setVisible(true);
        }
        
        public void EditableFalse(){
        txtNombres.setEditable(false);
        txtApellidos.setEditable(false);
        txtCC.setEditable(false);
        txtID.setEditable(false);
        txtNombMasc.setEditable(false);
        cboAño.setEditable(false);
        cboMes.setEditable(false);
        cboDia.setEditable(false);
        cboVacunas.setEditable(false);
        cboEdadMas.setEditable(false);
        cboGenero.setEditable(false);
        txtPeso.setEditable(false);
        brGrupo.clearSelection();
        pnlImagen.setIcon(null);
        txtRuta.setEnabled(false);
        txtRuta.setText("");
        txtRuta.setEditable(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        brGrupo = new javax.swing.ButtonGroup();
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
        cboAño = new javax.swing.JComboBox<>();
        lblMes = new javax.swing.JLabel();
        cboMes = new javax.swing.JComboBox<>();
        lblDia = new javax.swing.JLabel();
        cboDia = new javax.swing.JComboBox<>();
        lblID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        BtnNuevo = new javax.swing.JButton();
        BtnRegistrar = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();
        lblCabecera = new javax.swing.JLabel();
        PanelStatusBar = new javax.swing.JPanel();
        Linea = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblUsuarioLogin = new javax.swing.JLabel();
        lblFormulario = new javax.swing.JLabel();
        lblfrm = new javax.swing.JLabel();
        Reloj = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        BtnInicio = new javax.swing.JButton();
        BtnAnterior = new javax.swing.JButton();
        BtnSiguiente = new javax.swing.JButton();
        BtnUltimo = new javax.swing.JButton();
        BtnBlockNav = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        brGrupo.add(rdoCanino);
        rdoCanino.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        PanelTipoM.add(rdoCanino);
        rdoCanino.setBounds(10, 70, 130, 28);

        brGrupo.add(RdoRoedor);
        RdoRoedor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        PanelTipoM.add(RdoRoedor);
        RdoRoedor.setBounds(140, 30, 130, 28);

        brGrupo.add(rdoFelino);
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
        txtNombMasc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombMascFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombMascFocusLost(evt);
            }
        });
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
        txtPeso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPesoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPesoFocusLost(evt);
            }
        });
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
                .addContainerGap(29, Short.MAX_VALUE))
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
                .addComponent(PanelInfoA, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        PanelDatosP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Personales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(255, 0, 0))); // NOI18N

        lblNombre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblNombre.setText("jLabel1");

        lblApellido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblApellido.setText("jLabel2");

        txtNombres.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNombres.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNombres.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombresFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombresFocusLost(evt);
            }
        });

        txtApellidos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtApellidos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtApellidos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtApellidosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtApellidosFocusLost(evt);
            }
        });

        lblCC.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblCC.setText("jLabel1");

        txtCC.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCCFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCCFocusLost(evt);
            }
        });
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

        cboAño.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cboAño.setToolTipText("");

        lblMes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblMes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMes.setText("jLabel1");
        lblMes.setToolTipText("");

        cboMes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        lblDia.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblDia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDia.setText("jLabel1");
        lblDia.setToolTipText("");

        cboDia.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        lblID.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblID.setText("jLabel1");

        txtID.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIDFocusLost(evt);
            }
        });

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
                            .addComponent(cboAño, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMes, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboMes, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDia, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDia, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addComponent(txtID)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        PanelDatosPLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtApellidos, txtID, txtNombres});

        PanelDatosPLayout.setVerticalGroup(
            PanelDatosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDatosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(10, 10, 10)
                .addComponent(cboAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblMes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(cboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblDia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(cboDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        PanelDatosPLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtApellidos, txtID, txtNombres});

        BtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoActionPerformed(evt);
            }
        });

        BtnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegistrarActionPerformed(evt);
            }
        });

        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });

        lblCabecera.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        lblCabecera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        PanelStatusBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Linea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout LineaLayout = new javax.swing.GroupLayout(Linea);
        Linea.setLayout(LineaLayout);
        LineaLayout.setHorizontalGroup(
            LineaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        LineaLayout.setVerticalGroup(
            LineaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        javax.swing.GroupLayout PanelStatusBarLayout = new javax.swing.GroupLayout(PanelStatusBar);
        PanelStatusBar.setLayout(PanelStatusBarLayout);
        PanelStatusBarLayout.setHorizontalGroup(
            PanelStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStatusBarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuarioLogin)
                .addGap(72, 72, 72)
                .addComponent(Linea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(lblFormulario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblfrm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Reloj, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelStatusBarLayout.setVerticalGroup(
            PanelStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Linea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStatusBarLayout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(PanelStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblName)
                        .addComponent(lblUsuarioLogin))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFormulario)
                        .addComponent(lblfrm)
                        .addComponent(Reloj)))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Navegación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        BtnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInicioActionPerformed(evt);
            }
        });

        BtnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAnteriorActionPerformed(evt);
            }
        });

        BtnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSiguienteActionPerformed(evt);
            }
        });

        BtnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUltimoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(BtnSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(BtnUltimo, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                .addContainerGap())
        );

        BtnBlockNav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBlockNavActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelDatosP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnBlockNav, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PanelDatosM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(lblCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(PanelStatusBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelDatosP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelDatosM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BtnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnBlockNav, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelStatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void BtnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistrarActionPerformed
        if (txtID.getText().equals("") || txtID.getText().equals("Ingrese un ID") || cboAño.getSelectedItem().equals("Seleccione") || cboMes.getSelectedItem().equals("Seleccione") || 
                cboDia.getSelectedItem().equals("Seleccione") || cboGenero.getSelectedItem().equals("Seleccione") || cboEdadMas.getSelectedItem().equals("Seleccione") || 
                cboVacunas.getSelectedItem().equals("Seleccione") || RdoRoedor.isSelected()==false && rdoCanino.isSelected()==false && rdoFelino.isSelected()==false) {
            ImageIcon icono = new ImageIcon(getClass().getResource("/img/Error.png"));
            ImageIcon mitad_0 = new ImageIcon(icono.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(null, "No hay la cantidad de informacion suficiente para realizar el registro!","ERROR",JOptionPane.PLAIN_MESSAGE,mitad_0);
        }else{
            String Nombre, Apellido, CC, ID, Nombre_Mascota, Genero, Año, Mes, Dia, Vacunas, Edades, Tipo_Mas = null, Peso;
            ID = txtID.getText();
            Nombre = txtNombres.getText();
            Apellido = txtApellidos.getText();
            CC = txtCC.getText();
            Nombre_Mascota = txtNombMasc.getText();
            Año = (String) cboAño.getSelectedItem();
            Mes = (String) cboMes.getSelectedItem();
            Dia = (String) cboDia.getSelectedItem();
            String FecNac = Año + "/" + Mes + "/" + Dia;
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
            ImageIcon icono = new ImageIcon(getClass().getResource("/img/Question.png"));
            ImageIcon mitad_0 = new ImageIcon(icono.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
            int V = JOptionPane.showConfirmDialog(rootPane, "Su registro se guardara con los siguientes datos:  "
                    + "\n-------------Datos Personales--------------"
                    + "\nID: " + ID
                    + "\nNombre Completo: " + Nombre + " " + Apellido
                    + "\nGenero: " + Genero
                    + "\nDocumento Legal: " + CC
                    + "\nFecha De Nacimiento: " + Año + "/" + Mes + "/" + Dia
                    + "\n-------------Datos De La Mascota--------------"
                    + "\nNombre: " + Nombre_Mascota
                    + "\nTipo De Mascota: " + Tipo_Mas
                    + "\nVacunas Aplicadas: " + Vacunas
                    + "\nEdad Promedio: " + Edades
                    + "\nPeso Promedio: " + Peso
                    + "\n¿Es correcto?: ","AVISO",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE, mitad_0);
            if (V == JOptionPane.YES_OPTION) {
                String Tabla = "RegistrosUsu";
                db.RecognizeDATABASE("root", "", "Spa_MascotasAV");
                db.InsertData(Tabla, ID, Nombre, Apellido, CC, Genero, FecNac, Tipo_Mas, Nombre_Mascota, Edades, Vacunas, Peso);
                txtNombres.setText("");
                txtApellidos.setText("");
                txtCC.setText("");
                txtID.setText("");
                txtNombMasc.setText("");
                cboAño.setSelectedIndex(0);
                cboMes.setSelectedIndex(0);
                cboDia.setSelectedIndex(0);
                cboVacunas.setSelectedIndex(0);
                cboEdadMas.setSelectedIndex(0);
                cboGenero.setSelectedIndex(0);
                txtPeso.setText("");
                brGrupo.clearSelection();
                pnlImagen.setIcon(null);
                txtRuta.setEnabled(false);
                txtRuta.setText("");
                txtRuta.setEditable(false);

                BtnNuevo.setEnabled(true);
                InHabilitar();
                BtnSalir.setEnabled(true);
            }

        }
    }//GEN-LAST:event_BtnRegistrarActionPerformed

    private void txtNombresFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombresFocusGained
        if (txtNombres.getText().equals("Ingrese su nombre")) {
            txtNombres.setText("");
            txtNombres.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtNombresFocusGained

    private void txtNombresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombresFocusLost
        if (txtNombres.getText().equals("")) {
            txtNombres.setText("Ingrese su nombre");
            txtNombres.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtNombresFocusLost

    private void txtApellidosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApellidosFocusGained
        if (txtApellidos.getText().equals("Ingrese su apellido")) {
            txtApellidos.setText("");
            txtApellidos.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtApellidosFocusGained

    private void txtApellidosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApellidosFocusLost
        if (txtApellidos.getText().equals("")) {
            txtApellidos.setText("Ingrese su apellido");
            txtApellidos.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtApellidosFocusLost

    private void txtCCFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCCFocusGained
        if (txtCC.getText().equals("Ingrese su C.C o T.I")) {
            txtCC.setText("");
            txtCC.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtCCFocusGained

    private void txtCCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCCFocusLost
        if (txtCC.getText().equals("")) {
            txtCC.setText("Ingrese su C.C o T.I");
            txtCC.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtCCFocusLost

    private void txtNombMascFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombMascFocusGained
        if (txtNombMasc.getText().equals("Digite nombre de su mascota")) {
            txtNombMasc.setText("");
            txtNombMasc.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtNombMascFocusGained

    private void txtNombMascFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombMascFocusLost
        if (txtNombMasc.getText().equals("")) {
            txtNombMasc.setText("Digite nombre de su mascota");
            txtNombMasc.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtNombMascFocusLost

    private void txtPesoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoFocusGained
        if (txtPeso.getText().equals("Peso ejp(23.4kg)")) {
            txtPeso.setText("");
            txtPeso.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtPesoFocusGained

    private void txtPesoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoFocusLost
        if (txtPeso.getText().equals("")) {
            txtPeso.setText("Peso ejp(23.4kg)");
            txtPeso.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtPesoFocusLost

    private void txtCCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCCKeyTyped
        char A=evt.getKeyChar();
        if (A<'0'||A>'9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCCKeyTyped

    private void txtIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIDFocusGained
        if (txtID.getText().equals("Ingrese un ID")) {
            txtID.setText("");
            txtID.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtIDFocusGained

    private void txtIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIDFocusLost
        if (txtID.getText().equals("")) {
            txtID.setText("Ingrese un ID");
            txtID.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtIDFocusLost

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        db.closeConnection();
        this.setVisible(false);
    }//GEN-LAST:event_BtnSalirActionPerformed

    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
        Habilitar();
        BtnNuevo.setEnabled(false);
    }//GEN-LAST:event_BtnNuevoActionPerformed

    private void BtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioActionPerformed
        bd.primero();
        rellenarCampos();
        InHabilitar();
        BtnBlockNav.setEnabled(true);
    }//GEN-LAST:event_BtnInicioActionPerformed

    private void BtnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnteriorActionPerformed
        bd.anterior();
        rellenarCampos();
        InHabilitar();
        BtnBlockNav.setEnabled(true);
    }//GEN-LAST:event_BtnAnteriorActionPerformed

    private void BtnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSiguienteActionPerformed
        bd.siguiente();
        rellenarCampos();
        InHabilitar();
        BtnBlockNav.setEnabled(true);
    }//GEN-LAST:event_BtnSiguienteActionPerformed

    private void BtnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUltimoActionPerformed
        bd.ultimo();
        rellenarCampos();
        InHabilitar();
        BtnBlockNav.setEnabled(true);
    }//GEN-LAST:event_BtnUltimoActionPerformed

    private void BtnBlockNavActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBlockNavActionPerformed
        Habilitar();
        BtnBlockNav.setEnabled(false);
        this.setTitle("VATERINARIA VIVEROS");
        txtNombres.setText("");
                txtApellidos.setText("");
                txtCC.setText("");
                txtID.setText("");
                txtNombMasc.setText("");
                cboAño.setSelectedIndex(0);
                cboMes.setSelectedIndex(0);
                cboDia.setSelectedIndex(0);
                cboVacunas.setSelectedIndex(0);
                cboEdadMas.setSelectedIndex(0);
                cboGenero.setSelectedIndex(0);
                txtPeso.setText("");
                brGrupo.clearSelection();
                pnlImagen.setIcon(null);
                txtRuta.setEnabled(false);
                txtRuta.setText("");
                txtRuta.setEditable(false);
                lblfrm.setText(this.getTitle());
    }//GEN-LAST:event_BtnBlockNavActionPerformed
    
    
    
    /**
     * @param args the command line arguments
     */
    public void InHabilitar() {
        txtCC.setEnabled(false);
        txtNombres.setEnabled(false);
        txtNombMasc.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtPeso.setEnabled(false);
        txtID.setEnabled(false);
        txtRuta.setEnabled(false);
        cboAño.setEnabled(false);
        cboDia.setEnabled(false);
        cboEdadMas.setEnabled(false);
        cboGenero.setEnabled(false);
        cboMes.setEnabled(false);
        cboVacunas.setEnabled(false);
        btnFoto.setEnabled(false);
        BtnRegistrar.setEnabled(false);
        BtnSalir.setEnabled(false);
        RdoRoedor.setEnabled(false);
        rdoCanino.setEnabled(false);
        rdoFelino.setEnabled(false);

    }
    
        public void Habilitar() {
        txtCC.setEnabled(true);
        txtNombres.setEnabled(true);
        txtNombMasc.setEnabled(true);
        txtApellidos.setEnabled(true);
        txtPeso.setEnabled(true);
        txtID.setEnabled(true);
        cboAño.setEnabled(true);
        cboDia.setEnabled(true);
        cboEdadMas.setEnabled(true);
        cboGenero.setEnabled(true);
        cboMes.setEnabled(true);
        cboVacunas.setEnabled(true);
        btnFoto.setEnabled(true);
        BtnRegistrar.setEnabled(true);
        BtnSalir.setEnabled(true);
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
            java.util.logging.Logger.getLogger(FrmSpaMascotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSpaMascotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSpaMascotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSpaMascotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//Cambiar apariencia al estilo del sistema 
                new FrmSpaMascotas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAnterior;
    private javax.swing.JButton BtnBlockNav;
    private javax.swing.JButton BtnInicio;
    private javax.swing.JButton BtnNuevo;
    private javax.swing.JButton BtnRegistrar;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JButton BtnSiguiente;
    private javax.swing.JButton BtnUltimo;
    private javax.swing.JPanel Linea;
    private javax.swing.JPanel PanelDatosM;
    private javax.swing.JPanel PanelDatosP;
    private javax.swing.JPanel PanelInfoA;
    private javax.swing.JPanel PanelStatusBar;
    private javax.swing.JPanel PanelTipoM;
    private javax.swing.JRadioButton RdoRoedor;
    private javax.swing.JLabel Reloj;
    private javax.swing.ButtonGroup brGrupo;
    private javax.swing.JButton btnFoto;
    private javax.swing.JComboBox<String> cboAño;
    private javax.swing.JComboBox<String> cboDia;
    private javax.swing.JComboBox<String> cboEdadMas;
    private javax.swing.JComboBox<String> cboGenero;
    private javax.swing.JComboBox<String> cboMes;
    private javax.swing.JComboBox<String> cboVacunas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblAño;
    private javax.swing.JLabel lblCC;
    private javax.swing.JLabel lblCabecera;
    private javax.swing.JLabel lblDia;
    private javax.swing.JLabel lblEdadMas;
    private javax.swing.JLabel lblFormulario;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblMes;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNomMas;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblRuta;
    public static javax.swing.JLabel lblUsuarioLogin;
    private javax.swing.JLabel lblVacunas;
    private javax.swing.JLabel lblfrm;
    private javax.swing.JLabel pnlImagen;
    private javax.swing.JRadioButton rdoCanino;
    private javax.swing.JRadioButton rdoFelino;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCC;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNombMasc;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables
}
