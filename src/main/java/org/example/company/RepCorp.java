package company;

import staff.Employee;
import support.Address;
import support.AddressNotFoundException;
import support.NegativeValueException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RepCorp extends Building {

    private String name;
    private BigDecimal initCapital;
    private final List<RepairService> services;

    public RepCorp(String corpName, BigDecimal initCapital, Address address, double area, BigDecimal rentCost) throws AddressNotFoundException, NegativeValueException {
        super(address, area, rentCost);
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

    public void closeService(RepairService repairService) {
        services.remove(repairService);
        if (repairService != null) {
            for (Employee employee : repairService.getEmployees()) {
                if (employee.getHired()) {
                    employee.hire();
                }
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

    @Override
    public String toString() {
        return "\nCompany " + name + "\nCapital " + initCapital + "\nLocated " + address + "Services\n";
    }

    public void processRent(Rentable rentable, BigDecimal cost) {
        rentable.rent(cost);
        System.out.println("Just rented new building with cost " + cost);
    }

    public void stopRent(Rentable rentable) {
        rentable.vacate();
        System.out.println("Just vacated building");
    }
}
