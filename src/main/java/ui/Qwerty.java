package ui;

import core.ReplaceableText;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static controllers.Controller.typeText;

public class Qwerty extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws Exception {
        var txt = new ReplaceableText("Welcome to this type test! Isn't this cool?");

        HBox p = new HBox();
        Font font = new Font("Lucida Console", 40);
        TxtColour.initColor(txt); //initializing all to gray.
        for (Text q: txt.getChars()){
            q.setFont(font);
            p.getChildren().add(q);
        }
        p.setLayoutX(100);
        p.setLayoutY(100);
        p.setFocusTraversable(true);
        p.setPrefSize(700, 700);
        Scene s = new Scene(p, 800, 800);
        EventHandler<KeyEvent> handler = event -> typeText(event, txt);
        s.addEventFilter(KeyEvent.ANY, handler);
//        s.addEventFilter(KeyEvent.KEY_PRESSED, handler2);
        stage.setScene(s);
        stage.show();

    }

    public static void main (String[] args) throws Exception {
        Application.launch(Qwerty.class);
    }



}
