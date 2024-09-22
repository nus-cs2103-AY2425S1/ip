package meowmeow;

//solution below adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.application.Platform;
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

    private MeowMeow meowmeow;
    private Parser parser;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.png"));
    private Image meowMeowImage = new Image(this.getClass().getResourceAsStream("/images/MeowMeowImage.png"));

    /**
     * Initializes the controller. Binds the vertical scroll value of the scroll pane
     * to the height property of the dialog container, ensuring that the scroll pane
     * automatically scrolls to the bottom when new dialog elements are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    /** Injects the MeowMeow instance */
    public void setMeowMeow(MeowMeow m) throws IOException {
        assert m != null : "MeowMeow instance should not be null";
        meowmeow = m;
        meowmeow.run();

        dialogContainer.getChildren().addAll(
        MeowMeowDialogBox.getDialog("hewwo! i'm MeowMeow\n" + "what can i do for you? :3\n", meowMeowImage));

        this.parser = meowmeow.getParser();
        assert parser != null : "Parser should not be null";
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        assert userInput != null : "UserInput should not be null";
        String input = userInput.getText();
        String response = parser.parse(input);
        assert response != null : "Response from parser should not be null";
        dialogContainer.getChildren().addAll(
                UserDialogBox.getDialog(input, userImage),
                MeowMeowDialogBox.getDialog(response, meowMeowImage)
        );

        //Solution below inspired by my tp teammate Sanchita
        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
        userInput.clear();
    }
}
