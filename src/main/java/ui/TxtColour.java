package ui;
import core.QChar;
import core.ReplaceableText;
import javafx.scene.paint.*;
public enum  TxtColour  {

    Gray("rgb(160,174,188)"),
    Black("rgb(67,97,117)"),
    Red("rgb(126, 25, 27)"),
    PaleRed("rgb(175, 64, 53)");

     String paintString;

    private TxtColour(String value){
        this.paintString = value;

    };
    //initializes colour of text.
    public static void initColor(ReplaceableText replaceableText){
        for (QChar q : replaceableText.getChars()){
            q.setFill(Paint.valueOf(Gray.paintString));
        }
    }
    public static void changeColor(QChar qChar, TxtColour txtColour){
        qChar.setFill(Paint.valueOf(txtColour.paintString));
    }

}
