
public class QuakeEntry implements Comparable<QuakeEntry>{
    
    private Location myLocation;
    private String title;
    private double depth;
    private double magnitude;

    public QuakeEntry(double lat, double lon, double mag, 
                      String t, double d) {
        myLocation = new Location(lat,lon);
        
        magnitude = mag;
        title = t;
        depth = d;
    }
    
    public Location getLocation(){
        return myLocation;
    }
    
    public double getMagnitude(){
        return magnitude;
    }
    public String getInfo(){
        return title;
    }
    public double getDepth(){
        return depth;
    }

    @Override
    public int compareTo(QuakeEntry loc) {
        /*
         * Modify the compareTo method in the QuakeEntry class. (You may want to comment out the current code first). The 
         * compareTo method should now sort quake by magnitude first, from smallest magnitude to largest magnitude, and then
         * break ties (use == operator to determine whether magnitudes are equal) by depth, from smallest (most negative) 
         * depth to largest depth.
         */
        int compMagn = Double.compare(magnitude, loc.getMagnitude());
        int compDep = Double.compare(depth,loc.getDepth());
        if ( compMagn != 0 ) {
            return compMagn;
        }
        else {
            return compDep;
        }
       
        //return ;
        // Here is another way to sort by Magnitude
        /*
        if (magnitude < loc.getMagnitude()){
            return -1;
           }
        if (magnitude > loc.getMagnitude()){
            return 1;
           }
        return 0;
        
        */
        
        //  Below here sorts by Location
        /*
        double difflat = myLocation.getLatitude() - loc.myLocation.getLatitude();
        if (Math.abs(difflat) < 0.001) {
            double diff = myLocation.getLongitude() - loc.myLocation.getLongitude();
            if (diff < 0) return -1;
            if (diff > 0) return 1;
            return 0;
        }
        if (difflat < 0) return -1;
        if (difflat > 0) return 1;
        
        
        // never reached
        return 0;
        */
    }
    
    public String toString(){
        return String.format("(%3.2f, %3.2f), mag = %3.2f, depth = %3.2f, title = %s", myLocation.getLatitude(),myLocation.getLongitude(),magnitude,depth,title);
    }
    
}