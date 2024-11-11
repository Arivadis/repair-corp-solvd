package resources;

import java.util.Objects;

public class Laptop extends Device {

    private double screenSize;
    private double batteryHours;
    private boolean camera;

    public Laptop(String made, String model) {
        super(made, model);
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public double getBatteryHours() {
        return batteryHours;
    }

    public void setBatteryHours(double batteryHours) {
        this.batteryHours = batteryHours;
    }

    public boolean getCamera() {
        return camera;
    }

    public void setCamera(boolean camera) {
        this.camera = camera;
    }

    @Override
    public String toString() {
        return ("Device info\n" + getMade() + " " + getModel() + " " + batteryHours + "\nIs camera " + camera + "\nScreen Size " + screenSize + "\n");
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMade(), getModel(), screenSize, batteryHours, camera);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Laptop laptop = (Laptop) obj;
        return Double.compare(screenSize, laptop.screenSize) == 0 &&
                Double.compare(batteryHours, laptop.batteryHours) == 0 &&
                camera == laptop.camera &&
                Objects.equals(getMade(), laptop.getMade()) &&
                Objects.equals(getModel(), laptop.getModel());
    }
}
