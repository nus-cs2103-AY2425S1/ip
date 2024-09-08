package yappingbot.ui.gui;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import yappingbot.Launcher;
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

            stage.setOnCloseRequest(e -> {
                ui.setProgramClose(true);
                // HACK: we're just hoping yp cleans up faster than this
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(ex);
                }
                Platform.exit();
                System.exit(0);
            });


            stage.show();
            YappingBot yp = new YappingBot(ui, new Storage(Launcher.getSavefilePath()));
            // yp.start();

            // reach here when yp stops
            ui.setProgramClose(true);


        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }
}
