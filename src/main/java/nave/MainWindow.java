package nave;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Nave nave;
    private Scene scene;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image naveImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaNave.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Nave instance */
    public void setNave(Nave d) {
        nave = d;
    }

    /** Injects the Scene instance */
    public void setScene(Scene s) {
        scene = s;
    }

    /** Greet's User */
    public void greetUser() {
        dialogContainer.getChildren().add(
                DialogBox.getNaveDialog(nave.getGreeting(), naveImage)
        );
        nave.guiStart();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Nave's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nave.getResponse(input);
        Parser.Command commandType = nave.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNaveDialog(response, naveImage, commandType)
        );
        userInput.clear();
        if (commandType == Parser.Command.BYE) {
            closeWindow();
        }
    }

    /**
     * Closes the application or window.
     */
    private void closeWindow() {
        Duration delay = Duration.seconds(3);

        PauseTransition pause = new PauseTransition(delay);

        pause.setOnFinished(event -> {
            Stage stage = (Stage) scene.getWindow();
            if (stage != null) {
                stage.close();
            }
        });

        pause.play();
    }
}
