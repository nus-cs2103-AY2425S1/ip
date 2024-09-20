package gui;

import gui.DialogBox;
import kira.Kira;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import kira.Ui;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image kiraImage = new Image(this.getClass().getResourceAsStream("/images/kira.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Kira kira;


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Ui ui = new Ui();
        dialogContainer.getChildren().add(DialogBox.getKiraDialog("Annyeong I'm kira~", kiraImage));
    }

    /** Injects the Duke instance */
    public void setKira(Kira kira) {
        this.kira = kira;

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kira.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKiraDialog(response, kiraImage)
        );
        userInput.clear();
    }
}
