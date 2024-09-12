package cloudy;

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

    private Cloudy cloudy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image cloudyImage = new Image(this.getClass().getResourceAsStream("/images/cloudy.png"));

    {
        assert userImage != null : "User image should be loaded";
        assert cloudyImage != null : "Cloudy image should be loaded";
    }
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    /** Injects the Cloudy instance */
    public void setCloudy(Cloudy c) {
        assert c != null : "Cloudy instance should not be null";
        cloudy = c;

        //Display greeting message
        dialogContainer.getChildren().add(DialogBox.getCloudyDialog(cloudy.showGreeting(), cloudyImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Cloudy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cloudy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCloudyDialog(response, cloudyImage)
        );
        userInput.clear();
    }
}
