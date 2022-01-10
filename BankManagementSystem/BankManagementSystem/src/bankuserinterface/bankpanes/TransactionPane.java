package bankuserinterface.bankpanes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import bankmanagementsystem.*;
import bankuserinterface.BankFXController;
/*
 * Transaction Pane for entering transactions both withdraw and deposit
 * 
 * Author: William Applegate
 * INFO-C210
 */
public class TransactionPane extends BankBasePane {
	/*data fields*/
	private String accountFoundString;
	private String accountInfoString;
	private boolean accountFound;
	private long transactionValue;
	private VBox rightPane;
	private Account thisAccount;
	private TransactionForesightPane futurePane;
	private RadioButtonVBox leftPane;
	private Button entryBtn;
	private Button cancelBtn;
	private boolean isDeposit;
	private boolean isWithdraw;
	
	/*Constructor*/
	public TransactionPane(BankManagementSystem bankSystem){
		/*Title, Subtitle*/
		super("Transactions","Enter information for transaction");
		/*Formatting/style*/
		subFont = new Font("Arial", 14);
		GridPane menu = new GridPane();
		menu.setVgap(15);
		menu.setHgap(60);
		menu.setPrefWidth(200);
		double textFieldWidth = 200;
		double labelWidth = 125;
		menu.setPrefHeight(40);
		menu.setAlignment(Pos.TOP_CENTER);
		menu.setPadding(new Insets(20,10,10,10));
		
		/*Input fields*/
		InputFieldHBox accountNumberInput = new InputFieldHBox(10, "AccountNumber", 14);
		accountNumberInput.setChildPrefHeight(menu.getPrefHeight());
		accountNumberInput.setLabelPrefWidth(labelWidth);
		accountNumberInput.setTextFieldPrefWidth(textFieldWidth);
		accountNumberInput.setLabelStyle("-fx-background-color: white; -fx-border-color: black;");
		accountNumberInput.setAlignment(Pos.CENTER);
		accountNumberInput.setFont(new Font("Arial", 14));

		accountNumberInput.getLabel().setTextAlignment(TextAlignment.RIGHT);
		
		/*Label shows whether account is found or not*/
		Label accountFoundLabel = new Label("Account not found");
		accountFoundLabel.setPrefWidth(labelWidth);
		accountFoundLabel.setPrefHeight(menu.getPrefHeight());
		accountFoundLabel.setFont(new Font("Arial Bold", 16));
		accountFoundLabel.setTextAlignment(TextAlignment.RIGHT);
		accountFoundLabel.setAlignment(Pos.TOP_CENTER);
		accountFound = false;
		Text accountInformation = new Text("Account Information will\nshow here");
		accountInformation.setFont(new Font("Arial", 14));
	
		accountInformation.setTextAlignment(TextAlignment.LEFT);
		
		/*check if account is found every time a key is typed into field. If found, change the corresponding values*/
		accountNumberInput.getTextField().setOnKeyTyped(c->{
				int accountNumberInp = accountNumberInput.getIntValue();
				if(accountNumberInp > 0) {
					if(bankSystem.doesAccountNumberExist(accountNumberInp)) {
						accountFound = true;
						accountFoundString = "Account found!";
						accountInfoString = bankSystem.getAccountInfo(accountNumberInp);
						this.thisAccount = bankSystem.getAccount(accountNumberInp);
						if(isEntryValid()) {
						this.showTransactionPane();
						}
					}else {
						accountFound = false;
						accountFoundString ="Account not found";
						accountInfoString = "Account number entry does not match\nany current accounts";
						this.thisAccount = null;
						}
				}else {		
			
				accountFound = false;
				accountFoundString = "Invalid entry";
				accountInfoString = "Make sure account number entry is valid";
				this.thisAccount = null;
				}
				accountFoundLabel.setText(accountFoundString);
				accountInformation.setText(accountInfoString);
			
			
		});
		
		menu.add(accountNumberInput,0,0);
		
		/*Dollar amount input field*/
		InputFieldHBox dollarAmount = new InputFieldHBox(10, "Dollar Amount", 14);
		dollarAmount.setChildPrefHeight(menu.getPrefHeight());
		dollarAmount.setLabelPrefWidth(labelWidth);
		dollarAmount.setTextFieldPrefWidth(textFieldWidth);
		dollarAmount.setLabelStyle("-fx-background-color: white; -fx-border-color: black;");
		dollarAmount.setAlignment(Pos.CENTER);
		dollarAmount.setFont(new Font("Arial", 14));

		dollarAmount.getLabel().setTextAlignment(TextAlignment.RIGHT);
		menu.add(dollarAmount,0,1);
		

		
		
		/*Add transaction menu to the mainBorderPane*/
		this.mainBorderPane.setCenter(menu);
		
		
		/*RightPane setup for transaction information*/
		rightPane = new VBox(10);
		rightPane.setPadding(new Insets(10,10,10,10));
		rightPane.setStyle("-fx-background-color: white");
		rightPane.setStyle("-fx-border-color: black;");
		rightPane.setPrefWidth(300);
		rightPane.setPrefHeight(20);
				rightPane.setAlignment(Pos.BASELINE_CENTER);
		rightPane.getChildren().addAll(accountFoundLabel, accountInformation);
		accountFoundLabel.setPrefWidth(rightPane.getPrefWidth());
		
		this.mainBorderPane.setRight(rightPane);
		
		
		/*LeftPane setup for withdraw/deposit radio button toggle*/
		this.leftPane = new RadioButtonVBox(25, "Deposit", "Withdraw");
		leftPane.setAlignment(Pos.TOP_CENTER);
		this.mainBorderPane.setLeft(leftPane);
		
		/*If user makes change to radio button toggle, update the transaction*/
		leftPane.getButton(0).setOnAction(m ->{
			this.isDeposit = true;
			this.isWithdraw = false;
			if(this.transactionValue > 0 && accountFound) {
				showTransactionPane();
			}
		});
		leftPane.getButton(1).setOnAction(n->{
			this.isDeposit = false;
			this.isWithdraw = true;
			if(this.transactionValue > 0 && accountFound) {
				showTransactionPane();
			}
		});
		
		
		

		
		/*update transaction everytime a key is typed into dollar amount field*/
		dollarAmount.getTextField().setOnKeyTyped(a ->{
		this.transactionValue = Math.abs(dollarAmount.getLongValue());
		showTransactionPane();
		});
		
		/*Button creation*/
		this.entryBtn = BankFXController.createButton("Enter Transaction", "green", 14);
		this.cancelBtn = BankFXController.createButton("Cancel", "red", 14);
		
		HBox entryButtons = new HBox(15);
		entryButtons.getChildren().addAll(entryBtn, cancelBtn);
		entryButtons.setAlignment(Pos.BASELINE_RIGHT);
		menu.add(entryButtons, 0, 2);
		
	}
	
