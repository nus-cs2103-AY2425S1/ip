package gutti;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI. This class handles user interactions with the graphical user interface.
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

    private Gutti gutti;

    private Image userImage;
    private Image guttiImage;

    /**
     * Initializes the MainWindow controller.
     * Sets up the images for the user and Gutti avatars and binds the scroll pane to scroll as new dialog entries are added.
     */
    @FXML
    public void initialize() {
        // Initialize images here
        userImage = new Image(getClass().getResourceAsStream("/Image/DaUser.png"));
        guttiImage = new Image(getClass().getResourceAsStream("/Image/DaGutti.png"));

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Gutti instance into the MainWindow controller.
     *
     * @param g The Gutti instance to handle responses.
     */
    public void setGutti(Gutti g) {
        gutti = g;
        guiGreetings();

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Gutti's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = gutti.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGuttiDialog(response, guttiImage)
        );
        userInput.clear();
    }

    /**
     * Displays a greeting message from Gutti when the application starts.
     */
    private void guiGreetings() {
        String msg = "Hello! I'm Gutti What can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getGuttiDialog(msg,guttiImage));
    }
}