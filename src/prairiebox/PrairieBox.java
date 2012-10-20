/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prairiebox;

import java.io.IOException;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import org.netbeans.microedition.lcdui.SplashScreen;

/**
 * @author benlamb
 */
public class PrairieBox extends MIDlet implements CommandListener, ItemCommandListener {
    public String winner = "oops";
    private boolean midletPaused = false;

//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private SplashScreen splashScreen;
    private Form authScreen;
    private TextField tokenField;
    private StringItem connectionstatusItem;
    private StringItem savetokenButton;
    private Spacer authScreenSpacer1;
    private Spacer authScreenSpacer2;
    private StringItem authScreenExplainer;
    private Alert badTokenAlert;
    private Alert debug;
    private Command exitCommand;
    private Command authpopupCommand;
    private Command savetokenCommand;
    private Command okCommand;
//</editor-fold>//GEN-END:|fields|0|
    /**
     * The PrairieBox constructor.
     */
    public PrairieBox() {
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
        
        // http test
        httptest get = new httptest();
        try { get.getBirthdayFromNameUsingGet("benny"); }
        catch (IOException e) {System.out.println("IOException " + e.toString());}       
        //
        
        
        // httpS test
        httpStest sec = new httpStest();
        winner = sec.run();
        //
        
        switchDisplayable(null, getSplashScreen());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
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
            }//GEN-BEGIN:|7-commandAction|7|16-preAction
        } else if (displayable == splashScreen) {
            if (command == SplashScreen.DISMISS_COMMAND) {//GEN-END:|7-commandAction|7|16-preAction
                // write pre-action user code here
                verifyToken();//GEN-LINE:|7-commandAction|8|16-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|9|7-postCommandAction
        }//GEN-END:|7-commandAction|9|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|10|
//</editor-fold>//GEN-END:|7-commandAction|10|


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
            splashScreen.setText("Prairie Box");//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
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
            connectionstatusItem = new StringItem("Status: ", winner);//GEN-LINE:|28-getter|1|28-postInit
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
            tokenField = new TextField("Enter Token", "", 64, TextField.ANY);//GEN-LINE:|29-getter|1|29-postInit
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
            badTokenAlert = new Alert("alert", "Error: The authorization token is malformed or invalid. Please try entering a fresh token.", null, null);//GEN-BEGIN:|55-getter|1|55-postInit
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
        if (false) {//GEN-LINE:|59-if|1|60-preAction
            // write pre-action user code here
//GEN-LINE:|59-if|2|60-postAction
            // write post-action user code here
        } else {//GEN-LINE:|59-if|3|61-preAction
            // write pre-action user code here
            switchDisplayable(null, getBadTokenAlert());//GEN-LINE:|59-if|4|61-postAction
            // write post-action user code here
        }//GEN-LINE:|59-if|5|59-postIf
        // enter post-if user code here
    }//GEN-BEGIN:|59-if|6|
//</editor-fold>//GEN-END:|59-if|6|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: debug ">//GEN-BEGIN:|69-getter|0|69-preInit
    /**
     * Returns an initialized instance of debug component.
     *
     * @return the initialized component instance
     */
    public Alert getDebug() {
        if (debug == null) {//GEN-END:|69-getter|0|69-preInit
            // write pre-init user code here
            debug = new Alert("alert");//GEN-BEGIN:|69-getter|1|69-postInit
            debug.setTimeout(Alert.FOREVER);//GEN-END:|69-getter|1|69-postInit
            // write post-init user code here
        }//GEN-BEGIN:|69-getter|2|
        return debug;
    }
//</editor-fold>//GEN-END:|69-getter|2|



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
    *  This will eventually call up a mini browser screen to authenticate to foursquare
    */
			

    public void Auth24sq() {

        try {
            platformRequest("https://foursquare.com/oauth2/authenticate?client_id=" + PrivateData.OAUTH_CONSUMER_KEY + "&response_type=code&redirect_uri=" + PrivateData.REDIR_CALLBACK_URL);
        } catch (ConnectionNotFoundException ex) {
            System.out.println(ex);
        }

    }
  
}