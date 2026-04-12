package ui;

import javafx.scene.layout.*;
import javafx.util.Builder;

//TODO- TEST!!!
public class BasicLayoutBuilder implements Builder<BorderPane> {

    public Runnable toHome;
    public Runnable toGame;

    public BasicLayoutBuilder(Runnable toHome, Runnable toGame){
        this.toHome = toHome;
        this.toGame = toGame;
    }

    @Override
    public BorderPane build() {

        BorderPane bp = new BorderPane();

        String[] HomeMenuOptions = {"home", "game"};

        Runnable[] Runnables = { toHome, toGame};

        Region left = new MenuBuilder(HomeMenuOptions, Runnables).build();
        bp.setLeft(left);
        return(bp);
    }


}
