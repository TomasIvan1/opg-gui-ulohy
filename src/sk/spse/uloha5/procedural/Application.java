package sk.spse.uloha5.procedural;

import javafx.application.Platform;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    private TableView<Jedlo> tabulka;
    private ObservableList<Jedlo> jedloList;
    private int nextId = 0;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));

        Label title = new Label("Zoznam potravín");
        title.setStyle("-fx-font-size: 24px;");
        VBox titleBox = new VBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(0, 0, 10, 0));
        root.setTop(titleBox);

        tabulka = new TableView<>();
        tabulka.setMaxWidth(500);
        tabulka.setPrefHeight(260);
        tabulka.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        TableColumn<Jedlo, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(60);

        TableColumn<Jedlo, String> nazovColumn = new TableColumn<>("Názov");
        nazovColumn.setCellValueFactory(new PropertyValueFactory<>("nazov"));
        nazovColumn.setPrefWidth(200);

        TableColumn<Jedlo, Integer> kalorieColumn = new TableColumn<>("Kalórie");
        kalorieColumn.setCellValueFactory(new PropertyValueFactory<>("kalorie"));
        kalorieColumn.setPrefWidth(120);

        TableColumn<Jedlo, Double> cenaColumn = new TableColumn<>("Cena");
        cenaColumn.setCellValueFactory(new PropertyValueFactory<>("cena"));
        cenaColumn.setPrefWidth(120);

        tabulka.getColumns().addAll(idColumn, nazovColumn, kalorieColumn, cenaColumn);

        HBox tableContainer = new HBox(tabulka);
        tableContainer.setAlignment(Pos.CENTER);
        root.setCenter(tableContainer);

        jedloList = FXCollections.observableArrayList();
        jedloList.add(new Jedlo(nextId++, "Chlieb", 200, 2.0));
        jedloList.add(new Jedlo(nextId++, "Mlieko", 300, 0.65));
        jedloList.add(new Jedlo(nextId++, "Kebab", 500, 12.5));
        jedloList.add(new Jedlo(nextId++, "Coca Cola", 30, 1.39));
        jedloList.add(new Jedlo(nextId++, "Jablko", 50, 0.99));
        tabulka.setItems(jedloList);

        Button pridajButton = new Button("Pridaj potravinu");
        pridajButton.setOnAction(e -> zobrazPridajDialog());

        Button vymazButton = new Button("Vymaž");
        vymazButton.setOnAction(e -> vymaz());

        Button zatvorButton = new Button("Zatvor");
        zatvorButton.setOnAction(e -> Platform.exit());

        HBox buttonBox = new HBox(10, pridajButton, vymazButton, zatvorButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setPadding(new Insets(5, 0, 0, 0));
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 520, 410);
        stage.setTitle("Procedural Application 5");
        stage.setScene(scene);
        stage.setMinWidth(520);
        stage.setMinHeight(410);
        stage.show();
    }

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

                jedloList.add(new Jedlo(nextId++, nazov, kalorie, cena));
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
        Jedlo selected = tabulka.getSelectionModel().getSelectedItem();
        if (selected != null) {
            jedloList.remove(selected);
        }
    }
}