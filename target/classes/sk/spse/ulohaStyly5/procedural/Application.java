package sk.spse.ulohaStyly5.procedural;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    private TableView<Potravina> tableView;
    private ObservableList<Potravina> data;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("root");

        Label titleLabel = new Label("Zoznam potravín");
        titleLabel.getStyleClass().add("title-label");

        tableView = new TableView<>();
        tableView.setPrefHeight(250);
        tableView.setPrefWidth(400);
        tableView.getStyleClass().add("table-view");

        TableColumn<Potravina, Number> idColumn = new TableColumn<>("ID");
        idColumn.setPrefWidth(50);
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        TableColumn<Potravina, String> nazovColumn = new TableColumn<>("Názov");
        nazovColumn.setPrefWidth(150);
        nazovColumn.setCellValueFactory(cellData -> cellData.getValue().nazovProperty());

        TableColumn<Potravina, Number> kalorieColumn = new TableColumn<>("Kalórie");
        kalorieColumn.setPrefWidth(100);
        kalorieColumn.setCellValueFactory(cellData -> cellData.getValue().kalorieProperty());

        TableColumn<Potravina, Number> cenaColumn = new TableColumn<>("Cena");
        cenaColumn.setPrefWidth(100);
        cenaColumn.setCellValueFactory(cellData -> cellData.getValue().cenaProperty());

        tableView.getColumns().addAll(idColumn, nazovColumn, kalorieColumn, cenaColumn);

        data = FXCollections.observableArrayList(
                new Potravina(0, "Chlieb", 200, 2.0),
                new Potravina(1, "Mlieko", 300, 0.65),
                new Potravina(2, "Kebab", 500, 12.5),
                new Potravina(3, "Coca Cola", 30, 1.39),
                new Potravina(4, "Jablko", 50, 0.99)
        );

        tableView.setItems(data);
        tableView.getSelectionModel().select(3);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));

        Button pridajButton = new Button("Pridaj potravinu");
        pridajButton.getStyleClass().add("action-button");
        pridajButton.setOnAction(e -> pridajPotravinu());

        Button vymazButton = new Button("Vymaž");
        vymazButton.getStyleClass().add("action-button");
        vymazButton.setOnAction(e -> vymaz());

        Button zatvorButton = new Button("Zatvor");
        zatvorButton.getStyleClass().add("action-button");
        zatvorButton.setOnAction(e -> Platform.exit());

        buttonBox.getChildren().addAll(pridajButton, vymazButton, zatvorButton);

        root.getChildren().addAll(titleLabel, tableView, buttonBox);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("Procedural Application 5");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

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

        VBox dialogRoot = new VBox(10, form, buttonBox);
        dialogRoot.setPadding(new Insets(10));

        Scene scene = new Scene(dialogRoot);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    private void vymaz() {
        Potravina selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            data.remove(selected);
        }
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
