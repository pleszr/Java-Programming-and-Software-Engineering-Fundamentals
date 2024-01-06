
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings_short.csv");
    }
    
    public double getAverageByID(String movieId, int minimalRaters) {
        /*
         * In the SecondRatings class, write a private helper method named getAverageByID that has two parameters: a String named 
         * id representing a movie ID and an integer named minimalRaters. This method returns a double representing the average 
         * movie rating for this ID if there are at least minimalRaters ratings. If there are not minimalRaters ratings, then it 
         * returns 0.0.
         */    
        int movieCount = 0;
        double avgRtg = 0.0;
        
        // for ( int i=0; i<myRaters.size(); i++ ) {
                // Rater currRater = myRaters.get(i);
                // double currMveRtg = currRater.getRating(movieId);
                // if ( currMveRtg != -1 ) {
                    // movieCount++;
                    // avgRtg += currMveRtg;
                // }
        // } 
        
        if ( movieCount <= minimalRaters ) {
            return 0.0;
        }
        else return avgRtg/movieCount;
    }
    
    public HashMap<String,Double> getAverageRatings (int minimalRaters) {
        //This method should find the average rating for every movie that has been rated by at least minimalRaters raters.
        //Store each such rating in a Rating object in which the movie ID and the average rating are used in creating the Rating object
        HashMap<String,Double> movieCollection = new HashMap<String,Double>();
        for ( int i=0; i<myRaters.size(); i++ ) {
            //System.out.println("Starting Rater: " + myRaters.get(i).getID());
            Rater currRater = myRaters.get(i);
            ArrayList<String> uniqueMovies = currRater.getListOfRatedMovies();
            for ( int k=0; k<uniqueMovies.size(); k++ ) {
                //System.out.println("Starting Movie: " + uniqueMovies.get(k));
                String movieId = uniqueMovies.get(k);
                double currRating = getAverageByID(movieId, minimalRaters); 
                if (currRating == 0.0 || movieCollection.containsKey(movieId) ) {
                    continue;
                }
                else {
                    movieCollection.put(movieId,currRating);
                }
            }
        }
        return movieCollection;
    }
    
    public void testGetAverageRatings() {
        HashMap<String,Double> movies = getAverageRatings(2);
        for (Map.Entry<String, Double> entry : movies.entrySet()) {
            System.out.println("Movie: " + entry.getKey() + ", Rating: " + entry.getValue());
        }
        
    }
    
    public HashMap<String,Double> convMovieIdToTiltle(HashMap<String,Double> moviesById) {
    HashMap<String,Double> moviesByTitle = new HashMap<String,Double>();
    
    for ( String movieId : moviesById.keySet() ) {
        String movieTitle = findTitleById(movieId);
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
    
    private String findTitleById (String movieId) {
    
        for ( int i=0; i<myMovies.size(); i++ ) {
            if ( myMovies.get(i).getID().equals(movieId) ) {
                return myMovies.get(i).getTitle();
            }
        }
        return "NO SUCH TITLE.";
    }
    
    public String findIdByTitle(String movieTitle) {
        for ( int i=0; i<myMovies.size(); i++ ) {
            if ( myMovies.get(i).getTitle().equals(movieTitle) ) {
                return myMovies.get(i).getID();
            }
        }
        return "NO SUCH ID.";
    }
    

    
    
    
    public SecondRatings(String movieFile, String ratingsFile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(movieFile);
        myRaters = fr.loadRaters(ratingsFile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
}
