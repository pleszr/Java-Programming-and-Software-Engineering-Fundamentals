
/**
 * Duke learns to code String part1 assignment.
 * 
 * @author (Roland Plesz) 
 * @version (0.1)
 * 
 * Finds the index position of the start codon “ATG”. If there is no “ATG”, 
 * return the empty string.

Finds the index position of the first stop codon “TAA”
 appearing after the “ATG” that was found. If there is no such “TAA”, return the empty string. 

If the length of the substring between the “ATG” and “TAA” 
is a multiple of 3, then return the substring that starts with 
that “ATG” and ends with that “TAA”.
 */
public class Part1 {

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
        String testDnaStringNoAtg = "ATTGTRTYFTAATTFATA";
        System.out.println("Input DNA: " + testDnaStringNoAtg + ". Result: " + findSimpleGene(testDnaStringNoAtg) );
        
        String testDnaStringNoTaa = "ATGTRTYFTATATTATA";
        System.out.println("Input DNA: " + testDnaStringNoTaa + ". Result: " + findSimpleGene(testDnaStringNoTaa) );
        
        String testDnaStringNoTaaNoAtg = "ATSTRTYFTATATTATA";
        System.out.println("Input DNA: " + testDnaStringNoTaaNoAtg + ". Result: " + findSimpleGene(testDnaStringNoTaaNoAtg) );
        
        String testDnaStringNotDivededby3 = "ABATGBCBCBCCTAA";
        System.out.println("Input DNA: " + testDnaStringNotDivededby3 + ". Result: " + findSimpleGene(testDnaStringNotDivededby3) );
        
        String testDnaStringValidDna = "ATGABCDEFTAA";
        System.out.println("Input DNA: " + testDnaStringValidDna + ". Result: " + findSimpleGene(testDnaStringValidDna) );
        
        
        
    }
    
    public static void main (String[] args) {
        Part1 p1 = new Part1();
        //pr.testPerimeter();
        //pr.testEverything();
        p1.testSimpleGene();
        
    }
    
    
}

