package ui;

import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;
import javafx.event.EventHandler;
import java.awt.*;
import java.util.stream.Stream;
import javafx.scene.control.Button;


public class MenuBuilder implements Builder<Region> {

   final Button[] buttons;

    /**
     * constructor makes menubuilder object based on recieved titles.
     * @param buttonTitles
     * @param runnables
     */
    public MenuBuilder(String[] buttonTitles, Runnable[] runnables){
        assert(buttonTitles.length==runnables.length); //there's gotta be a nicer way to do this.

        this.buttons = new Button[buttonTitles.length];

        for (int i = 0; i < buttonTitles.length; i++){
            buttons[i] = new Button(buttonTitles[i]);
            int finalI = i; //making it final so we can do this.
            buttons[i].setOnAction(e -> runnables[finalI].run());
        }

    }

    @Override
    public Region build() {

        VBox left = new VBox();

        for (Button b: this.buttons){
        left.getChildren().add(b);}

        left.setBackground(Background.fill(Style.menuBkgrndPaint));
        left.setPrefHeight(Style.DEFAULT_SCENEHEIGHT);
        left.setPrefWidth(200);
        left.setMaxWidth(200);
        left.setMinWidth(200);
        left.setSpacing(70);
        return(left);
    }
}
