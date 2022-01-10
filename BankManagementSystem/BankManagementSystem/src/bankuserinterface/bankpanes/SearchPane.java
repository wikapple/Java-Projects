package bankuserinterface.bankpanes;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import bankmanagementsystem.*;
import bankuserinterface.BankFXController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/*
 * Pane for searching for accounts and lists their info
 * 
 * Author: William Applegate
 * INFO-C210
 */
public class SearchPane extends BankBasePane{
	/*Data Fields*/
	private ArrayList<Account> wholeList;	
	private ArrayList<Account> filteredList;
	private AccountSearchFieldsPane leftPane;
	private Account selectedAccount;
	private int accountNumberInput;
	private String customerNameInput;
	private int customerIDInput;
	private BigDecimal minValueInput;
	private BigDecimal maxValueInput;
	private StackPane rightPane;
	private Button returnToMenuBtn;
	
	/*Constructor*/
	public SearchPane(ArrayList<Account> wholeList){
		super("Account Search", "Select from list or enter search criteria");

		this.wholeList = wholeList;
		rightPane = new StackPane();

		this.filterList();
		
		
		leftPane = new AccountSearchFieldsPane();
		
		this.mainBorderPane.setLeft(leftPane);
		
		/*Complex filter of search results
		 *Any time a key is typed into search fields, update data fields,
		 * then call method to filter search results based on the data field values
		 */
		leftPane.getAccountNumberBox().getTextField().setOnKeyTyped(a->{
			this.accountNumberInput = leftPane.getAccountNumberBox().getIntValue();
			this.filterList();
		});
		
		leftPane.getCustomerNameBox().getTextField().setOnKeyTyped(b->{
			this.customerNameInput = leftPane.getCustomerNameBox().getTextField().getText();
			this.filterList();
		});
		
		leftPane.getCustomerIDBox().getTextField().setOnKeyTyped(c->{
			this.customerIDInput = leftPane.getCustomerIDBox().getIntValue();
			this.filterList();
		});
		
		leftPane.getMinValueBox().getTextField().setOnKeyTyped(d->{
			long inputLong = leftPane.getMinValueBox().getLongValue();
			this.minValueInput = new BigDecimal(inputLong);
			this.filterList();
		});
		
		leftPane.getMaxValueBox().getTextField().setOnKeyTyped(e->{
			long inputLong = leftPane.getMaxValueBox().getLongValue();
			this.maxValueInput = new BigDecimal(inputLong);
			this.filterList();
		});
		
		/*Pane Style and Formatting*/
		this.rightPane.setAlignment(Pos.TOP_LEFT);
		
		this.mainBorderPane.setRight(rightPane);
		
		this.returnToMenuBtn = BankFXController.createButton("Return To Menu", "darkblue", 18);
		this.returnToMenuBtn.setAlignment(Pos.CENTER);
		this.returnToMenuBtn.setTextAlignment(TextAlignment.CENTER);
		
		StackPane bottomPane = new StackPane();
		bottomPane.getChildren().add(returnToMenuBtn);
		bottomPane.setAlignment(Pos.TOP_CENTER);
		bottomPane.setPadding(new Insets(10,10,40,10));
		
		this.mainBorderPane.setBottom(bottomPane);
	}
	
	/*getter*/
	public Button getReturnButton() {
		return this.returnToMenuBtn;
	}
	
	/*Filter ArrayList fileredList based on user input into search fields*/
	private void filterList() {
		this.filteredList = new ArrayList<Account>();
		
		/*Enhanced for loop iterates through the whole account list one time determining if accounts match textfield input*/
		for (Account thisAccount : wholeList){
			/*If account number doesn't match or partial match user input, skip to the next account*/
			if(this.accountNumberInput > 0) {
				if(!BankManagementSystem.doesIntContainInt(accountNumberInput, thisAccount.getAccountNumber())) {
					continue;
				}
			}
			/*If customer name doesn't match or partial match, skip to the next account*/
			if(this.customerNameInput != null && this.customerNameInput.length() > 0) {
				if(!thisAccount.getCustomerName().contains(customerNameInput)) {
					continue;
				}			
			}
			/*If customer ID doesn't at least partially match user input, skip to next account*/
			if(this.customerIDInput > 0) {
				if(!BankManagementSystem.doesIntContainInt(this.customerIDInput, thisAccount.getCustomerID())) {
					continue;
				}
			}
			/*If balance is less than minimum value, skip to the next account*/
			if(this.minValueInput != null && this.minValueInput.compareTo(new BigDecimal("0")) > 0) {
				if(minValueInput.compareTo(thisAccount.getBalance()) > 0) {
					continue;
				}
			}
			/*If balance is more than the maximum value, skip to next account*/
			if(this.maxValueInput != null && this.maxValueInput.compareTo(new BigDecimal("0")) > 0) {
				if(maxValueInput.compareTo(thisAccount.getBalance()) < 1) {
					continue;
				}
			}
			/*If enhanced for loop makes it through all the above checks without skipping, add this account to the filtered list*/
			filteredList.add(thisAccount);
		}
		/*Sort the list based on account number*/
		Collections.sort(this.filteredList);
		/*use this filtered list to call getScrollList method of all the results. make this the center pane*/
		this.mainBorderPane.setCenter(getScrollList());
	}
	
	
	
	/*used ArrayList of filtered accounts and created an AccountListPane of them, sets */
	private AccountListPane getScrollList() {
		/*Right pane shows an account's details selected from scrollable list*/
		/*Clear the right pane if a new scroll list is called, we don't want old account information*/
		rightPane.getChildren().clear();
		
		/*Format the right pane which will depend upon our scroll list*/
		Label accountInfo = new Label("Account info will show here");
		accountInfo.setTextAlignment(TextAlignment.LEFT);
		accountInfo.setAlignment(Pos.TOP_LEFT);
		accountInfo.setFont(new Font("Arial Bold", 18));
		accountInfo.setPrefWidth(300);
		rightPane.setPadding(new Insets(10,20,10,10));
		rightPane.getChildren().add(accountInfo);
		
		/*Instantiate the filtered list based on the filtered search results ArrayList*/
		AccountListPane filteredListPane = new AccountListPane(this.filteredList, "Filtered List");
		filteredListPane.setAlignment(Pos.TOP_CENTER);
		
		/*If user clicks an account from a list, display account info in the right pane*/
		filteredListPane.getLV().getSelectionModel().selectedItemProperty().addListener(a->{
			int index = filteredListPane.getLV().getSelectionModel().getSelectedIndex();
			this.selectedAccount = filteredList.get(index);
			rightPane.getChildren().remove(accountInfo);
			accountInfo.setText(this.selectedAccount.toString());
			rightPane.getChildren().add(accountInfo);
		});
		
		/*Return filtered list*/
		return filteredListPane;	
	}
}
