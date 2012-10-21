/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prairiebox;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpsConnection;
import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 *
 * @author benlamb
 */


public class oauthstore {
    
    public static boolean isTokenValid(String token) {
    boolean valid = false; //failsafe
    String url;
    String httpcontent = null;
    
    url = "https://api.foursquare.com/v2/settings/receivePings?oauth_token=" + token + "&v=" + PrivateData.API_VERSION;
    PrivateData.debugmsg = "";
    try {
      // Query the server and retrieve the response.
      HttpsConnection hc = (HttpsConnection)Connector.open(url, Connector.READ_WRITE, true);
      hc.setRequestMethod(HttpsConnection.GET);
      InputStream pagedata = hc.openInputStream();
      int ch;
      StringBuffer pagestring = new StringBuffer();
      for(int ccnt=0; ccnt <= pagestring.length(); ccnt++) { // get the buffer.
        ch = pagedata.read();
        if (ch == -1){
        break;
        }
        pagestring.append((char)ch);
      }
      //PrivateData.debugmsg = pagestring.toString() + url;
      httpcontent = pagestring.toString();
      

      hc.close();
   }
    catch (IOException ioe) {
      System.out.println("HTTPS Exception" + ioe.toString());
      PrivateData.debugmsg = "Error. Can't make https connections.";
    }
    
    
    try {
        JSONObject json = new JSONObject(httpcontent);
        JSONObject meta = new JSONObject(json.getString("meta"));
        PrivateData.debugmsg = meta.getString("code");
    }
    catch (JSONException joe) {
        System.out.println("JSON Decode Exception" + joe.toString());
        PrivateData.debugmsg = "Error. Can't decode JSON";
    }
    
    valid = true;
    return (valid);
    }
    
    
 

    // interior function that takes URL and returns an array of info from that URL
    public static String httpSconnector(String url, String RequestMethod) {
        String returnval = null;
        String pagedatastring;

      try {
      // Query the server and retrieve the response.
      HttpsConnection hc = (HttpsConnection)Connector.open(url, Connector.READ_WRITE, true);
      hc.setRequestMethod(HttpsConnection.GET);
      InputStream pagedata = hc.openInputStream();
      int ch;
      StringBuffer pagestring = new StringBuffer();
      for(int ccnt=0; ccnt <= pagestring.length(); ccnt++) { // get the buffer.
        ch = pagedata.read();
        if (ch == -1){
        break;
        }
        pagestring.append((char)ch);
      }
      PrivateData.debugmsg = pagestring.toString() + url;
      
      
      hc.close();
    }
    catch (IOException ioe) {
      System.out.println("HTTPS Exception" + ioe.toString());
      PrivateData.debugmsg = "Error. Can't make https connections.";
    }  
        
        return(returnval);
    }
}
