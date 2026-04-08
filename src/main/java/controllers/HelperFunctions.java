package controllers;

import core.RunTracker;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ui.ReplaceableTextPane;
//CONTROLLERS.

public class HelperFunctions {
    /**
     * used to direct keyevent to appropriate function... this needs to be able to access the pane
     *
     * @param keyevent
     * @param pane
     */
    //TODO - QUESTION: is this the effectful function???
    /// maybe check the event keycode in here. If you take out the Keycode. part you're
    /// Key the pane expects a character to do its UI business.
    ///
    public static void delegateKeyEvents(KeyEvent keyevent, ReplaceableTextPane pane) {

        if (pane.getStatus()==true){ //TODO: way to eliminate the event listener once done? hm. might be more straightforward.

        if (keyevent.getEventType().equals(KeyEvent.KEY_PRESSED) && keyevent.getCode().equals(KeyCode.BACK_SPACE)) {
            keyevent.consume();
            pane.backSpace();
        }
        boolean isValidText = keyevent.getCharacter().toString().matches("[a-zA-Z |\\p{P}]");

        if ((keyevent.getEventType().equals(KeyEvent.KEY_TYPED)) && isValidText) {
            keyevent.consume();
            pane.updateForKeyTyped(keyevent.getCharacter());
        }
        keyevent.consume();
        }
    }

    //TODO: is this too much logic for a Controller?
    //...overloaded. has the added parameter of a runTracker and contains some extra logic.
    public static void delegateKeyEvents(KeyEvent keyevent, ReplaceableTextPane pane, RunTracker runTracker, AnimationTimer timer) {
        if (keyevent.getEventType().equals(KeyEvent.KEY_PRESSED) && keyevent.getCode().equals(KeyCode.BACK_SPACE)) {
            keyevent.consume();
            pane.backSpace();
            runTracker.incBackTracks();
        }

        boolean isValidText = keyevent.getCharacter().toString().matches("[a-zA-Z |\\p{P}]");

        if ((keyevent.getEventType().equals(KeyEvent.KEY_TYPED)) && isValidText) {
            runTracker.incKeyStrokes();
            pane.updateForKeyTyped(keyevent.getCharacter());
        }
        keyevent.consume();
        //the rest is calculated at the end. would have liked to have tally progressively displayed but ah well. little steps.
        if (pane.getCompletionStatus()) { //this
            System.out.println("DONE!");
            pane.deactivate();
            timer.stop();
//            showPane(runTracker.)
        }
    }


//
//    public void updateGameStatus(RunTracker T, ){
//        if
//
//
//    }

    public static void checkSettings() {
        System.out.println("SETTINGS CLICKED");
    }

}






