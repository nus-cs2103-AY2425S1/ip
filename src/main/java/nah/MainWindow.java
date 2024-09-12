package nah;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import nah.DialogBox;
import nah.Nah;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage =

            new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image nahImage =
            new Image(this.getClass().getResourceAsStream("/images/nah.png"));
    private final DialogBox greeting = DialogBox.getNahDialog(
            " Hello from Nah. How can I help you?", nahImage);
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Nah nah;

    private Stage stage;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public MainWindow(Nah nah) {
        this.nah = nah;
    }

    public void setStage(Stage stage) {
        try {
            this.stage = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            this.stage.setScene(scene);
            this.stage.show();
            dialogContainer.getChildren().addAll(this.greeting);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nah.getResponse(input);
        if (response == " Bye. Hope to see you again soon!\n") {
            this.stage.close();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNahDialog(response, nahImage)
        );
        userInput.clear();
    }
}
