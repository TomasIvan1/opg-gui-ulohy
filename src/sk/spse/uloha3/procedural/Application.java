package sk.spse.uloha3.procedural;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.net.URI;

///
/// Trieda pre procedurálne vytvorené GUI
///
/// Upravujte túto triedu
///

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(0, 20, 20, 20));

        Label title = new Label("Stredná priemyselná škola elektrotechnická, Prešov");
        title.setStyle("-fx-font-size: 18px;");
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setMargin(title, new Insets(0, 0, 10, 0));
        root.setTop(title);

        Image image = new Image(getClass().getResourceAsStream("/sk/spse/uloha3/spse.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);

        TextArea textArea = new TextArea(
                "Vážení rodičia a milí žiaci základných škôl,\n\n" +
                        "radi by sme Vás privítali v priestoroch našej školy\n" +
                        "dňa 10. 02. 2026 (utorok) od 13.00 do 17.00 h na\n" +
                        "Dni otvorených dverí, kde by sme Vám chceli\n" +
                        "predstaviť naše študijné odbory."
        );
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefRowCount(7);

        Hyperlink link = new Hyperlink("https://www.spse-po.sk");
        link.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.spse-po.sk"));
            } catch (Exception ignored) {
            }
        });

        VBox right = new VBox(10, textArea, link);
        right.setMaxWidth(350);
        right.setAlignment(Pos.TOP_LEFT);

        HBox center = new HBox(20, imageView, right);
        center.setAlignment(Pos.CENTER_LEFT);
        root.setCenter(center);

        Button closeButton = new Button("Beriem na vedomie");
        closeButton.setOnAction(e -> Platform.exit());

        HBox bottom = new HBox(closeButton);
        bottom.setAlignment(Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(bottom, new Insets(10, 0, 0, 0));
        root.setBottom(bottom);

        Scene scene = new Scene(root);

        stage.setTitle("Procedural Application 3");
        stage.setScene(scene);
        stage.show();
    }
}