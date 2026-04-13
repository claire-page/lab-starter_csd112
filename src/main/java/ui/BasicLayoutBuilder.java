package ui;

import controllers.Control;
import core.TextToType;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.util.Builder;

//TODO- TEST!!!
public class BasicLayoutBuilder implements Builder<Parent> {

    public Runnable toSettings;

    Control control;

    public BasicLayoutBuilder(Runnable toSettings, Control ctrl){
        this.toSettings = toSettings;
        this.control = ctrl;
    }

    @Override
    public Parent build() {

        BorderPane bp = new BorderPane();

        String[] HomeMenuOptions = {"Restart", "Settings"};

        Runnable[] Runnables = { toSettings};

        Region left = new MenuBuilder(HomeMenuOptions, Runnables).build();


        Region right = new MainView(control).build();
        bp.setLeft(left);
        bp.setRight(right);
        bp.addEventFilter(KeyEvent.ANY, e -> control.delegateKeyEvents(e));
        return(bp);
    }

}
