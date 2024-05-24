package modelo;

import java.lang.reflect.Field;

public class Airplane_Technician_Test implements Registrable {
    String SSN;
    String Model_Number;
    String Registration_Number;
    int Number_FAA;
    String Test_Begin;
    short Hours_Spent;
    short Score_Recieved;

    public Airplane_Technician_Test(){}
    public Airplane_Technician_Test(String SSN, String model_Number, String registration_Number, int number_FAA, String test_Begin, short hours_Spent, short score_Recieved) {
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
}
