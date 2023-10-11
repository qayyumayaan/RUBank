package rubank;

public class MoneyMarket extends Account{
    private int withdrawal; // number of withdrawals
    public static final double ANNUAL_INTEREST = 4.75;
    public static final double MONTHLY_FEE = 25.0;

    @Override
    public double monthlyInterest() {
        return moneyMarketAnnualInterest;
    }

    @Override
    public double monthlyFee() {
        if (balance >= 2000) return 0;
        else return moneyMarketMonthlyFee;
    }

    @Override
    public int compareTo(Account o) {
        return 0;
    }

}
