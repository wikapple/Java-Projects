package bankuserinterface.bankpanes;

import bankuserinterface.BankFXController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/*
 * Pane used to apply monthly adjustments
 * Two button Pane for checking if user is sure they want to apply adjustment
 * 
 * Author: William Applegate
 * INFO-C210 
 */
public class MonthResetPane extends BankBasePane{
	/*Data Fields*/
	private Button confirmBtn;
	private Button cancelBtn;
	private VBox centerPane;
	
	/*Constructor*/
	public MonthResetPane(){
		super("Month Reset", "Applies monthly adjustments to all accounts");
		
		setupCenterPane();
		
	}
	/*Sets up center pane*/
	private void setupCenterPane() {
		
		centerPane = new VBox(20);
		centerPane.setAlignment(Pos.TOP_CENTER);
		
		Label infoLabel = new Label("Are you sure you want to reset month and apply monthly adjustments?\nAction cannot be reversed");
		infoLabel.setFont(new Font("Arial Bold", 18));
		infoLabel.setTextAlignment(TextAlignment.CENTER);
		infoLabel.setStyle("-fx-background-color: white; -fx-border-color: orange");
		
		HBox buttons = new HBox(20);
		buttons.setAlignment(Pos.TOP_CENTER);
		
		this.confirmBtn = BankFXController.createButton("Yes, Apply Monthly Adjustment", "green", 20);
		this.cancelBtn = BankFXController.createButton("No, Return to Menu", "blue", 20);
		
		buttons.getChildren().addAll(this.confirmBtn, this.cancelBtn);
		
		this.centerPane.getChildren().addAll(infoLabel, buttons);
		centerPane.setAlignment(Pos.TOP_CENTER);
		centerPane.setPadding(new Insets(20,20,20,20));
		
		this.mainBorderPane.setCenter(this.centerPane);
	}
	
	/*Getters*/
	public Button getConfirmBtn() {
		return this.confirmBtn;
	}
	
	public Button getCancelBtn() {
		return this.cancelBtn;
	}

}
