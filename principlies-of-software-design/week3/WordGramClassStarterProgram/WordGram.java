import java.util.Arrays;
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for ( int i=0; i<myWords.length; i++ ) {
            sb.append(myWords[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }
    
    public int hashCode() {
        String arrayAsString = Arrays.toString(myWords);   
        int hashCode = arrayAsString.hashCode();
        return hashCode;
    }
    
    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if ( other.length() != myWords.length) {
            return false;
        }
        
        for ( int i=0; i<myWords.length; i++ ) {
            if (!myWords[i].equals(other.wordAt(i))) {
                return false;    
            }
        } 
        return true;
    }
    




    public WordGram shiftAdd(String word) {    
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        String[] tempArr = Arrays.copyOf(myWords, myWords.length);
        for ( int i=0; i<tempArr.length-1; i++ ) {
            //System.out.println(tempArr[i] + " + " + tempArr[i+1]);
            tempArr[i] = tempArr[i+1]; 
        }
        tempArr[tempArr.length-1] = word;
        WordGram out = new WordGram(tempArr, 0, tempArr.length);
        return out;
    }
    
    

}