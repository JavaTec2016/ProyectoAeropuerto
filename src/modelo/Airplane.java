package modelo;

public class Airplane extends Model{
    String Model_Number;
    int Capacity;
    int Weight;

    String Registration_Number;

    public Airplane(String model_Number, int capacity, int weight, String registration_Number) {
        super(model_Number, capacity, weight);
        Registration_Number = registration_Number;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "Registration_Number='" + Registration_Number + '\'' +
                ", Model_Number='" + Model_Number + '\'' +
                ", Capacity=" + Capacity +
                ", Weight=" + Weight +
                '}';
    }
}
