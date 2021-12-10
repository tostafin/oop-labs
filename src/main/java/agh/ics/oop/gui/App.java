package agh.ics.oop.gui;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import agh.ics.oop.*;

import java.io.FileNotFoundException;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
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
            ColumnConstraints columnConstraints = new ColumnConstraints(70);
            columnConstraints.setPercentWidth(100.0 / width);
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i <= height; i++) {
            RowConstraints rowConstraints = new RowConstraints(70);
            rowConstraints.setPercentHeight(100.0 / height);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        int xDiff = lowerLeft.x - 1;
        int yDiff = upperRight.y + 3;
        for (int i = 0; i <= height; i++) {
            yDiff -= 2;
            for (int j = 0; j <= width; j++) {
                String character = null;
                IMapElement elem = null;
                if (i == 0 && j == 0) character = "y/x";
                else if (i == 0) character = String.valueOf(lowerLeft.x + j - 1);
                else if (j == 0) character = String.valueOf(upperRight.y - i + 1);
                else {
                    Object obj = map.objectAt(new Vector2d(j + xDiff, i + yDiff));
                    elem = (IMapElement) obj;
                }

                if (elem != null) {
                    VBox vBox;
                    try {
                        vBox = new GuiElementBox(elem).createImage();
                    } catch (FileNotFoundException e) {
                        throw new FileNotFoundException("File hasn't been found!");
                    }
                    GridPane.setConstraints(vBox, j, i);
                    GridPane.setHalignment(vBox, HPos.CENTER);
                    gridPane.add(vBox, j, i);
                }
                else {
                    Label label = new Label(character);
                    GridPane.setConstraints(label, j, i);
                    GridPane.setHalignment(label, HPos.CENTER);
                    gridPane.add(label, j, i);
                }
            }
        }

        Scene scene = new Scene(gridPane, 70 * width, 70 * height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}
