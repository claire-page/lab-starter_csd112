package core;
import javafx.scene.text.Text;

import java.util.regex.Pattern;
import java.util.stream.*;
import java.awt.*;
import java.util.regex.Pattern.*;

public class ReplaceableText extends Text {


    private String[] splitTxt; //this is the under-text array. private and mayyyy make it final. dont want it to be modified externally

   String[] splitUserTxt; //this is whatever the user enters.

   private int idx; //will mainly be used to move caret around..... hopefully this is fast...
   private int wordCount; // to be used for data later.
   //constructor from string.
 public ReplaceableText(String text){
    this.splitTxt = text.split("");

    this.splitUserTxt = new String[splitTxt.length];  //same length as that first array but initially empty bc yeah. not even sure i need that.
    this.wordCount = splitTxt.length;
    //setting properties relating to the textArea
//     this.setWrapText(true);

 }

 public String charAt(int i){
     return(splitTxt[i]);
 }
 public int getIdx(){
     return(this.idx);
 }
   public void incIndex(){
      idx++;
   }

   public void decIndex(){
      idx--;}

   //default is next from current index
   public String nextChar(){
      return(splitTxt[idx+1]);
   }
   //but can also specify an index.
   public String nextChar(int i){
    return(splitUserTxt[i+1]);
   }

   public String prevChar(){
      return(splitTxt[idx-1]);
   }
   public String prevChar(int i){
    return(splitTxt[i-1]);

   }
   /**
    * moves backwards until it encounters a non-alphabetical char.
    * @param j - where to start looking.
    * @return an integer representing the first index of the word currently on.
    * character indexes belonging to a word.
    */
   public int startOfCurrentWord(int j){
    while(j>0 && this.prevChar().matches(String.valueOf(Pattern.compile("^[a-zA-Z]")))){ //matches most recent non alphabetical character.
      j +=1;
        return startOfCurrentWord(j);
    }
    return(j);
   }

   public void addToUserText(char c){
     this.splitUserTxt[idx] = String.valueOf(c);
   }
   public boolean isComplete(){
    return(this.splitTxt.equals(this.splitUserTxt));
   }

   public String getInnerText(){
       return(String.join(", ", this.splitTxt));
   }
//maybe I

    public int getWordCount(){
       return(this.wordCount);
    }

}
