package company;

import java.math.BigDecimal;

public interface Rentable {

    void rent(BigDecimal cost);

    void vacate();
}
