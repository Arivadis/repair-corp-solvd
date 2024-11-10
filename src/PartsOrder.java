import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PartsOrder {

    private int id;
    private String warehouse;
    private BigDecimal cost;
    private List<String> parts;

    public PartsOrder() {
        parts = new ArrayList<>();
    }

    public PartsOrder(int id) {
        this.id = id;
    }

    public int getId() {
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

    public List<String> getParts() {
        return parts;
    }

    public void addParts(String part) {
        parts.add(part);
    }

    public void removePart(int partIndex) {
        parts.remove(partIndex);
    }
}
