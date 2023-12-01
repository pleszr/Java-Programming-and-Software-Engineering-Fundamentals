
/**
 * Write a description of part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class exportCountry {
    public static void main (String[] args) {
        exportCountry p1 = new exportCountry();
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //p1.countryInfo(parser,"Nauru");
        //p1.listExportersTwoProducts(parser,"cotton","flowers");
        //System.out.println("Number of countries exporting cocoa: " + p1.numberOfExporters(parser,"cocoa") );
        p1.bigExporters(parser,"$999,999,999,999");
        //p1.whoExportsCoffee();
        //
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        boolean recordFound = false;
        for ( CSVRecord record: parser ) {
            String dollarValue = record.get("Value (dollars)"); 
            String csvCountry = record.get("Country");
            if ( dollarValue.length() > amount.length() ) {
                System.out.println(csvCountry + " " + dollarValue);
                recordFound = true;
            }
        }
        if ( !recordFound ) {
            System.out.println("No country found with larger amount then " + amount);     
        }
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        boolean recordFound = false;
        for ( CSVRecord record: parser ) {
            String export = record.get("Exports");
            if ( export.contains(exportItem1) && export.contains(exportItem2) ) {
                String csvCountry = record.get("Country");
                System.out.println(csvCountry);
                recordFound = true;
            }
        }
        if ( !recordFound ) {
            System.out.println("No country found with both " + exportItem1 + " and " + exportItem2);     
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int exporterCounter = 0;
        for (CSVRecord record : parser ) {
            String export = record.get("Exports");
            if ( export.contains(exportItem) ) {
                exporterCounter = exporterCounter + 1;    
            }
        }
        return exporterCounter;
    }
    
    public void countryInfo(CSVParser parser, String country) {
        //look for data based on country.
        //example format: Germany: motor vehicles, machinery, chemicals: $1,547,000,000,000
        boolean countryFound = false;
        
        for ( CSVRecord record : parser ) {
            String csvCountry = record.get("Country");
            if ( csvCountry.equals(country) ) {
                String export = record.get("Exports");    
                String dollarValue = record.get("Value (dollars)");                
                System.out.println(country + ": " + export + ": " + dollarValue);
                countryFound = true;  
                break;
            }               
        }
        if ( ! countryFound  ) {
            System.out.println("Country not found");     
        }
    }
    
    public void whoExportsCoffee() {
        //FileResource fr = new FileResource("exportdata.csv");
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser,"coffee");    
    }
    
    public void listExporters(CSVParser parser, String exportOfInterest) {
        for (CSVRecord record : parser ) {
            String export = record.get("Exports");
            if ( export.contains(exportOfInterest) ) {
                String country = record.get("Country");
                System.out.println(country);
            }
            
        }
        
    }
    
}
