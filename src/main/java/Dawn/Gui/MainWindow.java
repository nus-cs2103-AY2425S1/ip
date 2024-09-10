package Dawn.Gui;

//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.VBox;
//
//public class MainWindow extends AnchorPane {
//    @FXML
//    private ScrollPane scrollPane;
//    @FXML
//    private VBox dialogContainer;
//    @FXML
//    private TextField userInput;
//    @FXML
//    private Button sendButton;
//    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
//    private Image dawnImage = new Image(this.getClass().getResourceAsStream("/images/DaDawn.png"));
//    private Dawn.Dawn dawn = new Dawn.Dawn();
//
//    @FXML
//    public void intialize() {
//        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
//    }
//
//    public void setDawn(Dawn.Dawn d) {
//        dawn = d;
//    }
//
//    @FXML
//    private void handleUserInput() {
//        String userText = userInput.getText();
//        String dawnText = dawn.getResponse(userText);
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, userImage),
//                DialogBox.getDawnDialog(dawnText, dawnImage)
//        );
//        userInput.clear();
//    }
//}
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Dawn.Dawn dawn;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDawn.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setDawn(Dawn.Dawn d) {
        dawn = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dawn.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDawnDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
