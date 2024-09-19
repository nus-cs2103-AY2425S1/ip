package gui;

import impl.chatbot.Danny;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    private Danny danny;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/imgs/Anime.jpg"));
    private Image dannyImage = new Image(this.getClass().getResourceAsStream("/imgs/Cat.jpg"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setStyle("-fx-background-color: linear-gradient(to bottom, #f8d8fc, #d0ccff);");
    }

    /**
     * Injects the Danny instance
     */
    public void setVars(Danny danny) {
        this.danny = danny;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equalsIgnoreCase("bye")) {
            danny.end();
            Platform.exit();
        }
        String response = danny.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDannyDialog(response, dannyImage)
        );
        userInput.clear();
    }

}