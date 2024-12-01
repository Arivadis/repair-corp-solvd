package com.solvd.repaircorpsolvd.operations;

import com.solvd.repaircorpsolvd.resources.Device;
import com.solvd.repaircorpsolvd.staff.Customer;
import com.solvd.repaircorpsolvd.support.IdGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class RepairOrder extends Order {

    // we do not change the start order time
    private final LocalDateTime TIME;
    private LocalDateTime repairedTime;
    private final List<Device> devices;
    private RepairType repairType;
    private BigDecimal estimateCost;
    private DeliverOrder deliverOrder;
    private final Set<PartsOrder> partsOrders;

    public RepairOrder() {
        super(IdGenerator.createId());
        TIME = LocalDateTime.now();
        partsOrders = new HashSet<>();
        estimateCost = BigDecimal.ZERO;
        devices = new ArrayList<>();
    }

    public LocalDateTime getTIME() {
        return TIME;
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

    public List<Device> getDevices() {
        return devices;
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public void removeDevice(Device device) {
        devices.remove(device);
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

    public Set<PartsOrder> getPartsOrders() {
        return partsOrders;
    }

    public void addPartsOrder(PartsOrder partsOrder) {
        partsOrders.add(partsOrder);
    }

    public void removePartsOrder(PartsOrder order) {
        partsOrders.remove(order);
    }

    @Override
    public void setComplete() {
        if (complete) {
            System.out.println("The repair order was already complete!" + ID);
            return;
        }
        complete = true;
        repairedTime = LocalDateTime.now();
        System.out.println("The repair order is complete now " + ID);
    }

    @Override
    public void setIncomplete() {
        if (!complete) {
            System.out.println("The repair order was already incomplete!" + ID);
            return;
        }
        complete = false;
        repairedTime = null;
        System.out.println("The repair order is incomplete now " + ID);
    }

    @Override
    public String toString() {
        String output = "Repair order info\nID " + ID + "\nTime " + TIME + "\n Customer " + customer.toString() + "\nEstimate Cost " + estimateCost.toString();
        System.out.println(output);
        return output;
    }

    @Override
    public int hashCode(){
        return Objects.hash(ID, createTime, TIME, repairedTime);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        RepairOrder order = (RepairOrder) object;
        return Objects.equals(ID, order.ID) &&
                Objects.equals(createTime, order.createTime) &&
                Objects.equals(TIME, order.TIME) &&
                Objects.equals(repairedTime, order.repairedTime);
    }
}
