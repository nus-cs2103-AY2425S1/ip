package ipman.ui.javafx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
                List<Label> labels = c.getAddedSubList().stream()
                        .map(e -> new Label(e.message()))
                        .toList();
                dialogContainer.getChildren().addAll(labels);
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
