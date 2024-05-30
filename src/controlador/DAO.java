package controlador;

import conexionBD.ConexionBD;
import modelo.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {
    ConexionBD conexion = ConexionBD.obtenerConector();

    public static DAO d = new DAO();
    //metodo puercote, identifica y registra dinamicamente los diferentes objetos que usa la BD
    //0: operacion exitosa
    //1: un campo no existe para el objeto dado
    //2: un campo es inaccesible para la clase dada
    public int agregarUniversal(Registrable registrable){
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
        return conexion.ejecutarInstruccionDML(sql);
        //nuestra sentencia esta lista, le falta una BD
        //conexion.ejecutarInstruccionDML(sql);

    }
    //recibe una cantidad cualquiera de filtros con datos obtenidos y formateados desde la interfaz
    //por lo que no es necesario hacer cochinadas con clases
    public int EliminarUniversal(String tabla, String ...filtros){
        String sql = "DELETE FROM " + tabla + " WHERE ";
        for(String filtro : filtros){
            sql += filtro+" AND ";
        }
        //sobran 1 espacio, 3 caracteres AND y 1 espacio, 5 caracteres
        int corte = sql.length()-5;
        sql = sql.substring(0, corte);
        System.out.println(sql);

        return conexion.ejecutarInstruccionDML(sql);
    }

    //la interfaz toma los datos, genera un objeto y lo pasa al metodo
    public int actualizarUniversal(Registrable registrable, int primarias){
        //lectura del objeto, similar al agregarUniversal
        String tipo = registrable.getClass().getName();
        //indica que campos se dejan intactos y que campos se establecen en nulo
        boolean[] nnl = registrable.noNulos();
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
        String restr = " WHERE ";
        if(primarias == 0) {
            System.out.println("no se establecieron claves primarias a excluir");
        }
        //formateo de las restricciones de claves primarias
        int i = 0;
        for(; i < primarias; i++){
            String campo = camposNombres[i];
            String dato = camposTipos[i];
            String sqlRes = obtenerValor(dato, campo, registrable, tipo);
            System.out.println("ID: " + sqlRes);
            if(sqlRes.equalsIgnoreCase("NULL")) return 1;
            restr += campo+"="+sqlRes+" AND ";
        }
        restr = restr.substring(0, restr.length()-5);

        //establecer la edicion del resto de campos
        int cambios = 0;
        for(; i < camposTipos.length; i++){
            String campo = camposNombres[i];
            String dato = camposTipos[i];
            String sqlValor = obtenerValor(dato, campo, registrable, tipo);
            //si es nulo se queda sin cambios
            //System.out.println(sqlValor + " valor");
            if(sqlValor.equals("NULL") && nnl[i]) continue;
            sql += campo + "="+sqlValor+", ";
            cambios++;
        }
        System.out.println("DAO actualizar, cambios: " + cambios);
        //si no hay cambios no hay nada que hacer;
        if(cambios == 0) return 1;
        int corte = sql.length()-2;
        sql = sql.substring(0, corte);

        sql+=restr;
        //la instrucción está lista para ejecutarse
        System.out.println(sql);
        return conexion.ejecutarInstruccionDML(sql);

    }
    public String obtenerValor(String dato, String campo, Registrable registrable, String tipo){
        Object valor = null;
        try {
            Field f = registrable.getClass().getDeclaredField(campo);
            f.setAccessible(true);//con esto se pueden obtener los datos representados en el field
            valor = f.get(registrable);
        } catch (NoSuchFieldException e) {
            System.out.println(campo+ " > El campo que se busca no existe. " + tipo);
            return null;
        } catch (IllegalAccessException e) {
            System.out.println(campo+ " > El campo no es accesible. " + tipo);
            return null;
        }
        if(valor == null) return "NULL";
        String sqlValor = valor.toString();
        if(dato.equals("java.lang.String")){
            sqlValor = "'"+sqlValor+"'";
        }
        return sqlValor;
    }
    public ArrayList<Registrable> consultarUniversal(String tabla){
        ArrayList<Registrable> registros = new ArrayList<Registrable>();

        String sql = "SELECT * FROM " + tabla;

        ResultSet rs = conexion.ejecutarConsultaSQL(sql);
        //argumentos para el registrable;
        Object[] args;
        int i = 0;
        try {
            //saber que clase es
            Class<?> modelo = Class.forName("modelo."+tabla);


            //saber que constructor tiene la clase
            Constructor<?> cons = modelo.getConstructors()[0];

            //iniciar un objeto con espacios segun la cantidad de parametros
            args = new Object[cons.getParameterCount()];

            rs.next();
            do{
                for(int j = 0; j < args.length; j++){
                    //rellenar los argumentos

                    args[j] = rs.getObject(j+1);
                    //System.out.println( rs.getObject(j+1).getClass() );
                    //resulta que si le metes un date a un constructor con strings truena
                    if(rs.getObject(j+1).getClass().getName().equals("java.sql.Date")){

                        args[j] = rs.getObject(j+1).toString();
                    }
                }
                //inicializar el objeto como registrable
                Registrable o = (Registrable) cons.newInstance(args);

                //y agregarlo al output
                registros.add(o);

            }while (rs.next());

        } catch (SQLException e) {
            System.out.println("Error en consulta SQL");
            //throw new RuntimeException(e);
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("modelo."+tabla+" no existe");
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            System.out.println("Error al invocar el constructor de modelo."+tabla);
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            System.out.println("modelo."+tabla+" no puede ser instanciado");
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            System.out.println("modelo."+tabla+" no puede ser accesado");
            throw new RuntimeException(e);
        }
        return registros;
    }
    public ArrayList<Registrable> consultarUniversal(String tabla, String filtro){
        ArrayList<Registrable> registros = new ArrayList<Registrable>();

        String sql = "SELECT * FROM " + tabla + " WHERE " + filtro;

        ResultSet rs = conexion.ejecutarConsultaSQL(sql);
        //argumentos para el registrable;
        Object[] args;
        int i = 0;
        try {
            //saber que clase es
            Class<?> modelo = Class.forName("modelo."+tabla);


            //saber que constructor tiene la clase
            Constructor<?> cons = modelo.getConstructors()[0];

            //iniciar un objeto con espacios segun la cantidad de parametros
            args = new Object[cons.getParameterCount()];

            rs.next();
            do{
                for(int j = 0; j < args.length; j++){
                    //rellenar los argumentos

                    args[j] = rs.getObject(j+1);
                    //System.out.println( rs.getObject(j+1).getClass() );
                    //resulta que si le metes un date a un constructor con strings truena
                    if(rs.getObject(j+1).getClass().getName().equals("java.sql.Date")){

                        args[j] = rs.getObject(j+1).toString();
                    }
                }
                //inicializar el objeto como registrable
                Registrable o = (Registrable) cons.newInstance(args);

                //y agregarlo al output
                registros.add(o);

            }while (rs.next());

        } catch (SQLException e) {
            System.out.println("Error en consulta SQL: " + e.getErrorCode());

            return null;
            //throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("modelo."+tabla+" no existe");
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            System.out.println("Error al invocar el constructor de modelo."+tabla);
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            System.out.println("modelo."+tabla+" no puede ser instanciado");
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            System.out.println("modelo."+tabla+" no puede ser accesado");
            throw new RuntimeException(e);
        }
        return registros;
    }
    public static void main(String[] args) {
        DAO d = new DAO();
        d.agregarUniversal(new Aviation_Test(2, "KARAMBOLAS", 100));
        d.agregarUniversal(new Model("DC-10", 30, 172));
        d.agregarUniversal(new Employee("Seguridad Social", "RAS", "BD", "La concepcion 3", "Lomas Ajeas", "123456", "49420056", 24, 50000, "Technician"));
        d.actualizarUniversal(
                new Employee("Seguridad Social", "RAS", "TOPICOS", "La concepcion 3", "Lomas Ajeas", "123456", "49420056", 24, 50000, "Technician"),
                1);

        d.actualizarUniversal(new Model("DC-10", 30, 172), 1);

        d.agregarUniversal(new Airplane(12345, "DC-10"));
    }
}
