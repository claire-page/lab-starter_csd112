package core;

import javafx.animation.AnimationTimer;

/// keeps track of the state of the run.
///
public class RunTracker {

    int backtracks;
    int keystrokes;
    public String actual;
    public String expected;


    public RunTracker() {
        this.actual= "";
    }

    public void logBackspace(){
        this.backtracks+=1;
    }

    public void logKeyStroke(){
        this.keystrokes +=1;
    }

    public String getActual() {
        return actual;
    }

    public int getKeystrokes() {
        return keystrokes;
    }

    public int getBacktracks() {
        return backtracks;
    }
}

