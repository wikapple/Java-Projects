package bankmanagementsystem;

import java.math.BigDecimal;


/* CheckingAccount Class
 * Extends Account, applies checking account specifications
 * 
 * Author: William Applegate
 * Class: INFO-C210
 */
class CheckingAccount extends Account implements ChargesTransactionFee {
	
	
	/*Data Fields*/
	private int totalMonthlyTransactions;
	private int freeTransactionsLimit;
	private double transactionFee;
	
	/*Constructor*/
	CheckingAccount(int accountNumber, BigDecimal initialDeposit, int customerID, String customerName){
		super(accountNumber, initialDeposit, customerID, customerName);
		this.transactionFee = 3;
		this.totalMonthlyTransactions = 0;
		this.freeTransactionsLimit = 3;
	}

	
	/*Getters*/
	public int getMonthlyTransactionTotal() {
		return this.totalMonthlyTransactions;
	}
	
	public double getTransactionFee() {
		return this.transactionFee;
	}
	
	@Override
	public boolean isFeeCharged() {
		if(this.totalMonthlyTransactions >= this.freeTransactionsLimit) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void chargeFee() {
		if (this.totalMonthlyTransactions >= this.freeTransactionsLimit) {
			this.balance = this.balance.subtract(new BigDecimal(transactionFee));
		}
	}
	
	
	/* Deposit method
	 * Applies transaction fee specification to parent method
	 */
	@Override
	public boolean depositFunds(BigDecimal depositAmount){
		if(super.depositFunds(depositAmount)) {
			if(isFeeCharged()) {
				chargeFee();
			}
			return true;
		}else {
			return false;
		}
	}
	
	
	/* Withdraw method
	 * Applies transaction fee specification to parent method
	 */
	@Override
	public boolean withdrawFunds(BigDecimal withdrawAmount) {
		if(super.withdrawFunds(withdrawAmount)) {
			if(isFeeCharged()) {
				chargeFee();
			}
			return true;
		}else {
			return false;
		}
	}
	
	
	/* Apply monthly adjustments
	 * Overrides parent method to reset monthly transactions to 0
	 */
	@Override
	public void monthReset() {
		this.totalMonthlyTransactions = 0;
	}
	@Override
	public Account copy() {
		int accountNumberCopy = this.getAccountNumber();
		String customerNameCopy = this.getCustomerName();
		int customerIDCopy = this.getCustomerID();
		BigDecimal balanceCopy = this.copyBalance();
		
		CheckingAccount accountCopy = new CheckingAccount(accountNumberCopy, balanceCopy, customerIDCopy, customerNameCopy);
		return accountCopy;
	}
	
	
	/*toString method*/
	@Override
	public String toString() {
		String value = "Checking Account\n"; 
		value += super.toString();
		value += "Total Monthly Transactions: " + this.totalMonthlyTransactions + "\n";
		value += "Free Transaction Limit: " + this.freeTransactionsLimit + "\n";
		value +="Fee per transaction: " + this.transactionFee;
		return value;
	}
	
}
