package ui;

import controllers.HelperFunctions;
import core.RunData;
import core.RunTracker;
import javafx.animation.AnimationTimer;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

import java.util.function.Consumer;

import static controllers.HelperFunctions.*;

public class GameScreen {

    Runnable quitBtn;
    Runnable restartBtn;
    Consumer<RunData> onDone; //will try and use a callback to get popup when game is done????? idk.

    public GameScreen(Runnable quit, Runnable restart ) {
        this.quitBtn = quit;
        this.restartBtn = restart;
    }

    //initializes Game Scene.
    public Parent build() {

        RunTracker tracker = new RunTracker(); //so we can track what happens during the run.

        var pane = new ReplaceableTextPane("'So what now?'"); //getting text pane...
        pane.setPrefSize(300, 300);

        var ButtonTitles = new String[]{"Quit"};
        Runnable[] runnables = {quitBtn, restartBtn};
        Region left = new MenuBuilder(ButtonTitles, runnables).build();

        Label timerDisplay = new Label("TIMER IS HERE");
        timerDisplay.setPadding(new Insets(50));

        //getting the timer set up.
        AnimationTimer timer = new AnimationTimer() {

            private long startStamp = -1; //initializing at -1.

            @Override
            public void handle(long l) {
                if (startStamp ==-1){ //if first time being called, update to last time.
                    startStamp = l;}
                    var elapsed = (l- startStamp )/1000000000.0;
                    timerDisplay.setText(elapsed + "s".formatted());
            }
        };

        VBox right = new VBox();
        right.getChildren().addAll(timerDisplay,pane);

        //put it all into a pane.
        BorderPane bp = new BorderPane();
        bp.setLeft(left);
        bp.setCenter(right);

        //--PANE-LEVEL EVENT FILTERS--
        //setting up listener to be triggered on first key press (to start the timer), which will then remove itself,
        //ensuring its handler is only triggered once.
        bp.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                timer.start();
                System.out.println("timer started");
                bp.removeEventFilter(KeyEvent.KEY_PRESSED, this); //"self-destructs" after one key click.
                //not consuming the event, still want the keystroke to be picked up by the other handler.
            }});

        bp.addEventFilter(KeyEvent.ANY, e -> delegateKeyEvents(e, pane, tracker, timer));
        return(bp);
    }

    //maybe have a runtracker in here?????
    //checks the runtracker.:
    //pass a tracker.
    //while it's active{
    // do nothing.
    // }{
    // show the popup with tracker data.
    // button to send data off.
    //here's where we should probably start to think about database saving.
    //
    // }

    //todo- some questions.. pick whichever ones seems urgent?
    ///menu? this ok?
    ///how to handle game? pro tips?
    ///



    }


