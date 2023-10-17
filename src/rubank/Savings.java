package rubank;

public class Savings extends Account {
    protected boolean isLoyal;
    public static final double ANNUAL_INTEREST = 4.0;

    public static final double ANNUAL_INTEREST_LOYAL_ADDITION = .25;

    private final double MONTHLY_FEE = 25.0;

    /**
     * Initializes Savings.
     * @author Ayaan Qayyum
     */
    public Savings(Profile profile, Double quantity, boolean isLoyal) {
        this.holder = profile;
        this.balance = quantity;
        this.isLoyal = isLoyal;
    }


    /**
     * Returns the monthly interest.
     * @author Ayaan Qayyum
     * @return monthly interest
     */
    @Override
    public double monthlyInterest() {
        double interestRate = ANNUAL_INTEREST;
        if (isLoyal) interestRate += ANNUAL_INTEREST_LOYAL_ADDITION;
        return interestRate / 12;
    }


    /**
     * Returns the monthly fee based on balance.
     * @author Ayaan Qayyum
     * @return monthly fee
     */
    @Override
    public double monthlyFee() {
        if (balance >= 500) return 0;
        else return MONTHLY_FEE;
    }
    /**
     * Method determines if obj is equal to object it is being compared to
     * @param other
     * @return if obj is equal
     * @author Ayaan Qayuum
     */
    @Override
    public int compareTo(Account other) {
        return super.compareTo(other);
    }
    /**
     * Turns account to string
     * @return string
     * @author Ayaan Qayyum
     */
    @Override
    public String toString() {
        return "Savings::" + holder.getFname() + " " + holder.getLname() + " " + holder.getDob() + "::Balance $" + String.format("%.2f", balance) + (isLoyal ? "::is loyal" : "");
    }
}
