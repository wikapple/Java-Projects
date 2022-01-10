import java.util.Scanner;

public class UserInput {
    private final static Scanner in = new Scanner(System.in);

    public static int intInputScanner(String instructions, int min, int max) {
        int userInput = -1;
        boolean noValidEntries = true;
        while (noValidEntries) {
            System.out.println(instructions);
            if (in.hasNextInt()) {
                userInput = in.nextInt();
                if (userInput >= min && userInput <= max) {
                    noValidEntries = false;
                } else {
                    System.out.println("int entry is out of range");
                }
            } else {
                String badInput = in.nextLine();
                System.out.println(badInput + " is an invalid entry");
            }
        }

        return userInput;
    }

    public static String stringInputScanner(String instructions, int minLength, int maxLength) {
        String response = "";
        boolean responseLoop = true;
        while (responseLoop) {
            System.out.println(instructions);
            response = in.nextLine();
            if (response.length() > minLength && response.length() < maxLength) {
                responseLoop = false;
            }
        }
        return response;
    }
    
    public static boolean yesOrNo(String instructions) {
    	boolean responseLoop = true;
    	while (responseLoop) {
    		System.out.print(instructions);
    		System.out.println(" (Enter Y or N): ");
    		String response = in.next();
    		response = response.toUpperCase();
    		if(response.equals("Y") || response.equals("YES")) {
    			return true;
    		}else if(response.equals("N") || response.equals("NO")) {
    			return false;
    		}
    		System.out.println("Invalid response");
    	}
    	return true;
    }
}