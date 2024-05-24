package modelo;

public class Traffic_Controller extends Employee{
    String SSN;
    String First_Name;
    String Last_Name;
    String Address_Street;
    String Address_City;
    String Address_Postal_Code;
    String Phone_Number;
    int Union_Membership;
    double Salary;
    String Charge;

    byte Last_Exam_Day;
    byte Last_Exam_Month;
    byte Last_Exam_Year;

    public Traffic_Controller(String SSN, String first_Name, String last_Name, String address_Street, String address_City, String address_Postal_Code, String phone_Number, int union_Membership, double salary, String charge, byte last_Exam_Day, byte last_Exam_Month, byte last_Exam_Year) {
        super(SSN, first_Name, last_Name, address_Street, address_City, address_Postal_Code, phone_Number, union_Membership, salary, charge);
        Last_Exam_Day = last_Exam_Day;
        Last_Exam_Month = last_Exam_Month;
        Last_Exam_Year = last_Exam_Year;
    }

    @Override
    public String toString() {
        return "Traffic_Controller{" +
                "SSN='" + SSN + '\'' +
                ", First_Name='" + First_Name + '\'' +
                ", Last_Name='" + Last_Name + '\'' +
                ", Address_Street='" + Address_Street + '\'' +
                ", Address_City='" + Address_City + '\'' +
                ", Address_Postal_Code='" + Address_Postal_Code + '\'' +
                ", Phone_Number='" + Phone_Number + '\'' +
                ", Union_Membership=" + Union_Membership +
                ", Salary=" + Salary +
                ", Charge='" + Charge + '\'' +
                ", Last_Exam_Day=" + Last_Exam_Day +
                ", Last_Exam_Month=" + Last_Exam_Month +
                ", Last_Exam_Year=" + Last_Exam_Year + '\'' +
                '}';
    }
    //el metodo de la superclase incluye a los nuevos atributos
}
