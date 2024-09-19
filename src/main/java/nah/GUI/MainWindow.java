package nah.GUI;

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
import nah.GUI.DialogBox;
import nah.GUI.HelpWindow;
import nah.Main;
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
    private HelpWindow helpWindow = new HelpWindow();

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

            // title bar set up
            stage.setTitle("Nah");
            stage.getIcons().add(new Image("/images/nah.png"));

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

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNahDialog(response, nahImage)
        );
        // exit the program if the input is "bye"
        if (input.trim().toLowerCase().equals("bye")) {
            this.stage.close();
        }
        // open a helpWindow if input is "help"
        if (input.trim().toLowerCase().equals("help")) {
            helpWindow.show();
        }
        userInput.clear();
    }
}
