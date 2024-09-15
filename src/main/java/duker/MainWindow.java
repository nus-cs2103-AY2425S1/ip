package duker;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The MainWindow class serves as the controller for the main user interface of the Duker application.
 * It handles user interactions, including text input and button clicks,
 * and manages the dialog between the user and Duker.
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

    private Duker duker;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukerImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sends a greeting from Duker to the dialog container when the application starts.
     * This method is called to display Duker's initial greeting message.
     */
    @FXML
    public void sendGreeting() {
        dialogContainer.getChildren().add(DialogBox.getDukerGreeting(
                duker.getGreeting(), dukerImage));
    }

    /**
     * Sets the stage for the main window. This is used to control the window, such as closing it.
     *
     * @param s The stage to set.
     */
    public void setStage(Stage s) {
        stage = s;
    }

    /**
     * Sets the Duker instance for this window.
     * This allows the window to interact with the Duker application logic.
     *
     * @param d The Duker instance to set.
     */
    public void setDuker(Duker d) {
        duker = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duker.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukerDialog(response, dukerImage)
        );
        userInput.clear();

        if (!duker.isOnline()) {
            Platform.runLater(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stage.close();
                Platform.exit();
            });
        }
    }
}
