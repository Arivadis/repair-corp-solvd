package company;

import operations.RepairOrder;
import staff.Employee;

import java.math.BigDecimal;
import java.util.Arrays;

public class RepairService extends Building {

    private String name;
    private Employee[] employees;
    private String profile;
    private Employee teamLead;
    private RepairOrder[] orders;
    private int totalRepaired;

    public RepairService() {
        employees = new Employee[0];
        orders = new RepairOrder[0];
    }

    public RepairService(String name, String profile, String address, double area, BigDecimal rentCost) {
        super(address, area, rentCost);
        this.name = name;
        this.profile = profile;
        employees = new Employee[0];
        orders = new RepairOrder[0];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        Employee[] newEmployes = Arrays.copyOf(employees, employees.length + 1);
        newEmployes[newEmployes.length - 1] = employee;
        employees = newEmployes;
    }

    public void removeEmployee(Employee employee) {
        Employee[] newEmployeeList = new Employee[employees.length - 1];
        int shift = 0;
        for (int i = 0; i < newEmployeeList.length; i++) {
            if (employees[i].getId() == employee.getId()) {
                shift = 1;
            }
            newEmployeeList[i] = employees[i + shift];
        }
        employees = newEmployeeList;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Employee getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(Employee teamLead) {
        this.teamLead = teamLead;
    }

    public RepairOrder[] getOrders() {
        return orders;
    }

    public void addRepairOrder(RepairOrder repairOrder) {
        RepairOrder[] newRepairOrders = Arrays.copyOf(orders, orders.length + 1);
        newRepairOrders[newRepairOrders.length - 1] = repairOrder;
        orders = newRepairOrders;
    }

    public int getTotalRepaired() {
        return totalRepaired;
    }

    public void addRepairedCount() {
        totalRepaired++;
    }
}
