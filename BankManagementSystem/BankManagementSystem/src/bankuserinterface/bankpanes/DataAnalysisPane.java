package bankuserinterface.bankpanes;
import java.util.ArrayList;

import bankmanagementsystem.*;
import bankuserinterface.BankFXController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/*
 * Data Analysis Pane used to show bank data to the user
 * BankData object created specifically to be accessed by this view 
 * 
 * Author: William Applegate
 * INFO-C210
 */
public class DataAnalysisPane extends BankBasePane{
	/*Data Fields*/
	private Button returnToMenuBtn;
	private BankData bankData;
	private static Font mainFont = new Font("Arial Bold", 14);
	private HBox mainPane;
	
	/*Constructor*/
	public DataAnalysisPane(BankData bankData){
		/*set Title, Sub-Title*/
		super("Bank Data Analysis", "Shows Synapsis of Bank Financial Data");
		
		this.bankData = bankData;
		
		/*Formatting this pane*/
		this.mainPane = new HBox(10);
		mainPane.setAlignment(Pos.TOP_CENTER);
		mainPane.getChildren().addAll(this.getLeftPane(), this.getCenterPane(), this.getRightPane());
		
		/*Setting up bottom button to return to menu*/
		StackPane bottomPane = new StackPane();
		bottomPane.setPadding(new Insets(10,10,40,10));
		bottomPane.setAlignment(Pos.TOP_CENTER);
		this.returnToMenuBtn = BankFXController.createButton("Return to Main Menu", "blue", 20);
		returnToMenuBtn.setAlignment(Pos.CENTER);
		returnToMenuBtn.setTextAlignment(TextAlignment.CENTER);
		bottomPane.getChildren().add(returnToMenuBtn);
		
		/*Adding child panes*/
		this.mainBorderPane.setBottom(bottomPane);
		this.mainBorderPane.setCenter(mainPane);
	}
	
	/*Getter*/
	public Button getReturnToMenuBtn() {
		return this.returnToMenuBtn;
	}
	
	/*Basic label creation method*/
	private static Label createLabel(String text) {
		Label newLabel = new Label(text);
		newLabel.setFont(mainFont);
		newLabel.setPrefHeight(15);
		newLabel.setPrefWidth(200);
		newLabel.setTextAlignment(TextAlignment.CENTER);
		newLabel.setPadding(new Insets(4,4,4,4));
		newLabel.setStyle("-fx-background-color: lightgray; -fx-border-color: darkblue;");
		
		return newLabel;
	}
	
	/*Set up the left pane to show basic bank data values directly in Label nodes*/
	private VBox getLeftPane() {
		
		Label sumOfAccountsLabel = createLabel("Sum of All Accounts");
		sumOfAccountsLabel.setTextAlignment(TextAlignment.RIGHT);
		sumOfAccountsLabel.setAlignment(Pos.CENTER_RIGHT);
		Label sumOfAccounts = createLabel("$" + bankData.getSumOfAllAccounts().toString());
		HBox sumOfAccountsHBox = new HBox(1);
		sumOfAccountsHBox.getChildren().addAll(sumOfAccountsLabel, sumOfAccounts);
		
		Label numberOfAccountsLabel = createLabel("Number of Accounts");
		numberOfAccountsLabel.setTextAlignment(TextAlignment.RIGHT);
		numberOfAccountsLabel.setAlignment(Pos.CENTER_RIGHT);
		Label numberOfAccounts = createLabel(String.valueOf(bankData.getNumberOfAccounts()));
		HBox numberOfAccountsHBox = new HBox(1);
		numberOfAccountsHBox.getChildren().addAll(numberOfAccountsLabel, numberOfAccounts);
		
		Label averageValueLabel = createLabel("Average Account Value");
		averageValueLabel.setTextAlignment(TextAlignment.RIGHT);
		averageValueLabel.setAlignment(Pos.CENTER_RIGHT);
		Label averageValue = createLabel("$" + bankData.getAverageValue().toString());
		HBox averageValueHBox = new HBox(1);
		averageValueHBox.getChildren().addAll(averageValueLabel, averageValue);
		
		VBox leftPane = new VBox(20);
		leftPane.setPadding(new Insets(5,5,5,5));
		leftPane.setAlignment(Pos.TOP_CENTER);
		leftPane.getChildren().addAll(sumOfAccountsHBox, numberOfAccountsHBox, averageValueHBox);
		
		return leftPane;
		
	}
	
	/*Create center pane that will show account details for hte largest account in a VBox*/
	private VBox getCenterPane() {
		Label largestAccountLabel = createLabel("Largest Account:");
		largestAccountLabel.setAlignment(Pos.CENTER);
		Label largestAccountInfo = createLabel(bankData.getLargestValueAccount().toString());
		largestAccountInfo.setAlignment(Pos.TOP_CENTER);
		largestAccountLabel.setPrefWidth(250);
		largestAccountInfo.setPrefWidth(250);
		largestAccountInfo.setPrefHeight(175);
		largestAccountInfo.setAlignment(Pos.CENTER);
		
		VBox centerPane = new VBox(1);
		centerPane.setAlignment(Pos.TOP_CENTER);
		centerPane.setPadding(new Insets(5,5,5,5));
		centerPane.getChildren().addAll(largestAccountLabel, largestAccountInfo);
		
		return centerPane;
		
	}
	
	/*Set up right pane which will show a list of accounts with zero balance*/
	private VBox getRightPane() {
		
		ArrayList<Account> accountsWith0Balance = bankData.getAccountsWith0Balance();
		AccountListPane zeroBalanceAccountsList = new AccountListPane(accountsWith0Balance,"Accounts with $0 balance");
		zeroBalanceAccountsList.setPreferredWidth(300);
		zeroBalanceAccountsList.setFontSize(12);
		zeroBalanceAccountsList.setFXStyle("-fx-background-color: lightgray; -fx-border-color: darkblue;");
		zeroBalanceAccountsList.setAlignment(Pos.TOP_RIGHT);
		VBox rightPane = new VBox(5);
		rightPane.getChildren().add(zeroBalanceAccountsList);
		
		return rightPane;
	}
	
	
	
	

}
