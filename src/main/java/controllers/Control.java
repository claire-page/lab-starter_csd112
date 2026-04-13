package controllers;

import core.RunTracker;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import core.TypeChar;
import ui.MainView;

import java.util.ArrayList;
import java.util.List;

public class Control {
    private final RunTracker run;
    private  String expected ; //new one every time. How do I make that happen?
    private  String actual;

    public Control(String s){
        this.run = new RunTracker();
        this.expected = s;
        this.actual = "";

    }
    public void delegateKeyEvents(KeyEvent e) {
        e.consume();
        System.out.println("triggered");
        if (e.getEventType().equals(KeyEvent.KEY_PRESSED)||e.getEventType().equals(KeyEvent.KEY_TYPED)) {
            System.out.println("key pressed.");
            if (run.getKeystrokes() == 0) {
                run.logKeyStroke();
                MainView.startTimer();
            }

            if (e.getCode().equals(KeyCode.BACK_SPACE)) {
                System.out.println("backspace");
                e.consume();
                run.logBackspace();
                run.logKeyStroke();
                actual = (actual.length() == 0) ? "" : actual.substring(0, actual.length() - 1);

            } else if (e.getCharacter().matches("[a-zA-Z|\\s|\\p{P}]")) {
                actual += e.getCharacter();
                run.logKeyStroke();
            }
            var data = generateTextData(expected, actual);
            MainView.renderTextData(data, expected);

            if (expected.equals(actual)){


            }
        }
    }

    public static List<TypeChar> generateTextData(String template, String actual) {
        var textInfo = new ArrayList<TypeChar>(); //starting off as an ArrayList
        for (int j = 0; j < template.length(); j++) {
            //whether the user has typed something can be determined by whether the index is still within
            //the size of the user (actual) text.
            char entered = (j < actual.length()) ? actual.charAt(j) : ' '; //cannot have empty char. so this will have to do...I think it should be fine
            textInfo.add(new TypeChar(entered, template.charAt(j)));
        }
        return ((textInfo).stream().toList()); //listifying as per that one lecture to make it immutable.
    }


    public boolean isGameActive() {
        return isGameOver;
    }

    public void setGameActive(){
        this.isGameOver = true;

    }


}







