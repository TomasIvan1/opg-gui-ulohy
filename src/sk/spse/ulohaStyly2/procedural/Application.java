package sk.spse.ulohaStyly2.procedural;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) {

        String labelStyle = "-fx-text-fill: green; -fx-font-family: Serif;";
        String textFieldStyle = "-fx-border-color: green; -fx-border-radius: 7px; -fx-background-radius: 7px; -fx-font-family: Serif; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;";
        String passwordFieldStyle = "-fx-border-color: red; -fx-border-radius: 7px; -fx-background-radius: 7px; -fx-font-family: Serif; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;";
        String radioStyle = "-fx-text-fill: blue; -fx-font-family: Serif;";
        String buttonStyle = "-fx-text-fill: brown; -fx-border-radius: 7px; -fx-background-radius: 7px; -fx-font-family: Serif;";

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10));

        ColumnConstraints col0 = new ColumnConstraints();
        col0.setHalignment(javafx.geometry.HPos.RIGHT);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(javafx.scene.layout.Priority.ALWAYS);
        root.getColumnConstraints().addAll(col0, col1);

        Label menoLabel = new Label("Užívateľské meno:");
        menoLabel.setStyle(labelStyle);
        TextField menoField = new TextField();
        menoField.setPromptText("zadaj meno");
        menoField.setStyle(textFieldStyle);
        menoField.setPrefWidth(110);
        menoField.setMaxWidth(110);

        Label hesloLabel = new Label("Heslo:");
        hesloLabel.setStyle(labelStyle);
        PasswordField hesloField = new PasswordField();
        hesloField.setPromptText("zadaj heslo");
        hesloField.setStyle(passwordFieldStyle);
        hesloField.setPrefWidth(110);
        hesloField.setMaxWidth(110);

        Label pohlavieLabel = new Label("Pohlavie:");
        pohlavieLabel.setStyle(labelStyle);

        ToggleGroup pohlavieGroup = new ToggleGroup();
        RadioButton muzRadio = new RadioButton("Muž");
        muzRadio.setStyle(radioStyle);
        muzRadio.setToggleGroup(pohlavieGroup);
        muzRadio.setSelected(true);

        RadioButton zenaRadio = new RadioButton("Žena");
        zenaRadio.setStyle(radioStyle);
        zenaRadio.setToggleGroup(pohlavieGroup);

        HBox pohlavieBox = new HBox(10, muzRadio, zenaRadio);
        pohlavieBox.setAlignment(Pos.CENTER_LEFT);

        Separator separator = new Separator();

        Button registerButton = new Button("Registrovať");
        registerButton.setStyle(buttonStyle);
        registerButton.setOnAction(e -> {
            String meno = menoField.getText();
            String heslo = hesloField.getText();
            String pohlavie = ((RadioButton) pohlavieGroup.getSelectedToggle()).getText();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registrácia užívateľa");
            alert.setHeaderText("Registrácia prebehla úspešne");
            alert.setContentText("Užívateľ " + meno + " (" + pohlavie + ") s heslom " + heslo + " bol pridaný do systému");
            alert.showAndWait();
        });

        Button closeButton = new Button("Zavrieť");
        closeButton.setStyle(buttonStyle);
        closeButton.setOnAction(e -> Platform.exit());

        HBox buttonsBox = new HBox(10, registerButton, closeButton);
        buttonsBox.setAlignment(Pos.TOP_RIGHT);

        root.add(menoLabel, 0, 0);
        root.add(menoField, 1, 0);
        root.add(hesloLabel, 0, 1);
        root.add(hesloField, 1, 1);
        root.add(pohlavieLabel, 0, 2);
        root.add(pohlavieBox, 1, 2);
        root.add(separator, 0, 3, 2, 1);
        root.add(buttonsBox, 0, 4, 2, 1);

        Scene scene = new Scene(root);

        stage.setTitle("Procedural Application 2");
        stage.setScene(scene);
        stage.show();
    }
}
