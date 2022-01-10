package bankuserinterface.bankpanes;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/*Quality of life class for repetitive textfield creation
 * methods provide easier access to numeric values
 *
 * Author: William Applegate
 * INFO-C210
 */
public class InputFieldHBox extends HBox{
	/*Data Fields*/
	private Label descriptionLabel; 
	private TextField textField;
	
	/*Constructor*/
	public InputFieldHBox(double padBetweenNodes, String labelText, int fontSize){
		/*call the super constructor to get assign padding between HBox Nodes*/
		super(padBetweenNodes);
		
		descriptionLabel = new Label(labelText);
		descriptionLabel.setFont(new Font("Arial Bold", fontSize));
		
		textField = new TextField(); 
		
		this.getChildren().addAll(descriptionLabel, textField);	
	}
	
	/*getters*/
	TextField getTextField() {
		return this.textField;
	}
	Label getLabel() {
		return descriptionLabel;
	}
	/*Get int value of textfield if possible, otherwise return 0*/
	int getIntValue() {
		int fieldAsInt;
		try {
			fieldAsInt = Integer.parseInt(textField.getText());
		}catch(NumberFormatException e) {
			fieldAsInt = 0;
		}
			return fieldAsInt;
	}
	
	/*Get long value of textfield if possible, otherwise return 0*/
	long getLongValue() {
		long fieldAsInt;
		try {
			fieldAsInt = Long.parseLong(textField.getText());
		}catch(NumberFormatException e) {
			fieldAsInt = 0l;
		}
			return fieldAsInt;
	}
	
	/*Setters*/
	public void setChildPrefHeight(double prefHeight){
		setLabelPrefHeight(prefHeight);
		setTextFieldPrefHeight(prefHeight);
	}
	
	void setLabelPrefWidth(double prefWidth) {
		descriptionLabel.setPrefWidth(prefWidth);
	}
	void setLabelPrefHeight(double prefHeight) {
		descriptionLabel.setPrefHeight(prefHeight);
	}
	
	void setTextFieldPrefWidth(double prefWidth) {
		textField.setPrefWidth(prefWidth);
	}
	
	void setTextFieldPrefHeight(double prefHeight) {
		textField.setPrefHeight(prefHeight);
	}
	
	void setFont(Font newFont) {
		this.descriptionLabel.setFont(newFont);
	}
	
	void setLabelStyle(String fxStyle) {
		this.descriptionLabel.setStyle(fxStyle);
	}
	void setTextFieldStyle(String fxStyle) {
		this.textField.setStyle(fxStyle);
	}
}
