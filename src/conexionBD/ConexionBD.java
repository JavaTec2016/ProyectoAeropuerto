package conexionBD;
import java.sql.*;

public class ConexionBD {
    private Connection conexion;
    private Statement ste;
    private static ConexionBD conector = null;
    /*NOTA: es preferible utilizar PreparedStatement para evitar
            SQL INJECTION
     */

    private ResultSet rs;

    private ConexionBD(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //usar la BD correcta
            String URL = "jdbc:mysql://localhost:3306/BD_Escuela_Topicos_2024";

            conexion = DriverManager.getConnection(URL, "root", "chinga tu madre");

            System.out.println("yes ya casi soy ISC");

        } catch (ClassNotFoundException e) {
            //throw new RuntimeException(e);
            System.out.println("Error en el driver");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error en la URL");
        }
    }
    //metodos para operaciones ABC (Data Manipulation Language)
    public boolean ejecutarInstruccionDML(String instruccionSQL){
        boolean res = false;
        try {

            ste = conexion.createStatement();
            res = ste.executeUpdate(instruccionSQL) > 0;

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error en la instruccion");
        }
        return res;
    }
    //metodo para Consultas
    public ResultSet ejecutarConsultaSQL(String instruccionSQL){
        rs = null;

        try {

            ste = conexion.createStatement();
            rs = ste.executeQuery(instruccionSQL);

        } catch (SQLException e) {
            System.out.println("Error en la instruccion");
        }


        return rs;
    }
    //singleton
    public static ConexionBD obtenerConector(){
        if(conector == null){
            conector = new ConexionBD();
        }
        return conector;
    }
    public static void main(String[] args) {
        new ConexionBD();
    }
}