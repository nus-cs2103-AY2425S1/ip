import bobby.ui.Bobby;
import bobby.ui.Parser;
import bobby.ui.Storage;
import bobby.ui.TaskList;
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

    private Bobby bobby;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private final Image userImage = new Image(MainWindow.class.getResourceAsStream("/images/DaUser.png"));
    private final Image bobbyImage = new Image(MainWindow.class.getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getBotDialog(Bobby.getStartMsg(), bobbyImage));
    }

    /** Injects the Bobby instance */
    public void setBobby(Bobby b) {
        bobby = b;
        storage = new Storage(Storage.getFilePath());
        taskList = new TaskList(Storage.loadFile());
        parser = new Parser();
    }


    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = Bobby.checkAction(Parser.getActionType(input), Parser.getDesc(input));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, bobbyImage)
        );
        userInput.clear();
    }
}
