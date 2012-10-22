/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prairiebox;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpsConnection;
import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 *
 * @author benlamb
 */


public class oauthstore {
    
    /**
     * takes token from input text field and validates it against Foursquare API
     * 
     * @return boolean true if token is valid
     */
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
    
    // check if we get a code 200 OK from foursquare, means token is valid
    try {
        JSONObject json = new JSONObject(httpcontent);
        JSONObject meta = new JSONObject(json.getString("meta"));
       
        PrivateData.debugmsg = meta.getString("code");
        
        if ("200".equals(meta.getString("code"))) {
            valid = true;
        }
    }
    catch (JSONException joe) {
        System.out.println("JSON Decode Exception" + joe.toString());
        PrivateData.debugmsg = "Error. Can't decode JSON";
    }
    
    
    return (valid);
    }
 
  
    /**
     * returns String[] of recent check-ins for (String token, int limit)
     * 
     * @return String[] recent check-ins in array
     */
    public static String[][] recentCheckins(String token, int limit) {
    String[][] checkins;
    checkins = new String[limit][6];
        checkins[0][0] = "Error"; //failsafe
    String url;
    String httpcontent;
        httpcontent = null;
    boolean valid; // check whether we actually got good data
        valid = false;
    
    
    url = "https://api.foursquare.com/v2/checkins/recent?oauth_token=" + token + "&v=" + PrivateData.API_VERSION + "&=limit" + limit;
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
    
    // check if we get a code 200 OK from foursquare, means token is valid
    try {
        JSONObject json = new JSONObject(httpcontent);
        JSONObject meta = new JSONObject(json.getString("meta"));
       
        //PrivateData.debugmsg = meta.getString("code");
        
        // read json response if request returned valid
        if ("200".equals(meta.getString("code"))) {  
            // get the response json object
            JSONObject response = new JSONObject(json.getString("response"));
            // get the array (why isn't this an object? It's [] vs {} in JSON) of the recent checkins
            JSONArray recent = new JSONArray(response.getString("recent"));
            
            //loop gets data from JSONArray for parsing. Each array index is a new recent check-in item
            for (int i = 0; i < limit; i++) {
                JSONObject get = new JSONObject(recent.getString(i));
                //get checkin id
                checkins[i][0] = get.getString("id");
                //get checkin person's name, uses optString in case user doesn't share full name
                JSONObject user = new JSONObject(get.getString("user")); 
                checkins[i][1] = user.optString("firstName") + " " + user.optString("lastName");
                //get checkin venue name
                JSONObject venue = new JSONObject(get.getString("venue"));
                checkins[i][2] = venue.getString("name");
                //get checkin venue location, use opts in case info is unavailable
                JSONObject location = new JSONObject(venue.optString("location"));
                checkins[i][3] =  location.optString("city") + ", " + location.optString("state") + ", " + location.optString("cc") ;
                //get checkin time; opt forces this value to a String
                checkins[i][4] = get.optString("createdAt");
                //get picture
                checkins[i][5] = user.optString("photo");
            }
        }
        PrivateData.debugmsg = checkins[0][1];
    }
    catch (JSONException joe) {
        System.out.println("JSON Decode Exception" + joe.toString());
        PrivateData.debugmsg = "Error. Can't decode JSON";
    }
    
    
    return (checkins);
    }

    
 

    // interior function that takes URL and returns an array of info from that URL
    public static String httpSconnector(String url, String RequestMethod) {
        return(null);
    }
}
