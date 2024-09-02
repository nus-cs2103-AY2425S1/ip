package monique;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    static final String FILE_PATH = "data/tasks.txt";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Monique monique;

    private Image moniqueImage = new Image(this.getClass().getResourceAsStream("/images/monique.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gigachad.jpg"));
    private Stage stage;

    public MainWindow(Stage stage) {
        this.stage = stage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            setMonique(new Monique(FILE_PATH));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void showWelcomeMessage() {
        String welcomeMessage = monique.getWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getMoniqueDialog(welcomeMessage, moniqueImage));
    }

    /** Injects the Monique instance */
    public void setMonique(Monique monique) {
        this.monique = monique;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Monique's reply
     * and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = monique.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMoniqueDialog(response, moniqueImage)
        );
        userInput.clear();
        if (response.equalsIgnoreCase(monique.getUi().showGoodbye())) {
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(event -> stage.close());
            pause.play();
        }
    }
}
