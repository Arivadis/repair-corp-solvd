import company.RepCorp;
import company.RepairService;
import operations.DeliverOrder;
import operations.PartsOrder;
import operations.RepairOrder;
import operations.RepairType;
import resources.Device;
import resources.Laptop;
import resources.Mobile;
import resources.Tablet;
import staff.Customer;
import staff.Employee;
import staff.TeamLead;

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

        TeamLead employee1 = new TeamLead("Ali", "Baba", 18, Employee.JobPosition.TEAMLEAD, "+482322235");
        employee1.hire();
        Employee employee2 = new Employee("Josef", "Biden", 28, Employee.JobPosition.REPAIR_MASTER, "+482322225");
        employee2.hire();
        Employee employee3 = new Employee("Hilel", "Bubaleh", 48, Employee.JobPosition.DELIVERY, "+482322215");
        employee3.hire();
        employee3.setStatusReady(true);

        repairService.addEmployee(employee1);
        repairService.addEmployee(employee2);
        repairService.addEmployee(employee3);
        repairService.setTeamLead(employee1);
        repairService.getTeamLead().notifyPerson("Do something");

        System.out.println(employee3);

        Mobile device1 = new Mobile("Motorola", "G54");
        Mobile device2 = new Mobile("Samsung", "L10");
        Device device3 = new Laptop("Lenovo", "IdeaPad");
        Tablet device4 = new Tablet("Samsung", "TAB S10");
        device4.setNetworkType(Device.NetworkType.FIVE_G);

        Device[] devices = {device1, device2, device3};

        // Laptop[] laptops = {device3}               cant store this obj here

        device1.setNetworkType(Device.NetworkType.FIVE_G);
        device2.setNetworkType(Device.NetworkType.FIVE_G);

        // when can't call        device3.getCamera()  from child class Laptop as declared Device

        Customer customer1 = new Customer("Abu", "Bandit", 18);
        customer1.setDevice(device1);
        customer1.setEmail("olo@gmail.com");
        System.out.println(customer1);

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
        repairOrder1.setIncomplete();
        // incomplete again

        DeliverOrder deliverOrder = new DeliverOrder();
        deliverOrder.setCustomer(customer1);
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
        System.out.println(corporation);
        corporation.addService(repairService);
        corporation.addService(repairService2);
        corporation.closeService(repairService2.getName());
        System.out.println("Total repaired " + corporation.getTotalRepaired());

        // compare devices

        // same device
        System.out.println(device2.equals(device2));
        // 2 diff devices
        System.out.println(device2.equals(device1));
        // 2 devices are diff
        Mobile device1Clone = new Mobile("Motorola", "G54");
        System.out.println(device1Clone.equals(device1));
        // new device with same constructor field are not equal because of NetworkType
        device1Clone.setNetworkType(Device.NetworkType.FIVE_G);
        System.out.println(device1Clone.equals(device1));
        System.out.println("\n\n");
        // now they are equal

        // busyness method
        repairService.processOrder(repairOrder1);
        repairService.processOrder(deliverOrder);
    }
}
