
/**
 * Write a description of class Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }
    
    public ArrayList<String> getListOfRatedMovies() {
        ArrayList<String> uniqueMovies = new ArrayList<String>();
        for ( String movieId : myRatings.keySet() ) {
            uniqueMovies.add(myRatings.get(movieId).getItem());
        }   
        return uniqueMovies;
    }
    
    public void printAllRatings() {
        for ( String movieId : myRatings.keySet() ) {
            System.out.println(myRatings.get(movieId)); 
        }
    }

    public void addRating(String item, double rating) {
        myRatings.put(item,new Rating(item,rating));    
    }

    public boolean hasRating(String item) {
        if ( myRatings.containsKey(item) ) {
            return true;
        }
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        //looks up the value from the hashmap
        if ( myRatings.containsKey(item) ) {
            //returns the rating if found
            return myRatings.get(item).getValue();
        }
        else {
            //returns null if not found
            return -1;
        }
    }

    public int numRatings() {
        return myRatings.size();
    }
    
    public String toString() {
        return myID;
    }

    // public ArrayList<String> getItemsRated() {
        // ArrayList<String> list = new ArrayList<String>();
        // for(int k=0; k < myRatings.size(); k++){
            // list.add(myRatings.get(k).getItem());
        // }
        
        // return list;
    // }
}
