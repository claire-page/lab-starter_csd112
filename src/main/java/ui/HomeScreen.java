package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Builder;

import static javafx.scene.text.Font.font;

public class HomeScreen implements Builder<Node>{

    ReplaceableTextPane textpane;

    Button aboutBtn;
    Button playBtn;
    Button settingsBtn;
    Scene scene;
    ReplaceableTextPane txtPane;

    public BorderPane InitializeHomePane(){

        //INITIALIZING LEFT SIDE OF SCREEN... (MENU)

        VBox v = new VBox();
        this.playBtn = new Button("_Play");
        playBtn.setPadding(new Insets(100, 5, 5, 25));
        playBtn.setFont(font("Consolas", FontWeight.BOLD, 24));
        playBtn.setTextFill(Style.textBkgrndPaint);
        playBtn.setBackground(Background.fill(Style.menuBkgrndPaint));

        this.settingsBtn = new Button("_Settings");
        settingsBtn.setPadding(new Insets(5, 5, 5, 20));
        settingsBtn.setFont(Style.DEFAULT_MENU_FONT);
        settingsBtn.setTextFill(Style.textBkgrndPaint);
        settingsBtn.setBackground(Background.fill(Style.menuBkgrndPaint));

        aboutBtn = new Button("_About");
        aboutBtn.setPadding(new Insets(5, 5, 5, 20));
        aboutBtn.setFont(Style.DEFAULT_MENU_FONT);
        aboutBtn.setTextFill(Style.textBkgrndPaint);
        aboutBtn.setBackground(Background.fill(Style.menuBkgrndPaint));


        v.getChildren().add(playBtn);
        v.getChildren().add(settingsBtn);
        v.getChildren().add(aboutBtn);
        v.setPrefHeight(Style.DEFAULT_SCENEHEIGHT);
        v.setPrefWidth(200);
        v.setMaxWidth(200);
        v.setMinWidth(200);
        v.setSpacing(70);
        v.setBackground(Background.fill(Style.menuBkgrndPaint));

        //INITIALIZING CONTENT FOR RIGHT SIDE
        Font bigFont = font("Courier New", 80);
        Label titletext = new Label("Q W E R T Y");
        titletext.setTextFill(Style.accentPaint);
        titletext.setFont(bigFont);
        titletext.setPadding(new Insets(50, 0, 0, 0));
        titletext.setAlignment(Pos.BASELINE_LEFT);

        Label bottomtitle = new Label(" A JavaFX app for CSD112-26W");
        bottomtitle.setBackground(Background.fill(Style.titleTextPaint));
        bottomtitle.setFont(new Font("Consolas", 18));
        bottomtitle.setTextFill(Style.accentPaint);
        bottomtitle.setAlignment(Pos.BASELINE_LEFT);
        bottomtitle.setPrefWidth(750);
        bottomtitle.setPrefHeight(40);

        //SETTING UP REPLACEABLE TEXT (ALWAYS THE SAME FOR HOME SCREEN.)
       String s = ("Bottom text");
        var txtpane = new ReplaceableTextPane(s);
        this.textpane = txtpane;

        VBox rightpane = new VBox();
        rightpane.setSpacing(20);
        rightpane.setPrefHeight(Style.DEFAULT_SCENEHEIGHT);
        rightpane.getChildren().addAll(titletext, bottomtitle, txtpane);
        rightpane.setPadding(new Insets(0, 40, 0, 40));

        //PUTTING IT ALL IN A BORDERPANE.
//        BorderPane borderPane = new BorderPane();


        BorderPane bp = new BorderPane();
        bp.setLeft(v);
        bp.setAlignment(titletext,Pos.BASELINE_LEFT);
        bp.setCenter(rightpane);
        bp.setBackground(Background.fill((Style.mainBkgrndPaint)));

       return(bp);
    }

//TODO: eventually move this function to the main controller class -> pass a list of buttons ??????? idk.
    public void passToButtons(Runnable settingsAction, Runnable playAction, Runnable aboutAction ){
        this.settingsBtn.setOnAction(e -> settingsAction.run());
        this.playBtn.setOnAction(e -> playAction.run());
        this.aboutBtn.setOnAction( _ -> aboutAction.run());
    }

    @Override
    public Node build() {
        return(InitializeHomePane());
    }
}



