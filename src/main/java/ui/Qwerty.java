package ui;

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
    private Parent gameScreen;

    private Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {

        this.mainStage = stage;
        Font DEFAULT_FONT = new Font("Consolata", 30);

        //this will be a Style object. Just haven't switched over to it yet.
        TxtColourScheme DEFAULT_SCHEME = new TxtColourScheme(
                "rgb(140, 167, 174)",
                "rgb(16, 31, 36)",
                "rgb(255,79,90)",
                "rgb(175, 64, 53)"
        );

        Parent homeScreen = new HomeScreenBuilder(() -> stage.getScene().setRoot(gameScreen)).build();
        gameScreen = new GameScreen(()-> stage.getScene().setRoot(homeScreen), ()-> System.out.println("blablabla")).build();

        Scene starterScene = new Scene(homeScreen, Style.DEFAULT_SCENEWIDTH, Style.DEFAULT_SCENEHEIGHT);
        stage.setScene(starterScene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        Application.launch(Qwerty.class);
    }

    public void swapRoot(Node n){

    }
}


