package modelo;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Aviation_Test implements Registrable {
    int Number_FAA;
    String Name;
    byte Max_Score;

    public Aviation_Test(int nf,String n, byte ms){
        Number_FAA = nf;
        Name = n;
        Max_Score = ms;
    }

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
    public static String[] obtenerComponentes(){
        return new String[]{
                "JTextField", "JTextField", "JTextField"
        };
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"int", "VARCHAR", "short"};
    }
    public static boolean[] obtenerNoNulos(){
        return new boolean[]{true, true, true};
    }
    public static int[] obtenerLongitudes(){
        return new int[]{-1, 20, -1};
    }
}
