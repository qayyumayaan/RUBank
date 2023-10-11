package rubank;

public class Checking extends Account {
    private final int MONTHLY_FEE = 12;
    private final double ANNUAL_INTEREST = 1.0;

    /**
     * Returns annual interest.
     * @return ANNUAL_INTEREST
     * @author Ayaan Qayyum
     */
    @Override
    public double monthlyInterest() {
        return ANNUAL_INTEREST;
    }

    /**
     * Checks if account balance is >= 1000, else applies fee.
     * @return MONTHLY_FEE
     * @author Ayaan Qayyum
     */
    @Override
    public double monthlyFee() {
        if (balance >= 1000) return 0;
        else return MONTHLY_FEE;
    }

    @Override
    public int compareTo(Account o) {
        return 0;
    }
}
