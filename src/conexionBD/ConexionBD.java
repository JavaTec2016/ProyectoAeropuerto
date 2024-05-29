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
            String URL = "jdbc:mysql://localhost:3306/Aeropuerto";

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
    public int ejecutarInstruccionDML(String instruccionSQL){
        try {

            ste = conexion.createStatement();
            ste.executeUpdate(instruccionSQL);
            return 0;
        }catch (SQLIntegrityConstraintViolationException integ){

            System.out.println("Error: integridad de datos: " + integ.getErrorCode() + " > " + integ.getSQLState());
            integ.printStackTrace();

            return integ.getErrorCode();
        }
        catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error en la instruccion");
            e.printStackTrace();
            return -1;
        }

    }
    //metodo para Consultas
    public ResultSet ejecutarConsultaSQL(String instruccionSQL){
        rs = null;
        System.out.println(instruccionSQL);
        try {

            ste = conexion.createStatement();
            rs = ste.executeQuery(instruccionSQL);

        } catch (SQLException e) {
            System.out.println("Error en la instruccion: " + e.getErrorCode());
            e.printStackTrace();

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