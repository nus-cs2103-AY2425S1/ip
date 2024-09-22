package botimusprime;

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

    private BotimusPrime botimusPrime;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/ShiaLaBeouf.jpg"));
    private Image botimusPrimeImage = new Image(this.getClass().getResourceAsStream("/images/botimusPrime.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setBotimusPrime(BotimusPrime d) {
        botimusPrime = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing BotimusPrime's reply and then appends
     * them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();

        if (input.equals("bye")) {
            System.exit(0);
        }

        String response = botimusPrime.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotimusPrimeDialog(response, botimusPrimeImage)
        );

        userInput.clear();
    }
}

