import java.io.FileNotFoundException;

public class MainTest {

    public static void main(String[] args) throws FileNotFoundException{
        //instruction
    	System.out.println("Welcome to Recreational Dive Planner");
        
    	makeADiveTrip();
        
        System.out.println("Program Complete");
    }
    
    public static void makeADiveTrip() throws FileNotFoundException{
    	DiveTrip myDiveTrip = new DiveTrip();
        myDiveTrip.fullDiveTripSetup();
        System.out.println(myDiveTrip.toString());
        if(UserInput.yesOrNo("Do you want to save this dive plan as a .txt file?")) {
        	ReadAndWrite.saveDivePlan(myDiveTrip);
        }
        if(UserInput.yesOrNo("Do you want to make another dive plan?")){
        	makeADiveTrip();
        }
    }

}