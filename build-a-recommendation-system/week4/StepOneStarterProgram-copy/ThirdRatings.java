
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public double getAverageByID(String movieId, int minimalRaters) {
        /*
         * In the ThirdRatings class, write a private helper method named getAverageByID that has two parameters: a String named 
         * id representing a movie ID and an integer named minimalRaters. This method returns a double representing the average 
         * movie rating for this ID if there are at least minimalRaters ratings. If there are not minimalRaters ratings, then it 
         * returns 0.0.
         */    
        
        //initialize the avg and the count
        int movieCount = 0;
        double avgRtg = 0.0;
        
        //iterate on all raters 
        for ( int i=0; i<myRaters.size(); i++ ) {
                Rater currRater = myRaters.get(i);
                //check if that rater has rating for movie 
                if ( currRater.hasRating(movieId) ) {
                    //if it has rating then save rating
                    double currMveRtg = currRater.getRating(movieId).getValue();
                    //increase counter
                    movieCount++;
                    //increase sumrating
                    avgRtg += currMveRtg;
                }
        } 
        //if less movie is found then minimalRaters then return 0.0
        if ( movieCount < minimalRaters ) {
            return 0.0;
        }
        //else calculate the average and return it
        else return avgRtg/movieCount;
    }
    
    // public HashMap<String,Double> getAverageRatings (int minimalRaters) {
        // //refactored code
        // HashMap<String,Double> movieCollection = new HashMap<String,Double>();
        // ArrayList<String> uniqueMovies = MovieDatabase.filterBy(new TrueFilter());    
        
        // for ( int k=0; k<uniqueMovies.size(); k++ ) {
            // String movieId = uniqueMovies.get(k);
            // double currRating = getAverageByID(movieId, minimalRaters); 
            // if (currRating == 0.0 || movieCollection.containsKey(movieId) ) {
                // continue;
            // }
            // else {
                // movieCollection.put(movieId,currRating);
            // }
        // }
        // return movieCollection;
    // }
    
    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters, Filter filterCriteria) {
        //handle if there is no filter
        if ( filterCriteria == null ) {
            filterCriteria = new TrueFilter();
        }
        
        //initialize the result 
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        //import movies and filter them by criteria
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);  
        //iterate on filtered movies
        for ( int i=0; i<movies.size(); i++ ) {
            String movieId = movies.get(i);
            double avgRating = getAverageByID(movieId,minimalRaters);
            if ( avgRating != 0.0 ) {
                ratings.add(new Rating(movieId,avgRating));
            }
        }
        return ratings;
    }
    
    
    
    // public void testGetAverageRatings() {
        // HashMap<String,Double> movies = getAverageRatings(2);
        // for (Map.Entry<String, Double> entry : movies.entrySet()) {
            // System.out.println("Movie: " + entry.getKey() + ", Rating: " + entry.getValue());
        // }
        
    // }
    
    public HashMap<String,Double> convMovieIdToTitle(HashMap<String,Double> moviesById) {
    HashMap<String,Double> moviesByTitle = new HashMap<String,Double>();
    for ( String movieId : moviesById.keySet() ) {
        String movieTitle = MovieDatabase.findTitleById(movieId);
        //System.out.println("Looking up movie with id: " + movieId + ", found title: " + movieTitle);
        if ( movieTitle != "NO SUCH TITLE.") {
            moviesByTitle.put(movieTitle,moviesById.get(movieId));
        }
    }
    return moviesByTitle;
    }
    

    
    public LinkedHashMap<String, Double> orderByRatings(HashMap<String,Double> movies) {
        //Convert the entrySet of the map into a list
        List<Map.Entry<String, Double>> list = new ArrayList<>(movies.entrySet());
        // Sort the list using a custom Comparator
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        // Create a LinkedHashMap that preserves the order of the sorted list
        LinkedHashMap<String, Double> sortedByRating = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list) {
            sortedByRating.put(entry.getKey(), entry.getValue());
        }
        return sortedByRating;
    }
    

    


    
    
    
    public ThirdRatings(String ratingsFile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsFile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
}
