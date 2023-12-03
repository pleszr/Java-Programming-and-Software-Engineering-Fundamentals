
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    
    private String searchPhrase;
    private String searchLoc;

    
    
    public PhraseFilter(String phrase, String phraseLoc) {
        //phraseLoc can be start, end, any
        searchPhrase = phrase;
        searchLoc = phraseLoc;
    }
    
    public String getName() {
        return "phrase";
    }
    
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        if ( searchLoc.equals("start") ) {
            if ( title.startsWith(searchPhrase) ) {
                return true;
            }
        }
        if ( searchLoc.equals("end") ) {
            if ( title.endsWith(searchPhrase) ) {
                return true;
            }
        }
        if ( searchLoc.equals("any") ) {
            //System.out.println("sofaranygood");
            if ( title.contains(searchPhrase) ) {
                return true;
            }
        }
        return false;
    }

}
