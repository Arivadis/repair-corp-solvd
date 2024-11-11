package company;

import staff.Employee;

import java.math.BigDecimal;
import java.util.Arrays;

public class RepCorp extends Building {

    private String name;
    private BigDecimal initCapital;
    private RepairService[] services;

    public RepCorp(String corpName, BigDecimal initCapital, String address, double area, BigDecimal rentCost) {
        super(address, area, rentCost);
        this.name = corpName;
        this.initCapital = initCapital;
        services = new RepairService[0];
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

    public RepairService[] getServices() {
        return services;
    }

    public void addService(RepairService repairService) {
        RepairService[] newServices = Arrays.copyOf(services, services.length + 1);
        newServices[newServices.length - 1] = repairService;
        services = newServices;
    }

    public void closeService(String name) {
        RepairService[] newServices = new RepairService[services.length - 1];
        int shift = 0;
        RepairService closedService = null;
        for (int i = 0; i < newServices.length; i++) {
            if (services[i].getName().equals(name)) {
                shift = 1;
                closedService = services[i];
            }
            newServices[i] = services[i + shift];
        }
        services = newServices;
        if (closedService != null) {
            for (Employee employee : closedService.getEmployees()) {
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
}
