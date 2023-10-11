package rubank;

public class Savings extends Account {
    protected boolean isLoyal;
    public static final double savingsInterest = 4.0;

    public static final double savingsInterestLoyal = .25;

    private final double MONTHLY_FEE = 25;


    @Override
    public double monthlyInterest() {
        if (isLoyal) return savingsInterest + savingsInterestLoyal;
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
