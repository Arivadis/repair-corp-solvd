package staff;

public abstract class Person {

    protected long id;
    protected String name;
    protected String surname;
    protected int age;
    protected String phoneNumber;
    protected String email;
    protected String address;

    protected Person(String name, String surname, int age, long id) {
        this.id = id;
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Age can not be less 0 or more 150");
        }
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    protected abstract void notifyPerson(String remark);
}
