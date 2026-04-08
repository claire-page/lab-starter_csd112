package ui;

import core.ReplaceableText;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class ReplaceableTextPane extends FlowPane {

    ReplaceableText replaceableText;
    final QChar[] chars;
    boolean isActive;

    public ReplaceableTextPane(String string){

        this.chars = new QChar[string.length()];

        var splitString= string.split("");

        for (int i= 0; i< string.length(); i++){
            chars[i] = new QChar(splitString[i]);
        }
        this.isActive = false;

        this.replaceableText = new ReplaceableText(string);

            this.setPrefSize(200, 300);
            this.setPadding(new Insets(20));


            HBox[] holder = new HBox[replaceableText.getWordCount()];
            for (int i = 0; i < holder.length; i++) {
                holder[i] = new HBox();
            }

            int j = 0;
            for (int k = 0; k < chars.length; k++) {
                var q = chars[k];
                q.setFill(Style.blankTextPaint);
                q.setFont(Style.textFont);
                holder[j].getChildren().add(q);
                if ((q.valueOf().equals(" ") || q.valueOf().matches("\\p{P}") || k == chars.length-1)){ //we reach punctuation we end up at the end...
                    this.getChildren().add(holder[j]); //adding hBox.
                    j+=1;

                }
            }

            this.setFocusTraversable(true);
            this.setBackground(Background.fill(Style.textBkgrndPaint));
            this.setVisible(true);
            this.isActive = true;
        }


    public ReplaceableText getReplaceableText() {
        return replaceableText;
    }


    /**
     * to be called when backspace key is entered
     *
     */
    public void backSpace() {
        if (this.isActive) {


            var idx = this.replaceableText.getIdx();
            System.out.println("index" + idx);
            if (idx != 0) { ///if we CAN backspace.
                this.chars[idx - 1].revertTxt(); //make the character before its original character
                TxtColourScheme.changeColor(chars[idx - 1], Style.blankTextPaint);
                this.chars[idx-1].setTyped(false);
                replaceableText.decIndex();
            }
            //otherwise we just do nothing lol
        }

    }
    /**
     * to be called when any other key is entered (type event.)
     * @param entered
     */
    public void updateForKeyTyped(String entered){

        if (this.isActive) {

        System.out.println("key entered");
        var rtxt = this.getReplaceableText();
        var text = rtxt.getText();
        var index = rtxt.getIdx();
        var qChars = this.chars;

        String currentString = String.valueOf(text.charAt(index));

            if (entered.equals(currentString)) { //if correct
                TxtColourScheme.changeColor(qChars[index], Style.filledTextPaint);
                rtxt.incIndex();
                qChars[index].setTyped(true);

            } else if (entered.equals(" ")) { //if you spaced over a character, still want it to be noticeable.
                rtxt.incIndex();
                TxtColourScheme.changeColor(qChars[index-1], Style.blankTextPaint);
                System.out.println("space bar pressed");
            }
            else { //if it's not a match or a space.
                TxtColourScheme.changeColor(qChars[index],Style.mistakeTextPaint);
                rtxt.incIndex();
                qChars[index].setText(entered);
                System.out.println("not a match");
            }
         }

        }

        public boolean getStatus(){
            return(this.isActive);
        }

        public boolean getCompletionStatus(){
            for(QChar q: this.chars){
                if (!q.isTyped()){
                    return(false);
                }
            }
            return(true);
        }


        //deactivates pane and prevents it from being overrwritten.

        public void deactivate(){
        this.isActive = false;
        }
}





