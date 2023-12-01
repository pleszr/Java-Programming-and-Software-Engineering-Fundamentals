
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CodonCount {
    
    private HashMap<String,Integer> codonMap = new HashMap<String,Integer>();

    private void buildCodonMap(int start, String dna){
        //CGTTCAAGTTCAA
        codonMap.clear();
        int index = start;
        while ( index + 3 < dna.length() ) { 
            String currentDna = dna.substring(index,index+3);
            if ( codonMap.containsKey(currentDna) ) {
                codonMap.put(currentDna,codonMap.get(currentDna)+1);
            }
            else {
                codonMap.put(currentDna,1);    
            }
            index = index + 3;
                      
        }
    }
    
    private String getMostCommonCodon(){
        return Collections.max(codonMap.entrySet(), Map.Entry.comparingByValue()).getKey();    
    }
    
    private void printCodonCounts(int start, int end) {
        //Write a void method named printCodonCounts that has two int parameters, start and end. This method prints all the 
        //codons in the HashMap along with their counts if their count is between start and end, inclusive.
        
        for (String key : codonMap.keySet() ) {
            if (codonMap.get(key) >= start && codonMap.get(key) <= end) {
                System.out.println(key + ": " + codonMap.get(key));
            }
        }
        
    }
    

    


    
    public void testCodonMap() {
        //Write a tester method that prompts the user for a file that contains a DNA strand (could be upper or lower case 
        //letters in the file, convert them all to uppercase, since case should not matter). Then for each of the three possible 
        //reading frames, this method builds a HashMap of codons to their number of occurrences in the DNA strand, prints the 
        //total number of unique codons in the reading frame, prints the most common codon and its count, and prints the codons 
        //and their number of occurrences for those codons whose number of occurrences in this reading frame are between two 
        //numbers inclusive.
        FileResource fr = new FileResource("data/dnaMystery2.txt");
        String dna = fr.asString().toUpperCase().trim();
        
        for ( int frameNr = 0; frameNr <= 2; frameNr++ ) {
            //builds a HashMap of codons to their number of occurrences in the DNA strand
            codonMap.clear();
            buildCodonMap(frameNr,dna);
            
            //prints the total number of unique codons in the reading frame
            System.out.println("Reading frame starting with " + frameNr + ", number of unique codons: " + codonMap.size());
            
            //prints the most common codon and its count
            String mstCmnCdn = getMostCommonCodon();
            System.out.println("Most common codon is: " + mstCmnCdn + ": " + codonMap.get(mstCmnCdn));
            
            //prints the codons and their number of occurrences for those codons whose number of occurrences in this reading
            //frame are between two numbers inclusive
            int start = 0;
            int end = 15;
            
            System.out.println("Codons between " + start + " and " + end + ":");
            printCodonCounts(start,end);   
        }
        
        
        
        
    }

}
