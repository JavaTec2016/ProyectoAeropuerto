package modelo;

public class Traffic_Controller extends ModeloBD{
    String SSN;
    String Last_Exam;

    public Traffic_Controller(String sSN, String last_Exam) {
        SSN = sSN;
        Last_Exam = last_Exam;
    }
    public Traffic_Controller(){};
    @Override
    public String toString() {
        return "Traffic_Controller{" +
                "SSN='" + SSN + '\'' +
                ", Last_Exam=" + Last_Exam + '\'' +
                '}';
    }
    public static String[] obtenerComponentes(){
        return new String[]{
                "JTextField", "JTextField"
        };
    }

    public static String[] obtenerLabels(){
        return new String[]{"SSN", "Fecha de ultimo examen"};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"CHAR", "DATE"};
    }
    public static boolean[] obtenerNoNulos(){
        return new boolean[]{true, true};
    }
    public static int[] obtenerLongitudes(){
        return new int[]{20, -2};
    }
    //el metodo de la superclase incluye a los nuevos atributos
}
