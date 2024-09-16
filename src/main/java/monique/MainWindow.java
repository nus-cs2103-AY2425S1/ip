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
 * Controller class for the main GUI of the application.
 * This class is responsible for managing the interactions between the user interface components,
 * handling user input, and updating the display with responses from the Monique chatbot.
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

    /**
     * Constructs a MainWindow instance with the given stage.
     * Initializes the FXML components and sets up the Monique instance.
     *
     * @param stage the primary stage for this application
     */
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

    /**
     * Initializes the ScrollPane to automatically scroll to the bottom as new messages are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Displays a welcome message from the Monique instance in the dialog container.
     */
    public void showWelcomeMessage() {
        String welcomeMessage = monique.getWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getMoniqueDialog(welcomeMessage, moniqueImage, "default"));
    }
    /**
     * Injects the Monique instance into this controller.
     *
     * @param monique the Monique instance to be used by this controller
     */
    public void setMonique(Monique monique) {
        this.monique = monique;
    }


    /**
     * Handles user input by creating dialog boxes for user and Monique's replies,
     * appends them to the dialog container, and clears the user input field.
     * If the response is a goodbye message, the application will close after a short delay.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = monique.getResponse(input);
        String commandType = monique.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMoniqueDialog(response, moniqueImage, commandType)
        );
        userInput.clear();
        if (response.equalsIgnoreCase(monique.getUi().showGoodbye())) {
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> stage.close());
            pause.play();
        }
    }
}
