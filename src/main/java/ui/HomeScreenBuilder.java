package ui;

import controllers.HelperFunctions;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Builder;

import java.beans.EventHandler;

import static javafx.scene.text.Font.font;

public class HomeScreenBuilder implements Builder<Parent>{

    Runnable onGamePressed;
//    Runnable onSettings;


    public HomeScreenBuilder (Runnable onGame ){
        this.onGamePressed = onGame;
//       this.onSettings = onSettings;

    }

    @Override
    public Parent build() {

        String[] btnNames = {"Play"};
        Runnable[] runnables = {onGamePressed};

        Region left = new MenuBuilder(btnNames, runnables).build();

        Font bigFont = font("Courier New", 80);
        Label titletext = new Label("Q W E R T Y");
        titletext.setTextFill(Style.accentPaint);
        titletext.setFont(bigFont);
        titletext.setPadding(new Insets(50, 0, 0, 0));
        titletext.setAlignment(Pos.BASELINE_LEFT);

        Label bottomtitle = new Label(" A JavaFX app for CSD112-26W");
        bottomtitle.setBackground(Background.fill(Style.titleTextPaint));
        bottomtitle.setFont(new Font("Consolas", 18));
        bottomtitle.setTextFill(Style.accentPaint);
        bottomtitle.setAlignment(Pos.BASELINE_LEFT);
        bottomtitle.setPrefWidth(750);
        bottomtitle.setPrefHeight(40);

        //SETTING UP REPLACEABLE TEXT (ALWAYS THE SAME FOR HOME SCREEN.)
        String s = ("Bottom text");
        var txtpane = new ReplaceableTextPane(s);

        VBox rightpane = new VBox();
        rightpane.setSpacing(20);
        rightpane.setPrefHeight(Style.DEFAULT_SCENEHEIGHT);
        rightpane.getChildren().addAll(titletext, bottomtitle, txtpane);
        rightpane.setPadding(new Insets(0, 40, 0, 40));

//put it all in a borderpane...

        BorderPane bp = new BorderPane();
        bp.setLeft(left);
        bp.setAlignment(titletext,Pos.BASELINE_LEFT);
        bp.setCenter(rightpane);
        bp.setBackground(Background.fill((Style.mainBkgrndPaint)));
        bp.addEventFilter(KeyEvent.ANY, e -> HelperFunctions.delegateKeyEvents(e, txtpane));

    //TODO- when game is over: end screen showing run data pops up, can save with name (default provided).


        return(bp);
    }

}



//showpane()