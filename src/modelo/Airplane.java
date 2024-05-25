package modelo;

public class Airplane extends ModeloBD{


    String Model_Number;
    //int Capacity;
    //int Weight;

    String Registration_Number;

    public Airplane(String model_Number, String registration_Number) {
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
}
