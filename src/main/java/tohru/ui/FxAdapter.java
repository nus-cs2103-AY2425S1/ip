package tohru.ui;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import tohru.Tohru;

/**
 * Integrates the JavaFX window with the chatbot.
 */
public class FxAdapter implements Ui {

    private static FxAdapter instance;

    private Tohru chatbot;

    private VBox dialogContainer = null;
    private TextField userInput;

    private ArrayList<String> infoPreWindowMessages = new ArrayList<>();
    private ArrayList<String> errorPreWindowMessages = new ArrayList<>();

    /**
     * Initialises the adapter.
     *
     * @param tohru The chatbot instance to be integrated with.
     */
    public FxAdapter(Tohru tohru) {
        instance = this;
        chatbot = tohru;
    }

    /**
     * Retrieves the current instance of the adapter.
     *
     * @return The current instance adapter.
     */
    public static FxAdapter getInstance() {
        return instance;
    }

    /**
     * Sets the dialog container to interact with.
     *
     * @param dialogContainer The dialog container to interact with.
     */
    protected static void setDialogContainer(VBox dialogContainer) {
        instance.dialogContainer = dialogContainer;
    }

    /**
     * Sets the user input to interact with.
     *
     * @param userInput The user input to interact with.
     */
    protected static void setUserInput(TextField userInput) {
        instance.userInput = userInput;
    }

    /**
     * Retrieves user input.
     *
     * @return command entered by user.
     */
    public String readCommand() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input)
        );
        userInput.clear();
        return input;
    }

    /**
     * Closes the chatbot application.
     */
    public void closeInput() {
        // @@author rollingpencil-reused
        // Reused from https://stackoverflow.com/a/27334614
        // with minor modifications.
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> Platform.exit());
        delay.play();
        // @@author
    }

    /**
     * Displays text messages from commands.
     *
     * @param texts Messages to be displayed.
     */
    public void showText(String ...texts) {
        if (dialogContainer == null) {
            infoPreWindowMessages.addAll(Arrays.asList(texts));
        } else {
            String joinedMessage = String.join(System.lineSeparator(), texts);
            dialogContainer.getChildren().addAll(
                    DialogBox.getChatbotDialog(joinedMessage, false)
            );
        }

    }

    /**
     * Displays error messages from commands.
     *
     * @param errors Error messages to be displayed.
     */
    public void showError(String ...errors) {
        if (dialogContainer == null) {
            errorPreWindowMessages.addAll(Arrays.asList(errors));
        } else {
            String joinedMessage = String.join(System.lineSeparator(), errors);
            dialogContainer.getChildren().addAll(
                    DialogBox.getChatbotDialog(joinedMessage, true)
            );
        }
    }

    /**
     * Prints dividers (Not Applicable).
     */
    public void showDivider() {
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        for (String message : infoPreWindowMessages) {
            showText(message);
        }

        for (String message : errorPreWindowMessages) {
            showError(message);
        }

        showText("Hello! I'm Tohru",
                "What can I do for you?");
    }

    /**
     * Commands the chatbot to process the user's commands.
     */
    protected void process() {
        chatbot.process();
    }
}
