package ui;
import javafx.scene.paint.*;
public enum  TxtColour  {

    Gray("rgb(140,146,172)"),
    Black("rgb(0,0,28)"),
    Red("rgb(105, 27, 35)");

     String paintString;

    private TxtColour(String value){
        this.paintString = value;

    };

}
