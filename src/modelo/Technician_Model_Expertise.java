package modelo;

import java.lang.reflect.Field;

public class Technician_Model_Expertise implements Registrable {
    String SSN;
    String Model_Number;

    public Technician_Model_Expertise(String SSN, String model_Number) {
        this.SSN = SSN;
        Model_Number = model_Number;
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
