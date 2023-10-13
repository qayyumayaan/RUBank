package rubank;

public class Savings extends Account {
    protected boolean isLoyal;
    public static final double savingsInterestAnnual = 4.0;

    public static final double savingsInterestLoyalAnnual = .25;

    private final double MONTHLY_FEE = 25;

    public Savings(Profile profile, Double quantity, boolean isLoyal) {
        this.holder = profile;
        this.balance = quantity;
        this.isLoyal = isLoyal;
    }

    @Override
    public double monthlyInterest() {
        if (isLoyal) return (savingsInterestAnnual + savingsInterestLoyalAnnual) * 1.0 / 12;
        else return savingsInterest;
    }

    @Override
    public double monthlyFee() {
        if (balance >= 500) return 0;
        else return MONTHLY_FEE;
    }

    @Override
    public int compareTo(Account o) {
        return 0;
    }
}
