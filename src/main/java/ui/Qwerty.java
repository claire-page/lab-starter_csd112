package ui;

import controllers.Control;
import core.TextToType;
import javafx.application.Application;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.text.Font;

import javafx.stage.Stage;
import org.w3c.dom.Node;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Qwerty extends Application {

    private Preferences pref; //will access db for this.

    private Node homeScreen;
    private Node settingScreen;
    private Parent mainScreen;

    private Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {

    //instantiating controller.

        Control controller = new Control(new TextToType().getRandomtxt());
        this.mainStage = stage;

        Parent homeScreen = new BasicLayoutBuilder( () -> System.out.println("tosettings!!"), controller).build();

        Scene starterScene = new Scene(homeScreen, Style.DEFAULT_SCENEWIDTH, Style.DEFAULT_SCENEHEIGHT);
        stage.setScene(starterScene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        Application.launch(Qwerty.class);
    }

    public void showResultsinPopup(){
        A
    }

}


