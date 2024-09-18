package jude.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jude.Jude;

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

    private Jude jude;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image judeImage = new Image(this.getClass().getResourceAsStream("/images/jude.png"));

    /**
     * Initializes the chatbot screen and displays a welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Jude instance.
     */
    public void setJude(Jude d) {
        jude = d;
    }

    /**
     * Displays the welcome message.
     */
    public void displayWelcomeMessage() {
        String welcomeMessage = this.jude.generateWelcomeMessage();
        this.dialogContainer.getChildren().add(DialogBox.getJudeDialog(welcomeMessage, judeImage));
    }

    /**
     * Sets the Stage instance.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Jude's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jude.getResponse(input);
        String commandType = jude.getCommandType();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJudeDialog(response, judeImage, commandType)
        );

        if (jude.isExit()) {
            stage.close();
        }
        userInput.clear();
    }
}
