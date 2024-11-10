package staff;
import resources.Device;
import support.IdGenerator;

public class Customer extends Person {

    private Device device;
    private double discount;

    public Customer(String name, String surname, int age) {

        super(name, surname, age, IdGenerator.getInstance().createId());
    }

    public long getId() {
        return id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
