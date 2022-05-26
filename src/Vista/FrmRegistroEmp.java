package Vista;

import Controlador.Control;
import Modelo.MySQL;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ANDRES FELIPE VIVEROS ALBAN - S3AN
 */
public class FrmRegistroEmp extends javax.swing.JInternalFrame {
String[] ruta = {"/img/img_menu/Back_opt.png"};
public static int Ancho=32, Alto=32;
private Connection Conexion;
    Color color, color1;
    MySQL db=new MySQL();
    Control bdC = new Control();
    public int NRegistros;
    /**
     * Creates new form FrmRegistroEmp
     */
    public FrmRegistroEmp() {
        setTitle("CRUD de Empleados");
        initComponents();
        
        conectarBD();
        FrmLogin UsuarioLogin = new FrmLogin();
        lblUsuarioLogin.setText(UsuarioLogin.usuario);
        lblName.setText("Usuario: ");
        lblName.setForeground(Color.black);
        lblFormulario.setText("Formulario Actual: ");
        lblFormulario.setForeground(Color.black);
        lblfrm.setText(this.getTitle());
        
        ImageIcon foto_0 = new ImageIcon(getClass().getResource(ruta[0]));
        BtnSalir.setIcon(foto_0);
        
        //Pestaña1
        txtCC.setText("");
        txtCodigo.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtFechaIng.setText("");
        txtFechaNac.setText("");
        txtNombreC.setText("");
        txtNotas.setText("");
        txtNumCuenta.setText("");
        txtSalario.setText("");
        txtTelefono.setText("");
        lblCodigo.setText("Codigo: ");
        lblNombreC.setText("Nombre: ");
        lblCC.setText("CC/TI: ");
        lblNacionalidad.setText("Nacionalidad: ");
        lblCorreo.setText("Correo: ");
        lblTitulo.setText("CRUD de Empleados");
        lblNE.setText("Nivel Educativo: ");
        lblEstadoC.setText("Estado Civil: ");
        lblDireccion.setText("Direccion: ");
        lblFechaNac.setText("Fecha de Nacimiento:");
        lblGenero.setText("Genero: ");
        lblTelef.setText("Telefono: ");
        //Pestaña2
        lblFechaIn.setText("Fecha de ingreso: ");
        lblFormaP.setText("Forma de pago: ");
        lblNotas.setText("NOTA: ");
        lblNumCB.setText("Numero de cuenta: ");
        lblEstadoT.setText("Estado: ");
        lblSalario.setText("Salario: $");
        lblTurno.setText("Turno: ");
        lblTipoC.setText("Tipo de contrato: ");
        
        cboEstadoCivil.setToolTipText("Click para desplegar las opciones");
        cboEstadoCivil.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione",
        "Soltero/a",
        "Casado/a",
        "Union Libre",
        "Separado/a",
        "Divorciado/a",
        "Viudo/a"}));
        
        cboEstado.setToolTipText("Click para desplegar las opciones");
        cboEstado.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione",
        "Activo",
        "Inactivo",
        "Despedido",
        "Retiro",
        "Vacaciones",
        "En espera..."}));
        
        cboFormaPago.setToolTipText("Click para desplegar las opciones");
        cboFormaPago.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione",
        "Efecty (Efectvo)",
        "Cuenta Bancaria",
        "Movii",
        "Nequi",
        "Tarjeta Debito/Credito",
        "Cheque",
        "Contrareembolso"}));
        
        cboGenero.setToolTipText("Click para desplegar las opciones");
        cboGenero.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione",
        "Masculino",
        "Femenino",
        "Otros.."}));
        
        cboNacionalidad.setToolTipText("Click para desplegar las opciones");
        cboNacionalidad.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione",
        "Colombia",
        "Ecuador",
        "Chile",
        "Peru",
        "Paraguay",
        "El Salvador",
        "Estados Unidos",
        "Canada",
        "Irlanda",
        "Nueva Zelanda",
        "Reino Unido",
        "España",
        "Otro (Dentro de latinoamerica)",
        "Otro"}));
        
        cboNivelE.setToolTipText("Click para desplegar las opciones");
        cboNivelE.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione",
        "Preescolar",
        "Primaria",
        "Bachillerato",
        "Tecnico",
        "Tecnologo",
        "Pregrado",
        "Posgrado",
        "Master",
        "Doctorado",
        "Licenciado"}));
        
        cboTipoContrato.setToolTipText("Click para desplegar las opciones");
        cboTipoContrato.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione",
        "Contrato de aprendizaje",
        "Contrato a termino fijo",
        "Contrato a Termino Indefinido",
        "Contrato temporal"}));
        
        cboTurno.setToolTipText("Click para desplegar las opciones");
        cboTurno.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione",
        "Mañana",
        "Tarde",
        "Noche"}));
        
        ImageIcon foto_01 = new ImageIcon(getClass().getResource("/img/Registrar1.png"));
        ImageIcon mitad_0 = new ImageIcon(foto_01.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        BtnRegistrar.setIcon(mitad_0);
        BtnRegistrar.setToolTipText("Click para registrar los datos");
        
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
        
        ImageIcon foto_1 = new ImageIcon(getClass().getResource("/img/Modificar1.png"));
        ImageIcon mitad_01 = new ImageIcon(foto_1.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
        BtnSelect.setIcon(mitad_01);
        BtnSelect.setToolTipText("Click para seleccionar este registro y modificar");
        BtnSelect.setEnabled(false);
        
        ImageIcon fotoR2 = new ImageIcon(getClass().getResource("/img/Cancel.png"));
        ImageIcon mitadR_2 = new ImageIcon(fotoR2.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
        BtnCancelar.setIcon(mitadR_2);
        BtnCancelar.setToolTipText("Desactivar la navegacion en los registros");
        BtnCancelar.setEnabled(false);
        
        ImageIcon foto_3 = new ImageIcon(getClass().getResource("/img/Modificar2_opt.png"));
        BtnModificar.setIcon(foto_3);
        BtnModificar.setToolTipText("Click para modificar el registro actual");
        BtnModificar.setEnabled(false);
        
        ImageIcon foto_4 = new ImageIcon(getClass().getResource("/img/Trash.png"));
        ImageIcon mitad_1 = new ImageIcon(foto_4.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
        BtnEliminar.setIcon(mitad_1);
        BtnEliminar.setToolTipText("Click para eliminar el registro seleccionado en el campo Codigo!");
        
        //Estableciendo placeholders
        color = new Color(192, 192, 192);
        
        txtCodigo.setText("Ingrese un Codigo");
        txtCodigo.setForeground(color);
        txtCC.setText("Digite su cedula");
        txtCC.setForeground(color);
        txtCorreo.setText("Correo electronico");
        txtCorreo.setForeground(color);
        txtDireccion.setText("Ingrese su Direccion");
        txtDireccion.setForeground(color);
        txtFechaIng.setText("Digite fecha de ingreso");
        txtFechaIng.setForeground(color);
        txtFechaNac.setText("Digite fecha de nacimiento");
        txtFechaNac.setForeground(color);
        txtNombreC.setText("Ingrese su nombre completo");
        txtNombreC.setForeground(color);
        txtNotas.setText("Digite nota al empleado");
        txtNotas.setForeground(color);
        txtNumCuenta.setText("Ingrese un numero de cuenta");
        txtNumCuenta.setForeground(color);
        txtSalario.setText("Digite el salario");
        txtSalario.setForeground(color);
        txtTelefono.setText("Ingrese numero de telefono");
        txtTelefono.setForeground(color);
    }
    //*********************************************************************************************
        //Conecta con la db y devulve true si se conecto correctamente
        //false cualquier otro caso
        public boolean conectarBD(){
        //especificar cual es la llave primaria de la tabla y su tipo
        bdC.setCampoLlave("CODIGO","cadena");
        //bd.ordenarPor("Nombre");
        
        //conecta con el usuario, pass, BD, y db,s (manejador) especificados
        bdC.conectarBD("root", "", "spa_mascotasav", "mysql");
        //especifica la tabla sobre la que se va a manipular
        bdC.setTabla("registros_emp");
        //dentro del objetp bd rellena un arreglo con las llaves
        //primarias de la tabla que se esta manipulando
        bdC.obtenerCamposLlave();
        
        return true;
        } 
        
        //Rellena los campos de txto con los datos del registro actual
        public void rellenarCampos(){
            try {
                //limpia todos los campos del frame
                txtCC.setText("");
                txtCodigo.setText("");
                txtCorreo.setText("");
                txtDireccion.setText("");
                txtFechaIng.setText("");
                txtFechaNac.setText("");
                txtNombreC.setText("");
                txtNotas.setText("");
                txtNumCuenta.setText("");
                txtSalario.setText("");
                txtTelefono.setText("");
                cboEstado.setSelectedIndex(0);
                cboEstadoCivil.setSelectedIndex(0);
                cboFormaPago.setSelectedIndex(0);
                cboGenero.setSelectedIndex(0);
                cboNacionalidad.setSelectedIndex(0);
                cboNivelE.setSelectedIndex(0);
                cboTipoContrato.setSelectedIndex(0);
                cboTurno.setSelectedIndex(0);
                
                txtCodigo.setText(bdC.rs.getString("CODIGO"));
                txtNombreC.setText(bdC.rs.getString("NombreC"));
                txtCC.setText(bdC.rs.getString("CC"));
                String Nacionalidad = bdC.rs.getString("Nacionalidad");
                if (Nacionalidad.equals("Espa?a")) {
                cboNacionalidad.setSelectedIndex(12);
                }else{
                cboNacionalidad.setSelectedItem(Nacionalidad);
                }
                String NivelEdu = bdC.rs.getString("Nivel_Educativo");
                cboNivelE.setSelectedItem(NivelEdu);
                txtFechaNac.setText(bdC.rs.getDate("FechaNac").toString());
                txtTelefono.setText(bdC.rs.getString("Telefono"));
                txtCorreo.setText(bdC.rs.getString("Correo"));
                String Genero=bdC.rs.getString("Genero");
                if (Genero.equals("Masculino")) {
                cboGenero.setSelectedIndex(1);
                }else if (Genero.equals("Femenino")){
                cboGenero.setSelectedIndex(2);
                }else if(Genero.equals("Otros..")){
                cboGenero.setSelectedIndex(3);
                } 
                String EstadoCivi = bdC.rs.getString("EstadoCivil");
                cboEstadoCivil.setSelectedItem(EstadoCivi);
                txtDireccion.setText(bdC.rs.getString("Direccion"));
                txtFechaIng.setText(bdC.rs.getDate("FechaIngreso").toString());
                txtNumCuenta.setText(bdC.rs.getString("NumerodeCuenta"));
                txtSalario.setText(bdC.rs.getString("Salario"));
                String TipoContrato = bdC.rs.getString("TipoContrato");
                cboTipoContrato.setSelectedItem(TipoContrato);
                String Estado = bdC.rs.getString("Estado");
                cboEstado.setSelectedItem(Estado);
                String FormaPago = bdC.rs.getString("FormaPago");
                cboFormaPago.setSelectedItem(FormaPago);
                String Turno = bdC.rs.getString("Turno");
                if(Turno.equals("Ma?ana")){
                cboTurno.setSelectedIndex(1);
                }else{
                cboTurno.setSelectedItem(Turno);
                }
                txtNotas.setText(bdC.rs.getString("NOTA"));
                
                NRegistros = (bdC.intFilaActual + 1);
                this.setTitle("Registros "+ (bdC.intFilaActual + 1) +" de "+ bdC.intTotalRegistros);
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
        
        public void HabilitarIn(boolean enable){
        txtCC.setEnabled(enable);
        txtCodigo.setEnabled(enable);
        txtCorreo.setEnabled(enable);
        txtDireccion.setEnabled(enable);
        txtFechaIng.setEnabled(enable);
        txtFechaNac.setEnabled(enable);
        txtNombreC.setEnabled(enable);
        txtNotas.setEnabled(enable);
        txtNumCuenta.setEnabled(enable);
        txtSalario.setEnabled(enable);
        txtTelefono.setEnabled(enable);
        cboEstado.setEnabled(enable);
        cboEstadoCivil.setEnabled(enable);
        cboFormaPago.setEnabled(enable);
        cboGenero.setEnabled(enable);
        cboNacionalidad.setEnabled(enable);
        cboNivelE.setEnabled(enable);
        cboTipoContrato.setEnabled(enable);
        cboTurno.setEnabled(enable);
        }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        PanelStatusBar = new javax.swing.JPanel();
        Linea = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblUsuarioLogin = new javax.swing.JLabel();
        lblFormulario = new javax.swing.JLabel();
        lblfrm = new javax.swing.JLabel();
        Reloj = new javax.swing.JLabel();
        JTPestañas = new javax.swing.JTabbedPane();
        JPDatosG = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblNombreC = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombreC = new javax.swing.JTextField();
        lblCC = new javax.swing.JLabel();
        txtCC = new javax.swing.JTextField();
        lblNacionalidad = new javax.swing.JLabel();
        cboNacionalidad = new javax.swing.JComboBox<>();
        lblNE = new javax.swing.JLabel();
        cboNivelE = new javax.swing.JComboBox<>();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblFechaNac = new javax.swing.JLabel();
        lblTelef = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        txtFechaNac = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        cboGenero = new javax.swing.JComboBox<>();
        cboEstadoCivil = new javax.swing.JComboBox<>();
        lblEstadoC = new javax.swing.JLabel();
        JPDatosL = new javax.swing.JPanel();
        lblSalario = new javax.swing.JLabel();
        lblFechaIn = new javax.swing.JLabel();
        txtNotas = new javax.swing.JTextField();
        lblNotas = new javax.swing.JLabel();
        lblNumCB = new javax.swing.JLabel();
        lblFormaP = new javax.swing.JLabel();
        lblTipoC = new javax.swing.JLabel();
        txtFechaIng = new javax.swing.JTextField();
        txtSalario = new javax.swing.JTextField();
        txtNumCuenta = new javax.swing.JTextField();
        lblEstadoT = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox<>();
        cboTurno = new javax.swing.JComboBox<>();
        lblTurno = new javax.swing.JLabel();
        cboTipoContrato = new javax.swing.JComboBox<>();
        cboFormaPago = new javax.swing.JComboBox<>();
        BtnSalir = new javax.swing.JButton();
        BtnRegistrar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BarraNavegacion = new javax.swing.JPanel();
        BtnInicio = new javax.swing.JButton();
        BtnAnterior = new javax.swing.JButton();
        BtnSiguiente = new javax.swing.JButton();
        BtnUltimo = new javax.swing.JButton();
        BarraModificacion = new javax.swing.JPanel();
        BtnSelect = new javax.swing.JButton();
        BtnModificar = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();

        lblTitulo.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lblTitulo.setText("jLabel1");

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
                .addGap(36, 36, 36)
                .addComponent(Linea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        JTPestañas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JTPestañas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        JPDatosG.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        lblCodigo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCodigo.setText("jLabel1");

        lblNombreC.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombreC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombreC.setText("jLabel2");

        txtCodigo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoFocusLost(evt);
            }
        });

        txtNombreC.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNombreC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreCFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreCFocusLost(evt);
            }
        });

        lblCC.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCC.setText("jLabel4");

        txtCC.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCCFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCCFocusLost(evt);
            }
        });

        lblNacionalidad.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNacionalidad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNacionalidad.setText("jLabel5");

        cboNacionalidad.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        lblNE.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNE.setText("jLabel6");

        cboNivelE.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        lblDireccion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblDireccion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDireccion.setText("jLabel7");

        txtDireccion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtDireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDireccionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDireccionFocusLost(evt);
            }
        });

        lblFechaNac.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblFechaNac.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFechaNac.setText("jLabel1");

        lblTelef.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTelef.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTelef.setText("jLabel1");

        lblCorreo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCorreo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCorreo.setText("jLabel2");

        lblGenero.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblGenero.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGenero.setText("jLabel4");

        txtFechaNac.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtFechaNac.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFechaNacFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFechaNacFocusLost(evt);
            }
        });

        txtTelefono.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTelefonoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTelefonoFocusLost(evt);
            }
        });

        txtCorreo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCorreoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoFocusLost(evt);
            }
        });

        cboGenero.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        cboEstadoCivil.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        lblEstadoC.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblEstadoC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEstadoC.setText("jLabel4");

        javax.swing.GroupLayout JPDatosGLayout = new javax.swing.GroupLayout(JPDatosG);
        JPDatosG.setLayout(JPDatosGLayout);
        JPDatosGLayout.setHorizontalGroup(
            JPDatosGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPDatosGLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(JPDatosGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNombreC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNacionalidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(JPDatosGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JPDatosGLayout.createSequentialGroup()
                        .addGroup(JPDatosGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCC, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboNivelE, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JPDatosGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTelef, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFechaNac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGenero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEstadoC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(JPDatosGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFechaNac)
                            .addComponent(txtTelefono)
                            .addComponent(txtCorreo)
                            .addComponent(cboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtDireccion))
                .addContainerGap(196, Short.MAX_VALUE))
        );
        JPDatosGLayout.setVerticalGroup(
            JPDatosGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPDatosGLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(JPDatosGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPDatosGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCodigo)
                        .addComponent(lblFechaNac)
                        .addComponent(txtFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPDatosGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreC)
                    .addComponent(txtNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelef)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(JPDatosGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCC)
                    .addComponent(txtCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(JPDatosGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNacionalidad)
                    .addComponent(cboNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGenero)
                    .addComponent(cboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(JPDatosGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNE)
                    .addComponent(cboNivelE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoC))
                .addGap(26, 26, 26)
                .addGroup(JPDatosGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDireccion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPDatosGLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboEstadoCivil, cboGenero, cboNacionalidad, cboNivelE, txtCC, txtCodigo, txtCorreo, txtDireccion, txtFechaNac, txtNombreC, txtTelefono});

        JTPestañas.addTab("Datos Generales", new javax.swing.ImageIcon(getClass().getResource("/img/img_menu/Registrar_opt.png")), JPDatosG); // NOI18N

        JPDatosL.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        lblSalario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblSalario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSalario.setText("jLabel2");

        lblFechaIn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblFechaIn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFechaIn.setText("jLabel1");

        txtNotas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNotas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNotas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNotasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNotasFocusLost(evt);
            }
        });

        lblNotas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNotas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNotas.setText("jLabel7");

        lblNumCB.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNumCB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNumCB.setText("jLabel1");

        lblFormaP.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblFormaP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFormaP.setText("jLabel6");

        lblTipoC.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTipoC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTipoC.setText("jLabel5");

        txtFechaIng.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtFechaIng.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFechaIngFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFechaIngFocusLost(evt);
            }
        });

        txtSalario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtSalario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSalarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSalarioFocusLost(evt);
            }
        });

        txtNumCuenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNumCuenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumCuentaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumCuentaFocusLost(evt);
            }
        });

        lblEstadoT.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblEstadoT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEstadoT.setText("jLabel4");

        cboEstado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        cboTurno.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        lblTurno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTurno.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTurno.setText("jLabel4");

        cboTipoContrato.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        cboFormaPago.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        javax.swing.GroupLayout JPDatosLLayout = new javax.swing.GroupLayout(JPDatosL);
        JPDatosL.setLayout(JPDatosLLayout);
        JPDatosLLayout.setHorizontalGroup(
            JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPDatosLLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JPDatosLLayout.createSequentialGroup()
                        .addComponent(lblNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNotas))
                    .addGroup(JPDatosLLayout.createSequentialGroup()
                        .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNumCB, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFechaIn, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(lblTipoC))
                            .addComponent(lblFormaP, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cboFormaPago, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JPDatosLLayout.createSequentialGroup()
                                .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboTipoContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNumCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFechaIng, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTurno, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblSalario, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblEstadoT, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cboTurno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboEstado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(196, Short.MAX_VALUE))
        );
        JPDatosLLayout.setVerticalGroup(
            JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPDatosLLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JPDatosLLayout.createSequentialGroup()
                        .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFechaIng, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaIn))
                        .addGap(18, 18, 18)
                        .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumCB))
                        .addGap(26, 26, 26)
                        .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboTipoContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTipoC)))
                    .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(JPDatosLLayout.createSequentialGroup()
                            .addGap(110, 110, 110)
                            .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTurno)))
                        .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JPDatosLLayout.createSequentialGroup()
                                .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSalario))
                                .addGap(59, 59, 59))
                            .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblEstadoT)))))
                .addGap(18, 18, 18)
                .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFormaP))
                .addGap(29, 29, 29)
                .addGroup(JPDatosLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNotas)
                    .addComponent(txtNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        JTPestañas.addTab("Datos Laborales", new javax.swing.ImageIcon(getClass().getResource("/img/img_menu/DatosL_opt.png")), JPDatosL); // NOI18N

        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });

        BtnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegistrarActionPerformed(evt);
            }
        });

        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        BarraNavegacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Navegación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 12))); // NOI18N

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

        javax.swing.GroupLayout BarraNavegacionLayout = new javax.swing.GroupLayout(BarraNavegacion);
        BarraNavegacion.setLayout(BarraNavegacionLayout);
        BarraNavegacionLayout.setHorizontalGroup(
            BarraNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarraNavegacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BarraNavegacionLayout.setVerticalGroup(
            BarraNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarraNavegacionLayout.createSequentialGroup()
                .addGroup(BarraNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnAnterior)
                    .addComponent(BtnSiguiente)
                    .addComponent(BtnUltimo))
                .addContainerGap())
        );

        BarraNavegacionLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {BtnAnterior, BtnInicio, BtnSiguiente, BtnUltimo});

        BarraModificacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modificacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 12))); // NOI18N

        BtnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSelectActionPerformed(evt);
            }
        });

        BtnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModificarActionPerformed(evt);
            }
        });

        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BarraModificacionLayout = new javax.swing.GroupLayout(BarraModificacion);
        BarraModificacion.setLayout(BarraModificacionLayout);
        BarraModificacionLayout.setHorizontalGroup(
            BarraModificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BarraModificacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(BtnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        BarraModificacionLayout.setVerticalGroup(
            BarraModificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarraModificacionLayout.createSequentialGroup()
                .addGroup(BarraModificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelStatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addComponent(JTPestañas, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BarraNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BarraModificacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BtnRegistrar, BtnSalir});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTPestañas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BarraModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BarraNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(PanelStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {BtnRegistrar, BtnSalir});

        JTPestañas.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_BtnSalirActionPerformed

    private void BtnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistrarActionPerformed
        if (txtCodigo.getText().equals("") || txtCodigo.getText().equals("Ingrese un ID") || cboEstadoCivil.getSelectedItem().equals("Seleccione") || cboNivelE.getSelectedItem().equals("Seleccione") || 
                cboEstado.getSelectedItem().equals("Seleccione") || cboGenero.getSelectedItem().equals("Seleccione") || cboFormaPago.getSelectedItem().equals("Seleccione") || 
                cboNacionalidad.getSelectedItem().equals("Seleccione")) {
            ImageIcon icono = new ImageIcon(getClass().getResource("/img/Error.png"));
            ImageIcon mitad_0 = new ImageIcon(icono.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(null, "No hay la cantidad de informacion suficiente para realizar el registro!","ERROR",JOptionPane.PLAIN_MESSAGE,mitad_0);
        }else{
            String Nombre, Nacionalidad, CC, ID, Nivel_Academico, Genero, FechaIng, FechaRet, Telefono, Correo, 
                    EstadoCivil, Direccion,NumeroCuenta,Salario,TipoContrato,Estado,FormaPago,Turno,Nota,FechaNac;
            ID = txtCodigo.getText();
            Nombre = txtNombreC.getText();
            CC = txtCC.getText();
            Nacionalidad = (String) cboNacionalidad.getSelectedItem();
            Nivel_Academico = (String) cboNivelE.getSelectedItem();
            FechaIng = txtFechaIng.getText();
            FechaNac = txtFechaNac.getText();
            Genero = (String) cboGenero.getSelectedItem();
            Telefono = txtTelefono.getText();
            Correo = txtCorreo.getText();
            EstadoCivil = (String) cboEstadoCivil.getSelectedItem();
            Direccion = txtDireccion.getText();
            NumeroCuenta = txtNumCuenta.getText();
            Salario = txtSalario.getText();
            TipoContrato = (String) cboTipoContrato.getSelectedItem();
            Estado = (String) cboEstado.getSelectedItem();
            FormaPago = (String) cboFormaPago.getSelectedItem();
            Turno = (String) cboTurno.getSelectedItem();
            Nota = txtNotas.getText();
            ImageIcon icono = new ImageIcon(getClass().getResource("/img/Question.png"));
            ImageIcon mitad_0 = new ImageIcon(icono.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
            int V = JOptionPane.showConfirmDialog(rootPane, "Su registro se guardara con los siguientes datos:  "
                    + "\n-------------Datos Generales--------------"
                    + "\nID: " + ID
                    + "\nNombre Completo: " + Nombre
                    + "\nGenero: " + Genero
                    + "\nDocumento Legal: " + CC
                    + "\nFecha De Nacimiento: " + FechaNac
                    + "\nNacionalidad: " + Nacionalidad
                    + "\nNivel Educativo: " + Nivel_Academico
                    + "\nDireccion: " + Direccion
                    + "\nTelefono: " + Telefono
                    + "\nCorreo: " + Correo
                    + "\nEstado Civil: " + EstadoCivil
                    + "\n-------------Datos Laborales--------------"
                    + "\nFecha-Ingreso: " + FechaIng
                    + "\nNumero de cuenta: " + NumeroCuenta
                    + "\nSalario: " + Salario
                    + "\nTipo de Contrato: " + TipoContrato
                    + "\nForma de Pago: " + FormaPago
                    + "\nEstado: " + Estado
                    + "\nTurno: " + Turno
                    + "\nNota: " + Nota
                    + "\n¿Es correcto?: ","AVISO",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE, mitad_0);
            if (V == JOptionPane.YES_OPTION) {
                String Tabla = "Registros_Emp";
                db.RecognizeDATABASE("root", "", "Spa_MascotasAV");
                db.InsertData1(Tabla, ID, Nombre, CC, Nacionalidad, Nivel_Academico, FechaNac, Telefono, Correo, Genero, EstadoCivil, Direccion, FechaIng, NumeroCuenta, Salario, TipoContrato, Estado, FormaPago, Turno, Nota);
                txtCC.setText("");
                txtCodigo.setText("");
                txtCorreo.setText("");
                txtDireccion.setText("");
                txtFechaIng.setText("");
                txtFechaNac.setText("");
                txtNombreC.setText("");
                txtNotas.setText("");
                txtNumCuenta.setText("");
                txtSalario.setText("");
                txtTelefono.setText("");
                cboEstado.setSelectedIndex(0);
                cboEstadoCivil.setSelectedIndex(0);
                cboFormaPago.setSelectedIndex(0);
                cboGenero.setSelectedIndex(0);
                cboNacionalidad.setSelectedIndex(0);
                cboNivelE.setSelectedIndex(0);
                cboTipoContrato.setSelectedIndex(0);
                cboTurno.setSelectedIndex(0);
                BtnSalir.setEnabled(true);
            }

        }
    }//GEN-LAST:event_BtnRegistrarActionPerformed

    private void BtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioActionPerformed
        bdC.primero();
        rellenarCampos();
        HabilitarIn(false);
        BtnRegistrar.setEnabled(false);
        BtnModificar.setEnabled(false);
        BtnSelect.setEnabled(true);
        BtnCancelar.setEnabled(true);
    }//GEN-LAST:event_BtnInicioActionPerformed

    private void BtnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnteriorActionPerformed
        bdC.anterior();
        rellenarCampos();
        HabilitarIn(false);
        BtnRegistrar.setEnabled(false);
        BtnModificar.setEnabled(false);
        BtnSelect.setEnabled(true);
        BtnCancelar.setEnabled(true);
    }//GEN-LAST:event_BtnAnteriorActionPerformed

    private void BtnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSiguienteActionPerformed
        bdC.siguiente();
        rellenarCampos();
        HabilitarIn(false);
        BtnRegistrar.setEnabled(false);
        BtnModificar.setEnabled(false);
        BtnSelect.setEnabled(true);
        BtnCancelar.setEnabled(true);
    }//GEN-LAST:event_BtnSiguienteActionPerformed

    private void BtnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUltimoActionPerformed
        bdC.ultimo();
        rellenarCampos();
        HabilitarIn(false);
        BtnRegistrar.setEnabled(false);
        BtnModificar.setEnabled(false);
        BtnSelect.setEnabled(true);
        BtnCancelar.setEnabled(true);
    }//GEN-LAST:event_BtnUltimoActionPerformed

    private void BtnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSelectActionPerformed
        HabilitarIn(true);
        txtCodigo.setEnabled(false);
        BtnModificar.setEnabled(true);
        BtnSelect.setEnabled(false);
        BtnCancelar.setEnabled(true);
        this.setTitle("CRUD de Empleados");
        lblfrm.setText(this.getTitle());
    }//GEN-LAST:event_BtnSelectActionPerformed

    private void BtnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModificarActionPerformed
        if (txtCodigo.getText().equals("") || txtCodigo.getText().equals("Ingrese un ID") || cboEstadoCivil.getSelectedItem().equals("Seleccione") || cboNivelE.getSelectedItem().equals("Seleccione")
                || cboEstado.getSelectedItem().equals("Seleccione") || cboGenero.getSelectedItem().equals("Seleccione") || cboFormaPago.getSelectedItem().equals("Seleccione")
                || cboNacionalidad.getSelectedItem().equals("Seleccione")) {
            ImageIcon icono = new ImageIcon(getClass().getResource("/img/Error.png"));
            ImageIcon mitad_0 = new ImageIcon(icono.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(null, "No hay la cantidad de informacion suficiente para modificar el registro!", "ERROR", JOptionPane.PLAIN_MESSAGE, mitad_0);
        } else {
            String Nombre, Nacionalidad, CC, ID, Nivel_Academico, Genero, FechaIng, FechaRet, Telefono, Correo,
                    EstadoCivil, Direccion, NumeroCuenta, Salario, TipoContrato, Estado, FormaPago, Turno, Nota, FechaNac;
            ID = txtCodigo.getText();
            Nombre = txtNombreC.getText();
            CC = txtCC.getText();
            Nacionalidad = (String) cboNacionalidad.getSelectedItem();
            Nivel_Academico = (String) cboNivelE.getSelectedItem();
            FechaIng = txtFechaIng.getText();
            FechaNac = txtFechaNac.getText();
            Genero = (String) cboGenero.getSelectedItem();
            Telefono = txtTelefono.getText();
            Correo = txtCorreo.getText();
            EstadoCivil = (String) cboEstadoCivil.getSelectedItem();
            Direccion = txtDireccion.getText();
            NumeroCuenta = txtNumCuenta.getText();
            Salario = txtSalario.getText();
            TipoContrato = (String) cboTipoContrato.getSelectedItem();
            Estado = (String) cboEstado.getSelectedItem();
            FormaPago = (String) cboFormaPago.getSelectedItem();
            Turno = (String) cboTurno.getSelectedItem();
            Nota = txtNotas.getText();
            ImageIcon icono = new ImageIcon(getClass().getResource("/img/Question.png"));
            ImageIcon mitad_0 = new ImageIcon(icono.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
            int V = JOptionPane.showConfirmDialog(rootPane, "Su registro se modificara con los siguientes datos:  "
                    + "\n-------------Datos Generales--------------"
                    + "\nID: " + ID
                    + "\nNombre Completo: " + Nombre
                    + "\nGenero: " + Genero
                    + "\nDocumento Legal: " + CC
                    + "\nFecha De Nacimiento: " + FechaNac
                    + "\nNacionalidad: " + Nacionalidad
                    + "\nNivel Educativo: " + Nivel_Academico
                    + "\nDireccion: " + Direccion
                    + "\nTelefono: " + Telefono
                    + "\nCorreo: " + Correo
                    + "\nEstado Civil: " + EstadoCivil
                    + "\n-------------Datos Laborales--------------"
                    + "\nFecha-Ingreso: " + FechaIng
                    + "\nNumero de cuenta: " + NumeroCuenta
                    + "\nSalario: " + Salario
                    + "\nTipo de Contrato: " + TipoContrato
                    + "\nForma de Pago: " + FormaPago
                    + "\nEstado: " + Estado
                    + "\nTurno: " + Turno
                    + "\nNota: " + Nota
                    + "\n¿Es correcto?: ", "AVISO", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, mitad_0);
            if (V == JOptionPane.YES_OPTION) {
                String Tabla = "Registros_Emp";
                db.RecognizeDATABASE("root", "", "Spa_MascotasAV");
                db.UpdateData1(Tabla, ID, Nombre, CC, Nacionalidad, Nivel_Academico, FechaNac, Telefono, Correo, Genero, EstadoCivil, Direccion, FechaIng, NumeroCuenta, Salario, TipoContrato, Estado, FormaPago, Turno, Nota);
                txtCC.setText("");
                txtCodigo.setText("");
                txtCorreo.setText("");
                txtDireccion.setText("");
                txtFechaIng.setText("");
                txtFechaNac.setText("");
                txtNombreC.setText("");
                txtNotas.setText("");
                txtNumCuenta.setText("");
                txtSalario.setText("");
                txtTelefono.setText("");
                cboEstado.setSelectedIndex(0);
                cboEstadoCivil.setSelectedIndex(0);
                cboFormaPago.setSelectedIndex(0);
                cboGenero.setSelectedIndex(0);
                cboNacionalidad.setSelectedIndex(0);
                cboNivelE.setSelectedIndex(0);
                cboTipoContrato.setSelectedIndex(0);
                cboTurno.setSelectedIndex(0);
                BtnSalir.setEnabled(true);
                HabilitarIn(true);
                BtnSelect.setEnabled(false);
                BtnCancelar.setEnabled(false);
                BtnModificar.setEnabled(false);
                BtnRegistrar.setEnabled(true);
                setTitle("CRUD de Empleados");
                lblfrm.setText(this.getTitle());
            }

        }
    }//GEN-LAST:event_BtnModificarActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        HabilitarIn(true);
        txtCC.setText("");
        txtCodigo.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtFechaIng.setText("");
        txtFechaNac.setText("");
        txtNombreC.setText("");
        txtNotas.setText("");
        txtNumCuenta.setText("");
        txtSalario.setText("");
        txtTelefono.setText("");
        cboEstado.setSelectedIndex(0);
        cboEstadoCivil.setSelectedIndex(0);
        cboFormaPago.setSelectedIndex(0);
        cboGenero.setSelectedIndex(0);
        cboNacionalidad.setSelectedIndex(0);
        cboNivelE.setSelectedIndex(0);
        cboTipoContrato.setSelectedIndex(0);
        cboTurno.setSelectedIndex(0);
        BtnSelect.setEnabled(false);
        BtnCancelar.setEnabled(false);
        BtnModificar.setEnabled(false);
        BtnRegistrar.setEnabled(true);
        setTitle("CRUD de Empleados");
        lblfrm.setText(this.getTitle());
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        String CODIGO = txtCodigo.getText();
        if (txtCodigo.getText().equals("Ingrese ID del registro que desea eliminar o buscar") || txtCodigo.getText().isEmpty()) {
            ImageIcon foto_1 = new ImageIcon(getClass().getResource("/img/Error.png"));
            ImageIcon mitad_1 = new ImageIcon(foto_1.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(null, "El campo Codigo esta vacio!", "ERROR", JOptionPane.PLAIN_MESSAGE, mitad_1);
            } else {
            db.RecognizeDATABASE("root", "", "Spa_MascotasAV");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "Spa_MascotasAV", "root", "");
                String Sentencia = "SELECT * FROM Registros_Emp WHERE CODIGO = \"" + CODIGO + "\"";
                Statement ST = Conexion.createStatement();
                ResultSet RS;
                RS = ST.executeQuery(Sentencia);
                if (RS.next()) {
                    ImageIcon icono = new ImageIcon(getClass().getResource("/img/Question.png"));
                    ImageIcon mitad_0 = new ImageIcon(icono.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
                    int V = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar el registro con el CODIGO: " + CODIGO + " ?", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, mitad_0);
                    if (V == JOptionPane.YES_OPTION) {
                        db.RecognizeDATABASE("root", "", "Spa_MascotasAV");
                        db.deleteRecord1("Registros_Emp", CODIGO);}
                } else {
                    ImageIcon foto_1 = new ImageIcon(getClass().getResource("/img/Error1.png"));
                    ImageIcon mitad_1 = new ImageIcon(foto_1.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_DEFAULT));
                    JOptionPane.showMessageDialog(null, "El registro con el CODIGO:" + CODIGO + " No existe en la base de datos", "ERROR", JOptionPane.PLAIN_MESSAGE, mitad_1);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error en la busqueda de datos" + ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrmRegistroEmp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void txtCodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusGained
        if (txtCodigo.getText().equals("Ingrese un Codigo")) {
            txtCodigo.setText("");
            txtCodigo.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtCodigoFocusGained

    private void txtCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusLost
        if (txtCodigo.getText().equals("")) {
            txtCodigo.setText("Ingrese un Codigo");
            txtCodigo.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtCodigoFocusLost

    private void txtNombreCFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreCFocusGained
        if (txtNombreC.getText().equals("Ingrese su nombre completo")) {
            txtNombreC.setText("");
            txtNombreC.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtNombreCFocusGained

    private void txtNombreCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreCFocusLost
        if (txtNombreC.getText().equals("")) {
            txtNombreC.setText("Ingrese su nombre completo");
            txtNombreC.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtNombreCFocusLost

    private void txtCCFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCCFocusGained
        if (txtCC.getText().equals("Digite su cedula")) {
            txtCC.setText("");
            txtCC.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtCCFocusGained

    private void txtCCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCCFocusLost
        if (txtCC.getText().equals("")) {
            txtCC.setText("Digite su cedula");
            txtCC.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtCCFocusLost

    private void txtFechaNacFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaNacFocusGained
        if (txtFechaNac.getText().equals("Digite fecha de nacimiento")) {
            txtFechaNac.setText("");
            txtFechaNac.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtFechaNacFocusGained

    private void txtFechaNacFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaNacFocusLost
        if (txtFechaNac.getText().equals("")) {
            txtFechaNac.setText("Digite fecha de nacimiento");
            txtFechaNac.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtFechaNacFocusLost

    private void txtTelefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelefonoFocusGained
        if (txtTelefono.getText().equals("Ingrese numero de telefono")) {
            txtTelefono.setText("");
            txtTelefono.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtTelefonoFocusGained

    private void txtTelefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelefonoFocusLost
        if (txtTelefono.getText().equals("")) {
            txtTelefono.setText("Ingrese numero de telefono");
            txtTelefono.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtTelefonoFocusLost

    private void txtCorreoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusGained
        if (txtCorreo.getText().equals("Correo electronico")) {
            txtCorreo.setText("");
            txtCorreo.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtCorreoFocusGained

    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost
        if (txtCorreo.getText().equals("")) {
            txtCorreo.setText("Correo electronico");
            txtCorreo.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtCorreoFocusLost

    private void txtDireccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDireccionFocusGained
        if (txtDireccion.getText().equals("Ingrese su Direccion")) {
            txtDireccion.setText("");
            txtDireccion.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtDireccionFocusGained

    private void txtDireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDireccionFocusLost
        if (txtDireccion.getText().equals("")) {
            txtDireccion.setText("Ingrese su Direccion");
            txtDireccion.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtDireccionFocusLost

    private void txtFechaIngFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaIngFocusGained
        if (txtFechaIng.getText().equals("Digite fecha de ingreso")) {
            txtFechaIng.setText("");
            txtFechaIng.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtFechaIngFocusGained

    private void txtFechaIngFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaIngFocusLost
        if (txtFechaIng.getText().equals("")) {
            txtFechaIng.setText("Digite fecha de ingreso");
            txtFechaIng.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtFechaIngFocusLost

    private void txtNumCuentaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumCuentaFocusGained
        if (txtNumCuenta.getText().equals("Ingrese un numero de cuenta")) {
            txtNumCuenta.setText("");
            txtNumCuenta.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtNumCuentaFocusGained

    private void txtNumCuentaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumCuentaFocusLost
        if (txtNumCuenta.getText().equals("")) {
            txtNumCuenta.setText("Ingrese un numero de cuenta");
            txtNumCuenta.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtNumCuentaFocusLost

    private void txtSalarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSalarioFocusGained
        if (txtSalario.getText().equals("Digite el salario")) {
            txtSalario.setText("");
            txtSalario.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtSalarioFocusGained

    private void txtSalarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSalarioFocusLost
        if (txtSalario.getText().equals("")) {
            txtSalario.setText("Digite el salario");
            txtSalario.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtSalarioFocusLost

    private void txtNotasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNotasFocusGained
        if (txtNotas.getText().equals("Digite nota al empleado")) {
            txtNotas.setText("");
            txtNotas.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtNotasFocusGained

    private void txtNotasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNotasFocusLost
        if (txtNotas.getText().equals("")) {
            txtNotas.setText("Digite nota al empleado");
            txtNotas.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtNotasFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BarraModificacion;
    private javax.swing.JPanel BarraNavegacion;
    private javax.swing.JButton BtnAnterior;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnInicio;
    private javax.swing.JButton BtnModificar;
    private javax.swing.JButton BtnRegistrar;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JButton BtnSelect;
    private javax.swing.JButton BtnSiguiente;
    private javax.swing.JButton BtnUltimo;
    private javax.swing.JPanel JPDatosG;
    private javax.swing.JPanel JPDatosL;
    private javax.swing.JTabbedPane JTPestañas;
    private javax.swing.JPanel Linea;
    private javax.swing.JPanel PanelStatusBar;
    private javax.swing.JLabel Reloj;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JComboBox<String> cboEstadoCivil;
    private javax.swing.JComboBox<String> cboFormaPago;
    private javax.swing.JComboBox<String> cboGenero;
    private javax.swing.JComboBox<String> cboNacionalidad;
    private javax.swing.JComboBox<String> cboNivelE;
    private javax.swing.JComboBox<String> cboTipoContrato;
    private javax.swing.JComboBox<String> cboTurno;
    private javax.swing.JLabel lblCC;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEstadoC;
    private javax.swing.JLabel lblEstadoT;
    private javax.swing.JLabel lblFechaIn;
    private javax.swing.JLabel lblFechaNac;
    private javax.swing.JLabel lblFormaP;
    private javax.swing.JLabel lblFormulario;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblNE;
    private javax.swing.JLabel lblNacionalidad;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNombreC;
    private javax.swing.JLabel lblNotas;
    private javax.swing.JLabel lblNumCB;
    private javax.swing.JLabel lblSalario;
    private javax.swing.JLabel lblTelef;
    private javax.swing.JLabel lblTipoC;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTurno;
    public static javax.swing.JLabel lblUsuarioLogin;
    private javax.swing.JLabel lblfrm;
    private javax.swing.JTextField txtCC;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFechaIng;
    private javax.swing.JTextField txtFechaNac;
    private javax.swing.JTextField txtNombreC;
    private javax.swing.JTextField txtNotas;
    private javax.swing.JTextField txtNumCuenta;
    private javax.swing.JTextField txtSalario;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
