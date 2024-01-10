
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {
    
    public double getAverageByID(String movieId, int minimalRaters) {
        //initialize the avg and the count
        int movieCount = 0;
        double avgRtg = 0.0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        
        //iterate on all raters 
        for ( int i=0; i<RaterDatabase.size(); i++ ) {
                Rater currRater = myRaters.get(i);
                //check if that rater has rating for movie 
                if ( currRater.hasRating(movieId) ) {
                    //if it has rating then save rating
                    double currMveRtg = currRater.getRating(movieId);
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
    
    private double dotProduct(Rater me, Rater r) {
        // Check if the same Rater object is passed for both parameters
        if ( me == r ) {
            throw new IllegalArgumentException("Both raters cannot be the same");
        }
        // Create an ArrayList to hold movies from the MovieDatabase
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        double dotRating = 0; // Initialize the dot product rating to zero
        
        // Iterate over each movie
        for (String movieId : movies) {
            // Check if both raters have rated the movie
            if (me.hasRating(movieId) && r.hasRating(movieId)) {
                // Get the ratings for the current movie from both raters and adjust them to the -5 to 5 scale
                double meRating = me.getRating(movieId) - 5;
                double rRating = r.getRating(movieId) - 5;
    
                // Add the product of the two ratings to the dot product sum
                dotRating += meRating * rRating;
            }
        }
        
        // Return the calculated dot product
        return dotRating;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        // Create an ArrayList to store similarity ratings
        ArrayList<Rating> list = new ArrayList<Rating>();
    
        // Retrieve the list of all raters from the RaterDatabase
        ArrayList<Rater> raters = RaterDatabase.getRaters();
    
        // Iterate over each rater in the database
        for (Rater r : raters) {
            // Skip the iteration if the rater's ID matches the given ID
            if (r.getID().equals(id)) {
                continue;
            }
    
            // Calculate the dot product value between the given rater and the current rater
            double dpValue = dotProduct(RaterDatabase.getRater(id), r);
    
            // Create a new Rating object with the current rater's ID and the calculated dot product value
            Rating nRating = new Rating(r.getID(), dpValue);
    
            // Add the new Rating object to the list
            list.add(nRating);
        }
        
        // Sort the list from highest to lowest 
        Collections.sort(list,Collections.reverseOrder());
        
        //was used for debugging, commented out for now
        // System.out.println("---------getSimilarities result---------");
        // for ( int i=0; i<list.size(); i++ ) {
            // System.out.println(list.get(i));
        // }
        // System.out.println("---------getSimilarities result---------");
        
        // Return the list of similarity ratings
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String raterId, int numSimilarRaters, int minimalRaters, Filter f) {
        // Initiate return ArrayList
        ArrayList<Rating> result = new ArrayList<Rating>();
    
        // Get a list of raters similar to the specified rater
        ArrayList<Rating> similarRaters = getSimilarities(raterId);
        
        //was used for debugging, commented out
        //System.out.println(similarRaters.get(0).getItem() + " " + similarRaters.get(0).getValue() );
        
        //handle if filter was null
        if ( f == null ) {
            f = new TrueFilter();
        }
        
        // Retrieve a list of filtered movies
        ArrayList<String> movies = MovieDatabase.filterBy(f);
    
        // Iterate over each movie
        for (String movie : movies) {
            int raterCounter = 0;
            double sumMovieRtg = 0.0;
    
            // Go through the top 'numSimilarRaters' similar raters
            for (int i = 0; i < numSimilarRaters && i < similarRaters.size(); i++) {
                Rating simRating = similarRaters.get(i);
                String currRaterId = simRating.getItem();
                Rater currRater = RaterDatabase.getRater(currRaterId);
                double currWeight = simRating.getValue();
    
                // Check if the current rater has rated the movie
                if (currRater.hasRating(movie)) {
                    raterCounter++; //Update counter to use with minimalRaters
                    // Calculate sum and multiply with weight  to use in calculating weighted avg
                    sumMovieRtg += currRater.getRating(movie) * currWeight;
                    // increase weight to use in avg calculation
                }
            }
    
            // If enough raters have rated the movie, calculate the average rating
            if ( raterCounter != 0 && raterCounter >= minimalRaters) {
                double avgMovieRtg = sumMovieRtg / raterCounter;
                result.add(new Rating(movie, avgMovieRtg));
            }
        }
    
        // Sort the results in descending order of the rating
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }


}



























