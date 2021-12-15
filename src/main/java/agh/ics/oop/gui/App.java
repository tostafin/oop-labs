package agh.ics.oop.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import agh.ics.oop.*;

import java.io.FileNotFoundException;

public class App extends Application implements IPositionChangeObserver {
    private GrassField map;
    private GridPane mapGridPane;
    private SimulationEngine engine;
    private Vector2d lowerLeft;
    private Vector2d upperRight;
    private int height;
    private int width;

    public void init() {
        try {
            this.map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            this.engine = new SimulationEngine(this.map, positions);
            this.engine.addObserver(this);
            this.engine.changeMoveDelay(300);
            this.mapGridPane = new GridPane();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        TextField directionsInput = new TextField();
        Label movesLabel = new Label("Sekwencja ruchów: ");
        HBox inputHBox = new HBox(movesLabel, directionsInput);
        inputHBox.setAlignment(Pos.CENTER);

        Button startSimulation = new Button("Start");
        VBox dirsInput = new VBox(inputHBox, startSimulation);
        VBox.setMargin(dirsInput, new Insets(15, 0, 0, 0));
        dirsInput.setSpacing(10);
        dirsInput.setAlignment(Pos.CENTER);

        VBox appWindow = new VBox(this.mapGridPane, dirsInput);
        appWindow.setAlignment(Pos.CENTER);
        this.mapGridPane.setAlignment(Pos.CENTER);

        this.cntHeightAndWidth();
        for (int i = 0; i <= this.width; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints(65);
            this.mapGridPane.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i <= this.height; i++) {
            RowConstraints rowConstraints = new RowConstraints(65);
            this.mapGridPane.getRowConstraints().add(rowConstraints);
        }

        try {
            this.drawCurrMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        startSimulation.setOnAction(e -> {
            MoveDirection[] directions = new OptionsParser().parse(directionsInput.getText().split(" "));
            this.engine.setDirections(directions);
            Thread engineThread = new Thread(this.engine);
            engineThread.start();
        });

        primaryStage.setTitle("Mapa świata");
        Scene scene = new Scene(appWindow, 90 * this.width, 90 * this.height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void drawCurrMap() throws FileNotFoundException {
        this.cntHeightAndWidth();
        this.mapGridPane.setGridLinesVisible(false);
        this.mapGridPane.setGridLinesVisible(true);

        int xDiff = this.lowerLeft.x - 1;
        int yDiff = this.upperRight.y + 3;
        for (int i = 0; i <= this.height; i++) {
            yDiff -= 2;
            for (int j = 0; j <= this.width; j++) {
                String character = null;
                IMapElement elem = null;
                if (i == 0 && j == 0) character = "y/x";
                else if (i == 0) character = String.valueOf(this.lowerLeft.x + j - 1);
                else if (j == 0) character = String.valueOf(this.upperRight.y - i + 1);
                else {
                    Object obj = this.map.objectAt(new Vector2d(j + xDiff, i + yDiff));
                    elem = (IMapElement) obj;
                }

                if (elem != null) {
                    try {
                        VBox vBox;
                        vBox = new GuiElementBox(elem).createImage();
                        GridPane.setConstraints(vBox, j, i);
                        GridPane.setHalignment(vBox, HPos.CENTER);
                        this.mapGridPane.add(vBox, j, i);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                } else {
                    Label label = new Label(character);
                    GridPane.setConstraints(label, j, i);
                    GridPane.setHalignment(label, HPos.CENTER);
                    this.mapGridPane.add(label, j, i);
                }
            }
        }
    }

    public void cntHeightAndWidth() {
        this.lowerLeft = this.map.getLowerLeft();
        this.upperRight = this.map.getUpperRight();
        this.height = upperRight.y - lowerLeft.y + 1;
        this.width = upperRight.x - lowerLeft.x + 1;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(() -> {
            this.mapGridPane.getChildren().clear();
            try {
                this.drawCurrMap();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}
