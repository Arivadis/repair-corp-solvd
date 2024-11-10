package operations;

import support.IdGenerator;

import java.math.BigDecimal;
import java.util.Arrays;

public class PartsOrder extends Order {

    private String warehouse;
    private BigDecimal cost;
    private String[] parts;

    public PartsOrder() {
        super(IdGenerator.getInstance().createId());
        parts = new String[0];
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
}
