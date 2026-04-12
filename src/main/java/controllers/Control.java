package controllers;

import core.RunTracker;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ui.GameScreen;
import core.TypeChar;

import java.util.ArrayList;
import java.util.List;

public class Control {

    private static RunTracker run = new RunTracker();
    private static String expected = "blah blah blah";
    private static String actual = "";


    public static void initTimer(GameScreen g) {

     AnimationTimer timer = new AnimationTimer() {

        private long start = -1; //this is so the timer starts at 0...

        @Override
        public void handle(long l) { //this gets called very often.
            if (start == -1) { //only useful for first time being called.
                start = l; //
            }
            var elapsed = (l - start) / 1000000000.0; //dividing to get value as a double.
            g.updateTimeLabel(elapsed);
        }
    };
}


    public static void delegateKeyEvents(KeyEvent e, GameScreen g) {


        if (run.getKeystrokes()==0){
           initTimer(g);
        }

        if (e.getCode().equals(KeyCode.BACK_SPACE)) {
            run.logBackspace();
            run.logKeyStroke();
            actual = (actual.length() ==0)? "": actual.substring(0, actual.length() - 1);


        } else if (e.getCharacter().toString().matches("[a-zA-Z|\\s|\\p{P}]")) {
            actual += e.getCharacter();
            System.out.println(actual);
            System.out.println(expected);
            run.logKeyStroke();
        }
        var textData = generateTextData(expected, actual);
        g.renderTextData(textData, expected);
        System.out.println(actual);

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

    public static void startRun(GameScreen g) {

        var timer = new AnimationTimer() {
            private long start = -1; //this is so the timer starts at 0...

            @Override
            public void handle(long l) { //this gets called very often.
                if (start == -1) { //only useful for first time being called.
                    start = l; //
                }
                var elapsed = (l - start) / 1000000000.0; //dividing to get value as a double.
                g.updateTimeLabel(elapsed);
            }
        };
        timer.start();
        System.out.println("started.");
    }

public static void initPane(GameScreen g){
    g.renderTextData(generateTextData(expected, actual), expected);
}
}





