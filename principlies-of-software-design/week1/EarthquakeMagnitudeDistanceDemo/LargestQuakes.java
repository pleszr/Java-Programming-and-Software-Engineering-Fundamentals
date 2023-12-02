
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class LargestQuakes {

    public void findLargestQuakes() {
        /*
         * Write a void method named findLargestQuakes that reads in earthquake data from a source and storing them into an 
         * ArrayList of type QuakeEntry. Then it prints all the earthquakes and how many earthquakes that were from the source.
         * You should read in earthquakes from the small file nov20quakedatasmall.atom, print all the earthquakes and also print
         * how many there are. After this works you should comment out the printing of all the earthquakes, but continue to print 
         * out the total number of earthquakes read in. 
         */
        
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        ArrayList<QuakeEntry> largestList = getLargest(list,5);
        System.out.println("Found " + largestList.size() + " quakes");
        printQuakeList(largestList);
        
        
        
    }
    
    public void printQuakeList(ArrayList<QuakeEntry> list) {
        for (QuakeEntry qe : list) {
           System.out.println(qe); 
        }    
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        /*
         * Write a method named getLargest that has two parameters, an ArrayList of type QuakeEntry named quakeData and an 
         * integer named howMany. This method returns an ArrayList of type QuakeEntry of the top howMany largest magnitude 
         * earthquakes from quakeData. The quakes returned should be in the ArrayList in order by their magnitude, with the 
         * largest magnitude earthquake in index position 0. If quakeData has fewer than howMany earthquakes, then the number 
         * of earthquakes returned in the ArrayList is equal to the number of earthquakes in quakeData. This method should 
         * \call the method indexOfLargest.
         */
        
        //ret will be returned, copy is required because it is an iteration and the largest is always removed from it
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = quakeData;
        
        //iterate as many times as howMany or the size of the original data set
        for ( int i=0; i < howMany && i < quakeData.size(); i++) {
            //find the index of quake with the largest magnitude
            int indexOfLargest = indexOfLargest(copy);
            QuakeEntry foundQuake = copy.get(indexOfLargest);
            //once found, add it to return
            ret.add(foundQuake);
            //remove from copy. need to be removed from copy so the next iteration doesnt find the same quake
            copy.remove(foundQuake);
        }
        return ret;
    }
    
    private int indexOfLargest (ArrayList<QuakeEntry> quakeData) {
        /*
         * Write a method named indexOfLargest that has one parameter, an ArrayList of type QuakeEntry named data. This method 
         * returns an integer representing the index location in data of the earthquake with the largest magnitude. You should
         * test out this method by adding code to the method  findLargestQuakes to print the index location of the largest
         * magnitude earthquake in the file nov20quakedatasmall.atom and the earthquake at that location. You will see that 
         * the largest such earthquake is at location 3 and has magnitude 5.50. Once this works you may want to comment this out. 
         */
        double largestSoFar = Double.MIN_VALUE;
        int indexOfLargest = 0;
        
        for ( int i=0; i<quakeData.size();i++) {
            double currMagnitude = quakeData.get(i).getMagnitude();
            if ( currMagnitude > largestSoFar ) {
                largestSoFar = currMagnitude;
                indexOfLargest = i;
            }
        }
        return indexOfLargest;
    }
    
    
}
