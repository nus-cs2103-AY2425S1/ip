package ipman.ui.javafx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import ipman.ui.javafx.components.MessageBox;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Main window for the JavaFx application. Contains the interface for the IpMan
 * chatbot.
 */
public class MainWindow extends AnchorPane implements Initializable {
    @FXML
    private TextField inputTextField;
    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane scrollPane;

    private final MainWindowViewModel viewModel = new MainWindowViewModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        this.dialogContainer.prefWidthProperty().bind(this.scrollPane.widthProperty());

        // Bind dialogContainer to the viewModel's messages
        this.viewModel.addMessagesListener(c -> {
            while (c.next()) {
                List<MessageBox> messageBoxes = c.getAddedSubList().stream()
                        .map(MessageBox::createMessage)
                        .toList();

                assert !messageBoxes.isEmpty();
                dialogContainer.getChildren().addAll(messageBoxes);
            }
        });

        this.viewModel.showWelcome();
    }

    @FXML
    private void handleOnSendClick() {
        assert this.inputTextField != null;
        assert this.viewModel != null;

        // Read message and clear input
        String message = this.inputTextField.getText();
        this.inputTextField.setText("");

        boolean shouldExit = this.viewModel.processMessage(message);
        if (shouldExit) {
            Platform.exit();
        }
    }
}
