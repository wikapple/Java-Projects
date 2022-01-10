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
public class EntityCounter {
    //instance variables created
    
private int atSymbol;
private String tweet;


//The constructor, needs a tweet to build.
public EntityCounter(String tweet){
    //this.numbersign = 0;
    //this.hashtags = 0;
    this.atSymbol = 0;
    this.tweet = tweet;
}

    private int countAtSymbols(){
        
        //start for loop. Last element of string cannot be a hashtag.
        for (int i = 0 ; i <tweet.length()-1 ; i++){
            //declare for loop variables
            char currentLetter = tweet.charAt(i);
            char nextLetter = tweet.charAt(i+1);
            
            if (currentLetter == '@'){ //only concerned with element @1111
                
                //index 0 requires special if statement.
                if (i == 0){
                    if (nextLetter != ' '){
                   //only a hashtag if next element is not a space.
                    atSymbol++;
                    }
                }
                
                //if statement for all other indices
            else if (tweet.charAt(i-1) == ' ' && nextLetter != ' '){ //hashtag if previous element is a space and next element is not a space.
                    atSymbol++;
                }
            }
        }
        return atSymbol;
    }
    
//    public int getNumberSigns(){
//        return this.countNumberSigns();
    public int getAtSymbols(){
        return this.countAtSymbols();
    }   
}
