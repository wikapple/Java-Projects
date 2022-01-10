package bankmanagementsystem;




/*
 * Bank Operator Class
 * Provides user interface to a bank operator
 * uses UserInput class to get user input, calls on instance of AccountLedger class to manipulate bank data
 * 
 * Author: William Applegate
 * Class: INFO-C210-
 */
public class BankOperator {
	private String employeeName;
	private String employeePassword;
	
	/*Data Fields*/
	
	/*Constructor*/
	public BankOperator(String employeeName, String employeePassword){
		this.employeeName = employeeName;
		this.employeePassword = employeePassword;
	}
	
	public String getEmployeeName() {
		return this.employeeName;
	}
	
	public boolean passwordAuthentication(String passwordAttempt) {
		if(passwordAttempt.equals(this.employeePassword)){
			return true;
		}else {
			return false;
		}
	}
}