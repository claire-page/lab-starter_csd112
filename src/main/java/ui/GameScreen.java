package ui;

import controllers.Control;
import core.TypeChar;

import core.TypedStatus;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.text.DecimalFormat;
import java.util.List;



public class GameScreen {

    Runnable quitBtn;
    Runnable restartBtn;
    FlowPane pane;
    Label timerDisplay;
    Runnable callbackPaneInit;

    public GameScreen(Runnable quit, Runnable restart ) {
        this.quitBtn = quit;
        this.restartBtn = restart;
    }

    public Parent build() {
        //just doing this to give controller access to the view

        pane = new ReplaceableTextPane(); //getting text pane...

        pane.setPrefSize(300, 300);

        var ButtonTitles = new String[]{"Quit"};
        Runnable[] runnables = {quitBtn, restartBtn};
        Region left = new MenuBuilder(ButtonTitles, runnables).build();

        timerDisplay = new Label("enter any key to begin.");
        timerDisplay.setPadding(new Insets(50));

        //getting the timer set up.

        VBox right = new VBox();

        right.getChildren().addAll(timerDisplay,pane);

        //put it all into a pane.
        BorderPane bp = new BorderPane();
        bp.setLeft(left);
        bp.setCenter(right);

        //--PANE-LEVEL EVENT FILTERS--
        //setting up listener to be triggered on first key press (to start the timer), which will then remove itself,
        //ensuring its handler is only triggered once.
        //this is some nastiness here
        Control.initPane(this);

        bp.addEventFilter(KeyEvent.ANY, e -> Control.delegateKeyEvents(e, this));
        return(bp);

    }
    //making this part of the game scene because it deals with UI...I think that's fine.


    public void updateTimeLabel(double d){
        this.timerDisplay.setText(new DecimalFormat("0.00").format(d));
    }

    public void renderTextData(List<TypeChar> typeCharList, String template) {
        //i think this needs to be here because I need to access the children to remove them.
        // unless I access it through the game scene? I mean it is a part of the scene?
        this.pane.getChildren().removeAll(pane.getChildren()); //clearing the pane of its hboxes, if it had any before.

        //need as many Hboxes as we have words in the template...
        HBox[] holder = new HBox[template.split(" ").length];
        for (int i = 0; i < holder.length; i ++){
            holder[i]= new HBox();
        }

        int h = 0;

        for (int i = 0; i < typeCharList.size(); i++) {

            var currentTypeChar = typeCharList.get(i);
            var textFromChar = getColouredText(currentTypeChar);
            holder[h].getChildren().add(textFromChar);

            //checking to see if we're at the end or reached punctuation/space (need to add pane and move on to next hbox!)
            if (i == typeCharList.size()-1 || String.valueOf(currentTypeChar.expected()).matches("\\p{P}\\s")) {
                pane.getChildren().add(holder[h]);
                h += 1;
            }
        }
    }

    private static Text getColouredText(TypeChar currentTypeChar) {
        String strValue;

        if (currentTypeChar.getStatus()== TypedStatus.INCORRECT){
            strValue = String.valueOf(currentTypeChar.typed());
        }
        else{
            strValue = String.valueOf(currentTypeChar.expected());
        }

        var textFromChar = new Text(strValue);

        textFromChar.setFill(switch (currentTypeChar.getStatus()) {
            case INCORRECT -> Style.mistakeTextPaint;
            case CORRECT -> Style.filledTextPaint;
            case UNREACHED -> Style.blankTextPaint;
        });
        textFromChar.setFont(Style.myFont);
        return textFromChar;
    }

}


