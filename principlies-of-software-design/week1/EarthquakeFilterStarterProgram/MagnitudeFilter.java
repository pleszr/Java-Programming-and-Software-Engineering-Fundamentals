
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter {
    private double magMin;
    private double magMax;
    
    public MagnitudeFilter(double min, double max) {
        magMax = max;
        magMin = min;
    }
    
    public String getName() {
        return "magnitude";
    }
    
    
    public boolean satisfies(QuakeEntry qe) {
        if ( qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax ) {
            return true;
        }
        return false;
    }

}
