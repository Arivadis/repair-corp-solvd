package company;

import operations.DeliverOrder;
import operations.Order;
import operations.PartsOrder;
import operations.RepairOrder;
import resources.Device;
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
        Employee[] newEmployees = Arrays.copyOf(employees, employees.length + 1);
        newEmployees[newEmployees.length - 1] = employee;
        employees = newEmployees;
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

    public void processOrder(Order order) {
        if (order instanceof DeliverOrder deliverOrder) {
            Employee deliveryMan = null;
            for (Employee employee : employees) {
                if (employee.getHired() && employee.getPosition() == Employee.JobPosition.DELIVERY && employee.statusReady()) {
                    deliveryMan = employee;
                    deliveryMan.setStatusReady(false);
                    System.out.println("Delivery man has been set " + deliveryMan.getName() + " " + deliveryMan.getSurname() + "\n");
                    break;
                }
            }
            if (deliveryMan != null) {
                deliverOrder.setDeliveryMan(deliveryMan);
                deliveryMan.notifyPerson("\nNew delivery order ->\n");
                deliverOrder.getCustomer().notifyPerson("\n Your delivery -> " + order + "\n");
                deliverOrder.setComplete();
                deliveryMan.setStatusReady(true);
            } else {
                System.out.println("Something went wrong, check an order to find issues\n");
            }

        } else if (order instanceof RepairOrder repairOrder) {
            System.out.println("Order date/time " + repairOrder.getTime() + "\n");
            if (repairOrder.getDevices() != null) {
                System.out.println("Devices to repair ");
                for (Device device : repairOrder.getDevices()) {
                    System.out.println(device);
                }
            }
            if (repairOrder.getPartsOrders() != null) {
                System.out.println("Check the parts to order ");
            } else {
                System.out.println("Repairing with no orders ");
            }
            if (repairOrder.getEstimateCost() != null) {
                System.out.println("Charge client for " + repairOrder.getEstimateCost() + "\n");
            }
        } else if (order instanceof PartsOrder partsOrder) {
            System.out.println(partsOrder);
        }

    }

}
