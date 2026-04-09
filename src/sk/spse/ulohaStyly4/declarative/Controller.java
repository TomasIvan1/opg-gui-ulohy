package sk.spse.ulohaStyly4.declarative;

import java.util.Random;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Controller {

    private static final Random RANDOM = new Random();

    @FXML
    private VBox vbox1;

    @FXML
    private VBox vbox2;

    @FXML
    private VBox vbox3;

    @FXML
    private VBox vbox4;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private void close() {
        Platform.exit();
    }

    @FXML
    private void randomize() {
        vbox1.setStyle("-fx-background-color: " + getRandomColor() + ";");
        vbox2.setStyle("-fx-background-color: " + getRandomColor() + ";");
        vbox3.setStyle("-fx-background-color: " + getRandomColor() + ";");
        vbox4.setStyle("-fx-background-color: " + getRandomColor() + ";");

        randomizeImage(img1);
        randomizeImage(img2);
        randomizeImage(img3);
        randomizeImage(img4);
    }

    private void randomizeImage(ImageView img) {
        double width = 50 + RANDOM.nextInt(126);
        double height = 50 + RANDOM.nextInt(126);
        double opacity = 0.3 + RANDOM.nextDouble() * 0.7;
        double rotate = RANDOM.nextInt(360);

        img.setFitWidth(width);
        img.setFitHeight(height);
        img.setOpacity(opacity);
        img.setRotate(rotate);
    }

    private String getRandomColor() {
        int r = RANDOM.nextInt(256);
        int g = RANDOM.nextInt(256);
        int b = RANDOM.nextInt(256);
        return String.format("rgb(%d,%d,%d)", r, g, b);
    }
}
