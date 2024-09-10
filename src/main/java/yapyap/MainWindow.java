package yapyap;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends VBox {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private YapperBot yapperBot;

    private Image userImage = loadImage("/images/User.png");
    private Image yapperBotImage = loadImage("/images/YapperBot.png");

    /**
     * Initializes the MainWindow components. This method is automatically called after
     * the FXML fields have been injected. It binds the vertical scroll position of the
     * ScrollPane to the height property of the dialog container, ensuring that the ScrollPane
     * always scrolls to the bottom when new dialog entries are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setYapperBot(YapperBot yapperBot) {
        this.yapperBot = yapperBot;
    }

    /**
     * Handles the user input from the TextField. This method retrieves the input text from the user,
     * processes it through the YapperBot to get a response, and displays both the user's input and
     * the bot's response in the dialog container.
     *
     * This method also clears the TextField after processing the input.
     *
     * The dialog boxes for the user and the bot are displayed using the `DialogBox` class,
     * with appropriate images for each.
     */
    @FXML
    private void handleUserInput() {
        assert userInput != null : "UserInput should not be null";

        String input = userInput.getText();
        assert input != null : "User input text should not be null";

        String response = yapperBot.getResponse(input);
        assert response != null : "Response from YapperBot should not be null";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYapperBotDialog(response, yapperBotImage)
        );
        userInput.clear();
    }

    /**
     * Loads an image from the given path.
     *
     * @param path The path to the image resource.
     * @return The loaded Image object.
     */
    private Image loadImage(String path) {
        return new Image(this.getClass().getResourceAsStream(path));
    }
}
