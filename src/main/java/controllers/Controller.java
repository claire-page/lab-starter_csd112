package controllers;


import core.ReplaceableText;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ui.TxtColour;

import java.security.Key;

public class Controller {


    public EventHandler<KeyEvent> handler ;

    public static void typeText(KeyEvent e, ReplaceableText text) {

        int index = text.getIdx();
        //where we currently are.

        //compare next in line.

        var current = text.charAt(index).getText();

        var entered = e.getText();

       if (entered.matches("[A-Za-z]")){

         if (entered.equals(current)){

            TxtColour.changeColor(text.charAt(index), TxtColour.Black);
            text.incIndex();
            e.consume();
        }
        else{
            TxtColour.changeColor(text.charAt(index), TxtColour.Red);
            text.incIndex();
            text.setCharAt(index,entered);
            e.consume();
             System.out.println("not a match");
        }

        }
        if (e.getCode().equals(KeyCode.BACK_SPACE)) {
            text.revertCharAt(index-1);
            TxtColour.changeColor(text.charAt(index-1), TxtColour.Gray);
            if (index!=0) {
                text.decIndex();
            }
            e.consume();

        }


    }

}
//key combination : capitalize