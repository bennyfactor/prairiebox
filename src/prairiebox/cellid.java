/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prairiebox;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpsConnection;
import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 *
 * @author benlamb
 */
public class cellid {
    public static String cellid = System.getProperty("com.sonyericsson.net.cellid");
    public static String lac = System.getProperty("com.sonyericsson.net.lac");
    public static String mcc = System.getProperty("com.sonyericsson.net.mcc");
    public static String mnc = System.getProperty("com.sonyericsson.net.mnc");
    
    public static String getlocation() throws UnsupportedEncodingException {
        String location;
        
        
        String postObject  = "{ \"radioType\": \"gsm\",\"cellTowers\": [ { ";
               postObject += "\"cellId\": " + cellid + ", ";
               postObject += "\"locationAreaCode\": " + lac + ", ";
               postObject += "\"mobileCountryCode\": " + mcc + ", ";
               postObject += "\"mobileNetworkCode\": " + mnc;
               postObject += " } ] }";
        byte[] postData = postObject.getBytes();

        
        //set up the connection
        String url;
        String httpcontent = null;

    /*    url = "https://www.googleapis.com/geolocation/v1/geolocate?key=" + PrivateData.GOOGLE_API_KEY;
        PrivateData.debugmsg = "";
        try {
          // Query the server and retrieve the response.
          HttpsConnection hc = (HttpsConnection)Connector.open(url);
          hc.setRequestMethod(HttpsConnection.POST);
          hc.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
          hc.setRequestProperty("Content-Length", Integer.toString(postData.length));
          OutputStream os = hc.openOutputStream(); //dump the json into the POST message as an outputstream
            os.write(postData);
            os.flush();
            os.close(); //finish the dump


          
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
//        try {
//            JSONObject json = new JSONObject(httpcontent);
//            JSONObject meta = new JSONObject(json.getString("meta"));
//
//            PrivateData.debugmsg = meta.getString("code");
//
//            if ("200".equals(meta.getString("code"))) {
//                valid = true;
//            }
//        }
//        catch (JSONException joe) {
//            System.out.println("JSON Decode Exception" + joe.toString());
//            PrivateData.debugmsg = "Error. Can't decode JSON";
//        }    
        */
        location = postData.toString();
        return(location);
    }
    
}

