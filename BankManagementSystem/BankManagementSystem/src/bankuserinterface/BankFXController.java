package bankuserinterface;

import bankmanagementsystem.*;
import bankuserinterface.bankpanes.AccountCreationPane;
import bankuserinterface.bankpanes.DataAnalysisPane;
import bankuserinterface.bankpanes.DeleteAccountPane;
import bankuserinterface.bankpanes.LoginPane;
import bankuserinterface.bankpanes.MainMenuPane;
import bankuserinterface.bankpanes.MonthResetPane;
import bankuserinterface.bankpanes.SearchPane;
import bankuserinterface.bankpanes.TransactionPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * BankFXController was created to control which scene is presented to user
 * Main purpose is to respond to events, swap scenes, and call on the Bank Management System to manipulate or view data.
 * 
 * Note: Some of the beginning methods do a lot of pane creation code. I tried to limit the actual pane code to other classes as I went on.
 * 
 * Author: William Applegate
 * INFO-C210
 */
public class BankFXController {
	/*Data Fields*/
	private BankOperator employee;
	private Scene mainMenuScene;
	private BankManagementSystem bankSystem;
	private Stage superStage;
	
	/*Constructor*/
		public BankFXController(BankManagementSystem bankSystemInput, Stage superStage){
			this.bankSystem = bankSystemInput;
			this.superStage = superStage;
			this.superStage.setResizable(false);
			
		}

	
	/*Get login scene*/
	Scene getLoginScene() {
		
		/*Set Stage Title*/
		this.superStage.setTitle("Bank Management System - Employee Login");
		
		/*Instantiate new login pane*/
		LoginPane loginPane = new LoginPane("Login Screen", "Welcome to The Bank Management System", "Sign-In", "Create Account", "Sign In Failed");
		
		
		/*When user attempted to sign in:*/
		loginPane.getButtonOne().setOnAction(e -> {
			
			/*If login successful, assign values to employee global variable and move on to MainMenu*/
			if(this.bankSystem.employeeSignIn(loginPane.getTextFieldOne().getText(), loginPane.getTextFieldTwo().getText())) {
				System.out.println("Login success!");
				BankOperator thisEmployee = bankSystem.getEmployee(loginPane.getTextFieldOne().getText());
				this.employee = thisEmployee;
				this.mainMenuScene = getMainMenu();
				superStage.setScene(this.mainMenuScene);
				
			/*if login isn't successful, show fail text*/
			}else {
				loginPane.setFailVisible(true);
				
			}
		});
		
		/*Move the account creation scene if selected:*/
		loginPane.getButtonTwo().setOnAction(b -> {
			superStage.setScene(this.getEmployeeCreatorScene());
		});
		
		/*Instantiate Scene and return it*/
		Scene loginScene = new Scene(loginPane);
		
		return loginScene;
		
	}
	
	/*Get Employee Creation Scene*/
	 Scene getEmployeeCreatorScene() {
		 	/*Set Title*/
			this.superStage.setTitle("Bank Management System - Employee Login");
			
			/*Instantiate pane for creating employee*/
			LoginPane employeeAccountCreatePane = new LoginPane("Employee Account Creation", "Enter your information:", "Create", "Cancel", "Account Creation Failed\n2 char minimum for name\n1 char minimum for password");
			
			/*When user attempts to create new employee*/
			employeeAccountCreatePane.getButtonOne().setOnAction(e -> {
				
				/*If creation is successful*/
				if(bankSystem.createNewEmployee(employeeAccountCreatePane.getTextFieldOne().getText(), employeeAccountCreatePane.getTextFieldTwo().getText())) {
					
					/*If successful, make sure fail label is invisible*/
					employeeAccountCreatePane.setFailVisible(false);
					
					/*Swap center stage for success notice and return to login button*/
					employeeAccountCreatePane.setupCenterPaneAfterSuccess();
					
					/*when return to menu button pressed*/
					employeeAccountCreatePane.getFinalReturnToMenuBtn().setOnAction(a->{
						superStage.setScene(this.getLoginScene());
					});
				
					/*If account creation is not successful, show failure label*/
				}else {
					employeeAccountCreatePane.setFailVisible(true);
				}
			});
			
			/*return to login button action*/
			employeeAccountCreatePane.getButtonTwo().setOnAction(b -> {
				superStage.setScene(getLoginScene());
			});
			
			/*Instantiate and return scene*/
			Scene userAccountCreationScene = new Scene(employeeAccountCreatePane);
			return userAccountCreationScene;
			
		}
	 
