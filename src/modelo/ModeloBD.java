package modelo;

import java.lang.reflect.Field;

public class ModeloBD implements Registrable {
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
}
