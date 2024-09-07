package rainy.gui;

import java.io.IOException;
import java.io.PrintStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rainy.database.Rainy;
import rainy.database.UI;

/**
 * Loads the FXML file and initiates the GUI.
 */
public class Main extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image rainyImage = new Image(this.getClass().getResourceAsStream("/images/rainybot.png"));
    private Rainy rainy = new Rainy();

    /**
     * Loads the GUI for Rainy chatbot.
     * @param stage the primary stage for this application, onto which
     *        the application scene can be set.
     *        Applications may create other stages, if needed, but they will not be
     *        primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainController.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("RAINYBOT");
            MainController mainController = fxmlLoader.<MainController>getController();
            mainController.setRainy(rainy);
            this.dialogContainer = mainController.getDialogContainer();
            NewOutputStream newOutputStream = new NewOutputStream(dialogContainer, rainyImage);
            PrintStream ps = new PrintStream(newOutputStream);
            System.setOut(ps);
            UI ui = new UI();
            ui.welcomeMessage();
            ui.startTracking();
            System.out.print("^");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
