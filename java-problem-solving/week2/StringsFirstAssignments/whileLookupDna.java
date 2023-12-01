
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class whileLookupDna {

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
    
    
    
    
    
    public int findStopCodon(String dna, int startIndex,String stopCodon) {
        int currentIndex = startIndex +1;
        while ( currentIndex != -1 ) {
            currentIndex = dna.indexOf(stopCodon,currentIndex);
            if ( (currentIndex - startIndex) % 3 == 0 ) {
                return currentIndex + stopCodon.length();
            }
            else {
                currentIndex = dna.indexOf(stopCodon,currentIndex +1 );
            }
        }        
        return dna.length();
    }
    

    
    public String whileLookupDna(String dna) {
    //ATG to TAA if distance % 3 = 0
    
    int firstIndex = dna.indexOf("ATG");
    if ( firstIndex == -1 ) {
        return "";
    }
    int currentIndex = firstIndex + 3;
    //System.out.println("firstIndex: " + firstIndex);
    while ( currentIndex != -1 ) {
        //System.out.println("currentIndex: " + currentIndex);
        currentIndex = dna.indexOf("TAA",currentIndex);
        if ( (currentIndex - firstIndex) % 3 == 0 ) {
            return dna.substring(firstIndex,currentIndex + 3);
        }
        else {
            //System.out.println("firstIndex: " + firstIndex);
            //System.out.println("currentIndex: " + currentIndex);
            //System.out.println( ((currentIndex - firstIndex) % 3) + " " + dna.substring(firstIndex,currentIndex));
            currentIndex = dna.indexOf("TAA",currentIndex+1);
        }
    }
    return "";    
    }
    
    
    public void testYoutubeLinks() {
        youtubeLinks("https://www.dukelearntoprogram.com/course2/data/manylinks.html","youtube.com");    
    }
    
    public void testWhileLookupDna() {
        //ATG to TAA if distance % 3 = 0
        System.out.println("------NEW RUN------");
        /*
        String noValidResponse = "AAAAATCBBBTTATATAATT";
        System.out.println("Should be empty: " + whileLookupDna(noValidResponse) + "+ findStopCodon test: " + findStopCodon(noValidResponse,0,"TAA"));
        String validResponseSimple = "ATGBBBCCCTAA";
        System.out.println("Should be ATGBBBCCCTAA: " + whileLookupDna(validResponseSimple) + "+ findStopCodon test: " + findStopCodon(validResponseSimple,0,"TAA" ));
        String validResponseReal = "BBBBATGBBBCCXCTAATAADDUDQWETAAQTAAQQTAAQQQTAATAACTAACCTAACCCTTAA";
        System.out.println("Should be valid: " + whileLookupDna(validResponseReal) + "+ findStopCodon test: " + findStopCodon(validResponseReal,4,"TAA" ) );
        */
        
       String multiCodonTest1 = "XXATGABCDFATAACCC";
       System.out.println(multiCodonTest1 + ": " + firstGeneMultipleStopCodons(multiCodonTest1));
       String multiCodonTest2 = "XATGABCDFATGABBB";
       System.out.println(multiCodonTest2 + ": " + firstGeneMultipleStopCodons(multiCodonTest2));
       
       String multiCodonTest3 = "XXXATGABCDFATAGBBB";
       System.out.println(multiCodonTest3 + ": " + firstGeneMultipleStopCodons(multiCodonTest3));
    
       String multiCodonTest4 = "XXXATGABCDFATDAAGBBB";
       System.out.println(multiCodonTest3 + ": " + firstGeneMultipleStopCodons(multiCodonTest3));
       
        

        
        
    
    }  
    
    public String firstGeneMultipleStopCodons(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1 ) {
            return "";
        }
        
        String currentString = dna.substring(startIndex);
        
        int taaIndex = findStopCodon(currentString,0,"TAA");
        int tagIndex = findStopCodon(currentString,0,"TAG");
        int tgaIndex = findStopCodon(currentString,0,"TGA");
        int tempIndex = Math.min(taaIndex,tagIndex);
        int minIndex = Math.min(tempIndex,tgaIndex);
        
        if ( minIndex == dna.length() ) {
            return "";
        };    
        return dna.substring(startIndex,minIndex+3);
    }

    
    
    public static void main (String[] args) {
        whileLookupDna mainThing = new whileLookupDna();
        //pr.testPerimeter();
        //pr.testEverything();
        //p3.testSimpleGene();
        //p4.testLastPart();
        //p5.testYoutubeLinks();
        mainThing.testWhileLookupDna();
    }
    
    
}
