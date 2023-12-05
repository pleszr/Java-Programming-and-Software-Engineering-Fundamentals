import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 
    
    public ArrayList<QuakeEntry> sortByMagnWCopy(ArrayList<QuakeEntry> quakeData) {
        ArrayList<QuakeEntry> copy = quakeData;
        ArrayList<QuakeEntry> sorted = new ArrayList<QuakeEntry>();
        while ( !copy.isEmpty() ) {
            QuakeEntry tempQe = findSmallestMgn(copy);
            copy.remove(tempQe);
            sorted.add(tempQe);
        }
        return sorted;
    }
    
    public ArrayList<QuakeEntry> sortByMagn(ArrayList<QuakeEntry> quakeData) {
        
        for ( int i = 0; i < quakeData.size(); i++) {
            int minInd = getSmallestMgnInd(quakeData,i); 
            QuakeEntry tempQe = quakeData.get(i);
            quakeData.set(i,quakeData.get(minInd));
            quakeData.set(minInd,tempQe);
        }
        
        return quakeData;
    }
    
    
    
    public QuakeEntry findSmallestMgn(ArrayList<QuakeEntry> quakeData) {
        double smallestSoFar = Double.MAX_VALUE;
        QuakeEntry smallest = null;
        for ( QuakeEntry qe: quakeData ) {
            double mgn = qe.getMagnitude();
            if ( smallestSoFar > mgn ) {
                smallestSoFar = mgn;
                smallest = qe;
            } 
        }
        return smallest;
    }
    
    public int getSmallestMgnInd(ArrayList<QuakeEntry> quakeData, int from) {
        double smallestSoFar = Double.MAX_VALUE;
        int minInd = from;
        for ( int i = from; i < quakeData.size(); i++) {
            double mgn = quakeData.get(i).getMagnitude();
            if ( smallestSoFar > mgn ) {
                smallestSoFar = mgn;
                minInd = i;
            } 
        }
        return minInd;
    }
    
    public void testSorting() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");  
        
        
        
        list = sortByMagn(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
    }

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        Location tokyo = new Location(35.42, 139.43);
        
        //4 between 5 and 6
        MatchAllFilter maf = new MatchAllFilter();
        //maf.addFilter(new MagnitudeFilter(0.0,2.0));
        maf.addFilter(new DistanceFilter(tokyo,10000));
        //maf.addFilter(new DepthFilter(-100000.0,-10000.0));
        maf.addFilter(new PhraseFilter("Japan","any")); 
        ArrayList<QuakeEntry> filteredByAll  = filter(list, maf); 
        System.out.println("Filtered down to: " + filteredByAll.size() + " records.");

        
        for (QuakeEntry qe: filteredByAll) { 
            System.out.println(qe);
        } 
    }
    
    public void testMatchAllFilter2() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        
        Location tulsa = new Location(36.1314, -95.9372);
        Location denver = new Location(39.7392, -104.9903);
        Location billund = new Location(55.7308, 9.1153);
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0,5.0));
        //maf.addFilter(new DepthFilter(-180000.0,-30000.0));
        maf.addFilter(new DistanceFilter(billund,3000));
        maf.addFilter(new PhraseFilter("e","any")); 
        ArrayList<QuakeEntry> filteredByAll  = filter(list, maf); 
        System.out.println(maf.getName());
        System.out.println("Filtered down to: " + filteredByAll.size() + " records.");


        
        //for (QuakeEntry qe: filteredByAll) { 
        //    System.out.println(qe);
        //} 
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
