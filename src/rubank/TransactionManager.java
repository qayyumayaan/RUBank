package rubank;

import java.util.Objects;
import java.util.Scanner;

public class TransactionManager {
    /**
     * Runs the program, handles input and commands O, C, D, W, P, PI, UB, Q
     */

    Scanner scanner = new Scanner(System.in);
    String command;
    String[] parts;

    String accountType;
    Profile profile;
    Double quantity;
    Campus campusCode;
    Boolean isLoyal;
    String[] accountTypes = {"C", "CC", "S", "MM"};

    private final AccountDatabase accounts = new AccountDatabase();

    private static final int MONEY_MARKET_MINIMUM = 2000;


    /**
     * Run method to handle inputs.
     * @author Ayaan Qayyum
     */
    public void run() {
        System.out.println("Transaction Manager running...");
        while (!Objects.equals(command, "Q")) {
            String input = scanner.nextLine();
            parts = input.split("\\s+");
            if (parts.length == 0) {
                System.out.println("Invalid input!");
                continue;
            }
            command = parts[0];
            switch (command) {
                case ("O") -> caseOpen();
                case ("C") -> caseClose();
                case ("D") -> caseDeposit();
                case ("W") -> caseWithdraw();
                case ("P") -> caseDisplay();
                case ("PI") -> caseDisplayWithDetails();
                case ("UB") -> caseUpdateBalance();
                case ("Q") -> {
                    System.out.println("Transaction Manager is terminated.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid command!");
            }
        }
    }

    /**
     * Takes inputs and puts it into account format.
     * Command format:
     * command, accountType, fname, lname, dob, quantity, (campusCode or isLoyal) .
     * @author Ayaan Qayyum
     */


    // college checking parts[6] is campus code
    // savings parts[6] is isLoyal
    // monkey market is loyal by default

    private boolean profileProcessor() {
        try {

            if (parts.length >= 8) {
                System.out.println("Command too long!");
                return false;
            }
            if (!accountTypeChecker()) System.out.println("Account type does not match acceptable!");

            accountType = parts[1];
            String fname = parts[2];
            String lname = parts[3];
            if (!dateChecker(parts[4])) return false;
            Date dob = new Date(parts[4]);
            if (!advancedDateChecker(dob)) return false;
            profile = new Profile(fname, lname, dob);
            if (command.equals("C")) return true; // break and do not check parts[5], parts[6]

            if (!quantityProcessor()) return false; // parts[5]
            switch (accountType) {
                case "CC" -> {  // parts[6]
                    if (!dob.isYoungerThanTwentyFour()) {
                        System.out.println("DOB invalid: " + dob + " over 24.");
                        return false;
                    }
                    if (!campusProcessor()) return false;
                }
                case "S" -> {
                    if (!loyaltyProcessor()) return false;
                }
                case "MM" -> isLoyal = true;
            }
        } catch (Exception e) {
            System.out.println("Error in account Processor: " + e);
        }
        return true;
    }

    /**
     * Checks if the account type is valid.
     * @return boolean if account type is valid.
     * @author Ayaan Qayyum
     */
    private boolean accountTypeChecker() {
        for (String accountName : accountTypes) {
            if (accountName.equals(parts[1])) return true;
        }
        return false;
    }

    /**
     * Checks if the quantity specified is valid and processes it.
     * @return if quantity is valid.
     * @author Ayaan Qayyum
     */
    private boolean quantityProcessor() {
        if (parts.length >= 6) {
            try {
                quantity = Double.parseDouble(parts[5]);
                if (quantity <= 0) {
                    if (command.equals("O")) {
                        System.out.println("Initial deposit cannot be 0 or negative.");
                        return false;
                    } else if (command.equals("D")) {
                        System.out.println("Deposit - amount cannot be 0 or negative.");
                        return false;
                    }
                }
            } catch (Exception e) {
                System.out.println("Not a valid amount.");
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the campus code is valid and processes it.
     * @return if correctly processed.
     * @author Ayaan Qayyum
     */
    private boolean campusProcessor() {
        if (parts.length == 7) {
            int campus;
            try {
                campus = Integer.parseInt(parts[6]);
                if (campus != 0 && campus != 1 && campus != 2) {
                    System.out.println("Invalid campus code.");
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Invalid campus code.");
                return false;
            }
            campusCode = Campus.fromCode(campus);
        }
        return true;
    }

    /**
     * Checks if the loyalty code is valid and processes it.
     * @return if loyalty code is valid.
     * @author Ayaan Qayyum
     */
    private boolean loyaltyProcessor() {
        if (parts.length == 7) {
            try {
                isLoyal = Boolean.parseBoolean(parts[6]);
            } catch (Exception e) {
                System.out.println("Loyalty code is invalid!");
//                System.out.println("");
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the date format is correct
     * Priming for another check later in the command process.
     * @param dobRaw dob as entered from command line.
     * @return if date is formatted correctly.
     */
    private boolean dateChecker(String dobRaw) {
        int count = 0;
        for (char c : dobRaw.toCharArray()) {
            if (c == '/') {
                count++;
            }
        }
        return count == 2;
    }

    /**
     * Checks if the date satisfies requirements for account processing using Date class.
     * @param dob dob after processed to prevent fatal formatting errors.
     * @return if date is formatted correctly.
     * @author Ayaan Qayyum
     */
    private boolean advancedDateChecker(Date dob) {
        if (!dob.isValid()) {
            System.out.println("DOB invalid: " + dob + " not a valid calendar date!");
            return false;
        }

        if(dob.isTodayOrFuture()) {
            System.out.println("DOB invalid: " + dob + " cannot be today or a future day.");
            return false;
        }

        if(!dob.isOlderThanSixteen()) {
            System.out.println("DOB invalid: " + dob + " under 16.");
            return false;
        }
        return true;
    }


    /**
     * Handles command O, Open.
     * @author Ayaan Qayyum
     */
    private boolean caseOpen() {
        try {
            if (parts.length <= 5) {
                System.out.println("Missing data for opening an account.");
                return false;
            }
            if (!profileProcessor()) return false;
            switch (accountType) {
                case ("C") -> caseOpenChecking();
                case ("CC") -> caseOpenCollegeChecking();
                case ("S") -> caseOpenSavings();
                case ("MM") -> caseOpenMoneyMarketSavings();
                default -> System.out.println("Invalid command!");
            }
        } catch(Exception e){
            System.out.println("Error in caseOpen: " + e.getMessage());
        }
        return true;
    }

    /**
     * Handles opening the Checking account.
     * @author Ayaan Qayyum
     */
    private void caseOpenChecking() {
        Checking newAccount = new Checking(profile, quantity);
        if (accounts.open(newAccount)) {
            System.out.println(profile.getFname() + " " + profile.getLname() + " " + profile.getDob() + "(" + accountType + ") opened.");
        }
    }

    /**
     * Handles opening the College Checking account.
     * @author Ayaan Qayyum
     */
    private void caseOpenCollegeChecking() {
        CollegeChecking newAccount = new CollegeChecking(profile, quantity);
        if (accounts.open(newAccount)) {
            System.out.println(profile.getFname() + " " + profile.getLname() + " " + profile.getDob() + "(" + accountType + ") opened.");
        }
    }

    /**
     * Handles opening the Savings account.
     * @author Ayaan Qayyum
     */
    private void caseOpenSavings() {
        Savings newAccount = new Savings(profile, quantity, isLoyal);
        if (accounts.open(newAccount)) {
            System.out.println(profile.getFname() + " " + profile.getLname() + " " + profile.getDob() + "(" + accountType + ") opened.");
        }
    }

    /**
     * Handles opening the Money Market Savings account.
     * @author Ayaan Qayyum
     */
    private void caseOpenMoneyMarketSavings() {
        if (quantity < MONEY_MARKET_MINIMUM) {
            System.out.println("Minimum of $2000 to open a Money Market account.");
        } else {
            MoneyMarket newAccount = new MoneyMarket(profile, quantity);
            if (accounts.open(newAccount)) {
                System.out.println( profile.getFname() + " " + profile.getLname() + " " + profile.getDob() + "(" + accountType + ") opened.");
            }
        }

    }


    /**
     * Handles command C, Close.
     * @author Ayaan Qayyum
     */
    private boolean caseClose() {
        try {
            if (parts.length <= 4) {
                System.out.println("Missing data for closing an account.");
                return false;
            }
            if (!profileProcessor()) return false;
            switch (accountType) {
                case ("C") -> caseCloseChecking();
                case ("CC") -> caseCloseCollegeChecking();
                case ("S") -> caseCloseSavings();
                case ("MM") -> caseCloseMoneyMarketSavings();
                default -> System.out.println("Invalid command!");
            }
            System.out.println( profile.getFname() + " " + profile.getLname() + " " + profile.getDob() + "(" + accountType + ") has been closed.");
        } catch (Exception e) {
            System.out.println("Error in caseClose: " + e.getMessage());
        }
        return true;
    }

    /**
     * Handles closing the Checking account.
     * @author Ayaan Qayyum
     */
    private void caseCloseChecking() {
        Checking accountToClose = new Checking(profile, quantity);
        accounts.close(accountToClose);
    }

    /**
     * Handles closing the College Checking account.
     * @author Ayaan Qayyum
     */
    private void caseCloseCollegeChecking() {
        CollegeChecking accountToClose = new CollegeChecking(profile, quantity);
        accounts.close(accountToClose);
    }

    /**
     * Handles closing the Savings account.
     * @author Ayaan Qayyum
     */
    private void caseCloseSavings() {
        Savings accountToClose = new Savings(profile, quantity, false);
        accounts.close(accountToClose);
    }

    /**
     * Handles closing the Money Market Savings account.
     * @author Ayaan Qayyum
     */
    private void caseCloseMoneyMarketSavings() {
        MoneyMarket accountToClose = new MoneyMarket(profile, quantity);
        accounts.close(accountToClose);
    }


    /**
     * Handles command D, Deposit.
     * @author Ayaan Qayyum
     */
    private boolean caseDeposit () {
        try {
            if (!profileProcessor()) return false;

            switch (accountType) {
                case ("C") -> {
                    Checking accountToDepositC = new Checking(profile, quantity);
                    accounts.deposit(accountToDepositC);
                }
                case ("CC") -> {
                    CollegeChecking accountToDepositCC = new CollegeChecking(profile, quantity);
                    accounts.deposit(accountToDepositCC);
                }
                case ("S") -> {
                    isLoyal = false;
                    Savings accountToDepositS = new Savings(profile, quantity, isLoyal);
                    accounts.deposit(accountToDepositS);
                }
                case ("MM") -> {
                    MoneyMarket accountToDepositMM = new MoneyMarket(profile, quantity);
                    accounts.deposit(accountToDepositMM);
                }
                default -> System.out.println("Invalid command!");
            }
        } catch (Exception e) {
            System.out.println("Error in caseDeposit: " + e.getMessage());
        }
        return true;
    }

    /**
     * Handles command W, Withdraw.
     * @author Ayaan Qayyum
     */
    private boolean caseWithdraw() {
        try {
            if (!profileProcessor()) return false;

            switch (accountType) {
                case ("C") -> {
                    Checking accountToWithdrawC = new Checking(profile, quantity);
                    processWithdraw(accountToWithdrawC);
                }
                case ("CC") -> {
                    CollegeChecking accountToWithdrawCC = new CollegeChecking(profile, quantity);
                    processWithdraw(accountToWithdrawCC);
                }
                case ("S") -> {
                    Savings accountToWithdrawS = new Savings(profile, quantity, isLoyal);
                    processWithdraw(accountToWithdrawS);
                }
                case ("MM") -> {
                    MoneyMarket accountToWithdrawMM = new MoneyMarket(profile, quantity);
                    accountToWithdrawMM.updateNumberOfWithdrawals();
                    processWithdraw(accountToWithdrawMM);
                }
                default -> System.out.println("Invalid command!");
            }
        } catch (Exception e) {
            System.out.println("Error in caseWithdraw: " + e.getMessage());
        }
        return true;
    }

    private void processWithdraw(Account account) {
        boolean success = accounts.withdraw(account);
        if (!success) {
            System.out.println("Insufficient funds or account not found.");
        }
    }

    /**
     * Handles command P, Display.
     * Sort by account type and profile.
     * @author Ayaan Qayyum
     */
    private boolean caseDisplay () {
        try {
            if (accounts.isEmpty()) {
                System.out.println("Account Database is empty!");
                return false;
            }
            accounts.printSorted();
        } catch (Exception e) {
            System.out.println("Error in caseDisplay: " + e.getMessage());
        }
        return true;
    }

    /**
     * Handles command PI, Display With Details.
     * @author Ayaan Qayyum
     */
    private boolean caseDisplayWithDetails () {
        try {
            if (accounts.isEmpty()) {
                System.out.println("Account Database is empty!");
                return false;
            }
            accounts.printFeesAndInterests();
        } catch (Exception e) {
            System.out.println("Error in caseDisplayWithDetails: " + e.getMessage());
        }
        return true;
    }

    /**
     * Handles command UB, Update Balance.
     * @author Ayaan Qayyum
     */
    private boolean caseUpdateBalance () {
        try {
            if (accounts.isEmpty()) {
                System.out.println("Account Database is empty!");
                return false;
            }
            accounts.printUpdatedBalances();
        } catch (Exception e) {
            System.out.println("Error in opening: " + e.getMessage());
        }
        return true;
    }








}

