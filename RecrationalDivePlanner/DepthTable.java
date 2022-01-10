

public class DepthTable {

    //The depth options for Table One:
    private final static int[] DEPTH_ARRAY = {35, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140};
    //The dive times for Table One:
    private final static int[][] DEPTH_TIMES_ARRAY = {
            {10, 19, 25, 29, 32, 36, 40, 44, 48, 52, 57, 62, 67, 73, 79, 85, 92, 100, 108, 117, 127, 139, 152, 168, 188, 205},
            {9, 16, 22, 25, 27, 31, 34, 37, 40, 44, 48, 51, 55, 60, 64, 69, 74, 79, 85, 91, 97, 104, 111, 120, 129, 140},
            {7, 13, 17, 19, 21, 24, 26, 28, 31, 33, 36, 39, 41, 44, 47, 50, 53, 57, 60, 63, 67, 71, 75, 80},
            {6, 11, 14, 16, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 42, 44, 47, 49, 52, 54, 55},
            {5, 9, 12, 13, 15, 16, 18, 19, 21, 22, 24, 26, 27, 29, 31, 33, 35, 36, 38, 40},
            {4, 8, 10, 11, 13, 14, 15, 17, 18, 19, 21, 22, 23, 25, 26, 28, 29, 30},
            {4, 7, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 21, 22, 23, 24, 25},
            {3, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20},
            {3, 6, 7, 8, 9, 10, 11, 12, 13, 13, 14, 15, 16},
            {3, 5, 6, 7, 8, 9, 10, 11, 11, 12, 13},
            {3, 5, 6, 7, 7, 8, 9, 10},
            {4, 4, 5, 6, 7, 8}
            };

    //Constructor not accessible
    private DepthTable(){

    }

    /*
    get the entire depth array
     */
    public static int[] getDepthArray(){
        return DEPTH_ARRAY;
    }

    /*
    Returns the rounded depth
     */
    public static int depthRounder(int depthEstimate){
       int roundedDepth = DiveTools.roundUp(depthEstimate, DEPTH_ARRAY);
       return roundedDepth;
    }

    /*
    gets the single array of possible depth times from the 2D array
     */
    public static int[] getDepthTimes(int roundedDepth){
        int row = DiveTools.indexSearch(roundedDepth, DEPTH_ARRAY);
        int[] thisRow = new int[DEPTH_TIMES_ARRAY[row].length];
        for(int i = 0; i < DEPTH_TIMES_ARRAY[row].length; i++){
            thisRow[i] = DEPTH_TIMES_ARRAY[row][i];
        }
        return thisRow;
    }

    //Round to the nearest dive time
    public static int diveTimeRounder(int timeEstimate, int[]timesArray){
        int diveTime = DiveTools.roundUp(timeEstimate, timesArray);
        return diveTime;
    }

    //
    public static char pressureGroup(int roundedDiveTime, int[] timesArray){
        int index = DiveTools.indexSearch(roundedDiveTime, timesArray);
        char pressureGroup = DiveTools.pressureGroupFromIndex(index);
        return pressureGroup;
    }

    public static int residualNitrogenTime(char pressureGroup, int[] depthTimes){
        int index = DiveTools.pressureGroupIndex(pressureGroup);
        int residualNitrogenTime = depthTimes[index];
        return residualNitrogenTime;
    }

    public static int[] depthTimesArrayMinusNitro(int residualNitrogenTime, int[] depthTimes){
        int newLimit = depthTimes[depthTimes.length-1] - residualNitrogenTime;
        int roundedNewLimit = DiveTools.roundDown(newLimit, depthTimes);
        int indexStopper = DiveTools.indexSearch(roundedNewLimit, depthTimes);
        int[] adjustedDepthTimesArray = new int[indexStopper+1];
        for(int i = 0; i < adjustedDepthTimesArray.length; i++){
            adjustedDepthTimesArray[i] = depthTimes[i];
        }
        return adjustedDepthTimesArray;
    }

}
