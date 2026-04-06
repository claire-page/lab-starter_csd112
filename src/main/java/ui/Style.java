package ui;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import static javafx.scene.text.Font.font;
//want dyslexic font,.
//namespace class.
public class Style {

    public static  final int DEFAULT_SCENEWIDTH = 1000;
    public static final int DEFAULT_SCENEHEIGHT = 670;
    public static final int DEFAULT_PANEHEIGHT = 600;
    public static final int DEFAULT_PANEWIDTH = 250;

    public static final Font DEFAULT_TITLE_FONT = new Font("Courier New", 80);
    public static final  Font DEFAULT_MENU_FONT = (font("Consolas", 20));

    public static  Paint menuBkgrndPaint = Paint.valueOf("rgb(74, 111, 117)");

    public static  Paint mainBkgrndPaint = Paint.valueOf("rgb(178, 201, 209)");
    public static  Paint blankTextPaint = Paint.valueOf("rgb(140, 167, 174)");
    public static  Paint filledTextPaint = Paint.valueOf("rgb(16, 31, 36)");
    public static Paint mistakeTextPaint =  Paint.valueOf("rgb(176, 79, 90)");;

    public static  Paint textBkgrndPaint = Paint.valueOf("rgb(217,227,239)");

    public static  Paint titleTextPaint = Paint.valueOf("rgb(87, 24, 41)");
    public static  Paint accentPaint = Paint.valueOf("rgb(134, 41, 52)"); //for dark red
    public static Paint textAccentPaint =  Paint.valueOf("rgb(176, 79, 90)"); //for smaller text

    public static Font textFont = new Font("Courier New", 20);

    public Preferences pref;

    public Style(String mainBkgrnd, String menuBkgrnd,String titleTextColour, String buttonColour, String blankText, String filledText, String mistakeText, String fontFace, int fontSize) {
        this.mainBkgrndPaint = Paint.valueOf(mainBkgrnd);
        this.menuBkgrndPaint = Paint.valueOf(menuBkgrnd);
        this.blankTextPaint = Paint.valueOf(blankText);
        this.filledTextPaint = Paint.valueOf(filledText);
        this.mistakeTextPaint = Paint.valueOf(mistakeText);
        this.accentPaint = Paint.valueOf(buttonColour);
        this.titleTextPaint = Paint.valueOf(titleTextColour);
        this.textFont = font(fontFace, fontSize);
    }


}