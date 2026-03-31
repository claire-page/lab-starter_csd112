package ui;
import core.QChar;
import core.ReplaceableText;
import javafx.scene.paint.*;
public enum  TxtColour  {

    Gray("rgb(140,146,172)"),
    Black("rgb(0,0,28)"),
    Red("rgb(105, 27, 35)");

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