	/*Get Main Menu*/
	 Scene getMainMenu() {
		 
		 this.superStage.setTitle("Bank Management System - " + this.employee.getEmployeeName() + " - Main Menu");

		 MainMenuPane mainMenuPane = new MainMenuPane(this.employee.getEmployeeName());
		 
		 /*handle events when menu buttons are pressed
		  * call on other methods to get next scene and set it 
		  * to Stage
		  */
		 mainMenuPane.getCreateNewAccount().setOnAction(b ->{
			 Scene accountCreationScene = this.getAccountCreationScene();
			 superStage.setScene(accountCreationScene);
		 });
		 
		 mainMenuPane.getTransactionButton().setOnAction(c ->{
			 Scene transactionScene = this.getTransactionScene();
			 superStage.setScene(transactionScene);
		 });
		 
		 mainMenuPane.getAccountSearch().setOnAction(d ->{
			 superStage.setScene(getSearchScene());
		 });
		 
		 mainMenuPane.getRemoveAccount().setOnAction(e->{
			 superStage.setScene(getDeletionScene());
		 });
		 
		 mainMenuPane.getMonthReset().setOnAction(f->{
			 superStage.setScene(getMonthScene());
		 });
		 
		 mainMenuPane.getBankDataButton().setOnAction(g->{
			 superStage.setScene(getBankDataScene());
		 });
		 
		 
		 mainMenuPane.getLogoutButton().setOnAction(a ->{
			 superStage.setScene(this.getLoginScene());
		 });
		 
		/*set scene*/ 
		 Scene mainMenuScene = new Scene(mainMenuPane);
		 
		 return mainMenuScene;
		 
	 }
	 
	 /*Get account creation scene*/
	 Scene getAccountCreationScene() {
		superStage.setTitle("Bank Management System - Account Creation");
		AccountCreationPane creationPane = new AccountCreationPane();
		
		/*When user pressed button to create new account:*/
		creationPane.getEnterButton().setOnAction(a ->{
			
			/*If this boolean value switches to true, even will return user to main menu*/
			boolean isAccountCreated = false;
			
			/*Values that must be filled to instantiate a new account*/
			String customerName;
			int customerID;
			int accountNumber;
			int initialDeposit;
			
			/*Try to get values because parsing may cause a runtime error if textfields contain a non-int*/
			try {
				customerName = creationPane.getCustomerNameField().getText();
				
				String customerIDAsString = creationPane.getCustomerIDField().getText();
				customerID = Integer.parseInt(customerIDAsString);
				
				String accountNumberAsString = creationPane.getAccountNumberField().getText();
				accountNumber = Integer.parseInt(accountNumberAsString);
				
				String initialDepositAsString = creationPane.getInitialDepositField().getText();
				initialDeposit = Integer.parseInt(initialDepositAsString);
				
				/*Check which radio button is selected. Create corresponding type of account*/
				if(creationPane.getCheckingRB().isSelected()) {
					if(bankSystem.createAccount(1, accountNumber, customerName, customerID, initialDeposit)){
						isAccountCreated = true;
					}
				}else if(creationPane.getGoldRB().isSelected()) {
					if(bankSystem.createAccount(2, accountNumber, customerName, customerID, initialDeposit)){
						isAccountCreated = true;
					}
				}else if(creationPane.getRegularRB().isSelected()) {
					if(bankSystem.createAccount(3, accountNumber, customerName, customerID, initialDeposit)){
						isAccountCreated = true;
					}
				}
				/*If boolean value indicates an account was created, return to main menu*/
				if(isAccountCreated) {
						superStage.setScene(mainMenuScene);
						
				/*Otherwise, provide a message:*/
				}else if(creationPane.getAccountTypeSelector().getSelectedToggle() == null) {
						creationPane.errorPopUp("Select an account type");
				}else {
					creationPane.errorPopUp("Account Number Already In Use");
				}
			
			/*If runtime error occurred while parsing, provide error message*/
			}catch(NumberFormatException t) {
				
				creationPane.errorPopUp("Error, check integer values in text fields");
				
			}
		});
		
		/*When user presses the return to menu button*/
		creationPane.getReturnToMenuButton().setOnAction(e->{
			superStage.setScene(this.mainMenuScene);
		});
		
		/*Instantiate Scene*/
		Scene accountCreationScene = new Scene(creationPane);
		return accountCreationScene;
		 
	 }
	 
