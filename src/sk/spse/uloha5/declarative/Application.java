package sk.spse.uloha5.declarative;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

///
/// Trieda pre deklaratívne vytvorené GUI
///
/// Upravujte hlavne FXML súbor a Controller, nie túto triedu!
///

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("primary.fxml"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 520, 410);

        stage.setTitle("Declarative Application 5");
        stage.setScene(scene);
        stage.setMinWidth(520);
        stage.setMinHeight(410);
        stage.show();
    }
}