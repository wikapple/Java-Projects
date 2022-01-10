package bankmanagementsystem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/* Instances of this class are used to pass a single reference variable
 * from bankmanagementsystem to the GUI in order to output all analysis data
 * 
 * Author: William Applegate
 * INFO-C210
 */
public class BankData {
	/*Data Fields*/
	private BigDecimal sumOfAllAccounts;
	private int numberOfAccounts;
	private  BigDecimal averageValue;
	private Account largestValueAccount;
	private ArrayList<Account> accountsWith0Balance;
	
		/*Constructor*/
		BankData(BigDecimal sumOfAllAccounts, int numberOfAccounts, BigDecimal averageValue, Account largestValueAccount, ArrayList<Account> accountsWith0Balance){
			this.sumOfAllAccounts = sumOfAllAccounts;
			this.numberOfAccounts = numberOfAccounts;
			this.averageValue = averageValue;
			this.largestValueAccount = largestValueAccount;
			this.accountsWith0Balance = accountsWith0Balance;
		}
		
		/*getters*/
		public BigDecimal getSumOfAllAccounts() {
			return sumOfAllAccounts.setScale(2, RoundingMode.FLOOR);
		}
		
		public String getSumOfAccountsString() {
			return sumOfAllAccounts.setScale(2, RoundingMode.FLOOR).toString();
		}

		public int getNumberOfAccounts() {
			return numberOfAccounts;
		}

		public BigDecimal getAverageValue() {
			return averageValue.setScale(2, RoundingMode.FLOOR);
		}

		public Account getLargestValueAccount() {
			return largestValueAccount;
		}

		public ArrayList<Account> getAccountsWith0Balance() {
			return accountsWith0Balance;
		}
		
		
	
}
