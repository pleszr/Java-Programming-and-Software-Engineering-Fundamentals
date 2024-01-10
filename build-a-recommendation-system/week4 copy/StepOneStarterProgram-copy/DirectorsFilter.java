import java.util.*;

public class DirectorsFilter implements Filter {
    private String[] filterDirectors;

    // Constructor: splits the input string of directors into an array
    public DirectorsFilter(String directors) {
        filterDirectors = directors.split(",");
    }
    
    @Override
    // Checks if the movie (identified by id) satisfies the directors filter
    public boolean satisfies(String id) {
        // Split the directors of the movie into an array
        String[] movieDirectors = MovieDatabase.getDirector(id).split(",");
        // Create a HashSet from the movie directors for efficient searching
        Set<String> set = new HashSet<>(Arrays.asList(movieDirectors));
        
        // Used for debugging, commented out
        // System.out.println("------------------------");
        // System.out.println(MovieDatabase.getTitle(id));
        // System.out.println(Arrays.toString(movieDirectors));
        // System.out.println(Arrays.toString(filterDirectors));
        
        
        // Iterate over the filter directors and check if any of them matches the movie's directors
        for (String element : filterDirectors) {
            if (set.contains(element)) {
                //System.out.println("TRUE");
                return true; // Return true if there is a match
            }
        }
        //System.out.println("FALSE");
        return false; // Return false if no directors match
    }
}
