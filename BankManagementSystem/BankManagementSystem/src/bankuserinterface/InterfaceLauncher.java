package bankuserinterface;
import bankmanagementsystem.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/*
 * InterfaceLauncher Class contains main method launches Bank GUI
 * 
 * Author: William Applegate
 * INFO-C210
 */
public class InterfaceLauncher extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		BankManagementSystem bankSystem = new BankManagementSystem();
		/*Create a default employee with default password that is already entered into login screen (Save time for testing)*/
		bankSystem.createNewEmployee("Default Login", "Default Password");
		
		/*Directly create a few example accounts before launching GUI*/
		bankSystem.createAccount(1, 1, "William", 1, 1000000);
		bankSystem.createAccount(2, 2, "Kris", 2, 200000);
		bankSystem.createAccount(3, 3, "Applegate", 3, 300000);
		
		/* Instantiate the Controller*/
		BankFXController myBankFX = new BankFXController(bankSystem, primaryStage);
		
		/*Set the Icon*/
		primaryStage.getIcons().add(new Image("bankuserinterface/Images/MoneyIcon.png"));
		
		/*set scene to the login scene to get the GUI started*/
		primaryStage.setScene(myBankFX.getLoginScene());
		
		/*Show the GUI*/
		primaryStage.show();
		
		/*Make sure everything is closed when user wants it to be. 
		* This code may be unnecessary, but I'm finding my IDE slowing down my computer the longer I code in this program. 
		* I suspect I'm losing memory/performance as I repeatedly run and test GUI
		*/
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				try {
					stop();
					System.out.println("Program Ended");
					System.exit(1);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		});
	}

	/*Main Method*/
	public static void main(String[] args) {
		launch(args);
	}
}
