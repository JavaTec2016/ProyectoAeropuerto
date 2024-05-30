package modelo;

import java.lang.reflect.Field;

public class Airplane_Technician_Test extends ModeloBD {

    Integer Test_Id;
    String SSN;
    Integer Number_FAA;
    Integer Registration_Number;
    String Model_Number;
    String Test_Begin;
    Integer Hours_Spent;
    Integer Score_Recieved;


    public Airplane_Technician_Test(Integer tid, String SSN, Integer number_FAA, Integer registration_Number, String model_Number, String test_Begin, Integer hours_Spent, Integer score_Recieved) {
        Test_Id = tid;
        this.SSN = SSN;
        Number_FAA = number_FAA;
        Registration_Number = registration_Number;
        Model_Number = model_Number;
        Test_Begin = test_Begin;
        Hours_Spent = hours_Spent;
        Score_Recieved = score_Recieved;
    }
    public Airplane_Technician_Test(){}
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
        return new String[]{"Numero de prueba", "SSN", "Numero de tipo de prueba FAA", "Numero de registro", "Numero de modelo", "Fecha de inicio", "Horas tomadas", "Puntuacion obtenida"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{
                "JTextField", "JTextField", "JTextField", "JTextField", "JTextField", "JTextField", "JTextField", "JTextField"
        };
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"int", "CHAR", "int", "int", "CHAR", "DATE", "int", "int"};
    }
    public static boolean[] obtenerNoNulos(){
        return new boolean[]{true, true, true, true, true, true, true, true};
    }

    @Override
    public boolean[] noNulos() {
        return new boolean[]{true,true, true, true, true, true, true, true};
    }

    //-1 numero
    //-2 date
    public static int[] obtenerLongitudes(){
        return new int[]{-1, 20, -1, -1, 5, -2, -1, -1};
    }

    public String getModel_Number() {
        return Model_Number;
    }

    public Integer getRegistration_Number() {
        return Registration_Number;
    }
}
