/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prairiebox;

/**
 *
 * @author benlamb
 */

/*
 * Returns human-readible comparison of two unix epochs (int more_recent, int less_recent
 * This is in SECONDS not MILLISECONDS
 * 
 * 
 */
public class HowLongAgo {
    /**
     * Returns human-readable comparison of two unix epochs (int recent, int distant)
     * This is in SECONDS not MILLISECONDS
     * 
     * @return human-readable comparison X days ago / X hrs ago / X min ago / just now
     */
    public static String epoch(int recent, int distant) {
        String returnstring;
        int difference = recent - distant;
        int days = (difference*60*60) % 24;
        int hours = (difference*60) % 60;
        int minutes = difference % 60;
        if (days > 0) {
            returnstring = days + " days ago";
        
        }
        else if (hours > 0) {
            returnstring = hours + " hours ago";
            
        }
        else if (minutes > 0) {
            returnstring = minutes + " minutes ago";
        }
        else {
            returnstring = "just now";
        }
        return(returnstring);
    }
    
}
