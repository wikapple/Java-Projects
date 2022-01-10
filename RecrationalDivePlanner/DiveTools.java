

/*
A simple tools class with some useful methods to reduce repetitive code
 */
public class DiveTools {
    /*
    Compares an int to an int array, rounds up to the nearest element in array and returns that element.
     */
    public static int roundUp(int intInput, int[] arrayInput){
        int intReturnValue = arrayInput[0];

        for(int i = 1 ; i < arrayInput.length; i++){
            if(intInput > intReturnValue){
                intReturnValue = arrayInput[i];
            }
        }
        return intReturnValue;
    }

    public static int roundDown(int intInput, int[] arrayInput){
        int intReturnValue = arrayInput[arrayInput.length-1];

        for(int i = arrayInput.length - 2 ; i >= 0; i--){
            if(intInput < intReturnValue){
                intReturnValue = arrayInput[i];
            }
        }
        return intReturnValue;
    }
    /*
    creates and returns a char array of the alphabet(Capitalized)
     */
    public static char[] createPressureGroupList(){
        char []pressureGroups = new char[26];
        int unicodeIndex = 65;
        for (int j = 0; j < pressureGroups.length; j++){
            pressureGroups[j] = (char)unicodeIndex;
            unicodeIndex++;
        }
        return pressureGroups;
    }

    public static int pressureGroupIndex(char pressureGroup){
        char[] pressureGroups = createPressureGroupList();
        int index = indexSearch(pressureGroup, pressureGroups);
        return index;
    }

    public static char pressureGroupFromIndex(int index){
        char[] pressureGroups = createPressureGroupList();
        char pressureGroup = pressureGroups[index];
        return pressureGroup;
    }

    public static int indexSearch(int intInput, int[] array){
        for(int i = 0; i < array.length ; i++){
            if(intInput == array[i]){
                return i;
            }
        }
            return -1;
        }


    public static int indexSearch(char charInput, char[] array){
        for(int i = 0; i < array.length ; i++){
            if(charInput == array[i]){
                return i;
            }
        }
            return -1;
    }



}
