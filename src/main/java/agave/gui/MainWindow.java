package agave.gui;

import agave.Agave;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private VBox dialogContainer = new VBox();

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    @FXML
    private ScrollPane scrollPane;

    private Agave agave;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image agaveImage = new Image(this.getClass().getResourceAsStream("/images/DaAgave.png"));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(1.0);
        });    }

    public void setAgave(Agave a) {
        agave = a;
    }

    /**
     * Handles the user input when the Send button is clicked or Enter is pressed in the text field.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = agave.getResponse(input);

        boolean isError = response.startsWith("Uh-oh:");

        if (isError) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getErrorDialog(response, agaveImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getBotDialog(response, agaveImage)
            );
        }

        userInput.clear();
    }
}
