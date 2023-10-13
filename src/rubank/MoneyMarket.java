package rubank;

public class MoneyMarket extends Account{
    private int withdrawal; // number of withdrawals
    public static final double ANNUAL_INTEREST = 4.75;
    public static final double MONTHLY_FEE = 25.0;

    /**
     * Initializes MoneyMarket.
     * @author Ayaan Qayyum
     */

    public MoneyMarket(Profile profile, Double quantity) {
        this.holder = profile;
        this.balance = quantity;
        this.withdrawal = 0;
    }

    /**
     * Returns the monthly interest.
     * @author Ayaan Qayyum
     * @return monthly interest
     */
    @Override
    public double monthlyInterest() {
        return ANNUAL_INTEREST / 12;
    }

    /**
     * Returns the monthly interest.
     * @author Ayaan Qayyum
     * @return monthly fee.
     */
    @Override
    public double monthlyFee() {
        if (balance >= 2000) return 0;
        else return MONTHLY_FEE;
    }

    /**
     * Updates the number of withdrawals.
     * @author Ayaan Qayyum
     * @return new number of withdrawals.
     */
    public int updateNumberOfWithdrawals() {
        return withdrawal++;
    }

    @Override
    }

}
