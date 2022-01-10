package bankuserinterface.bankpanes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import bankmanagementsystem.*;
import bankuserinterface.BankFXController;

/*
 * Delete Account pane makes user enter a valid account number and press  button 
 * in order to delete an account
 * 
 *  Author: William Applegate
 *  INFO-C210
 */
public class DeleteAccountPane extends BankBasePane{
	/*Data Fields*/
	private Account thisAccount;
	private StackPane centerPane;
	private StackPane rightPane;
	private HBox bottomPane;
	private InputFieldHBox accountEntryBox;
	private Button enterBtn;
	private Button cancelBtn;
	
	/*Constructor*/
	public DeleteAccountPane(BankManagementSystem bankSystem){
		/*Set Title, subtitle*/
		super("Delete an account", "Enter account number to be deleted");
		
		
		/*Format child panes*/
		this.centerPane = new StackPane();
		centerPane.setAlignment(Pos.TOP_CENTER);
		centerPane.setPadding(new Insets(50,10,10, 300));
		
		this.accountEntryBox = new InputFieldHBox(5, "Account Number", 20);
		setupRightPane();
		this.mainBorderPane.setCenter(centerPane);
		
		/*Event handler: Every time the user enters a key into the account textfield, evaluate whether the input is a valid account number*/
		accountEntryBox.getTextField().setOnKeyTyped(a->{
			int accountNumberInput = accountEntryBox.getIntValue();
			if(bankSystem.doesAccountNumberExist(accountNumberInput)) {
				this.thisAccount = bankSystem.getAccount(accountNumberInput);
				setupRightPane();
			}else {
				this.thisAccount = null;
				setupRightPane();
			}
		});
		
		/*Add child panes in*/
		centerPane.getChildren().add(this.accountEntryBox);
		this.mainBorderPane.setCenter(centerPane);
		/*Set up the bottom pane*/
		setupBottomPane();
		
	}

	/*Every time this method is called, it creates the right pane
	 * Right pane checks user input and outputs corresponding information
	 */
	private void setupRightPane() {
		
		this.rightPane = new StackPane();
		rightPane.setPadding(new Insets(10, 20, 10, 10));
		rightPane.getChildren().clear();
		
		Label accountInfoLbl = new Label();
		accountInfoLbl.setPrefHeight(300);
		accountInfoLbl.setPrefWidth(350);
		accountInfoLbl.setFont(new Font("Arial Bold", 18));
		accountInfoLbl.setAlignment(Pos.TOP_CENTER);
		accountInfoLbl.setTextAlignment(TextAlignment.LEFT);
		
		/*Depending on user input for account number field in the center pane:*/
		/*if account number input is valid*/
		if(this.thisAccount != null) { 
			accountInfoLbl.setText(this.thisAccount.toString());
		/*If there is no user input*/
		}else if(accountEntryBox.getTextField().getText().length() == 0) { 
			accountInfoLbl.setText("Account info will show here");
		/*else there is user input that is invalid account number:*/
		}else { 
			accountInfoLbl.setText("Account Number not found");
		}
		
		rightPane.getChildren().add(accountInfoLbl);
		
		this.mainBorderPane.setRight(rightPane);
		
	}
	/*Bottom pane for delete and return to menu buttons*/
	private void setupBottomPane() {
		
		this.bottomPane = new HBox(10);
		bottomPane.setAlignment(Pos.TOP_CENTER);
		bottomPane.setPadding(new Insets(10,10,100,10));
		this.enterBtn = BankFXController.createButton("Delete Account", "red", 20);
		this.cancelBtn = BankFXController.createButton("Return to Menu", "darkblue", 20);
		
		bottomPane.getChildren().addAll(this.enterBtn, this.cancelBtn);
		
		this.mainBorderPane.setBottom(bottomPane);
	}
	
	/*lets parent (controller object) check if user input is a valid account*/
	/*controller checks boolean to decide whether to delete account or not*/
	public boolean isAccountFound() {
		if(this.thisAccount != null) {
			return true;
		}else {
			return false;
		}
	}
	
	/*getters*/
	public Button getEnterButton() {
		return this.enterBtn;
	}
	public Button getCancelButton() {
		return this.cancelBtn;
	}
	
	public int getAccountNumber() {
		if(this.isAccountFound()) {
			return this.thisAccount.getAccountNumber();
		}else {
			return -1;
		}
	}
}
