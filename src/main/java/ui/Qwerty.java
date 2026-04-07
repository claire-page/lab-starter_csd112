package ui;

import javafx.application.Application;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.text.Font;

import javafx.stage.Stage;
import org.w3c.dom.Node;
import controllers.UtilityFunctions.*;
public class Qwerty extends javafx.application.Application {

    private Preferences pref; //will access db for this.

    private Node homeScreen;
    private Node settingScreen;
    private Node gameScene;

    private Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {

        this.mainStage = stage;
        Font DEFAULT_FONT = new Font("Consolata", 30);

        TxtColourScheme DEFAULT_SCHEME = new TxtColourScheme("rgb(140, 167, 174)",
                "rgb(16, 31, 36)",
                "rgb(255,79,90)",
                "rgb(175, 64, 53)");


        stage.setScene(new Scene(new BasicLayoutBuilder().build()));
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        Application.launch(Qwerty.class);
    }
}


