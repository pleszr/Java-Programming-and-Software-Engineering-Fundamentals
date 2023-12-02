
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes{
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany){
        /*
        * Complete the method getClosest that has already been started for you. This method has three parameters, an ArrayList 
        * of type QuakeEntry named quakeData, a Location named current, and an int named howMany. This method should find the
        * closest number of howMany earthquakes to the current Location and return them in an ArrayList of type QuakeEntry. 
        * The earthquakes should be in the ArrayList in order with the closest earthquake in index position 0.  If there are 
        * fewer then howMany earthquakes in quakeData, then the ArrayList returned would be the same size as quakeData. 
        */
    
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        
        
        //first find the closest 1
        
        for ( int i = 0; i<howMany && i<quakeData.size();i++ ) {
            double closestDist = Double.MAX_VALUE;
            QuakeEntry closestQuake = null;
            for (QuakeEntry qe : copy ) {
                double currentDist = current.distanceTo(qe.getLocation());
                if ( currentDist < closestDist ) {
                    closestDist = currentDist;
                    closestQuake = qe;
                }   
            } 
            ret.add(closestQuake);
            copy.remove(closestQuake);
        }


        return ret;
    }
   
    
    public void findClosestQuakes(){
        /*
        * The first method you need has already been written for you. The method findClosestQuakes reads in data on earthquakes
        * storing them in the ArrayList list and prints how many quakes there are. It sets a location variable named jakarta
        * to the location of the city Jakarta. It then calls the method getClosest to determine the ten closest earthquakes in
        * list and prints information about those quakes and how close they are to Jakarta. This method has already been
        * written for you, but doesn’t work yet since the method getClosest is not complete.
        */ 
        
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata small.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size());
        Location jakarta  = new Location(-6.211, 106.845);
        list = getClosest(list,jakarta,311);
        System.out.println("filtered to " + list.size() + " quakes:");
        for (QuakeEntry qe : list) {
           System.out.println(qe); 
        }
        
    
    }
}
