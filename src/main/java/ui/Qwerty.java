package ui;

import com.sun.javafx.property.adapter.PropertyDescriptor;
import controllers.Controller;
import core.ReplaceableText;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import java.util.EventListener;

public class Qwerty extends Application {

public Qwerty(){
    super();
}
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qwerty0.fxml"));
        Parent root = fxmlLoader.load();
        var s = (new Scene(root));

        TextArea txt = new ReplaceableText("ABCDEFG HIJ BLAH BLAH");
        VBox v = new VBox(txt);

    }


}
