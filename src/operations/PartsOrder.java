package operations;

import support.IdGenerator;

import java.math.BigDecimal;
import java.util.Arrays;

public class PartsOrder extends Order {

    private String warehouse;
    private BigDecimal cost;
    private String[] parts;
    private String[] partsOrdered;
    private boolean paid;

    public PartsOrder() {
        super(IdGenerator.getInstance().createId());
        parts = new String[0];
        partsOrdered = new String[0];
    }

    public long getId() {
        return id;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String[] getParts() {
        return parts;
    }

    public void addParts(String part) {
        String[] newPartsArray = Arrays.copyOf(parts, parts.length + 1);
        newPartsArray[newPartsArray.length - 1] = part;
        parts = newPartsArray;
    }

    public void removePart(int partIndex) {
        String[] newPartsArray = new String[parts.length - 1];
        for (int i = 0; i < newPartsArray.length; i++) {
            if (i >= partIndex) {
                newPartsArray[i] = parts[i + 1];
            } else {
                newPartsArray[i] = parts[i];
            }
        }
        parts = newPartsArray;
    }

    public boolean getPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setComplete() {
        complete = !complete;
        System.out.println(complete ? "The parts order is complete now " + id : "Something went wrong -> the parts order is uncompleted now " + id);
    }

    @Override
    public String toString() {
        String partsString = "";
        if (parts != null) {
            for (String part : parts) {
                partsString += part + " ";
            }
        }
        return "Parts order info \n" + partsString + "\nWarehouse " + warehouse + "\nCost " + cost;
    }
}
