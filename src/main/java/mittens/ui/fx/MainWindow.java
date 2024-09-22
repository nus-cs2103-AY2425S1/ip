package mittens.ui.fx;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mittens.Mittens;
import mittens.MittensException;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Mittens mittens;

    @FXML
    public void initialize(Mittens mittens) {
        this.mittens = mittens;

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        mittens.prepare_run();
    }

    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            return;
        }

        printUserMessage(input);
        userInput.clear();

        boolean shouldExit = mittens.process(input);
        if (shouldExit) {
            scrollPane.getScene().getWindow().hide();
        }
    }

    @FXML
    public void printUserMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.ofUserMessage(message));
    }

    @FXML
    public void printRegularMessage(List<String> messages) {
        dialogContainer.getChildren().add(DialogBox.ofRegularMessage(messages));
    }

    @FXML
    public void printMittensMessage(List<String> messages) {
        dialogContainer.getChildren().add(DialogBox.ofMittensMessage(messages));
    }

    @FXML
    public void printErrorMessage(MittensException e) {
        dialogContainer.getChildren().add(DialogBox.ofErrorMessage(e));
    }
}
