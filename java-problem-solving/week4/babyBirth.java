
/**
 * Write a description of part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class babyBirth {
    
    public void totalBirth() {
        FileResource fr = new FileResource("us_babynames_by_year/yob1905.csv");   
        int totalBirth = 0;
        int numberOfGirls = 0;
        int numberOfBoys = 0;
        for ( CSVRecord currentRow : fr.getCSVParser(false) ) {
            int currentBirth = Integer.parseInt(currentRow.get(2));
            totalBirth += currentBirth;
            if ( currentRow.get(1).equals("F") ) {
                numberOfGirls += currentBirth;
            }
            if ( currentRow.get(1).equals("M") ) {
                numberOfBoys += currentBirth;
            }
        }
        System.out.println("totalBirth: " + totalBirth + " numberOfGirls: " + numberOfGirls + " numberOfBoys " + numberOfBoys );
    }
    
    public int getRank (int year, String gender, String name) {
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        int rank = 1;
        boolean found = false;
        for ( CSVRecord currentRow : fr.getCSVParser(false) ) {
            if ( currentRow.get(1).equals(gender) ) {
                if ( currentRow.get(0).equals(name) ) {
                    found = true;
                    break;
                }
                else {
                    rank += +1;   
                }
                
            }
        }
        if ( !found ) {
            rank = -1;    
        }
        return rank;
    }
    public void testGetRank() {
        //For example, in the file "yob2012short.csv", given the name Mason, the year 2012 and the gender ‘M’, 
        //the number returned is 2, as Mason is the boys name with the second highest number of births. 
        //Given the name Mason, the year 2012 and the gender ‘F’, the number returned is -1 as Mason does n
        System.out.println("Mason in 2012, should be 2. As per getRank, it is: " + getRank(2012,"M","Mason"));
        System.out.println("Jason in 2012, should be -1. As per getRank, it is: " + getRank(2012,"M","Jason"));
        System.out.println("Sophia in 2012, should be 1. As per getRank, it is: " + getRank(2012,"F","Sophia"));
        System.out.println("Emily in 1960, as per getRank, it is: " + getRank(1971,"M","Frank"));
    }
    
    public String getName(int year, int rank, String gender) {
        //Write the method named getName that has three parameters: an integer named year, 
        //an integer named rank, and a string named gender (F for female and M for male). 
        //This method returns the name of the person in the file at this rank, for the given gender, 
        //where rank 1 is the name with the largest number of births. 
        //If the rank does not exist in the file, then “NO NAME”  is returned.
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        int currentRank = 1;
        boolean found = false;
        String foundName = "NO NAME";
        
        for ( CSVRecord currentRow : fr.getCSVParser(false) ) {
            if ( currentRow.get(1).equals(gender) ) {
                if ( currentRank == rank ) {
                    foundName = currentRow.get(0);
                    break;
                }
                else {
                    currentRank += +1;
                }
            }
        }      
        return foundName;
    }
    
    public void testGetName() {
        System.out.println("Rank 1 in 2012 for female should be Sophia. As per getRank, it is: " + getName(2012,1,"F"));
        System.out.println("Rank 1 in 2012 for male should be Jacob. As per getRank, it is: " + getName(2012,1,"M"));
        System.out.println("Rank 5 in 2012 for male should be William. As per getRank, it is: " + getName(2012,5,"M"));
        System.out.println("Rank 6 in 2012 for male should be No name. As per getRank, it is: " + getName(2012,6,"M"));
        System.out.println("Rank 350 from Girls in 1980: " + getName(1980,350,"F"));
        System.out.println("Rank 450 from Boys in 1982: " + getName(1982,450,"M"));
    }
    
    public String whatIsNameInYear (String name, int year, int newYear, String gender) {
        //This method determines what name would have been named if they were born in a different year, 
        //based on the same popularity. That is, you should determine the rank of name in the year they were born, 
        //and then print the name born in newYear that is at the same rank and same gender. For example, using the files 
        //"yob2012short.csv" and "yob2014short.csv", notice that in 2012 Isabella is the third most popular girl's name. 
        //If Isabella was born in 2014 instead, she would have been named Sophia, the third most popular girl's name 
        //that year. The output might look like this: "Isabella born in 2012 would be Sophia if she was born in 2014."
        
        int originalRank = getRank(year,gender,name);
        String newName = getName(newYear,originalRank,gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
        
        return "";
    }
    
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Owen",1974,2014,"M");   
    }
    
    public  int yearOfHighestRank(String name, String gender) {
        //Write the method yearOfHighestRank that has two parameters: a string name, and a string named gender 
        //(F for female and M for male). This method selects a range of files to process and returns an integer, 
        //the year with the highest rank for the name and gender. If the name and gender are not in any of the selected files,
        //it should return -1. For example, calling yearOfHighestRank with name Mason and gender ‘M’ and selecting
        //the three test files above results in returning the year 2012. That is because Mason was ranked the  
        //2nd most popular name in 2012, ranked 4th in 2013 and ranked 3rd in 2014. His highest ranking was in 2012.
        int highestRank = -1;
        int yearOfHighestRank = -1;
        
        for ( int year=1880; year<=2014; year++ ) {
            int currentRank = getRank(year,gender,name);
            //System.out.println(year + " " + gender + " " + name + " " + currentRank);
            if ( currentRank == -1 ) {
                continue;
            }
            if ( highestRank == -1 ) {
                highestRank = currentRank;    
            }
            if ( currentRank < highestRank ) {
                    highestRank = currentRank;
                    yearOfHighestRank = year;
            }
        }
        return yearOfHighestRank;
    }
    public void testYearOfHighestRank() {
        System.out.println("Olivia's was highest 2 in 2014. As per function it is: " + yearOfHighestRank("Olivia","F") ) ;
        System.out.println("Ava's was highest 5 in 2012. As per function it is: " + yearOfHighestRank("Ava","F") ) ;
        System.out.println("Emma's was highest 1 in 2014. As per function it is: " + yearOfHighestRank("Emma","F") ) ;
        System.out.println("Judlo is not in the list. As per function it is: " + yearOfHighestRank("Judlo","F") ) ;    
        System.out.println("Genevieve in all data files in girls: " + yearOfHighestRank("Genevieve","F"));
        System.out.println("Mich in all data files in girls: " + yearOfHighestRank("Mich","M"));

    }
    
    public double getAverageRank(String name, String gender) {
        //Write the method getAverageRank that has two parameters: a string name, and a string named gender 
        //(F for female and M for male). This method selects a range of files to process and returns a double representing
        //the average rank of the name and gender over the selected files. It should return -1.0 if the name is not ranked 
        //in any of the selected files. For example calling getAverageRank with name Mason and gender ‘M’ and selecting the 
        //three test files above results in returning 3.0, as he is rank 2 in the year 2012, rank 4 in 2013 and rank 3 in 2014. 
        //As another example, calling   getAverageRank with name Jacob and gender ‘M’ and selecting the three test files above 
        //results in returning 2.66.
        int sumSoFar = 0;
        int nrOfResults = 0;
        double AVGRanking = -1.0;
        DirectoryResource dr = new DirectoryResource();   
        for ( File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currentRank = 1;
            boolean found = false;
            for ( CSVRecord currentRow : fr.getCSVParser(false) ) {
                if ( currentRow.get(1).equals(gender) ) {
                    if ( currentRow.get(0).equals(name) ) {
                        //System.out.println("currentRow.get(0): " + currentRow.get(0) + " name: " + name);
                        found = true;
                        break;
                    }
                    else {
                        currentRank += +1;
                    }
                }
            }
            //System.out.println("found : " + found + " sumSoFar: " + sumSoFar + " nrOfResults: " + nrOfResults );
            if ( found ) {
                sumSoFar += currentRank;
                nrOfResults += +1;
            }
            
        }
        if ( sumSoFar != 0 && nrOfResults != 0 ) {
            AVGRanking = (double) sumSoFar / nrOfResults;      
        }
        return AVGRanking;
    }
    
    public void testGetAverageRank() {
        //For example calling getAverageRank with name Mason and gender ‘M’ and selecting the 
        //three test files above results in returning 3.0, as he is rank 2 in the year 2012, rank 4 in 2013 and rank 3 in 2014. 
        //As another example, calling   getAverageRank with name Jacob and gender ‘M’ and selecting the three test files above 
        //results in returning 2.66.    
        System.out.println("Mason with M and 3 files selected should 3.0 - With function it is: " + getAverageRank("Mason","M") );
        System.out.println("Jacob with M and 3 files selected should 2.66 - With function it is: " + getAverageRank("Jacob","M") );
        System.out.println("Susan - in girls With function it is: " + getAverageRank("Susan","F") );
        System.out.println("Robert - in boys With function it is: " + getAverageRank("Robert","M") );

    }
    
    public int getTotalBirthsRankedHigher (int year, String name, String gender) {
        //Write the method getTotalBirthsRankedHigher that has three parameters: an integer named year, a string named name, 
        //and a string named gender (F for female and M for male). This method returns an integer, the total number of births 
        //of those names with the same gender and same year who are ranked higher than name. For example, 
        //if getTotalBirthsRankedHigher accesses the "yob2012short.csv" file with name set to “Ethan”, gender set to “M”, 
        //and year set to 2012, then this method should return 15, since Jacob has 8 births and Mason has 7 births, 
        //and those are the only two ranked higher than Ethan. 
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        int sumBirth = 0;
        for ( CSVRecord currentRow : fr.getCSVParser(false) ) {
            if ( currentRow.get(1).equals(gender) ) {
                if ( currentRow.get(0).equals(name) ) {
                    break;    
                }
                else {
                    sumBirth += Integer.parseInt(currentRow.get(2));
                }
            }
            
        }
        return sumBirth;
    }
    
    public void testGetTotalBirthsRankedHigher() {
        System.out.println("In 2012, Ethan and M should be 15. As per the function, it is: " + getTotalBirthsRankedHigher(2012,"Ethan","M"));
        System.out.println("Emily in girls in 1990: " + getTotalBirthsRankedHigher(1990,"Emily","F"));
        System.out.println("Drew in boys in 1990: " + getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
}
