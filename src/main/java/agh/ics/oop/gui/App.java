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

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        MoveDirection[] directions = new OptionsParser().parse(args);
        GrassField map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();

        int height = upperRight.y - lowerLeft.y + 1;
        int width = upperRight.x - lowerLeft.x + 1;
        primaryStage.setTitle("World Map");
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        for (int i = 0; i <= width; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints(20);
            columnConstraints.setPercentWidth(100.0 / width);
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i <= height; i++) {
            RowConstraints rowConstraints = new RowConstraints(20);
            rowConstraints.setPercentHeight(100.0 / height);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        int xDiff = lowerLeft.x - 1;
        int yDiff = upperRight.y + 3;
        for (int i = 0; i <= height; i++) {
            yDiff -= 2;
            for (int j = 0; j <= width; j++) {
                String character = null;
                if (i == 0 && j == 0) character = "y/x";
                else if (i == 0) character = String.valueOf(lowerLeft.x + j - 1);
                else if (j == 0) character = String.valueOf(upperRight.y - i + 1);
                else {
                    Object obj = map.objectAt(new Vector2d(j + xDiff, i + yDiff));
                    if (obj != null) character = obj.toString();
                }
                Label label = new Label(character);
                GridPane.setConstraints(label, j, i);
                GridPane.setHalignment(label, HPos.CENTER);
                gridPane.add(label, j, i);
            }
        }

        Scene scene = new Scene(gridPane, 50 * width, 50 * height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}
