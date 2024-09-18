import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;

import javafx.stage.Stage;
import sigma.Storage;
import sigma.TaskList;
import sigma.Ui;

import java.util.Objects;

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

    private Sigma sigma;
    private Ui ui;
    private Storage storage;
//    private TaskList taskList;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image sigmaImage = new Image(this.getClass().getResourceAsStream("/images/sigma.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Sigma instance */
    public void setSigma(Sigma s) {
        sigma = s;
        displayWelcomeMessage();
        displayTasksFromFile();
    }

    /** Displays the welcome message */
    private void displayWelcomeMessage() {
        ui = new Ui();
        dialogContainer.getChildren().add(
                DialogBox.getSigmaDialog(ui.welcome(), sigmaImage)
        );
    }

    private void displayTasksFromFile() {
        storage = new Storage("data/sigma.txt");
//        taskList = new TaskList();
        storage.readTasksFromFile();
        String list = TaskList.toPrettyList();
        dialogContainer.getChildren().add(
                DialogBox.getSigmaDialog(list, sigmaImage)
        );
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sigma.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSigmaDialog(response, sigmaImage)
        );
        userInput.clear();
        if (response.equals("bye")) {
            Stage stage = (Stage) sendButton.getScene().getWindow();
            stage.close();
            Platform.exit();
        }
    }
}
