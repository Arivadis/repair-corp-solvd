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

    public void notifyPerson(String remark) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            System.out.println("\nCan't call as customer did not give a number with\n");
        } else {
            System.out.println("\nMake a call to customer -> " + phoneNumber + " with info " + remark + "\n");
            return;
        }
        if (email == null || email.isEmpty()) {
            System.out.println("\nCan't send e-mail as customer did not give an address\n");
        } else {
            System.out.println("\nSend e-mail to customer -> " + email + " with info " + remark + "\n");
            return;
        }
        if (address == null || address.isEmpty()) {
            System.out.println("\nThere is no any customer's contacts, did you forget to ask for it???\n");
        } else {
            System.out.println("\nSend a list to customer using address -> " + address + " with info " + remark + "\n");
        }
    }

    @Override
    public String toString() {
        String output = "\nCustomer info\nID " + id + "\nName " + name + "\nSurname " + surname + "\nDiscount " + discount + "\n";
        System.out.println(output);
        return output;
    }
}
