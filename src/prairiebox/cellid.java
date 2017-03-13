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
    public static int cellid = hexToDec(System.getProperty("com.sonyericsson.net.cellid"));
    public static int lac    = hexToDec(System.getProperty("com.sonyericsson.net.lac"));
    public static String mcc = System.getProperty("com.sonyericsson.net.cmcc");
    public static String mnc = System.getProperty("com.sonyericsson.net.cmnc");
    
  
     /**
     * Does the power function, which is not a part of JavaME
     * Only works for integer powers (which is fine, this is just a subfunction
     * of converting hex to dec)
     * @return x^y
     */
    private static int pow( int x, int y)  {
    int z = x; 
    for( int i = 1; i < y; i++ )z *= x;
    return z;
    }

      /**
     * Converts a hexidecimal number of type String to a decimal int
     * @return decimal
     */   
    private static int hexToDec(String hexNumber) {
	int power = 0;
	int decimal = 0;
	hexNumber = hexNumber.toUpperCase();
	int length = hexNumber.length();
	String hexCode = "0123456789ABCDEF";
	for (int index = 0; index < length; index++) {
		char digit = hexNumber.charAt(length - index - 1);
		decimal = decimal + hexCode.indexOf(digit) * pow(16, power++);
	}
	return decimal;
    }
    
    /**
     * Returns String[] of lat, long, accuracy of position based on current cell tower
     * 
     * @return [0]=latitude, [1]=longitude, [2]=accuracy
     * @throws java.io.UnsupportedEncodingException
     */
    public static String getlocation() throws UnsupportedEncodingException {
        String location = null;
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
           // try {
                //JSONObject json = new JSONObject(httpcontent);
                //JSONObject cellloc = new JSONObject(json.getString("location"));
//                location[0] = cellloc.getString("lat");
//                location[1] = cellloc.getString("lng");
//                location[2] = json.getString("accuracy");
                location = httpcontent;
            //} //catch (JSONException ex) {
               // PrivateData.debugmsg = ex.toString();
            //}
      }
      if (location == null ? httpcontent != null : !location.equals(httpcontent)) {location="error";}

        return(location);
    }
    
}

