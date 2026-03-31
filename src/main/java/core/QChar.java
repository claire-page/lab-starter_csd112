package core;

import javafx.scene.text.Text;
//every QChar is a text object. it also has a property of typed.


public class QChar extends Text {

    private boolean typed;
    final String value;

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

}


//want the incorrect character to be displayed...hmm.