
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {
    
    
    public void printAverageRatings() {
        String movieFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        SecondRatings sr = new SecondRatings(movieFile,ratingsFile);
        System.out.println("Number of imported movies: " + sr.getMovieSize());
        System.out.println("Number of imported Raters: " + sr.getRaterSize());
        
        HashMap<String,Double> movieRtgById = sr.getAverageRatings(12);
        HashMap<String,Double> movieRtgByTitle = sr.convMovieIdToTiltle(movieRtgById);
        LinkedHashMap<String,Double> orderMoviesByRtg = sr.orderByRatings(movieRtgByTitle);

        printMovieMap(orderMoviesByRtg);
    }
    
    
    public void testGetAverageRatingOneMovie() {
        getAverageRatingOneMovie("The Maze Runner");  
        getAverageRatingOneMovie("Moneyball");  
        getAverageRatingOneMovie("Vacation");  
        

    }
    
    public void getAverageRatingOneMovie(String movieTitle) {
        String movieFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        SecondRatings sr = new SecondRatings(movieFile,ratingsFile);
        String movieId = sr.findIdByTitle(movieTitle);
        double movieRatingAvg = sr.getAverageByID(movieId,0);
        System.out.println(movieTitle + " " + movieRatingAvg);
    }
    
    private void printMovieMap(HashMap<String,Double> movies) {
        for (Map.Entry<String, Double> entry : movies.entrySet()) {
            System.out.println(entry.getKey() +" " + entry.getValue());
        }
    }
    
    

}
