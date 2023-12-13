
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
/*
 * Write the TitleAndDepthComparator class that implements a Comparator of type QuakeEntry. In this class you should write the 
 * compare method that has two parameters, a QuakeEntry named q1 and a QuakeEntry named q2. This method should compare the title
 * of q1 and q2. If q1’s title comes before q2’s title in alphabetical order, then this method should return a negative integer.
 * If q1’s title comes after q2’s title, then this method should return a positive integer. If q1’s title is the same as q2’s 
 * title, then this method should compare the depth of the two earthquakes. If q1’s depth is less than q2’s depth, then this
 * method should return a negative number. If q1’s depth is greater than q2’s depth, then this method should return a positive
 * integer. Otherwise, this method should return 0. 
 */
public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    
    
    
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String q1T = q1.getInfo();
        String q2T = q2.getInfo();
        int compTi = q1T.compareTo(q2T);
        int compDep = Double.compare(q1.getDepth(),q2.getDepth());
        if ( compTi != 0 ) {
            return compTi;
        }
        else if ( compDep != 0 ) {
            return compDep;        
        }
        
        return 0;
    }
}
