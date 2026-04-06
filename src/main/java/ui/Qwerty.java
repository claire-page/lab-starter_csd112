package ui;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.text.Font;

import javafx.stage.Stage;

public class Qwerty extends javafx.application.Application {

    private Preferences pref; //will access db for this.
    private Scene mainScene;
    private Scene homeScene;
    private GameScreen gameScene;
    private SettingScene settingScene;
    private Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {

//TODO: HAVE A FUNCTION THAT RETRIEVES USER PREFERENCES.
        Font DEFAULT_FONT = new Font("Consolata", 30);

        TxtColourScheme DEFAULT_SCHEME = new TxtColourScheme("rgb(140, 167, 174)",
                "rgb(16, 31, 36)",
                "rgb(255,79,90)",
                "rgb(175, 64, 53)");

        HomeScreen hs = new HomeScreen();
        this.homeScene = hs.InitializeHomeScene();

        stage.setScene(homeScene);
        stage.getScene().addEventFilter(KeyEvent.ANY,  e -> delegateKeyEvents(e, hs.textpane));

        stage.setWidth(1000);
        stage.setHeight(670);
        stage.setResizable(false);
        stage.show();
    }

    public static void main (String[] args) throws Exception {
        Application.launch(Qwerty.class);
    }

    /**
     * used to direct keyevent to appropriate function...
     * @param keyevent
     * @param pane
     */
    public void delegateKeyEvents(KeyEvent keyevent, ReplaceableTextPane pane){

        if ( keyevent.getEventType().equals(KeyEvent.KEY_PRESSED) && keyevent.getCode().equals(KeyCode.BACK_SPACE) ){
            keyevent.consume();
            pane.backSpace();
        }

boolean isValidText = keyevent.getCharacter().toString().matches("[a-zA-Z |\\p{P}]");
         if ( (keyevent.getEventType().equals(KeyEvent.KEY_TYPED)) && isValidText ){
            keyevent.consume();
            pane.updateForKeyTyped(keyevent.getCharacter());
    }
}

//public Scene loadHomeScene(){
//        return(HomeScene.);
//}
public void swapTo(Scene screen){
        this.mainStage.setScene(screen);
}


}
