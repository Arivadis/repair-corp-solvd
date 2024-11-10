import company.*;
import operations.*;
import resources.Device;
import staff.Customer;
import staff.Employee;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        RepairService repairService = new RepairService(
                "Whoop and done",
                "Mobile",
                "Zakopane str. 99",
                400.,
                new BigDecimal("8000")
        );

        Employee employee1 = new Employee("Ali", "Baba", 18, Employee.JobPosition.TEAMLEAD);
        employee1.hire();
        Employee employee2 = new Employee("Josef", "Biden", 28, Employee.JobPosition.REPAIR_MASTER);
        employee2.hire();
        Employee employee3 = new Employee("Hilel", "Bubaleh", 48, Employee.JobPosition.DELIVERY);
        employee3.hire();

        repairService.addEmployee(employee1);
        repairService.addEmployee(employee2);
        repairService.addEmployee(employee3);
        repairService.setTeamLead(employee1);
        repairService.removeEmployee(employee1);

        Device device1 = new Device(83821904230492L, "Motorola", "G54");
        Device device2 = new Device(83821904230492L, "Motorola", "G54");
        Device[] devices = {device1, device2};

        Customer customer1 = new Customer("Hilel", "Bubaleh", 48);
        customer1.setDevice(device1);

        RepairOrder repairOrder1 = new RepairOrder();
        repairOrder1.setCustomer(customer1);
        repairOrder1.setDevices(devices);
        repairOrder1.setEstimateCost(new BigDecimal("100.00"));

        RepairType repairType = new RepairType();
        repairType.setMaster(employee2);
        repairType.setDamage("Screen");
        repairType.setComments("Can repair");

        repairOrder1.setRepairType(repairType);
        repairService.addRepairOrder(repairOrder1);

        PartsOrder partsOrder1 = new PartsOrder();
        partsOrder1.setCost(new BigDecimal("50.00"));
        partsOrder1.setWarehouse("Warsaw");
        partsOrder1.addParts("Screen " + device1.getMade() + " " + device1.getModel());
        partsOrder1.removePart(1);
        repairOrder1.addPartsOrder(partsOrder1);

        repairOrder1.setComplete();
        repairOrder1.setRepairedTime(LocalDateTime.now());

        DeliverOrder deliverOrder = new DeliverOrder();
        repairOrder1.setDeliverOrder(deliverOrder);
        deliverOrder.setWeight(.3455);
        deliverOrder.setAddress("Warsaw, 05-050");
        deliverOrder.setDeliveryDay(LocalDate.of(2024, 11, 15));
        repairService.addRepairedCount();
        repairService.addRepairedCount();

        RepairService repairService2 = new RepairService();
        repairService2.setName("Test");

        RepCorp corporation;
        corporation = new RepCorp(
                "Fixed Enterprise",
                new BigDecimal("1000000000"),
                "Menachem Mendel Schneerson str. 12",
                255.,
                new BigDecimal("9000")
        );
        corporation.setAddress("The main street of all time");
        corporation.addService(repairService);
        corporation.addService(repairService2);
        corporation.closeService(repairService2.getName());
        System.out.println(corporation.getServices());
        System.out.println(corporation.getTotalRepaired());
    }
}
