package staff;

import java.math.BigDecimal;

public final class ExecutiveDirector extends Employee implements SalarySettable, BonusSettable {

    public ExecutiveDirector(String name, String surname, int age, JobPosition position, String phoneNumber) {
        super(name, surname, age, position, phoneNumber);
    }

    @Override
    public void setSalary(Employee employee, BigDecimal salary) {
        if (!(employee instanceof ExecutiveDirector)) {
            System.out.println("ExecutiveDirector set salary to " + employee + " SALARY " + salary);
            employee.setSalary(salary);
        } else {
            System.out.println("ExecutiveDirector - you can't set salary for yourself");
        }
    }

    @Override
    public void setBonus(Employee employee, BigDecimal bonus) {
        if (!(employee instanceof ExecutiveDirector)) {
            System.out.println("ExecutiveDirector set bonus to " + employee + " BONUS " + bonus);
            employee.setBonus(bonus);
        } else {
            System.out.println("ExecutiveDirector - Can't set bonus for yourself");
        }

    }

    @Override
    public String toString() {
        String output = "\nExecutiveDirector info\nID " + id + "\n" + getBaseInfo();
        System.out.println(output);
        return output;
    }
}
