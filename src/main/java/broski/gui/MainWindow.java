package broski.gui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import broski.output.Broski;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * In charge of visuals of the main window.
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
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image broskiImage = new Image(this.getClass().getResourceAsStream("/images/Broski.png"));
    private Broski bot;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Broski instance
     * @param bot the broski bot instance
     */
    public void setBroski(Broski bot) {
        this.bot = bot;
        dialogContainer.getChildren().addAll(DialogBox.getBroskiDialog(bot.start(), broskiImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        String userText = userInput.getText();
        bot.run(userText);
        String broskiText = outputStream.toString();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getBroskiDialog(broskiText, broskiImage)
        );
        userInput.clear();
    }
}
