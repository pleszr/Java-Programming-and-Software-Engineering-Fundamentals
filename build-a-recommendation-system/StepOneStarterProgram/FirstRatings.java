
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename) {
    /*
     * Write a method named loadMovies that has one parameter, a String named filename. This method should process every record
     * from the CSV file whose name is filename, a file of movie information, and return an ArrayList of type Movie with all of 
     * the movie data from the file.
     */
    ArrayList<Movie> importedMovies = new ArrayList<Movie>();
    FileResource fr = new FileResource("data/" + filename);
    
    CSVParser parser = fr.getCSVParser();
    for ( CSVRecord currRecord : parser ) {
        //csv headers: id	title	year	country	genre	director	minutes	poster
        String id = currRecord.get("id");
        String title = currRecord.get("title");
        String year = currRecord.get("year");
        String country = currRecord.get("country");
        String genre = currRecord.get("genre");
        String director = currRecord.get("director");
        int minutes = Integer.parseInt(currRecord.get("minutes"));
        String poster = currRecord.get("genre");
        //public Movie (String anID, String aTitle, String aYear, String theGenres, String aDirector,
        //String aCountry, String aPoster, int theMinutes)
        Movie newMovie = new Movie(id,title,year,genre,director,country,poster,minutes);
        importedMovies.add(newMovie);
    }
    return importedMovies;
    }
    
    public void printMovies(ArrayList<Movie> movies){
        for ( int i=0; i<movies.size(); i++ ) {
            System.out.println(movies);
        }
    }
    
    public void printRaters(ArrayList<Rater> raters){
        for ( int i=0; i<raters.size(); i++ ) {
            System.out.println(raters);
        }
    }
    
    public int countByGenre(ArrayList<Movie> movies, String genre){
        int counter = 0;
        for ( int i=0; i<movies.size(); i++ ) {
            if ( movies.get(i).getGenres().contains(genre)) {
                counter++;
            }
        }
        return counter;
    }
    
    public int countByGreaterThenMins(ArrayList<Movie> movies, int mins) {
        int counter = 0;
        for ( int i=0; i<movies.size(); i++ ) {
            if ( movies.get(i).getMinutes()>mins) {
                counter++;
            }
        }
        return counter;   
    }
    
    public void printDirsWithMostMovies(ArrayList<Movie> movies) {
        //HashMap to store all the directors and counts
        HashMap<String,Integer> dirMap = new HashMap<String,Integer>();
        
        //iterate trough all the movies
        for ( int i=0; i<movies.size(); i++ ) {
            //movies can have multipe directors, separated with ",". 
            String[] directors = movies.get(i).getDirector().split(",");
            //iterate through all directors 
            for ( int k=0; k<directors.length; k++ ) {
                //if director is already in hashmap then increase counter
                if ( dirMap.containsKey(directors[k] )) {
                    int currCounter = dirMap.get(directors[k]);
                    dirMap.put(directors[k],currCounter+1);  
                }
                //if director is not in hashmap, put there
                else {
                    dirMap.put(directors[k],1);      
                }
            }
        }
        //find the highest director count
        int maxCounter = Collections.max(dirMap.values());
        System.out.println("Max. number by a director is: " + maxCounter + ". Directors with this many movie(s) are:");
        //print all from directors count
        for ( String s : dirMap.keySet() ) {
            if ( dirMap.get(s) == maxCounter ) {
                System.out.println(s);
            }
        }
    }
    
    public void testLoadMovies() {
        //ArrayList<Movie> movieList = loadMovies("ratedmoviesfull.csv");  
        //ArrayList<Movie> movieList = loadMovies("ratedmovies_short.csv"); 
        ArrayList<Movie> movieList = loadMovies("ratedmoviesfull.csv");
        
    
        System.out.println("Number of movies: " + movieList.size());
        System.out.println("Number of movies with genre: Comedy: " + countByGenre(movieList,"Comedy"));
        System.out.println("Number of movies longer then 150 mins: " + countByGreaterThenMins(movieList,150));
        printDirsWithMostMovies(movieList);

        printMovies(movieList);
    }
    
    private int findIdRaterInList(ArrayList<Rater> raterList, String raterId) {
        for ( int i=0; i<raterList.size(); i++ ) {
            if ( raterList.get(i).getID().equals(raterId) ) {
                return i;
            }
        }
        return -1;
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> importedRaters = new ArrayList<Rater>();
        FileResource fr = new FileResource("data/" + filename);
        
        CSVParser parser = fr.getCSVParser();
        for ( CSVRecord currRecord : parser ) {
            //first do the import for all 
            //csv headers: rater_id	movie_id	rating	time
            String raterId = currRecord.get("rater_id");
            String movieId = currRecord.get("movie_id");
            Double rating = Double.parseDouble(currRecord.get("rating"));
            //String time = currRecord.get("time"); //is not used now but is part of csv
            //create rater, as id is not fix, keep it as null
            Rater currRater = null;
            //try to  look up if if Rater in List. Returns id or -1 if not found
            int raterInd = findIdRaterInList(importedRaters, raterId);
            //if not found in list, create new Rater
            if ( raterInd == -1 ) {
                currRater = new Rater(raterId);
                importedRaters.add(currRater);
            }
            //if rater is found, set currRater to found rater
            else {
                currRater = importedRaters.get(raterInd);
            }
            //add ratings
            currRater.addRating(movieId,rating);
        }
        return importedRaters;
    }
    
    public void testLoadRaters() {
        /*
         * Call the method loadRaters on the file ratings_short.csv and store the result in a local ArrayList variable. Print
         * the total number of raters. Then for each rater, print the rater’s ID and the number of ratings they did on one 
         * line, followed by each rating (both the movie ID and the rating given) on a separate line. If you run your program
         * on the file ratings_short.csv you will see there are five raters. After it looks like it works, you may want to 
         * comment out the printing of each rater and their ratings. If you run your program on the file ratings.csv, you 
         * should get 1048 raters.
         */
        //ArrayList<Rater> raterList = loadRaters("ratings_short.csv");
        ArrayList<Rater> raterList = loadRaters("ratings.csv");
        System.out.println("Imported " + raterList.size() + " Raters.");
        //System.out.println("Imported Raters:");
        // for ( int i=0; i<raterList.size(); i++ ) {
            // Rater currRater = raterList.get(i);
            // System.out.println("Rater id: " + currRater.getID() + ". Has " + currRater.numRatings() + " ratings. They are:");
            // currRater.printAllRatings();
        // }
        
        int mostRatings = findMostRatings(raterList);
        System.out.println("Most Number of ratings is: " + mostRatings);
        System.out.println("Raters with this amount of Ratings:");
        printRatersWithRatingNr(raterList,mostRatings);
        
        String raterId1 = "193";
        System.out.println("The rater: " + raterId1 + " rated " + nrOfRatingsByRaterId(raterList,raterId1) + " movies");
        
        String testMovieId1 = "1798709";
        System.out.println("Number of raters for movieId: " + testMovieId1 + " is: " + numberOfRatersByMovie(raterList,testMovieId1));
        System.out.println("Total number of unique movies rated is: " + numberOfUniqueMovies(raterList));
        
    }
    
    public int numberOfRatersByMovie(ArrayList<Rater> raterList, String movieId) {
        /*
         * Add code to find the number of ratings a particular movie has. If you run this code on the file ratings_short.csv 
         * for the movie “1798709”, you will see it was rated by four raters.
         */  
        int ratingCounter = 0;    
            for ( int i=0; i<raterList.size(); i++ ) {
                Rater currRater = raterList.get(i);
                if ( currRater.hasRating(movieId) ) {
                    ratingCounter++;
                }
            }    
        return ratingCounter;
    }
    
    public int numberOfUniqueMovies(ArrayList<Rater> raterList) {
        //create a temp ArrayList for the unique moves
        ArrayList<String> uniqueMovies = new ArrayList<String>();
        //first iterate on all rater
        for ( int i=0; i<raterList.size(); i++ ) {
                Rater currRater = raterList.get(i);
                //create a list of movies for the rater
                ArrayList<String> moviesFromRater = currRater.getListOfRatedMovies();
                //iterate on all movies
                for ( int k=0; k < moviesFromRater.size(); k++ ) {
                    //if movie is not part of uniqueMovies then add
                    if ( !uniqueMovies.contains(moviesFromRater.get(k)) ) {
                        uniqueMovies.add(moviesFromRater.get(k));
                    }
                }

        }  
        return uniqueMovies.size();
    }
    
    public int nrOfRatingsByRaterId(ArrayList<Rater> raterList, String raterId) {
    /*
     * Add code to find the number of ratings for a particular rater you specify in your code. For example, if you run this 
     * code on the rater whose rater_id is 2 for the file ratings_short.csv, you will see they have three ratings. 
     */
    
    for ( int i=0; i<raterList.size(); i++ ) {
        Rater currRater = raterList.get(i);
        if ( currRater.getID() == raterId ) {
            return currRater.numRatings();
        }
    }
    return -1;
    }
    
    public int findMostRatings (ArrayList<Rater> raterList) {
        /*
         * Add code to find the maximum number of ratings by any rater. Determine how many raters have this maximum number of 
         * ratings and who those raters are. If you run this code on the file ratings_short.csv, you will see rater 2 has three 
         * ratings, the maximum number of ratings of all the raters, and that there is only one rater with three ratings.
         */
        int maxRatings = 0;
        for ( int i=0; i<raterList.size(); i++ ) {
            Rater currRater = raterList.get(i);
            if ( currRater.numRatings() > maxRatings ) {
                maxRatings = currRater.numRatings();
            } 
        }   
        return maxRatings;
    }
    
    public void printRatersWithRatingNr(ArrayList<Rater> raterList, int ratingNr) {
        ArrayList<Rater> ratersWithRatingNo = new ArrayList<Rater>();
        for ( int i=0; i<raterList.size(); i++ ) {
            Rater currRater = raterList.get(i);
            if ( currRater.numRatings() == ratingNr ) {
                ratersWithRatingNo.add(currRater);    
            }
        }     
        printRaters(ratersWithRatingNo);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}












