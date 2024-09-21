package controllers;

import java.util.Objects;

import core.Brock;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import task.TaskList;
import utility.Pair;

/**
 * Controller class for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private static final double SCROLL_AMOUNT = 0.0007;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Brock brock;

    private TaskList tasks;

    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("/images/DaUser.jpg")));
    private final Image brockImage =
            new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("/images/DaBrock.jpg")));
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        this.setupScroll();
        this.userInput.setPromptText("Enter your command here!");
        this.setupButtonIcon();
    }

    /**
     * Sets up the scroll and auto-scroll mechanism;
     */
    private void setupScroll() {
        // Add listener function that listens to height of dialog container
        // When height increases (ie: user + brock dialog added), apply the listener function
        // Auto-scroll to the very bottom
        this.dialogContainer.heightProperty().addListener((obs, oldBounds, newBounds) ->
            scrollPane.setVvalue(scrollPane.getVmax())
        );

        // Add scroll callback function to dialog container
        // When dialog container is scrolled, apply the callback function
        // Scroll up/down by some amount
        this.dialogContainer.setOnScroll(event -> {
            double deltaY = event.getDeltaY() * SCROLL_AMOUNT;
            scrollPane.setVvalue(scrollPane.getVvalue() - deltaY);
        });
    }

    /**
     * Creates a button icon within the send button.
     */
    private void setupButtonIcon() {
        // Approach was adopted from https://edencoding.com/how-to-add-an-image-to-a-button/
        // To add an icon within the send button
        ImageView imageView = new ImageView(Objects.requireNonNull(getClass()
                .getResource("/images/SendIcon.jpg")).toExternalForm());
        sendButton.setGraphic(imageView);
        sendButton.setContentDisplay(ContentDisplay.TOP);
        imageView.fitWidthProperty()
                .bind(sendButton.widthProperty().divide(10));
        imageView.setPreserveRatio(true);

        // Prevent button from wrap to text + graphic size
        sendButton.setMaxWidth(Double.MAX_VALUE);
    }

    /**
     * Injects the {@code Brock} instance.
     *
     * @param b {@code Brock} instance to be injected.
     */
    public void setBrock(Brock b) {
        this.brock = b;
    }

    /**
     * Injects the {@code TaskList} instance.
     *
     * @param t {@code TaskList} instance to be injected.
     */
    public void setTasks(TaskList t) {
        this.tasks = t;
    }

    /**
     * Shows initial Brock response from set up procedure on the GUI.
     * Which entails creating save file, loading from save file and welcome message.
     *
     * @param response Initial Brock response to be displayed.
     */
    public void showInitialResponse(String response) {
        boolean isCorruption = response.startsWith("[Corruption]");
        dialogContainer.getChildren().addAll(
                DialogBox.getBrockDialog(response, isCorruption, brockImage)
        );
    }

    /**
     * Processes the raw command.
     *
     * @param rawCommand Raw command to be processed.
     * @return Processed command.
     */
    private String processCommand(String rawCommand) {
        // Trim away leading & trailing whitespaces
        // Replace multiple whitespaces with a single one
        return rawCommand.trim()
                .replaceAll(" +", " ");
    }

    /**
     * Shows both user input and Brock response on the GUI.
     *
     * @param rawCommand Raw user input string.
     * @param isException Indicator if exception was caught during response.
     * @param brockResponse Brock response string.
     */
    private void showBothDialog(String rawCommand, boolean isException, String brockResponse) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(rawCommand, userImage),
                DialogBox.getBrockDialog(brockResponse, isException, brockImage)
        );
        this.userInput.clear();
    }

    /**
     * Creates two dialog boxes, one showing user input and the other containing Brock's reply
     * and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String rawCommand = userInput.getText();
        if (rawCommand.isBlank()) {
            // User did not type anything (besides whitespaces)
            // Don't bother handling!
            return;
        }
        String processedCommand = this.processCommand(rawCommand);
        Pair<Boolean, Pair<Boolean,String>> responseResult = this.brock
                .respondToCommand(processedCommand, this.tasks);

        boolean isExit = responseResult.getFirst();
        boolean isException = responseResult.getSecond().getFirst();
        String brockResponse = responseResult.getSecond().getSecond();
        this.showBothDialog(rawCommand, isException, brockResponse);

        // Exit after displaying the dialog boxes
        // So that user can see the input + response first
        if (isExit) {
            this.exitProgram();
        }
    }

    /**
     * Exits the GUI application.
     * Approach was adapted from:
     * <a href="https://github.com/nus-cs2103-AY2425S1/forum/issues/199#issuecomment-2333192757">...</a>
     */
    public void exitProgram() {
        // Sets a 3-second delay before exiting
        // To make it less abrupt
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> Platform.exit());
        pause.play();
    }
}
