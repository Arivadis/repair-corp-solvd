package com.solvd.repaircorpsolvd.company;

import com.solvd.repaircorpsolvd.operations.DeliverOrder;
import com.solvd.repaircorpsolvd.operations.Order;
import com.solvd.repaircorpsolvd.operations.PartsOrder;
import com.solvd.repaircorpsolvd.operations.RepairOrder;
import com.solvd.repaircorpsolvd.resources.Device;
import com.solvd.repaircorpsolvd.staff.Employee;
import com.solvd.repaircorpsolvd.staff.ExecutiveDirector;
import com.solvd.repaircorpsolvd.staff.JobPosition;
import com.solvd.repaircorpsolvd.support.Address;
import com.solvd.repaircorpsolvd.support.AddressNotFoundException;
import com.solvd.repaircorpsolvd.support.NegativeValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepairService extends Building {

    private String name;
    private final List<Employee> employees;
    private String profile;
    private Employee teamLead;
    private ExecutiveDirector executiveDirector;
    private final List<RepairOrder> orders;
    private int totalRepaired;
    private static final Logger logger = LoggerFactory.getLogger(RepairService.class);

    public RepairService() {
        employees = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public RepairService(String name, String profile, Address address, double area, BigDecimal rentCost) throws AddressNotFoundException, NegativeValueException {
        super(address, area, rentCost);
        this.name = name;
        this.profile = profile;
        employees = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public ExecutiveDirector getExecutiveDirector() {
        return executiveDirector;
    }

    public void setExecutiveDirector(ExecutiveDirector executiveDirector) {
        this.executiveDirector = executiveDirector;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
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

    public List<RepairOrder> getOrders() {
        return orders;
    }

    public void addRepairOrder(RepairOrder repairOrder) {
        orders.add(repairOrder);
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
                if (employee.getHired() && employee.getPosition() == JobPosition.DELIVERY && employee.statusReady()) {
                    deliveryMan = employee;
                    deliveryMan.setStatusReady(false);
                    logger.info("Delivery man has been set {} {} \n", deliveryMan.getName(), deliveryMan.getSurname());
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
                logger.warn("Something went wrong, check an order to find issues\n");
            }

        } else if (order instanceof RepairOrder repairOrder) {
            logger.info("Order date/time {} \n", repairOrder.getTIME());
            if (repairOrder.getDevices() != null) {
                logger.info("Devices to repair ");
                for (Device device : repairOrder.getDevices()) {
                    logger.info("{}", device);
                }
            }
            if (repairOrder.getPartsOrders() != null) {
                logger.info("Check the parts to order ");
            } else {
                logger.info("Repairing with no orders ");
            }
            if (repairOrder.getEstimateCost() != null) {
                logger.info("Charge client for {} \n", repairOrder.getEstimateCost());
            }
        } else if (order instanceof PartsOrder partsOrder) {
            logger.info("{}", partsOrder);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, profile);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        RepairService repairService = (RepairService) object;
        return Objects.equals(name, repairService.getName()) &&
                Objects.equals(profile, repairService.getProfile());

    }

}
