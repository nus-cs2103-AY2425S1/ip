package cookie.gui;

import cookie.Cookie;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main window controller class for the cookie GUI application.
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

    private Cookie cookie;

    private final Image cookieImage = new Image(this.getClass().getResourceAsStream("/images/Cookie.png"));
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setCookie(Cookie c) {
        cookie = c;
        displayGreetingMessage();
    }

    private void displayGreetingMessage() {
        String greetingMessage = cookie.getGreetingMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getCookieDialog(greetingMessage, cookieImage)
        );
    }
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cookie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCookieDialog(response, cookieImage)
        );
        userInput.clear();

        if ("Bye. See you soon!".equals(response)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Goodbye!");
            alert.setHeaderText(null);
            alert.setContentText("Cookie is closing. See you next time!");
            alert.showAndWait();

            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
        }
    }
}
