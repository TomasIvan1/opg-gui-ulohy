package sk.spse.ulohaStyly2.declarative;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller {

    @FXML
    private TextField menoField;

    @FXML
    private PasswordField hesloField;

    @FXML
    private RadioButton muzRadio;

    @FXML
    private RadioButton zenaRadio;

    private ToggleGroup pohlavieGroup;

    @FXML
    private void initialize() {
        pohlavieGroup = new ToggleGroup();
        muzRadio.setToggleGroup(pohlavieGroup);
        zenaRadio.setToggleGroup(pohlavieGroup);
    }

    @FXML
    private void close() {
        Platform.exit();
    }

    @FXML
    private void register() {
        String meno = menoField.getText();
        String heslo = hesloField.getText();
        String pohlavie = ((RadioButton) pohlavieGroup.getSelectedToggle()).getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registrácia užívateľa");
        alert.setHeaderText("Registrácia prebehla úspešne");
        alert.setContentText("Užívateľ " + meno + " (" + pohlavie + ") s heslom " + heslo + " bol pridaný do systému");
        alert.showAndWait();
    }
}
