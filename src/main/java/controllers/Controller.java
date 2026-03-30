package controllers;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Controller {


    @FXML
    private static TextField typeableText;


    public static void replaceText(KeyEvent e){
typeableText.setText(e.getText());
    }
}