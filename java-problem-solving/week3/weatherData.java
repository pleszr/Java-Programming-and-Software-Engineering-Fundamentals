
/**
 * Write a description of part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class weatherData {
    public static void main (String[] args) {
        weatherData p1 = new weatherData();
        FileResource fr = new FileResource("data/2013/weather-2013-09-02.csv");
        CSVParser parser = fr.getCSVParser();
        
        //p1.countryInfo(parser,"Nauru");
        //p1.listExportersTwoProducts(parser,"fish","nuts");
        //System.out.println("Number of countries exporting sugar: " + p1.numberOfExporters(parser,"sugar") );
        //p1.bigExporters(parser,"$999,999,999,999");
        //p1.whoExportsCoffee();
        //CSVRecord record = p1.hottestInDay(parser);
        //System.out.println( "Highest temp was: " + record.get("TemperatureF") + " and was recorded at: " + record.get("TimeEST") );
        //p1.hottestInManyDays();
        //CSVRecord record = p1.coldestInDay(parser);
        //System.out.println( "Lowest temp was: " + record.get("TemperatureF") + " and was recorded at: " + record.get("DateUTC") );
        p1.coldestInManyDays();
        //CSVRecord record = p1.lowestHumidityInFile(parser);
        //System.out.println( "Lowest humidity was " + record.get("Humidity") + " and was recorded at: " + record.get("DateUTC") );
        //p1.lowestHumidityInManyFiles(); //averageInFIle
        //System.out.println("Average temperature in file is: " + p1.averageInFIle(parser,"TemperatureF"));
        //System.out.println("Average Temp when high Humidity is " + p1.averageTemperatureWithHighHumidityInFile(parser,80));
    }
    
    
    
     
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
    //this function gives back the record of the highest temperature
    //first we initialize the value as null
    CSVRecord smallestSoFar = null;    
    //we iterate trough the csv file
    for ( CSVRecord currentRecord: parser ) {
        //if this is the first record we just update highestTempRecord
        if ( currentRecord.get("Humidity").equals("N/A") ) {
            continue;
        }
        smallestSoFar = getSmallestOfTwo(currentRecord,smallestSoFar,"Humidity");
                                
    }
    return smallestSoFar;
    }
    
    public CSVRecord getLargestOfTwo(CSVRecord currentRecord, CSVRecord largestSoFar,String columnHeader) {
        
        if ( largestSoFar == null ) {
            largestSoFar = currentRecord;
        }
        else {
            double currentTempDouble = Double.parseDouble(currentRecord.get(columnHeader));
            double highestTempDouble = Double.parseDouble(largestSoFar.get(columnHeader));
            if ( currentTempDouble > highestTempDouble ) {
            largestSoFar = currentRecord;      
            }      
        }
       return largestSoFar;
    }
    
    public CSVRecord getSmallestOfTwo(CSVRecord currentRecord, CSVRecord smallestSoFar,String columnHeader) {
        
        if ( smallestSoFar == null ) {
            smallestSoFar = currentRecord;
        }
        else {
            double currentTempDouble = Double.parseDouble(currentRecord.get(columnHeader));
            double smallestTempDouble = Double.parseDouble(smallestSoFar.get(columnHeader));
            //System.out.println("currentTempDouble: " + currentTempDouble + " smallestTempDouble: " + smallestTempDouble);
            if ( currentTempDouble < smallestTempDouble && currentTempDouble != -9999 ) {
            smallestSoFar = currentRecord;      
            }      
        }
       return smallestSoFar;
    }
    
    public void hottestInManyDays() {
        DirectoryResource dr = new DirectoryResource();
        
        CSVRecord largestSoFar = null;
        for ( File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRecord = hottestInDay(parser);
            largestSoFar = getLargestOfTwo(currentRecord,largestSoFar,"TemperatureF");          
              
        }
        System.out.println("Hottest temp was " + largestSoFar.get("TemperatureF") + " at " + largestSoFar.get("DateUTC") + "." ) ;
        
    }
    
    public void printAllTemp(CSVParser parser) {
        int test = 0;
        for ( CSVRecord currentRow : parser ) {
            test = test + 1;
            System.out.println(currentRow.get("DateUTC") +": " + currentRow.get("TemperatureF") );
        }
    }
    
    public void coldestInManyDays() {
        DirectoryResource dr = new DirectoryResource();
        File lowestFile = null;
        CSVRecord coldestSoFar = null;
        CSVParser coldestSoFarCSV = null;
        for ( File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRecord = coldestInDay(parser);
            coldestSoFar = getSmallestOfTwo(currentRecord,coldestSoFar,"TemperatureF");    
            if ( coldestSoFar == currentRecord ) {
                lowestFile = f;
            }
              
        }
        String lowestFileString = lowestFile + "";
        
        FileResource fr2 = new FileResource(lowestFile);
        coldestSoFarCSV = fr2.getCSVParser();
        
        System.out.println("Coldest day was in file " + lowestFileString.substring(lowestFileString.lastIndexOf("/")+1));
        System.out.println("Coldest temperature on that day was: " + coldestSoFar.get("TemperatureF"));
        printAllTemp(coldestSoFarCSV);
        
    }
    
    public void lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestSoFar = null;
        for ( File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRecord = lowestHumidityInFile(parser);
            coldestSoFar = getSmallestOfTwo(currentRecord,coldestSoFar,"Humidity");    
        }
        System.out.println("Lowest humidity was " + coldestSoFar.get("Humidity") + " at " + coldestSoFar.get("DateUTC") );
        
    }
    
    
    public CSVRecord hottestInDay(CSVParser parser) {
        //this function gives back the record of the highest temperature
        //first we initialize the value as null
        CSVRecord largestSoFar = null;    
        //we iterate trough the csv file
        for ( CSVRecord currentRecord: parser ) {
            //if this is the first record we just update highestTempRecord
            if ( largestSoFar == null ) {
                largestSoFar = currentRecord;    
            }
            //we convert the strings from the records to double to be able to convert which is higher
            //the two doubles are not required, just made it like this for easier readability
            else {
                largestSoFar = getLargestOfTwo(currentRecord,largestSoFar,"TemperatureF");
            }                        
        }
        return largestSoFar;
    }
    
    public CSVRecord coldestInDay(CSVParser parser) {
    //this function gives back the record of the highest temperature
    //first we initialize the value as null
    CSVRecord smallestSoFar = null;    
    //we iterate trough the csv file
    for ( CSVRecord currentRecord: parser ) {
        //if this is the first record we just update highestTempRecord
        smallestSoFar = getSmallestOfTwo(currentRecord,smallestSoFar,"TemperatureF");
                                
    }
    return smallestSoFar;
    } 
    
    public double averageInFIle(CSVParser parser,String columnHeader) {
    double sumSoFar = 0.0;
    int recordCounter = 0;
    //we iterate trough the csv file
    for ( CSVRecord currentRecord: parser ) {
        if (currentRecord.get(columnHeader).contains("N/A") || currentRecord.get(columnHeader).contains("-9999") ) {
            continue;
        }
        double currentValue = Double.parseDouble(currentRecord.get(columnHeader));   
        sumSoFar = sumSoFar + currentValue;
        recordCounter = recordCounter + 1;
                                
    }
    return sumSoFar / recordCounter;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int humidLimit) {
    double sumSoFar = 0.0;
    int recordCounter = 0;
    //we iterate trough the csv file
    for ( CSVRecord currentRecord: parser ) {
        if (currentRecord.get("Humidity").contains("N/A") || currentRecord.get("TemperatureF").contains("-9999") ) {
            continue;
        }
        double currentHumid = Double.parseDouble(currentRecord.get("Humidity"));
        double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
        if ( currentHumid >= humidLimit ) {
            sumSoFar = sumSoFar + currentTemp;
            recordCounter = recordCounter + 1;
        }                                      
    }
    return sumSoFar / recordCounter;
    }
    
}
