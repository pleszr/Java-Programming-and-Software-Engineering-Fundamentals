
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    
    public MatchAllFilter() {
        filters = new ArrayList<Filter>();    
    }
    
    
    public void addFilter(Filter f) {
        filters.add(f);
    }
    
    public String getName() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filters.size(); i++) {
        sb.append(filters.get(i).getName());
        if (i < filters.size() - 1) {
            sb.append(",");
        }
    }
        return sb.toString();
    }
    
    
    public boolean satisfies(QuakeEntry qe) {
        for ( Filter f : filters ) {
            if ( !f.satisfies(qe) ) {
                return false;
            }
        }
        return true;
    }
}
