package jade.gui;

import jade.Jade;
import jade.command.GreetCommand;
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
     * Initializes the main window by setting up the necessary GUI components.
     */
    @FXML
    public void initialize() {
        handleInitialGreeting();
        handleScroll();
    }

    /**
     * Injects the Jade instance to this controller.
     *
     * @param j The Jade instance to be used by this controller.
     */
    public void setJade(Jade j) {
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
        String response = jade.getResponse(input);
        addDialogBoxes(input, response);
        userInput.clear();
    }

    /**
     * Adds dialog boxes for the user's input and Jade's response.
     *
     * @param messages The messages to be displayed in dialog boxes (user input and Jade response).
     */
    private void addDialogBoxes(String... messages) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(messages[0], userImage),
                DialogBox.getJadeDialog(messages[1], jadeImage)
        );
    }

    /**
     * Handles the initial greeting command when the application starts.
     * Adds Jade's initial greeting message to the dialog container.
     */
    private void handleInitialGreeting() {
        String response = new GreetCommand().runForGui();
        dialogContainer.getChildren().addAll(
                DialogBox.getJadeDialog(response, jadeImage)
        );
    }

    /**
     * Sets up custom scrolling behavior for the dialog container.
     * Adds a listener to automatically scroll to the bottom when new messages are added,
     * and handles manual scrolling with mouse input to adjust the scroll position.
     */
    private void handleScroll() {
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
