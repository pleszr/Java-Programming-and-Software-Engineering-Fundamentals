
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    /*
     * Write the TitleLastAndMagnitudeComparator class that implements a Comparator of type QuakeEntry. In this class you should
     * write the compare method that has two parameters, a QuakeEntry named q1 and a QuakeEntry named q2. This method should 
     * compare the last word in the title of q1 and q2. If q1’s last word comes before q2’s last word in alphabetical order, 
     * then this method should return a negative integer. If q1’s last word comes after q2’s last word, then this method should
     * return a positive integer. If q1’s last word is the same as q2’s last word, then this method should compare the magnitude
     * of the two earthquakes. If q1’s magnitude is less than q2’s magnitude, then this method should return a negative number. 
     * If q1’s magnitude is greater than q2’s magnitude, then this method should return a positive integer. Otherwise, this 
     * method should return 0.
     */
    
    public static String findLastWord(String input) {
        // Split the string into words using space as the delimiter
        String[] words = input.split("\\s+");

        // Check if there are any words in the array
        if (words.length > 0) {
            // The last word is at index (length - 1)
            return words[words.length - 1];
        } else {
            // Return an empty string if there are no words
            return "";
        }
    }
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String q1T = findLastWord(q1.getInfo());
        String q2T = findLastWord(q2.getInfo());
        
        int compTi = q1T.compareTo(q2T);
        if ( compTi != 0 ) {
            return compTi;
        }
        int compMagn = Double.compare(q1.getMagnitude(),q2.getMagnitude());
        
        return compMagn;
    }

}
