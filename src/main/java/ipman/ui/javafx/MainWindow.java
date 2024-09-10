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

public class MainWindow extends AnchorPane implements Initializable {
    @FXML
    private TextField inputTextField;
    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane scrollPane;

    private MainWindowViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        this.viewModel = new MainWindowViewModel();
        this.viewModel.getMessages().addListener((ListChangeListener<Message>) c -> {
            while (c.next()) {
                List<MessageBox> messageBoxes = c.getAddedSubList().stream()
                        .map(MessageBox::createMessage)
                        .toList();
                dialogContainer.getChildren().addAll(messageBoxes);
            }
        });
    }

    @FXML
    private void handleOnSendClick() {
        String message = this.inputTextField.getText();
        boolean shouldExit = this.viewModel.processMessage(message);
        if (shouldExit) {
            Platform.exit();
        }
    }
}
