package chatbaby;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
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
    private ChatBaby baby;
    private final Image userImage;
    private final Image babyImage;

    /**
     * Constructs an MainWindow that handles NullPointer Exception
     */
    public MainWindow() {
        userImage = loadImage("/images/user.png");
        babyImage = loadImage("/images/chatBaby.png");
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the ChatBaby instance */
    public void setChatBaby(ChatBaby baby) {
        this.baby = baby;
        // Show the greeting message when the ChatBaby instance is set
        String greeting = baby.greet();
        dialogContainer.getChildren().add(DialogBox.getBabyDialog(greeting, babyImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing ChatBaby's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null && !input.trim().isEmpty() : "User input should not be null or empty";
        String response = baby.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBabyDialog(response, babyImage)
        );
        userInput.clear();
        if (response.equals(baby.bye())) {
            // Close the window after the bye message is shown
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
    }

    /**
     * Attempts to load an image from the folder 'resources'.
     * If the image cannot be found or loaded, returns an empty/transparent image.
     *
     * @param path The path to the image resource.
     * @return The loaded Image or a transparent fallback Image.
     */
    private Image loadImage(String path) {
        try {
            return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(path),
                    "Image resource not found: " + path));
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Failed to load image at: " + path + ". Using an empty image.");
            // Return an empty 1x1 transparent image as fallback
            return new Image("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAA"
                    + "BCAQAAAC1HAwCAAAAC0lEQVR42mP8/wcAAwAB/woL9Y4AAAAASUVORK5CYII=");
        }
    }
}
