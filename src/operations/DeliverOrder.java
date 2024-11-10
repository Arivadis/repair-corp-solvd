package operations;
import java.time.LocalDate;
import support.IdGenerator;

public class DeliverOrder extends Order {

    private String address;
    private Double weight;
    private LocalDate deliveryDay;

    public DeliverOrder() {
        super(IdGenerator.getInstance().createId());
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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
}
