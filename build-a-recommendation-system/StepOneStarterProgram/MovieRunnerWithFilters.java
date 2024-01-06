
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {
    
    public void printAverageRatingsByYearAfter() {
        // Initialize the file paths for movie and ratings data
        String movieFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        
        // Create an instance of ThirdRatings with the ratings file
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        
        // Initialize the MovieDatabase with the movie data file
        MovieDatabase.initialize(movieFile);
        System.out.println("Imported " + MovieDatabase.size() + " movies.");
        System.out.println("Imported " + tr.getRaterSize() + " Raters.");
        
        Filter yearAfterFilter = new YearAfterFilter(2000);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(20,yearAfterFilter);
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies.");
        printRatings(ratings);
        
    }
    
    public void printAverageRatings() {
        // Initialize the file paths for movie and ratings data
        
        //String movieFile = "ratedmovies_short.csv";
        //String ratingsFile = "ratings_short.csv";
        String movieFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        
        // Create an instance of ThirdRatings with the ratings file
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        
        // Initialize the MovieDatabase with the movie data file
        MovieDatabase.initialize(movieFile);
        System.out.println("Imported " + MovieDatabase.size() + " movies.");
        System.out.println("Imported " + tr.getRaterSize() + " Raters.");
        
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(35,new TrueFilter());
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies.");
        printRatings(ratings);
        
    }
    
    public void printAverageRatingsByGenre() {
        // Initialize the file paths for movie and ratings data
        String movieFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        
        // Create an instance of ThirdRatings with the ratings file
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        
        // Initialize the MovieDatabase with the movie data file
        MovieDatabase.initialize(movieFile);
        System.out.println("Imported " + MovieDatabase.size() + " movies.");
        System.out.println("Imported " + tr.getRaterSize() + " Raters.");
        
        Filter genreFilter = new GenreFilter("Comedy");
        //Filter yearAfterFilter = new TrueFilter();
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(20,genreFilter);
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies.");
        for (Rating r : ratings ) {
            String movieId = r.getItem();
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(movieId));
            System.out.println("\t" + MovieDatabase.getGenres(movieId));
        }
    }
    
    public void printAverageRatingsByMinutes() {
        // Initialize the file paths for movie and ratings data
        String movieFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        
        // Create an instance of ThirdRatings with the ratings file
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        
        // Initialize the MovieDatabase with the movie data file
        MovieDatabase.initialize(movieFile);
        System.out.println("Imported " + MovieDatabase.size() + " movies.");
        System.out.println("Imported " + tr.getRaterSize() + " Raters.");
        
        Filter currFilter = new MinutesFilter(105,135);
        //Filter yearAfterFilter = new TrueFilter();
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(5,currFilter);
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies.");
        for (Rating r : ratings ) {
            String movieId = r.getItem();
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(movieId)+ " " + MovieDatabase.getTitle(movieId));
        }
    }
    
    public void printAverageRatingsByDirectors() {
        // Initialize the file paths for movie and ratings data
        String movieFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        
        // Create an instance of ThirdRatings with the ratings file
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        
        // Initialize the MovieDatabase with the movie data file
        MovieDatabase.initialize(movieFile);
        System.out.println("Imported " + MovieDatabase.size() + " movies.");
        System.out.println("Imported " + tr.getRaterSize() + " Raters.");
        
        Filter currFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(4,currFilter);
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies.");
        for (Rating r : ratings ) {
            String movieId = r.getItem();
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(movieId));
            System.out.println("\t" + MovieDatabase.getDirector(movieId));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        // Initialize the file paths for movie and ratings data
        String movieFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        
        // Create an instance of ThirdRatings with the ratings file
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        
        // Initialize the MovieDatabase with the movie data file
        MovieDatabase.initialize(movieFile);
        System.out.println("Imported " + MovieDatabase.size() + " movies.");
        System.out.println("Imported " + tr.getRaterSize() + " Raters.");
        
        
        AllFilters currFilter = new AllFilters();
        Filter yearAfterFilter = new YearAfterFilter(1990);
        currFilter.addFilter(yearAfterFilter);
        Filter genreFilter = new GenreFilter("Drama");
        currFilter.addFilter(genreFilter);
        
        

        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(8,currFilter);
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies.");
        
        for (Rating r : ratings ) {
            String movieId = r.getItem();
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(movieId) + " " + MovieDatabase.getTitle(movieId));
            System.out.println("\t" + MovieDatabase.getGenres(movieId));
        }

    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        // Initialize the file paths for movie and ratings data
        String movieFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        
        // Create an instance of ThirdRatings with the ratings file
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        
        // Initialize the MovieDatabase with the movie data file
        MovieDatabase.initialize(movieFile);
        System.out.println("Imported " + MovieDatabase.size() + " movies.");
        System.out.println("Imported " + tr.getRaterSize() + " Raters.");
        
        
        AllFilters currFilter = new AllFilters();
        Filter minutesFilter = new MinutesFilter(90,180);
        currFilter.addFilter(minutesFilter);
        Filter dirFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        currFilter.addFilter(dirFilter);
        
        

        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(3,currFilter);
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies.");
        
        for (Rating r : ratings ) {
            String movieId = r.getItem();
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(movieId) + " " + MovieDatabase.getTitle(movieId));
            System.out.println("\t" + MovieDatabase.getDirector(movieId));
        }

    }
    
    
    
    public void printRatings(ArrayList<Rating> ratings) {
        //sample print: 7.0 2013 Dallas Buyers Club
        for (Rating r : ratings ) {
            String movieId = r.getItem();
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(movieId) + " " + MovieDatabase.getTitle(movieId));
        }
    }

    
    // public void printMoviesList(ArrayList<String> movies) {
        // //sample print: 7.0 2013 Dallas Buyers Club
        // for ( String s: movies ) {
            // System.out.println(tr.        
        // }
    // }
    
    // public void printAverageRatings() {
        // String movieFile = "ratedmoviesfull.csv";
        // String ratingsFile = "ratings.csv";
        // ThirdRatings tr = new ThirdRatings(ratingsFile);
        // MovieDatabase.initialize(movieFile);
        // System.out.println("Number of imported movies: " + MovieDatabase.size());
        // System.out.println("Number of imported Raters: " + tr.getRaterSize());
        
        // HashMap<String,Double> movieRtgById = tr.getAverageRatings(12);
        // //printMovieMap(movieRtgById);
        // HashMap<String,Double> movieRtgByTitle = tr.convMovieIdToTitle(movieRtgById);
        // //printMovieMap(movieRtgByTitle);
        // LinkedHashMap<String,Double> orderMoviesByRtg = tr.orderByRatings(movieRtgByTitle);

        // printMovieMap(orderMoviesByRtg);
    // }
    
    private void printMovieMap(HashMap<String,Double> movies) {
        for (Map.Entry<String, Double> entry : movies.entrySet()) {
            System.out.println(entry.getKey() +" " + entry.getValue());
        }
    }

}
