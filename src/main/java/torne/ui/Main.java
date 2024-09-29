package torne.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * This is a GUI class - so it needs to extend `Application`
 */
public class Main extends Application {

    // note that if you need to have a constructor with arguments, create an **overloaded** constructor with no args
    // that calls the original constructor. (Check tutorial pt 1)

    public Main() {
        Font.loadFont(getClass().getResourceAsStream("/fonts/CaskaydiaCoveNerdFont-SemiBold.ttf"), 18);
        Font.loadFont(getClass().getResourceAsStream("/fonts/CaskaydiaCoveNerdFont-Regular.ttf"), 18);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Inter-Medium.ttf"), 18);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Montserrat-SemiBold.ttf"), 18);
    }

    private final Torne torne = new Torne(); // Init Torne

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // set up app icon
            Image appIcon = new Image("/images/torne_icon.png");
            stage.getIcons().add(appIcon);

            stage.setTitle("Torne");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTorne(torne);  // inject the Torne instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}