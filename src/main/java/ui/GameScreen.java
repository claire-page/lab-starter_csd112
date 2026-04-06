package ui;

import core.ReplaceableText;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import static ui.BasicLayout.customComponents.initPane;

public class GameScreen {

    Runnable handleOnQuitPressed;
    Runnable setOnRestartPressed;
    Runnable handleKeysPressed;
    Button quitBtn;


    public Scene getInitializedGameScene(ui.Preferences pref, Runnable handleOnQuitPressed, Runnable setOnRestartPressed, Runnable handleKeysPressed ){

        //TODO: have api fetch random words. for now we can just test with a custom paragraph.
        Text instruction = new Text("-START TYPING TO START THE TIMER-");
        ReplaceableText text = new ReplaceableText("'There's no use,' she said. 'I already sent the maids to clean up her room. All of her effects have been incinerated.' She seemed mildly irked that we were even asking about it, and eager to get on with the rest of her day. Before any of us could react, Therese thanked her and grabbed us by the arm, dragging us sputtering down the marble hall and back into the sunlight.I blinked in the harsh light and rubbed my arm gingerly. I kept forgetting how strong her grip was.'So what now?'");

        QChar[] qChars = new QChar[text.getText().length()];

        var splitText = text.getText().split("");

        for (int i= 0; i< splitText.length; i++){
            qChars[i] = new QChar(splitText[i]);
        }
        var pane = initPane(qChars, text, pref ); //initializing textpane with qChars.

        VBox v = new VBox();
        Button restartBtn = new Button("_Restart");
        restartBtn.setFont(Style.DEFAULT_MENU_FONT);
        restartBtn.setBackground(Background.fill(Style.menuBkgrndPaint));

        restartBtn.setPadding(new Insets(5));

        Button quitBtn = new Button("_Quit");
        quitBtn.setFont(Style.DEFAULT_MENU_FONT);
        quitBtn.setBackground(Background.fill(Style.menuBkgrndPaint));
        quitBtn.setPadding(new Insets(5));
        quitBtn.setOnAction(_-> {
            handleOnQuitPressed.run();
        });
        /**
         * we will provide a function to be called as an arg to the constructor of the gamescene.
         * and exactly what gets done will be specified somewhere ele
         */

        Label timerDisplay = new Label("");

        //TODO: GET TIMER TO DISPLAY ACCURATE VALUE...

        v.getChildren().add(restartBtn);
        v.getChildren().add(quitBtn);
        v.setPrefHeight(Style.DEFAULT_SCENEHEIGHT);
        v.setPrefWidth(250);
        v.setPadding(new Insets(10));
        v.setSpacing(100);
        v.setBackground(Background.fill(Style.menuBkgrndPaint));

        HBox h = new HBox();
        h.getChildren().addAll(v, pane, timerDisplay);
        Scene s = new Scene(h, 1000, 670);
        s.setFill(Style.mainBkgrndPaint);

        s.addEventFilter(KeyEvent.ANY, e -> handleKeysPressed.run());

        return(s);
    }


}
