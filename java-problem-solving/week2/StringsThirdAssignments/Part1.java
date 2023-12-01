
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import edu.duke.*;
public class Part1 {
    
    
    

    public static void main (String[] args) {
        Part1 p1 = new Part1();
        StorageResource geneStore = new StorageResource();
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        p1.storeAllGenes(dna,geneStore);

        p1.processGenes(geneStore);
        
    }    

        public void testFindStopCodon() {
        String dna = "ATGTCGTGA"; //TGA index = 6
        System.out.println(findStopCodon(dna, 0, "TGA"));
        dna = "ATGTCGTGTAA"; //invalid. should return dna length = 11
        System.out.println(findStopCodon(dna, 0, "TAA"));
        dna = "ATGTCGTGCTAA"; //TAA index = 9
        System.out.println(findStopCodon(dna, 0, "TAA"));
    }
    
    public void printAllFromStorage(StorageResource storage) {
        //System.out.println(storage.size() );

        for ( String line : storage.data() ) {
            System.out.println(line);    
        }
        
    }
    
    public void processGenes(StorageResource storage) {
        //print all the Strings in sr that are longer than 9 characters
        System.out.println("Nr. of Genes: " + storage.size() );
        int longerThen60CharCounter = 0;
        for ( String line : storage.data() ) {
            if ( line.length() > 60 ) {
                System.out.println(line); 
                longerThen60CharCounter = longerThen60CharCounter + 1;
            }    
        }    
        
        //print the number of Strings in sr that are longer than 9 characters
        System.out.println("Number of Strings in sr that are longer then 60 characters: " + longerThen60CharCounter );
        
        //print the Strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("print the Strings in sr whose C-G-ratio is higher than 0.35:");
        int cgCounter = 0;
        for ( String line : storage.data() ) {
            if ( cgRatio(line) > 0.35 ) {
                System.out.println(line);  
                cgCounter = cgCounter + 1;

            }        
        }
        
        
        //print the number of strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("print the number of strings in sr whose C-G-ratio is higher than 0.35: " + cgCounter); 
        //print the length of the longest gene in sr   
        int longestGeneLength = 0;
        for ( String line : storage.data() ) {
            if ( line.length() > longestGeneLength  ) {
                longestGeneLength = line.length();
            }        
        }
        System.out.println("print the length of the longest gene in sr: " + longestGeneLength);
        

    
    }
    
    public double cgRatio(String dna) {
        double cgCounter = 0.0;
    
        
        for ( int index = 0; index < dna.length(); index++ ) {
            if ( dna.substring(index,index+1).equalsIgnoreCase("C") || dna.substring(index,index+1).equalsIgnoreCase("G") ) {
                cgCounter = cgCounter + 1;
            }
        }
        double cgRatio = cgCounter / dna.length();
        return cgRatio;
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
        return -1;
    }
    
    
    
    
    public String storeAllGenes(String dnaInput, StorageResource outputStorage) {
        //make DNA uppercase as later on we search for upper case TAA, TAG and TGA
        String dna = dnaInput.toUpperCase();
        
        //declare currentIndex as it needs to be used outside of the while 
        
        int minIndex = 0;
        int currentIndex = 0;
        
        //the while will go on until there TAA, TAG or TGA found in the string
        while ( true ) {
            
            //finds the first iteration. All genes start with "ATG". 
            //It starts the search at the index of last 
            //iteration (currentIndex) + at the relative location of the first StopCodon 
            //we don't need to add +3 because the findStopCodon function is written in a way that it already adds the length of the search to the minindex
            currentIndex = dna.indexOf("ATG",currentIndex+minIndex);
            
            //as we used true in the while we need an attempt to break. if there is no more gene, we break
            if ( currentIndex == -1 ) {
                break;
            }
            
            //we know that there is currentString, we created the new string to look at it
            String currentString = dna.substring(currentIndex);
            //testing only System.out.println("currentString: " + currentString);
            //we have 3 alternate endings, running the findStopCodon to find gene with all 3
            int taaIndex = findStopCodon(currentString,0,"TAA");
            int tagIndex = findStopCodon(currentString,0,"TAG");
            int tgaIndex = findStopCodon(currentString,0,"TGA");
            
            //logic the find the smallest gene && handling if it was not found (findStopCodon gives back -1 in that case)
            minIndex = Math.max(Math.max(taaIndex,tagIndex),tgaIndex);
            if ( taaIndex < minIndex && taaIndex >= 0 ) {
                minIndex = taaIndex;
            }
            if ( tagIndex < minIndex && tagIndex >= 0 ) {
                minIndex = tagIndex;
            }
            if ( tgaIndex < minIndex && tgaIndex >= 0 ) {
                minIndex = tgaIndex;
            }
            
            //testing System.out.println("currentIndex: " + currentIndex + " MinIndex: " + minIndex + " taaIndex: " + taaIndex + " tagIndex: " + tagIndex + " tgaIndex: " + tgaIndex);
            //if all 3 findStopCodon resulted with -1 (no gene found) then minIndex is -1 and we have nothing to print. Excluding that option
            if (minIndex != -1 ) {
                outputStorage.add(dna.substring(currentIndex,minIndex+currentIndex));  
            }                    
        }
        return "";       
    }
          
}
    
