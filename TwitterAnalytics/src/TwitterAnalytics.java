
import java.lang.Character; 
import java.util.Scanner; 
import java.io.IOException; 
import java.nio.file.Files; 
import java.nio.file.Paths; 
import edu.ius.HashtagHistogram; 
import edu.ius.EntityHistogram;


public class TwitterAnalytics { 
    
    /**Christopher J Kimmer's tweetsFromFile method.
     * 
     * @param fileName 
     * @return 
     */
    public static String[] tweetsFromFile(String fileName) { 
        try { String fileContents = new String(Files.readAllBytes(Paths.get(fileName)));
        return fileContents.split("\n");  }
        catch(IOException e) { 
        System.out.println(e.toString());
        } return null;}
        
    /** Takes an array of tweets, returns an array of normalized tweets with
     * symbols removed except '@' and '#'.
     * 
     * @param tweets
     * @return 
     */
    public static String[] normalizedTweets(String[] tweets){
        //initialize
        String[] normalizedTweets = new String[tweets.length];
        String tweet;
        //traverse the array
        for (int x = 0; x < tweets.length ; x++){
            tweet = tweets[x];
        //initialize
        char [] normalTweetArray = new char[tweet.length()];
        int j = 0;
        //traverse the String
        for (int i = 0 ; i < tweet.length() ; i++){
            char lettercheck = tweet.charAt(i);
            //if true, char is added to new tweet array.
            if (Character.isLetter(lettercheck) || lettercheck == '#' || 
                    lettercheck == ' '|| lettercheck == '@'){
                normalTweetArray[j] = lettercheck;
                j++;
            }
        }
            normalizedTweets[x] = new String(normalTweetArray); //make tweet array a String.
        }
        return normalizedTweets;
    }
        
    
        /** * @param args the command line arguments */ 
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 
        String corpusFileName; String[] tweets; 
        
        System.out.print("Please enter the name of a text file containing 1 tweet "
                + "per line (or press 'enter' for default package): "); 
        String input = in.nextLine();
      if (input.equals("")){
          corpusFileName = "src/twitterCorpus.txt";
      }else{
        corpusFileName = input;
      }
        tweets = tweetsFromFile(corpusFileName); 
        if (tweets != null) {
            //invoke normalizedTweets
            String[] normalizedTweets = normalizedTweets(tweets);
            
            //Output tweets
            for (int i = 0 ; i < tweets.length ; i++){
                System.out.printf("Original tweet: %s\nNormalized tweet: %s\n\n",
                        tweets[i], normalizedTweets[i]);
            }
            //Asks what to check for
            boolean Test = true;
            while (Test){
            System.out.println("\nenter '#' to count hashtags. Enter '@' in order to count "
                   + "user mentions: ");
           String answer = in.next();
           
           //check for '@' symbol
           if (answer.charAt(0) == '@'){
               Test = false;
               EntityHistogram entity = new EntityHistogram(normalizedTweets);
               entity.printHistogram();
           }
           //check for '#' symbol
           else if (answer.charAt(0) == '#'){
               Test = false;
                HashtagHistogram count = new HashtagHistogram(normalizedTweets);
                count.printHistogram();
                
            //error backup, loops instructions.
            }else
               System.err.println("Error! you did not enter a '#' or a '@'\n");
            }
        } 
    } 
}