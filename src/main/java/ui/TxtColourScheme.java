package ui;
import core.ReplaceableText;
import javafx.scene.paint.*;

import java.util.Arrays;
/// this will be a record.
public class TxtColourScheme {

    private Paint blankTextColour;
    private Paint filledInColour;
    private Paint mistakeColour;
    private Paint highlightColour ;

    public TxtColourScheme( String blanktextString, String filledinString, String mistakeString, String highlightString) {
        this.blankTextColour = Paint.valueOf(blanktextString);
        this.filledInColour = Paint.valueOf(filledinString);
        this.mistakeColour = Paint.valueOf(mistakeString);
        this.highlightColour = Paint.valueOf(highlightString);
    }

    public Paint getBlankTextColour() {
        return blankTextColour;
    }

    public Paint getFilledInColour() {
        return filledInColour;
    }

    public Paint getMistakeColour() {
        return mistakeColour;
    }

    public Paint getHighlightColour() {
        return highlightColour;
    }

    //initializes
    public static void initBlankColor(TxtColourScheme scheme, QChar[] QChars) {
        for (QChar q : QChars) {
            q.setFill(scheme.getBlankTextColour());
        }
    }
    public static void changeColor(QChar qChar, Paint paint) {
        qChar.setFill(paint);
    }

}
