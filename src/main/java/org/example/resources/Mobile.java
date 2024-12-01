package resources;

import java.util.Objects;

public class Mobile extends Device implements Chargeable, Networkable {

    private long imei;
    private NetworkType networkType;
    private boolean charging;
    private boolean isConnected;

    public Mobile(String made, String model) {
        super(made, model);
    }

    public long getImei() {
        return imei;
    }

    public void setImei(long imei) {
        this.imei = imei;
    }

    public NetworkType getNetworkType() {
        return networkType;
    }

    public void setNetworkType(NetworkType networkType) {
        this.networkType = networkType;
    }

    @Override
    public String toString() {
        return ("Device info\n" + getMade() + " " + getModel() + "\nNetwork type" + networkType + "\niMEI " + imei + "\n");
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMade(), getModel(), imei, networkType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mobile mobile = (Mobile) obj;
        return Objects.equals(getMade(), mobile.getMade()) &&
                Objects.equals(getModel(), mobile.getModel()) &&
                imei == ((Mobile) obj).imei &&
                networkType == ((Mobile) obj).networkType;
    }

    @Override
    public void charge() {
        charging = true;
        System.out.println(getMade() + " " + getModel() + " is charging now");
    }

    @Override
    public void stopCharge() {
        charging = false;
        System.out.println(getMade() + " " + getModel() + " is not charging now");
    }

    @Override
    public boolean onCharging() {
        return charging;
    }

    @Override
    public void connectNetwork() {
        isConnected = true;
        System.out.println(getMade() + " " + getModel() + " is connected now");
    }

    @Override
    public void disconnectNetwork() {
        isConnected = false;
        System.out.println(getMade() + " " + getModel() + " is not connected now");
    }

    @Override
    public boolean getNetworkStatus() {
        return isConnected;
    }
}
