package rubank;

public class AccountDatabase {
    private Account[] accounts = new Account[4]; //list of various types of accounts
    private int numberOfAccounts; //number of accounts in the array

    /**
     * Finds an account in the array.
     * @param account
     * @return account number
     */
    private int find(Account account) {
        for (int accountNumber = 0; accountNumber < numberOfAccounts; accountNumber++) {
            if (accounts[accountNumber].equals(account)) {
                return accountNumber;
            }
        }
        return -1;
    }

    /**
     * Increases the account database capacity by 4.
     * @return account number
     * @author Mychal Ortega
     */
    private void grow() {
        Account[] newAccountDatabase = new Account[numberOfAccounts + 4];
        for (int accountNumber = 0; accountNumber < accounts.length; accountNumber++) {
            newAccountDatabase[accountNumber] = accounts[accountNumber];
        }
        accounts = newAccountDatabase;
    }

    /**
     * Searches for an account if it exists in the database.
     *
     * @author Ayaan Qayyum
     */
    public boolean contains(Account account) {
        return (find(account) != -1);
    }

    public boolean open(Account account) {
        if (contains(account)) return false;
        if (accounts.length == numberOfAccounts) grow();

        accounts[numberOfAccounts] = account;
        numberOfAccounts++;

        return true;
    }

    /**
     * Removes a given account.
     *
     * @author Ayaan Qayyum
     */
    public boolean close(Account account) {
        int location = find(account);
        for (int i = location; i < numberOfAccounts - 1; i++) {
            accounts[i] = accounts[i + 1];
        }
        return true;
    }

    /**
     * Returns false if account balance if there is insufficient funds.
     *
     * @author Ayaan Qayyum
     */
    public boolean withdraw(Account account) {
        return account.balance > 0;
//        return (quantity - account.getBalance() >= 0);
        return account.getBalance() > 0;
    }

    /**
     * Deposits specified money into account.
     *
     * @author Ayaan Qayyum
     */
    public void deposit(Account account) {

        if(contains(account)) {
            account.setBalance(account.getBalance() + amount);
        } else {
            System.out.println("Error in the deposit method!");
        }
    }

    /**
     * Prints sorted by account type and profile.
     *
     * Uses bubble sort.
     * @author Ayaan Qayyum
     */
    public void printSorted() {
        for (int i = 0; i < numberOfAccounts - 1; i++) {
            for (int j = 0; j < numberOfAccounts - i - 1; j++) {
                if (accounts[j].compareTo(accounts[j + 1]) > 0) {
                    Account temp = accounts[j];
                    accounts[j] = accounts[j + 1];
                    accounts[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < numberOfAccounts; i++) {
            System.out.println(accounts[i].toString());
        }
    }

    /**
     * Prints account interest and fees.
     *
     * @author Ayaan Qayyum
     */
    public void printFeesAndInterests() {
        for (int i = 0; i < numberOfAccounts; i++) {
            double interest = accounts[i].monthlyInterest();
            double fee = accounts[i].monthlyFee();

            System.out.printf("%s: Interest: %.2f, Fee: %.2f%n",
                    accounts[i].holder, interest, fee);
        }
    }

    /**
     * Prints account updated balance after applying interest and fees.
     *
     * @author Ayaan Qayyum
     */
    public void printUpdatedBalances() {
        for (int i = 0; i < numberOfAccounts; i++) {
            double interest = accounts[i].monthlyInterest();
            double fee = accounts[i].monthlyFee();
            double updatedBalance = accounts[i].balance + interest - fee;

            accounts[i].balance = updatedBalance;

            System.out.printf("%s: Updated Balance: %.2f%n",
                    accounts[i].holder, updatedBalance);
        }
    }
}