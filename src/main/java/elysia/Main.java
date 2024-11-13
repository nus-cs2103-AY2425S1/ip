package elysia;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.TimeZone;

import elysia.exception.InvalidFileFormatException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main application class for Elysia, which extends {@link Application}. This class sets up the main window of the
 * application and initializes the Elysia instance.
 */
public class Main extends Application {
    private Elysia elysia = new Elysia();

    public Main() throws IOException, InvalidFileFormatException {
    }

    @Override
    public void start(Stage stage) {
        try {
            // prevent unexpected error from time parser and date parser
            Locale.setDefault(new Locale("en_US", "SG"));
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Singapore"));

            stage.setTitle("Elysia");
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            URL fxmlLoader2 = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml")).getLocation();
            System.out.println(fxmlLoader2.toString());
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setElysia(elysia); // inject the Elysia instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
