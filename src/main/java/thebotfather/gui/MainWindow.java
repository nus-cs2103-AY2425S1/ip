package thebotfather.gui;

import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import thebotfather.TheBotFather;
import thebotfather.util.TheBotFatherException;

/**
 * Controller for the main user interface of TheBotFather application.
 * <p>
 * This class manages user interactions, including sending input to the bot and displaying
 * the corresponding responses in a dialog container.
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

    /** The instance of TheBotFather that handles the application's logic. */
    private TheBotFather bigBoss;

    /** Image representing the user in the dialog. */
    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/DaUser.jpg")));

    /** Image representing the bot in the dialog. */
    private final Image bossImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/DaBoss.jpg")));

    /**
     * Initializes the {@code MainWindow} by binding the vertical scroll value of the {@code scrollPane}
     * to the height property of the {@code dialogContainer}.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the {@code TheBotFather} instance into the controller.
     *
     * @param bigBoss The instance of {@code TheBotFather} to be used for processing user input.
     */
    public void setBigBoss(TheBotFather bigBoss) {
        this.bigBoss = bigBoss;
        dialogContainer.getChildren().addAll(
                DialogBox.getBossDialog(this.bigBoss.getGreeting(), bossImage)
        );
    }

    /**
     * Handles user input from the {@code TextField}, processes it through the {@code TheBotFather} instance,
     * and updates the dialog container with both the user input and the bot's response.
     * <p>
     * If the bot's response indicates that the application should exit (i.e., "EXIT-CODE"), the application
     * will close. If an exception is thrown during processing, it will be displayed as an error message.
     * Finally, the user input field is cleared after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = bigBoss.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getBossDialog(response, bossImage)
            );
            if (response.equals("EXIT-CODE")) {
                Platform.exit();
            }
        } catch (TheBotFatherException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getBossDialog(e.getMessage(), bossImage)
            );
        } finally {
            userInput.clear();
        }
    }
}
