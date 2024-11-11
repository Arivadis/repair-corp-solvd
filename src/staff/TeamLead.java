package staff;

public class TeamLead extends Employee {

    private String department;
    private int maxSubordinates;

    public TeamLead(String name, String surname, int age, JobPosition position, String phoneNumber) {
        super(name, surname, age, position, phoneNumber);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getMaxSubordinates() {
        return maxSubordinates;
    }

    public void setMaxSubordinates(int maxSubordinates) {
        this.maxSubordinates = maxSubordinates;
    }
}
