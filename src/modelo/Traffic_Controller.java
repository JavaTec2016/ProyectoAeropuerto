package modelo;

public class Traffic_Controller extends ModeloBD{
    String SSN;
    String Last_Exam;


    public Traffic_Controller(String SSN, String last_Exam) {
        Last_Exam = last_Exam;
        this.SSN = SSN;
    }

    @Override
    public String toString() {
        return "Traffic_Controller{" +
                "SSN='" + SSN + '\'' +
                ", Last_Exam=" + Last_Exam + '\'' +
                '}';
    }
    //el metodo de la superclase incluye a los nuevos atributos
}
