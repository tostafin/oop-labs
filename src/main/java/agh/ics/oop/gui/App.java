package agh.ics.oop.gui;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import agh.ics.oop.*;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        MoveDirection[] directions = new OptionsParser().parse(args);
        GrassField map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        int[] lowerLeft = {map.getLowerLeft().x, map.getLowerLeft().y};
        int[] upperRight = {map.getUpperRight().x, map.getUpperRight().y};
        List<Grass> fields = map.fields;
        List<Vector2d> v = fields.stream().map(Grass::getPosition).collect(Collectors.toList());
        System.out.println(v);
        System.out.println(Arrays.toString(lowerLeft));
        System.out.println(Arrays.toString(upperRight));

//        int[] lowerLeft = {-5, 1};
        int[] lowerLeftCopy = lowerLeft.clone();
//        int[] upperRight = {8, 9};
        int[] upperRightCopy = upperRight.clone();
        int y = upperRight[1] - lowerLeft[1] + 1;
        int x = upperRight[0] - lowerLeft[0] + 1;
        primaryStage.setTitle("World Map");
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        for (int i = 0; i <= x; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints(50);
            columnConstraints.setPercentWidth(100.0 / x);
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i <= y; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0 / y);
            gridPane.getRowConstraints().add(rowConstraints);
        }
        int xDiff = lowerLeft[0] - 1;
        int yDiff = upperRight[1] + 3;
        for (int i = 0; i <= y; i++) {  // i – row
            yDiff -= 2;
            for (int j = 0; j <= x; j++) {  // j - column
                int y1 = j, x1 = i;
                String text = null;
                if (i == 0 && j == 0) text = "y / x";
                else if (i == 0) {
                    text = String.valueOf(lowerLeftCopy[0]);
                    lowerLeftCopy[0]++;
                }
                else if (j == 0) {
                    text = String.valueOf(upperRightCopy[1]);
                    upperRightCopy[1]--;
                }
                else {
                    Object obj = map.objectAt(new Vector2d(j + xDiff, i + yDiff));
                    if (obj != null) {
                        text = obj.toString();
                        System.out.println(obj);
                        System.out.println(i + " " + j);
                    }
                }
                Label label = new Label(text);
                GridPane.setConstraints(label, y1, x1);
                GridPane.setHalignment(label, HPos.CENTER);
                gridPane.add(label, y1, x1);
            }
        }

        Scene scene = new Scene(gridPane, 50 * x, 50 * y);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
    /*
    * Do testowania:
    * f b r l f f r r f f f f f f f f
    * l l f f f f f f f f f f f f f f*/
}
