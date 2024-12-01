package staff;

import java.math.BigDecimal;

public final class Accountant extends Employee implements BonusSettable, SalarySettable {

    public Accountant(String name, String surname, int age, JobPosition position, String phoneNumber) {
        super(name, surname, age, position, phoneNumber);
    }

    @Override
    public void setBonus(Employee employee, BigDecimal bonus) {
        if (!(employee instanceof Accountant)) {
            System.out.println("Accountant set bonus to " + employee + " BONUS " + bonus);
            employee.setBonus(bonus);
        } else {
            System.out.println("ACCOUNTANT - Can't set bonus for yourself");
        }
    }

    @Override
    public void setSalary(Employee employee, BigDecimal salary) {
        if (!(employee instanceof Accountant)) {
            System.out.println("Accountant set salary to " + employee + " SALARY " + salary);
            employee.setSalary(salary);
        } else {
            System.out.println("ACCOUNTANT - you can't set salary for yourself");
        }
    }

    @Override
    public String toString() {
        String output = "\nAccountant info\nID " + id + "\n" + getBaseInfo();
        System.out.println(output);
        return output;
    }
}
