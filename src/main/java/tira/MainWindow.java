package tira;

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

    private Tira tira;
    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_image.png"));
    private Image tiraImage = new Image(this.getClass().getResourceAsStream("/images/tira_image.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = ui.showWelcome();
        dialogContainer.getChildren().addAll(
                DialogBox.getTiraDialog(welcomeMessage, tiraImage)
        );


    }

    /** Injects the Duke instance */
    public void setTira(Tira d) {
        tira = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws TiraException {
        String input = userInput.getText();
        if (input.equals("bye")) {
            ui.showBye();
            String byeString = ui.getOutMessage();
            dialogContainer.getChildren().add(
                    DialogBox.getTiraDialog(byeString, tiraImage)
            );
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
        String response;
        try {
            response = tira.getResponse(input);
        } catch (TiraException e) {
            response = e.getMessage();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTiraDialog(response, tiraImage)
        );
        userInput.clear();
    }
}
