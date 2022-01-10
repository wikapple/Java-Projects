
import java.io.*;

public class FileWriter {
	
	
	public static void saveDivePlan(DiveTrip thisDivePlan) {	
		String fileName = "savedDivePlans.txt";
		try {
			PrintWriter outputStream = new PrintWriter(fileName);
			outputStream.println(thisDivePlan.toString());
			outputStream.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	

}
