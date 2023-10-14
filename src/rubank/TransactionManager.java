package rubank;

import java.util.Objects;
import java.util.Scanner;

public class TransactionManager {
    /**
     * Runs the program, handles input and commands O, C, D, W, P, PI, UB, Q
     * @author Ayaan Qayyum
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

    private AccountDatabase accounts = new AccountDatabase();

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
                case ("O"):
                    caseOpen();
                    break;
                case ("C"):
                    caseClose();
                    break;
                case ("D"):
                    caseDeposit();
                    break;
                case ("W"):
                    caseWithdraw();
                    break;
                case ("P"):
                    caseDisplay();
                    break;
                case ("PI"):
                    caseDisplayWithDetails();
                    break;
                case ("UB"):
                    caseUpdateBalance();
                    break;
                case ("Q"):
                    System.out.println("Transaction Manager is terminated.");
                    scanner.close();
                    return;
                default:
                    System.out.println(command + " is an invalid command!");
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
            if (parts.length <= 5) {
                System.out.println("Command too short!");
                return false;
            }
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
            if (!dob.isOlderThanSixteen() || dob.isTodayOrFuture() || !dob.isValid()) return false;
            profile = new Profile(fname, lname, dob);

            if (command.equals("C")) return true; // break and do not check parts[5], parts[6]

            if (!quantityProcessor()) return false; // parts[5]
            if (accountType.equals("CC")) { // parts[6]
                if(!dob.isYoungerThanTwentyFour()) {
                    System.out.println("DOB invalid: " + dob + " over 24.");
                    return false;
                }
                if (!campusProcessor()) return false;
            } else if (accountType.equals("S")) {
                if (!loyaltyProcessor()) return false;
            } else if (accountType.equals("MM")) {
                isLoyal = true;
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
                if (quantity < 0) {
                    System.out.println("Initial deposit cannot be 0 or negative.");
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Quantity is invalid!");
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
     * @param dobRaw
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
     * Handles command O, Open.
     * @author Ayaan Qayyum
     */
    private boolean caseOpen() {
        try {
            if (!profileProcessor()) return false;
            switch (accountType) {
                case ("C"):
                    caseOpenChecking();
                    break;
                case ("CC"):
                    caseOpenCollegeChecking();
                    break;
                case ("S"):
                    caseOpenSavings();
                    break;
                case ("MM"):
                    caseOpenMoneyMarketSavings();
                    break;
                default:
                    System.out.println("Invalid command!");
//                    John Doe 2/19/2000(CC) opened.
//                John Doe 2/19/2000(CC) opened.
            }
        } catch(Exception e){
            System.out.println("Error in caseOpen: " + e.getMessage());
        }
        System.out.println( profile.getFname() + " " + profile.getLname() + " " + profile.getDob() + "(" + accountType + ") opened.");
        return true;
    }

    /**
     * Handles opening the Checking account.
     * @author Ayaan Qayyum
     */
    private void caseOpenChecking() {
        Checking newAccount = new Checking(profile, quantity);
        accounts.open(newAccount);
        System.out.println("Successfully added " + profile.getFname() + " " + profile.getLname() + " Checking Account. ");
    }

    /**
     * Handles opening the College Checking account.
     * @author Ayaan Qayyum
     */
    private void caseOpenCollegeChecking() {
        CollegeChecking newAccount = new CollegeChecking(profile, quantity);
        accounts.open(newAccount);
    }

    /**
     * Handles opening the Savings account.
     * @author Ayaan Qayyum
     */
    private void caseOpenSavings() {
        Savings newAccount = new Savings(profile, quantity, isLoyal);
        accounts.open(newAccount);
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
            accounts.open(newAccount);
        }
    }


    /**
     * Handles command C, Close.
     * @author Ayaan Qayyum
     */
    private boolean caseClose() {
        try {
            profileProcessor();
            switch (accountType) {
                case ("C"):
                    caseCloseChecking();
                    break;
                case ("CC"):
                    caseCloseCollegeChecking();
                    break;
                case ("S"):
                    caseCloseSavings();
                    break;
                case ("MM"):
                    caseCloseMoneyMarketSavings();
                    break;
                default:
                    System.out.println("Invalid command!");
            }
            System.out.println("Closed account successfully!");
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
                case ("C"):
                    Checking accountToDepositC = new Checking(profile, quantity);
                    accounts.deposit(accountToDepositC);
                    break;
                case ("CC"):
                    CollegeChecking accountToDepositCC = new CollegeChecking(profile, quantity);
                    accounts.deposit(accountToDepositCC);
                    break;
                case ("S"):
                    isLoyal = false;
                    Savings accountToDepositS = new Savings(profile, quantity, isLoyal);
                    accounts.deposit(accountToDepositS);
                    break;
                case ("MM"):
                    MoneyMarket accountToDepositMM = new MoneyMarket(profile, quantity);
                    accounts.deposit(accountToDepositMM);
                    break;
                default:
                    System.out.println("Invalid command!");
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
                case ("C"):
                    Checking accountToWithdrawC = new Checking(profile, quantity);
                    processWithdraw(accountToWithdrawC);
                    break;
                case ("CC"):
                    CollegeChecking accountToWithdrawCC = new CollegeChecking(profile, quantity);
                    processWithdraw(accountToWithdrawCC);
                    break;
                case ("S"):
                    Savings accountToWithdrawS = new Savings(profile, quantity, isLoyal);
                    processWithdraw(accountToWithdrawS);
                    break;
                case ("MM"):
                    MoneyMarket accountToWithdrawMM = new MoneyMarket(profile, quantity);
                    processWithdraw(accountToWithdrawMM);
                    break;
                default:
                    System.out.println("Invalid command!");
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

