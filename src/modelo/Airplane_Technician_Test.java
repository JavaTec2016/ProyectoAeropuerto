package modelo;

import java.lang.reflect.Field;

public class Airplane_Technician_Test extends ModeloBD {
    String SSN;
    int Number_FAA;
    int Registration_Number;
    String Model_Number;
    String Test_Begin;
    short Hours_Spent;
    short Score_Recieved;

    public Airplane_Technician_Test(){}
    public Airplane_Technician_Test(String SSN, String model_Number, int registration_Number, int number_FAA, String test_Begin, short hours_Spent, short score_Recieved) {
        this.SSN = SSN;
        Model_Number = model_Number;
        Registration_Number = registration_Number;
        Number_FAA = number_FAA;
        Test_Begin = test_Begin;
        Hours_Spent = hours_Spent;
        Score_Recieved = score_Recieved;
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
        return new String[]{"SSN", "Numero de modelo", "Numero de registro", "Numero de prueba FAA", "Fecha de inicio", "Horas tomadas", "Puntuacion obtenida"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{
                "JTextField", "JTextField", "JTextField", "JTextField", "JTextField", "JTextField"
        };
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"CHAR", "int", "int", "CHAR", "DATE", "short", "short"};
    }
    public static boolean[] obtenerNoNulos(){
        return new boolean[]{true, true, true, true, true, true, true};
    }
    //-1 numero
    //-2 date
    public static int[] obtenerLongitudes(){
        return new int[]{20, -1, -1, 5, -2, -1, -1};
    }
}
