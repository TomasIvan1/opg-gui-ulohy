package sk.spse.ulohaStyly5.declarative;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private TableView<Potravina> tableView;

    @FXML
    private TableColumn<Potravina, Number> idColumn;

    @FXML
    private TableColumn<Potravina, String> nazovColumn;

    @FXML
    private TableColumn<Potravina, Number> kalorieColumn;

    @FXML
    private TableColumn<Potravina, Number> cenaColumn;

    private ObservableList<Potravina> data;

    @FXML
    public void initialize() {
        data = FXCollections.observableArrayList(
                new Potravina(0, "Chlieb", 200, 2.0),
                new Potravina(1, "Mlieko", 300, 0.65),
                new Potravina(2, "Kebab", 500, 12.5),
                new Potravina(3, "Coca Cola", 30, 1.39),
                new Potravina(4, "Jablko", 50, 0.99)
        );

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nazovColumn.setCellValueFactory(cellData -> cellData.getValue().nazovProperty());
        kalorieColumn.setCellValueFactory(cellData -> cellData.getValue().kalorieProperty());
        cenaColumn.setCellValueFactory(cellData -> cellData.getValue().cenaProperty());

        tableView.setItems(data);
        tableView.getSelectionModel().select(3);
    }

    @FXML
    private void pridajPotravinu() {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Pridaj potravinu");

        Label nazovLabel = new Label("Názov:");
        TextField nazovField = new TextField();
        nazovField.setPrefWidth(200);

        Label kalorieLabel = new Label("Kalórie:");
        TextField kalorieField = new TextField();
        kalorieField.setPrefWidth(200);

        Label cenaLabel = new Label("Cena:");
        TextField cenaField = new TextField();
        cenaField.setPrefWidth(200);

        VBox form = new VBox(10, nazovLabel, nazovField, kalorieLabel, kalorieField, cenaLabel, cenaField);
        form.setPadding(new Insets(20));

        Button ulozitButton = new Button("Uložiť");
        Button zrusitButton = new Button("Zrušiť");

        ulozitButton.setOnAction(e -> {
            try {
                String nazov = nazovField.getText();
                int kalorie = Integer.parseInt(kalorieField.getText());
                double cena = Double.parseDouble(cenaField.getText());

                int newId = data.size();
                data.add(new Potravina(newId, nazov, kalorie, cena));
                dialogStage.close();
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Chyba");
                alert.setHeaderText("Neplatné údaje");
                alert.setContentText("Kalórie a cena musia byť čísla.");
                alert.showAndWait();
            }
        });

        zrusitButton.setOnAction(e -> dialogStage.close());

        HBox buttonBox = new HBox(10, ulozitButton, zrusitButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        VBox root = new VBox(10, form, buttonBox);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    @FXML
    private void vymaz() {
        Potravina selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            data.remove(selected);
        }
    }

    @FXML
    private void zatvor() {
        Platform.exit();
    }

    public static class Potravina {
        private final SimpleIntegerProperty id;
        private final SimpleStringProperty nazov;
        private final SimpleIntegerProperty kalorie;
        private final SimpleDoubleProperty cena;

        public Potravina(int id, String nazov, int kalorie, double cena) {
            this.id = new SimpleIntegerProperty(id);
            this.nazov = new SimpleStringProperty(nazov);
            this.kalorie = new SimpleIntegerProperty(kalorie);
            this.cena = new SimpleDoubleProperty(cena);
        }

        public SimpleIntegerProperty idProperty() {
            return id;
        }

        public SimpleStringProperty nazovProperty() {
            return nazov;
        }

        public SimpleIntegerProperty kalorieProperty() {
            return kalorie;
        }

        public SimpleDoubleProperty cenaProperty() {
            return cena;
        }
    }
}
