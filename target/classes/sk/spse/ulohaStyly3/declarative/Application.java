package sk.spse.ulohaStyly3.declarative;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.layout.Priority;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.Desktop;
import java.net.URI;


public class Application extends javafx.application.Application {

    private static final double LOGO_TILT = -18.0;
    private static final String OZNAM = "Vážení rodičia a mili žiaci základných škôl,\n\n"
            + "radi by sme Vás privítali v priestoroch našej školy\n"
            + "dnia 10. 02. 2026 (utorok) od 13.00 do 17.00 h na\n"
            + "Dni otvorených dverí, kde by sme Vám chceli\n"
            + "predstaviť naše študijné odbory.";

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("root-pane");
        root.setMaxWidth(560);
        root.setPadding(new Insets(16));

        Label heading = new Label("Stredná priemyselná škola elektrotechnická, Prešov");
        heading.getStyleClass().add("heading-label");
        BorderPane.setAlignment(heading, Pos.CENTER);
        root.setTop(heading);

        ImageView logo = new ImageView();
        logo.setFitWidth(140);
        logo.setFitHeight(140);
        logo.setPreserveRatio(true);
        logo.setSmooth(true);
        logo.getStyleClass().add("logo-view");
        Image img = new Image(getClass().getResourceAsStream("spse.png"));
        logo.setImage(img);

        double r = 70;
        Circle clip = new Circle(r, r, r);
        StackPane logoPane = new StackPane(logo);
        logoPane.setClip(clip);
        logoPane.getStyleClass().add("logo-pane");

        VBox leftBox = new VBox(logoPane);
        leftBox.setAlignment(Pos.TOP_CENTER);
        leftBox.setPadding(new Insets(8, 16, 0, 0));
        leftBox.getStyleClass().add("left-bar");
        root.setLeft(leftBox);

        TextArea oznamText = new TextArea();
        oznamText.setEditable(false);
        oznamText.setWrapText(true);
        oznamText.setPrefRowCount(8);
        oznamText.setPrefColumnCount(28);
        oznamText.getStyleClass().add("oznam-text-area");
        oznamText.setText(OZNAM);
        VBox.setVgrow(oznamText, Priority.NEVER);

        Hyperlink odkaz = new Hyperlink("https://www.spse-po.sk");
        odkaz.getStyleClass().add("oznam-hyperlink");

        StackPane linkPane = new StackPane(odkaz);
        linkPane.setPadding(new Insets(0, 0, 5, 5));
        linkPane.setAlignment(Pos.CENTER_LEFT);
        linkPane.setEffect(new javafx.scene.effect.DropShadow(javafx.scene.effect.BlurType.GAUSSIAN, javafx.scene.paint.Color.web("#dee1ea"), 10, 0.6, 0, 0));

        VBox centerBox = new VBox(12, oznamText, linkPane);
        centerBox.setPadding(new Insets(8, 0, 0, 0));
        centerBox.setMaxHeight(Region.USE_PREF_SIZE);
        centerBox.setMaxWidth(480);

        StackPane centerStack = new StackPane(centerBox);
        centerStack.getStyleClass().add("center-stack");
        StackPane.setAlignment(centerBox, Pos.TOP_LEFT);
        root.setCenter(centerStack);

        Button ack = new Button("Beriem na vedomie");
        ack.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #2a2a2a; -fx-background-color: #FFFA; -fx-border-color: #555555; -fx-border-width: 1; -fx-border-radius: 0; -fx-background-radius: 0; -fx-background-insets: 0; -fx-padding: 5 10;");
        ack.setOnAction(e -> Platform.exit());

        HBox bottom = new HBox(ack);
        bottom.setAlignment(Pos.CENTER_RIGHT);
        bottom.setPadding(new Insets(12, 0, 0, 0));
        bottom.getStyleClass().add("bottom-bar");
        root.setBottom(bottom);

        logoPane.setRotate(LOGO_TILT);

        RotateTransition rotateToStraight = new RotateTransition(Duration.millis(280), logoPane);
        rotateToStraight.setToAngle(0);

        RotateTransition rotateToTilt = new RotateTransition(Duration.millis(280), logoPane);
        rotateToTilt.setToAngle(LOGO_TILT);

        logoPane.setOnMouseEntered(e -> {
            rotateToTilt.stop();
            rotateToStraight.setFromAngle(logoPane.getRotate());
            rotateToStraight.playFromStart();
        });
        logoPane.setOnMouseExited(e -> {
            rotateToStraight.stop();
            rotateToTilt.setFromAngle(logoPane.getRotate());
            rotateToTilt.playFromStart();
        });

        ScaleTransition enlarge = new ScaleTransition(Duration.millis(200), logoPane);
        enlarge.setToX(1.25);
        enlarge.setToY(1.25);
        ScaleTransition shrink = new ScaleTransition(Duration.millis(200), logoPane);
        shrink.setToX(1.0);
        shrink.setToY(1.0);
        SequentialTransition clickPulse = new SequentialTransition(enlarge, shrink);
        logoPane.setOnMouseClicked(e -> clickPulse.playFromStart());

        odkaz.setOnAction(e -> {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(new URI("https://www.spse-po.sk"));
                }
            } catch (Exception ignored) {
            }
        });

        Scene scene = new Scene(root);
        scene.setFill(Color.web("#d8dce6"));
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("Declarative Application 3");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setMinWidth(400);
        stage.setMinHeight(320);
        stage.show();
    }
}
