package company;

import java.math.BigDecimal;

public abstract class Building {

    protected String address;
    protected double area;
    protected BigDecimal rentCost;
    protected boolean rentalStatus;

    protected Building() {

    }

    protected Building(String address, double area, BigDecimal rentCost) {
        this.address = address;
        this.area = area;
        this.rentCost = rentCost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
