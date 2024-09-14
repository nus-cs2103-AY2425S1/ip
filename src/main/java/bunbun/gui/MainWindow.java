package bunbun.gui;

import bunbun.Bunbun;
import javafx.application.Platform;
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

    private Bunbun bunbun;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image bunbunImage = new Image(this.getClass().getResourceAsStream("/images/DaBunbun.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Bunbun instance */
    public void setBunbun(Bunbun b) {
        bunbun = b;
        bunbun.start();
        String welcomeMsg = "Bunbun: Hi! How can I help you? ^^";
        dialogContainer.getChildren().addAll(DialogBox.getBunbunDialog(welcomeMsg, bunbunImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bunbun's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            handleClose();
            Platform.exit();
        }
        String response = bunbun.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBunbunDialog(response, bunbunImage)
        );
        userInput.clear();
    }

    private void handleClose() {
        bunbun.close();
    }
}
