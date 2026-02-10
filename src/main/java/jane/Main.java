package jane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.text.Font;


public class Main extends Application {
    private JaneEyre jane;

    public void start(Stage stage) {
        try {
            Font.loadFont(getClass().getResourceAsStream("/fonts/Baskervville-VariableFont_wght.ttf"), 10);
            jane = new JaneEyre("data/janeeyre.txt");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/main.css")).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/dialog-box.css")).toExternalForm());
            fxmlLoader.<MainWindow>getController().setJane(jane);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}