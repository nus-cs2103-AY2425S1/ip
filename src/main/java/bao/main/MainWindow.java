package bao.main;

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

    private Bao bao;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image baoImage = new Image(this.getClass().getResourceAsStream("/images/bao.png"));

    /**
     * Initializes the dialogue container and scroll pane
     *
     * */
    @FXML
    public void initialize() {
        assert scrollPane != null : "ScrollPane should not be null";
        assert dialogContainer != null : "DialogueContainer should not be null";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Bao instance
     *
     * @param bao A Bao instance is taken as a parameter
     * */
    public void setBao(Bao bao) {
        assert bao != null : "Bao instance should not be null";
        this.bao = bao;
        showWelcomeMessage();
    }

    private void showWelcomeMessage() {
        String welcomeMessage = bao.getResponse("welcome");
        dialogContainer.getChildren().add(DialogBox.getBaoDialog(welcomeMessage, baoImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }

        String response = Parser.parse(input, bao.getTaskList(), bao.getStorage());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBaoDialog(response, baoImage)
        );
        userInput.clear();
    }
}
