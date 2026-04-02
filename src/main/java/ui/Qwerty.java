package ui;

import core.ReplaceableText;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.text.Font;

import javafx.scene.text.Text;
import javafx.stage.Stage;

//TODO: GET TEXT TO WRAP PROPERLY.

import java.util.Timer;

import static controllers.Controller.typeText;

public class Qwerty extends javafx.application.Application {

    public Preferences pref;

    @Override
    public void start(Stage stage) throws Exception {
//TODO: HAVE A FUNCTION THAT RETRIEVES USER PREFERENCES.

        Font DEFAULT_FONT = new Font("Consolata", 30);

        TxtColourScheme DEFAULT_SCHEME = new TxtColourScheme("rgb(160,174,188)",
                "rgb(67,97,117)",
                "rgb(126, 25, 27)",
                "rgb(175, 64, 53)");

        this.pref = new Preferences(DEFAULT_FONT, DEFAULT_SCHEME);

        stage.setScene(getInitializedHomeScene(pref, stage));
        stage.show();
    }

    public static void main (String[] args) throws Exception {
        Application.launch(Qwerty.class);
    }

    /**
     * Factory method. sets up according to preference.
     * @param chars
     * @param rt
     * @param pref
     * @return
     */
    public FlowPane initPane( QChar[] chars, ReplaceableText rt, Preferences pref){

        FlowPane p = new FlowPane();
        p.setPadding(new Insets(70));
        p.setPrefSize(500,        Defaults.DEFAULT_PANEWIDTH);

        HBox [] holder = new HBox[rt.getWordCount()];
        for (int i = 0; i< holder.length; i++) {
            holder[i] = new HBox();
        }
        int j = 0;
        for (int k = 0; k < chars.length; k++) {
            var q = chars[k];
            q.setFill(pref.colours().getBlankTextColour());
            q.setFont(pref.font());
            holder[j].getChildren().add(q);
            if (q.valueOf().equals(" ") || q.valueOf().matches("\\p{P}") || k == chars.length-1){ //we reach punctuation or we end up at the end...
                p.getChildren().add(holder[j]); //adding hBox.
                j+=1;
            }
        }
        return(p);
    }

    /**
     * returns initialized scene
     * @param pref
     * @return
     */



    public Scene getInitializedGameScene(Preferences pref, Stage stage){

        //TODO: have api fetch random words. for now we can just test with a custom paragraph.
        Text instruction = new Text("-START TYPING TO START THE TIMER-");
        ReplaceableText text = new ReplaceableText("'There's no use,' she said. 'I already sent the maids to clean up her room. All of her effects have been incinerated.' She seemed mildly irked that we were even asking about it, and eager to get on with the rest of her day. Before any of us could react, Therese thanked her and grabbed us by the arm, dragging us sputtering down the marble hall and back into the sunlight.I blinked in the harsh light and rubbed my arm gingerly. I kept forgetting how strong her grip was.'So what now?'");

        QChar[] qChars = new QChar[text.getText().length()];

        var splitText = text.getText().split("");

        for (int i= 0; i< splitText.length; i++){
            qChars[i] = new QChar(splitText[i]);
        }
        var pane = initPane(qChars, text, pref ); //initializing textpane with qChars.
        pane.setFocusTraversable(true);

        VBox v = new VBox();
        Button restartBtn = new Button("Restart");
        restartBtn.setPadding(new Insets(5));

        Button quitBtn = new Button("Quit");
        quitBtn.setPadding(new Insets(5));
        EventHandler<MouseEvent> handler1 = e -> {
            stage.setScene(getInitializedHomeScene(pref, stage));
        };
        quitBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, handler1);

        Label timerDisplay = new Label("");

        //TODO: GET TIMER TO DISPLAY ACCURATE VALUE...
//

        v.getChildren().add(restartBtn);
        v.getChildren().add(quitBtn);
        v.setPrefHeight(Defaults.DEFAULT_SCENEHEIGHT);
        v.setPadding(new Insets(10));
        v.setSpacing(100);

        HBox h = new HBox();
        h.getChildren().addAll(v, pane, timerDisplay);
        Scene s = new Scene(h, 900, 900);

        EventHandler<KeyEvent> handler = event -> typeText(event, text, pref, qChars);
        s.addEventFilter(KeyEvent.ANY, handler);

        return(s);
    }

//have two methods repeating similar steps.
    public Scene getInitializedHomeScene(Preferences pref, Stage stage){
        ReplaceableText text = new ReplaceableText("Welcome to this type test! Lorem ipsum blah blah blah...texty texty texty....ajgflweafljewbfaerjhferwlafhere!");
        System.out.println(text.toString().length());

        QChar[] qChars = new QChar[text.getText().length()];

        var splitText = text.getText().split("");

        for (int i= 0; i< splitText.length; i++){
            qChars[i] = new QChar(splitText[i]);
        }
        var pane = initPane(qChars, text, pref );
        pane.setFocusTraversable(true);

        VBox v = new VBox();
        Button newGame = new Button("Play");
        newGame.setPadding(new Insets(5));

        EventHandler<MouseEvent> handler0 =  event -> stage.setScene(getInitializedGameScene(pref, stage));
        newGame.addEventHandler(MouseEvent.MOUSE_CLICKED, handler0);


        Button settings = new Button("Settings");
        settings.setPadding(new Insets(5));
        Button about = new Button("About");
        about.setPadding(new Insets(5));


        v.getChildren().add(newGame);
        v.getChildren().add(settings);
        v.getChildren().add(about);
        v.setPrefHeight(Defaults.DEFAULT_SCENEHEIGHT);
        v.setMaxWidth(Defaults.DEFAULT_PANEWIDTH);
        v.setPadding(new Insets(10));
        v.setSpacing(100);
        HBox h = new HBox();
        h.getChildren().addAll(v, pane);
        Scene s = new Scene(h, 900, 900);
        EventHandler<KeyEvent> handler = event -> typeText(event, text, pref, qChars);
        s.addEventFilter(KeyEvent.ANY, handler);

        return(s);
    }

}
