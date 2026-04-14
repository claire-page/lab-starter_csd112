package ui;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import static javafx.scene.text.Font.font;
//NAMESPACE CLASS FOR STYLES.
//was going to have a whole settings menu for font / colour prefererences so that's what all the defaults are about.

public class Style {

    public class FontFaces{
        public static final Font COURIER= (Font.font("Courier New", 20));
        public static final Font CONSOLAS = (Font.font("Consolas", 20));
        public static final Font DYSLEXIC = Font.loadFont(Style.class.getResourceAsStream("/fonts/OpenDyslexic-Bold.otf"), 15);
    };
    public static  final int DEFAULT_SCENEWIDTH = 1000;
    public static final int DEFAULT_SCENEHEIGHT = 670;
    public static final int DEFAULT_PANEHEIGHT = 600;
    public static final int DEFAULT_PANEWIDTH = 250;

    public static final Font DEFAULT_TITLE_FONT = new Font("Courier New", 80);
    public static final  Font DEFAULT_MENU_FONT = (font("Consolas",FontWeight.BOLD, 22));

    public static  Paint menuBkgrndPaint = Paint.valueOf("rgb(74, 111, 117)");
    public static  Paint mainBkgrndPaint = Paint.valueOf("rgb(178, 201, 209)");
    public static  Paint blankTextPaint = Paint.valueOf("rgb(140, 167, 174)");
    public static  Paint filledTextPaint = Paint.valueOf("rgb(16, 31, 36)");
    public static Paint mistakeTextPaint =  Paint.valueOf("rgb(176, 79, 90)");;

    public static  Paint textBkgrndPaint = Paint.valueOf("rgb(217,227,239)");

    public static  Paint titleTextPaint = Paint.valueOf("rgb(87, 24, 41)");
    public static  Paint darkred = Paint.valueOf("rgb(134, 41, 52)"); //for dark red
    public static Paint lightred =  Paint.valueOf("rgb(176, 79, 90)"); //for smaller text


}