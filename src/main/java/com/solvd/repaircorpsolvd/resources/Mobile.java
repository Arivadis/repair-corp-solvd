package com.solvd.repaircorpsolvd.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Mobile extends Device implements Chargeable, Networkable {

    private long imei;
    private NetworkType networkType;
    private boolean charging;
    private boolean isConnected;
    private static final Logger logger = LoggerFactory.getLogger(Mobile.class);

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
        logger.info("{} {} is charging now", getMade(), getModel());
    }

    @Override
    public void stopCharge() {
        charging = false;
        logger.info("{} {} is not charging now", getMade(), getModel());
    }

    @Override
    public boolean onCharging() {
        return charging;
    }

    @Override
    public void connectNetwork() {
        isConnected = true;
        logger.info("{} {} is connected now", getMade(), getModel());
    }

    @Override
    public void disconnectNetwork() {
        isConnected = false;
        logger.info("{} {} is not connected now", getMade(), getModel());
    }

    @Override
    public boolean getNetworkStatus() {
        return isConnected;
    }
}