package jarvis.ui;

import jarvis.logic.Jarvis;
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

    private Jarvis jarvis;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/jarvis.jpg"));
    private Image jarvisImage = new Image(this.getClass().getResourceAsStream("/images/jarvis.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String logo =
                "       _                  _     \n" +
                        "      (_)___ _______   __(_)____\n" +
                        "     / / __ `/ ___/ | / / / ___/\n" +
                        "    / / /_/ / /   | |/ / (__  )\n" +
                        " __/ /\\__,_/_/    |___/_/____/\n" +
                        "/___/\n";


        dialogContainer.getChildren().add(
                DialogBox.getJarvisDialog("hello from: \n" + logo + " \nHow can I assist you today?", jarvisImage)
        );
    }

    /** Injects the Duke instance */
    public void setJarvis(Jarvis j) {
        this.jarvis = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jarvis.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJarvisDialog(response, jarvisImage)
        );
        userInput.clear();
    }
}