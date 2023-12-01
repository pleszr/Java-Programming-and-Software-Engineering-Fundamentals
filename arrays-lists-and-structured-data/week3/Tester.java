
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    private void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        //la.printAll();
        System.out.println("Unique IPs: " + la.countUniqueIPs());
        int num = 400;
        System.out.println("Print higher then: " + num);
        la.printAllHigherThanNum(num);
        
        String uniqueIPDate = "Sep 24";
        
        ArrayList<LogEntry> uniqueIpList = la.uniqueIPVisitsOnDay(uniqueIPDate);
        System.out.println("uniqueIPVisitsOnDay on " + uniqueIPDate + ": (size is: " + uniqueIpList.size() );
        for ( LogEntry log : uniqueIpList ) {
            System.out.println(log.getAccessTime());
        }
        
        int low1 = 200;
        int high1 = 299;
        System.out.println("countUniqueIPsinRange between  " + low1 + " and " + high1 + ":" + la.countUniqueIPsInRange(low1,high1));
        
        int low2 = 300;
        int high2 = 399;
        System.out.println("countUniqueIPsinRange between  " + low2 + " and " + high2 + ":" + la.countUniqueIPsInRange(low2,high2));
        
        
        //test website visits
        HashMap<String,Integer> visits = la.countVisitsPerIP();
        
        for ( String s : visits.keySet() ) {
            System.out.println(s + ": " + visits.get(s));    
        }
        
        //test mostNumberVisitsByIP
        System.out.println("mostNumberVisitsByIP: " + la.mostNumberVisitsByIP(visits));
        
        //test iPsMostVisits
        System.out.println("Testing iPsMostVisits:");
        ArrayList<String> ipMostVisitsVisit =  la.iPsMostVisits(visits);
        
        for ( int i = 0; i<ipMostVisitsVisit.size(); i++ ) {
        System.out.println(ipMostVisitsVisit.get(i));
        }
        
        //Test iPsForDays
        System.out.println("--------------------");
        System.out.println("Testing iPsForDays:");
        HashMap<String,ArrayList<String>> ipPerDays = la.iPsForDays();
        for ( String s : ipPerDays.keySet() ) {
            System.out.println(s);
            ArrayList<String> tempList = ipPerDays.get(s);
            for ( int i=0; i<tempList.size(); i++ ) {
                System.out.println(tempList.get(i));
            }
        }
        
        //Test dayWithMostIPVisits
        System.out.println("--------------------");
        System.out.println("Testing dayWithMostIPVisits:");
        System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
        
        //Test iPsWithMostVisitsOnDay
        String testDate = "Sep 30";
        System.out.println("--------------------");
        System.out.println("Testing iPsWithMostVisitsOnDay with " + testDate);
        ArrayList<String> iPsWithMostVisitsOnDay = la.iPsWithMostVisitsOnDay(ipPerDays,testDate);
        for ( int i=0; i<iPsWithMostVisitsOnDay.size(); i++ ) {
                System.out.println(iPsWithMostVisitsOnDay.get(i));
        }
        
    
        
    }
}
