package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import agh.ics.oop.Vector2d;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class GuiElementBox extends Application {
    private final String source;
    private final Vector2d pos;
    public GuiElementBox(IMapElement iMapElement) {
        this.source = iMapElement.getSource();
        this.pos = iMapElement.getPosition();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image elem = new Image(new FileInputStream(this.source));
        ImageView elemView = new ImageView(elem);
        elemView.setFitHeight(20);
        elemView.setFitWidth(20);
        if (this.source.equals("")) {
            Label label = new Label("Trawa");
        }
        else {
            Label label = new Label("Z" + this.pos);
        }

//        Image downArrow = new Image(new FileInputStream("../../../resources/down.png"));
//        Image upArrow = new Image(new FileInputStream("../../../resources/up.png"));
//        Image leftArrow = new Image(new FileInputStream("../../../resources/left.png"));
//        Image rightArrow = new Image(new FileInputStream("../../../resources/right.png"));
//        Image grass = new Image(new FileInputStream("../../../resources/grass.png"));
//
//        ImageView downArrowView = new ImageView(downArrow);
//        ImageView upArrowView = new ImageView(upArrow);
//        ImageView leftArrowView = new ImageView(leftArrow);
//        ImageView rightArrowView = new ImageView(rightArrow);
//        ImageView grassView = new ImageView(grass);
//
//        downArrowView.setFitHeight(20);
//        downArrowView.setFitWidth(20);
//        upArrowView.setFitHeight(20);
//        upArrowView.setFitWidth(20);
//        leftArrowView.setFitHeight(20);
//        leftArrowView.setFitWidth(20);
//        rightArrowView.setFitHeight(20);
//        rightArrowView.setFitWidth(20);
//        grassView.setFitHeight(20);
//        grassView.setFitWidth(20);
//
//        Label downLabel = new Label();
//        Label upLabel = new Label();
//        Label leftLabel = new Label();
//        Label rightLabel = new Label();
//        Label grassLabel = new Label();
//
//        VBox vBoxDown = new VBox();
//        VBox vBoxUp = new VBox();
//        VBox vBoxLeft = new VBox();
//        VBox vBoxRight = new VBox();
//        VBox vBoxGrass = new VBox();
//
//        ObservableList list1 = vBoxDown.getChildren();
//        ObservableList list2 = vBoxUp.getChildren();
//        ObservableList list3 = vBoxLeft.getChildren();
//        ObservableList list4 = vBoxRight.getChildren();
//        ObservableList list5 = vBoxGrass.getChildren();
//
//        list1.addAll(vBoxDown, downLabel);
//        list2.addAll(vBoxUp, upLabel);
//        list3.addAll(vBoxLeft, leftLabel);
//        list4.addAll(vBoxRight, rightLabel);
//        list5.addAll(vBoxGrass, grassLabel);
//
//        vBoxDown.setAlignment(Pos.CENTER);
//        vBoxUp.setAlignment(Pos.CENTER);
//        vBoxLeft.setAlignment(Pos.CENTER);
//        vBoxRight.setAlignment(Pos.CENTER);
//        vBoxGrass.setAlignment(Pos.CENTER);


    }
}
