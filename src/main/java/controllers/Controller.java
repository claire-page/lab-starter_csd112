package controllers;


import core.ReplaceableText;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ui.Preferences;
import ui.QChar;
import ui.TxtColourScheme;

public class Controller {

    public EventHandler<KeyEvent> handler ;

    //deals with the replacing of qchars/styling.
    public static void typeText(KeyEvent e, ReplaceableText text, Preferences pref , QChar[] qChars) {

        //text displayed? call ui.

        int index = text.getIdx(); //where are we?

        String currentString = String.valueOf(text.getText().charAt(index));
        var currentQChar = qChars[index];

        if (currentString.equals("\n")){
            System.out.println("found a new line...");
        }
        if (e.getEventType().equals(KeyEvent.KEY_PRESSED)&&(e.getCode().equals(KeyCode.BACK_SPACE))) {
            e.consume();
                if (index != 0) {
                    qChars[index-1].revertTxt();
                    TxtColourScheme.changeColor(qChars[index-1],pref.colours().getBlankTextColour());
                    text.decIndex();
                  }
            qChars[index].toggleTyped();
        }

        var entered = e.getCharacter();
        boolean isValidText = entered.matches("[a-zA-Z |\\p{P}]");//alphabetical or punct.

        if (e.getEventType().equals(KeyEvent.KEY_TYPED)&& isValidText) {
            e.consume(); //yum

            if (entered.equals(currentString)) {
                    TxtColourScheme.changeColor(qChars[index], pref.colours().getFilledInColour());
                    text.incIndex();
                    qChars[index].toggleTyped();

                } else if (entered.equals(" ")) { //if you spaced over a character, still want it to be noticeable.
                text.incIndex();
                TxtColourScheme.changeColor(qChars[index-1], pref.colours().getBlankTextColour());
                System.out.println("space bar pressed");
                }
            else { //if it's not a match or a space.
                TxtColourScheme.changeColor(qChars[index], pref.colours().getMistakeColour());
                text.incIndex();
                qChars[index].setText(entered);
                System.out.println("not a match");
            }
            }
        e.consume(); //consuming any key event not meeting the conditions.
        }

    }
