package ui;

import controllers.Control;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Builder;

//TODO- TEST!!!
public class BasicLayoutBuilder implements Builder<Parent> {

    static Control control;
    static Region results;
    static Region main;

    public BasicLayoutBuilder(Control ctrl){
        this.control = ctrl;

    }

    @Override
    public Parent build() {
        BorderPane bp = new BorderPane();
        String[] HomeMenuOptions = {"View Results"};

        Runnable[] Runnables = {BasicLayoutBuilder::hideMain};

        Region left = new MenuBuilder(HomeMenuOptions, Runnables).build();

        StackPane stack = new StackPane();
        this.main = new MainView(control::initPane, control::resetNewRun,  control::resetSameRun).build();
        this.results = new ResultScreen(control::getEntriesasStrings, BasicLayoutBuilder::showMain).build();
        stack.getChildren().addAll(results, main);
        bp.setLeft(left);
        bp.setRight(stack);
        bp.addEventFilter(KeyEvent.ANY, e -> control.delegateKeyEvents(e));
        bp.setBackground(Background.fill(Color.WHITE));
        return(bp);
    }

    public static void hideMain(){

        if (main.isVisible()){
            main.setVisible(false);
            control.resetSameRun(); //making sure it's reset when the user returns.
        }

    }

    public static void showMain(){
        if (!main.isVisible()){
            main.setVisible(true);
        }
    }
}
