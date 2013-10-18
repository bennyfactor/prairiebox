/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prairiebox;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.lcdui.*;
import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationListener;
import javax.microedition.location.LocationProvider;
import javax.microedition.location.QualifiedCoordinates;
import javax.microedition.midlet.*;
import org.json.me.JSONException;
import org.json.me.JSONObject;
import org.netbeans.microedition.lcdui.SplashScreen;


/**
 * @author benlamb
 */
public class PrairieBox extends MIDlet implements CommandListener, ItemCommandListener, LocationListener{

    public String httpstatus = "Very slow data connection."; //this only shows if HTTPSTest isn't completed before splash screen dismisses
    private boolean midletPaused = false;
    public String[][] recentCheckins;
    public String[][] nearbyVenues;
    public String[] cellloc;
    public static String lat, lon, alt, hac, vac;
    
    private LocationProvider locationProvider = null;

//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private List nearbyVenuesList;
    private SplashScreen splashScreen;
    private Form checkedIn;
    private StringItem checkInString;
    private Form authScreen;
    private TextField tokenField;
    private StringItem connectionstatusItem;
    private StringItem savetokenButton;
    private StringItem authScreenExplainer;
    private Spacer authScreenSpacer2;
    private Spacer authScreenSpacer1;
    private Alert noGPSLockAlert;
    private Alert badTokenAlert;
    private List recentCheckinsList;
    private Form currentLocation;
    private StringItem info;
    private StringItem stringItem;
    private Command exitCommand4;
    private Command okCommand1;
    private Command exitCommand1;
    private Command okCommand3;
    private Command exitCommand2;
    private Command exitCommand;
    private Command okCommand2;
    private Command authpopupCommand;
    private Command savetokenCommand;
    private Command okCommand;
    private Command checkinCommand;
    private Command exitCommand3;
    private Image logo160;
//</editor-fold>//GEN-END:|fields|0|

    /**
     * The PrairieBox constructor.
     */
    public PrairieBox() {
        //Location Provider instance criteria
        Criteria criteria = new Criteria();
        criteria.setCostAllowed(true);
        criteria.setPreferredPowerConsumption(Criteria.POWER_USAGE_HIGH);
        criteria.setPreferredResponseTime(5000);
        
        
        try {
            locationProvider = LocationProvider.getInstance(criteria);
        } catch (Exception e) {
            info.setText("Error. GPS subsystem unavailable. " + e);
        }
    lat = "39.175278"; 
    lon = "-76.668333";
    alt = "12";
    hac = "800";
    vac = "800";
    
    // play with these values for testing nearby locales
    
    }

//<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
//</editor-fold>//GEN-END:|methods|0|
//<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initializes the application. It is called only once when the MIDlet is
     * started. The method is called before the
     * <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
//</editor-fold>//GEN-END:|0-initialize|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getSplashScreen());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
        
        // httpS test
        httpStest sec = new httpStest();
        httpstatus = sec.run();
    }//GEN-BEGIN:|3-startMIDlet|2|
//</editor-fold>//GEN-END:|3-startMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
//</editor-fold>//GEN-END:|4-resumeMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The
     * <code>display</code> instance is taken from
     * <code>getDisplay</code> method. This method is used by all actions in the
     * design for switching displayable.
     *
     * @param alert the Alert which is temporarily set to the display;
     * if <code>null</code>, then <code>nextDisplayable</code> is set
     * immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
