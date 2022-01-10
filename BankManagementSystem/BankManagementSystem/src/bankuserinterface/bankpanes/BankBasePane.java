package bankuserinterface.bankpanes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/* 
 * Full window GUI panes extend from this base pane with basic style and formatting
 * 
 * Author: William Applegate
 * INFO-C210
 */
 abstract class BankBasePane extends StackPane {
	
	/*Data Fields*/
	private Image backgroundImage = new Image("bankuserinterface/Images/Bank.jpg");
	protected BorderPane mainBorderPane = new BorderPane();
	private VBox header = new VBox(10);
	private Font titleFont = new Font("Arial Bold", 45);
	protected Font subFont = new Font("Arial Bold", 20);
	
	/*No Arg. constructor would go here, but creating one defeats purpose of having a base pane with a specific title and subtitle*/
	
	/*Constructor*/
	BankBasePane(String title, String subTitle){
		/*Use ImageView for background of GUI*/
		ImageView background = new ImageView(backgroundImage);
		/*Background size is hard coded*/
		background.setFitWidth(1000);
		background.setFitHeight(550);
		StackPane backgroundPane = new StackPane(background);
		backgroundPane.setPadding(new Insets(10,10,10,10));
		
		/*Setting up Title and subtitle*/
		Text paneTitle = new Text(title);
		paneTitle.setFont(titleFont);
		Text subtitle = new Text(subTitle);
		subtitle.setFont(subFont);
		header.getChildren().addAll(paneTitle, subtitle);
		header.setPadding(new Insets(10,10,10,10));
		header.setAlignment(Pos.CENTER);
		header.setStyle("-fx-background-color: lightgray; -fx-border-color: white; -fx-background-radius: 15px;");
		
		mainBorderPane.setTop(header);
	
		/*Add background and border pane to this object*/
		this.getChildren().addAll(backgroundPane, mainBorderPane);
	}

}
