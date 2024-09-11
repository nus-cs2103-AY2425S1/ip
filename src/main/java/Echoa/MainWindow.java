package echoa;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * MainWindow is a class that encapsulates the main interface that the user interacts with.
 * It extends from class AnchorPane.
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

    private Echoa echoa;

    private Image echoaImage = new Image(this.getClass().getResourceAsStream("/images/Echoa.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Echoa instance */
    public void setEchoa(Echoa e) {
        echoa = e;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        echoa.start(input);
        response = echoa.getResponse();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, echoaImage)
        );
        userInput.clear();
    }
}
