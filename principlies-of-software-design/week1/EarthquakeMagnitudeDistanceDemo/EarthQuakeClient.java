
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    
    public void starter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        list = filterByMagnitude(list,5.5);
        dumpCSV(list);
        
    }
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        /*
         * Write the method filterByMagnitude that has already been started for you. This method has two parameters, an 
         * ArrayList of type QuakeEntry named quakeData, and a double named magMin. This method should return an ArrayList of 
         * type QuakeEntry of all the earthquakes from quakeData that have a magnitude larger than magMin. Notice that we 
         * have already created an ArrayList named answer for you to store those earthquakes that satisfy this requirement. 
         */
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData ) {
            if ( from.distanceTo(qe.getLocation()) < distMax ) {
                //System.out.println("distmax: " + distMax + "distanceTo: " + from.distanceTo(qe.getLocation()) + " and in reverse : " + qe.getLocation().distanceTo(from));
                answer.add(qe);    
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth ) {
        /*
         * Write the method filterByDepth that has three parameters, an ArrayList of type QuakeEntry named quakeData,  a 
         * double named minDepth and a double named maxDepth. This method should return an ArrayList of type QuakeEntry of 
         * all the earthquakes from quakeData whose depth is between minDepth and maxDepth, exclusive. (Do not include quakes
         * with depth exactly minDepth or maxDepth.)
         */
        
        //create list to send back
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        //iterate trough all quakeentry 
        for (QuakeEntry qe : quakeData ) {
            //filter for depth
            if ( qe.getDepth() > minDepth && qe.getDepth() < maxDepth ) {
                answer.add(qe);    //add to answer list if depth is between boundaries
            }
        }
        return answer;
    }
    
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String phraseLoc, String phrase) {
        /*
         * Write the method filterByPhrase that has three parameters, an ArrayList of type QuakeEntry named quakeData, a String 
         * named where that indicates where to search in the title and has one of three values: (“start”, ”end”, or “any”), and a 
         * String named phrase, indicating the phrase to search for in the title of the earthquake. The title of the earthquake 
         * can be obtained through the getInfo() method. The filterByPhrase method should return an ArrayList of type QuakeEntry 
         * of all the earthquakes from quakeData whose titles have the given phrase found at location where (“start” means the 
         * phrase must start the title, “end” means the phrase must end the title and “any” means the phrase is a substring 
         * anywhere in the title.)
         */
        
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        //phraseLoc can be start,end,any
        //if ( !phraseLoc.equals("start") || !phraseLoc.equals("end") || !phraseLoc.equals("any") ) {
        //    return answer;
        //}
        
        int phraseLength = phrase.length();
        
        for (QuakeEntry qe : quakeData ) {
            String title = qe.getInfo();
            /* //testing prints
            //System.out.println("phraseLoc: " + phraseLoc + " phrase: " + phrase);
            System.out.println(phraseLoc.equals("end"));
            System.out.println(" phrase: " + phrase.length() + " title.substring(title.length()-phraseLength) " + title.substring(title.length()-phraseLength).length());
            System.out.println(title.substring(title.length()-phraseLength).equals(phrase));
            System.out.println("***" + title.substring(title.length()-phrase.length()) + "***");
            //System.out.println("title.substring(title.length()-phraseLength): " + title.substring(title.length()-phraseLength));
            */
            
            if ( phraseLoc.equals("start") ) {
                if ( title.substring(0,phrase.length()).equals(phrase) ) {
                    answer.add(qe);    
                }
            }
            if ( phraseLoc.equals("any") ) {
                if ( title.indexOf(phrase) != -1 ) {
                    answer.add(qe);    
                }
            }
            if ( phraseLoc.equals("end") ) {
                if ( title.substring(title.length()-phrase.length()).equals(phrase) ) {
                    answer.add(qe);    
                }
            }
        }
        
        return answer;
    }
    
            
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
    
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata small.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void quakesOfDepth() {
        /*
         * Write the void method quakesOfDepth that has no parameters to use filterByDepth and print all the earthquakes from a 
         * data source whose depth is between a given minimum and maximum value. You should also print out the number of 
         * earthquakes found. After writing this method, when you run your program on the file nov20quakedatasmall.atom for 
         * quakes with depth between -10000.0 and -5000.0 you should find five such quakes and get the output:
         */
        
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        list = filterByDepth(list,-8000.0,-5000.0);
        System.out.println("filtered to " + list.size() + " quakes:");
        for (QuakeEntry qe : list) {
           System.out.println(qe); 
        }
    }
    
    public void quakesByPhrase() {
        /*
         * Write the void method quakesByPhrase to use filterByPhrase and print all the earthquakes from a data source that 
         * have phrase in their title in a given position in the title. You should also print out the number of earthquakes 
         * found. After writing this method, when you run your program on the file nov20quakedatasmall.atom for quakes with 
         * phrase “California” and where set to “end”  you should find four such quakes and get the output:
         */
        
        //    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String phraseLoc, String phrase) {

        
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("---quakesByPhrase---");
        System.out.println("# quakes read: " + list.size());
        
        
        System.out.println("---------");
        String phase1 = "California";
        String loc1 = "end";
        ArrayList<QuakeEntry> listCalEndCopy = filterByPhrase(list,loc1,phase1);
        for (QuakeEntry qe : listCalEndCopy) {
           System.out.println(qe); 
        }
        System.out.println("Found " + listCalEndCopy.size() + " quakes that match " + phase1 + " at " + loc1);
        
        System.out.println("---------");
        String phase2 = "Creek";
        String loc2 = "any";
        ArrayList<QuakeEntry> listCalAny = filterByPhrase(list,loc2,phase2);
        System.out.println("filtered to " + listCalAny.size() + " quakes:");
        for (QuakeEntry qe : listCalAny) {
           System.out.println(qe); 
        }
        System.out.println("Found " + listCalAny.size() + " quakes that match " + phase2 + " at " + loc2);
        
        System.out.println("---------");
        String phase3 = "Explosion";
        String loc3 = "start";
        ArrayList<QuakeEntry> listExplosionStart = filterByPhrase(list,loc3,phase3);
        System.out.println("filtered to " + listExplosionStart.size() + " quakes:");
        for (QuakeEntry qe : listExplosionStart) {
           System.out.println(qe); 
        }
        System.out.println("Found " + listExplosionStart.size() + " quakes that match " + phase3 + " at " + loc3);
        
    }
    
    
    
    
    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata small.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        
        //    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      

        
        //Durham, NC
        //Location city = new Location(35.988, -78.907);
        
        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        
        list = filterByDistanceFrom(list,1000 * 1000,city);
        for (QuakeEntry qe : list) {
            System.out.println("Distance from Bridgeport, CA: " + city.distanceTo(qe.getLocation())/1000 + " km " + qe.getInfo()); 
        }
        
        
        
   
    }
}
