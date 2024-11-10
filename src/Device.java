public class Device {

    private long imei;
    private String made;
    private String model;

    public Device() {

    }

    public Device(long imei, String made, String model) {
        this.imei = imei;
        this.model = model;
        this.made = made;
    }

    public long getImei() {
        return imei;
    }

    public void setImei(long imei) {
        this.imei = imei;
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
}
