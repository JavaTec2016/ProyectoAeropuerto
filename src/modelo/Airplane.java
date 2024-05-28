package modelo;

public class Airplane extends ModeloBD{

    int Registration_Number;
    String Model_Number;
    //int Capacity;
    //int Weight;


    public Airplane(int registration_Number, String model_Number) {
        Registration_Number = registration_Number;
        Model_Number = model_Number;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "Registration_Number='" + Registration_Number + '\'' +
                ", Model_Number='" + Model_Number + '\'' +
                '}';
    }
    public static String[] obtenerComponentes(){
        return new String[]{
                "JTextField", "JTextField"
        };
    }
    public static String[] obtenerLabels(){
        return new String[]{"Numero de modelo", "Numero de registro"};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"int", "CHAR"};
    }

    public static boolean[] obtenerNoNulos(){
        return new boolean[]{true, true};
    }
    //-1 no revisa
    public static int[] obtenerLongitudes(){
        return new int[]{-1, 5};
    }
}
