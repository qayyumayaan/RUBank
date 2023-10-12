package rubank;

public abstract class Account implements Comparable<Account> {
    protected Profile holder;
    protected double balance;
    public abstract double monthlyInterest();
    public abstract double monthlyFee();

    /**
     *
     * @param other the object to be compared.
     * @return typeComparison
     * @author Ayaan Qayyum
     */
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
     * @author Ayaan Qayyum
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets account holder.
     * @author Ayaan Qayyum
     */
    public Profile getHolder() {
        return holder;
    }

    /**
     * Sets account balance.
     * @param balance
     * @author Ayaan Qayyum
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

}
