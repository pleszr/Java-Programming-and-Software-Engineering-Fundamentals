
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    
    private Location loc;
    private double maxDist;
    
    public DistanceFilter(Location inputLoc, double inputMaxDist) {
        loc = inputLoc;
        maxDist = inputMaxDist;
    }
    
    public String getName() {
        return "distance";
    }
    
    
    public boolean satisfies(QuakeEntry qe) {
        if ( loc.distanceTo(qe.getLocation()) < maxDist*1000 ) {
            return true;
        }
        return false;
    }

}
