public class Employee {

    private int id;
    private Person person;
    private JobPosition position;
    private boolean hired;

    public Employee() {

    }

    public Employee(int id, Person person, JobPosition position) {
        this.id = id;
        this.person = person;
        this.position = position;
        this.hired = false;
    }

    public long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public JobPosition getPosition() {
        return position;
    }

    public void setPosition(JobPosition position) {
        this.position = position;
    }

    public void hire() {
        this.hired = !this.hired;
        System.out.println("Person " + person.getName() + " " + person.getSurname() + (this.hired ? " hired" : " fired"));
    }

    public boolean getHired() {
        return this.hired;
    }
}
