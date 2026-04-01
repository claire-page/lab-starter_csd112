package ui;

import core.ReplaceableText;
import javafx.application.Application;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

//TODO: GET TEXT TO WRAP PROPERLY.

import static controllers.Controller.typeText;

public class Qwerty extends javafx.application.Application {

    public Preferences pref;

    @Override
    public void start(Stage stage) throws Exception {
         //will take these out.


        Font DEFAULT_FONT = new Font("Courier New", 20);

        TxtColourScheme DEFAULT_SCHEME = new TxtColourScheme("rgb(160,174,188)",
                "rgb(67,97,117)",
                "rgb(126, 25, 27)",
                "rgb(175, 64, 53)");


        this.pref = new Preferences(DEFAULT_FONT, DEFAULT_SCHEME);

        //addQChars method.
        ReplaceableText text = new ReplaceableText("Welcome to this type test! Isn't this cool? Blah blah blahhh, blah de blah de blah......yeah. i need a caret thing mayhaps kahsdjgfa;wga kjahf ajhdfhksgfkg akegfhgsrglrehgtlerwagtersjg");
       QChar[] qChars = new QChar[text.getText().length()];

       var splitText = text.getText().split("");

       for (int i= 0; i< splitText.length; i++){
           qChars[i] = new QChar(splitText[i]);
       }
       var pane = initPane(qChars, text, pref );

        pane.setFocusTraversable(true);

        Scene s = new Scene(pane, 900, 900);
        EventHandler<KeyEvent> handler = event -> typeText(event, text, pref, qChars);
        s.addEventFilter(KeyEvent.ANY, handler);

        stage.setScene(s);
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
        p.setPadding(new Insets(100));
        p.setPrefSize(500, 500);
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
            if (q.valueOf().matches("\\p{P}") || k == chars.length-1){ //we reach punctuation or we end up at the end...
                p.getChildren().add(holder[j]); //adding hBox.
                j+=1;
            }
        }

        return(p);
    }



}
