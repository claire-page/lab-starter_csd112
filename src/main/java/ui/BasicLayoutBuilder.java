package ui;

import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Builder;
import controllers.HelperFunctions;

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
        var replaceableText = new ReplaceableTextPane("blah blah blah...wonder if this will work.");

        Node[] nodes = {new Text( "here is some text"), replaceableText} ;
        Region right = new ContentPaneBuilder(nodes).build();

        bp.setLeft(left);
        bp.setRight(right);
        bp.addEventFilter(KeyEvent.ANY,  e -> HelperFunctions.delegateKeyEvents(e, replaceableText));
        return(bp);
    }

}
