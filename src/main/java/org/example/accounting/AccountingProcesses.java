package accounting;

import support.CalculationRuntimeException;
import support.EmptyFileException;
import support.InvalidFormatException;
import support.TxtFileDataImporter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class AccountingProcesses {

    private static final TxtFileDataImporter validator = TxtFileDataImporter.getInstance();

    private AccountingProcesses() {
    }

    public static BigDecimal sumOfInvoice(String invoice) throws EmptyFileException, CalculationRuntimeException {
        if (invoice.isEmpty()) {
            throw new EmptyFileException("An empty invoice was given!");
        }
        String formattedInvoice = "";
        try {
            formattedInvoice = validator.fileProcess(invoice);
        } catch (IOException | InvalidFormatException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Illegal argument as file");
        }
        BigDecimal finalCount = BigDecimal.ZERO;
        try {
            ArrayList<String> splittedInvoice = new ArrayList<>(Arrays.asList(formattedInvoice.split("/")));
            for (String now : splittedInvoice) {
                if (now.isEmpty()) {
                    continue;
                }
                finalCount = finalCount.add(new BigDecimal(now));
            }

        } catch (Exception e) {
            throw new CalculationRuntimeException("Could not finish process due to calculation error " + e);
        }

        return finalCount;
    }
}
