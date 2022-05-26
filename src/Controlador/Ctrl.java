package Controlador;

/**
 *
 * @author ANDRES FELIPE VIVEROS ALBAN - S3AN
 */
import java.sql.*;
import javax.swing.JOptionPane;

public class Ctrl {

    private static Connection Conexion;
    String strSentenciaSQL = "";
    String strCadenaConexion = "";
    String strOrdenarPor = "";
    String strCampoLLave = "";
    String strTabla = "";
    String[] strArrayCamposLlave;
    int intArrayCamposLlave[];

//Los posibles tipos del campo llave son cadena y entero
//Por defecto el campo llave es de tipo cadena
    String strTipoCampoLlave = "entero";

    public static int intTotalRegistros = 0, intFilaActual = 0;
    String strDriverMySQL = "com.mysql.jdbc.Driver";

    String strManejador = "mysql";

    String strBaseDatos = "spa_mascotasav";
    String strServidor = "localhost";

    String strUsuario = "root";

    String strPassword = "";

    String strControlador = strDriverMySQL;

    String strPuerto = "3306";

    private static Connection con = null;
    public static Statement st;
    public static ResultSet rs;

    public Ctrl() {
    }
//se mueve a la primera fila y devuelve true si se pudo, false sino se pudo ir a la primera fila

