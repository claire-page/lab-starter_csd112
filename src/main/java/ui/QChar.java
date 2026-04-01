package ui;

import javafx.scene.text.Text;

import java.awt.*;
public class QChar extends Text {

    private boolean typed;
    private final String value;

    public boolean isTyped() {
        return typed;
    }

    public QChar(){
        super();
        this.value = "";
        this.typed = false;
    }

    public QChar(String string){
        super(string);
        this.value = string;
        this.typed = false;
    }

    public String valueOf(){
        return(this.value);
    }

    public void toggleTyped(){
        if (this.typed) {
            this.typed= false;
        }
        else {
            this.typed= true;
        }
    }

    public void revertTxt(){
        this.setText(this.valueOf());
    }


}
//new process:
//1- array of Qchar
//2-
