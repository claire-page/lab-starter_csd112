package ui;

import core.ReplaceableText;
import core.TypeChar;
import core.TypedStatus;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ReplaceableTextPane extends FlowPane {


    public ReplaceableTextPane(){
        this.setPrefSize(200, 300);
        this.setPadding(new Insets(20));
        this.setBackground(Background.fill(Style.textBkgrndPaint));

        }

}
