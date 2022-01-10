package bankuserinterface.bankpanes;

import bankuserinterface.BankFXController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/*
 * Login pane used for account login and account creation windows
 * 
 * Author: William Applegate
 * INFO-C210
 * 
 */
public class LoginPane extends BankBasePane {

	/*Data Fields*/
	private String title;
	private Button buttonOne;
	private Button buttonTwo;
	private TextField textFieldOne;
	private TextField textFieldTwo;
	private Text signInFailure;
	private GridPane loginPane;
	private StackPane signInFailurePane;
	private Button finalReturnToMenuBtn;
	
	/*Constructor*/
	public LoginPane(String title, String subTitle, String buttonOneText, String buttonTwoText, String bottomFailPane){
		super(title, subTitle);
		this.title = title;
		
		setupCenterLoginPane(buttonOneText, buttonTwoText);
		
		setupBottomFailPane(bottomFailPane);
	}
	
	/*Getters*/
	public Button getButtonOne() {
		return buttonOne;
	}

	public Button getButtonTwo() {
		return buttonTwo;
	}
	
	public Button getFinalReturnToMenuBtn() {
		return this.finalReturnToMenuBtn;
	}
	
	public TextField getTextFieldOne() {
		return textFieldOne;
	}

	public TextField getTextFieldTwo() {
		return textFieldTwo;
	}
	
	
	
	
	private void setupCenterLoginPane(String buttonOneText, String buttonTwoText) {
		//Login input
		this.loginPane = new GridPane();
		loginPane.setAlignment(Pos.BASELINE_CENTER);
		loginPane.setPadding(new Insets(75,10,10,10));
		loginPane.setHgap(5);
		loginPane.setVgap(5);
				
				
		Text employeeText = new Text(" Employee Name: ");
		employeeText.setFont(subFont);
		loginPane.add(employeeText, 0, 2);
				
		this.textFieldOne = new TextField("Default Login");
		loginPane.add(textFieldOne, 1, 2);
				
				
		Text passwordText = new Text(" Employee Password: ");
		passwordText.setFont(subFont);
				
		loginPane.add(passwordText, 0, 3);
		this.textFieldTwo = new TextField("Default Password");
		loginPane.add(textFieldTwo, 1, 3);
				
				
		//buttons
		HBox buttons = new HBox(20);
		/*loginAttempt Button*/
		this.buttonOne = BankFXController.createButton(buttonOneText, "green", 25);
		this.buttonOne.setStyle("-fx-background-color: lightgray");
		
		/*Create Account Button*/
		this.buttonTwo = BankFXController.createButton(buttonTwoText, "orange", 25);
		this.buttonTwo.setStyle("-fx-background-color: lightgray");
		
		buttons.getChildren().addAll(buttonOne, buttonTwo);
		
		loginPane.add(buttons, 1, 5);
		
		this.mainBorderPane.setCenter(loginPane);
	}
	
	/*Center pane changes to a success notice if method is called*/
	public void setupCenterPaneAfterSuccess() {
		Label successLabel = new Label(this.title + " successful");
		successLabel.setFont(new Font("Arial Bold", 25));
		successLabel.setStyle("-fx-background-color: lightgray; -fx-border-color: darkgreen;");
		
		this.finalReturnToMenuBtn = BankFXController.createButton("Return", "darkblue", 30);
		this.finalReturnToMenuBtn.setStyle("-fx-background-color: lightgray");
		
		VBox successVBox = new VBox(20);
		
		successVBox.setAlignment(Pos.BASELINE_CENTER);
		successVBox.setPadding(new Insets(10,10,10,10));
		successVBox.getChildren().addAll(successLabel, finalReturnToMenuBtn);
		
		this.mainBorderPane.setCenter(successVBox);
		
		
	}
	
	/*Bottom pane shows failed login if applicable*/
	private void setupBottomFailPane(String failText) {
	
		this.signInFailure = new Text(failText);
	
		signInFailure.setFont(new Font("Arial Bold", 22));
		signInFailure.setFill(Color.RED);
		this.signInFailurePane = new StackPane(signInFailure);
		signInFailurePane.setPadding(new Insets(25,25,25,25));
		signInFailurePane.setVisible(false);
		this.mainBorderPane.setBottom(signInFailurePane);
		
	}
	
	/*Lets parent controller make fail notice visible if applicable*/
	public void setFailVisible(boolean value) {
		this.signInFailurePane.setVisible(value);
	}


	
	
	
}