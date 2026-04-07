package ui;

import controllers.UtilityFunctions;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

import java.awt.*;

public class GameScreenBuilder implements Builder<Parent> {

    Runnable quitBtn;
    Runnable restartBtn;

    public GameScreenBuilder(Runnable quit, Runnable restart) {
        this.quitBtn = quit;
        this.restartBtn = restart;

    }

    @Override
    public Parent build() {

        var pane = new ReplaceableTextPane("'There's no use,' she said. 'I already sent the maids to clean up her room. All of her effects have been incinerated.' She seemed mildly irked that we were even asking about it, and eager to get on with the rest of her day. Before any of us could react, Therese thanked her and grabbed us by the arm, dragging us sputtering down the marble hall and back into the sunlight.I blinked in the harsh light and rubbed my arm gingerly. I kept forgetting how strong her grip was.'So what now?'");
        pane.setPrefSize(300, 300);
        var ButtonTitles = new String[]{"Quit"};
        Runnable[] runnables = {quitBtn, restartBtn};
        Region left = new MenuBuilder(ButtonTitles, runnables).build();

        Label timerDisplay = new Label("TIMER IS HERE");
        timerDisplay.setPadding(new Insets(50));

        //TODO: GET TIMER TO DISPLAY ACCURATE VALUE...

        VBox right = new VBox();
        right.getChildren().addAll(timerDisplay,pane);

        BorderPane bp = new BorderPane();
        bp.setLeft(left);
        bp.setCenter(right);
        bp.addEventFilter(KeyEvent.ANY, e -> UtilityFunctions.delegateKeyEvents(e, pane));

        return(bp);
    }


    }


