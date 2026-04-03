package ui;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import static javafx.scene.text.Font.font;

//namespace class.
public class Defaults {


    public static final int DEFAULT_SCENEWIDTH = 1000;
    public static final int DEFAULT_SCENEHEIGHT = 670;
    public static final int DEFAULT_PANEHEIGHT = 600;
    public static final int DEFAULT_PANEWIDTH = 250;

    public static final Paint DEFAULT_ACCENT_1 = Paint.valueOf("rgb(74, 111, 117)");

    public static final Paint DEFAULT_BKGROUND = Paint.valueOf("rgb(178, 201, 209)");
    public static final Paint DEFAULT_BLANK_TXT = Paint.valueOf("rgb(140, 167, 174)");
    public static final Paint DEFAULT_FILLED_TXT = Paint.valueOf("rgb(16, 31, 36)");



    public static final Paint DEFAULT_TXT_BKGRND = Paint.valueOf("rgb(217,227,239)");

    public static final Paint DEFAULT_CONTRAST_ACCENT_DK = Paint.valueOf("rgb(87, 24, 41)");
    public static final Paint DEFAULT_CONTRAST_ACCENT_LT = Paint.valueOf("rgb(134, 41, 52)");
    public static final Paint DEFAULT_CONTRAST_LIGHT_RED = Paint.valueOf("rgb(176, 79, 90)");

    public static final Font DEFAULT_TITLE_FONT = new Font("Courier New", 80);
    public static final Font DEFAULT_MENU_FONT = (font("Consolas", 20));
    public static final Font DEFAULT_TEXT_FONT = new Font("Courier New", 20);
//
//

//    public final Font DEFAULT_FONT = new Font("Courier New", 20);
//
//    public final TxtColourScheme DEFAULT_SCHEME = new TxtColourScheme("rgb(160,174,188)",
//                                                          "rgb(67,97,117)",
//                                                         "rgb(126, 25, 27)",
//                                                        "rgb(175, 64, 53)");
//
//
//    final Preferences PREF = new Preferences(DEFAULT_FONT, DEFAULT_SCHEME);
//
//
//    public static Preferences getDefaultPreferences(){
//        return(PREF);
//    }

    //method to load default preferences?

}