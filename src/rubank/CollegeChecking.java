package rubank;

public class CollegeChecking extends Checking {
    private final double MONTHLY_FEE = 0;

    private final double ANNUAL_INTEREST = 1.0;

    private Campus campus;

    /**
     * Setter for CollegeChecking
     * @param profile
     * @param quantity
     * @param campusCode
     * @author Mychal Ortega
     */
    public CollegeChecking(Profile profile, Double quantity, Campus campusCode) {
        super(profile, quantity);
        this.campus = campusCode;
    }

    /**
     * Checks if account balance is >= 1000, else applies fee.
     * @return MONTHLY_FEE
     * @author Ayaan Qayyum
     */
    @Override
    public double monthlyFee() {
        return MONTHLY_FEE;
    }

    /**
     * Returns the monthly interest.
     * @author Ayaan Qayyum
     * @return monthly interest
     */
    public double monthlyInterest() {
        return ANNUAL_INTEREST / 12;
    }
    /**
     * Turns account to string
     * @return string
     * @author Ayaan Qayyum
     */
    @Override
    public String toString() {
        return "College Checking::" + holder.getFname() + " " + holder.getLname() + " " + holder.getDob() + "::Balance $" + String.format("%.2f", balance) + "::" + campus.toString();
    }


}
