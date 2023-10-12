package rubank;

public abstract class Account implements Comparable<Account> {
    protected Profile holder;
    protected double balance;
    public abstract double monthlyInterest();
    public abstract double monthlyFee();

    @Override
    public int compareTo(Account other) {
        int typeComparison = this.getClass().getName().compareTo(other.getClass().getName());

        if (typeComparison != 0) {
            return typeComparison;
        }

        return this.holder.compareTo(other.holder);

    }


    /**
     * Gets account balance.
     * @param balance
     * @author Ayaan Qayyum
     */
    public double getBalance() {
        return balance;
    }

    public Profile getHolder() {
        return holder;
    }

    /**
     * Updates account balance.
     * @param balance
     * @author Ayaan Qayyum
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

}
