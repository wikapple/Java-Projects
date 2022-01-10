package bankmanagementsystem;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

/*
 * AccountLedger Class
 * Responsible for holding ArrayList of all accounts and manipulating the data
 * BankOperator Class calls on this class, this class then calls on Accounts held within it if applicable
 * 
 * Dual purpose class to allow user access, but protect data from passing reference variables
 * 
 * Author: William Applegate
 * Class: INFO-C210
 */

	class AccountLedger{
	
	/*Data Fields*/
	private String ledgerName;
	private ArrayList<Account> accounts;
	private int numberOfCreatedAccounts;
	
	/*Constructors*/
	AccountLedger(){
		this("My Ledger");
	}
	
	AccountLedger(String nameOfLedger){
		accounts = new ArrayList<>();
		this.ledgerName = nameOfLedger;
		this.numberOfCreatedAccounts = 0;
		
	}
	
	AccountLedger(String nameOfLedger, ArrayList<Account> accountList){
		accounts = accountList;
		this.ledgerName = nameOfLedger;
	}
	
	
	/*Getters*/
	String getLedgerName() {
		return this.ledgerName;
	}
	int getNumberOfAccountsCreated() {
		return this.numberOfCreatedAccounts;
	}
	
	/* Add Checking Account
	 * Return true if successful, false if failed
	 */
	boolean addCheckingAccount(int accountNumber, BigDecimal initialDeposit, int customerID, String customerName){
		if(getIndexOfAccount(accountNumber) == -1) {
			CheckingAccount newCheckingAccount = new CheckingAccount(accountNumber, initialDeposit, customerID, customerName);
			accounts.add(newCheckingAccount);
			this.numberOfCreatedAccounts++;
			return true;
		}else {
			return false;
		}
	}
	
	
	/* Add Gold Account 
	 * Return true if successful, false if failed
	 */
	boolean addGoldAccount(int accountNumber, BigDecimal initialDeposit, int customerID, String customerName) {	
		if(getIndexOfAccount(accountNumber) == -1) {
			GoldAccount newGoldAccount = new GoldAccount(accountNumber, initialDeposit, customerID, customerName);
			accounts.add(newGoldAccount);
			this.numberOfCreatedAccounts++;
			return true;
		}else {
			return false;
		}
	}
	
	
	/* Add regular Account
	 * Return true if successful, false if failed
	 */
	boolean addRegularAccount(int accountNumber, BigDecimal initialDeposit, int customerID, String customerName) {
		if(getIndexOfAccount(accountNumber) == -1) {
			RegularAccount newRegularAccount = new RegularAccount(accountNumber, initialDeposit, customerID, customerName);
			accounts.add(newRegularAccount);
			this.numberOfCreatedAccounts++;
			return true;
		}else {
			return false;
		}
	}
	
	
	/* Deposit funds
	 * Return true if successful, false if failed
	 */
	boolean depositFunds(int accountNumber, BigDecimal depositAmount) {
		int index = this.getIndexOfAccount(accountNumber);
		if(index != -1) {
			if(accounts.get(index).depositFunds(depositAmount)) {
				return true;
			}
		}
		return false;
	}
	
	
	/* Withdraw funds
	 * Return true if successful, false if failed
	 */
	boolean withdrawFunds(int accountNumber, BigDecimal withdrawAmount) {
		int index = this.getIndexOfAccount(accountNumber);
		if(index != -1) {
			if(accounts.get(index).withdrawFunds(withdrawAmount)) {
				return true;
			}
		}
		return false;
	}
	
	
	/* Get account information
	 * Take account number to find and print information on account
	 */
	String getAccountInfo(int accountNumber) {
		int index = this.getIndexOfAccount(accountNumber);
		if(index != -1) {
			return accounts.get(index).toString();
		}else
			return "Account not found";
	}
	
	
	/* Remove Account
	 * Take account number and delete that account
	 */
	boolean removeAccount(int accountNumber) {
		int index = this.getIndexOfAccount(accountNumber);
		if(index != -1) {
			accounts.remove(index);
			return true;
		}else {
		return false;
		}
	}
	
	
	/* Apply end of month adjustments
	 * Enhanced for loop calls on each Accounts end of month adjustment method
	 */
	void monthReset() {
		for(Account thisAccount: accounts) {
			thisAccount.monthReset();
		}
	}
	
	
	/*	Methods for bank statistics:	*/
	
	
	/* Get sum of all account balances
	 */
	BigDecimal sumOfAccounts() {
		BigDecimal sum = new BigDecimal(0);
		
		for (Account thisAccount : accounts) {
			sum = sum.add(thisAccount.getBalance());
		}
		return sum;
	}
	
	
	/* Get number of Accounts contained in Accounts 
	 */
	int getNumberOfAccounts() {
		return accounts.size();
	}
	
	
	/* Get average balance value of accounts 
	 */
	BigDecimal getAverageValue() {
		BigDecimal total = sumOfAccounts();
		
		int totalLength = total.toString().length();
		MathContext newMC = new MathContext(totalLength, RoundingMode.HALF_UP);
		String numberOfAccountsString = String.valueOf(getNumberOfAccounts());
		BigDecimal numberOfAccounts = new BigDecimal(numberOfAccountsString);
		BigDecimal averageValue = total.divide(numberOfAccounts, newMC);
		
		return averageValue;
	}
	
	
	/* Get the largest Account balance from accounts
	 */
	Account getLargestValue() {	
		Account largestAccount;
		
		if(accounts.size() > 0) {
			largestAccount = accounts.get(0);
			
			for(int i = 1; i < accounts.size(); i ++) {
				
				if(largestAccount.getBalance().compareTo(accounts.get(i).getBalance()) < 1){
					largestAccount = accounts.get(i);
				}
			}
			return largestAccount.copy();
			
		}else {
			return null;
		}
	}
	
	
	/* Get an ArrayList of accounts with 0 balance
	 */
	ArrayList<Account> getAccountsWith0Balance(){
		ArrayList<Account> zeroBalanceAccounts = new ArrayList<>();
		
		for (Account thisAccount : accounts) {
			
			if(thisAccount.getBalance().compareTo(new BigDecimal(0)) < 1) {
				zeroBalanceAccounts.add(thisAccount);
			}
		}
		return copyAnAccountList(zeroBalanceAccounts);
	}
	
	/*Searching methods*/
	
	boolean accountNumberExists(int accountNumber) {
		if(getIndexOfAccount(accountNumber) == -1) {
			return false;
		}else {
			return true;
		}
	}
	
	
	BigDecimal getAccountBalance(int accountNumber) {
		if(accountNumberExists(accountNumber)) {
			Account thisAccount = searchByAccountNumber(accountNumber);
			return thisAccount.copyBalance();
		}else {
			return null;
		}
	}
	
	
	/*  Takes an account number and returns index of corresponding account
	 */
	private int getIndexOfAccount(int accountNumber) {
		int index = -1;
		
		for(int i = 0 ; i < accounts.size() ; i++) {
			
			if (accounts.get(i).getAccountNumber() == accountNumber) {
				index = i;
			}
		}
		return index;
	}
	
	
	/* Takes an account number and gets corresponding Account
	 */
	Account searchByAccountNumber(int accountNumber) {
		int index = -1;
		
		for(int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountNumber() == accountNumber) {
				index = i;
			}
		}
		if(index >-1) {
			return this.accounts.get(index).copy();
		}else {
			return null;
		}
	}
	
	
	/* Takes customer name and returns list of corresponding accounts 
	 */
	ArrayList<Account> searchAccountsByCustomerName(String customerName) {
		ArrayList<Account> results = new ArrayList<>();
		
		for (Account thisAccount : accounts) {
			if(thisAccount.getCustomerName().equals(customerName)) {
				results.add(thisAccount);
			}
		}
		return copyAnAccountList(results);
	}
	
	/* Takes customerID and returns a list of corresponding accounts
	 */
	ArrayList<Account> searchAccountsByCustomerID(int customerID) {
		ArrayList<Account> queryList = new ArrayList<>();
		
		for (Account thisAccount : accounts) {
			if(thisAccount.getCustomerID() == customerID) {
				queryList.add(thisAccount);
			}
		}
		return copyAnAccountList(queryList);
		
	}
	
	public static ArrayList<Account> copyAnAccountList(ArrayList<Account> realList){
		ArrayList<Account> copyOfList = new ArrayList<>();
		for(Account realAccount: realList) {
			Account accountCopy = realAccount.copy();
			copyOfList.add(accountCopy);
		}
		
		return copyOfList;
	}
	
	ArrayList<Account> copyMainAccountList(){
		ArrayList<Account> copyOfLedger = new ArrayList<>();
		for(Account realAccount : accounts) {
			Account accountCopy = realAccount.copy();
			copyOfLedger.add(accountCopy);
		}
		
		return copyOfLedger;
	}
	
	
	/*Account Ledger toString method*/
	@Override
	public String toString() {
		String value = "Bank Ledger Name: " + this.ledgerName + "\tTotal Accounts Created: " + this.numberOfCreatedAccounts + "\n";
		value += accounts.toString();
		return value;
	}
}