    public boolean primero() {

        try {
            intFilaActual = 0;

            if (strTipoCampoLlave.equals("cadena")) {
                rs = ejecutarConsulta("SELECT * FROM " + strTabla + " WHERE " + strCampoLLave + " = '"
                        + strArrayCamposLlave[intFilaActual] + "'");
            } else {
                rs = ejecutarConsulta("SELECT * FROM " + strTabla + " WHERE " + strCampoLLave + " = "
                        + intArrayCamposLlave[intFilaActual]);
            }

            if (rs.next()) {
                return true;

            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }

    }

//se mueve a la anterior fila y devuelve true si se pudo, false sino se pudo ir a la anterior fila
    public boolean anterior() {
        try {
            intFilaActual--;
            if (intFilaActual < 0) {
                intFilaActual = 0;

            }

            if (strTipoCampoLlave.equals("cadena")) {
                rs = ejecutarConsulta("SELECT * FROM " + strTabla + " WHERE " + strCampoLLave + " = '"
                        + strArrayCamposLlave[intFilaActual] + "'");
            } else {
                rs = ejecutarConsulta("SELECT * FROM " + strTabla + " WHERE " + strCampoLLave + " = "
                        + intArrayCamposLlave[intFilaActual]);

            }

            if (rs.next()) {
                return true;

            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
    }

//se mueve a la siguiente fila y devuelve true si se pudo, false sino se pudo ir a la siguiente fila
    public boolean siguiente() {

        try {
            intFilaActual++;

            if (intFilaActual > intTotalRegistros -1) {
                intFilaActual = intTotalRegistros -1;
            }

            if (strTipoCampoLlave.equals("cadena")) {
                rs = ejecutarConsulta("SELECT * FROM " + strTabla + " WHERE " + strCampoLLave + " = '"
                        + strArrayCamposLlave[intFilaActual] + "'");
            } else {
                rs = ejecutarConsulta("SELECT * FROM " + strTabla + " WHERE " + strCampoLLave + " = "
                        + intArrayCamposLlave[intFilaActual]);
            }

            if (rs.next()) {
                return true;

            }else{
            JOptionPane.showMessageDialog(null, "Fila vacia o no encontrada");
            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
    }

//se mueve a la ultima fila y devuelve true si se pudo, false sino se pudo ir a la ultima fila
    public boolean ultimo() {

        try {
            intFilaActual = intTotalRegistros - 1;

            if (strTipoCampoLlave.equals("cadena")) {
                rs = ejecutarConsulta("SELECT * FROM " + strTabla + " WHERE " + strCampoLLave + " = '"
                        + strArrayCamposLlave[intFilaActual] + "'");
            } else {
                rs = ejecutarConsulta("SELECT * FROM " + strTabla + " WHERE " + strCampoLLave + " = "
                        + intArrayCamposLlave[intFilaActual]);

            }

            if (rs.next()) {
                return true;

            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }

    }

//Obtiene el controlador del manejador mysql o postgres
    public String obtenerDriverManejador(String manejador) {

        if (manejador.equals("mysql")) //Si el manejador es mysql
        {

            return strDriverMySQL;
        }

//en caso d k no sea ninguno devuelve el de MySQL
        return strDriverMySQL;
    }

//Conecta a la BD con los datos por default
    public boolean conectarBD() throws SQLException {
        try {
            return conectarServidor(strManejador, strServidor, strUsuario, strPassword, strBaseDatos, strControlador);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//Conecta a la BD con los datos del usuario, bd y password especificados, para los demas toma los k estan por default
    public boolean conectarBD(String usuario, String password, String BD)// throws SQLException
    {

        boolean ress = false;

        try {

            strUsuario = usuario;
            strPassword = password;
            strBaseDatos = BD;
            ress = conectarServidor(strManejador, strServidor, usuario, password, BD, strControlador);
            return ress;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }

    }

//permite especificar el campo por el que se van a ordenar los registros
    public void ordenarPor(String campo) {
        strOrdenarPor = campo;

    }

//Conecta a la BD con los datos especificados, para los demas toma los k estan por default
    public boolean conectarBD(String usuario, String password, String BD, String manejador) //throws SQLException
    {
        boolean ress;
        try {
            strControlador = obtenerDriverManejador(manejador);
            strUsuario = usuario;
            strPassword = password;
            strBaseDatos = BD;
            strManejador = manejador;
            ress = conectarServidor(manejador, strServidor, usuario, password, BD, strControlador);
            return ress;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//Realiza una conexion a sqlserver, mysql o postgres con los datos especificados
    public boolean conectarServidor(String manejador, String servidor, String usuario, String pw, String bd, String driver)
            throws SQLException {

        if (manejador.equals("mysql")) //Si el manejador es mysql
        {
            try {

                Class.forName(driver);
                strCadenaConexion = "jdbc:mysql://" + servidor + "/" + bd;

                con = DriverManager.getConnection(strCadenaConexion, usuario, pw);

                if (con == null) {
                    return false;

                }

                return true;

            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
                return false;

            } catch (Exception e) {
                e.printStackTrace();
                return false;

            }

        }

        return false;

    }

//Ejecuta un SELECT y devuelve el Resultset con los resultados
    public ResultSet ejecutarConsulta(String cadSQ) throws SQLException {
        rs = null;
        if (!strOrdenarPor.equals("")) {

            cadSQ += " ORDER BY " + strOrdenarPor;

        }

        try {
            rs = st.executeQuery(cadSQ);
            return rs;

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            return rs;

        }
    }

    public void obtenerCamposLlave() {
        obtenerCamposLlave(strTabla);
    }

//Si el campo llave es de tipo entero guarda las llaves primarias en un arreglo de enteros
//Si el campo Ilave es de tipo cadena guarda las llaves primarias en un arreglo de Strings
    public void obtenerCamposLlave(String tabla) {
        int c = 0;
        try {
            rs = ejecutarConsulta("SELECT * FROM " + tabla);

            while (rs.next()) {

                c++;
            }
            intTotalRegistros = c;
            strArrayCamposLlave = new String[intTotalRegistros];

            intArrayCamposLlave = new int[intTotalRegistros];

            cerrar(rs); //Cerrar consulta anterior
//Ejecutar nuevamente la consulta
            rs = ejecutarConsulta("SELECT * FROM " + tabla);

            rs.next();  //Moverse al primer registro

            c = 0;
            do { //determina el tipo del campo Ilave y en base a eso guarda en el array correspondiente

                if (strTipoCampoLlave.equals("cadena")) {

                    strCampoLLave = "Id";

                    strArrayCamposLlave[c] = rs.getString(strCampoLLave);
                    
                } else {

                    intArrayCamposLlave[c] = rs.getInt(strCampoLLave);
                    
                }
                
                c++;

            } while (rs.next());

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();

        }
    }

// Cierra un objeto Resultset
    public static void cerrar(ResultSet rs) {
        try {
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

// cierra un objeto Statemet
    public static void cerrar(Statement st) {
        try {
            st.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

// Cierra un objeto Connection
    public static void cerrar(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//Permite especificar cual es el campo llave de la tabla
    public void setCampoLlave(String campo) {
        strCampoLLave = campo;

    }

//Permite especificar cual es el campo llave y su tipo, de la tabla
    public void setCampoLlave(String campo, String tipo) {
        strCampoLLave = campo;
        strTipoCampoLlave = tipo;

    }

    public void setTipoCampoLlave(String tipo) {
        strTipoCampoLlave = tipo;

    }

//Especifica la tabla sobre la k se va a trabajar
    public void setTabla(String tabla) {

        try {
            strTabla = tabla;

            st = con.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }
} //Fin de la clase

