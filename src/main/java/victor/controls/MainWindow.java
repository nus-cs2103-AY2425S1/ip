package victor.controls;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import victor.Handler;

/**
 * Controller for the main GUI.
 */
public class MainWindow {
    private static final String WELCOME_MESSAGE = "Hi, I'm Victor! Let's get started with tracking your tasks!";
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private ScrollPane scrollPane;
    private Stage stage;
    private Handler handler;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/toucan.png"));
    private Image victorImage = new Image(this.getClass().getResourceAsStream("/images/victor.png"));

    /**
     * Sets Stage instance used by main.
     * @param stage A Stage object used to run the application.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Initialises a handler for the main window which handles user input.
     */
    public void setHandler() {
        this.handler = new Handler();
    }

    /**
     * Sets a listener for the height of the dialog container to scroll the bottom when
     * text goes beyond bottom of window.
     */
    public void setScrollListener() {
        this.dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates an initial welcome message for the user upon starting the program.
     */
    public void welcomeUser() {
        dialogContainer.getChildren().addAll(
                DialogBox.getVictorDialog(WELCOME_MESSAGE, victorImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().equalsIgnoreCase("bye")) {
            stage.close();
        }
        String response = handler.handleRequest(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getVictorDialog(response, victorImage)
        );
        userInput.clear();
    }
}
