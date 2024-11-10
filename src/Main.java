import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        RepairService repairService = new RepairService("Whoop and done", "Mobile");

        Person personEmp1 = new Person("Ali", "Baba", 18);
        Person personEmp2 = new Person("Jack", "Black", 38);
        Person personEmp3 = new Person("Josef", "Biden", 28);
        Person personClient1 = new Person("Josef", "Biden", 28);

        IdGenerator idGenerator = new IdGenerator();

        Employee employee1 = new Employee(idGenerator.newEmpId(2024), personEmp1, JobPosition.TEAMLEAD);
        employee1.hire();
        Employee employee2 = new Employee(idGenerator.newEmpId(2024), personEmp2, JobPosition.REPAIR_MASTER);
        employee2.hire();
        Employee employee3 = new Employee(idGenerator.newEmpId(2024), personEmp3, JobPosition.DELIVERY);
        employee3.hire();

        repairService.addEmployee(employee1);
        repairService.addEmployee(employee2);
        repairService.addEmployee(employee3);
        repairService.setTeamLead(employee1);

        Device device1 = new Device(83821904230492L, "Motorola", "G54");
        Device device2 = new Device(83821904230492L, "Motorola", "G54");
        Device[] devices = {device1, device2};

        Customer customer1 = new Customer();
        customer1.setId(idGenerator.newCustId(2024));
        customer1.setName(personClient1);
        customer1.setDevice(device1);

        RepairOrder repairOrder1 = new RepairOrder(idGenerator.repairOrdId(123));
        repairOrder1.setCustomer(customer1);
        repairOrder1.setDevice(devices);
        repairOrder1.setEstimateCost(new BigDecimal("100.00"));

        RepairType repairType = new RepairType();
        repairType.setMaster(employee2);
        repairType.setDamage("Screen");
        repairType.setComments("Can repair");

        repairOrder1.setRepairType(repairType);
        repairService.addRepairOrder(repairOrder1);

        PartsOrder partsOrder1 = new PartsOrder(idGenerator.partsOrdId(repairOrder1.getId()));
        partsOrder1.setCost(new BigDecimal("50.00"));
        partsOrder1.setWarehouse("Warsaw");
        partsOrder1.addParts("Screen " + device1.getMade() + " " + device1.getModel());
        repairOrder1.addPartsOrder(partsOrder1);

        repairOrder1.orderingParts(repairOrder1.getId());
        repairOrder1.doRepair(repairOrder1.getRepairType().getMaster());
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

        RepCorp corporation = new RepCorp("Fixed Enterprise", new BigDecimal("1000000000"));
        corporation.setAddress("The main street of all time");
        corporation.addService(repairService);
        corporation.addService(repairService2);
        corporation.closeService(repairService2.getName());
        System.out.println(corporation.getServices());
        System.out.println(corporation.getTotalRepaired());
    }
}
