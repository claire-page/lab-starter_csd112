package ui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class ContentPaneBuilder implements Builder<Region> {

    Node[] nodesToAdd;


    public ContentPaneBuilder(Node[] nodes){
        this.nodesToAdd = nodes;
    }

    @Override
    public Region build() {

       VBox center = new VBox();
       for (Node n: nodesToAdd){
           center.getChildren().add(n);

       }
        center.setId("right");
        center.setBackground(Background.fill(Style.mainBkgrndPaint));
        center.setPadding(new Insets(0, 40, 0, 40));
        center.setPrefHeight(Style.DEFAULT_SCENEHEIGHT);
        center.setFillWidth(true);
        return(center);
    }
}
