
public class MinutesFilter implements Filter {
    private int myMinMins;
    private int myMaxMins;
    
    public MinutesFilter(int minMins, int maxMins) {
        myMinMins = minMins;
        myMaxMins = maxMins;
    }
    
    @Override
    public boolean satisfies(String id) {
        int movieMins = MovieDatabase.getMinutes(id);
        
        if ( movieMins <= myMaxMins && movieMins >= myMinMins ) {
            return true;
        }
        else {
            return false;
        }
    }

}
