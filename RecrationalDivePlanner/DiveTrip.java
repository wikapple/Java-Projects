import java.util.ArrayList;

public class DiveTrip {
    private String diveTripName;
    private int numberOfDives;
    private ArrayList<Dive> dives;
    private boolean isDecompressionLimitReached;

    DiveTrip(){
        this.isDecompressionLimitReached = false;
    }
    
    public String getDiveTripName() {
    	return this.diveTripName;
    }
    public void fullDiveTripSetup(){
        askForDiveTripName();
        askForNumberOfDives();
        initializeDives();
    }

    private void askForDiveTripName(){
        String instructions = "What is the name of your dive trip?";
        this.diveTripName = UserInput.stringInputScanner(instructions, 2, 35);
    }
    private void askForNumberOfDives(){
        String instructions = "How many dives will be in this trip?";
        this.numberOfDives = UserInput.intInputScanner(instructions, 1, 4);
    }

    private void initializeDives(){
        dives = new ArrayList<Dive>();
        char startingPressureGroup = 0;
        for(int i = 1 ; i < numberOfDives ; i++) {
            Dive newDive = new Dive(startingPressureGroup, i);
            newDive.fullSetup();
            newDive.askForSurfaceTime();
            int surfaceInterval = newDive.getSurfaceInterval();
            char pressureGroupAfterInterval = SurfaceInterval.fullConversion(newDive.getEndingPressureGroup(), surfaceInterval);
            startingPressureGroup = pressureGroupAfterInterval;
            this.isDecompressionLimitReached = newDive.isDecompressionLimitReached();
            dives.add(newDive);
            if (isDecompressionLimitReached) {
                return;
            }
        }
            Dive lastDive = new Dive(startingPressureGroup, dives.size() + 1);
            lastDive.fullSetup();
            dives.add(lastDive);
    }
    @Override
    public String toString(){
        String stringValue = "\t\t\t" + diveTripName + "\n";
        stringValue += "number of dives: " + numberOfDives + "\n";
        for (Dive dive : dives){
            stringValue += dive.toString() + "\n";
        }
        return stringValue;
    }

}
