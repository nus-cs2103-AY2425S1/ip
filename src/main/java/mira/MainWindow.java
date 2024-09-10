package mira;

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

    private Mira mira;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image miraImage = new Image(this.getClass().getResourceAsStream("/images/DaMira.png"));

    /**
     * Initializes the UI components and sets up necessary bindings.
     * <p>
     * This method is called automatically when the associated FXML file is loaded.
     * It binds the vertical scroll position of the {@code scrollPane} to the height of the {@code dialogContainer}
     * so that the scroll view automatically scrolls to the bottom when new messages are added.
     * <p>
     * Additionally, this method displays a welcome message from Mira in the dialog container
     * and sets a placeholder prompt text for the user input field.
     * </p>
     *
     * @implNote The welcome message is added to the {@code dialogContainer} using the {@code DialogBox} utility,
     *     and it includes an image representing Mira.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // show welcome message
        dialogContainer.getChildren().add(
                DialogBox.getMiraDialog("Hello! I'm Mira\nWhat can I do for you?", miraImage)
        );
        userInput.setPromptText("Type here...");
    }

    /**
     * Injects the Mira instance
     */
    public void setMira(Mira d) {
        mira = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mira.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMiraDialog(response, miraImage)
        );
        userInput.clear();
    }
}

