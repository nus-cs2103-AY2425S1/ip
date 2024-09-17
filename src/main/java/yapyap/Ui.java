package yapyap;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;


/**
 * Handles user interactions and displays messages in the YapperBot application.
 */
public class Ui {
    private VBox dialogContainer;
    private Image yapperBotImage;

    /**
     * Constructor for Ui.
     *
     * @param dialogContainer The VBox where dialog messages will be displayed.
     */
    public Ui(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
        this.yapperBotImage = new Image(this.getClass().getResourceAsStream("/images/YapperBot.png"));
    }

    /**
     * Displays a loading error message when tasks fail to load from the file.
     */
    public void printLoadingError() {
        String errorMessage = "Error loading tasks. Starting with an empty task list.";
        dialogContainer.getChildren().add(DialogBox.getYapperBotDialog(errorMessage, yapperBotImage));
    }

    /**
     * Displays a welcome message when the UI is first launched.
     */
    public void displayWelcomeMessage() {
        String welcomeMessage = "Hello! I'm YapperBot. What can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getYapperBotDialog(welcomeMessage, yapperBotImage));
    }
}
