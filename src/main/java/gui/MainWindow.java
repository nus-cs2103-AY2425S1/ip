package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mizz.Mizz;

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

    private Mizz mizz;

    private Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/RohitSharmaUser.png"));
    private Image mizzImage =
            new Image(this.getClass().getResourceAsStream("/images/RohitSharma.png"));

    /**
     * Method to initialize the controller.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getResponseDialog("Hello! I'm Mizz\nWhat can I do for you?", mizzImage));
    }

    /** Injects the Mizz instance */
    public void setMizz(Mizz m) {
        this.mizz = m;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = this.mizz.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getResponseDialog(response, mizzImage));
        userInput.clear();

        if (this.mizz.isExited()) {
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
        }
    }
}
