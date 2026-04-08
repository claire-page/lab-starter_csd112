package core;

import java.util.regex.Pattern;
import java.util.stream.*;
import java.awt.*;

public class ReplaceableText {
    private String text;
    private int idx; //will mainly be used to move caret around..... hopefully this is fast...
    private int wordCount; // to be used for data later.
    public boolean gameEnabled;

 public ReplaceableText(String text){
     this.text = text;
     this.wordCount = text.split("[ \\p{P}]*").length;
     this.idx = 0;
     this.gameEnabled = false;

 }
 public String getText(){
     return(text);
 }

 public int getIdx(){
     return(this.idx);
 }


   public void incIndex() {
       if (idx < this.text.length()) { //want to make sure we don't go out of bounds.
           idx++;
       }
   }

   public void decIndex() { //want to make sure we don't go out of bounds.
     if (idx > 0) {
      idx--;
     }
 }

    public int getWordCount(){
       return(this.wordCount);
    }

    //if game enabled.
    public boolean isComplete(){
     return(this.idx== this.getText().length());
    }

    //if game is enabled on this text.

}



