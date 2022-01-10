/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ius;

/**
 *
 * @author wikap
 */
public class HashtagCounter {
    //instance variables created
private int numbersign;
private int hashtags;
private String tweet;


//The constructor, needs a tweet to build.
public HashtagCounter(String tweet){
    this.numbersign = 0;
    this.hashtags = 0;
    this.tweet = tweet;
}


    /**
     * Takes in a tweet, counts and returns number of number signs.
     * @return 
     */
    private int countNumberSigns(){
        for (int i = 0 ; i <tweet.length() ; i++){
            char currentLetter = tweet.charAt(i);
            if (currentLetter == '#'){
                numbersign++;
            }
        }
        return numbersign;
    }
    
    
       
    /**
     * Takes in a tweet, counts and returns the numbers of hashtags.
     * Note: does not discern letters and numbers from symbols.
     * @return 
     */
    public int countHashtags(){
        
        //start for loop. Last element of string cannot be a hashtag.
        for (int i = 0 ; i <tweet.length()-1 ; i++){
            //declare for loop variables
            char currentLetter = tweet.charAt(i);
            char nextLetter = tweet.charAt(i+1);
            
            if (currentLetter == '#'){ //only concerned with element #
                
                //index 0 requires special if statement.
                if (i == 0){
                    if (nextLetter != ' '){
                   //only a hashtag if next element is not a space.
                    hashtags++;
                    }
                }
                
                //if statement for all other indices
            else if (tweet.charAt(i-1) == ' ' && nextLetter != ' '){ //hashtag if previous element is a space and next element is not a space.
                    hashtags++;
                }
            }
        }
        return hashtags;
    }
    
    public int getNumberSigns(){
        return this.countNumberSigns();
    }
    public int getHashtags(){
        return this.countHashtags();
    }   
}
