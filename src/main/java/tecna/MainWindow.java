package tecna;

import javafx.application.Platform;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
/**
 * Controller for the main GUI.
 *
 * @author https://se-education.org/.`
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
    private Circle tecnaAvatarCircle;
    @FXML
    private Label tecnaHeaderName;

    private Tecna tecna;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image tecnaImage = new Image(this.getClass().getResourceAsStream("/images/TecnaAvatar.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Image tecnaAvatarImage = new Image(this.getClass().getResourceAsStream("/images/TecnaAvatar.jpg"));
        tecnaAvatarCircle.setFill(new ImagePattern(tecnaAvatarImage));
        tecnaHeaderName.setText("Tecna");
    }

    /** Injects the Duke instance */
    public void setTecna(Tecna t) {
        tecna = t;
    }

    /**
     * Sets stage for MainWindow
     *
     * @param stage which shows the MainWindow
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input == null || input.isEmpty()) {
            return;
        }

        String response = tecna.getResponse(input);

        assert response != null || response.isEmpty() : "Tecna's response is empty.";

        DialogBox userDialogBox = DialogBox.getUserDialog(input, userImage);

        DialogBox tecnaDialogBox = DialogBox.getTecnaDialog(response, tecnaImage);
        dialogContainer.getChildren().addAll(
                userDialogBox, tecnaDialogBox);
        userInput.clear();
        VBox.setVgrow(userDialogBox, Priority.ALWAYS);
        VBox.setVgrow(tecnaDialogBox, Priority.ALWAYS);
        if (response.equals("Pleased to help you! See you again ^_^")) {
            Platform.exit();
        }
    }

    public void sendMessage(String message) {
        assert message != null : "Message is null.";
        DialogBox tecnaDialogBox = DialogBox.getTecnaDialog(message, tecnaImage);
        dialogContainer.getChildren().addAll(tecnaDialogBox);
        VBox.setVgrow(tecnaDialogBox, Priority.ALWAYS);
    }

}
