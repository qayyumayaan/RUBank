package rubank;

public class MoneyMarket extends Account{
    private int withdrawal; // number of withdrawals
    public static final double ANNUAL_INTEREST = 4.75;
    public static final double LOYAL_CUSTOMER_ADDITION = 0.25; // Loyal customer addition

    public static final double MONTHLY_FEE = 25.0;
    public static final double WITHDRAWAL_PENALTY = 10.0;

    protected boolean isLoyal;

    /**
     * Initializes MoneyMarket.
     * @author Ayaan Qayyum
     */

    public MoneyMarket(Profile profile, Double quantity) {
        this.holder = profile;
        this.balance = quantity;
        this.withdrawal = 0;
        updateLoyaltyStatus();
    }

    /**
     * Returns the monthly interest, accounting for loyal customers.
     * @author Ayaan Qayyum
     * @return monthly interest
     */
    @Override
    public double monthlyInterest() {
        double interestRate = ANNUAL_INTEREST;
        if (isLoyal) {
            interestRate += LOYAL_CUSTOMER_ADDITION;
        }
        return interestRate / 12;
    }

    /**
     * Returns the monthly fee.
     * @author Ayaan Qayyum
     * @return monthly fee.
     */
    @Override
    public double monthlyFee() {
        if (balance >= 2000) {
            return 0;
        } else if (withdrawal > 3) {
            return WITHDRAWAL_PENALTY + MONTHLY_FEE;
        } else {
            return MONTHLY_FEE;
        }
    }

    /**
     * Updates the number of withdrawals.
     * @author Ayaan Qayyum
     * @return new number of withdrawals.
     */
    public int updateNumberOfWithdrawals() {
        withdrawal++;
        updateLoyaltyStatus();
        return withdrawal;
    }


    @Override
    public int compareTo(Account other) {
        return super.compareTo(other);
    }

    /**
     * Updates the loyalty status based on balance.
     * @author Ayaan Qayyum
     */
    private void updateLoyaltyStatus() {
        if (this.balance >= 2000) {
            this.isLoyal = true;
        } else {
            this.isLoyal = false;
        }
    }

    @Override
    public String toString() {
        return "Money Market::Savings::" + holder.getFname() + " " + holder.getLname() + " " + holder.getDob() + "::Balance $" + String.format("%,.2f", balance)  + (isLoyal ? "::is loyal" : "") + "::withdrawal: " + withdrawal;
    }

}
