package com.solvd.repaircorpsolvd.resources;

import java.util.Objects;

public class Tablet extends Device implements Chargeable, Networkable {

    private boolean simCard;
    private double screenSize;
    private long imei;
    private NetworkType networkType;
    private boolean charging;
    private boolean isConnected;

    public Tablet(String made, String model) {
        super(made, model);
    }

    public boolean getSimCard() {
        return simCard;
    }

    public void setSimCard(boolean simCard) {
        this.simCard = simCard;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
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
    public int hashCode() {
        return Objects.hash(getMade(), getModel(), simCard, screenSize, imei);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tablet tablet = (Tablet) obj;
        return Objects.equals(getMade(), tablet.getMade()) &&
                Objects.equals(getModel(), tablet.getModel()) &&
                imei == ((Tablet) obj).imei &&
                networkType == ((Tablet) obj).networkType;
    }

    @Override
    public String toString() {
        return ("Device info\n" + getMade() + " " + getModel() + "\nSim card " + simCard + "\nScreenSize" + screenSize + "\n" + imei + "\n");
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
