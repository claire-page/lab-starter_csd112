package ui;

import core.ReplaceableText;
import javafx.geometry.Insets;
import javafx.scene.layout.*;

public class BasicLayout {

    public BorderPane getDefaultLayoutPane() {

        BorderPane bp = new BorderPane();

        VBox left = new VBox();
        left.setId("left");
        left.setBackground(Background.fill(Style.menuBkgrndPaint));
        left.setPrefHeight(Style.DEFAULT_SCENEHEIGHT);
        left.setPrefWidth(200);
        left.setMaxWidth(200);
        left.setMinWidth(200);
        left.setSpacing(70);

        VBox right = new VBox();
        right.setId("right");
        right.setBackground(Background.fill(Style.mainBkgrndPaint));
        right.setPadding(new Insets(0, 40, 0, 40));
        right.setPrefHeight(Style.DEFAULT_SCENEHEIGHT);
        right.setFillWidth(true);
        bp.setLeft(left);
        bp.setCenter(right);

        return(bp);
    }

    public class customComponents{

        /**
         * Factory method. sets up according to preference.
         * @param chars
         * @param rt
         * @param pref
         * @return
         */
        public static FlowPane initPane(QChar[] chars, ReplaceableText rt, Preferences pref){
///
            FlowPane p = new FlowPane();
            p.setPadding(new Insets(20));

            HBox[] holder = new HBox[rt.getWordCount()];
            for (int i = 0; i < holder.length; i++) {
                holder[i] = new HBox();
            }

            int j = 0;
            for (int k = 0; k < chars.length; k++) {
                var q = chars[k];
                q.setFill(pref.colours().getBlankTextColour());
                q.setFont(pref.font());
                holder[j].getChildren().add(q);
                if ((q.valueOf().equals(" ") || q.valueOf().matches("\\p{P}") || k == chars.length-1)){ //we reach punctuation or we end up at the end...
                    p.getChildren().add(holder[j]); //adding hBox.
                    j+=1;

                }
            }
            p.setFocusTraversable(true);
            p.setBackground(Background.fill(Style.textBkgrndPaint));
            return(p);
        }

    }
}
