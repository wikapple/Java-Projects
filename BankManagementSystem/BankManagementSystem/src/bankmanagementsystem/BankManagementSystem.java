package bankmanagementsystem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
/*
 * Bank Management System is the hub between GUI, Bank Operators, and the Accounts
 * All requests to manipulate of access data go through bank management system
 * Further additions such as a read-write ability would expand off of this class
 * 
 * Author: William Applegate
 * INFO-C210
 * */
	public class BankManagementSystem {
	/*Data Fields*/
	private ArrayList<BankOperator> employees;
	private AccountLedger bankLedger;
	
	/*Constructor*/
	public BankManagementSystem(){
		employees = new ArrayList<BankOperator>();
		bankLedger  = new AccountLedger();
	}
	
	/*Checks if a sign in attempt is successful*/
	public boolean employeeSignIn(String nameInput, String passwordInput) {
		
			for(BankOperator employee: employees) {
				System.out.println(employee.getEmployeeName());
				System.out.println("Name entered was: " + nameInput);
				if(employee.getEmployeeName().equals(nameInput)) {
					System.out.println("Employee found!");
					if(employee.passwordAuthentication(passwordInput)) {
						System.out.println("Password success!");
						return true;
				}
			}
		}
			System.out.println("Sign in Failure!");
			return false;
		}
	
	/*Get index of employee in global variable*/
	private int getEmployeeIndex(String nameInput) {
		for(int index = 0; index < employees.size(); index ++) {
			String currentName = employees.get(index).getEmployeeName();
			if(currentName.equals(nameInput)) {
				return index;
			}
		}
			return -1;
	}

	/*Create a new employee*/
	public boolean createNewEmployee(String newEmployeeName, String newPassword) {
		/*name must be at least 2 characters and password at least 1 character*/
		if(newEmployeeName.length() > 1 && newPassword.length() > 0) {
		
		BankOperator newEmployee = new BankOperator(newEmployeeName, newPassword);
		employees.add(newEmployee);
		return true;
		}else {
			return false;
		}
	}
	/*Get a BankOperator object by their name*/
	public BankOperator getEmployee(String employeeName) {
		if(getEmployeeIndex(employeeName) > -1) {
			return employees.get(getEmployeeIndex(employeeName));
		}
		else return null;
	}
	
	/* Menu Option 1
	 * Creates a Checking Account
	 */
	public boolean createAccount(int accountType,int accountNumber, String customerName, int customerID, int initialDeposit) {
		;
		BigDecimal deposit = new BigDecimal(initialDeposit);
		
		boolean isAccountCreated = false;
		
		switch(accountType){
		case 1:
			if(bankLedger.addCheckingAccount(accountNumber, deposit, customerID, customerName)) {
				isAccountCreated = true;
			};
			break;
		case 2:
			if(bankLedger.addGoldAccount(accountNumber, deposit, customerID, customerName)) {
				isAccountCreated = true;
			};
			break;
		case 3:
			if(bankLedger.addRegularAccount(accountNumber, deposit, customerID, customerName)) {
				isAccountCreated = true;
			};
			break;
		default:
			break;
		}	
			return isAccountCreated;
	}

	

	
	
	/* Menu Option 4
	 * Deposit funds if possible
	 */
	public boolean depositFunds(int accountNumber, long depositAmountInput) {
		
		/*Choose deposit amount*/
		
		BigDecimal depositAmount = new BigDecimal(depositAmountInput);
		
		/*deposit cash*/
		if(bankLedger.depositFunds(accountNumber, depositAmount)) {
			System.out.println("Deposit complete");
			return true;
		}else {
			System.out.println("Deposit failed");
			return false;
		}

	}
	
	
	/* Menu Option 5
	 * Withdraw funds if possible
	 */
	public boolean withdrawFunds(int accountNumber, long withdrawAmountInput) {
		/*Choose deposit amount*/
		
		BigDecimal withdrawAmount = new BigDecimal(withdrawAmountInput);
		
		

		/*deposit cash*/
		if(bankLedger.withdrawFunds(accountNumber, withdrawAmount)) {
			System.out.println("withdraw complete");
			return true;
		}else {
			System.out.println("withdraw failed");
			return false;
		}

	}
	
	
	/*
	 *Check if an account number entry is valid 
	 */
	public boolean doesAccountNumberExist(int accountNumber) {
		if(bankLedger.accountNumberExists(accountNumber)) {
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 * Delete Account
	 */
	public boolean removeAccount(int accountNumber) {
		if(!this.doesAccountNumberExist(accountNumber)) {
			System.out.println("Account not found");
		}
		
		if(bankLedger.removeAccount(accountNumber)){
			System.out.println("Account removed");

			return true;
		}else {
			System.out.println("Removal failed");
		}
		System.out.println("Removal failed");
		return false;
	}
	
	/* Menu Option 8
	 * Run end of the month adjustments to all accounts
	 */
	public void applyEndOfMonth() {
		
			bankLedger.monthReset();
	}
	
	/*returns a String of an account balance by searching account number
	 * 
	 */
	public String getAccountBalance(int accountNumber) {
		if(bankLedger.accountNumberExists(accountNumber)) {
			BigDecimal bigDeciBalance = bankLedger.getAccountBalance(accountNumber);
			String roundedBalance = bigDeciBalance.setScale(2, RoundingMode.HALF_UP).toString();
			return roundedBalance;
			
		}else {
			return "Not Applicable";
		}
	}
	
	
	/* Menu Option 9
	 * Display bank statistics if any are available to list
	 */
	public BankData getBankStats() {
		
		if(bankLedger.getNumberOfAccounts() < 1) {
			return null;
		}else {
			BigDecimal sumOfAccounts = bankLedger.sumOfAccounts();
			int numberOfAccounts = bankLedger.getNumberOfAccounts();
			BigDecimal averageValue = bankLedger.getAverageValue();
			Account largestValueAccount = bankLedger.getLargestValue();
			ArrayList<Account> accountsWith0Balance = bankLedger.getAccountsWith0Balance();
			
			BankData bankData = new BankData(sumOfAccounts, numberOfAccounts, averageValue, largestValueAccount, accountsWith0Balance);
			return bankData;
			
		}
		
	}
	/*	Gets a copy of the first account associated to the account number
	 * Does not check for duplicates
	 */
	public Account getAccount(int accountNumber) {
		return this.bankLedger.searchByAccountNumber(accountNumber);
	}
	
	/*Returns the toString method of an account associated to account number parameter
	 * Does not check for duplicates
	 */
	public String getAccountInfo(int accountNumber) {
		return this.bankLedger.getAccountInfo(accountNumber);
	}
	
	public ArrayList<Account> getCopyOfAccountList(){
		return bankLedger.copyMainAccountList();
	}
	
	/*
	 * Static method to check is int value partially matches another int value
	 * only checks beginning and end of whole value 
	 */
	public static boolean doesIntContainInt(int partialInt, int wholeInt) {
		
		
		String partialIntString = String.valueOf(partialInt);
		int partialStringLength = partialIntString.length();
		
		String wholeIntString = String.valueOf(wholeInt);
		int wholeStringLength = wholeIntString.length();
		
		if(partialStringLength > wholeStringLength) {
			return false;
		}
		String beginningOfWholeString = wholeIntString.substring(0, partialStringLength);
		
		String endOfWholeString = wholeIntString.substring(wholeIntString.length()-partialStringLength, wholeIntString.length());
		
		
		if(beginningOfWholeString.equals(partialIntString) || endOfWholeString.equals(partialIntString)) {
		return true;
		}else {
			return false;
		}
		
		
	}
	
	

}
