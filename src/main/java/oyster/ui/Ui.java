package oyster.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oyster.Oyster;

/**
 * Ui class that handles user interface.
 */
public class Ui {
    private static final int LINE_LENGTH = 50;

    private MainWindow mainWindow;

    /**
     * Creates the Ui object.
     *
     * @param stage The base Stage provided by javaFX.
     */
    public Ui(Oyster oyster, Stage stage) {
        stage.setTitle(Oyster.CHATBOT_NAME);
        stage.setResizable(false);

        try {
            mainWindow = new MainWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Oyster.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setRoot(mainWindow);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setOyster(oyster); // inject the Oyster instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
