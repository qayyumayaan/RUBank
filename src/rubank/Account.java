package rubank;

public abstract class Account implements Comparable<Account> {
    protected Profile holder;
    protected double balance;

    public abstract double monthlyInterest();

    public abstract double monthlyFee();

    /**
     * @param other the object to be compared.
     * @author Ayaan Qayyum
     */

    @Override
    public int compareTo(Account other) {
        String thisClassName = this.getClass().getSimpleName();
        String otherClassName = other.getClass().getSimpleName();

        int typeComparison = thisClassName.compareTo(otherClassName);

        if (typeComparison != 0) {
            return typeComparison;
        }

        // Assuming the Profile class has a proper compareTo method
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
     * @param balance for amount
     * @author Ayaan Qayyum
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public abstract String toString();

}
