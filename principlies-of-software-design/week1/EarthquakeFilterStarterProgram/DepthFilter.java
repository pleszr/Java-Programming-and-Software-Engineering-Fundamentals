
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    private double depMin;
    private double depMax;
    
    public DepthFilter(double min, double max) {
        depMax = max;
        depMin = min;
    }
    
    public String getName() {
        return "depth";
    }
    
    
    public boolean satisfies(QuakeEntry qe) {
        if ( qe.getDepth() >= depMin && qe.getDepth() <= depMax ) {
            return true;
        }
        return false;
    }

}
