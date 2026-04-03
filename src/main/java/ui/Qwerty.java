package ui;

import core.ReplaceableText;
import javafx.application.Application;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.scene.text.Font;

import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//TODO: GET TEXT TO WRAP PROPERLY.

import static controllers.Controller.typeText;
import static javafx.scene.text.Font.font;

public class Qwerty extends javafx.application.Application {

    public Preferences pref;

    @Override
    public void start(Stage stage) throws Exception {
//TODO: HAVE A FUNCTION THAT RETRIEVES USER PREFERENCES.

        Font DEFAULT_FONT = new Font("Consolata", 30);

        TxtColourScheme DEFAULT_SCHEME = new TxtColourScheme("rgb(140, 167, 174)",
                "rgb(16, 31, 36)",
                "rgb(255,79,90)",
                "rgb(175, 64, 53)");

        this.pref = new Preferences(DEFAULT_FONT, DEFAULT_SCHEME);

        stage.setScene(getInitializedHomeScene(pref, stage));
        stage.setWidth(1000);
        stage.setHeight(670);
        stage.setResizable(false);
        stage.show();
    }

    public static void main (String[] args) throws Exception {
        Application.launch(Qwerty.class);
    }

    /**
     * Factory method. sets up according to preference.
     * @param chars
     * @param rt
     * @param pref
     * @return
     */
    public FlowPane initPane( QChar[] chars, ReplaceableText rt, Preferences pref){
///
        FlowPane p = new FlowPane();
        p.setPadding(new Insets(20));

        HBox [] holder = new HBox[rt.getWordCount()];
        for (int i = 0; i < holder.length; i++) {
            holder[i] = new HBox();
        }

        int j = 0;
        for (int k = 0; k < chars.length; k++) {
            var q = chars[k];
            q.setFill(pref.colours().getBlankTextColour());
            q.setFont(pref.font());
            holder[j].getChildren().add(q);
            if ((q.valueOf().equals(" ") || q.valueOf().matches("\\p{P}") || k == chars.length-1)){ //we reach punctuation or we end up at the end...
                p.getChildren().add(holder[j]);
                j+=1;
                //adding hBox.
            }
        }
        p.setFocusTraversable(true);
        p.setBackground(Background.fill(Defaults.DEFAULT_TXT_BKGRND));
        return(p);
    }

    /**
     * returns initialized scene
     * @param pref
     * @return
     */

    public Scene getInitializedGameScene(Preferences pref, Stage stage){

        //TODO: have api fetch random words. for now we can just test with a custom paragraph.
        Text instruction = new Text("-START TYPING TO START THE TIMER-");
        ReplaceableText text = new ReplaceableText("'There's no use,' she said. 'I already sent the maids to clean up her room. All of her effects have been incinerated.' She seemed mildly irked that we were even asking about it, and eager to get on with the rest of her day. Before any of us could react, Therese thanked her and grabbed us by the arm, dragging us sputtering down the marble hall and back into the sunlight.I blinked in the harsh light and rubbed my arm gingerly. I kept forgetting how strong her grip was.'So what now?'");

        QChar[] qChars = new QChar[text.getText().length()];

        var splitText = text.getText().split("");

        for (int i= 0; i< splitText.length; i++){
            qChars[i] = new QChar(splitText[i]);
        }
        var pane = initPane(qChars, text, pref ); //initializing textpane with qChars.

        VBox v = new VBox();
        Button restartBtn = new Button("_Restart");
        restartBtn.setFont(Defaults.DEFAULT_MENU_FONT);
        restartBtn.setBackground(Background.fill(Defaults.DEFAULT_ACCENT_1));

        restartBtn.setPadding(new Insets(5));

        Button quitBtn = new Button("_Quit");
        quitBtn.setFont(Defaults.DEFAULT_MENU_FONT);
        quitBtn.setBackground(Background.fill(Defaults.DEFAULT_ACCENT_1));
        quitBtn.setPadding(new Insets(5));

        EventHandler<MouseEvent> handler1 = e -> {
            stage.setScene(getInitializedHomeScene(pref, stage));
        };
        quitBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, handler1);

        Label timerDisplay = new Label("");

        //TODO: GET TIMER TO DISPLAY ACCURATE VALUE...

        v.getChildren().add(restartBtn);
        v.getChildren().add(quitBtn);
        v.setPrefHeight(Defaults.DEFAULT_SCENEHEIGHT);
        v.setPrefWidth(250);
        v.setPadding(new Insets(10));
        v.setSpacing(100);
        v.setBackground(Background.fill(Defaults.DEFAULT_ACCENT_1));

        HBox h = new HBox();
        h.getChildren().addAll(v, pane, timerDisplay);
        Scene s = new Scene(h, 1000, 670);
        s.setFill(Defaults.DEFAULT_BKGROUND);

