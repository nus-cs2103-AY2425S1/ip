package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.MainWindow;
import javafx.scene.image.Image;


import java.io.IOException;
import java.util.Objects;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private KukiShinobu kukiShinobu = new KukiShinobu();

    private static final String APPLICATION_TITLE = "Kuki Shinobu";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Get the controller and set the KukiShinobu instance and the Stage reference
            MainWindow controller = fxmlLoader.getController();
            controller.setKukiShinobu(kukiShinobu);

            // Assert that the kukiShinobu field of MainWindow is not null
            assert kukiShinobu != null : "kukiShinobu should not be null";

            // Set the title of the window
            stage.setTitle(Main.APPLICATION_TITLE);

            // Set the app logo to the avatar of Kuki Shinobu
            Image icon = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/KukiShinobuAvatar.png")));
            stage.getIcons().add(icon);


            controller.setStage(stage);
            stage.show();
            controller.displayWelcomeMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
