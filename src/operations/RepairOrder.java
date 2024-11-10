package operations;

import resources.Device;
import staff.Customer;
import support.IdGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class RepairOrder extends Order {

    private final LocalDateTime orderDay;
    private LocalDateTime repairedTime;
    private Customer customer;
    private Device[] devices;
    private RepairType repairType;
    private BigDecimal estimateCost;
    private DeliverOrder deliverOrder;
    private PartsOrder[] partsOrders;
    private boolean complete;

    public RepairOrder() {
        super(IdGenerator.getInstance().createId());
        orderDay = LocalDateTime.now();
        partsOrders = new PartsOrder[0];
        estimateCost = BigDecimal.ZERO;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getOrderDay() {
        return orderDay;
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

    public boolean getComplete() {
        return complete;
    }

    public void setComplete() {
        complete = !complete;
    }
}
