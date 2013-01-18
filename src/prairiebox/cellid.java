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
    public static int cellid = Integer.parseInt(System.getProperty("com.sonyericsson.net.cellid").toString(),16);
    public static int lac    = Integer.parseInt(System.getProperty("com.sonyericsson.net.lac").toString(),16);
    public static String mcc = System.getProperty("com.sonyericsson.net.cmcc");
    public static String mnc = System.getProperty("com.sonyericsson.net.cmnc");
    
    public static String getlocation() throws UnsupportedEncodingException {
        String location;
        String responsecode = "999";
        
        
        String postObject  = "{ \"radioType\":  \"gsm\", \n \"cellTowers\": [ \n  { \n   \"mobileNetworkCode\": "+mnc+", \n   \"cellId\": "+cellid+", \n   \"locationAreaCode\": "+lac+", \n   \"mobileCountryCode\": "+mcc+", \n   \"age\": 0 \n  } \n ] \n}";
        
        byte postData[] = postObject.getBytes();

        
        //set up the connection
        String url;
        String httpcontent = null;

        url = "https://www.googleapis.com/geolocation/v1/geolocate?key=" + PrivateData.GOOGLE_API_KEY;
        PrivateData.debugmsg = "";
        try {
          // Query the server and retrieve the response.
          HttpsConnection hc = (HttpsConnection)Connector.open(url);
          hc.setRequestMethod(HttpsConnection.POST);
          hc.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
          //hc.setRequestProperty("Content-Length", Integer.toString(postData.length));
          OutputStream os = hc.openOutputStream(); //dump the json into the POST message as an outputstream
          for(int i=0;i<postData.length;i++) {
            os.write(postData[i]);
          }
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

          //google doesn't put the response code in the JSON so we get it from the http headers
          responsecode = Integer.toString(hc.getResponseCode());
          hc.close();
       }
        catch (IOException ioe) {
          System.out.println("HTTPS Exception" + ioe.toString());
          PrivateData.debugmsg = "Error. Can't make https connections.";     
        }
      //make sure we're getting a valid response from google  
      if ("200".equals(responsecode)) {
            try {
                JSONObject json = new JSONObject(httpcontent);
                responsecode = json.toString();
            } catch (JSONException ex) {
                responsecode = ex.toString();
            }
      }


        return(responsecode);
    }
    
}

