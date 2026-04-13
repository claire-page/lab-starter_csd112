package ui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;

public class ReplaceableTextPane extends FlowPane {


    public ReplaceableTextPane(){
        this.setPrefSize(200, 300);
        this.setPadding(new Insets(20));
        this.setBackground(Background.fill(Style.textBkgrndPaint));

        }

}