	/*show Transacion pane shows transaction information in right pane according to global values*/
	void showTransactionPane() {
		try {
			if(this.futurePane != null) {
				rightPane.getChildren().remove(this.futurePane);	
			}
			
		}finally {
		long dollarValue =this.transactionValue;
		
		if(this.leftPane.getButton(1).isSelected()) {
			dollarValue = this.transactionValue * -1;
		}
		this.futurePane = new TransactionForesightPane(10, this.thisAccount, dollarValue);
		futurePane.setAlignment(Pos.BOTTOM_CENTER);
		rightPane.getChildren().add(futurePane);
		}
	}
	
	/*checks if user input is a valid entry*/
	public boolean isEntryValid() {
		if(accountFound && (this.transactionValue != 0)) {
			if((isWithdraw && !isDeposit)||(!isWithdraw && isDeposit)) {
				return true;
			}
		}
			return false;
	}
	
	/*Getters*/
	public Button getEnterButton() {
		return this.entryBtn;
	}
	public Button getCancelButton() {
		return this.cancelBtn;
	}
	public long getTransactionValue(){
		return this.transactionValue;
	}
	public int getAccountNumber() {
		return this.thisAccount.getAccountNumber();
	}
	public boolean getIfDeposit() {
		
		return this.isDeposit;
	}
	public boolean getIfWithdraw() {
		
		return this.isWithdraw;
	}
}