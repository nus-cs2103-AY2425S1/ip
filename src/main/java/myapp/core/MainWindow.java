package myapp.core;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * Controller for the main GUI. Manages the main window of the application,
 * handling user input and displaying the bot's responses.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    /** The instance of the BingBongBot that handles user interactions and commands. */
    private BingBongBot bot;

    /** The stage representing the main window of the application. */
    private Stage stage;

    /** The image representing the user in the dialog boxes. */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    /** The image representing the bot in the dialog boxes. */
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/BingBong.png"));

    /**
     * Constructs a MainWindow and sets up the primary stage.
     *
     * @param stage The primary stage for this application, onto which the main window is set.
     */
    public MainWindow(Stage stage) {
        this.stage = stage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();

            Scene scene = new Scene(this);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the main window. Binds the scroll pane's vertical scroll value to the dialog container's height
     * to automatically scroll down as new messages are added. Displays an initial greeting from the bot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getBingBongDialog("Hello! I'm BingBong\n" + "What can I do for you?", botImage)
        );
    }

    /**
     * Injects the BingBongBot instance to be used by this controller.
     *
     * @param b The BingBongBot instance to handle user input and generate responses.
     */
    public void setBingBong(BingBongBot b) {
        bot = b;
    }

    /**
     * Handles user input when the send button is clicked or Enter is pressed.
     * Creates two dialog boxes: one for the user's input and one for the bot's response.
     * If the bot's response indicates an exit command, the application window is closed.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bot.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBingBongDialog(response, botImage)
        );

        if (bot.isExit()) {
            stage.close();
        }

        userInput.clear();
    }

    /**
     * Shows the stage, making the application window visible to the user.
     */
    public void show() {
        stage.show();
    }
}
