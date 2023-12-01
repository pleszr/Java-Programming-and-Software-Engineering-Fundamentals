
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class Part4 {

    public String youtubeLinks(String url, String searchTerm) {
    URLResource inputFile = new URLResource(url);
    
    for (String line : inputFile.lines() ) {
        String lowerLine = line.toLowerCase();
        int yLoc = line.indexOf(searchTerm);
        if ( yLoc != -1 ) {
            //System.out.println(line);
            String lineBeforeY = lowerLine.substring(0,yLoc);
                //System.out.println("lineBeforeY: "+ lineBeforeY);
            int firstQuoteLoc = lineBeforeY.lastIndexOf("\"");
                //System.out.println("firstQuoteLoc: "+ firstQuoteLoc);
            String lineAfterY = lowerLine.substring(yLoc+11,line.length());
                //System.out.println("lineAfterY: "+ lineAfterY);
            int secondQuoteLoc = lineAfterY.indexOf("\"");
                //System.out.println("secondQuoteLoc: "+ secondQuoteLoc);
                
            System.out.println(line.substring(firstQuoteLoc+1, lineBeforeY.length() + searchTerm.length() + secondQuoteLoc));    
        }
            
    }
    return "";
    }
    
    
    
    
    public boolean isGeneInDna (String dna, int startChar, int endChar) {
        String currentString = dna.substring(startChar,endChar);
        int startDna = currentString.indexOf("ATG");
        if ( startDna == -1 ) {
            return false;
        }
        int endDna = currentString.indexOf("TAA",startDna);
        if ( endDna == -1 ) {
            return false;
        }
        String resultDna = currentString.substring(startDna,endDna);
        if ( resultDna.length() % 3 !=0 ) {
            return false;
        }
        else {
            return true;
        }

    }
    
    public String findSimpleGene (String dna) {
        int startDna = dna.indexOf("ATG");
        if ( startDna == -1 ) {
            return "a";
        }
        int endDna = dna.indexOf("TAA",startDna);
        if ( endDna == -1 ) {
            return "b";
        }
        String resultDna = dna.substring(startDna,endDna);
        if ( resultDna.length() % 3 !=0 ) {
            return "c";
        }
        else {
            return resultDna;
        }

    }
    
    public boolean twoOccurrences ( String searchString, String searchTerm ) {
    int firstOccurence = searchString.indexOf(searchTerm) + searchTerm.length();
    //System.out.println("firstOccurence: " + firstOccurence);
    if (firstOccurence != -1 ) {
        String currentString = searchString.substring(firstOccurence,searchString.length());
        //System.out.println("currentString: " + currentString);
        if (currentString.indexOf(searchTerm) != -1 ) {
            return true;
        }
    }
    return false;
    }
    
    
    
    public String lastPart ( String searchString, String searchTerm ) {
    /*
    Write the method named lastPart that has two String parameters named stringa and stringb. 
    This method finds the first occurrence of stringa in stringb, and returns the part of stringb that follows stringa
    */
    int firstOccurence = searchString.indexOf(searchTerm);
    if ( firstOccurence != -1 ) {
        String resultString = searchString.substring(firstOccurence + searchTerm.length(),searchString.length() );
        return resultString;
    }
    
           
    return "";
    }
    
    public void testSimpleGene() {
        System.out.println("-----NEW RUN-----");
        String testDnaStringNoAtg = "AAAAAAAAATTGTRTYFTAATTFATA";
        System.out.println("Input DNA: " + testDnaStringNoAtg + ". Result: " + isGeneInDna(testDnaStringNoAtg,0,12) );
        
        String testDnaStringNoTaa = "ATGTRTYFTATATTATA";
        System.out.println("Input DNA: " + testDnaStringNoTaa + ". Result: " + isGeneInDna(testDnaStringNoTaa,0,12) );
        
        String testDnaStringNoTaaNoAtg = "ATSTRTYFTATATTATA";
        System.out.println("Input DNA: " + testDnaStringNoTaaNoAtg + ". Result: " + isGeneInDna(testDnaStringNoTaaNoAtg,0,12) );
        
        String testDnaStringNotDivededby3 = "ABATGBCBCBCCTAA";
        System.out.println("Input DNA: " + testDnaStringNotDivededby3 + ". Result: " + isGeneInDna(testDnaStringNotDivededby3,0,12) );
        
        String testDnaStringValidDna = "ATGABCDEFTAA";
        System.out.println("Input DNA: " + testDnaStringValidDna + ". Result: " + isGeneInDna(testDnaStringValidDna,0,12) );
        
        System.out.println(twoOccurrences("AATGABCDEFTABAA","AA"));
        
    }
    
    public void testLastPart() {
    System.out.println("Should be empty: " + lastPart("AATGABCDEFTABAA","AAC"));
    System.out.println("Should be empty: " + lastPart("A","AAC"));
    System.out.println("Should be TGABCDEFTABAA: " + lastPart("AATGABCDEFTABAA","AA"));
    System.out.println("Should be BCDEFTABAA: " + lastPart("AATGABCDEFTABAA","TGA"));
    }
    
    public void testYoutubeLinks() {
        youtubeLinks("https://www.dukelearntoprogram.com/course2/data/manylinks.html","youtube.com");    
    }
    

    
    
    public static void main (String[] args) {
        Part4 p4 = new Part4();
        //pr.testPerimeter();
        //pr.testEverything();
        //p3.testSimpleGene();
        //p4.testLastPart();
        p4.testYoutubeLinks();
    }
    
    
}
