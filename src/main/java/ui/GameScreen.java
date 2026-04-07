package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GameScreen {


    Button quitBtn;
    Button restartBtn;


    public BorderPane getInitializedGameScene(){

        //TODO: have api fetch random words. for now we can just test with a custom paragraph.

        var pane = new ReplaceableTextPane("'There's no use,' she said. 'I already sent the maids to clean up her room. All of her effects have been incinerated.' She seemed mildly irked that we were even asking about it, and eager to get on with the rest of her day. Before any of us could react, Therese thanked her and grabbed us by the arm, dragging us sputtering down the marble hall and back into the sunlight.I blinked in the harsh light and rubbed my arm gingerly. I kept forgetting how strong her grip was.'So what now?'"); //initializing textpane with qChars.

        VBox v = new VBox();

        this.restartBtn = new Button("_Restart");
        restartBtn.setFont(Style.DEFAULT_MENU_FONT);
        restartBtn.setBackground(Background.fill(Style.menuBkgrndPaint));
        restartBtn.setPadding(new Insets(5));

        this.quitBtn = new Button("_Quit");
        quitBtn.setFont(Style.DEFAULT_MENU_FONT);
        quitBtn.setBackground(Background.fill(Style.menuBkgrndPaint));
        quitBtn.setPadding(new Insets(5));


        Label timerDisplay = new Label("TIMER IS HERE");

        //TODO: GET TIMER TO DISPLAY ACCURATE VALUE...

        v.getChildren().add(restartBtn);
        v.getChildren().add(quitBtn);
        v.setPrefHeight(Style.DEFAULT_SCENEHEIGHT);
        v.setPrefWidth(250);
        v.setPadding(new Insets(10));
        v.setSpacing(100);
        v.setBackground(Background.fill(Style.menuBkgrndPaint));

        VBox right = new VBox();
        right.getChildren().addAll(timerDisplay,pane);

        BorderPane bp = new BasicLayoutBuilder().build();

        bp.setLeft(v);
        bp.setRight(right);

        return(bp);
    }

    public void passToButtons(Runnable handleOnQuitPressed, Runnable setOnRestartPressed){
//        this.quitBtn.setOnAction(e -> handleOnQuitPressed.run());
//        this.restartBtn.setOnAction(e -> c); //TODO: have this restart timer and reset text...
        this.restartBtn.setOnAction( e-> setOnRestartPressed.run());

    }


}
