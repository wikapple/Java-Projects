public class Dive {
    private char startingPressureGroup;
    private char endingPressureGroup;
    private int diveNumber;
    private int diveDepth;
    private int diveTime;
    private int[] timesArray;
    private int[] adjustedTimesArray;
    private int residualNitroTime;
    private boolean isNoDecompressionLimitReached;
    private String decompressWarning = "No decompression limit reached!";
    private boolean isSafetyStopRequired;
    private String safetyStopWarning = "Safety stop required!";
    private int surfaceInterval;

    Dive(int diveNumber){
        this.diveNumber = diveNumber;
        isSafetyStopRequired = false;
        isNoDecompressionLimitReached = false;
        this.surfaceInterval = -1;
    }
    Dive(char startingPressureGroup, int diveNumber){
        this.startingPressureGroup = startingPressureGroup;
        this.diveNumber = diveNumber;
        this.isSafetyStopRequired = false;
        this.isNoDecompressionLimitReached = false;
        this.surfaceInterval = -1;
    }

    public char getEndingPressureGroup(){
        return endingPressureGroup;
    }

    public int getSurfaceInterval(){
        return this.surfaceInterval;
    }

    public boolean isDecompressionLimitReached(){
        return isNoDecompressionLimitReached;
    }
    public void fullSetup(){
        askForDepth();
        calculateTimesArray();
        askForDiveTime();
        calculatePressureGroup();
        checkLimits();
    }

    private void askForDepth(){
        String instructions = "What is your estimated depth in feet for dive #" + this.diveNumber + ":";
        this.diveDepth = UserInput.intInputScanner(instructions, 1, 140);
    }

    private void calculateTimesArray(){
        int roundedDepth = DepthTable.depthRounder(this.diveDepth);
        int[] timesArray = DepthTable.getDepthTimes(roundedDepth);
        if(startingPressureGroup != 0){
            this.residualNitroTime = DepthTable.residualNitrogenTime(startingPressureGroup, timesArray);
            this.adjustedTimesArray = DepthTable.depthTimesArrayMinusNitro(this.residualNitroTime, timesArray);
        }
        this.timesArray = timesArray;
    }

    private void askForDiveTime(){
        String instructions = "How many minutes do you plan this dive to last?";
        if(startingPressureGroup != 0) {
            instructions += " (Up to " + adjustedTimesArray[adjustedTimesArray.length - 1] + ")";
            this.diveTime = UserInput.intInputScanner(instructions, 1, adjustedTimesArray[adjustedTimesArray.length-1]);
        }else {
            instructions += " (Up to " + timesArray[timesArray.length - 1] + ")";
            this.diveTime = UserInput.intInputScanner(instructions, 1, timesArray[timesArray.length - 1]);
        }
    }

    private void calculatePressureGroup(){
        int diveTimePlusNitro = this.diveTime + this.residualNitroTime;
        int roundedDiveTime = DepthTable.diveTimeRounder(diveTimePlusNitro, this.timesArray);
        this.endingPressureGroup = DepthTable.pressureGroup(roundedDiveTime, this.timesArray);
    }

    private void checkLimits(){
        if (this.diveDepth > 90){
            this.isSafetyStopRequired = true;
            System.out.println(this.safetyStopWarning);
        }else if(this.diveTime > timesArray[timesArray.length - 5]){
            this.isSafetyStopRequired = true;
            System.out.println(this.decompressWarning);
        }
        if(this.diveTime > timesArray[timesArray.length-2]){
            isNoDecompressionLimitReached = true;
            System.out.println(this.decompressWarning);
        }
    }

    public void askForSurfaceTime(){
        String instructions = "Enter how long you plan to be on the surface before your next dive: ";
        this.surfaceInterval = UserInput.intInputScanner(instructions, 0, 900);
    }

    @Override
    public String toString(){
        String value = "\t\t\tDive Number " + this.diveNumber +"\n";
        value +="Dive Depth: " + this.diveDepth + " feet. --  ";
        value += "Time at depth: " + this.diveTime + " minutes\n";
        if(this.startingPressureGroup != 0) {
            value += "startingPressureGroup: " + startingPressureGroup + "  --  ";
        }else{
            value += "startingPressureGroup = n/a  --  ";
        }
        value += "Ending pressure group: " + this.endingPressureGroup;

        if(isSafetyStopRequired){
            value += "\n" + safetyStopWarning +"\t\t";
        }
        if(isNoDecompressionLimitReached){
            value += decompressWarning;
        }
        if(this.surfaceInterval != -1){
            value += "\nSurface Interval: " + this.surfaceInterval + " minutes\n";
        }
        return value;

    }





}
