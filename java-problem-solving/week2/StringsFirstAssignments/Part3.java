
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {

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
    

    
    
    public static void main (String[] args) {
        Part3 p3 = new Part3();
        //pr.testPerimeter();
        //pr.testEverything();
        //p3.testSimpleGene();
        p3.testLastPart();
    }
    
    
}
