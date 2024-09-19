package Naega.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import Naega.*;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Naega naega;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/IMG_6086.jpg"));
    private Image naegaImage = new Image(this.getClass().getResourceAsStream("/images/IMG_6087.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setNaega(Naega naega) {
        this.naega = naega;
    }

    /**
     * Handles user input by sending it to Naega and displaying the response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = naega.getResponse(input);
        // Create new dialog boxes for the input and the response
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox naegaDialog = DialogBox.getNaegaDialog(response, naegaImage);

        // Add these dialog boxes to the VBox
        dialogContainer.getChildren().addAll(userDialog, naegaDialog);
        userInput.clear();
    }
}
