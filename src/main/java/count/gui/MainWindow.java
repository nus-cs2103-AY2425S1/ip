package count.gui;

import count.Count;
import count.action.Greet;
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

    private Count count;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/ocean_frog.png"));
    private Image countImage = new Image(this.getClass().getResourceAsStream("/images/red_frog.png"));

    /**
     * initializes the MainWindow instance with the ability to scroll
     * and adds greets the user
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        DialogBox countGreet = DialogBox.getCountDialog(new Greet().run(), countImage);
        dialogContainer.getChildren().add(countGreet);

    }

    /** Injects the Count instance */
    public void setCount(Count c) {
        count = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Count's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = count.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCountDialog(response, countImage)
        );
        userInput.clear();
    }
}
