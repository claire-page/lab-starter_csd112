package controllers;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.w3c.dom.Node;
import ui.ReplaceableTextPane;

public class UtilityFunctions {

    /**
     * used to direct keyevent to appropriate function... this needs to be able to access the pane
     * @param keyevent
     * @param pane
     */
    public static void delegateKeyEvents(KeyEvent keyevent, ReplaceableTextPane pane){

        if ( keyevent.getEventType().equals(KeyEvent.KEY_PRESSED) && keyevent.getCode().equals(KeyCode.BACK_SPACE) ){
            keyevent.consume();
            pane.backSpace();
        }

        boolean isValidText = keyevent.getCharacter().toString().matches("[a-zA-Z |\\p{P}]");

        if ( (keyevent.getEventType().equals(KeyEvent.KEY_TYPED)) && isValidText ){
            keyevent.consume();
            pane.updateForKeyTyped(keyevent.getCharacter());
        }

        keyevent.consume();
    }


    public static void swapToScreen(Node n){

    }

    public static void checkSettings(){
        System.out.println("SETTINGS CLICKED");
    }

    public static void checkAbout(){
        System.out.println("ABOUT");
    }

}


