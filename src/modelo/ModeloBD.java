package modelo;

import vista.VentanaExterna;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ModeloBD implements Registrable {
    //public static ModeloBD modelombo = new ModeloBD();

    public String[] propiedades(){
        Field[] fld = getClass().getDeclaredFields();
        String[] res = new String[fld.length];
        byte i = 0;
        for(Field f : fld){
            res[i] = fld[i].getName();
            i++;

        }
        return res;
    }
    public String[] tipoDatos(){
        Field[] fld = getClass().getDeclaredFields();
        String[] res = new String[fld.length];
        byte i = 0;
        for(Field f : fld){
            res[i] = fld[i].getType().getName();
            i++;
        }
        return res;
    }
    public static String[] obtenerLabels(){return null;}
    public static String[] obtenerComponentes(){return null;}
    public static String[] obtenerTipoDato(){return null;}
    public static boolean[] obtenerNoNulos(){return null;}
    public static int[] obtenerLongitudes(){return null;}

    public Object[] obtenerValores(){
        //obtiene los campos a capturar y prepara el output
        String[] campos = propiedades();
        Object[] res = new Object[campos.length];

        for(int i = 0; i < campos.length; i++){
            String campo = campos[i];
            try {
                //toma el campo y busca su valor para este objeto

                Field f = this.getClass().getDeclaredField(campo);
                f.setAccessible(true);
                //guardalo en el output
                res[i] = f.get(this);

            } catch (NoSuchFieldException e) {
                System.out.println(campo+ " > El campo que se busca no existe. ");
                return null;
            } catch (IllegalAccessException e) {
                System.out.println(campo+ " > El campo no es accesible. ");
                return null;
            }
        }
        //retorna los valores del objeto
        return res;
    }

    public static ModeloBD instanciar(Object[] args, String className){

        try {

            Class<?> modelombo = Class.forName("modelo."+className);
            Constructor<?> constructombo = modelombo.getConstructors()[0];
            return (ModeloBD)(constructombo.newInstance(args));

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static ModeloBD instanciarVacio(String className){

        try {

            Class<?> modelombo = Class.forName("modelo."+className);
            Constructor<?> constructombo = modelombo.getConstructors()[1];
            return (ModeloBD)(constructombo.newInstance(new Object[]{}));

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
