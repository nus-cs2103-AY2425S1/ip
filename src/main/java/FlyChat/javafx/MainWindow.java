package flychat.javafx;
import flychat.core.FlyChat;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private FlyChat flyChat;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image flyChatImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        assert scrollPane != null : "ScrollPane is not initialized";
        assert dialogContainer != null : "DialogContainer is not initialized";
        assert userInput != null : "UserInput is not initialized";
        assert sendButton != null : "SendButton is not initialized";
        assert userImage != null : "UserImage is not initialized";
        assert flyChatImage != null : "FlyChatImage is not initialized";

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getFlyChatDialog(FlyChat.greetUser(), flyChatImage));
    }

    /**
     * Injects the FlyChat instance.
     */
    public void setFlyChat(FlyChat flyChat) {
        this.flyChat = flyChat;
        flyChat.startUp();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing FlyChat's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        String response = flyChat.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFlyChatDialog(response, flyChatImage)
        );
        userInput.clear();

        if (input.trim().equals("bye")) {
            dialogContainer.getChildren().add(DialogBox.getFlyChatDialog(flyChat.shutDown(), flyChatImage));
            //TODO Print the final goodbye message before exiting
            System.exit(0);
        }
    }
}