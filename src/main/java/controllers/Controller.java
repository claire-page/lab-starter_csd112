package controllers;


import core.ReplaceableText;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ui.TxtColour;

import java.security.Key;

public class Controller {


    public EventHandler<KeyEvent> handler ;



    //deals with handling the key events.
    public static void typeText(KeyEvent e, ReplaceableText text) {

        int index = text.getIdx();

        var current = text.charAt(index).getText();

        if (e.getEventType().equals(KeyEvent.KEY_PRESSED)&&(e.getCode().equals(KeyCode.BACK_SPACE))) {
            e.consume();
                if (index != 0) {
                    text.revertCharAt(index-1);
                    TxtColour.changeColor(text.charAt(index-1), TxtColour.Gray);
                    text.decIndex();
                  }
            text.charAt(index).toggleTyped();

        }

        var entered = e.getCharacter();
        boolean boo = entered.matches("[a-zA-Z |\\p{P}]");

        if (e.getEventType().equals(KeyEvent.KEY_TYPED)&& boo ) {
            e.consume(); //yum

            if (entered.equals(current)) {
                    TxtColour.changeColor(text.charAt(index), TxtColour.Black);
                    text.incIndex();
                    text.charAt(index).toggleTyped();

                } else if (entered.equals(" ")) { //if you spaced over a character, still want it to be noticeable.
                text.incIndex();
                TxtColour.changeColor(text.charAt(index), TxtColour.Gray);
                }
            else {
                TxtColour.changeColor(text.charAt(index), TxtColour.Red);
                text.incIndex();
                text.setCharAt(index, entered);
                System.out.println("not a match");
            }

            }
        e.consume(); //consuming any key event not meeting the conditions.
        }
    }

//key combination : capitalize