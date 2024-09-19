package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import levelhundred.LevelHundred;

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

    private LevelHundred levelHundred;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image levelHundredImage = new Image(this.getClass().getResourceAsStream("/images/DaLevelHundred.png"));

    /**
     * Initialises the Main Window of the GUI
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the LevelHundred instance */
    public void setLevelHundred(LevelHundred l) {
        this.levelHundred = l;
        dialogContainer.getChildren().add(
                DialogBox.getLevelHundredDialog(this.levelHundred.getGreeting(), levelHundredImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing LevelHundred's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = levelHundred.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLevelHundredDialog(response, levelHundredImage)
        );
        userInput.clear();

        if (this.levelHundred.isExited()) {
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
        }
    }
}
