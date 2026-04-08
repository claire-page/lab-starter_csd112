package core;

import javafx.animation.AnimationTimer;

public class RunTracker {

    private int backtracks;
    private int keystrokes;

    //no constructor provided because everything gets initialized to zero anyway.

    public void incBackTracks(){
        this.backtracks+=1;

    }

    public void incKeyStrokes(){
        this.keystrokes+=1;

    }


}