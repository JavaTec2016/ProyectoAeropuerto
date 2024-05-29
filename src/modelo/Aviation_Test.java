package modelo;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Aviation_Test extends ModeloBD {
    Integer Number_FAA;
    String Name;
    Integer Max_Score;

    public Aviation_Test(Integer nf,String n, Integer ms){
        Number_FAA = nf;
        Name = n;
        Max_Score = ms;
    }

    public Aviation_Test(){};

    @Override
    public String toString() {
        return "Aviation_Test{" +
                "Number_FAA=" + Number_FAA +
                ", Name='" + Name + '\'' +
                ", Max_Score=" + Max_Score +
                '}';
    }
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
        return new String[]{"Numero de prueba FAA", "Nombre de prueba", "Puntuacion maxima"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{
                "JTextField", "JTextField", "JTextField"
        };
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"int", "VARCHAR", "int"};
    }
    public static boolean[] obtenerNoNulos(){
        return new boolean[]{true, true, true};
    }

    @Override
    public boolean[] noNulos() {
        return new boolean[]{true, true, true};
    }

    public static int[] obtenerLongitudes(){
        return new int[]{-1, 20, -1};
    }
}
