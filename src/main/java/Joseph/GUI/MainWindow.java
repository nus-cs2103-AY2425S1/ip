package Joseph.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Joseph.UI;
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

    private Joseph joseph;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gon.jpg"));
    private Image josephImage = new Image(this.getClass().getResourceAsStream("/images/bakugou.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        UI ui = new UI();
        String welcomeMessage = ui.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getJosephDialog(welcomeMessage, josephImage));
    }

    /** Injects the Joseph instance */
    public void setJoseph(Joseph j, Stage stage) {
        joseph = j;
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Joseph's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = joseph.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJosephDialog(response, josephImage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            stage.close();
        }
    }
}

