package dipsy.javafx;

import dipsy.Dipsy;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


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
    @FXML
    private Button helpButton;

    private Dipsy dipsy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/PepeClown.jpeg"));
    private Image dipsyImage = new Image(this.getClass().getResourceAsStream("/images/Dipsy.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        userInput.setPromptText("Type command here...");
    }

    /** Injects the Dipsy instance and shows welcome message. */
    public void setDipsy(Dipsy dipsy) {
        assert dipsy != null : "Dipsy instance should not be null";

        this.dipsy = dipsy;
        dipsy.setMainWindow(this);

        showWelcomeMessage();
    }

    @FXML
    private void showWelcomeMessage() {
        String welcome = dipsy.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getDipsyDialog(welcome, dipsyImage)
        );
    }

    /**
     * Closes the JavaFX application with a delay.
     * This method can be called by the {@link Dipsy} class to trigger the application shutdown.
     *
     * @param delayMillis The delay in milliseconds before the application exits.
     */
    public void closeApplicationWithDelay(long delayMillis) {
        // Run the delay on a background thread
        new Thread(() -> {
            try {
                // Delay the application exit
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted state
            }

            // Now exit the application on the JavaFX Application Thread
            Platform.runLater(Platform::exit);
        }).start();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Dipsy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userInput != null : "userInput should not be null";
        assert dipsy != null : "Dipsy instance should not be null";
        assert userImage != null : "userImage should not be null";
        assert dipsyImage != null : "dipsyImage should not be null";
        assert dialogContainer != null : "dialogContainer should not be null";

        String input = userInput.getText();
        String response = dipsy.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDipsyDialog(response, dipsyImage)
        );
        userInput.clear();
    }

    /**
     * Creates am alert that shows the users the commands available.
     */
    @FXML
    private void handleHelpButton() {
        Alert alert = new Alert(AlertType.INFORMATION);

        alert.setTitle("Help Information");
        alert.setHeaderText(null);

        // Create a GridPane for layout
        GridPane grid = new GridPane();
        grid.setHgap(10); // Horizontal gap between columns
        grid.setVgap(10); // Vertical gap between rows

        // Add commands and descriptions in rows
        grid.add(new Label("Command"), 0, 0);
        grid.add(new Label("Description"), 1, 0);

        grid.add(new Label("todo <description>"), 0, 1);
        grid.add(new Label("Create a new todo item"), 1, 1);

        grid.add(new Label("deadline <description> /by <date>"), 0, 2);
        grid.add(new Label("Create a task with a deadline (yyyy-MM-dd)"), 1, 2);

        grid.add(new Label("event <description> /from <start> /to <end>"), 0, 3);
        grid.add(new Label("Create an event with start and end dates"), 1, 3);

        grid.add(new Label("mark <index>"), 0, 4);
        grid.add(new Label("Mark the task at the given index as done"), 1, 4);

        grid.add(new Label("unmark <index>"), 0, 5);
        grid.add(new Label("Unmark the task at the given index"), 1, 5);

        grid.add(new Label("list"), 0, 6);
        grid.add(new Label("List all tasks"), 1, 6);

        grid.add(new Label("list <date>"), 0, 7);
        grid.add(new Label("List tasks on the specified date"), 1, 7);

        grid.add(new Label("bye"), 0, 8);
        grid.add(new Label("Exit the program"), 1, 8);

        // Set the content of the alert to the GridPane
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setContent(grid);

        alert.showAndWait();

    }
}
