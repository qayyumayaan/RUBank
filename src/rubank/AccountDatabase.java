package rubank;

public class AccountDatabase {
    private Account[] accounts; //list of various types of accounts
    private int numberOfAccounts; //number of accounts in the array

    /**
     * @param account
     * @return account number
     */
    private int find(Account account) {
        //search for an account in the array
        for (int accountNumber = 0; accountNumber < numberOfAccounts; accountNumber++) {
            if (accounts[accountNumber].equals(account)) {
                return accountNumber;
            }
        }
        return 0;
    }

    private void grow() {
        //increase the capacity by 4
        Account[] newAccountDatabase = new Account[numberOfAccounts + 4];
        for (int accountNumber = 0; accountNumber < accounts.length; accountNumber++) {
            newA[accountNumber] = accounts[accountNumber];
        }
        accounts = newAccountDatabase;
    }

    /**
     * Searches for an account if it exists in the database.
     *
     * @author Ayaan Qayyum
     */
    public boolean contains(Account account) {
        //overload if necessary
        return (find(account) != -1);
    }

    public boolean open(Account account) {
        //add a new account
    }

    public boolean close(Account account) {
        //remove the given account
    }

    public boolean withdraw(Account account) {
        //false if insufficient fund
        return account.balance > 0;
    }

    public void deposit(Account account) {

    }

    /**
     * Prints sorted by account type and profile.
     *
     * @author Ayaan Qayyum
     */
    public void printSorted() {

    }

    /**
     * Prints account interest and fees.
     *
     * @author Ayaan Qayyum
     */
    public void printFeesAndInterests() {

    }

    /**
     * Prints account updated balance after applying interest and fees.
     *
     * @author Ayaan Qayyum
     */
    public void printUpdatedBalances() {

    }
}