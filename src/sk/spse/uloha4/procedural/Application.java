package sk.spse.uloha4.procedural;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

///
/// Trieda pre procedurálne vytvorené GUI
///
/// Upravujte túto triedu
///

public class Application extends javafx.application.Application {

    private static final Random RANDOM = new Random();

    private VBox vbox1;
    private VBox vbox2;
    private VBox vbox3;
    private VBox vbox4;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        TilePane tilePane = new TilePane();
        tilePane.setPrefColumns(4);
        tilePane.setHgap(0);
        tilePane.setVgap(0);
        tilePane.setPrefTileHeight(240);
        tilePane.setPrefTileWidth(180);

        Image tomatoImage = new Image(getClass().getResourceAsStream("/sk/spse/uloha4/tomato.jpg"));

        vbox1 = new VBox();
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setStyle("-fx-background-color: blue;");
        vbox1.setPrefWidth(180);
        vbox1.setPrefHeight(240);
        ImageView imageView1 = new ImageView(tomatoImage);
        imageView1.setFitWidth(175);
        imageView1.setPreserveRatio(true);
        vbox1.getChildren().add(imageView1);

        vbox2 = new VBox();
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setStyle("-fx-background-color: yellow;");
        vbox2.setPrefWidth(180);
        vbox2.setPrefHeight(240);
        ImageView imageView2 = new ImageView(tomatoImage);
        imageView2.setFitWidth(175);
        imageView2.setPreserveRatio(true);
        vbox2.getChildren().add(imageView2);

        vbox3 = new VBox();
        vbox3.setAlignment(Pos.CENTER);
        vbox3.setStyle("-fx-background-color: green;");
        vbox3.setPrefWidth(180);
        vbox3.setPrefHeight(240);
        ImageView imageView3 = new ImageView(tomatoImage);
        imageView3.setFitWidth(175);
        imageView3.setPreserveRatio(true);
        vbox3.getChildren().add(imageView3);

        vbox4 = new VBox();
        vbox4.setAlignment(Pos.CENTER);
        vbox4.setStyle("-fx-background-color: red;");
        vbox4.setPrefWidth(180);
        vbox4.setPrefHeight(240);
        ImageView imageView4 = new ImageView(tomatoImage);
        imageView4.setFitWidth(175);
        imageView4.setPreserveRatio(true);
        vbox4.getChildren().add(imageView4);

        tilePane.getChildren().addAll(vbox1, vbox2, vbox3, vbox4);
        root.setCenter(tilePane);

        HBox bottomBox = new HBox(15);
        bottomBox.setAlignment(Pos.CENTER_LEFT);
        bottomBox.setStyle("-fx-background-color: #EAEAEA;");
        bottomBox.setPadding(new Insets(10, 15, 10, 15));

        VBox quoteBox = new VBox(2);
        Label quoteLabel = new Label("\"In the future, everyone will be world-famous for 15 minutes\"");
        Label authorLabel = new Label("- Andy Warhol");
        quoteBox.getChildren().addAll(quoteLabel, authorLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        Button randomizeButton = new Button("Randomize");
        randomizeButton.setOnAction(e -> randomize());

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> Platform.exit());

        HBox buttonsBox = new HBox(10, randomizeButton, closeButton);
        buttonsBox.setAlignment(Pos.CENTER_RIGHT);

        bottomBox.getChildren().addAll(quoteBox, spacer, buttonsBox);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root);

        stage.setTitle("Procedural Application 4");
        stage.setScene(scene);
        stage.show();
    }

    private void randomize() {
        vbox1.setStyle("-fx-background-color: " + getRandomColor() + ";");
        vbox2.setStyle("-fx-background-color: " + getRandomColor() + ";");
        vbox3.setStyle("-fx-background-color: " + getRandomColor() + ";");
        vbox4.setStyle("-fx-background-color: " + getRandomColor() + ";");
    }

    private String getRandomColor() {
        int r = RANDOM.nextInt(256);
        int g = RANDOM.nextInt(256);
        int b = RANDOM.nextInt(256);
        return String.format("rgb(%d,%d,%d)", r, g, b);
    }
}