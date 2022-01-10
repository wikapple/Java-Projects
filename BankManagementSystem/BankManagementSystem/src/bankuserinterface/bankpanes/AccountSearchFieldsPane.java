package bankuserinterface.bankpanes;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/*
 * Account SearchFieldsPane used to get user input in search bars
 * data fields updated as user updates text fields
 * getters so SearchPane class can respond to changes
 * 
 * Author: William Applegate
 * INFO-C210
 */
public class AccountSearchFieldsPane extends GridPane{
	/*Data Fields*/
	private InputFieldHBox accountNumberBox;
	private InputFieldHBox customerNameBox;
	private InputFieldHBox customerIDBox;
	private InputFieldHBox minValueBox;
	private InputFieldHBox maxValueBox;
	
	/*Constructor*/
	public AccountSearchFieldsPane(){
		
		/*Pane formatting*/
		this.setVgap(25);
		this.setAlignment(Pos.TOP_CENTER);

		ArrayList<InputFieldHBox> boxes = new ArrayList<>();
		
		Label title = new Label("Search Fields");
		title.setFont(new Font("Arial Bold", 24));
		
		title.setTextAlignment(TextAlignment.CENTER);
		title.setAlignment(Pos.TOP_CENTER);
		title.setPrefWidth(275);
		title.setPrefHeight(20);
		this.add(title, 0, 0);
		
		/*Setting up nodes (LLabels and textfields)*/
		this.accountNumberBox = new InputFieldHBox(1, "Account Number", 14);
		this.add(accountNumberBox, 0, 1);
		boxes.add(accountNumberBox);
		
		this.customerNameBox = new InputFieldHBox(1, "Customer Name", 14);
		this.add(customerNameBox, 0, 2);
		boxes.add(customerNameBox);
		
		this.customerIDBox = new InputFieldHBox(1,"Customer ID", 14);
		this.add(customerIDBox, 0, 3);
		boxes.add(customerIDBox);
		
		this.minValueBox = new InputFieldHBox(1, "Min.Value", 14);
		this.add(minValueBox, 0, 4);
		boxes.add(minValueBox);
		
		this.maxValueBox = new InputFieldHBox(1, "Max Value", 14);
		this.add(maxValueBox, 0, 5);
		boxes.add(maxValueBox);
		
		/*formatting fields*/
		boxes.forEach(a->{
			((InputFieldHBox)a).setChildPrefHeight(15);
			((InputFieldHBox)a).setLabelPrefWidth(125);
			((InputFieldHBox)a).setTextFieldPrefWidth(150);
			((InputFieldHBox)a).setAlignment(Pos.CENTER);
			((InputFieldHBox)a).getLabel().setTextAlignment(TextAlignment.RIGHT);
			((InputFieldHBox)a).getLabel().setAlignment(Pos.CENTER_RIGHT);
			
		});
	}

	/*Getters*/
	public InputFieldHBox getAccountNumberBox() {
		return accountNumberBox;
	}

	public InputFieldHBox getCustomerNameBox() {
		return customerNameBox;
	}

	public InputFieldHBox getCustomerIDBox() {
		return customerIDBox;
	}

	public InputFieldHBox getMinValueBox() {
		return minValueBox;
	}

	public InputFieldHBox getMaxValueBox() {
		return maxValueBox;
	}
	
	


}
