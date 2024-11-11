package resources;

public class Device {

    protected String made;
    protected String model;

    public Device() {

    }

    public Device(String made, String model) {
        this.model = model;
        this.made = made;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return (made + model);
    }

    public enum NetworkType {
        THREE_G,
        FOUR_G,
        FIVE_G
    }
}
