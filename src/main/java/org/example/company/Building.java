package company;

import support.Address;
import support.AddressNotFoundException;
import support.Addresses;
import support.NegativeValueException;

import java.math.BigDecimal;

public abstract class Building implements Rentable {

    protected Address address;
    protected double area;
    protected BigDecimal rentCost;
    protected boolean rentalStatus;

    protected Building() {
    }

    protected Building(Address address, double area, BigDecimal rentCost) throws AddressNotFoundException, NegativeValueException {

        Addresses.addressExists(address);
        this.address = address;
        if (area < 0) {
            throw new NegativeValueException("Area cant be less than 0");
        }
        if (rentCost.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeValueException("Rental cost cant be less than 0");
        }
        this.area = area;
        this.rentCost = rentCost;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        try {
            Addresses.addressExists(address);
            this.address = address;
        } catch (AddressNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("The current address is " + this.address);
        }

    }

    public BigDecimal getRentCost() {
        return rentCost;
    }

    public void setRentCost(BigDecimal rentCost) {
        this.rentCost = rentCost;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public boolean getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(boolean rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    @Override
    public void rent(BigDecimal cost) {
        this.rentCost = cost;
        this.rentalStatus = true;
        System.out.println("Building is now rented for " + cost);
    }

    @Override
    public void vacate() {
        this.rentalStatus = false;
        this.rentCost = BigDecimal.ZERO;
        System.out.println("Building at is now vacated.");
    }
}
