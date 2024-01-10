
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings {
    public static void main(String[] args) {
    	MovieRunnerSimilarRatings o1 = new MovieRunnerSimilarRatings();
    	o1.printSimilarRatings();
    }
    public void  printAverageRatings() {
        // Initialize the file paths for movie and ratings data
        
        //String movieFile = "ratedmovies_short.csv";
        //String ratingsFile = "ratings_short.csv";
        String movieFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        
        // Create an instance of ThirdRatings with the ratings file
        FourthRatings fr = new FourthRatings();
        
        // Initialize the MovieDatabase with the movie data file
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingsFile);
        
        System.out.println("Imported " + MovieDatabase.size() + " movies.");
        System.out.println("Imported " + RaterDatabase.size() + " Raters.");
        
        int minRaters = 35;
        ArrayList<Rating> ratings = fr.getAverageRatingsByFilter(minRaters,new TrueFilter());
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies.");
        for (Rating r : ratings ) {
            String movieId = r.getItem();
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(movieId) + " " + MovieDatabase.getTitle(movieId));
        }
        
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        // Initialize the file paths for movie and ratings data
        String movieFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        
        // Create an instance of ThirdRatings with the ratings file
        FourthRatings fr = new FourthRatings();
        
        // Initialize the MovieDatabase with the movie data file
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingsFile);
        
        System.out.println("Imported " + MovieDatabase.size() + " movies.");
        System.out.println("Imported " + RaterDatabase.size() + " Raters.");
        
        int minRaters = 8;
        AllFilters currFilter = new AllFilters();
        Filter yearAfterFilter = new YearAfterFilter(1990);
        currFilter.addFilter(yearAfterFilter);
        Filter genreFilter = new GenreFilter("Drama");
        currFilter.addFilter(genreFilter);
        
        

        ArrayList<Rating> ratings = fr.getAverageRatingsByFilter(minRaters,currFilter);
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies.");
        
        for (Rating r : ratings ) {
            String movieId = r.getItem();
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(movieId) + " " + MovieDatabase.getTitle(movieId));
            System.out.println("\t" + MovieDatabase.getGenres(movieId));
        }

    }
    
    public void printSimilarRatings() {
        // Initialize the file paths for movie and ratings data
        String movieFile = null;
        String ratingsFile = null;
        
        //change full to short if you want to test with smaller dataset
        String shortOrFull = "full";
        if ( shortOrFull.equals("short") ) {
            movieFile = "ratedmovies_short.csv";
            ratingsFile = "ratings_short.csv";    
        }
        if ( shortOrFull.equals("full") ) {
            movieFile = "ratedmoviesfull.csv";
            ratingsFile = "ratings.csv";    
        }
        
        // Create an instance of ThirdRatings with the ratings file
        FourthRatings fr = new FourthRatings();
        
        // Initialize the MovieDatabase with the movie data file
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingsFile);
        
        System.out.println("Imported " + MovieDatabase.size() + " movies.");
        System.out.println("Imported " + RaterDatabase.size() + " Raters.");
        
        //parameters
        String raterId = "71";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        Filter f = new TrueFilter();
        
        //call getSimilarRatingsByFilter with parameters and store in array
        ArrayList<Rating> result = fr.getSimilarRatingsByFilter(raterId,numSimilarRaters,minimalRaters,f);
        
        
        
        
        //print array in fixed column width format
        for (int i = 0; i < result.size(); i++) {
            Rating r = result.get(i);
            // Print the rank (i), the rating value formatted to two decimal places, and the movie title
            // The rank field width is adjusted for alignment, the rating is right-justified, and the title is left-justified
            System.out.printf("%-3d %7.2f %-50s%n", i, r.getValue(), MovieDatabase.getTitle(r.getItem()));
        }
        
        // Trim result to max 10 items
        if ( result.size() > 10 ) {
            result.subList(10, result.size()).clear();
        }
        
        for (int i = 0; i < result.size(); i++) {
            Rating r = result.get(i);
            // Print the rank (i), the rating value formatted to two decimal places, and the movie title
            // The rank field width is adjusted for alignment, the rating is right-justified, and the title is left-justified
            System.out.printf("%-3d %7.2f %-50s%n", i, r.getValue(), MovieDatabase.getTitle(r.getItem()));
        }
        
        
    }
    
    public void printSimilarRatingsByGenre() {
        // Initialize the file paths for movie and ratings data
        String movieFile = null;
        String ratingsFile = null;
        
        //change full to short if you want to test with smaller dataset
        String shortOrFull = "full";
        if ( shortOrFull.equals("short") ) {
            movieFile = "ratedmovies_short.csv";
            ratingsFile = "ratings_short.csv";    
        }
        if ( shortOrFull.equals("full") ) {
            movieFile = "ratedmoviesfull.csv";
            ratingsFile = "ratings.csv";    
        }
        
        // Create an instance of ThirdRatings with the ratings file
        FourthRatings fr = new FourthRatings();
        
        // Initialize the MovieDatabase with the movie data file
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingsFile);
        
        System.out.println("Imported " + MovieDatabase.size() + " movies.");
        System.out.println("Imported " + RaterDatabase.size() + " Raters.");
        
        //parameters
        String raterId = "964";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        Filter f = new GenreFilter("Mystery");
        
        //call getSimilarRatingsByFilter with parameters and store in array
        ArrayList<Rating> result = fr.getSimilarRatingsByFilter(raterId,numSimilarRaters,minimalRaters,f);
        
        //print array in fixed column width format
        for (int i = 0; i < result.size(); i++) {
            Rating r = result.get(i);
            // Print the rank (i), the rating value formatted to two decimal places, and the movie title
            // The rank field width is adjusted for alignment, the rating is right-justified, and the title is left-justified
            System.out.printf("%-3d %7.2f %-50s%n", i, r.getValue(), MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector() {
        // Initialize the file paths for movie and ratings data
        String movieFile = null;
        String ratingsFile = null;
        
        //change full to short if you want to test with smaller dataset
        String shortOrFull = "full";
        if ( shortOrFull.equals("short") ) {
            movieFile = "ratedmovies_short.csv";
            ratingsFile = "ratings_short.csv";    
        }
        if ( shortOrFull.equals("full") ) {
            movieFile = "ratedmoviesfull.csv";
            ratingsFile = "ratings.csv";    
        }
        
        // Create an instance of ThirdRatings with the ratings file
        FourthRatings fr = new FourthRatings();
        
        // Initialize the MovieDatabase with the movie data file
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingsFile);
        
        System.out.println("Imported " + MovieDatabase.size() + " movies.");
        System.out.println("Imported " + RaterDatabase.size() + " Raters.");
        
        //parameters
        String raterId = "120";
        int numSimilarRaters = 10;
        int minimalRaters = 2;
        Filter f = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        
        //call getSimilarRatingsByFilter with parameters and store in array
        ArrayList<Rating> result = fr.getSimilarRatingsByFilter(raterId,numSimilarRaters,minimalRaters,f);
        
        //print array in fixed column width format
        for (int i = 0; i < result.size(); i++) {
            Rating r = result.get(i);
            // Print the rank (i), the rating value formatted to two decimal places, and the movie title
            // The rank field width is adjusted for alignment, the rating is right-justified, and the title is left-justified
            System.out.printf("%-3d %7.2f %-50s%n", i, r.getValue(), MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        // Initialize the file paths for movie and ratings data
        String movieFile = null;
        String ratingsFile = null;
        
        //change full to short if you want to test with smaller dataset
        String shortOrFull = "full";
        if ( shortOrFull.equals("short") ) {
            movieFile = "ratedmovies_short.csv";
            ratingsFile = "ratings_short.csv";    
        }
        if ( shortOrFull.equals("full") ) {
            movieFile = "ratedmoviesfull.csv";
            ratingsFile = "ratings.csv";    
        }
        
        // Create an instance of ThirdRatings with the ratings file
        FourthRatings fr = new FourthRatings();
        
        // Initialize the MovieDatabase with the movie data file
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingsFile);
        
        System.out.println("Imported " + MovieDatabase.size() + " movies.");
        System.out.println("Imported " + RaterDatabase.size() + " Raters.");
        
        //parameters
        String raterId = "168";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        AllFilters f = new AllFilters();
        f.addFilter(new GenreFilter("Drama"));
        f.addFilter(new MinutesFilter(80,160));
        
        
        
        //call getSimilarRatingsByFilter with parameters and store in array
        ArrayList<Rating> result = fr.getSimilarRatingsByFilter(raterId,numSimilarRaters,minimalRaters,f);
        
        //print array in fixed column width format
        for (int i = 0; i < result.size(); i++) {
            Rating r = result.get(i);
            // Print the rank (i), the rating value formatted to two decimal places, and the movie title
            // The rank field width is adjusted for alignment, the rating is right-justified, and the title is left-justified
            System.out.printf("%-3d %7.2f %-50s%n", i, r.getValue(), MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        // Initialize the file paths for movie and ratings data
        String movieFile = null;
        String ratingsFile = null;
        
        //change full to short if you want to test with smaller dataset
        String shortOrFull = "full";
        if ( shortOrFull.equals("short") ) {
            movieFile = "ratedmovies_short.csv";
            ratingsFile = "ratings_short.csv";    
        }
        if ( shortOrFull.equals("full") ) {
            movieFile = "ratedmoviesfull.csv";
            ratingsFile = "ratings.csv";    
        }
        
        // Create an instance of ThirdRatings with the ratings file
        FourthRatings fr = new FourthRatings();
        
        // Initialize the MovieDatabase with the movie data file
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingsFile);
        
        System.out.println("Imported " + MovieDatabase.size() + " movies.");
        System.out.println("Imported " + RaterDatabase.size() + " Raters.");
        
        //parameters
        String raterId = "314";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1975));
        f.addFilter(new MinutesFilter(70,200));
        
        
        
        //call getSimilarRatingsByFilter with parameters and store in array
        ArrayList<Rating> result = fr.getSimilarRatingsByFilter(raterId,numSimilarRaters,minimalRaters,f);
        
        //print array in fixed column width format
        for (int i = 0; i < result.size(); i++) {
            Rating r = result.get(i);
            // Print the rank (i), the rating value formatted to two decimal places, and the movie title
            // The rank field width is adjusted for alignment, the rating is right-justified, and the title is left-justified
            System.out.printf("%-3d %7.2f %-50s%n", i, r.getValue(), MovieDatabase.getTitle(r.getItem()));
        }
    }

}
