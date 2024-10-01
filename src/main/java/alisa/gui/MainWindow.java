package alisa.gui;

import alisa.Alisa;
import javafx.fxml.FXML;
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
    private Alisa alisa;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/ian.jpg"));
    private Image alisaImage = new Image(this.getClass().getResourceAsStream("/images/alisa.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Alisa instance */
    public void setDuke(Alisa a) {
        alisa = a;
        String welcomeMessage = alisa.welcome();
        dialogContainer.getChildren().addAll(
                DialogBox.getAlisaDialog(welcomeMessage, alisaImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Alisa's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = alisa.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAlisaDialog(response, alisaImage)
        );
        userInput.clear();
    }
}
