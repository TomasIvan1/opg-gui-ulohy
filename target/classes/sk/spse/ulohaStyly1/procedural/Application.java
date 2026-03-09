package sk.spse.ulohaStyly1.procedural;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Application extends javafx.application.Application {

    private boolean updating;
    private TextField celsiusField;
    private TextField fahrenheitField;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        root.getStylesheets().add(getClass().getResource("/sk/spse/ulohaStyly1/declarative/styles.css").toExternalForm());

        HBox celsiusRow = new HBox(20);
        celsiusRow.setAlignment(Pos.CENTER_RIGHT);
        celsiusRow.setPadding(new Insets(10));
        Label celsiusLabel = new Label("Stupne Celsia:");
        celsiusField = new TextField("0");
        celsiusField.setPrefWidth(182);
        celsiusField.setStyle("-fx-padding: 10 20 10 20;");
        celsiusField.setOnKeyTyped(e -> onCelsiusChange());
        Label celsiusUnit = new Label("°C");
        celsiusRow.getChildren().addAll(celsiusLabel, celsiusField, celsiusUnit);

        HBox fahrenheitRow = new HBox(20);
        fahrenheitRow.setAlignment(Pos.CENTER_RIGHT);
        fahrenheitRow.setPadding(new Insets(10));
        Label fahrenheitLabel = new Label("Stupne Fahrenheita:");
        fahrenheitField = new TextField("0");
        fahrenheitField.setPrefWidth(185);
        fahrenheitField.setStyle("-fx-padding: 10 20 10 20;");
        fahrenheitField.setOnKeyTyped(e -> onFahrenheitChange());
        Label fahrenheitUnit = new Label("°F");
        fahrenheitRow.getChildren().addAll(fahrenheitLabel, fahrenheitField, fahrenheitUnit);

        root.getChildren().addAll(celsiusRow, fahrenheitRow);

        Scene scene = new Scene(root);

        stage.setTitle("Procedural Application 1");
        stage.setScene(scene);
        stage.show();
    }

    private void onCelsiusChange() {
        if (updating) {
            return;
        }

        String input = celsiusField.getText();
        if (input == null || input.trim().isEmpty() || input.equals("-") || input.equals(".")) {
            updating = true;
            try {
                fahrenheitField.setText("");
            } finally {
                updating = false;
            }
            return;
        }

        try {
            double celsius = Double.parseDouble(input);
            double fahrenheit = celsius * 1.8 + 32;
            String fahrenheitString = String.format("%.2f", fahrenheit);
            updating = true;
            try {
                fahrenheitField.setText(fahrenheitString);
            } finally {
                updating = false;
            }
        } catch (NumberFormatException e) {
            updating = true;
            try {
                fahrenheitField.setText("");
            } finally {
                updating = false;
            }
        }
    }

    private void onFahrenheitChange() {
        if (updating) {
            return;
        }

        String input = fahrenheitField.getText();
        if (input == null || input.trim().isEmpty() || input.equals("-") || input.equals(".")) {
            updating = true;
            try {
                celsiusField.setText("");
            } finally {
                updating = false;
            }
            return;
        }

        try {
            double fahrenheit = Double.parseDouble(input);
            double celsius = (fahrenheit - 32) / 1.8;
            String celsiusString = String.format("%.2f", celsius);
            updating = true;
            try {
                celsiusField.setText(celsiusString);
            } finally {
                updating = false;
            }
        } catch (NumberFormatException e) {
            updating = true;
            try {
                celsiusField.setText("");
            } finally {
                updating = false;
            }
        }
    }
}