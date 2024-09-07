package echo.javafx;

import echo.main.Echo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    private Echo echo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.jpg"));
    private Image echoImage = new Image(this.getClass().getResourceAsStream("/images/echoImage.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Echo instance and creates a Echo dialogBox to greet users*/
    public void setEcho(Echo echo) {
        this.echo = echo;
        DialogBox echoBox = DialogBox.getEchoDialog(echo.initialise(), echoImage);
        dialogContainer.getChildren().addAll(echoBox);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String echoText = echo.executeInput(userText);

        if (userText.equals("bye")) {
            ImageView sleepyEcho = new ImageView(this.getClass()
                    .getResource("/images/sleepyEcho.png").toString());
            sleepyEcho.setFitWidth(200);
            sleepyEcho.setFitHeight(200);
            sleepyEcho.setPreserveRatio(true);

            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Goodnight");
            alert.setContentText(echoText);
            alert.setGraphic(sleepyEcho);
            alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();

            alert.showAndWait();

        } else {
            DialogBox userBox = DialogBox.getUserDialog(userText, userImage);
            DialogBox echoBox = DialogBox.getEchoDialog(echoText, echoImage);
            dialogContainer.getChildren().addAll(userBox, echoBox);
            userInput.clear();
        }
    }
}

