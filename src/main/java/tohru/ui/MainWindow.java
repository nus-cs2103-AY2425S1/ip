package tohru.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents the Main Window to be displayed.
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

    private FxAdapter fxAdapter;

    /**
     * Initialises the window.
     */
    @FXML
    public void initialize() {
        fxAdapter = FxAdapter.getInstance();
        FxAdapter.setDialogContainer(dialogContainer);
        FxAdapter.setUserInput(userInput);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        fxAdapter.showWelcome();
    }

    /**
     * Calls the chatbot to process the user input.
     */
    @FXML
    private void handleUserInput() {
        fxAdapter.process();
    }
}
