package topaz.ui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import topaz.main.Topaz;

public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Scene scene;

    private Topaz topaz;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image topazImage = new Image(this.getClass().getResourceAsStream("/images/DaTopaz.png"));

    /**
     * Automatically called when load the FXML file, adds welcome message in the beginning.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcome = new Ui().welcome();
        dialogContainer.getChildren().add(DialogBox.getTopazDialog(welcome, topazImage));
    }

    public void setTopaz(Topaz topaz) {
        this.topaz = topaz;
    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String topazText = topaz.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), userImage),
                DialogBox.getTopazDialog(topazText, topazImage));
        userInput.clear();
        if (userInput.getText().toLowerCase().startsWith("bye")) {
            Stage state = (Stage) scene.getWindow();
            state.close();
        }
    }
}
