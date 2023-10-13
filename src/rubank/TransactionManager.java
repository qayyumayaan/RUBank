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
     * command, accountType, fname, lname, dob, quantity, campusCode.
     * @author Ayaan Qayyum
     */
    private boolean profileProcessor() {
        try {
            if (parts.length < 5) {
                System.out.println("Command too short!");
                return false;
            }
            if (parts.length > 7) {
                System.out.println("Command too long!");
                return false;
            }

            if (!accountTypeChecker()) System.out.println("Account type does not match acceptable!");


            // college checking parts[6] is campus code
            // savings parts[6] is isLoyal
            // monkey market is loyal by default

            accountType = parts[1];
            String fname = parts[2];
            String lname = parts[3];
            if (!dateChecker(parts[4])) return false;
            Date dob = new Date(parts[4]);
            profile = new Profile(fname, lname, dob);

            if (command.equals("C")) return true; // break and do not check parts[5], parts[6]

            if (!quantityProcessor()) return false; // parts[5]

            if (accountType.equals("CC")) { // parts[6]
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
     * @return
     * @return if quantity is valid.
     * @author Ayaan Qayyum
     */
    private boolean quantityProcessor() {
        if (parts.length >= 5) {
            try {
                quantity = Double.parseDouble(parts[6]);
//                System.out.println(quantity);
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
        if (parts.length == 6) {
            int campus;
            try {
                campus = Integer.parseInt(parts[6]);
            } catch (Exception e) {
                System.out.println("Campus code is invalid!");
                return false;
            }
            campusCode = Campus.fromCode(campus);
        }
        return true;
    }

    /**
     * Checks if the campus code is valid and processes it.
     * @return
     * Checks if the loyalty code is valid and processes it.
     * @return if loyalty code is valid.
     * @author Ayaan Qayyum
     */
    private boolean loyaltyProcessor() {
        if (parts.length == 6) {
            try {
                isLoyal = Boolean.parseBoolean(parts[6]);
            } catch (Exception e) {
                System.out.println("Loyalty code is invalid!");
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
                    System.out.println(command + " is an invalid command!");
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
        accounts.open(newAccount);
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

        MoneyMarket newAccount = new MoneyMarket(profile, quantity);
        accounts.open(newAccount);
    }





    /**
     * Handles command C, Close.
     * @author Ayaan Qayyum
     */
    private boolean caseClose () {
        try {
            profileProcessor();
            // test for invalid command format
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
                    System.out.println(command + " is an invalid command!");

            }

//            accounts.close(account);
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
        Savings accountToClose = new Savings(profile, quantity, false);
        accounts.close(accountToClose);
    }



    
    /**
     * Handles command D, Deposit.
     * @author Ayaan Qayyum
     */
    private boolean caseDeposit () {
        try {
            if (!profileProcessor()) return false;

//            Account accountToDeposit = new Checking();

//            accountToDeposit.setBalance(quantity);
//            accounts.deposit(accountToDeposit);
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
//            Account accountToWithdraw = new Account(profile, quantity);
//            boolean success = accounts.withdraw(accountToWithdraw, quantity);
//            if(!success) {
//                System.out.println("Insufficient funds or account not found.");
//            }

//            accounts.withdraw(account);
        } catch (Exception e) {
            System.out.println("Error in caseWithdraw: " + e.getMessage());
        }
        return true;
    }

    /**
     * Handles command P, Display.
     * @author Ayaan Qayyum
     */
    private boolean caseDisplay () {
        try {
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
            accounts.printUpdatedBalances();
        } catch (Exception e) {
            System.out.println("Error in opening: " + e.getMessage());
        }
        return true;
    }








}

