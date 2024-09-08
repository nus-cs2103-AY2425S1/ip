package killjoy.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import killjoy.main.KillJoy;
import killjoy.main.UserInterface;

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

    private KillJoy kj;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));
    private Image killjoyImage = new Image(this.getClass().getResourceAsStream("/images/killjoy.png"));
    private Image logo = new Image(this.getClass().getResourceAsStream("/images/killjoy.gif"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setKj(KillJoy kj) {
        this.kj = kj;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kj.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKjDialog(response, killjoyImage)
        );
        userInput.clear();
    }

    @FXML
    public void welcomeMessage() {
        String greetings = UserInterface.getWelcomeString();
        dialogContainer.getChildren().addAll(
                DialogBox.getKjDialog(greetings, killjoyImage)
        );
    }

}
