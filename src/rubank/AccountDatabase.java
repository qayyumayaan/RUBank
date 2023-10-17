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
     * Checks if an account exists in the database.
     * @param account
     * @return true if an account exists in the database.
     * @author Mychal Ortega
     */
    public boolean contains(Account account) {
        return (find(account) != -1);
    }

    /**
     * Opens an account in the database.
     * @param account
     * @return 1 if opening was a success.
     * @author Ayaan Qayyum
     */
    // O  CC   John Doe 2/19/2000 599.99 0
    // O  C    John Doe 2/19/2000 599.99
    public int open(Account account) {
        int accountNum = find(account);
        if (accountNum != -1) { // check if account exists
            return -1;
        }

        // Check for special condition: College Checking and Checking
        for (Account existingAccount : accounts) {
            if (existingAccount != null) {
                Profile existing = existingAccount.getHolder();
                if (existing.equals(account.holder)) {
                    if ((existingAccount instanceof CollegeChecking && account instanceof Checking)) {
                        System.out.println(account.getHolder().getFname() + " " + account.getHolder().getLname() + " " + account.getHolder().getDob() + "(CC) is already in database.");
                        return 0; // Person already has one of these account types
                    } else if (existingAccount instanceof Checking && account instanceof CollegeChecking) {
                        System.out.println(account.getHolder().getFname() + " " + account.getHolder().getLname() + " " + account.getHolder().getDob() + "(C) is already in database.");
                        return 0; // Person already has one of these account types
                    }

                }
            }
        }

        if (accounts.length == numberOfAccounts) grow();

        accounts[numberOfAccounts] = account;
        numberOfAccounts++;

        return 1;
    }

    /**
     * Removes a given account.
     * @return 1 if method is successful
     * @author Ayaan Qayyum
     */
    public boolean close(Account account) {
        int location = find(account);
        if (location == -1) {
            return false; // Account not found
        }
        for (int i = location; i < numberOfAccounts - 1; i++) {
            accounts[i] = accounts[i + 1];
        }
        accounts[numberOfAccounts - 1] = null;
        numberOfAccounts--;
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
            System.out.println("Account balance updated.");
        } else {
            Profile profile = account.getHolder();
            System.out.println(profile.getFname() + " " + profile.getLname() + " " + profile.getDob() + "(" + "accountType" + ") is not in the database.");
        }
    }

    /**
     * Prints sorted by account type and profile.
     * Uses insertion sort.
     * @author Ayaan Qayyum
     */
    public void printSorted() {
        // Sorting logic remains the same
        for (int i = 1; i < numberOfAccounts; i++) {
            Account key = accounts[i];
            int j = i - 1;

            while (j >= 0 && accounts[j].compareTo(key) > 0) {
                accounts[j + 1] = accounts[j];
                j = j - 1;
            }
            accounts[j + 1] = key;
        }

        // Updated printing logic
        for (int i = 0; i < numberOfAccounts; i++) {
            double balance = accounts[i].getBalance();
            String balanceFormatted = String.format("%,.2f", balance);
            Profile holder = accounts[i].getHolder();

            System.out.println(accounts[i].toString());
        }
    }


    /**
     * Prints an account's interest and fees.
     * @author Ayaan Qayyum
     */
    public void printFeesAndInterests() {
        for (int i = 0; i < numberOfAccounts; i++) {
            System.out.println(accounts[i].toString());
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
            double updatedBalance = accounts[i].getBalance() + interest - fee;

            accounts[i].setBalance(updatedBalance);
//            String balanceFormatted = String.format("%,.2f", updatedBalance);
//            Profile holder = accounts[i].getHolder();

            System.out.printf("%s: Interest: %.2f, Fee: %.2f%n",
                    accounts[i].toString(), interest, fee);
        }
    }

    /**
     * Method checks if Account Database is empty
     * @return boolean to show if numberOfAccounts is empty
     * @author Mychal Ortega
     */
    public boolean isEmpty() {
        return numberOfAccounts == 0;
    }


    /**
     * Second test method for test cases of method close()
     * @return false if account in database is not found and not able to close
     * @author Mychal Ortega
     */
    public boolean testCloseAccount_AccountNotFound() {
        AccountDatabase accountDatabase = new AccountDatabase();
        Date bDay = new Date("11/18/2001");
        Date bDay2 = new Date("12/18/2004");
        Profile acct1 = new Profile("Joe", "Smith", bDay);
        Profile acct2 = new Profile("John", "Doe", bDay2);
        Account account1 = new Checking(acct1, 1000.00);
        Account account2 = new Savings(acct2, 2000.0, true);
        //Open the accounts to database before testing the close method
        accountDatabase.open(account1);
        //Try to close an account that is not in the database
        boolean result = accountDatabase.close(account2);

        if (result) {
            return true; // Test failed
        } else {
            return false; // Test passed
        }
    }
    /**
     * Test method for test cases of method close()
     * @return true if account in database is closed
     * @author Mychal Ortega
     */
    public boolean CloseAccount_Success() {
            AccountDatabase accountDatabase = new AccountDatabase();
            Date bDay = new Date("11/18/2001");
            Profile acct1 = new Profile("Joe", "Smith", bDay);
            Account account1 = new Checking(acct1, 1000.0);
            //Open the account before testing the close method
            accountDatabase.open(account1);
            //Try to close the account
            boolean result = accountDatabase.close(account1);

            //Check if the account was closed
            if (result && !accountDatabase.contains(account1)) {
                return true; // Test passed
            } else {
                return false; // Test failed
            }
        }


        public static void main(String[] args) {
           // AccountDatabaseTest test = new AccountDatabaseTest();

            // Run and print the results of the test cases
            AccountDatabase test = new AccountDatabase();
            System.out.println("Test Close() (Account not in database): " + test.testCloseAccount_AccountNotFound());
            System.out.println("Test Close() (Account in database): " + test.CloseAccount_Success());

        }
    }


