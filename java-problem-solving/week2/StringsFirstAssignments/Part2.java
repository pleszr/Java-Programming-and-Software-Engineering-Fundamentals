
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {

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
        
        
        
    }
    
    public static void main (String[] args) {
        Part2 p2 = new Part2();
        //pr.testPerimeter();
        //pr.testEverything();
        p2.testSimpleGene();
        
    }
    
    
}
