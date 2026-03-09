package sk.spse.uloha1.procedural;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

///
/// Trieda pre procedurálne vytvorené GUI
///
/// Upravujte túto triedu
///

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) {

        String fieldStyle = "-fx-padding: 10 20 10 20;";

        Label celsiusLabel = new Label("Stupne Celsia:");
        TextField celsiusField = new TextField("0");
        celsiusField.setPrefWidth(182.0);
        celsiusField.setStyle(fieldStyle);
        Label celsiusUnitLabel = new Label("°C");

        HBox celsiusRow = new HBox(20, celsiusLabel, celsiusField, celsiusUnitLabel);
        celsiusRow.setAlignment(Pos.CENTER_RIGHT);
        celsiusRow.setPadding(new Insets(10));

        Label fahrenheitLabel = new Label("Stupne Fahrenheita:");
        TextField fahrenheitField = new TextField("0");
        fahrenheitField.setPrefWidth(185.0);
        fahrenheitField.setStyle(fieldStyle);
        Label fahrenheitUnitLabel = new Label("°F");

        HBox fahrenheitRow = new HBox(20, fahrenheitLabel, fahrenheitField, fahrenheitUnitLabel);
        fahrenheitRow.setAlignment(Pos.CENTER_RIGHT);
        fahrenheitRow.setPadding(new Insets(10));

        final boolean[] updating = {false};

        Runnable recalcFromCelsius = () -> {
            if (updating[0]) {
                return;
            }
            updating[0] = true;
            try {
                String text = celsiusField.getText();
                if (text == null || text.trim().isEmpty() || text.equals("-") || text.equals(".")) {
                    fahrenheitField.setText("");
                    return;
                }
                double celsius = Double.parseDouble(text.replace(',', '.'));
                double fahrenheit = celsius * 1.8 + 32;
                String fahrenheitString = String.format("%.2f", fahrenheit);
                fahrenheitField.setText(fahrenheitString);
            } catch (NumberFormatException ex) {
                fahrenheitField.setText("");
            } finally {
                updating[0] = false;
            }
        };

        Runnable recalcFromFahrenheit = () -> {
            if (updating[0]) {
                return;
            }
            updating[0] = true;
            try {
                String text = fahrenheitField.getText();
                if (text == null || text.trim().isEmpty() || text.equals("-") || text.equals(".")) {
                    celsiusField.setText("");
                    return;
                }
                double fahrenheit = Double.parseDouble(text.replace(',', '.'));
                double celsius = (fahrenheit - 32) / 1.8;
                String celsiusString = String.format("%.2f", celsius);
                celsiusField.setText(celsiusString);
            } catch (NumberFormatException ex) {
                celsiusField.setText("");
            } finally {
                updating[0] = false;
            }
        };

        celsiusField.setOnKeyTyped(e -> recalcFromCelsius.run());
        fahrenheitField.setOnKeyTyped(e -> recalcFromFahrenheit.run());
        recalcFromCelsius.run();

        VBox root = new VBox(celsiusRow, fahrenheitRow);

        Scene scene = new Scene(root);

        stage.setTitle("Procedural Application 1");
        stage.setScene(scene);
        stage.show();
    }
}