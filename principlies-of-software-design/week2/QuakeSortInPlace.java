
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public void QuakeSortInPlace2() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
        
        sortByLargestDepth(list);
        
        System.out.println("read data for "+list.size()+" quakes");
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        System.out.println(checkInSortedOrder(list));
        
        
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>();
        copy.addAll(list);
       
   
        sortByMagnitudeWithBubbleSort(list);
        System.out.println("read data for "+list.size()+" quakes");
        for (QuakeEntry qe: copy) { 
            System.out.println(qe);
        } 
        System.out.println(checkInSortedOrder(list));
        System.out.println("---------");

        /*sortByMagnitudeWithCheck(copy);
        System.out.println("read data for "+copy.size()+" quakes");
        for (QuakeEntry qe: copy) { 
            System.out.println(qe);
        } 
        System.out.println(checkInSortedOrder(copy));*/
        
    }
    
    private int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        double largestSoFar = Double.MIN_VALUE;
        int maxInd = from;
        for ( int i = from+1; i < quakeData.size(); i++ ) {
            if ( quakeData.get(i).getDepth() > quakeData.get(maxInd).getDepth() ) {
                maxInd = i;    
            }
            
        }
        //System.out.println("internal:  from = " + from + ", maxInd = " + maxInd);
        return maxInd;
    }
    
    public ArrayList<QuakeEntry> sortByLargestDepth (ArrayList<QuakeEntry> in) {
        
        for (int i=0; i< 50; i++) {
            int maxIdx = getLargestDepth(in,i);
            //System.out.println("maxIdx" + maxIdx);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            //System.out.println("qi: " + qi);
            //System.out.println("qmax: " + qmax);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }
        return in;
    }
        
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
       
        for (int i=0; i< in.size(); i++) {
        if ( !checkInSortedOrder(in) )  {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);    
           }
        else {
            System.out.println("It needed " + i + " pass to sort the list.");
            break;
        }
        }
        
    }
    
    
    private boolean checkInSortedOrder(ArrayList<QuakeEntry> list) {
        for ( int i=0; i<list.size()-1; i++ ) {
            if ( list.get(i).getMagnitude() > list.get(i+1).getMagnitude() ) {
                return false;    
            }
        }
        
        return true;
    }

    
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> list, int numSorted) {
        /*
         * Write the void method onePassBubbleSort that has two parameters, an ArrayList of type QuakeEntry named quakeData
         * and an int named numSorted that represents the number of times this method has already been called on this ArrayList
         * and thus also represents the number of the elements that are guaranteed to already be where they belong when the 
         * ArrayList is sorted by magnitude. This method makes one pass of bubble sort on the ArrayList. It should take 
         * advantage of the fact that the last numSorted elements are already in sorted order. 
         * 
         */   
        
        for ( int i = 0; i < list.size() - numSorted-1; i++ ) {
            if ( list.get(i).getMagnitude() > list.get(i+1).getMagnitude() ) {
                QuakeEntry tempQe = list.get(i);
                list.set(i,list.get(i+1));
                list.set(i+1,tempQe);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> list) {
    /*
     * Write the void method sortByMagnitudeWithBubbleSort that has one parameter, an ArrayList of type QuakeEntry named in. 
     * If the ArrayList in has N elements in it, this method should call onePassBubbleSort N – 1 times to sort the elements 
     * in in.  
     */
        
        for ( int i=0; i < list.size(); i++ ) {
            if(! checkInSortedOrder(list)) {
                onePassBubbleSort(list,i);     
            }
            else {
                System.out.println("It needed " + i + " pass to sort the list.");
                break;
            }
        }
    }
    
    
    
    
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
