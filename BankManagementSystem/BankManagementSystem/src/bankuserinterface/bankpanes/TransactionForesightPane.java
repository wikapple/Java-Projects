package bankuserinterface.bankpanes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import bankmanagementsystem.*;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/*Sub-Pane for Transaction Pane. Shows balance, transaction amount, and ending balance as a math equation
 * 
 * Author: William Applegate
 * INFO-C210
 */
public class TransactionForesightPane extends VBox{
	/*Data Fields*/
	private BigDecimal transactionAmount;
	private boolean isFeeApplied;
	private double transactionFee;
	private BigDecimal accountValue;
	private BigDecimal transactionSum;
	
	/*Constructor*/
	TransactionForesightPane(double lineDistance, Account thisAccount, long transactionAmount){
		super(lineDistance);
		
		this.setPadding(new Insets(10,10,20,10));
		
		/*If transaction amount is 0, then show error message*/
		if(transactionAmount == 0) {
			Label errorMessage = new Label("Invalid transaction amount entered");
			errorMessage.setStyle("-fx-text-fill: red; -fx-background-color: white; -fx-border-color: red;");
			errorMessage.setFont(new Font("Arial Bold", 12));
			this.getChildren().add(errorMessage);
			
		}else {
		
			/*If a transaction fee could be possible check and adjust transaction accordingly*/
			if(thisAccount instanceof ChargesTransactionFee) {
				isFeeApplied = ((ChargesTransactionFee)thisAccount).isFeeCharged();
				if(isFeeApplied) {
					transactionFee = ((ChargesTransactionFee)thisAccount).getTransactionFee();
				}
			}
			/*Assign data fields values*/
			this.accountValue = thisAccount.getBalance().setScale(2, RoundingMode.FLOOR);
			this.transactionAmount = new BigDecimal(transactionAmount);
			this.transactionSum = accountValue.add(this.transactionAmount);
		
			/*Make String values for equation, then put Strings into Text so they can be added to GUI*/
			/*Starting Balance:*/
			String startingBalanceString = this.accountValue.toString() + "\tBeginning Balance";
			this.getChildren().add(new Text(startingBalanceString));
			
			/*If there is a fee, otherwise don't add this line*/
			if(isFeeApplied) {
				String feeApplicationString = "" + transactionFee + "\tTransactionfee";
				this.getChildren().add(new Text(feeApplicationString));
			};
			
			/*Transaction Amount: (if positive this is a deposit, if negative, this is a withdraw*/
			String transactionAmountString;
			if(this.transactionAmount.compareTo(new BigDecimal("0")) > 0){
				transactionAmountString = "+ " + this.transactionAmount.toString() + "\tDeposit"; 
			}else {
				transactionAmountString = "" + this.transactionAmount.toString() + "\tWithdraw";
			}
			this.getChildren().add(new Text(transactionAmountString));
			
			/*Equals dividing line*/
			String barrierLine = "--------------------";
			this.getChildren().add(new Text(barrierLine));
			
			/*Sum amount (if negative turn Text red)*/
			String sumString;
			boolean isSumNegative = false;

			if(this.transactionSum.compareTo(new BigDecimal("0")) < 0) {
				isSumNegative = true;
			}
			sumString = this.transactionSum.toString();
		
			Text sumText = new Text(sumString);
			if(isSumNegative) {
				sumText.setFill(Color.RED);
			}
			this.getChildren().add(sumText);

		}
		
	}
	

}
