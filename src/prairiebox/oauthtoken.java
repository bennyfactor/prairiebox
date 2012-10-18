/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prairiebox;

/**
 *
 * @author benlamb
 */

import java.io.*;
import javax.microedition.io.*;




public final class oauthtoken  {


     /**
      This function connects to web server and passes the parameter name to the
    target in HTTP Get request. Upon successful connection, web server returns
    birthday for 'name'.
    This function also calls getConnectionInformation to retrieve properties of
    HTTPConnection
   **/

  public void getBirthdayFromNameUsingGet(String name) throws IOException {

    HttpConnection httpConn = null;
      String url = "http://localhost:8080/examples/servlet/GetBirthday?name=" + name;

    InputStream is = null;
    OutputStream os = null;

    try {
      // Open an HTTP Connection object
      httpConn = (HttpConnection)Connector.open(url);

      // Setup HTTP Request
      httpConn.setRequestMethod(HttpConnection.GET);
      httpConn.setRequestProperty("User-Agent",
        "Profile/MIDP-1.0 Confirguration/CLDC-1.0");


      // This function retrieves the information of this connection
      getConnectionInformation(httpConn);

      /** Initiate connection and check for the response code. If the
        response code is HTTP_OK then get the content from the target
      **/
      int respCode = httpConn.getResponseCode();
      if (respCode == httpConn.HTTP_OK) {
        StringBuffer sb = new StringBuffer();
        os = httpConn.openOutputStream();
        is = httpConn.openDataInputStream();
        int chr;
        while ((chr = is.read()) != -1){
          sb.append((char) chr);}

        // Web Server just returns the birthday in mm/dd/yy format.
        System.out.println(name+"'s Birthday is " + sb.toString());
      }
      else {
        System.out.println("Error in opening HTTP Connection. Error#" + respCode);
      }

      } finally {
        if(is!= null){
           is.close();}
          if(os != null){
            os.close();}
      if(httpConn != null){
            httpConn.close();}
    }

    }

  /**
      This function connects to web server and passes the parameter name to the
    target in HTTP POST request. Upon successful connection, web server returns
    birthday for 'name'.
    This function also calls getConnectionInformation to retrieve properties of
    HTTPConnection
   **/

  public void getBirthdayFromNameUsingPost(String name) throws IOException {

    HttpConnection httpConn = null;
      String url = "http://localhost:8080/examples/servlet/GetBirthday";
    InputStream is = null;
    OutputStream os = null;

    try {
      // Open an HTTP Connection object
      httpConn = (HttpConnection)Connector.open(url);
      // Setup HTTP Request to POST
      httpConn.setRequestMethod(HttpConnection.POST);

      httpConn.setRequestProperty("User-Agent",
        "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
      httpConn.setRequestProperty("Accept_Language","en-US");
      //Content-Type is must to pass parameters in POST Request
      httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

      // This function retrieves the information of this connection
      getConnectionInformation(httpConn);


      os = httpConn.openOutputStream();

      String params;
      params = "name=" + name;

      os.write(params.getBytes());

      /**Caution: os.flush() is controversial. It may create unexpected behavior
            on certain mobile devices. Try it out for your mobile device **/

      //os.flush();

      // Read Response from the Server

      StringBuffer sb = new StringBuffer();
      is = httpConn.openDataInputStream();
      int chr;
      while ((chr = is.read()) != -1){
        sb.append((char) chr);}

      // Web Server just returns the birthday in mm/dd/yy format.
      System.out.println(name+"'s Birthday is " + sb.toString());

      } finally {
        if(is!= null){
           is.close();}
          if(os != null){
            os.close();}
      if(httpConn != null){
            httpConn.close();}
    }

    }


    /***  After setup, attributes of the HttpConnection object can be retrived using
        various get methods.
    ***/
    void getConnectionInformation(HttpConnection hc) {

    System.out.println("Request Method for this connection is " + hc.getRequestMethod());
    System.out.println("URL in this connection is " + hc.getURL());
    System.out.println("Protocol for this connection is " + hc.getProtocol()); // It better be HTTP:)
    System.out.println("This object is connected to " + hc.getHost() + " host");
    System.out.println("HTTP Port in use is " + hc.getPort());
    System.out.println("Query parameter in this request are  " + hc.getQuery());

    }

}