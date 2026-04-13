package ui;

import controllers.Control;
import core.TypeChar;
import core.TypedStatus;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Builder;

import java.text.DecimalFormat;
import java.util.List;

import static javafx.scene.text.Font.font;

public class MainView implements Builder<Region> {
    private static AnimationTimer timer;
    private static FlowPane pane;
    private static Label timerDisplay;
    private Control controlla;

    public MainView(Control ctrl) {
        this.controlla = ctrl;
        this.timerDisplay = new Label("enter any key to begin.");

    }

    @Override
    public Region build() {

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

        this.pane = new FlowPane();
        pane.setPrefSize(200, 300);

        pane.setPadding(new Insets(5));
        pane.setBackground(Background.fill(Style.textBkgrndPaint));//getting text pane...
//        pane.setPrefSize(400, 300);
        pane.setPadding(new Insets(20, 40, 0, 40));


        timerDisplay.setPadding(new Insets(20));
        timerDisplay.setFont(Style.DEFAULT_MENU_FONT);

        timer = new AnimationTimer() {
            private long start = -1; //this is so the timer starts at 0...

            @Override
            public void handle(long l) { //this gets called very often.
                if (start == -1) { //only useful for first time being called.
                    start = l; //
                }
                var elapsed = (l - start) / 1000000000.0; //dividing to get value as a double.
                timerDisplay.setText(new DecimalFormat("0.0").format(elapsed));
            }

        };
        //getting the timer set up.
        VBox right = new VBox();
        right.getChildren().addAll(titletext, bottomtitle, timerDisplay, pane);

        right.requestFocus();
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
            if (currentTypeChar.getStatus() == TypedStatus.INCORRECT) {
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
            textFromChar.setFont(Style.FontFaces.COURIER);

            holder[h].getChildren().add(textFromChar);
            //checking to see if we're at the end or reached what's supposed to be punctuation/space
            // (need to add pane and move on to next hbox!)
            boolean needNext = (String.valueOf(currentTypeChar.expected()).matches("\\s"));

            if (needNext) {
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
    public void stopTimerandGetTime(){
        timer.stop();

    }


}



