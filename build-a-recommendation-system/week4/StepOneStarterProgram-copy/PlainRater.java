
/**
 * Write a description of class Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class PlainRater implements Rater {
    private String myID;
    private ArrayList<Rating> myRatings;

    public PlainRater(String id) {
        myID = id;
        myRatings = new ArrayList<Rating>();
    }
    
    public ArrayList<String> getListOfRatedMovies() {
        ArrayList<String> uniqueMovies = new ArrayList<String>();
        for ( int i=0; i<myRatings.size(); i++ ) {
            uniqueMovies.add(myRatings.get(i).getItem());
        }   
        return uniqueMovies;
    }
    
    public void printAllRatings() {
        for ( int i=0; i<myRatings.size(); i++ ) {
            System.out.println(myRatings.get(i));
        }
    }

    public void addRating(String item, double rating) {
        myRatings.add(new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return true;
            }
        }
        
        return false;
    }

    public String getID() {
        return myID;
    }

    public Rating getRating(String item) {
        Rating r = null;
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return myRatings.get(k);
            }
        }
        return null;
    }

    public int numRatings() {
        return myRatings.size();
    }
    
    public String toString() {
        return myID;
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(int k=0; k < myRatings.size(); k++){
            list.add(myRatings.get(k).getItem());
        }
        
        return list;
    }
}
