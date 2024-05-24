package modelo;

import java.lang.reflect.Field;

public class Model implements Registrable {
    String Model_Number;
    int Capacity;
    int Weight;

    public Model(String model_Number, int capacity, int weight) {
        Model_Number = model_Number;
        Capacity = capacity;
        Weight = weight;
    }

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
}
