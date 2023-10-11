package rubank;

public class AccountDatabase {
    private Account [] accounts; //list of various types of accounts
    private int numAcct; //number of accounts in the array
    private int find(Account account) {
        //search for an account in the array
        for (int i = 0; i < numAcct; i++){
            if(accounts[i].equals(account)){
                return i;
            }
        }
        return 0;
    }

    private void grow(){
        //increase the capacity by 4
        Account[] newA = new Account[numAcct+4];
        for(int i = 0; i< accounts.length; i++){
            newA[i] = accounts[i];
        }
        accounts = newA;
        }
    public boolean contains(Account account) {
        //overload if necessary
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
    public void deposit(Account account){}
    public void printSorted() {
        //sort by account type and profile
    }
    public void printFeesAndInterests() {
        //calculate interests/fees
    }
    public void printUpdatedBalances() {
        //apply the interests/fees
    }
