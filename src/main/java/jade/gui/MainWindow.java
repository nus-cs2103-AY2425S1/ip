package jade.gui;

import jade.Jade;
import jade.command.GreetCommand;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * Controller for the main GUI window of Jade.
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

    private Jade jade;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserIcon.jpg"));
    private Image jadeImage = new Image(this.getClass().getResourceAsStream("/images/JadeIcon.jpg"));

    /**
     * Initialises the main window by setting up the necessary GUI components.
     */
    @FXML
    public void initialize() {
        assert dialogContainer != null : "Dialog container should not be null";
        assert scrollPane != null : "Scroll pane should not be null";
        assert userInput != null : "User input text field should not be null";
        assert sendButton != null : "Send button should not be null";

        handleInitialGreeting();
        handleScroll();
    }

    /**
     * Injects the Jade instance to this controller.
     *
     * @param j The Jade instance to be used by this controller.
     */
    public void setJade(Jade j) {
        assert j != null : "Jade instance should not be null";
        jade = j;
    }

    /**
     * Handles the user input by creating dialog boxes for the user's input and Jade's response,
     * and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null && !input.trim().isEmpty() : "User input should not be null or empty";

        String response = jade.getResponse(input);
        boolean isError = jade.getErrorResponse();
        addDialogBoxes(input, response, isError);
        userInput.clear();
        checkForBye(input);
    }

    /**
     * Adds dialog boxes for the user's input and Jade's response to the dialog container.
     *
     * @param input The user's input.
     * @param response Jade's response.
     * @param isError True if the response is an error message.
     */
    private void addDialogBoxes(String input, String response, boolean isError) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJadeDialog(response, jadeImage, isError)
        );
    }

    /**
     * Checks if the user input is "bye" and if so, waits for 3 seconds before closing the application.
     *
     * @param input The user input to check.
     */
    private void checkForBye(String input) {
        if (input.trim().equalsIgnoreCase("bye")) {
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    Platform.runLater(Platform::exit);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /**
     * Handles the initial greeting command when the application starts.
     */
    private void handleInitialGreeting() {
        String response = new GreetCommand().runForGui();
        assert response != null : "Initial greeting response should not be null";

        dialogContainer.getChildren().addAll(
                DialogBox.getJadeDialog(response, jadeImage, false)
        );
    }

    /**
     * Configures custom scrolling behavior for the dialog container.
     * Automatically scrolls to the bottom when new messages are added and handles manual scrolling.
     */
    private void handleScroll() {
        assert dialogContainer != null && scrollPane != null : "Dialog container and scroll pane must not be null";

        // Automatically scrolls to the bottom when new messages are added
        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue((Double) newValue);
        });

        // Allows manual scrolling using mouse input
        scrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            double deltaY = event.getDeltaY();

            double contentHeight = dialogContainer.getHeight();
            double viewportHeight = scrollPane.getViewportBounds().getHeight();
            double maxScroll = contentHeight - viewportHeight;

            double currentScroll = scrollPane.getVvalue() * maxScroll;
            double scrollAmount = deltaY > 0 ? 30 : -30;
            double newScroll = currentScroll - scrollAmount;

            scrollPane.setVvalue(newScroll / maxScroll);
            event.consume();
        });
    }
}
