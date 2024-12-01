package com.solvd.repaircorpsolvd.staff;

import java.util.Objects;

public final class RepairTechnician extends Employee {

    private String department;
    private Specification specification;

    public RepairTechnician(String name, String surname, int age, JobPosition position, String phoneNumber, String department) {
        super(name, surname, age, position, phoneNumber);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    @Override
    public String toString() {
        String output = "\nRepairTechnician info\nID " + id + "\n" + getBaseInfo() + "\nSpecification " + specification + "\nDepartment " + department;
        System.out.println(output);
        return output;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, department, id, specification);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        RepairTechnician repairTechnician = (RepairTechnician) object;
        return Objects.equals(name, repairTechnician.getName()) &&
                Objects.equals(surname, repairTechnician.getSurname()) &&
                Objects.equals(department, repairTechnician.getDepartment()) &&
                Objects.equals(specification, repairTechnician.getSpecification()) &&
                Objects.equals(id, repairTechnician.getId());

    }

}
