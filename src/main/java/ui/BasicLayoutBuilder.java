package ui;

import core.ReplaceableText;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Builder;
import controllers.UtilityFunctions;

//TODO- TEST!!!
public class BasicLayoutBuilder implements Builder<BorderPane> {


    @Override
    public BorderPane build() {

        BorderPane bp = new BorderPane();

        String[] HomeMenuOptions = {"PRESS THIS", "DO NOT PRESS THIS"};

        Runnable[] Runnables = {new Runnable() {
            @Override
            public void run() {
                System.out.println("BLAH! you pressed it!");
            }
        }, new Runnable() {
            @Override
            public void run() {
                System.out.println("thou shouldst not have pressed it...");

            }
        }
        };

        Region left = new MenuBuilder(HomeMenuOptions, Runnables).build();
        var replaceableText = new ReplaceableTextPane("blah blah blah...wonder if this will work.");

        Node[] nodes = {new Text( "here is some text"), replaceableText} ;
        Region right = new ContentPaneBuilder(nodes).build();

        bp.setLeft(left);
        bp.setRight(right);
        bp.addEventFilter(KeyEvent.ANY,  e -> UtilityFunctions.delegateKeyEvents(e, replaceableText));
        return(bp);
    }

}
