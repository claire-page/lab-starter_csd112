package core;

import java.util.regex.Pattern;
import java.util.stream.*;
import java.awt.*;

public class ReplaceableText {



   private QChar[] splitTxt; //split by character. so individual characters can be colorized.

   private int idx; //will mainly be used to move caret around..... hopefully this is fast...
   private int end;
   private int wordCount; // to be used for data later.

 public ReplaceableText(String text){
    var s = (text.split(""));
    this.splitTxt = Stream.of(s).map(string -> new QChar(string)) //making a new Text obj for each string in array.
                                                .toArray(QChar[]::new);
     System.out.println(splitTxt.length);
    this.wordCount = s.length;
    this.end = splitTxt.length;
 }

 public QChar[] getChars(){
     return(this.splitTxt);
 }

 public QChar charAt(int i){
     return splitTxt[i];
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
   public QChar nextChar(){
      return splitTxt[idx+1];
   }

   //but can also specify an index.
    public QChar nextChar(int i){
        return  splitTxt[i+1];
    }

   public QChar prevChar(){
      return(splitTxt[idx-1]);
   }

   public QChar prevChar(int i){
    return(splitTxt[i-1]);
   }
   /**
    * moves backwards until it encounters a non-alphabetical char.
    * @param j - where to start looking.
    * @return an integer representing the first index of the word currently on.
    * character indexes belonging to a word.
    */
   public int startOfCurrentWord(int j){
    while(j>0 && this.prevChar().valueOf().matches(String.valueOf(Pattern.compile("^[a-zA-Z]")))){ //matches most recent non alphabetical character.
      j +=1;
        return startOfCurrentWord(j);
    }
    return(j);
   }

   public void SetFill(Paint p, int index){
   }
    public int getWordCount(){
       return(this.wordCount);
    }

    public boolean isComplete(){
       if (idx < end){ //exits if we're not at the end, lol.
       for(QChar q: this.splitTxt){
           if(!q.isTyped()){
               return(false);
           }
       }
       return(true);}

      else {return(false);
      }
    }

    public void setCharAt(int index, String s){
       this.splitTxt[index].setText(s);
    }

    public void revertCharAt(int index){
       this.splitTxt[index].setText(this.splitTxt[index].valueOf());
    }
//divide the core logic and i/o...have the revert to

}