//</editor-fold>//GEN-END:|5-switchDisplayable|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a
     * particular displayable.
     *
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == authScreen) {//GEN-BEGIN:|7-commandAction|1|33-preAction
            if (command == authpopupCommand) {//GEN-END:|7-commandAction|1|33-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|2|33-postAction
                // write post-action user code here
                Auth24sq();
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|3|26-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|4|26-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|57-preAction
        } else if (displayable == badTokenAlert) {
            if (command == okCommand) {//GEN-END:|7-commandAction|5|57-preAction
                // write pre-action user code here
                switchDisplayable(null, getAuthScreen());//GEN-LINE:|7-commandAction|6|57-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|7|158-preAction
        } else if (displayable == checkedIn) {
            if (command == exitCommand2) {//GEN-END:|7-commandAction|7|158-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|8|158-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|9|129-preAction
        } else if (displayable == currentLocation) {
            if (command == exitCommand1) {//GEN-END:|7-commandAction|9|129-preAction
                // write pre-action user code here	  
                exitMIDlet();//GEN-LINE:|7-commandAction|10|129-postAction
                // write post-action user code here
            } else if (command == okCommand1) {//GEN-LINE:|7-commandAction|11|144-preAction
                // write pre-action user code here
                stringItem.setText("Processing");
                nearbyVenues = Foursquare.nearbyVenues(PrivateData.OAUTH_TOKEN, lat, lon, alt, hac, vac, 20);
                stringItem.setText("finished");
                stringItem.setText(nearbyVenues[0][0] + PrivateData.debugmsg);
                switchDisplayable(null, getNearbyVenuesList());//GEN-LINE:|7-commandAction|12|144-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|13|137-preAction
        } else if (displayable == nearbyVenuesList) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|13|137-preAction
                // write pre-action user code here
                nearbyVenuesListAction();//GEN-LINE:|7-commandAction|14|137-postAction
                // write post-action user code here
            } else if (command == exitCommand4) {//GEN-LINE:|7-commandAction|15|141-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|16|141-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|17|148-preAction
        } else if (displayable == noGPSLockAlert) {
            if (command == okCommand2) {//GEN-END:|7-commandAction|17|148-preAction
                
                switchDisplayable(null, getCurrentLocation());//GEN-LINE:|7-commandAction|18|148-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|19|105-preAction
        } else if (displayable == recentCheckinsList) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|19|105-preAction
                // write pre-action user code here
                recentCheckinsListAction();//GEN-LINE:|7-commandAction|20|105-postAction
                // write post-action user code here
            } else if (command == checkinCommand) {//GEN-LINE:|7-commandAction|21|126-preAction
                // write pre-action user code here
                switchDisplayable(null, getCurrentLocation());//GEN-LINE:|7-commandAction|22|126-postAction
                // write post-action user code here
                //start parallel thread to get google pseudogps data
                pseudogps googlecellapi = new pseudogps();
                googlecellapi.start();

            } else if (command == exitCommand3) {//GEN-LINE:|7-commandAction|23|121-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|24|121-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|25|16-preAction
        } else if (displayable == splashScreen) {
            if (command == SplashScreen.DISMISS_COMMAND) {//GEN-END:|7-commandAction|25|16-preAction
                // write pre-action user code here
                if (PrivateData.OAUTH_TOKEN != null) {
                    //start parallel thread to get check-in data
                    splashScreen.setText("Please wait");
                    getrecent getrecentcheckins = new getrecent();
                    getrecentcheckins.start();

                }
                //start parallel thread to get GPS data
                new Thread() {
                    public void run() {
                        locationProvider.setLocationListener(PrairieBox.this, 1, -1, -1);                       

                    }
                }.start();
                if (PrivateData.OAUTH_TOKEN != null) {
                    switchDisplayable(null, getRecentCheckinsList());
                } else {
                    switchDisplayable(null, getAuthScreen());//GEN-LINE:|7-commandAction|26|16-postAction
                // write post-action user code here
                }
            }//GEN-BEGIN:|7-commandAction|27|7-postCommandAction
        }//GEN-END:|7-commandAction|27|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|28|
//</editor-fold>//GEN-END:|7-commandAction|28|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: splashScreen ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initialized instance of splashScreen component.
     *
     * @return the initialized component instance
     */
    public SplashScreen getSplashScreen() {
        if (splashScreen == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            splashScreen = new SplashScreen(getDisplay());//GEN-BEGIN:|14-getter|1|14-postInit
            splashScreen.setTitle("Prairie Box");
            splashScreen.setCommandListener(this);
            splashScreen.setImage(getLogo160());
            splashScreen.setText("version " + getAppProperty("MIDlet-Version"));//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
            if (PrivateData.OAUTH_TOKEN != null) {
            //splashScreen.setText("Found saved token");

            }

        }//GEN-BEGIN:|14-getter|2|
        return splashScreen;
    }
//</editor-fold>//GEN-END:|14-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: authScreen ">//GEN-BEGIN:|23-getter|0|23-preInit
    /**
     * Returns an initialized instance of authScreen component.
     *
     * @return the initialized component instance
     */
    public Form getAuthScreen() {
        if (authScreen == null) {//GEN-END:|23-getter|0|23-preInit
            // write pre-init user code here
            authScreen = new Form("Login", new Item[]{getConnectionstatusItem(), getTokenField(), getAuthScreenSpacer1(), getSavetokenButton(), getAuthScreenSpacer2(), getAuthScreenExplainer()});//GEN-BEGIN:|23-getter|1|23-postInit
            authScreen.addCommand(getExitCommand());
            authScreen.addCommand(getAuthpopupCommand());
            authScreen.setCommandListener(this);//GEN-END:|23-getter|1|23-postInit
            // write post-init user code here
            if (PrivateData.OAUTH_TOKEN != null) {
                tokenField.setString(PrivateData.OAUTH_TOKEN);
            }
        }//GEN-BEGIN:|23-getter|2|
        return authScreen;
    }
//</editor-fold>//GEN-END:|23-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|25-getter|0|25-preInit
    /**
     * Returns an initialized instance of exitCommand component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|25-getter|1|25-postInit
            // write post-init user code here
        }//GEN-BEGIN:|25-getter|2|
        return exitCommand;
    }
//</editor-fold>//GEN-END:|25-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: connectionstatusItem ">//GEN-BEGIN:|28-getter|0|28-preInit
    /**
     * Returns an initialized instance of connectionstatusItem component.
     *
     * @return the initialized component instance
     */
    public StringItem getConnectionstatusItem() {
        if (connectionstatusItem == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
            connectionstatusItem = new StringItem("Status: ", httpstatus);//GEN-LINE:|28-getter|1|28-postInit
            // write post-init user code here
        }//GEN-BEGIN:|28-getter|2|
        return connectionstatusItem;
    }
//</editor-fold>//GEN-END:|28-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: tokenField ">//GEN-BEGIN:|29-getter|0|29-preInit
    /**
     * Returns an initialized instance of tokenField component.
     *
     * @return the initialized component instance
     */
    public TextField getTokenField() {
        if (tokenField == null) {//GEN-END:|29-getter|0|29-preInit
            // write pre-init user code here
            tokenField = new TextField("Enter Token", "", 32768, TextField.ANY);//GEN-BEGIN:|29-getter|1|29-postInit
            tokenField.setInitialInputMode("UCB_BASIC_LATIN");//GEN-END:|29-getter|1|29-postInit
            // write post-init user code here
        }//GEN-BEGIN:|29-getter|2|
        return tokenField;
    }
//</editor-fold>//GEN-END:|29-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: authpopupCommand ">//GEN-BEGIN:|32-getter|0|32-preInit
    /**
     * Returns an initialized instance of authpopupCommand component.
     *
     * @return the initialized component instance
     */
    public Command getAuthpopupCommand() {
        if (authpopupCommand == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
            authpopupCommand = new Command("Authorize", Command.ITEM, 0);//GEN-LINE:|32-getter|1|32-postInit
            // write post-init user code here

        }//GEN-BEGIN:|32-getter|2|
        return authpopupCommand;
    }
//</editor-fold>//GEN-END:|32-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: savetokenButton ">//GEN-BEGIN:|49-getter|0|49-preInit
    /**
     * Returns an initialized instance of savetokenButton component.
     *
     * @return the initialized component instance
     */
    public StringItem getSavetokenButton() {
        if (savetokenButton == null) {//GEN-END:|49-getter|0|49-preInit
            // write pre-init user code here
            savetokenButton = new StringItem("", "Save Token", Item.BUTTON);//GEN-BEGIN:|49-getter|1|49-postInit
            savetokenButton.addCommand(getSavetokenCommand());
            savetokenButton.setItemCommandListener(this);
            savetokenButton.setLayout(ImageItem.LAYOUT_CENTER | ImageItem.LAYOUT_NEWLINE_BEFORE | Item.LAYOUT_SHRINK | Item.LAYOUT_VSHRINK | Item.LAYOUT_2);
            savetokenButton.setPreferredSize(-1, -1);//GEN-END:|49-getter|1|49-postInit
            // write post-init user code here
        }//GEN-BEGIN:|49-getter|2|
        return savetokenButton;
    }
//</editor-fold>//GEN-END:|49-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Items ">//GEN-BEGIN:|8-itemCommandAction|0|8-preItemCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a
     * particular item.
     *
     * @param command the Command that was invoked
     * @param displayable the Item where the command was invoked
     */
    public void commandAction(Command command, Item item) {//GEN-END:|8-itemCommandAction|0|8-preItemCommandAction
        // write pre-action user code here
        if (item == savetokenButton) {//GEN-BEGIN:|8-itemCommandAction|1|51-preAction
            if (command == savetokenCommand) {//GEN-END:|8-itemCommandAction|1|51-preAction
                // write pre-action user code here
                verifyToken();//GEN-LINE:|8-itemCommandAction|2|51-postAction
                // write post-action user code here
            }//GEN-BEGIN:|8-itemCommandAction|3|8-postItemCommandAction
        }//GEN-END:|8-itemCommandAction|3|8-postItemCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|8-itemCommandAction|4|
//</editor-fold>//GEN-END:|8-itemCommandAction|4|


//<editor-fold defaultstate="collapsed" desc=" Generated Getter: savetokenCommand ">//GEN-BEGIN:|50-getter|0|50-preInit
    /**
     * Returns an initialized instance of savetokenCommand component.
     *
     * @return the initialized component instance
     */
    public Command getSavetokenCommand() {
        if (savetokenCommand == null) {//GEN-END:|50-getter|0|50-preInit
            // write pre-init user code here
            savetokenCommand = new Command("Save", "Save Token", Command.ITEM, 0);//GEN-LINE:|50-getter|1|50-postInit
            // write post-init user code here

        }//GEN-BEGIN:|50-getter|2|
        return savetokenCommand;
    }
//</editor-fold>//GEN-END:|50-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: authScreenSpacer1 ">//GEN-BEGIN:|52-getter|0|52-preInit
    /**
     * Returns an initialized instance of authScreenSpacer1 component.
     *
     * @return the initialized component instance
     */
    public Spacer getAuthScreenSpacer1() {
        if (authScreenSpacer1 == null) {//GEN-END:|52-getter|0|52-preInit
            // write pre-init user code here
            authScreenSpacer1 = new Spacer(16, 20);//GEN-LINE:|52-getter|1|52-postInit
            // write post-init user code here
        }//GEN-BEGIN:|52-getter|2|
        return authScreenSpacer1;
    }
//</editor-fold>//GEN-END:|52-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: authScreenSpacer2 ">//GEN-BEGIN:|53-getter|0|53-preInit
    /**
     * Returns an initialized instance of authScreenSpacer2 component.
     *
     * @return the initialized component instance
     */
    public Spacer getAuthScreenSpacer2() {
        if (authScreenSpacer2 == null) {//GEN-END:|53-getter|0|53-preInit
            // write pre-init user code here
            authScreenSpacer2 = new Spacer(16, 40);//GEN-LINE:|53-getter|1|53-postInit
            // write post-init user code here
        }//GEN-BEGIN:|53-getter|2|
        return authScreenSpacer2;
    }
//</editor-fold>//GEN-END:|53-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: authScreenExplainer ">//GEN-BEGIN:|54-getter|0|54-preInit
    /**
     * Returns an initialized instance of authScreenExplainer component.
     *
     * @return the initialized component instance
     */
    public StringItem getAuthScreenExplainer() {
        if (authScreenExplainer == null) {//GEN-END:|54-getter|0|54-preInit
            // write pre-init user code here
            authScreenExplainer = new StringItem("How to log in:", "\nFoursquare requires a web login to allow this app access, and then grants a token. Click Authorize to open a browser. When presented with the token, copy it to the clipboard & paste it into the field above, then click Save Token.");//GEN-LINE:|54-getter|1|54-postInit
            // write post-init user code here
        }//GEN-BEGIN:|54-getter|2|
        return authScreenExplainer;
    }
//</editor-fold>//GEN-END:|54-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: badTokenAlert ">//GEN-BEGIN:|55-getter|0|55-preInit
    /**
     * Returns an initialized instance of badTokenAlert component.
     *
     * @return the initialized component instance
     */
    public Alert getBadTokenAlert() {
        if (badTokenAlert == null) {//GEN-END:|55-getter|0|55-preInit
            // write pre-init user code here
            String badtokenalertmsg = "Error: The authorization token is malformed or invalid. Please try entering a fresh token.";
            badTokenAlert = new Alert("alert", badtokenalertmsg, null, null);//GEN-BEGIN:|55-getter|1|55-postInit
            badTokenAlert.addCommand(getOkCommand());
            badTokenAlert.setCommandListener(this);
            badTokenAlert.setTimeout(Alert.FOREVER);//GEN-END:|55-getter|1|55-postInit
            // write post-init user code here
        }//GEN-BEGIN:|55-getter|2|
        return badTokenAlert;
    }
//</editor-fold>//GEN-END:|55-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|56-getter|0|56-preInit
    /**
     * Returns an initialized instance of okCommand component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|56-getter|0|56-preInit
            // write pre-init user code here
            okCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|56-getter|1|56-postInit
            // write post-init user code here
        }//GEN-BEGIN:|56-getter|2|
        return okCommand;
    }
//</editor-fold>//GEN-END:|56-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: verifyToken ">//GEN-BEGIN:|59-if|0|59-preIf
    /**
     * Performs an action assigned to the verifyToken if-point.
     */
    public void verifyToken() {//GEN-END:|59-if|0|59-preIf
        // enter pre-if user code here
        //get token, prevent user from editing field or pressing button again
        if (PrivateData.OAUTH_TOKEN == null) {
            PrivateData.OAUTH_TOKEN = tokenField.getString();
        }
        String token = tokenField.getString();
        tokenField.setString("PROCESSING");
        tokenField.setConstraints(TextField.UNEDITABLE);
        
        boolean tokenValid;
        tokenValid = Foursquare.isTokenValid(PrivateData.OAUTH_TOKEN);
        if (tokenValid) {//GEN-LINE:|59-if|1|60-preAction
            // write pre-action user code here
            recentCheckins =  Foursquare.recentCheckins(PrivateData.OAUTH_TOKEN, 10);
            switchDisplayable(null, getRecentCheckinsList());//GEN-LINE:|59-if|2|60-postAction
            // write post-action user code here
            tokenField.setString(token);
        } else {//GEN-LINE:|59-if|3|61-preAction
            // write pre-action user code here
            switchDisplayable(null, getBadTokenAlert());//GEN-LINE:|59-if|4|61-postAction
            tokenField.setString("");
            tokenField.setConstraints(TextField.ANY);
            // write post-action user code here
        }//GEN-LINE:|59-if|5|59-postIf
        // enter post-if user code here
    }//GEN-BEGIN:|59-if|6|
//</editor-fold>//GEN-END:|59-if|6|








//<editor-fold defaultstate="collapsed" desc=" Generated Getter: recentCheckinsList ">//GEN-BEGIN:|103-getter|0|103-preInit
    /**
     * Returns an initialized instance of recentCheckinsList component.
     *
     * @return the initialized component instance
     */
    public List getRecentCheckinsList() {
        if (recentCheckinsList == null) {//GEN-END:|103-getter|0|103-preInit
            // write pre-init user code here
            recentCheckinsList = new List("recent checkins", Choice.IMPLICIT);//GEN-BEGIN:|103-getter|1|103-postInit
            recentCheckinsList.append("Awaiting response from Foursquare", null);
            recentCheckinsList.addCommand(getExitCommand3());
            recentCheckinsList.addCommand(getCheckinCommand());
            recentCheckinsList.setCommandListener(this);
            recentCheckinsList.setSelectedFlags(new boolean[]{false});//GEN-END:|103-getter|1|103-postInit
            // write post-init user code here
            //programattically add new lines of recent checkins here
            recentCheckinsList.delete(0);
            for (int i = 0; i < recentCheckins.length; i++) {
                String[] thisCheckin = recentCheckins[i];
                recentCheckinsList.append( thisCheckin[1] + "\n at " + thisCheckin[2] + "\n " + thisCheckin[3] + "\n " + thisCheckin[4], null );
            }
            
        }//GEN-BEGIN:|103-getter|2|
        return recentCheckinsList;
    }
//</editor-fold>//GEN-END:|103-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: recentCheckinsListAction ">//GEN-BEGIN:|103-action|0|103-preAction
    /**
     * Performs an action assigned to the selected list element in the
     * recentCheckinsList component.
     */
    public void recentCheckinsListAction() {//GEN-END:|103-action|0|103-preAction
        // enter pre-action user code here
        String __selectedString = getRecentCheckinsList().getString(getRecentCheckinsList().getSelectedIndex());//GEN-BEGIN:|103-action|1|123-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Awaiting response from Foursquare")) {//GEN-END:|103-action|1|123-preAction
                // write pre-action user code here
//GEN-LINE:|103-action|2|123-postAction
                // write post-action user code here
            }//GEN-BEGIN:|103-action|3|103-postAction
        }//GEN-END:|103-action|3|103-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|103-action|4|
//</editor-fold>//GEN-END:|103-action|4|













//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand3 ">//GEN-BEGIN:|120-getter|0|120-preInit
    /**
     * Returns an initialized instance of exitCommand3 component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand3() {
        if (exitCommand3 == null) {//GEN-END:|120-getter|0|120-preInit
            // write pre-init user code here
            exitCommand3 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|120-getter|1|120-postInit
            // write post-init user code here
        }//GEN-BEGIN:|120-getter|2|
        return exitCommand3;
    }
//</editor-fold>//GEN-END:|120-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: currentLocation ">//GEN-BEGIN:|124-getter|0|124-preInit
    /**
     * Returns an initialized instance of currentLocation component.
     *
     * @return the initialized component instance
     */
    public Form getCurrentLocation() {
        if (currentLocation == null) {//GEN-END:|124-getter|0|124-preInit
            // write pre-init user code here
            currentLocation = new Form("Current Location", new Item[]{getInfo(), getStringItem()});//GEN-BEGIN:|124-getter|1|124-postInit
            currentLocation.addCommand(getExitCommand1());
            currentLocation.addCommand(getOkCommand1());
            currentLocation.setCommandListener(this);//GEN-END:|124-getter|1|124-postInit
            // write post-init user code here
        }//GEN-BEGIN:|124-getter|2|
        return currentLocation;
    }
//</editor-fold>//GEN-END:|124-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: info ">//GEN-BEGIN:|131-getter|0|131-preInit
    /**
     * Returns an initialized instance of info component.
     *
     * @return the initialized component instance
     */
    public StringItem getInfo() {
        if (info == null) {//GEN-END:|131-getter|0|131-preInit
            // write pre-init user code here
            info = new StringItem("Current Location:", ("waiting on GPS..."));//GEN-LINE:|131-getter|1|131-postInit
            // write post-init user code here
        }//GEN-BEGIN:|131-getter|2|
        return info;
    }
//</editor-fold>//GEN-END:|131-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: checkinCommand ">//GEN-BEGIN:|125-getter|0|125-preInit
    /**
     * Returns an initialized instance of checkinCommand component.
     *
     * @return the initialized component instance
     */
    public Command getCheckinCommand() {
        if (checkinCommand == null) {//GEN-END:|125-getter|0|125-preInit
            // write pre-init user code here
            checkinCommand = new Command("Check in", "Check in", Command.ITEM, 0);//GEN-LINE:|125-getter|1|125-postInit
            // write post-init user code here
            
        }//GEN-BEGIN:|125-getter|2|
        return checkinCommand;
    }
//</editor-fold>//GEN-END:|125-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand1 ">//GEN-BEGIN:|128-getter|0|128-preInit
    /**
     * Returns an initialized instance of exitCommand1 component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand1() {
        if (exitCommand1 == null) {//GEN-END:|128-getter|0|128-preInit
            // write pre-init user code here
            exitCommand1 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|128-getter|1|128-postInit
            // write post-init user code here
        }//GEN-BEGIN:|128-getter|2|
        return exitCommand1;
    }
//</editor-fold>//GEN-END:|128-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: verifyLocation ">//GEN-BEGIN:|132-if|0|132-preIf
    /**
     * Performs an action assigned to the verifyLocation if-point.
     */
    public void verifyLocation() {//GEN-END:|132-if|0|132-preIf
        // enter pre-if user code here
        if (true) {//GEN-LINE:|132-if|1|133-preAction
            // write pre-action user code here
            switchDisplayable(null, getNearbyVenuesList());//GEN-LINE:|132-if|2|133-postAction
            // write post-action user code here
        } else {//GEN-LINE:|132-if|3|134-preAction
            // write pre-action user code here
            switchDisplayable(null, getNoGPSLockAlert());//GEN-LINE:|132-if|4|134-postAction
            // write post-action user code here
        }//GEN-LINE:|132-if|5|132-postIf
        // enter post-if user code here
    }//GEN-BEGIN:|132-if|6|
//</editor-fold>//GEN-END:|132-if|6|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: nearbyVenuesList ">//GEN-BEGIN:|136-getter|0|136-preInit
    /**
     * Returns an initialized instance of nearbyVenuesList component.
     *
     * @return the initialized component instance
     */
    public List getNearbyVenuesList() {
        if (nearbyVenuesList == null) {//GEN-END:|136-getter|0|136-preInit
            // write pre-init user code here
            nearbyVenuesList = new List("list", Choice.IMPLICIT);//GEN-BEGIN:|136-getter|1|136-postInit
            nearbyVenuesList.append("dummy", null);
            nearbyVenuesList.addCommand(getExitCommand4());
            nearbyVenuesList.setCommandListener(this);
            nearbyVenuesList.setSelectedFlags(new boolean[]{false});//GEN-END:|136-getter|1|136-postInit
            // write post-init user code here
            nearbyVenuesList.delete(0);
            if (nearbyVenues != null) {
            for (int i = 0; i < nearbyVenues.length; i++) {
                String[] venue = nearbyVenues[i];
                nearbyVenuesList.append(venue[0], null);
            
                      }
            } else {
                nearbyVenuesList.append("No venues nearby", null);
            }
            //nearbyVenuesList.append(PrivateData.debugmsg, null);
        }//GEN-BEGIN:|136-getter|2|
        return nearbyVenuesList;
    }
//</editor-fold>//GEN-END:|136-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: nearbyVenuesListAction ">//GEN-BEGIN:|136-action|0|136-preAction
    /**
     * Performs an action assigned to the selected list element in the
     * nearbyVenuesList component.
     */
    public void nearbyVenuesListAction() {//GEN-END:|136-action|0|136-preAction
        // enter pre-action user code here
        String __selectedString = getNearbyVenuesList().getString(getNearbyVenuesList().getSelectedIndex());//GEN-BEGIN:|136-action|1|151-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("dummy")) {//GEN-END:|136-action|1|151-preAction
                // write pre-action user code here
                switchDisplayable(null, getCheckedIn());//GEN-LINE:|136-action|2|151-postAction
              /*  // write post-action user code here
//GEN-BEGIN:|136-action|3|136-postAction
                 }
                 }//GEN-END:|136-action|3|136-postAction
        */  }
            
        // enter post-action user code here
         int __selectedIndex = getNearbyVenuesList().getSelectedIndex();
        // //pseudocode: nearbyVenues[__selectedIndex][1] = venueid for lookup
        // //https://api.foursquare.com/v2/checkins/add?v=20120321&venueId=VENUE_ID&broadcast=public,twitter&ll=LAT%2CLON&llAcc=HAC&alt=ALT&altAcc=VAC
        nearbyVenuesList.setTitle("processing");
        checkInString.setText(Foursquare.Checkin(PrivateData.OAUTH_TOKEN, nearbyVenues[__selectedIndex][1], ""/*shout*/, lat, lon).toString());
        //nearbyVenuesList.append(PrivateData.debugmsg, null);
         nearbyVenuesList.setTitle("processed");
         switchDisplayable(null, getCheckedIn());
        }
    }//GEN-BEGIN:|136-action|4|
//</editor-fold>//GEN-END:|136-action|4|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand4 ">//GEN-BEGIN:|140-getter|0|140-preInit
    /**
     * Returns an initialized instance of exitCommand4 component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand4() {
        if (exitCommand4 == null) {//GEN-END:|140-getter|0|140-preInit
            // write pre-init user code here
            exitCommand4 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|140-getter|1|140-postInit
            // write post-init user code here
        }//GEN-BEGIN:|140-getter|2|
        return exitCommand4;
    }
//</editor-fold>//GEN-END:|140-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand1 ">//GEN-BEGIN:|143-getter|0|143-preInit
    /**
     * Returns an initialized instance of okCommand1 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand1() {
        if (okCommand1 == null) {//GEN-END:|143-getter|0|143-preInit
            // write pre-init user code here
            okCommand1 = new Command("Ok", Command.OK, 0);//GEN-LINE:|143-getter|1|143-postInit
            // write post-init user code here
        }//GEN-BEGIN:|143-getter|2|
        return okCommand1;
    }
//</editor-fold>//GEN-END:|143-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: noGPSLockAlert ">//GEN-BEGIN:|146-getter|0|146-preInit
    /**
     * Returns an initialized instance of noGPSLockAlert component.
     *
     * @return the initialized component instance
     */
    public Alert getNoGPSLockAlert() {
        if (noGPSLockAlert == null) {//GEN-END:|146-getter|0|146-preInit
            // write pre-init user code here
            noGPSLockAlert = new Alert("alert", "please wait for GPS lock", null, null);//GEN-BEGIN:|146-getter|1|146-postInit
            noGPSLockAlert.addCommand(getOkCommand2());
            noGPSLockAlert.setCommandListener(this);
            noGPSLockAlert.setTimeout(Alert.FOREVER);//GEN-END:|146-getter|1|146-postInit
            // write post-init user code here
        }//GEN-BEGIN:|146-getter|2|
        return noGPSLockAlert;
    }
//</editor-fold>//GEN-END:|146-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand2 ">//GEN-BEGIN:|147-getter|0|147-preInit
    /**
     * Returns an initialized instance of okCommand2 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand2() {
        if (okCommand2 == null) {//GEN-END:|147-getter|0|147-preInit
            // write pre-init user code here
            okCommand2 = new Command("Ok", Command.OK, 0);//GEN-LINE:|147-getter|1|147-postInit
            // write post-init user code here
        }//GEN-BEGIN:|147-getter|2|
        return okCommand2;
    }
//</editor-fold>//GEN-END:|147-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|152-getter|0|152-preInit
    /**
     * Returns an initialized instance of stringItem component.
     *
     * @return the initialized component instance
     */
    public StringItem getStringItem() {
        if (stringItem == null) {//GEN-END:|152-getter|0|152-preInit
            // write pre-init user code here
            //String cat = lat + ", " + lon + ", " + ", " + alt + " hac " + hac + " vac " + vac;
            stringItem = new StringItem("debug", "");//GEN-LINE:|152-getter|1|152-postInit
 // write post-init user code here
        }//GEN-BEGIN:|152-getter|2|
        return stringItem;
    }
//</editor-fold>//GEN-END:|152-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand3 ">//GEN-BEGIN:|153-getter|0|153-preInit
    /**
     * Returns an initialized instance of okCommand3 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand3() {
        if (okCommand3 == null) {//GEN-END:|153-getter|0|153-preInit
            // write pre-init user code here
            okCommand3 = new Command("Ok", Command.OK, 0);//GEN-LINE:|153-getter|1|153-postInit
            // write post-init user code here
        }//GEN-BEGIN:|153-getter|2|
        return okCommand3;
    }
//</editor-fold>//GEN-END:|153-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: checkedIn ">//GEN-BEGIN:|155-getter|0|155-preInit
    /**
     * Returns an initialized instance of checkedIn component.
     *
     * @return the initialized component instance
     */
    public Form getCheckedIn() {
        if (checkedIn == null) {//GEN-END:|155-getter|0|155-preInit
            // write pre-init user code here
            checkedIn = new Form("Check In", new Item[]{getCheckInString()});//GEN-BEGIN:|155-getter|1|155-postInit
            checkedIn.addCommand(getExitCommand2());
            checkedIn.setCommandListener(this);//GEN-END:|155-getter|1|155-postInit
            // write post-init user code here
        }//GEN-BEGIN:|155-getter|2|
        return checkedIn;
    }
//</editor-fold>//GEN-END:|155-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand2 ">//GEN-BEGIN:|157-getter|0|157-preInit
    /**
     * Returns an initialized instance of exitCommand2 component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand2() {
        if (exitCommand2 == null) {//GEN-END:|157-getter|0|157-preInit
            // write pre-init user code here
            exitCommand2 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|157-getter|1|157-postInit
            // write post-init user code here
        }//GEN-BEGIN:|157-getter|2|
        return exitCommand2;
    }
//</editor-fold>//GEN-END:|157-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: checkInString ">//GEN-BEGIN:|160-getter|0|160-preInit
    /**
     * Returns an initialized instance of checkInString component.
     *
     * @return the initialized component instance
     */
    public StringItem getCheckInString() {
        if (checkInString == null) {//GEN-END:|160-getter|0|160-preInit
            // write pre-init user code here
            checkInString = new StringItem("Reply \n", null);//GEN-LINE:|160-getter|1|160-postInit
            // write post-init user code here
        }//GEN-BEGIN:|160-getter|2|
        return checkInString;
    }
//</editor-fold>//GEN-END:|160-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: logo160 ">//GEN-BEGIN:|177-getter|0|177-preInit
    /**
     * Returns an initialized instance of logo160 component.
     *
     * @return the initialized component instance
     */
    public Image getLogo160() {
        if (logo160 == null) {//GEN-END:|177-getter|0|177-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|177-getter|1|177-@java.io.IOException
                logo160 = Image.createImage("/assets/images/prairiebox_160.png");
            } catch (java.io.IOException e) {//GEN-END:|177-getter|1|177-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|177-getter|2|177-postInit
            // write post-init user code here
        }//GEN-BEGIN:|177-getter|3|
        return logo160;
    }
//</editor-fold>//GEN-END:|177-getter|3|












    /**
     * Returns a display instance.
     *
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        //kill off GPS data thread
        new Thread() {
            public void run() {

                locationProvider.setLocationListener(null, -1, -1, -1);

            }
        }.start();
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started. Checks whether the MIDlet have been
     * already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     *
     * @param unconditional if true, then the MIDlet has to be unconditionally
     * terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

    /**
     * Authenticate to Foursquare
     *
     * This will eventually call up a mini browser screen to authenticate to
     * foursquare
     */
    public void Auth24sq() {

        try {
            platformRequest("http://prairiebox4j2me.appspot.com/login2");
        } catch (ConnectionNotFoundException ex) {
            System.out.println(ex);
        }

    }
    
    // location stuff
    public void locationUpdated(LocationProvider provider, Location location) {
        if (location != null && location.isValid()) {
            
            //get coordinates
            QualifiedCoordinates qc = location.getQualifiedCoordinates();
            
            //see which method was used to get coordinates
            String method = GPS.method(location.getLocationMethod());

            
            //display coordinates
            info.setText(
                    "Lat: " + qc.getLatitude() + "\n"
                    + "Lon: " + qc.getLongitude() + "\n"
                    + "Alt: " + qc.getAltitude() + "\n"
                    + "Acc: " + qc.getHorizontalAccuracy() + "\n"
                    + method + "\n" 
                    + System.getProperty("microedition.platform")
                    );
            
            //set variables with gotten coordinates
            lat = ""+qc.getLatitude();
            lon = ""+qc.getLongitude();
            alt = ""+qc.getAltitude();
            hac = ""+qc.getHorizontalAccuracy();
            vac = ""+qc.getVerticalAccuracy();
            //gpscoords[5] = ""+(System.currentTimeMillis()/1000L);
                       
        }
    }

    public void providerStateChanged(LocationProvider provider,
            int newState) {
    }

    public class pseudogps extends Thread {

        public void run() {
            boolean quit = false;
            try {
                //Thread.sleep(2000);
                JSONObject json = new JSONObject(cellid.getlocation());
                JSONObject cellloc = new JSONObject(json.getString("location"));
                //set variables with gotten coordinates
                lat = cellloc.getString("lat");
                lon = cellloc.getString("lng");
                alt = "0"; 
                hac = json.getString("accuracy");
                vac = json.getString("accuracy");
                info.setText(
                        "Waiting on GPS... \n" 
                    + "Lat: " + lat + "\n"
                    + "Lon: " + lon + "\n"
                    + "Alt: " + "unknown" + "\n"
                    + "Acc: " + hac + "\n"
                    + "via Cell Tower" + "\n" 
                    );
            } catch (JSONException ex) {
                ex.printStackTrace(); 
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }// catch (InterruptedException ex) {
            //    ex.printStackTrace();
            //}
        }


        
    }

 
        
    
    public class getrecent extends Thread {

        public void run() {
            recentCheckins =  Foursquare.recentCheckins(PrivateData.OAUTH_TOKEN, 10);
            if (recentCheckinsList != null) {
                recentCheckinsList.deleteAll();
                for (int i = 0; i < recentCheckins.length; i++) {
                    String[] thisCheckin = recentCheckins[i];
                    recentCheckinsList.append( thisCheckin[1] + "\n at " + thisCheckin[2] + "\n " + thisCheckin[3] + "\n " + thisCheckin[4], null );
                }
            }
            switchDisplayable(null, getRecentCheckinsList());

            return;
        }


        
    }

    
}