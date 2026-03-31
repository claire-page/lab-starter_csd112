package controllers;


import core.ReplaceableText;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.nio.charset.StandardCharsets;

public class Controller {

    ReplaceableText text;

    int index = text.getIdx();
    KeyCode currentKey = KeyCode.getKeyCode(text.charAt(index)); //getting keycode for the character you're typing on.

    public void alterText(KeyEvent e) {
        var entered = e.getCode();
        if (entered == KeyCode.BACK_SPACE ) {
                if (index != 0) {
                    text.decIndex();
                    //change color to default (grey)
                    //log a backtrack to the data.
                }
            }
        else if (entered.equals(currentKey)){
            text.incIndex();
            //make that character solid.
        }
        //if you got here, you made a mistake.
        //make the whole word red.
        //maybe animate a shake???
    }
}
