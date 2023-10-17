package rubank;

public abstract class Account implements Comparable<Account> {
    protected Profile holder;
    protected double balance;

    public abstract double monthlyInterest();

    public abstract double monthlyFee();

    /**
     * @param other the object to be compared.
     * @return
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
     * @param obj checks if obj is equal
     * @author Ayaan Qayyum
     * @return true if it is equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Account account = (Account) obj;
        Profile profile = account.getHolder();

        return this.getHolder().getFname().equals(profile.getFname()) &&
                this.getHolder().getLname().equals(profile.getLname()) &&
                this.getHolder().getDob().equals(profile.getDob());
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
    /**
     * Turns object to string
     * @author Ayaan Qayyum
     */
    @Override
    public abstract String toString();

}
