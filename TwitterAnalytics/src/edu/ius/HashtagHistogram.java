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
public class HashtagHistogram {
    private String[] tweets;
    private int[] histogram;
    
    
    public HashtagHistogram(String[] tweets){
        this.histogram = new int[9];
        this.tweets = tweets;
        for (int traverse : histogram){
            histogram[traverse] = 0;  
        }buildHistogram();
        
    }
    
    private int[] buildHistogram(){
        for (String tweet : tweets){
          
            HashtagCounter count = new HashtagCounter(tweet);
            int hashtags = count.getHashtags();
            histogram[hashtags]++;
        }
        return histogram;
    }
    
    public void printHistogram(){
        for (int j = 0 ; j < 9 ; j++){
            System.out.printf("There are %d tweet(s) containing %d hashtags\n",
                    histogram[j], j);
        }
    }
}
