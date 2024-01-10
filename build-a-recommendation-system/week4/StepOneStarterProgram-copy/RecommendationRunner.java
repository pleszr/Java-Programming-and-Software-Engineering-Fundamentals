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
        int numSimilarRaters = 20;
        int minimalRaters = 1;
        Filter f = new TrueFilter();
        
        //call getSimilarRatingsByFilter with parameters and store in array
        ArrayList<Rating> result = fr.getSimilarRatingsByFilter(webRaterID,numSimilarRaters,minimalRaters,f);
        
        // Trim result to max 10 items
        if ( result.size() > 10 ) {
            result.subList(10, result.size()).clear();
        }
        
        if ( result.size() == 0 ) {
            System.out.println("Unfortunately, my friend, there is no recommendation for you at all. Please go back and enter more recommendation.");
        }
        else {
            printTable(result);
        }
        
        
        
        
        
        
    }
    
    private void printTable(ArrayList<Rating> result) {
        System.out.println("<style> table { width: 50%; border-collapse: collapse; margin-top: 20px; margin-left: auto;  /* Centers the table */ margin-right: auto; /* Centers the table */ } th { background-color: #3399FF; padding: 10px; text-align: center; } td { border: 1px solid #000; padding: 10px; vertical-align: middle; } .movie-info { display: flex; align-items: center; } .movie-poster { width: 100px; /* Adjust the width as needed */ height: auto; margin-right: 10px; } .movie-year { text-align: right; font-weight: bold; } .rating { text-align: center; } tr:nth-child(even) { background-color: #f2f2f2; } </style>");
        // Start the table
        System.out.println("<table>");
        System.out.println("    <thead>");
        System.out.println("        <tr><th>Poster & Year</th><th>Title</th>"); 
        System.out.println("    </thead>");   
        System.out.println("<tbody>");
        // Iterate through the result ArrayList to populate the table
        for (int i = 0; i < result.size(); i++) {
            Rating r = result.get(i);
            // Each movie entry is a row in the table
            System.out.println("<tr>");
            System.out.println("    <td class=\"movie-info\">");
            System.out.println("        <img src=\""+ MovieDatabase.getPoster(r.getItem()) + "\" alt=\"Movie Poster\" class=\"movie-poster\">");
            System.out.println("        <span class=\"movie-year\">" + MovieDatabase.getYear(r.getItem()) + "</span>");
            System.out.println("    </td>");
            System.out.println("    <td class=\"rating\">" + MovieDatabase.getTitle(r.getItem()));
            System.out.println("    </td>");
            System.out.println("</tr>");
        }
        
        System.out.println("</tbody></table>"); // End of the table
    }

}
