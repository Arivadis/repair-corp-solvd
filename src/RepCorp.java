import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RepCorp {

    private String name;
    private String address;
    private BigDecimal initCapital;
    private final List<RepairService> services;

    public RepCorp(String corpName, BigDecimal initCapital) {
        this.name = corpName;
        this.initCapital = initCapital;
        services = new ArrayList<>();
    }

    public String getCorpName() {
        return name;
    }

    public void setCorpName(String corpName) {
        this.name = corpName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getInitCapital() {
        return initCapital;
    }

    public void setInitCapital(BigDecimal initCapital) {
        this.initCapital = initCapital;
    }

    public List<RepairService> getServices() {
        return services;
    }

    public void addService(RepairService repairService) {
        services.add(repairService);
    }

    public void closeService(String name) {
        for (int i = 0; i < services.size(); i++) {
            if (services.get(i).getName().equals(name)) {
                services.remove(i);
                break;
            }
        }
    }

    public int getTotalRepaired() {
        int counter = 0;
        for (RepairService repairService : services) {
            counter += repairService.getTotalRepaired();
        }
        return counter;
    }
}
