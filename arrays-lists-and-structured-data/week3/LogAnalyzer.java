
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    
    
     
     
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
        
    }
     
    public int countUniqueIPs() {
        ArrayList<String> tempList = new ArrayList<String>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress(); 
            if ( !tempList.contains(ip) ) {
                tempList.add(ip);
            }          
        }
        return tempList.size();
    }
     
    public void printAllHigherThanNum(int num) {
    //In the LogAnalyzer class, write the void method printAllHigherThanNum that has one 
    //integer parameter num. This method should examine all the web log entries in records
    //and print those LogEntrys that have a status code greater than num. Be sure to add  
    //code in the Tester class to test out this method with the file short-test_log.   
        for (LogEntry le : records) {
            int sCode = le.getStatusCode();
            if ( sCode > num ) {
             System.out.println(le); 
            }
        }
    }
     
     
    public ArrayList<LogEntry> uniqueIPVisitsOnDay(String date) {
        //In the LogAnalyzer class, write the method uniqueIPVisitsOnDay that has one String parameter named someday in the format “MMM DD” where MMM is the first three characters of the 
        //month name with the first letter capitalized and the others in lowercase, and DD is the day in two digits (examples are “Dec 05” and “Apr 22”). This method accesses the web logs in 
        //records and returns an ArrayList of Strings of unique IP addresses that had access on the given day. (Note that the dates in LogEntrys are stored as a Date object, but using 
        //toString will allow you to access the characters in the Date. For example, consider that d is a Date. String str = d.toString(); allows you to now use a String representation of the 
        //date.) Be sure to test your program with code in the Tester class.  Using the file weblog-short_log you should see that the call to uniqueIPVisitsOnDay(“Sep 14”) returns an 
        //ArrayList of 2 items and uniqueIPVisitsOnDay(“Sep 30”) returns an ArrayList of 3 items.     
        
        //first I create the ArrayList of LogEntry I want to send back
        ArrayList<LogEntry> tempList = new ArrayList<LogEntry>();
        ArrayList<String> tempListIp = new ArrayList<String>();
        //I go trough all LogEntrys created in records
        for (LogEntry le : records) {
            //the date originally is in format: "Wed Sep 30 13:47:11 CEST 2015". It is easier to convert this to MMM dd then to manipulate the strings. Creating SimpleDateFormat first
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
            //uses the SimpleDateFormat to convert the date
            String formattedDate = sdf.format(le.getAccessTime());
            //convert it to String so I can compare it with 
            String stringDate = formattedDate.toString();
             
            //System.out.println("date: " + date + " formattedDate: " + formattedDate);
             
             //using equals as I need them to be logically equal
            if ( date.equals(formattedDate) ) {
                //need the ip as we only want to add records with unique ip
                String ip = le.getIpAddress(); 
                //check if ip is already added
                if ( !tempListIp.contains(ip) ) {
                    //if ip is not added yet, add ip to ip list
                    tempListIp.add(ip);
                    //if ip is not added yet, add record to record list
                    tempList.add(le);
                }    
            }  
        }
        return tempList;
    }
     
    public int countUniqueIPsInRange(int low, int high) {
        /*
        In the LogAnalyzer class, write the method countUniqueIPsInRange that has two integer parameters named low and high. This method returns the number of unique IP addresses in records 
        that have a status code in the range from low to high, inclusive. Be sure to test your program on several ranges. For example, using the file short-test_log, the call 
        countUniqueIPsInRange(200,299) returns 4, as there are four unique IP addresses that have a status code from 200 to 299. The call countUniqueIPsInRange(300,399) returns 2. In this case, 
        note that there are three entries in the file that have a status code in the 300 range, but two of them have the same IP address.
        */
        //declaring an a list for ip-s to help with uniqueness       
        ArrayList<String> tempListIp = new ArrayList<String>();
        //go through all records and filter based on the status code if they are within range 
        for (LogEntry le : records) {
             int sCode = le.getStatusCode();
             //need the ip as we only want to add records with unique ip
             String ip = le.getIpAddress();
             //first checking if status code is within range
             if ( sCode >= low && sCode <= high ) {
                 //checking if IP is already added or not
                 if ( !tempListIp.contains(ip) ) {
                     //if IP is not in list already, adding it
                     tempListIp.add(ip);  
                 }
             }
        }
        //returns the number of unique ips
        return tempListIp.size();    
    }
     
    public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         WebLogParser wlp = new WebLogParser();
         for ( String line : fr.lines() ) {
             records.add(wlp.parseEntry(line));
         }
    }
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> map){
        /*
         * In the LogAnalyzer class, write the method mostNumberVisitsByIP, which has one parameter, a HashMap<String, Integer> that maps an IP address to the number of times that IP address 
         * appears in the web log file. This method returns the maximum number of visits to this website by a single IP address. For example, the call mostNumberVisitsByIP on a HashMap formed 
         * using the file weblog3-short_log returns 3.
         */
        
        return Collections.max(map.values());
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
        /*
         * In the LogAnalyzer class, write the method iPsMostVisits, which has one parameter, a HashMap<String, Integer> that maps an IP address to the number of times that IP address appears in 
         * the web log file. This method returns an ArrayList of Strings of IP addresses that all have the maximum number of visits to this website. For example, the call iPsMostVisits on a 
         * HashMap formed using the file weblog3-short_log returns the ArrayList with these two IP addresses,  61.15.121.171 and  84.133.195.161. Both of them visited the site three times, 
         * which is the maximum number of times any IP address visited the site. 
         */   
        ArrayList<String> tempList = new ArrayList<String>();
        int maxVisits = mostNumberVisitsByIP(map);
        for ( String s : map.keySet() ) {
            if ( map.get(s) == maxVisits ) {
                tempList.add(s);
            }
        }
        return tempList;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays() {
        /*
         * In the LogAnalyzer class, write the method iPsForDays, which has no parameters. This method returns a HashMap<String, ArrayList<String>> that uses records and maps days from web logs 
         * to an ArrayList of IP addresses that occurred on that day (including repeated IP addresses). A day is in the format “MMM DD” where MMM is the first three characters of the month name
         * with the first letter capital and the others in lowercase, and DD is the day in two digits (examples are “Dec 05” and “Apr 22”). For example, for the file weblog3-short_log, after 
         * building this HashMap, if you print it out, you will see that Sep 14 maps to one IP address, Sep 21 maps to four IP addresses, and Sep 30 maps to five IP addresses.
         */ 
        HashMap<String, ArrayList<String>> tempMap = new HashMap<String, ArrayList<String>>();

        //I go trough all LogEntrys created in records
        for (LogEntry le : records) {
            //the date originally is in format: "Wed Sep 30 13:47:11 CEST 2015". It is easier to convert this to MMM dd then to manipulate the strings. Creating SimpleDateFormat first
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
            //uses the SimpleDateFormat to convert the date
            String formattedDate = sdf.format(le.getAccessTime());
            //convert it to String so I can compare it with 
            String stringDate = formattedDate.toString();
            String ip = le.getIpAddress();
            
            if (! tempMap.containsKey(stringDate) ) {
                ArrayList<String> tempListIp = new ArrayList<String>();
                tempListIp.add(ip);
                tempMap.put(stringDate,tempListIp);
            }
            else {
                tempMap.get(stringDate).add(ip);
            }

        }
        return tempMap; //form is something like {Sep 30 = ip1, ip2, ip3}, {Sep 14 = ip4,ip5,ip5};
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
        /*
         * In the LogAnalyzer class, write the method dayWithMostIPVisits, which has one parameter that is a HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an
         * ArrayList of IP addresses that occurred on that day. This method returns the day that has the most IP address visits. If there is a tie, then return any such day. For example, if you
         * use the file weblog3-short_log, then this method should return the day most visited as Sep 30.
         */    
        int maxSize = 0;
        String dateWithMaxSize = null;
        for ( String s : map.keySet() ) {
            ArrayList<String> tempList = map.get(s);
            if ( tempList.size() > maxSize ) {
                maxSize = tempList.size();
                dateWithMaxSize = s;
            }
        }
        return dateWithMaxSize;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String date) {
        /*
         * In the LogAnalyzer class, write the method iPsWithMostVisitsOnDay, which has two parameters—the first one is a HashMap<String, ArrayList<String>> that uses records and maps days 
         * from web logs to an ArrayList of IP addresses that occurred on that day, and the second parameter is a String representing a day in the format “MMM DD” described above. This method 
         * returns an ArrayList<String> of IP addresses that had the most accesses on the given day. For example, if you use the file weblog3-short_log, and the parameter for the 
         * day is “Sep 30”, then there are two IP addresses in the ArrayList returned: 61.15.121.171 and 177.4.40.87. Hint: This method should call another method you have written.
         * I send map and date and gives me back the IP which visited the most on that day.
         * use this somehow: public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
         */

        ArrayList<String> tempList = map.get(date);
        ArrayList<String> tempListIp = new ArrayList<String>();
        
        
        HashMap<String,Integer> tempMap = new HashMap<String,Integer>();
        int maxSize = 0;
        
        
        for ( int i = 0; i < tempList.size(); i++ ) {
            String ip = tempList.get(i);
            if ( !tempMap.keySet().contains(ip) ) {
                tempMap.put(ip,1);
            }
            else {
                tempMap.put(ip,tempMap.get(ip)+1);
            }
        }
        tempListIp = iPsMostVisits(tempMap);

        return tempListIp;
    }
    
    
    
    
    public HashMap<String,Integer> countVisitsPerIP() {
        HashMap<String,Integer> visits = new HashMap<String,Integer>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();   
            if ( visits.containsKey(ip) ) {
                visits.put(ip,visits.get(ip)+1);        
            }
            else {
                visits.put(ip,1); 
            }
        }
        return visits;
    }
        
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
     
     
}
