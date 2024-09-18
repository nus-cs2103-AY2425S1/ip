package ip.derrick;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import ip.derrick.Derrick;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


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

    private Derrick chatbot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image chatBotImage = new Image(this.getClass().getResourceAsStream("/images/Chatbot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().add(
                DialogBox.getChatBotDialog(
                        "Hello! I am Derrick. How can I assist you today?",
                        chatBotImage
                )
        );
    }

    public void setChatbot(Derrick d) {
        chatbot = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        chatbot.run(userInput.getText());

        System.setOut(originalOut);

        String chatBotText = outputStream.toString();

        userInput.clear();

        if (userText.equalsIgnoreCase("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, userImage),
                    DialogBox.getChatBotDialog("Goodbye!", chatBotImage)
            );

            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        } else if (userText.equalsIgnoreCase("hi")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, userImage),
                    DialogBox.getChatBotDialog("Hello, my name is Derrick! What can I do for you?", chatBotImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, userImage),
                    DialogBox.getChatBotDialog(chatBotText, chatBotImage)
            );
        }
    }
}
