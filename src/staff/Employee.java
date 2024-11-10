package staff;

import support.IdGenerator;

public class Employee extends Person {

    private JobPosition position;
    private boolean hired;

    public Employee(String name, String surname, int age, JobPosition position) {
        super(name, surname, age, IdGenerator.getInstance().createId());
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
        this.hired = !this.hired;
    }

    public boolean getHired() {
        return this.hired;
    }

    public enum JobPosition {
        MANAGER,
        REPAIR_MASTER,
        ACCOUNTANT,
        DELIVERY,
        TEAMLEAD
    }
}
