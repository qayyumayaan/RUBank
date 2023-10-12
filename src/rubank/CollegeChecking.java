package rubank;

public class CollegeChecking extends Checking {
    private final double MONTHLY_FEE = 0;

    private final double ANNUAL_INTEREST = 1.0;

    private Campus campus;

    /**
     * Checks if account balance is >= 1000, else applies fee.
     * @return MONTHLY_FEE
     * @author Ayaan Qayyum
     */
    @Override
    public double monthlyFee() {
        return MONTHLY_FEE;
    }

    public double monthlyInterest() {
        return ANNUAL_INTEREST / 12;
    }
}
