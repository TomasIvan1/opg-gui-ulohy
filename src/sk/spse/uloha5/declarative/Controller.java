package sk.spse.uloha5.declarative;

import javafx.application.Platform;
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
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Controller {

    @FXML
    private TableView<Jedlo> tabulka;

    @FXML
    private TableColumn<Jedlo, Integer> idColumn;

    @FXML
    private TableColumn<Jedlo, String> nazovColumn;

    @FXML
    private TableColumn<Jedlo, Integer> kalorieColumn;

    @FXML
    private TableColumn<Jedlo, Double> cenaColumn;

    private ObservableList<Jedlo> jedloList;
    private int nextId = 0;

    @FXML
    private void initialize() {
        jedloList = FXCollections.observableArrayList();
        jedloList.add(new Jedlo(nextId++, "Chlieb", 200, 2.0));
        jedloList.add(new Jedlo(nextId++, "Mlieko", 300, 0.65));
        jedloList.add(new Jedlo(nextId++, "Kebab", 500, 12.5));
        jedloList.add(new Jedlo(nextId++, "Coca Cola", 30, 1.39));
        jedloList.add(new Jedlo(nextId++, "Jablko", 50, 0.99));

        tabulka.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tabulka.setFixedCellSize(30);
        idColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getId()).asObject());
        nazovColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getNazov()));
        kalorieColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKalorie()).asObject());
        cenaColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getCena()).asObject());
        tabulka.getItems().setAll(jedloList);
    }

    @FXML
    private void zobrazPridajDialog() {
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

                Jedlo noveJedlo = new Jedlo(nextId++, nazov, kalorie, cena);
                jedloList.add(noveJedlo);
                tabulka.getItems().add(noveJedlo);
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
        Jedlo selected = tabulka.getSelectionModel().getSelectedItem();
        if (selected != null) {
            jedloList.remove(selected);
            tabulka.getItems().remove(selected);
        }
    }

    @FXML
    private void zatvor() {
        Platform.exit();
    }
}
