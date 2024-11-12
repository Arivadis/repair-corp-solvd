package staff;

import support.IdGenerator;

public class Employee extends Person {

    private JobPosition position;
    private boolean hired;
    private boolean statusReady;

    public Employee(String name, String surname, int age, JobPosition position, String phoneNumber) {
        super(name, surname, age, IdGenerator.getInstance().createId());
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.hired = false;
    }

    public long getId() {
        return id;
    }

    public JobPosition getPosition() {
        return position;
    }

    public void setPosition(JobPosition position) {
        this.position = position;
    }

    public void hire() {
        this.hired = true;
    }

    public void fire() {
        this.hired = false;
    }

    public boolean getHired() {
        return this.hired;
    }

    public boolean statusReady() {
        return statusReady;
    }

    public void setStatusReady(boolean statusReady) {
        this.statusReady = statusReady;
    }

    public enum JobPosition {
        MANAGER,
        REPAIR_MASTER,
        ACCOUNTANT,
        DELIVERY,
        TEAMLEAD
    }

    @Override
    public void notifyPerson(String remark) {
        System.out.println("Send message to employee in telegram Sol group or call by ->" + phoneNumber + "with " + remark);
    }

    @Override
    public String toString() {
        String output = "\nEmployee info\nID " + id + "\nName " + name + "\nSurname " + surname + "\nPosition " + position + "\n";
        System.out.println(output);
        return output;
    }
}
