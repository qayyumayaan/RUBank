package rubank;

public class AccountDatabase {
    private Account[] accounts = new Account[4]; //list of various types of accounts
    private int numberOfAccounts; //number of accounts in the array

    /**
     * Finds an account in the database.
     * @param account
     * @return account number
     * @author Ayaan Qayyum
     */
    private int find(Account account) {
        for (int accountNumber = 0; accountNumber < numberOfAccounts; accountNumber++) {
            if (accounts[accountNumber].compareTo(account) == 0) {
                return accountNumber;
            }
        }
//        System.out.println("Account not found -- AcountDatabase find");
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
     * Opens an account in the database.
     * @param account
     * @return if opening was a success.
     * @author Ayaan Qayyum
     */
    public boolean open(Account account) {
        int accountNum = find(account);
        if (accountNum != -1) {
            System.out.println(account.getHolder().getFname() + " " + account.getHolder().getLname() + " " + account.getHolder().getDob() + "(" + ") already exists.");
            return false;
        }

        // Check for special condition: College Checking and Checking
        for (Account existingAccount : accounts) {
            if (existingAccount != null) {
                if (existingAccount.holder.equals(account.holder) &&
                        ((existingAccount instanceof CollegeChecking && account instanceof Checking) ||
                                (existingAccount instanceof Checking && account instanceof CollegeChecking))) {
                    return false; // Person already has one of these account types
                }
            }
        }

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
        accounts[accounts.length - 1] = null;
        return true;
    }

    /**
     * Withdraws a specified amount from an account.
     * @param account - the account from which to withdraw.
     * @return true if the withdrawal was successful, false otherwise
     * @author Ayaan Qayyum
     */
    public boolean withdraw(Account account) {
        int index = find(account);
        if(index == -1) {
            return false;
        }

        double currentBalance = accounts[index].getBalance();
        double amountToWithdraw = account.getBalance();
        // Assuming the withdrawal amount is set in the passed Account object

        if(amountToWithdraw > currentBalance) {
//            System.out.println("Insufficient funds.");
            return false;
        }

        accounts[index].setBalance(currentBalance - amountToWithdraw);
        return true;
    }

    /**
     * Deposits specified money into account.
     * Checks if account exists first.
     * @author Ayaan Qayyum
     */
    public void deposit(Account account) {
        int index = find(account);

        if(index != -1) {
            accounts[index].setBalance(accounts[index].getBalance() + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    /**
     * Prints sorted by account type and profile.
     * Uses insertion sort.
     * @author Ayaan Qayyum
     */
    public void printSorted() {
        for (int i = 1; i < numberOfAccounts; i++) {
            Account key = accounts[i];
            int j = i - 1;

            while (j >= 0 && accounts[j].compareTo(key) > 0) {
                accounts[j + 1] = accounts[j];
                j = j - 1;
            }
            accounts[j + 1] = key;
        }

        for (int i = 0; i < numberOfAccounts; i++) {
            System.out.println(accounts[i].toString());
        }
    }


    /**
     * Prints an account's interest and fees.
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

    public boolean isEmpty() {
        if (numberOfAccounts == 0) return true;
        return false;
    }
}