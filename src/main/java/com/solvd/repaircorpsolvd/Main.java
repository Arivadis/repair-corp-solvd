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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Main {

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
            repairService = new RepairService(
                    "Whoop and done",
                    "Mobile",
                    repairServiceAddress,
                    400.,
                    new BigDecimal("8000")
            );
        } catch (AddressNotFoundException | NegativeValueException e) {
            System.out.println(e.getMessage());
        }

        // test wrong address
        RepairService repairService1 = null;
        try {
            repairService1 = new RepairService(
                    "Whoop and done",
                    "Mobile",
                    checkAddress,
                    400.,
                    new BigDecimal("8000")
            );
        } catch (AddressNotFoundException | NegativeValueException e) {
            System.out.println(e.getMessage());
        }
        // will fail due to the not init instance
        // System.out.println(repairService1.getProfile());
        System.out.println(repairService1);


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

        System.out.println(dekiveryMan);

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
        System.out.println(customer1);

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
            corporation = new RepCorp(
                    "Fixed Enterprise",
                    new BigDecimal("1000000000"),
                    repairServiceAddress,
                    255.,
                    new BigDecimal("9000")
            );
        } catch (AddressNotFoundException | NegativeValueException e) {
            System.out.println(e.getMessage());
        }


        corporation.setAddress(corpAddress);
        corporation.addService(repairService);
        corporation.addService(repairService2);
        corporation.closeService(repairService2);
        System.out.println("Total repaired " + corporation.getTotalRepaired());

        // compare devices

        System.out.println(device2.equals(device2));
        System.out.println(device2.equals(device1));
        Mobile device1Clone = new Mobile("Motorola", "G54");
        device1Clone.setNetworkType(NetworkType.FIVE_G);

        repairService.processOrder(repairOrder1);
        repairService.processOrder(deliverOrder);

        teamLead.setBonus(repairTechnician, new BigDecimal("501"));
        System.out.println(repairTechnician.getBonus());
        // cant do it
        teamLead.setBonus(repairTechnician, new BigDecimal("500"));
        System.out.println(repairTechnician.getBonus());

        accountant.setBonus(teamLead, new BigDecimal("1000"));
        accountant.setBonus(accountant, new BigDecimal("100"));
        // cant do it
        ExecutiveDirector executiveDirector = new ExecutiveDirector("Jeff", "Bezos", 60, JobPosition.EXECUTIVEDIRECTOR, "");
        repairService.setExecutiveDirector(executiveDirector);
        executiveDirector.setSalary(accountant, new BigDecimal("4000"));

        corporation.processRent(repairService, new BigDecimal("5012"));
        corporation.stopRent(repairService2);
        System.out.println(device1.onCharging());
        device4.charge();
        System.out.println(device4.onCharging());

        try {
            // Normal data
            System.out.println("The sum of invoice -> " + AccountingProcesses.sumOfInvoice("src/main/resources/invoice.txt"));
            // Illegal format
//            System.out.println("The sum of invoice -> " + AccountingProcesses.sumOfInvoice("src/main/resources/test format.txtt"));
            // wrong data
//            System.out.println("The sum of invoice -> " + AccountingProcesses.sumOfInvoice("src/main/resources/wrongData.txt"));
        } catch (EmptyFileException e) {
            System.out.println(e.getMessage());
        } catch (CalculationRuntimeException e) {
            System.out.println("Not handled problem \nCould not finish the counting -> " + e);
        }

        CustomLinkedList<String> linkedList = new CustomLinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        for (String val : linkedList) {
            System.out.println(val);
        }
        System.out.println(Arrays.toString(linkedList.toArray()));

        linkedList.remove("1");
        System.out.println(linkedList.getHead().getValue());
        System.out.println(linkedList.getTail().getValue());
        System.out.println(linkedList.getSize());
        System.out.println(linkedList.isEmpty() + " 1");
        linkedList.remove("1");
        System.out.println(linkedList.getSize() + " 2");
        System.out.println(linkedList.isEmpty());
        linkedList.remove("2");
        System.out.println(linkedList.getSize() + " 3");
        System.out.println(linkedList.isEmpty());
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        System.out.println(linkedList.getSize());
        linkedList.addAll(new ArrayList<>(Arrays.asList("4", "5", "6")));
        System.out.println(linkedList.toString());
        System.out.println(linkedList.getSize());
        linkedList.addAll(linkedList.size(), new ArrayList<>(Arrays.asList("1", "1", "1")));
        System.out.println(linkedList.getSize());
        linkedList.add(5, "10");
        System.out.println(linkedList.toString());
        System.out.println(linkedList.getSize());
        linkedList.add(0, "10");
        System.out.println(linkedList.toString());
        System.out.println(linkedList.getSize());
        linkedList.add(linkedList.size(), "10");

        System.out.println(linkedList.toString());
        System.out.println(linkedList.getSize());
        linkedList.remove(11);
        System.out.println(linkedList.lastIndexOf("10"));
        System.out.println(linkedList.toString());
        System.out.println(linkedList.subList(1, 3));

        ListIterator<String> iterator = linkedList.listIterator();

        // Test hasNext and next
        System.out.println("Iterating forward:");
        while (iterator.hasNext()) {
            System.out.println("Next: " + iterator.next());
        }
        System.out.println("\nIterating backward:");
        while (iterator.hasPrevious()) {
            System.out.println("Previous: " + iterator.previous());
        }
        iterator.add("Five");
        iterator.set("Six");
        while (iterator.hasNext()) {
            System.out.println("Next: " + iterator.next());
        }

        while (iterator.hasPrevious()) {
            System.out.println("Previous: " + iterator.previous());
        }

    }
}
