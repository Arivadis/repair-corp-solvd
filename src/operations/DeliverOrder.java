package operations;

import staff.Customer;
import staff.Employee;
import support.IdGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DeliverOrder extends Order {

    private String address;
    private Double weight;
    private LocalDate deliveryDay;
    private Employee deliveryMan;
    private LocalDateTime deliveredTime;

    public DeliverOrder() {
        super(IdGenerator.getInstance().createId());
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDeliveryDay() {
        return deliveryDay;
    }

    public void setDeliveryDay(LocalDate deliveryDay) {
        this.deliveryDay = deliveryDay;
    }

    public Employee getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(Employee deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public LocalDateTime getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(LocalDateTime deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    public void setComplete() {
        complete = !complete;
        if (complete) {
            deliveryMan.setStatusReady(true);
            deliveredTime = LocalDateTime.now();
        } else {
            deliveryMan.setStatusReady(false);
            deliveredTime = null;
        }
        System.out.println(complete ? "The delivery order is complete now -> " + id : "Something went wrong -> the delivery order is uncompleted now -> " + id);
    }

    @Override
    public String toString() {
        String output = "Delivery order info\nID " + id + "\nWeight " + weight + "\n Address " + address + "\nDelivery day " + deliveryDay;
        System.out.println(output);
        return output;
    }
}
