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
public class EntityHistogram {
    //instance variables
    private String[] tweets;
    private int[] histogram;
    
    //The constructor
    public EntityHistogram(String[] tweets){
        this.histogram = new int[10]; //Risk for logic error if array length set too short.
        this.tweets = tweets;
        //Initialize all elements of histogram, set to 0.
        for (int traverse : histogram){
            histogram[traverse] = 0;  
        }buildHistogram();
        
    }
    /**Builds a histogram by using EntityCounter class
     * 
     * @return 
     */
    private int[] buildHistogram(){
        for (String tweet : tweets){
          
            EntityCounter count = new EntityCounter(tweet);
            int entityCounter = count.getAtSymbols();
            histogram[entityCounter]++;
        }
        return histogram;
    }
    
    /**Outputs histogram from buildHistogram method.
     * 
     */
    public void printHistogram(){
        for (int j = 0 ; j <= 9 ; j++){
            System.out.printf("There are %d tweet(s) containing %d user mentions\n",
                    histogram[j], j);
        }
    }
}
