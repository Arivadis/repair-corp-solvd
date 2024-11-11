package support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IdGenerator {

    private static IdGenerator instance;
    private long idCount = 0;
    private long custIdCount = 0;
    private long repairId = 0;
    private long partsOrder = 0;
    private long delivOrder = 0;
    private long unknown = 0;

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    private String getCallingClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        if (stackTrace.length > 3) {
            return stackTrace[3].getClassName();
        } else {
            return "Unknown";
        }
    }

    private String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.now().format(formatter);
    }

    private long newEmpId() {
        return Long.parseLong("99" + ++idCount);
    }

    private long newCustId() {
        return Long.parseLong("1400" + ++custIdCount);
    }

    private long repairOrdId() {
        return Long.parseLong(getDate() + ++repairId);
    }

    private long partsOrdId() {
        return Long.parseLong(getDate() + ++partsOrder);
    }

    private long deliveryId() {
        return Long.parseLong(getDate() + delivOrder);
    }

    public long createId() {
        String callingClass = getCallingClassName();
        return switch (callingClass) {
            case "staff.Employee" -> newEmpId();
            case "staff.Customer" -> newCustId();
            case "operations.RepairOrder" -> repairOrdId();
            case "operations.PartsOrder" -> partsOrdId();
            case "operations.DeliverOrder" -> deliveryId();
            default -> ++unknown;
        };
    }
}
