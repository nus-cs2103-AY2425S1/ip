package momo;

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

    private Momo momo;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/userIcon.png"));
    private final Image momoImage = new Image(this.getClass().getResourceAsStream("/images/momoIcon.png"));

    /**
     * Initializes the UI components of the main window. This method binds the vertical scroll
     * property of the scroll pane to the height of the dialog container to ensure automatic
     * scrolling as new messages are added. It also displays an initial greeting message
     * from Momo when the application starts.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String greeting = "Greetings mortal... I am Ṁ̴̙O̵̖̓M̷͇̈O̸̠͋.\nWhat will you do for me?";

        dialogContainer.getChildren().addAll(
                DialogBox.getMomoDialog(greeting, momoImage)
        );
    }

    /** Injects the Momo instance */
    public void setMomo() {
        this.momo = new Momo(Momo.FILE_PATH);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        String response = momo.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMomoDialog(response, momoImage)
        );
        userInput.clear();


    }
}