	 /*Create a transaction scene*/
	 Scene getTransactionScene() {
		 superStage.setTitle("Bank Management System - New Transaction");
		 TransactionPane transactionPane = new TransactionPane(bankSystem);
		 
		 /*When user presses button to enter a transaction:*/
		 transactionPane.getEnterButton().setOnAction(a->{
			 if(transactionPane.isEntryValid()) {
				 int accountNumber = transactionPane.getAccountNumber();
				 long transactionAmount = transactionPane.getTransactionValue();
				 boolean isTransactionSuccessful = false;
				 if(transactionPane.getIfDeposit()) {
					 bankSystem.depositFunds(accountNumber, transactionAmount);
					 isTransactionSuccessful = true;
				 }else if(transactionPane.getIfWithdraw()) {
					 bankSystem.withdrawFunds(accountNumber, transactionAmount);
					 isTransactionSuccessful = true;
				 }
				 
				 if(isTransactionSuccessful) {
					 superStage.setScene(mainMenuScene);
				 }
			 }
		 });
		 
		 /*When user presses the cancel button to return to menu:*/
		 transactionPane.getCancelButton().setOnAction(b->{
			 superStage.setScene(mainMenuScene);
		 });
		 
		 /*Instantiate Scene*/
		 Scene transactionScene = new Scene(transactionPane);
		
		 return transactionScene;
	 }
	 
	 /*Get scene to search all accounts*/
	 Scene getSearchScene() {
		 superStage.setTitle("Bank Management System - Account Search");
		 SearchPane thisSearchPane = new SearchPane(bankSystem.getCopyOfAccountList());
		 
		 thisSearchPane.getReturnButton().setOnAction(a->{
			 superStage.setScene(mainMenuScene);
		 });
		 
		 Scene searchScene = new Scene(thisSearchPane);
		 
		 return searchScene;
	 }
	 
	 /*Get scene to delete accounts*/
	 Scene getDeletionScene() {
		 superStage.setTitle("Bank Management System - Account Deletion");
		 DeleteAccountPane deletePane = new DeleteAccountPane(bankSystem);
		 
		 /*When user presses button to delete account, return to main menu if successful*/
		 deletePane.getEnterButton().setOnAction(a->{
			 if(deletePane.isAccountFound()) {
				 int accountNumber = deletePane.getAccountNumber();
				 if(bankSystem.removeAccount(accountNumber)) {
					 superStage.setScene(mainMenuScene);
				 }
				 
			 }
		 });
		 
		 /*If user presses cancel button, return to main menu:*/
		 deletePane.getCancelButton().setOnAction(b->{
			 superStage.setScene(mainMenuScene);
		 });
		 
		 
		 Scene deleteScene = new Scene(deletePane);
		 
		 return deleteScene;
		 
	 }
	 
	 /*Get scene to apply monthly adjustment*/
	 Scene getMonthScene() {
		 superStage.setTitle("Bank Management System - Apply Monthly Adjustments");
		 MonthResetPane resetPane = new MonthResetPane();
		 
		 /*If user confirms monthly reset, apply change and return to main menu*/
		 resetPane.getConfirmBtn().setOnAction(a->{
			 bankSystem.applyEndOfMonth();
			 this.superStage.setScene(mainMenuScene);
		 });
		 
		 /*If uses presses button to cancel, return to main menu*/
		 resetPane.getCancelBtn().setOnAction(b->{
			 this.superStage.setScene(mainMenuScene);
		 });
		 
		 Scene monthResetScene = new Scene(resetPane);
		 
		 return monthResetScene;
		 
	 }
	
	 /*Get scene to display bank statistics*/
	 Scene getBankDataScene(){
		 superStage.setTitle("Bank Management System - Bank Data Analysis");
		 DataAnalysisPane bankDataPane = new DataAnalysisPane(bankSystem.getBankStats());
		
		/*When return to menu button pressed:*/
		bankDataPane.getReturnToMenuBtn().setOnAction(a->{
			this.superStage.setScene(mainMenuScene);
		});		
		
		Scene bankDataScene = new Scene(bankDataPane);

		return bankDataScene;
	 }
	 
	/*Static button creation method for repeatedly making similar buttons*/	
	public static Button createButton(String btnText, String borderColor, int textSize) {
		Font btnFont = new Font("Arial", textSize);
		Button newButton = new Button(btnText);
		newButton.setFont(btnFont);
		newButton.setStyle("-fx-border-color: " + borderColor + ";");
		return newButton;
	}

		
}
	
	
	
	
	

