package com.solvd.repaircorpsolvd;

import com.solvd.repaircorpsolvd.accounting.AccountingProcesses;
import com.solvd.repaircorpsolvd.company.RepCorp;
import com.solvd.repaircorpsolvd.company.RepairService;
import com.solvd.repaircorpsolvd.custom_linked_list.CustomLinkedList;
import com.solvd.repaircorpsolvd.operations.DeliverOrder;
import com.solvd.repaircorpsolvd.operations.PartsOrder;
import com.solvd.repaircorpsolvd.operations.RepairOrder;
import com.solvd.repaircorpsolvd.operations.RepairType;
import com.solvd.repaircorpsolvd.resources.*;
import com.solvd.repaircorpsolvd.staff.*;
import com.solvd.repaircorpsolvd.support.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        //possible addresses
        Address repairServiceAddress = new Address("Zakopane", "01-900", "Krut", "99");
        Address deliverOrderAddress = new Address("Warsaw", "05-090", "Krakowska", "3");
        Address corpAddress = new Address("Warsaw", "05-090", "Main", "15");

        Addresses.addAddresses(new ArrayList<>(List.of(repairServiceAddress, deliverOrderAddress, corpAddress)));
        Address checkAddress = new Address("Warsaw", "05-090", "Main", "14");
        // Addresses.addressExists(checkAddress); throws unknow address
        RepairService repairService = null;
        try {
            repairService = new RepairService
                    ("Whoop and done", "Mobile", repairServiceAddress, 400., new BigDecimal("8000"));
        } catch (AddressNotFoundException | NegativeValueException e) {
            logger.info(e.getMessage());
        }

        // test wrong address
        RepairService repairService1 = null;
        try {
            repairService1 = new RepairService("Whoop and done", "Mobile", checkAddress, 400., new BigDecimal("8000"));
        } catch (AddressNotFoundException | NegativeValueException e) {
            logger.info(e.getMessage());
        }
        // will fail due to the not init instance
        // logger.info("{}", repairService1.getProfile());
        logger.info("{}", repairService1);


        TeamLead teamLead = new TeamLead("Ali", "Baba", 18, JobPosition.TEAMLEAD, "+482322235", "Mobile");
        teamLead.hire();
        RepairTechnician repairTechnician = new RepairTechnician("Josef", "Biden", 28, JobPosition.REPAIR_MASTER, "+482322225", "Mobile");
        repairTechnician.hire();
        repairTechnician.setSpecification(Specification.MOBILE);
        Employee dekiveryMan = new Employee("Hilel", "Bubaleh", 48, JobPosition.DELIVERY, "+482322215");
        dekiveryMan.hire();
        dekiveryMan.setStatusReady(true);
        Accountant accountant = new Accountant("Alisa", "Garmin", 33, JobPosition.ACCOUNTANT, "+482322115");

        repairService.addEmployee(teamLead);
        repairService.removeEmployee(teamLead);
        repairService.addEmployee(teamLead);
        repairService.addEmployee(repairTechnician);
        repairService.addEmployee(dekiveryMan);
        repairService.setTeamLead(teamLead);
        repairService.getTeamLead().notifyPerson("Do something");

        logger.info("{}", dekiveryMan);

        Mobile device1 = new Mobile("Motorola", "G54");
        Mobile device2 = new Mobile("Samsung", "L10");
        Device device3 = new Laptop("Lenovo", "IdeaPad");
        Tablet device4 = new Tablet("Samsung", "TAB S10");
        device4.setNetworkType(NetworkType.FIVE_G);

        device1.setNetworkType(NetworkType.FIVE_G);
        device2.setNetworkType(NetworkType.FIVE_G);

        // when can't call        device3.getCamera()  from child class Laptop as declared Device

        Customer customer1 = new Customer("Abu", "Bandit", 18);
        customer1.setDevice(device1);
        customer1.setEmail("olo@gmail.com");
        logger.info("{}", customer1);

        RepairOrder repairOrder1 = new RepairOrder();
        repairOrder1.setCustomer(customer1);
        repairOrder1.addDevice(device1);
        repairOrder1.addDevice(device2);
        repairOrder1.addDevice(device3);
        repairOrder1.setEstimateCost(new BigDecimal("100.00"));

        RepairType repairType = new RepairType();
        repairType.setMaster(repairTechnician);
        repairType.setDamage("Screen");
        repairType.setComments("Can repair");

        repairOrder1.setRepairType(repairType);
        repairService.addRepairOrder(repairOrder1);

        PartsOrder partsOrder1 = new PartsOrder();
        partsOrder1.setCost(new BigDecimal("50.00"));
        partsOrder1.setWarehouse("Warsaw");
        String part = "Screen " + device1.getMade() + " " + device1.getModel();
        partsOrder1.addParts(part, 2);
        partsOrder1.removePart(part);
        repairOrder1.addPartsOrder(partsOrder1);

        repairOrder1.setComplete();
        repairOrder1.setIncomplete();
        // incomplete again

        DeliverOrder deliverOrder = new DeliverOrder();
        deliverOrder.setCustomer(customer1);
        repairOrder1.setDeliverOrder(deliverOrder);
        deliverOrder.setWeight(.3455);

        deliverOrder.setAddress(deliverOrderAddress);
        deliverOrder.setDeliveryDay(LocalDate.of(2024, 11, 15));
        repairService.addRepairedCount();
        repairService.addRepairedCount();

        RepairService repairService2 = new RepairService();
        repairService2.setName("Test");

        RepCorp corporation = null;
        try {
            corporation = new RepCorp("Fixed Enterprise", new BigDecimal("1000000000"), repairServiceAddress, 255., new BigDecimal("9000"));
        } catch (AddressNotFoundException | NegativeValueException e) {
            logger.info(e.getMessage());
        }


        corporation.setAddress(corpAddress);
        corporation.addService(repairService);
        corporation.addService(repairService2);
        corporation.closeService(repairService2);
        logger.info("Total repaired {}", corporation.getTotalRepaired());

        // compare devices

        logger.info("{}", device2.equals(device2));
        logger.info("{}", device2.equals(device1));
        Mobile device1Clone = new Mobile("Motorola", "G54");
        device1Clone.setNetworkType(NetworkType.FIVE_G);

        repairService.processOrder(repairOrder1);
        repairService.processOrder(deliverOrder);

        teamLead.setBonus(repairTechnician, new BigDecimal("501"));
        logger.info("{}", repairTechnician.getBonus());
        // cant do it
        teamLead.setBonus(repairTechnician, new BigDecimal("500"));
        logger.info("{}", repairTechnician.getBonus());

        accountant.setBonus(teamLead, new BigDecimal("1000"));
        accountant.setBonus(accountant, new BigDecimal("100"));
        // cant do it
        ExecutiveDirector executiveDirector = new ExecutiveDirector("Jeff", "Bezos", 60, JobPosition.EXECUTIVEDIRECTOR, "");
        repairService.setExecutiveDirector(executiveDirector);
        executiveDirector.setSalary(accountant, new BigDecimal("4000"));

        corporation.processRent(repairService, new BigDecimal("5012"));
        corporation.stopRent(repairService2);
        logger.info("{}", device1.onCharging());
        device4.charge();
        logger.info("{}", device4.onCharging());

        try {
            // Normal data
            logger.info("The sum of invoice -> {}", AccountingProcesses.sumOfInvoice("src/main/resources/invoice.txt"));
            // Illegal format
//            logger.info("The sum of invoice -> {}", AccountingProcesses.sumOfInvoice("src/main/resources/test format.txtt"));
            // wrong data
//            logger.info("The sum of invoice -> {}", AccountingProcesses.sumOfInvoice("src/main/resources/wrongData.txt"));
        } catch (EmptyFileException e) {
            logger.info(e.getMessage());
        } catch (CalculationRuntimeException except) {
            logger.info("Not handled problem \n" + "Could not finish the counting -> {}", except);
        }

        CustomLinkedList<String> linkedList = new CustomLinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        for (String val : linkedList) {
            logger.info(val);
        }
        logger.info("{}", Arrays.toString(linkedList.toArray()));

        linkedList.remove("1");
        logger.info("{}", linkedList.getHead().getValue());
        logger.info("{}", linkedList.getTail().getValue());
        logger.info("{}", linkedList.getSize());
        logger.info("{}", linkedList.isEmpty() + " 1");
        linkedList.remove("1");
        logger.info("{}", linkedList.getSize() + " 2");
        logger.info("{}", linkedList.isEmpty());
        linkedList.remove("2");
        logger.info("{}", linkedList.getSize() + " 3");
        logger.info("{}", linkedList.isEmpty());
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        logger.info("{}", linkedList.getSize());
        linkedList.addAll(new ArrayList<>(Arrays.asList("4", "5", "6")));
        logger.info("{}", linkedList.toString());
        logger.info("{}", linkedList.getSize());
        linkedList.addAll(linkedList.size(), new ArrayList<>(Arrays.asList("1", "1", "1")));
        logger.info("{}", linkedList.getSize());
        linkedList.add(5, "10");
        logger.info("{}", linkedList.toString());
        logger.info("{}", linkedList.getSize());
        linkedList.add(0, "10");
        logger.info("{}", linkedList.toString());
        logger.info("{}", linkedList.getSize());
        linkedList.add(linkedList.size(), "10");

        logger.info("{}", linkedList.toString());
        logger.info("{}", linkedList.getSize());
        linkedList.remove(11);
        logger.info("{}", linkedList.lastIndexOf("10"));
        logger.info("{}", linkedList.toString());
        logger.info("{}", linkedList.subList(1, 3));

        ListIterator<String> iterator = linkedList.listIterator();

        // Test hasNext and next
        logger.info("Iterating forward:");
        while (iterator.hasNext()) {
            logger.info("Next: {}", iterator.next());
        }
        logger.info("\nIterating backward:");
        while (iterator.hasPrevious()) {
            logger.info("Previous: {}", iterator.previous());
        }
        iterator.add("Five");
        iterator.set("Six");
        while (iterator.hasNext()) {
            logger.info("Next: {}", iterator.next());
        }

        while (iterator.hasPrevious()) {
            logger.info("Previous: {}", iterator.previous());
        }
        Map<String, Integer> retTxt = UniqCounter.uniqWordsTxt("src/main/resources/Financier_1109.txt");
        for (Map.Entry<String, Integer> entry : retTxt.entrySet()) {
            logger.info("{}: {}", entry.getKey(), entry.getValue());
        }

        Map<String, Integer> retPdf = UniqCounter.uniqWordsPdf("src/main/resources/Financier_1109.pdf");
        for (Map.Entry<String, Integer> entry : retPdf.entrySet()) {
            logger.info("{}: {}", entry.getKey(), entry.getValue());
        }
    }
}
