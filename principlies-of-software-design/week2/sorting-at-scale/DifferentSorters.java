
/**
 * Write a description of class DifferentSorters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class DifferentSorters {
    public void firstExercise() {
            
    }
    
    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list);
        
        int quakeNumber = 600;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
        System.out.println("All QuakeEntries in order:");
            for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }    
    
    public void sortByLastWordInTitleThenByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        
        int quakeNumber = 500;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
        System.out.println("All QuakeEntries in order:");
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        
    }
    
    public void sortByTitleAndDepth() {
        /*
         * Write the void method sortByTitleAndDepth in the DifferentSorters class. This method should create an 
         * EarthQuakeParser, read data from a file on earthquakes and create an ArrayList of QuakeEntry’s. Then this method 
         * should call Collections.sort on this ArrayList and use the TitleAndDepthComparator to sort the earthquakes. You 
         * should be able to see that the earthquakes are sorted by title first, and those with the same title are sorted by 
         * depth. Modify this method to print out the QuakeEntry in the ArrayList in position 10 (which is actually the 11th 
         * element in the ArrayList) after sorting and printing out all the elements.
         */
    
        
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new TitleAndDepthComparator());
        
        int quakeNumber = 500;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
        System.out.println("All QuakeEntries in order:");
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        
        /*
        for ( int i=0; i< list.size()-1; i++ ) {
            System.out.println("------------"); 
            QuakeEntry q1 = list.get(i);
            QuakeEntry q2 = list.get(i+1);
            
            String q1T = q1.getInfo();
            String q2T = q2.getInfo();
            
            int compTi = q2.compareTo(q1);
            int compDep = Double.compare(q1.getDepth(),q2.getDepth());
            
            System.out.println(q1T);
            System.out.println(q2T);
            System.out.println(compTi);
        */
        
    }

    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }

    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }
}
