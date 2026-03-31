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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import java.util.EventListener;

public class Qwerty extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws Exception {
        Text txt = new ReplaceableText("ABCDEFG HIJ BLAH BLAH");
        txt.setFill(Paint.valueOf(TxtColour.Gray.paintString));
        Pane p = new Pane();
        p.getChildren().add(txt);
        p.setPrefSize(700, 700);
        stage.setScene(new Scene(p, 800, 800));
        stage.show();
    }





    public static void main (String[] args) throws Exception {
        Application.launch(Qwerty.class);
    }

}
