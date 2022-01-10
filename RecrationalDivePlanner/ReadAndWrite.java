import java.io.*;
public class ReadAndWrite {
	
	public static void saveDivePlan(DiveTrip thisDivePlan) throws FileNotFoundException {	
		String fileName = "SavedDives/" + thisDivePlan.getDiveTripName() + ".txt";
		
		FileOutputStream fos = new FileOutputStream(fileName);
		PrintWriter pw = new PrintWriter(fos);
		
		pw.println(thisDivePlan.toString());
		pw.close();
					
		
	}
	
	
}
