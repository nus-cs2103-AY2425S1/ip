package megamind.gui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import megamind.main.Megamind;

/**
 * The `MainWindow` class represents the main window of the Megamind GUI application.
 * It extends `AnchorPane` and includes various UI components such as a scroll pane,
 * dialog container, user input field, and send button.
 * The class provides methods to initialize the UI, set the Megamind instance,
 * display greeting messages, and handle user input.
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

    private Megamind megamind;

    private final Image megamindImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Megamind.png")));
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/User.png")));

    /**
     * Initializes the main window by binding the scroll pane's vertical value property
     * to the height property of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Megamind instance for this main window and displays the greeting message.
     *
     * @param m The Megamind instance to be set.
     */
    public void setMegamind(Megamind m) {
        assert m != null : "Megamind instance should not be null";
        megamind = m;
        displayGreetingMessage();
    }

    /**
     * Displays the greeting message in the dialog container.
     */
    private void displayGreetingMessage() {
        String greetingMessage = megamind.greet();
        dialogContainer.getChildren().addAll(
                DialogBox.getMegamindDialog(greetingMessage, megamindImage)
        );
    }

    /**
     * Handles the user input by getting the text from the user input field,
     * processing it with the Megamind instance, and displaying the response
     * in the dialog container. If the response is "See you around!", it shows
     * a goodbye alert and closes the application.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = megamind.handleCommand(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMegamindDialog(response, megamindImage)
        );
        userInput.clear();

        if ("See you around!".equals(response)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Goodbye!");
            alert.setHeaderText(null);
            alert.setContentText("Megamind is closing. See you next time!");
            alert.showAndWait();

            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
        }
    }
}
