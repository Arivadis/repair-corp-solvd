package com.solvd.repaircorpsolvd.operations;

import com.solvd.repaircorpsolvd.staff.Customer;
import com.solvd.repaircorpsolvd.staff.Employee;
import com.solvd.repaircorpsolvd.support.Address;
import com.solvd.repaircorpsolvd.support.AddressNotFoundException;
import com.solvd.repaircorpsolvd.support.Addresses;
import com.solvd.repaircorpsolvd.support.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class DeliverOrder extends Order {

    private Address address;
    private Double weight;
    private LocalDate deliveryDay;
    private Employee deliveryMan;
    private LocalDateTime deliveredTime;
    private BigDecimal cost;
    private static final Logger logger = LoggerFactory.getLogger(DeliverOrder.class);

    public DeliverOrder() {
        super(IdGenerator.createId());
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        try {
            Addresses.addressExists(address);
            this.address = address;
        } catch (AddressNotFoundException e) {
            logger.warn(e.getMessage());
        }
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public void setComplete() {
        if (complete) {
            logger.info("The delivery order was already complete! {}", ID);
            return;
        }
        complete = true;
        deliveryMan.setStatusReady(true);
        deliveredTime = LocalDateTime.now();
        logger.info("The delivery order is complete now -> {}", ID);
    }

    @Override
    public void setIncomplete() {
        if (!complete) {
            logger.info("The delivery order was already incomplete!{}", ID);
            return;
        }
        complete = false;
        deliveryMan.setStatusReady(false);
        deliveredTime = null;
        logger.info("The delivery order is incomplete now -> {}", ID);
    }

    @Override
    public String toString() {
        String output = "Delivery order info\nID " + ID + "\nWeight " + weight + "\n Address " + address + "\nDelivery day " + deliveryDay;
        logger.info(output);
        return output;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, createTime, address, weight, deliveryDay, deliveredTime);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        DeliverOrder order = (DeliverOrder) object;
        return Objects.equals(ID, order.ID) &&
                Objects.equals(createTime, order.createTime) &&
                Objects.equals(address, order.address) &&
                Objects.equals(weight, order.weight) &&
                Objects.equals(deliveryDay, order.deliveryDay) &&
                Objects.equals(deliveredTime, order.deliveredTime);
    }
}