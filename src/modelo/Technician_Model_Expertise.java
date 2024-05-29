package modelo;

import java.lang.reflect.Field;

public class Technician_Model_Expertise extends ModeloBD {
    String SSN;
    String Model_Number;

    public Technician_Model_Expertise(String SSN, String model_Number) {
        this.SSN = SSN;
        Model_Number = model_Number;
    }
    public Technician_Model_Expertise(){}
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

    public static String[] obtenerLabels(){
        return new String[]{"SSN", "Numero de modelo"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{
                "JTextField", "JTextField"
        };
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"CHAR", "CHAR"};
    }
    public static boolean[] obtenerNoNulos(){
        return new boolean[]{true, true};
    }

    @Override
    public boolean[] noNulos() {
        return new boolean[]{true, true};
    }

    public static int[] obtenerLongitudes(){
        return new int[]{20, 5};
    }
}
