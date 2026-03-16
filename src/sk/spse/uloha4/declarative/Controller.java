package sk.spse.uloha4.declarative;

import java.util.Random;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * Controller pre FXML súbor – obsahuje logiku aplikácie
 */
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
    private void close() {
        Platform.exit();
    }

    @FXML
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
