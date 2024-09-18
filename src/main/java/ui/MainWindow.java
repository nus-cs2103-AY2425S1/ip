package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.KukiShinobu;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    // all these are references to MainWindow.fxml lol
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private KukiShinobu kukiShinobu;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/AetherAvatar.png"));
    private Image kukiShinobuImage =
            new Image(this.getClass().getResourceAsStream("/images/KukiShinobuAvatar.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void displayWelcomeMessage() {
        String welcomeMessage = this.kukiShinobu.generateWelcomeMessage();
        this.dialogContainer.getChildren().add(DialogBox.getKukiShinobuDialog(welcomeMessage, kukiShinobuImage));
    }

    /**
     * Injects the KukiShinobu instance
     */
    public void setKukiShinobu(KukiShinobu d) {
        kukiShinobu = d;
    }


    /**
     * Injects the Stage instance
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Checks if the KukiShinobu instance is null.
     *
     * @return true if kukiShinobu is null, false otherwise
     */
    public boolean isKukiShinobuNull() {
        return kukiShinobu == null;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kukiShinobu.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKukiShinobuDialog(response, kukiShinobuImage)
        );
        userInput.clear();

        // Checks if KukiShinobu::isExit is true, then exit the program if so
        if (kukiShinobu.isExit()) {
            stage.close();
        }
    }
}
