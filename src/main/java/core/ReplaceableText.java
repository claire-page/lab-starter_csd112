package core;

import java.util.regex.Pattern;
import java.util.stream.*;
import java.awt.*;

public class ReplaceableText {
    private String text;
   private int idx; //will mainly be used to move caret around..... hopefully this is fast...
   private int end;
   private int wordCount; // to be used for data later.

 public ReplaceableText(String text){
     this.text = text;
     this.wordCount = text.split("[ \\p{P}]*").length;
 }
 public String getText(){
     return(text);
 }

 public int getIdx(){
     return(this.idx);
 }
   public void incIndex(){
      idx++;
   }

   public void decIndex(){
      idx--;
 }

    public int getWordCount(){
       return(this.wordCount);
    }


}



