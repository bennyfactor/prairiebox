/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prairiebox;

import javax.microedition.location.Location;

/**
 *
 * @author benlamb
 */
public class GPS {
   
    /**
     * Returns string indicating method by which GPS was obtained by parsing location.getLocationMethod();
     * @param int method
     * @return string indicating method of ascertaining GPS
     */
    public static String method(int method) {
                
            String methodmsg = "method: ";

            if ((method & Location.MTA_ASSISTED) == Location.MTA_ASSISTED) {
                methodmsg += "Assisted GPS";
            }
            if ((method & Location.MTA_UNASSISTED) == Location.MTA_UNASSISTED) {
                methodmsg += "Unassisted GPS";
            }
            if ((method & Location.MTE_CELLID) == Location.MTE_CELLID) {
                methodmsg += "Cell Site";
            }
        return(methodmsg);
    }
}
