package bankmanagementsystem;

import java.math.BigDecimal;
import java.math.RoundingMode;

/* Account Class
 * Abstract class with common account methods and data fields
 * 
 * Author: William Applegate
 * Class: INFO-C210
 */
public abstract class Account implements Comparable<Account>, Copeable<Account>{
	
	/*Data Fields*/
	private int accountNumber;
	protected BigDecimal balance;
	private int customerID;
	private String customerName;
	
	
	/*Constructors*/
	Account(int accountNumber, BigDecimal initialDeposit, int customerID, String customerName){
		this.accountNumber = accountNumber;
		this.balance = new BigDecimal(initialDeposit.toString());
		this.customerID = customerID;
		this.customerName = customerName;
	}
	
	
	/*Getters*/
	public int getAccountNumber(){
		return this.accountNumber;
	}
	
	public BigDecimal getBalance() {
		return this.balance.setScale(2, RoundingMode.FLOOR);
	}
	public int getCustomerID() {
		return this.customerID;
	}
	public String getCustomerName() {
		return this.customerName;
	}
	
	
	/*setters*/
	void setBalance(BigDecimal newBalance) {
		this.balance = new BigDecimal(newBalance.toString());
	}
	
	void setBalance(int newBalance) {
		this.balance = new BigDecimal(newBalance);
	}
	
	public void setBalance(double newBalance) {
		this.balance = new BigDecimal(newBalance);
	}
	
	public void setCustomerID(int newCustomerID) {
		this.customerID = newCustomerID;
	}
	public void setCustomerName(String newName) {
		this.customerName = newName;
	}
	
	
	/* Deposit method
	 * Returns true if successful, false if failed
	 */
	public boolean depositFunds(BigDecimal depositAmount) {
		
		BigDecimal transactionFee = new BigDecimal("0");
		if(this instanceof ChargesTransactionFee) {
			if(((ChargesTransactionFee)this).isFeeCharged()){
				transactionFee = new BigDecimal(((ChargesTransactionFee)this).getTransactionFee());	
			}
		}
		
		
		if(depositAmount.compareTo(transactionFee) > 0) {
		this.balance = this.balance.add(depositAmount);
		return true;
		}else {
			return false;
		}
	}
	
	
	/* Withdraw method
	 * Returns true if successful, false if failed
	 */
	public boolean withdrawFunds(BigDecimal withdrawAmount) {
		
		if(this instanceof ChargesTransactionFee){
			if(((ChargesTransactionFee)this).isFeeCharged()){
				BigDecimal transactionFee = new BigDecimal(((ChargesTransactionFee)(this)).getTransactionFee());
			
				BigDecimal withdrawAmountPlusFee = withdrawAmount.add(transactionFee);
			
				if(withdrawAmountPlusFee.compareTo(this.balance)<=0) {
					this.balance = this.balance.subtract(withdrawAmount);
					return true;
				}else {
					return false;
			
				}
			}
		}
		
		/*if able to complete withdraw*/
		if(withdrawAmount.compareTo(this.balance) <= 0) {
			this.balance = this.balance.subtract(withdrawAmount);
			return true;
		/*If no withdraw is possible*/
		}else {
			return false;
		}
	
	}
	
	
	/*Month Reset method*/
	public void monthReset() {
		
	}
	
	public BigDecimal copyBalance() {

		BigDecimal balanceCopy = (new BigDecimal("0")).add(this.balance);
		
		return balanceCopy;
	}
	
	/*toString method*/
	@Override
	public String toString() {
		String value = "Account Number: " + this.accountNumber + "\n";
		value+= "Customer Name: " + this.customerName + "\n";
		value += "Customer ID: " + this.customerID + "\n";
		value += "Account Balance: " + this.balance.setScale(2, RoundingMode.FLOOR) + "\n";

		return value;
	}
	
	
	
	public String getBalanceAsString() {
		return this.balance.setScale(2, RoundingMode.FLOOR).toString();
	}
	
	public static String roundBigDecimal(BigDecimal input) {
		return input.setScale(2, RoundingMode.FLOOR).toString();
	}
	
	
	/*equals method*/
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Account)) {
			return false;
		}
		if(this.accountNumber == ((Account)obj).getAccountNumber()) {
			return true;
			
		}else {
			return false;
		}
	}
	
	@Override
	public int compareTo(Account ac) {
		int difference = this.accountNumber - ac.getAccountNumber();
		if (difference > 0) {
			return 1;
		}else if(difference == 0) {
			return 0;
		}else {
			return -1;
		}
	}
	
	
	
	

}
