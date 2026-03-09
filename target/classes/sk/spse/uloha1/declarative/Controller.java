package sk.spse.uloha1.declarative;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller pre FXML súbor – obsahuje logiku aplikácie
 */
public class Controller {

    @FXML
    private TextField celsiusField;

    @FXML
    private TextField fahrenheitField;

    @FXML
    private void onCelsiusChange() {
        String text = celsiusField.getText();
        if (text == null || text.trim().isEmpty() || text.equals("-") || text.equals(".")) {
            fahrenheitField.setText("");
            return;
        }
        try {
            double celsius = Double.parseDouble(text.replace(',', '.'));
            double fahrenheit = celsius * 1.8 + 32;
            String fahrenheitString = String.format("%.2f", fahrenheit);
            fahrenheitField.setText(fahrenheitString);
        } catch (NumberFormatException ex) {
            fahrenheitField.setText("");
        }
    }

    @FXML
    private void onFahrenheitChange() {
        String text = fahrenheitField.getText();
        if (text == null || text.trim().isEmpty() || text.equals("-") || text.equals(".")) {
            celsiusField.setText("");
            return;
        }
        try {
            double fahrenheit = Double.parseDouble(text.replace(',', '.'));
            double celsius = (fahrenheit - 32) / 1.8;
            String celsiusString = String.format("%.2f", celsius);
            celsiusField.setText(celsiusString);
        } catch (NumberFormatException ex) {
            celsiusField.setText("");
        }
    }
}
