public class IdGenerator {

    private Integer idCount = 0;
    private Integer custIdCount = 0;
    private Integer repairId = 0;
    private Integer partsOrder = 0;

    public Integer newEmpId(Integer year) {
        return Integer.parseInt(year + String.valueOf(++idCount));
    }

    public Integer newCustId(Integer year) {
        return Integer.parseInt(year + "00" + ++custIdCount);
    }

    public Integer repairOrdId(Integer custId) {
        return Integer.parseInt(String.valueOf(custId) + ++repairId);
    }

    public Integer partsOrdId(Integer repOrdId) {
        return Integer.parseInt(String.valueOf(repOrdId) + ++partsOrder);
    }
}
