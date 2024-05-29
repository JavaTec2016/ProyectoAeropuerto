package modelo;

import java.lang.reflect.Field;

public class Model extends ModeloBD {
    String Model_Number;
    Integer Capacity;
    Integer Weight;

    public Model(String model_Number, Integer capacity, Integer weight){
        Model_Number = model_Number;
        Capacity = capacity;
        Weight = weight;
    }

    /**
     *
     * public Model(String model_Number, int capacity, int weight) {
     *         Model_Number = model_Number;
     *         Capacity = capacity;
     *         Weight = weight;
     *     }
     */

    public Model(){};
    @Override
    public String toString() {
        return "Model{" +
                "Model_Number='" + Model_Number + '\'' +
                ", Capacity=" + Capacity +
                ", Weight=" + Weight +
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
        return new String[]{"Numero de modelo", "Capacidad", "Peso"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{
                "JTextField", "JTextField", "JTextField"
        };
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"CHAR", "int", "int"};
    }
    public static boolean[] obtenerNoNulos(){
        return new boolean[]{true, true, true};
    }

    @Override
    public boolean[] noNulos() {
        return new boolean[]{true, true, true};
    }

    public static int[] obtenerLongitudes(){
        return new int[]{5, -1, -1};
    }
}
