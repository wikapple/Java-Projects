package bankuserinterface.bankpanes;

import java.util.ArrayList;

import bankuserinterface.BankFXController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/*
 * Account Creation Pane used for creating new accounts
 * 
 * Author: William Applegate
 * INFO-C210
 */
public class AccountCreationPane extends BankBasePane{
	/*Data Fields (Lots of them)*/
	private ToggleGroup accountTypeSelector;
	private RadioButton checkingRB;
	private RadioButton goldRB;
	private RadioButton regularRB;
	private TextField accountNumberField;
	private TextField customerNameField;
	private TextField customerIDField;
	private TextField initialDepositField;
	private Button returnToMenuButton;
	private Button enterButton;
	private HBox bottomSelectorPane;
	private VBox errorPane;
	private Font gridFont = new Font("Arial", 18);
	/*End of Data Fields*/
	
	/*Constructor*/
	public AccountCreationPane(){
		super("Bank Account Creation", "Enter new bank account information below:");
		
		/*Main center pane instantiation*/
		VBox mainPane = new VBox(10);
		mainPane.setAlignment(Pos.BASELINE_CENTER);
		
		/*Instantiate creation menu to be used to enter new account values*/
		GridPane creationMenu = new GridPane();
		creationMenu.setVgap(20);
		creationMenu.setHgap(20);
		creationMenu.setPadding(new Insets(10,10,10,10));
		creationMenu.setAlignment(Pos.BASELINE_CENTER);
		creationMenu.setPrefWidth(200);
		
		/*Label creation*/
		ArrayList<Label> labelList = new ArrayList<Label>();
		Label accountNumberLabel = new Label(" Account Number: ");
		labelList.add(accountNumberLabel);
		Label customerNameLabel = new Label(" Account Holder Name: ");
		labelList.add(customerNameLabel);
		Label customerIDLabel = new Label(" Customer ID Number: ");
		labelList.add(customerIDLabel);
		Label initialDepositLabel = new Label(" Initial Deposit: ");
		labelList.add(initialDepositLabel);
		
		labelList.forEach(a ->{
			((Label)a).setFont(gridFont);
			((Label)a).setStyle("-fx-background-color: lightgray; -fx-border-color: black;");
			((Label)a).setPrefWidth(creationMenu.getPrefWidth());
		});
		
		/*TextFields*/
		customerNameField = new TextField();
		customerNameField.setPrefWidth(creationMenu.getPrefWidth());
		accountNumberField = new TextField();
		customerIDField = new TextField();
		initialDepositField = new TextField();

		
		/*Add all nodes to the creationMenu Gird Pane*/
		creationMenu.add(customerNameLabel, 0, 0);
		creationMenu.add(customerNameField, 1, 0);
		creationMenu.add(customerIDLabel, 0, 1);
		creationMenu.add(customerIDField, 1, 1);
		creationMenu.add(accountNumberLabel, 3, 0);
		creationMenu.add(accountNumberField, 4, 0);
		creationMenu.add(initialDepositLabel, 3, 1);
		creationMenu.add(initialDepositField, 4, 1);
		
		
		
		
		/*RadioButtons*/
		/*VBox Creation*/
		HBox radioPane = new HBox(10);
		radioPane.setAlignment(Pos.BASELINE_CENTER);
		radioPane.setPadding(new Insets(10,10,10,10));
		
		Label accountTypeLabel = new Label(" Account Type: ");
		accountTypeLabel.setFont(gridFont);
		accountTypeLabel.setStyle("-fx-background-color: lightgray; -fx-border-color: black;");
		accountTypeLabel.setPrefWidth(200);
		
		/*ToggleGroup*/
		accountTypeSelector = new ToggleGroup();
		
		/*Setting up Radio Buttons*/
		ArrayList<RadioButton> radioButtonList = new ArrayList<RadioButton>();
		checkingRB = new RadioButton("Checking Account");
		radioButtonList.add(checkingRB);
		goldRB = new RadioButton("Gold Account");
		radioButtonList.add(goldRB);
		regularRB = new RadioButton("Regular Account");
		radioButtonList.add(regularRB);
		
		radioButtonList.forEach(b ->{
			((RadioButton)b).setTextFill(Color.DARKGREEN);
			((RadioButton)b).setStyle("-fx-border-color: black; -fx-background-color: lightgray;");
			((RadioButton)b).setPadding(new Insets(10,10,10,10));
			((RadioButton)b).setToggleGroup(accountTypeSelector);
		});
		
		radioPane.getChildren().addAll(checkingRB, goldRB, regularRB);
		/*Done setting up Radio Buttons*/
		
		/*finish adding child panes to the main center pane*/
		mainPane.getChildren().addAll(radioPane, creationMenu);
		
		/*Setup bottom pane with two buttons to create account or return to main menu*/
		this.bottomSelectorPane = new HBox(20);
		this.returnToMenuButton = BankFXController.createButton(" Cancel ", " red ", 20);
		this.enterButton = BankFXController.createButton(" Enter ", "darkgreen", 20);
		this.returnToMenuButton.setPrefWidth(150);
		this.enterButton.setPrefWidth(150);
		bottomSelectorPane.getChildren().addAll( this.enterButton, this.returnToMenuButton);
		bottomSelectorPane.setPadding(new Insets(10,10,100,10));
		bottomSelectorPane.setAlignment(Pos.CENTER);
		
		
		this.mainBorderPane.setBottom(bottomSelectorPane);
		this.mainBorderPane.setCenter(mainPane);
		this.errorPane = new VBox(10);
		errorPane.setAlignment(Pos.CENTER);
		errorPane.setPadding(new Insets(10,10,10,10));
		bottomSelectorPane.getChildren().add(errorPane);
	}
	/*End Constructor*/
	
	
	/*Getters*/
	public ToggleGroup getAccountTypeSelector() {
		return accountTypeSelector;
	}

	public RadioButton getCheckingRB() {
		return checkingRB;
	}

	public RadioButton getGoldRB() {
		return goldRB;
	}

	public RadioButton getRegularRB() {
		return regularRB;
	}

	public TextField getAccountNumberField() {
		return accountNumberField;
	}

	public TextField getCustomerNameField() {
		return customerNameField;
	}

	public TextField getCustomerIDField() {
		return customerIDField;
	}

	public TextField getInitialDepositField() {
		return initialDepositField;
	}

	public Button getReturnToMenuButton() {
		return returnToMenuButton;
	}

	public Button getEnterButton() {
		return enterButton;
	}


	public VBox getErrorPane() {
		return errorPane;
	}
	/*end getters*/

	
	/*Error popUp created*/
	public void errorPopUp(String errorMessage) {
		
		
		Label errorLabel = new Label(errorMessage);
		if(errorPane.getChildren().size() < 5){
		
		
		errorLabel.setFont(new Font("Arial Bold", 14));
		errorLabel.setStyle("-fx-text-fill: red; -fx-border-color: red; -fx-background-color: white");
		
		errorLabel.setAlignment(Pos.CENTER);
		this.errorPane.getChildren().add(errorLabel);
		}
	}


	
	
	
}