        EventHandler<KeyEvent> handler = event -> typeText(event, text, pref, qChars);
        s.addEventFilter(KeyEvent.ANY, handler);

        return(s);
    }

//have two methods repeating similar steps.
    public Scene getInitializedHomeScene(Preferences pref, Stage stage){


        //INITIALIZING LEFT SIDE OF SCREEN... (MENU)
        VBox v = new VBox();
        Button newGame = new Button("_Play");
        newGame.setPadding(new Insets(100, 5, 5, 25));
        newGame.setFont(font("Consolas", FontWeight.BOLD, 24));

        newGame.setTextFill(Defaults.DEFAULT_TXT_BKGRND);
        newGame.setBackground(Background.fill(Defaults.DEFAULT_ACCENT_1));

        Button settings = new Button("_Settings");
        settings.setPadding(new Insets(5, 5, 5, 20));
        settings.setFont(Defaults.DEFAULT_MENU_FONT);
        settings.setTextFill(Defaults.DEFAULT_TXT_BKGRND);
        settings.setBackground(Background.fill(Defaults.DEFAULT_ACCENT_1));

        Button about = new Button("_About");
        about.setPadding(new Insets(5, 5, 5, 20));
        about.setFont(Defaults.DEFAULT_MENU_FONT);
        about.setTextFill(Defaults.DEFAULT_TXT_BKGRND);
        about.setBackground(Background.fill(Defaults.DEFAULT_ACCENT_1));

        v.getChildren().add(newGame);
        v.getChildren().add(settings);
        v.getChildren().add(about);
        v.setPrefHeight(Defaults.DEFAULT_SCENEHEIGHT);
        v.setPrefWidth(200);
        v.setMaxWidth(200);
        v.setMinWidth(200);
        v.setSpacing(70);
        v.setBackground(Background.fill(Defaults.DEFAULT_ACCENT_1));

        //INITIALIZING CONTENT FOR RIGHT SIDE
        Font bigFont = font("Courier New", 80);
        Label titletext = new Label("Q W E R T Y");
        titletext.setTextFill(Defaults.DEFAULT_CONTRAST_ACCENT_LT);
        titletext.setFont(bigFont);
        titletext.setPadding(new Insets(50, 0, 0, 0));
        titletext.setAlignment(Pos.BASELINE_LEFT);

        Label bottomtitle = new Label(" A JavaFX app for CSD112-26W");
        bottomtitle.setBackground(Background.fill(Defaults.DEFAULT_CONTRAST_ACCENT_DK));
        bottomtitle.setFont(new Font("Consolas", 18));
        bottomtitle.setTextFill(Defaults.DEFAULT_CONTRAST_LIGHT_RED);
        bottomtitle.setAlignment(Pos.BASELINE_LEFT);
        bottomtitle.setPrefWidth(750);
        bottomtitle.setPrefHeight(40);

        //SETTING UP REPLACEABLE TEXT (ALWAYS THE SAME FOR HOME SCREEN.)
        ReplaceableText text = new ReplaceableText("Welcome to this type test! Lorem ipsum blah blah blah...texty texty texty....ajgflweafljewbfaerjhferwlafhere! More text, even more text. Words and nouns and adjectives. TEXTYDSYTUFGIUHIHDIHLISD");

        QChar[] qChars = new QChar[text.getText().length()];

        var splitText = text.getText().split("");

        for (int i= 0; i< splitText.length; i++){
            qChars[i] = new QChar(splitText[i]);
        }

        //INITIALIZING CUSTOM FLOWPANE CONTAINING QCHARS
        var txtpane = initPane(qChars, text, pref );

        //INITIALIZING PANE FOR RIGHT SIDE
        VBox rightpane = new VBox();
        rightpane.setSpacing(20);
        rightpane.setPrefHeight(Defaults.DEFAULT_SCENEHEIGHT);
        rightpane.getChildren().addAll(titletext, bottomtitle, txtpane);
        rightpane.setPadding(new Insets(0, 40, 0, 40));

        //PUTTING IT ALL IN A BORDERPANE.
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(v);
        BorderPane.setAlignment(titletext,Pos.BASELINE_LEFT );
        borderPane.setCenter(rightpane);
        borderPane.setBackground(Background.fill((Defaults.DEFAULT_BKGROUND)));
        Scene s = new Scene(borderPane, 1000, 670);
        s.setFill(Defaults.DEFAULT_BKGROUND);

        //ADDING EVENT HANDLERS---
        //this has to be added at the scene level...
        EventHandler<KeyEvent> handler = event -> typeText(event, text, pref, qChars);
        s.addEventFilter(KeyEvent.ANY, handler);

        //button clicks.
        EventHandler<MouseEvent> handler0 =  event -> stage.setScene(getInitializedGameScene(pref, stage));
        newGame.addEventHandler(MouseEvent.MOUSE_CLICKED, handler0);


        return(s);
    }

}
