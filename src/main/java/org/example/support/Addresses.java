package support;

import java.util.ArrayList;
import java.util.List;

// Class to simulate GoogleMap request
public class Addresses {

    private static final List<Address> allowedAddresses = new ArrayList<>();

    private Addresses() {
    }

    public static List<Address> getAllowedAddresses() {
        return allowedAddresses;
    }

    public static void addAddress(Address address) {
        allowedAddresses.add(address);
    }

    public static void addAddresses(List<Address> addressesToAdd) {
        allowedAddresses.addAll(addressesToAdd);
    }

    public static void removeAddress(int index) {
        allowedAddresses.remove(index);
    }

    // Simulate the googlmap request
    public static void addressExists(Address address) throws AddressNotFoundException {
        for (Address currentAddress : allowedAddresses) {
            if (address.equals(currentAddress)) {
                return;
            }
        }
        throw new AddressNotFoundException("Address not found " + address.getFullAddress());
    }

}
