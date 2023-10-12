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

    private AccountDatabase accounts = new AccountDatabase();


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

    private void accountProcessor() {
    /**
     * Takes inputs and puts it into account format.
     * @author Ayaan Qayyum
     */
    private void profileProcessor() {
        try {
            if (parts.length >= 5) {
            if (parts.length >= 5 && parts.length <= 7) {
                accountType = parts[1];
                String fname = parts[2];
                String lname = parts[3];
                String dobRaw = parts[4];
                Date dob = new Date(dobRaw);
                profile = new Profile(fname, lname, dob);
                if (parts.length == 6) {
                    quantity = Double.parseDouble(parts[6]);
                }
            }

        } catch (Exception e) {
            System.out.println("Error in account Processor: " + e);
        }
    /**
     * Checks if the account type is valid.
     * @return boolean if account type is valid. 
     * @author Ayaan Qayyum
     */
    private boolean accountTypeChecker() {
        String[] accountTypes = {"C", "CC", "S", "MM"};
        for (String accountName : accountTypes) {
            if (accountName.equals(parts[1])) return true;
        }
        return false;
    }

    /**
     * Checks if the quantity specified is valid and processes it.
     * @return
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
     * @return
     * @author Ayaan Qayyum
     */
    private boolean campusProcessor() {
        if (parts.length == 6) {
            int campus = 0;
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

    private boolean dateChecker(String dobRaw) {
        // this is JUST to make sure it is not completely broken.
        // specific checks come later.
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
                    caseChecking();
                    caseOpenChecking();
                    break;
                case ("CC"):
                    caseCollegeChecking();
                    caseOpenCollegeChecking();
                    break;
                case ("S"):
                    caseSavings();
                    caseOpenSavings();
                    break;
                case ("MM"):
                    caseMoneyMarketSavings();
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

    private void caseChecking() {
    private void caseOpenChecking() {

    }

    private void caseCollegeChecking() {
    private void caseOpenCollegeChecking() {

    }

    private void caseSavings() {
    private void caseOpenSavings() {

    }

    private void caseMoneyMarketSavings() {
    private void caseOpenMoneyMarketSavings() {

    }





    /**
     * Handles command C, Close.
     * @author Ayaan Qayyum
     */
    private boolean caseClose() {
        try {
            // test for invalid command format

        } catch (Exception e) {
            System.out.println("Error in caseClose: " + e.getMessage());
        }
        return true;
    }

    /**
     * Handles command D, Deposit.
     * @author Ayaan Qayyum
     */
    private boolean caseDeposit() {
        try {

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

        } catch (Exception e) {
            System.out.println("Error in caseWithdraw: " + e.getMessage());
        }
        return true;
    }

    /**
     * Handles command P, Display.
     * @author Ayaan Qayyum
     */
    private boolean caseDisplay() {
        try {

        } catch (Exception e) {
            System.out.println("Error in caseDisplay: " + e.getMessage());
        }
        return true;
    }

    /**
     * Handles command PI, Display With Details.
     * @author Ayaan Qayyum
     */
    private boolean caseDisplayWithDetails() {
        try {

        } catch (Exception e) {
            System.out.println("Error in caseDisplayWithDetails: " + e.getMessage());
        }
        return true;
    }

    /**
     * Handles command UB, Update Balance.
     * @author Ayaan Qayyum
     */
    private boolean caseUpdateBalance() {
        try {

        } catch (Exception e) {
            System.out.println("Error in opening: " + e.getMessage());
        }
        return true;
    }








}
