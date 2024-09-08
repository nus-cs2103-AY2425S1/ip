package yapper.app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import yapper.components.Ui;
import yapper.components.Yapper;

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

    private Yapper yapper;

    private Image youImage = new Image(this.getClass().getResourceAsStream("/images/you.png"));
    private Image yapperImage = new Image(this.getClass().getResourceAsStream("/images/icon.png"));

    /**
     * Initialises the program to run
     */
    @FXML
    public void initialize() {
        displayIntro();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Yapper instance */
    public void setYapper(Yapper y) {
        yapper = y;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = yapper.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, youImage),
                DialogBox.getDukeDialog(response, yapperImage)
        );
        userInput.clear();
    }

    private void displayIntro() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.yapperIntroduction(), yapperImage)
        );
    }
}

