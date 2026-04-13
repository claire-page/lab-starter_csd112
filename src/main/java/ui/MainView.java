package ui;

import core.TypeChar;
import core.TypedStatus;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Builder;

import java.text.DecimalFormat;
import java.util.List;

import static javafx.scene.text.Font.font;
//this IS the right pane.

public class MainView implements Builder<Region> {
    private static CustomTimer timer;
    private static FlowPane pane;
    private static Label timerDisplay;
    private Runnable paneInitializer;
    private Runnable runRestarter;
    private Runnable runResetter;

    public MainView(Runnable paneInitializer, Runnable forButton1, Runnable forButton2) {
        this.timerDisplay = new Label("enter any key to begin.");
        this.paneInitializer = paneInitializer;
        this.runResetter = forButton1;
        this.runRestarter = forButton2;
    }

    @Override
    public Region build() {

        Font bigFont = font("Courier New", 80);
        Label titletext = new Label("Q W E R T Y");
        titletext.setTextFill(Style.accentPaint);
        titletext.setFont(bigFont);
        titletext.setPadding(new Insets(50, 0, 0, 10));
        titletext.setAlignment(Pos.BASELINE_LEFT);

        Label line = new Label("--------------------------------------------------------------------------------------------------------");
        line.setBackground(Background.fill(Style.titleTextPaint));
        line.setFont(new Font("Montserrat", 18));
        line.setTextFill(Color.ANTIQUEWHITE);
        line.setAlignment(Pos.BASELINE_LEFT);
        line.setPrefWidth(750);
        line.setPrefHeight(40);

        this.pane = new FlowPane();
        pane.setPrefSize(200, 300);
        pane.setPadding(new Insets(5));
        pane.setBackground(Background.fill(Style.textBkgrndPaint));//getting text pane...
        pane.setPadding(new Insets(20, 40, 0, 40));

        timerDisplay.setPadding(new Insets(20));
        timerDisplay.setFont(Style.DEFAULT_MENU_FONT);

        timer = new CustomTimer();

        Button b = new Button("RESTART THIS RUN");
        Button b2 = new Button("GET NEW TEXT");
        b.setOnMouseClicked(e -> runRestarter.run());
        b2.setOnMouseClicked(e -> runResetter.run());


        HBox buttonarea = new HBox( b, b2);
        buttonarea.setSpacing(20);
        VBox right = new VBox();
        right.setBackground(Background.fill(Color.WHITE));
        right.getChildren().addAll(titletext, line, buttonarea, pane, timerDisplay);
        right.setSpacing(20);


        right.requestFocus();
        paneInitializer.run(); //this sets up the pane to display text.

        return (right);

    }

    public static void renderTextData(List<TypeChar> typeCharList, String template) {

        pane.getChildren().removeAll(pane.getChildren()); //clearing the pane of its hboxes, if it had any before.

        //need as many Hboxes as we have words in the template...
        HBox[] holder = new HBox[template.split(" ").length];
        for (int i = 0; i < holder.length; i++) {
            holder[i] = new HBox();
        }

        int h = 0;

        for (int j = 0; j < typeCharList.size(); j++) {

            var currentTypeChar = typeCharList.get(j);
            String strValue;
            if (currentTypeChar.getStatus() == TypedStatus.INCORRECT && currentTypeChar.typed()!=(' ')) { //if it's a space we don't want to just slap a space over it
                strValue = String.valueOf(currentTypeChar.typed());
            } else {
                strValue = String.valueOf(currentTypeChar.expected());
            }

            var textFromChar = new Text(strValue);

            textFromChar.setFill(switch (currentTypeChar.getStatus()) {
                case INCORRECT -> Style.mistakeTextPaint;
                case CORRECT -> Style.filledTextPaint;
                case UNREACHED -> Style.blankTextPaint;
            });
            textFromChar.setFont(Style.FontFaces.DYSLEXIC);

            holder[h].getChildren().add(textFromChar);
            //checking to see if we're at the end or reached what's supposed to be punctuation/space
            // (need to add pane and move on to next hbox!)
            boolean needNextbox = (String.valueOf(currentTypeChar.expected()).matches("\\s"));

            if (needNextbox) {
                pane.getChildren().add(holder[h]);
                if (h < holder.length - 1) {  //making sure we're still in range/
                    h += 1;
                } else {
                    break;
                }
            }
        }
    }

    public static void startTimer(){
        timer.start();
    }

    public static void stopTimer(){
        timer.stop();
    }
    public static double stopTimerandGetTime() {
        timer.stop();
        return(timer.getElapsed());
    };

    public static void updateTime(Long elapsed) {
        timerDisplay.setText(new DecimalFormat("0.0").format(elapsed));
    }

    public static void resetTimer(){
        timer.resetStartTime();
        timerDisplay.setText("enter any key to begin.");
        timer.stop();
    }

    public class CustomTimer extends AnimationTimer {
        private long startTime = -1;
        double elapsed;

        @Override
        public void handle(long l) {

            if (startTime == -1) { //only useful for first time being called.
                startTime = l; //
            }
            elapsed = (l - startTime) / 1000000000.0; //dividing to get value as a double.
            timerDisplay.setText(new DecimalFormat("0.0").format(elapsed));
        }

        public double getElapsed(){
            return(this.elapsed);
        }

        public void resetStartTime(){
            this.startTime = -1;
        }

    }




}



