
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author Roland Plesz
 * @version 2024.01.07
 */
import java.util.*;
public class RecommendationRunner implements Recommender {
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> movies = new ArrayList<String>(Arrays.asList( "1617661", "1951266", "1243974", "2381249", "3503840", 
        "470752", "2561572", "2273657", "2918436", "3168230", "1340138", "4284010", "3819668", "1694021", "4046784", "3247714", 
        "1674771", "2726560", "2582496", "3705388"));
        
        return movies;
    }
    
    public void printRecommendationsFor(String webRaterID) {
        //initialize ForthRatings
        FourthRatings fr = new FourthRatings();
        
        //define parameters
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        Filter f = new TrueFilter();
        
        //call getSimilarRatingsByFilter with parameters and store in array
        ArrayList<Rating> result = fr.getSimilarRatingsByFilter(webRaterID,numSimilarRaters,minimalRaters,f);
        
        // Trim result to max 10 items
        if ( result.size() > 10 ) {
            result.subList(10, result.size()).clear();
        }
        
        // Start the table
        System.out.println("<table>");
        // Add table headers
        System.out.println("<tr><th>Rank</th><th>Rating</th><th>Title</th></tr>");
        
        // Iterate through the result ArrayList to populate the table
        for (int i = 0; i < result.size(); i++) {
            Rating r = result.get(i);
            // Each movie entry is a row in the table
            System.out.println("<tr>");
            // Add table data for rank, formatted rating, and title
            System.out.printf("<td>" + i+1 + "</td>" + "<td>" + r.getValue() + "</td>" + "<td>" + MovieDatabase.getTitle(r.getItem()) + "<td>");
            System.out.println("</tr>");
        }
        
        System.out.println("</table>"); // End of the table
        
        
        
        
    }

}
