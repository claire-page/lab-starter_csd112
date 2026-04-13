package controllers;

import core.RunData;
import core.RunTracker;

import core.TextToType;
import database.DatabaseInteractor;

import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import core.TypeChar;
import ui.MainView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Control {
    private RunTracker run;
    private String expected;
    private String actual;
    private RunData endData; //only exists if
    private static final DatabaseInteractor db = new DatabaseInteractor("jdbc:mysql://localhost:3306/qwertydb");

    public Control() {
        this.run = new RunTracker();
        this.expected = TextToType.getRandomtxt();
        this.actual = "";

    }
    /// ////I kind of view this as the switchboard for the game process. It triggers appropriate functions to change both the data
    /// and the view in response to the view's event listeners.
    public void delegateKeyEvents(KeyEvent e) {
        e.consume();

        if (e.getEventType().equals(KeyEvent.KEY_PRESSED) || e.getEventType().equals(KeyEvent.KEY_TYPED)) {

            if (run.getKeystrokes() == 0) {
                run.logKeyStroke();
                MainView.startTimer();
            }

            if (e.getCode().equals(KeyCode.BACK_SPACE)) {
                e.consume();
                run.logBackspace();
                run.logKeyStroke();
                actual = (actual.length() == 0) ? "" : actual.substring(0, actual.length() - 1);

            } else if (e.getCharacter().matches("[a-zA-Z|\\s|\\p{P}]")) {
                actual += e.getCharacter();
                run.logKeyStroke();
            }
            drawPane();

            if (expected.equals(actual)) {
                System.out.println("DONE");
                var finaltime = MainView.stopTimerandGetTime();
                var maybeString = promptToSend(finaltime);
                endData = getDataToSend(finaltime, maybeString, run);
                db.sendData(endData);
                resetNewRun(); //so when the user gets back from the popup, they already have a new run loaded!
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

    /// //////////////////////////////////////////////////////////////////////////////////////////////////
    /// these functions deal with interacting with a DatabaseInteractor object we use to access a database.


    public Optional<String> promptToSend(double time) {

        TextInputDialog d = new TextInputDialog("Milkshake");
        d.setContentText("Whoa! You're done! And in only " + new DecimalFormat("0.00").format(time) + " seconds. Enter your name and register your run!");
        Optional<String> maybeName = d.showAndWait();

        return (maybeName);
    }

    public RunData getDataToSend(double time, Optional<String> result, RunTracker tracker) {
        String name = (result.isPresent()) ? result.get() : "Milkshake";

        return (new RunData(time, name, tracker.getKeystrokes(), tracker.getBacktracks(), expected.split(" ").length, expected.length()));
    }

    /** function for rendering the pane in the view based on the current state of the model.
     * this is kind of a two-parter, which isn't ideal...but
     * it also makes it reusable both as a runnable function during the
     * initialization of the view.
     * might not follow SRP??, but it abbreviates things and is easy to understand imo.
     */
    public void drawPane() {
        var data = generateTextData(expected, actual);
        MainView.renderTextData(data, expected);
    }

    public void resetSameRun() {
        run = new RunTracker();
        actual = "";
        drawPane();
        MainView.resetTimer();
    }

    public void resetNewRun() {
        System.out.println("switch");
        this.run = new RunTracker();
        this.actual = "";
        var oldtxt = this.expected;

        while (true) {
            //making sure the new text is different.
            var newtxt = TextToType.getRandomtxt();

            if (!oldtxt.equals(newtxt)) {
                this.expected = newtxt;
                break;
            }
        }
        drawPane();
        MainView.resetTimer();
    }
/// /////////////////////////////////////////////////////////////////////////////

    /**
     * provides formatted strings containing data displayed in the "table"
     * in the Results View.
     * @return
     */
    public List<List<String>> getEntriesasStrings() {

       var dbData = this.db.retrieveLastNEntries(10); //number of rows comfortably supported by window size.
        var all = new ArrayList();

        for (RunData entry: dbData) {

            ArrayList alist = new ArrayList<>();
            alist.add(String.valueOf(Math.floor(entry.time())));
            alist.add(entry.name());
            alist.add(String.valueOf(entry.getWordsPerMinute()));
            alist.add(String.valueOf(entry.getCharsPerSecond()));
            alist.add(String.valueOf(entry.getFaults()));

            all.add(alist.stream().toList());
        }
    return(all.stream().toList());
    }



}





