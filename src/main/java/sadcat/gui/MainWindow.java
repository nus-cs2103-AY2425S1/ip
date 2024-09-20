package sadcat.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sadcat.SadCat;

/**
 * Represents the main window of the SadCat application.
 * This class extends AnchorPane and controls the primary user interface.
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
    private AnchorPane mainPane;

    private SadCat sadcat;
    private Stage stage;

    private Image userImage = new Image(getClass().getResourceAsStream("/images/user.png"));
    private Image sadCatImage = new Image(getClass().getResourceAsStream("/images/sadcat.png"));
    private boolean isDarkMode = false;

    /**
     * Initializes the main window.
     * This method is automatically called by JavaFX after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        mainPane.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    }

    /**
     * Sends the introduction message from SadCat.
     * This method adds SadCat's greeting to the dialog container.
     */
    @FXML
    public void sendIntro() {
        dialogContainer.getChildren().add(DialogBox.getSadCatIntro(
                sadcat.getGreeting(), sadCatImage));
    }

    /**
     * Toggles dark mode in the dialog container.
     */
    @FXML
    private void toggleDarkMode() {
        isDarkMode = !isDarkMode;
        if (isDarkMode) {
            mainPane.getStyleClass().add("dark-mode");
        } else {
            mainPane.getStyleClass().remove("dark-mode");
        }
    }

    /**
     * Sets the SadCat instance for this window.
     *
     * @param d The SadCat instance to be used
     */
    public void setSadCat(SadCat d) {
        sadcat = d;
    }

    /**
     * Sets the Stage instance for this window.
     *
     * @param s The Stage instance to be used
     */
    public void setStage(Stage s) {
        stage = s;
    }

    /**
     * Handles user input.
     * This method is called when the user sends a message. It processes the input,
     * gets a response from SadCat, and updates the dialog container.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sadcat.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSadCatDialog(response, sadCatImage)
        );
        userInput.clear();

        if (!sadcat.isRunning()) {
            Platform.runLater(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stage.close();
                Platform.exit();
            });
        }
    }
}
