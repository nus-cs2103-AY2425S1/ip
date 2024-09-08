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

    private static final UiGui ui = new UiGui();

    @Override
    public void start(Stage stage) {
        stage.setOnCloseRequest(e -> {
            ui.setProgramClose(true);
            Platform.exit();
            System.exit(0);
        });

        try {
            URL mainWindowResource = MainGuiApplication.class.getResource("/view/MainWindow.fxml");
            FXMLLoader fxmlloader = new FXMLLoader(mainWindowResource);

            VBox vb = fxmlloader.load();
            Scene scene = new Scene(vb);

            stage.setScene(scene);
            fxmlloader.<MainWindow>getController().setUi(ui);


            stage.show();
            YappingBot yp = new YappingBot(ui, new Storage(Launcher.getSavefilePath()));
            Thread ypLogic = new Thread(yp::start);
            ypLogic.start();

        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }
}
