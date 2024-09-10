package fanny.gui;

import fanny.Fanny;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import fanny.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

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

    private Fanny fanny;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showStartupMessage();
    }

    /** Injects the Duke instance */
    public void setFanny(Fanny f) {
        fanny = f;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = fanny.generateResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFannyDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Displays the startup message from the Ui class in the dialog container.
     */
    private void showStartupMessage() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;

        System.setOut(ps);

        Ui ui = new Ui();
        ui.printHello();

        System.out.flush();
        System.setOut(old);

        String welcomeMessage = baos.toString();
        String filteredMessage = Arrays.stream(welcomeMessage.split(System.lineSeparator()))
                .filter(line -> !line.matches("[-_]+"))
                .collect(Collectors.joining(System.lineSeparator()));

        dialogContainer.getChildren().add(
                DialogBox.getFannyDialog(filteredMessage, dukeImage)
        );
    }
}
