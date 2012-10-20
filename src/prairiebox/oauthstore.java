/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prairiebox;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpsConnection;
import javax.microedition.io.SecurityInfo;
import javax.microedition.pki.Certificate;

/**
 *
 * @author benlamb
 */


public class oauthstore {
    
    public static boolean isTokenValid(String token) {
    boolean valid = false; //failsafe
    String url = "https://api.foursquare.com/v2/settings/receivePings?oauth_token=" + token;
    PrivateData.debugmsg = "";
    
    try {
      // Query the server and retrieve the response.
      HttpsConnection hc = (HttpsConnection)Connector.open(url, Connector.READ_WRITE, true);
      hc.setRequestMethod(HttpsConnection.GET);
      InputStream pagedata = hc.openInputStream();
      int ch;
      StringBuffer pagestring = new StringBuffer();
      for(int ccnt=0; ccnt < 150; ccnt++) { // get the title.
        ch = pagedata.read();
        if (ch == -1){
        break;
        }
        pagestring.append((char)ch);
      }
      PrivateData.debugmsg = pagestring.toString() + url;
      
      
      hc.close();
      valid = true;
    }
    catch (IOException ioe) {
      System.out.println("HTTPS Exception" + ioe.toString());
      PrivateData.debugmsg = "Error. Can't make https connections.";
    }        
    
        
    return (valid);
    }
}
