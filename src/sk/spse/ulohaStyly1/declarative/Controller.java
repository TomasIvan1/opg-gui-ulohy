package sk.spse.ulohaStyly1.declarative;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {

    private boolean updating;

    @FXML
    private TextField celsiusField;

    @FXML
    private TextField fahrenheitField;

    @FXML
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

    @FXML
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
