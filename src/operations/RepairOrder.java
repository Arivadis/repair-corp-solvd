package operations;

import resources.Device;
import staff.Customer;
import support.IdGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class RepairOrder extends Order {

    private final LocalDateTime time;
    private LocalDateTime repairedTime;
    private Device[] devices;
    private RepairType repairType;
    private BigDecimal estimateCost;
    private DeliverOrder deliverOrder;
    private PartsOrder[] partsOrders;

    public RepairOrder() {
        super(IdGenerator.getInstance().createId());
        time = LocalDateTime.now();
        partsOrders = new PartsOrder[0];
        estimateCost = BigDecimal.ZERO;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public LocalDateTime getRepairedTime() {
        return repairedTime;
    }

    public void setRepairedTime(LocalDateTime repairedTime) {
        this.repairedTime = repairedTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Device[] getDevices() {
        return devices;
    }

    public void setDevices(Device[] devices) {
        this.devices = devices;
    }

    public RepairType getRepairType() {
        return repairType;
    }

    public void setRepairType(RepairType repairType) {
        this.repairType = repairType;
    }

    public BigDecimal getEstimateCost() {
        return estimateCost;
    }

    public void setEstimateCost(BigDecimal estimateCost) {
        this.estimateCost = estimateCost;
    }

    public DeliverOrder getDeliverOrder() {
        return deliverOrder;
    }

    public void setDeliverOrder(DeliverOrder deliverOrder) {
        this.deliverOrder = deliverOrder;
    }

    public PartsOrder[] getPartsOrders() {
        return partsOrders;
    }

    public void addPartsOrder(PartsOrder partsOrder) {
        PartsOrder[] newPartsOrders = Arrays.copyOf(partsOrders, partsOrders.length + 1);
        newPartsOrders[newPartsOrders.length - 1] = partsOrder;
        partsOrders = newPartsOrders;
    }

    public void removePartsOrder(int index) {
        PartsOrder[] newPartsOrders = new PartsOrder[partsOrders.length - 1];
        for (int i = 0; i < newPartsOrders.length; i++) {
            if (i >= index) {
                newPartsOrders[i] = partsOrders[i + 1];
            } else {
                newPartsOrders[i] = partsOrders[i];
            }
        }
        partsOrders = newPartsOrders;
    }

    @Override
    public void setComplete() {
        if (complete) {
            System.out.println("The repair order was already complete!" + id);
            return;
        }
        complete = true;
        repairedTime = LocalDateTime.now();
        System.out.println("The repair order is complete now " + id);
    }

    @Override
    public void setIncomplete() {
        if (!complete) {
            System.out.println("The repair order was already incomplete!" + id);
            return;
        }
        complete = false;
        repairedTime = null;
        System.out.println("The repair order is incomplete now " + id);
    }

    @Override
    public String toString() {
        String output = "Repair order info\nID " + id + "\nTime " + time + "\n Customer " + customer.toString() + "\nEstimate Cost " + estimateCost.toString();
        System.out.println(output);
        return output;
    }
}
