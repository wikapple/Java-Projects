package bankuserinterface.bankpanes;

import java.util.ArrayList;
import java.util.Collections;

import bankmanagementsystem.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
/*
 *Account ListPane takes an ArrayList of accounts
 *then returns a pane with a ListView
 *Able to select and scroll the accounts from list
 *
 *Author: William Applegate
 *INFO-C210
 */
public class AccountListPane extends GridPane{
	/*data fields*/
	private ArrayList<Account> filteredList;
	private ListView<String> lv;
	private ScrollPane listScrollPane;
	private Label title;
	
	/*Constructor*/
	public AccountListPane(ArrayList<Account> accountListInput, String titleString){
		
		/*Setup the top title*/
		this.title = new Label(titleString);
		title.setFont(new Font("Arial Bold", 24));
		
		title.setTextAlignment(TextAlignment.CENTER);
		title.setAlignment(Pos.TOP_CENTER);
		title.setPrefWidth(300);
		//title.setPrefHeight(15);
		this.add(title, 0, 0);
		
		/*Processing Account list now*/
		
		this.filteredList = accountListInput;
		
		/*Sort the account list first*/
		Collections.sort(filteredList);
		/*Switch to an observable list of the Accounts basic info*/
		ObservableList<String> accountObsList = getObsList();
		
		/*Setup Listview using the Observable List*/
		this.lv = new ListView<>(accountObsList);
		this.lv.setPrefSize(300, 300);
		
		/*Instantiate the scroll pane which will be visible, uses the ListView above*/
		listScrollPane = new ScrollPane(lv);
		this.add(listScrollPane, 0,1);
		this.setAlignment(Pos.TOP_CENTER);
	}
	/*End Constructor*/
	
	/*Method to swap ArrayList of Accounts into an ObservableList of Strings*/
	private ObservableList<String> getObsList(){
		ObservableList<String> accountStringList = FXCollections.observableArrayList();
		/*Enhanced for loop to run through all account information*/
		for(Account thisAccount: this.filteredList) {
			String accountString = "Acct# " + thisAccount.getAccountNumber() + " - ";
			accountString += "Name: " + thisAccount.getCustomerName() + " - ";
			accountString += "ID# " + thisAccount.getCustomerID() + " - ";
			accountString += "$" + thisAccount.getBalanceAsString();
			
			accountStringList.add(accountString);
		}
		return accountStringList;
	}
	/* Getter for ListView
	 * Needed for selecting accounts
	 */
	public ListView<String> getLV() {
		return this.lv;
	}
	
	/*Change width of this pane if needed*/
	public void setPreferredWidth(double width) {
		this.lv.setPrefSize(width, 300);
		this.title.setPrefWidth(width);
	}
	
	/*Change title font size if needed*/
	public void setFontSize(double fontSize) {
		this.title.setFont(new Font("Arial Bold", fontSize));
	}
	
	/*Change fx style, calls .setStyle() for both listview and the title*/
	public void setFXStyle(String fxStyle) {
		lv.setStyle(fxStyle);
		this.title.setStyle(fxStyle);
	}
	
	
}
