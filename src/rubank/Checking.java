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
        return ANNUAL_INTEREST / 12;
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

    /**
     *
     * @param other the object to be compared.
     * @return 1 if it is equal
     * @author Mychal Ortega
     */
    @Override
    public int compareTo(Account other) {
        return super.compareTo(other);
    }

    /**
     * Setter for Checking
     * @param profile
     * @param quantity
     * @author Ayaan Qayyum
     */
    public Checking(Profile profile, Double quantity) {
        this.holder = profile;
        this.balance = quantity;
    }

    /**
     * Turns account to string
     * @return string
     * @author Ayaan Qayyum
     */
    @Override
    public String toString() {
        return "Checking::" + holder.getFname() + " " + holder.getLname() + " " + holder.getDob() + "::Balance $" + String.format("%.2f", balance);
    }
}
