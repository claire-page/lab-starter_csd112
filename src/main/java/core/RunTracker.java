package core;

import javafx.animation.AnimationTimer;
//TODO: hm. this is modifiable.
public class RunTracker {

//this only keeps tracks of the backtracks and keystrokes.
    //maybe move this and make a whole class for a Run?
    //DATA:
    //active (boolean).
    //time.
    //update time when called
    //use animationtimer in ui to grab this.
    //would send keyevents, too...
    //ask for help honestly I am a bit confused.

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