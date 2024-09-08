package yappingbot.ui.gui;

import static yappingbot.Launcher.savefilePath;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import yappingbot.YappingBot;
import yappingbot.storage.Storage;

/**
 * MainGuiApplication class to launchGui GUI Application.
 */
public class MainGuiApplication extends Application {

    private UiGui ui;

    @Override
    public void start(Stage stage) {
        ui = new UiGui();

        try {
            URL mainWindowResource = MainGuiApplication.class.getResource("/view/MainWindow.fxml");
            FXMLLoader fxmlloader = new FXMLLoader(mainWindowResource);

            VBox vb = fxmlloader.load();
            Scene scene = new Scene(vb);

            stage.setScene(scene);
            fxmlloader.<MainWindow>getController().setUi(ui);

            stage.show();


            // TODO: pass savefilePath instead of importing
            YappingBot yp = new YappingBot(ui, new Storage(savefilePath));
            yp.start();

        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }
}
