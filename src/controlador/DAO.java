package controlador;

import conexionBD.ConexionBD;
import modelo.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DAO {
    ConexionBD conexion = ConexionBD.obtenerConector();

    public static DAO d = new DAO();
    //metodo puercote, identifica y registra dinamicamente los diferentes objetos que usa la BD
    //0: operacion exitosa
    //1: un campo no existe para el objeto dado
    //2: un campo es inaccesible para la clase dada
    public byte agregarUniversal(Registrable registrable){
        //ArrayList extraidos = new ArrayList();
        String tipo = registrable.getClass().getName();
        for(int j = tipo.length()-1; j >= 0; j--){
            if(tipo.charAt(j) == '.'){
                tipo = tipo.substring(j+1);
                break;
            }
        }
        //obtener la informacion del registrable
        String[] camposNombres = registrable.propiedades();
        String[] camposTipos = registrable.tipoDatos();

        //preparar la sentencia sql
        System.out.println(tipo);
        String sql = "INSERT INTO " + tipo + " VALUES(";

        //reflexion truco a ver si no se rompe mi programa uyuyuy
        int i = 0;
        for(; i < camposTipos.length; i++){
            String campo = camposNombres[i];
            String dato = camposTipos[i];
            Object valor = null;
            //identificar el tipo de dato, si existe en el objeto
            try {
                Field f = registrable.getClass().getDeclaredField(campo);
                f.setAccessible(true);//con esto se pueden obtener los datos representados en el field
                valor = f.get(registrable);
            } catch (NoSuchFieldException e) {
                System.out.println(campo+ " > El campo que se busca no existe. " + tipo);
                return 1;
            } catch (IllegalAccessException e) {
                System.out.println(campo+ " > El campo no es accesible. " + tipo);
                return 2;
            }
            //la unica diferencia entre tipos de datos es que strings son valores que llevan comillas en sql
            String sqlValor = valor.toString();
            if(dato.equals("java.lang.String")){
                sqlValor = "'"+sqlValor+"'";
            }
            sql += sqlValor+", ";
            //System.out.println(valor.toString());
        }
        //al final sobran una coma y espacio en la sentencia, hay que arreglarlo
        int corte = sql.length()-2;
        sql = sql.substring(0, corte)+")";
        System.out.println(sql);
        //nuestra sentencia esta lista, le falta una BD
        //conexion.ejecutarInstruccionDML(sql);
        return 0;
    }
    //recibe una cantidad cualquiera de filtros con datos obtenidos y formateados desde la interfaz
    //por lo que no es necesario hacer cochinadas con clases
    public boolean EliminarUniversal(String tabla, String ...filtros){
        String sql = "DELETE FROM " + tabla + " ";
        for(String filtro : filtros){
            sql += filtro+", ";
        }
        //sobran un punto y espacio, arreglarlo
        int corte = sql.length()-2;
        sql = sql.substring(0, corte)+")";
        System.out.println(sql);

        return conexion.ejecutarInstruccionDML(sql);
    }

    //la interfaz toma los datos, genera un objeto y lo pasa al metodo
    public byte actualizarUniversal(Registrable registrable, String ...filtros){
        //lectura del objeto, similar al agregarUniversal
        String tipo = registrable.getClass().getName();
        for(int j = tipo.length()-1; j >= 0; j--){
            if(tipo.charAt(j) == '.'){
                tipo = tipo.substring(j+1);
                break;
            }
        }

        String[] camposNombres = registrable.propiedades();
        String[] camposTipos = registrable.tipoDatos();

        //preparar la sentencia sql
        String sql = "UPDATE " + tipo + " SET ";

        int i = 0;
        for(; i < camposTipos.length; i++){
            String campo = camposNombres[i];
            String dato = camposTipos[i];
            Object valor = null;

            try {
                Field f = registrable.getClass().getDeclaredField(campo);
                f.setAccessible(true);//con esto se pueden obtener los datos representados en el field
                valor = f.get(registrable);
            } catch (NoSuchFieldException e) {
                System.out.println(campo+ " > El campo que se busca no existe. " + tipo);
                return 1;
            } catch (IllegalAccessException e) {
                System.out.println(campo+ " > El campo no es accesible. " + tipo);
                return 2;
            }
            //la unica diferencia entre tipos de datos es que strings son valores que llevan comillas en sql
            String sqlValor = valor.toString();
            if(dato.equals("java.lang.String")){
                sqlValor = "'"+sqlValor+"'";
            }
            sql += campo + "="+sqlValor+", ";
        }
        int corte = sql.length()-2;
        if(filtros.length == 0) {
            System.out.println(tipo +" > no hay filtros para actualizar");
            return 3; //modificar la tabla entera no es comun
        }
        sql = sql.substring(0, corte)+" WHERE ";
        //agregar filtros y segundo pason de formateo
        for(String filtro : filtros){
            sql += filtro+", ";
        }
        corte = sql.length()-2;
        sql = sql.substring(0, corte);
        //la instrucción está lista para ejecutarse
        System.out.println(sql);

        //conexion.ejecutarInstruccionDML(sql);
        return 0;

    }
    public static void main(String[] args) {
        DAO d = new DAO();
        d.agregarUniversal(new Aviation_Test(2, "KARAMBOLAS", (byte)100));
        d.agregarUniversal(new Model("DC-10", 30, 172));
        d.agregarUniversal(new Employee("Seguridad Social", "RAS", "BD", "La concepcion 3", "Lomas Ajeas", "123456", "49420056", 24, 50000.0, "Technician"));
        d.actualizarUniversal(
                new Employee("Seguridad Social", "RAS", "TOPICOS", "La concepcion 3", "Lomas Ajeas", "123456", "49420056", 24, 50000.0, "Technician"),
                "SSN='Seguridad Social'", "First_Name='RAS'");

        d.actualizarUniversal(new Model("DC-10", 30, 172), "Model_Number='DC-10'");

        d.agregarUniversal(new Airplane("DC-10", 12345));
    }
}
