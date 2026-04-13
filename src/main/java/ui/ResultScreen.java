package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Builder;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static javafx.scene.text.Font.font;

public class ResultScreen implements Builder<Parent> {

    Supplier<List<List<String>>> dataSupplier;
    Runnable backtoMain;

    //this will initialize the screen. Note that I only want it available when game is done.
    public ResultScreen(Supplier<List<List<String>>> dataSupply, Runnable backtoMain){
        this.dataSupplier = dataSupply;
        this.backtoMain = backtoMain;
    }
    @Override
    public Region build() {
        System.out.println("tried to build.");
            Font bigFont = font("Courier New", 80);
            Label titletext = new Label("YOUR RESULTS");
            titletext.setTextFill(Style.darkred);
            titletext.setFont(bigFont);
            titletext.setPadding(new Insets(50, 0, 0, 30));
            titletext.setAlignment(Pos.BASELINE_LEFT);


            var columns = new VBox[]{new VBox(new Label("time")),
                    new VBox(new Label("name")),
                    new VBox(new Label("words/min")),
                    new VBox(new Label("keys typed/second")),
                    new VBox(new Label("mistakes"))};

            //loading data from the db connection.

            var data = dataSupplier.get();

            for (List<String> e: data){
                for (int i = 0; i < e.size(); i++){
                    String value = e.get(i);
                    Label l = new Label(value);
                    l.setFont(Style.FontFaces.COURIER);
                    columns[i].getChildren().add(l);
                }
            }

        HBox table = new HBox();
            table.setSpacing(20);

            //Adding columns to table.
        Arrays.stream(columns).forEach(col -> {table.getChildren().add(col);});

        Button b = new Button();

            b.setText("BACK");
            b.setBackground(Background.fill(Style.lightred));
            b.setOnMouseClicked( e -> backtoMain.run());
            table.setBackground((Background.fill(Style.textBkgrndPaint)));
            VBox v = new VBox(titletext, table, b);
            v.setSpacing(20);
            return(v);

        }



}
