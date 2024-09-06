package gui;

import components.Light;
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

    private Light light;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/LightYagami.jpg"));
    private Image ryukImage = new Image(this.getClass().getResourceAsStream("/images/Ryuk.png"));

    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello, I am Ryuk. What do you want me to do?", ryukImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Light instance */
    public void setLight(Light light) {
        this.light = light;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ryuk's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = light.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, ryukImage)
        );
        userInput.clear();
    }
}