package modelo;

import java.lang.reflect.Field;

public class Employee implements Registrable {
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

    public Employee(String SSN, String first_Name, String last_Name, String address_Street, String address_City, String address_Postal_Code, String phone_Number, int union_Membership, double salary, String charge) {
        this.SSN = SSN;
        First_Name = first_Name;
        Last_Name = last_Name;
        Address_Street = address_Street;
        Address_City = address_City;
        Address_Postal_Code = address_Postal_Code;
        Phone_Number = phone_Number;
        Union_Membership = union_Membership;
        Salary = salary;
        Charge = charge;
    }

    @Override
    public String toString() {
        return "Employee{" +
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
