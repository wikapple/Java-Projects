package bankmanagementsystem;

import java.math.BigDecimal;


/* GoldAccount Class
 * Extends Account, applies Gold Account specifications 
 * 
 * Author: William Applegate
 * Class: INFO-C210
 * 
 */
class GoldAccount extends Account{
	
	
	/*Data Fields*/
	private double interestRate;
	
	
	/*Constructor*/
	public GoldAccount(int accountNumber, BigDecimal initialDeposit, int customerID, String customerName) {
		super(accountNumber, initialDeposit, customerID, customerName);
		this.interestRate = 0.05;
	}

	
	/*Getter*/
	public double getInterestRate() {
		return this.interestRate;
	}


	/* Override withdraw method to allow unlimited withdraws
	 * regardless of balance for Gold Account.
	 */
	@Override
	public boolean withdrawFunds(BigDecimal withdrawAmount) {
		this.setBalance(this.getBalance().subtract(withdrawAmount));
		return true;
	}
	
	/* Apply end of month adjustments
	 * Adds monthly interest to gold account balance
	 */
	@Override
	public void monthReset() {
		double monthlyInterest = this.interestRate / 12;
		BigDecimal earnedInterest = this.getBalance().multiply(new BigDecimal(monthlyInterest));
		this.depositFunds(earnedInterest);
	}
	@Override
	public Account copy() {
		int accountNumberCopy = this.getAccountNumber();
		String customerNameCopy = this.getCustomerName();
		int customerIDCopy = this.getCustomerID();
		BigDecimal balanceCopy = this.copyBalance();
		
		GoldAccount accountCopy = new GoldAccount(accountNumberCopy, balanceCopy, customerIDCopy, customerNameCopy);
		return accountCopy;
	}
	
	/*toString method*/
	@Override
	public String toString() {
		String value = "Gold Account\n";
		value += super.toString();
		value += "Interest Rate: " + this.interestRate + "\n";
		return value;
	}
}
