package killua.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import killua.Killua;

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

    private Killua killua;
    private Stage stage;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/gon.jpg"));
    private final Image killuaImage = new Image(this.getClass().getResourceAsStream("/images/killua.jpg"));


    /** Initialize main window */
    @FXML
    public void initialize() {
        assert scrollPane != null;
        assert dialogContainer != null;
        assert userInput != null;
        assert sendButton != null;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Killua instance into the controller.
     *
     * @param k The Killua instance to be used by the controller.
     */
    public void setKillua(Killua k) {
        killua = k;
        welcomeUser();
    }

    /**
     * Injects the primary stage into the controller.
     *
     * @param stage The primary stage for the application.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /** Show welcome message */
    @FXML
    public void welcomeUser() {
        assert killuaImage != null : "Killua image is not loaded!";
        assert userImage != null : "User image is not loaded!";
        dialogContainer.getChildren().add(
                DialogBox.getKilluaDialog(killua.welcomeUser(), killuaImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert !input.trim().isEmpty() : "User input should not be empty!";
        String response = killua.getResponse(input);
        assert response != null : "Response from Killua should not be null!";
        if (response.startsWith("Error")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getKilluaErrorDialog(response.substring(5), killuaImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getKilluaDialog(response, killuaImage)
            );
        }
        userInput.clear();
        exitWithDelay();
    }

    /**
     * Closes the application after a 1-second delay if Killua is not running.
     * The delay allows the exit message to be displayed before closing the application.
     */
    private void exitWithDelay() {
        if (!killua.isRunning()) {
            new Thread(() -> {
                try {
                    // Wait for 1 second to allow the exit message to be displayed
                    Thread.sleep(1000);
                    // Close the stage and exit the application
                    Platform.runLater(() -> {
                        stage.close();
                        Platform.exit();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
