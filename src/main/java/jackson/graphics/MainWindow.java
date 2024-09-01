package jackson.graphics;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import jackson.Jackson;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

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

    private Jackson jackson;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image jacksonImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        this.userInput.setPromptText("Type here!");
    }

    /** Injects the Duke instance */
    public void setJackson(Jackson j) {
        this.jackson = j;
        this.dialogContainer.getChildren().addAll(
                DialogBox.getJacksonDialog(this.jackson.load(), this.jacksonImage, "intro"),
                DialogBox.getJacksonDialog(this.jackson.start(), this.jacksonImage, "intro"));
    }

    /**
     * Creates two dialog boxes, one for user input and one for Jackson's response.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = this.jackson.getResponse(input);
        String commandType = this.jackson.getCommandType();

        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getJacksonDialog(response, this.jacksonImage, commandType)
        );

        if (commandType.equals("exit")) {
            this.dialogContainer.getChildren().addAll(
                    DialogBox.getJacksonDialog(jackson.sayGoodbye(), this.jacksonImage, commandType)
            );
//            Platform.exit();
        }
        this.userInput.clear();
    }
}