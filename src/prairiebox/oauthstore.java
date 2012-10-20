/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prairiebox;

/**
 *
 * @author benlamb
 */


public class oauthstore {
    
    public boolean isTokenValid(String token) {
    boolean valid = false; //failsafe
    String url = "https://api.foursquare.com/v2/settings/receivePings?oauth_token=" + token;
    
    
        
    return (valid);
    }
}
