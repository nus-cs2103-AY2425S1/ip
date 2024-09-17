package components;

import blitz.Blitz;
import io.Ui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField userInputTextField;
    @FXML
    private Button sendButton;
    @FXML
    private VBox dialogContainer;

    private final Blitz blitz;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image blitzImage = new Image(this.getClass().getResourceAsStream("/images/blitz.png"));

    public MainWindow(Blitz blitz) {
        this.blitz = blitz;
    }

    public void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        DialogBox blitzDialogBox = DialogBox.getBlitzDialog(Ui.getStartUpMessage(), blitzImage);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setBottomAnchor(userInputTextField, 1.0);
        AnchorPane.setLeftAnchor(userInputTextField, 1.0);
        AnchorPane.setRightAnchor(userInputTextField, sendButton.getPrefWidth() + 1.0);
        dialogContainer.getChildren().add(blitzDialogBox);
    }

    @FXML
    private void handleUserInput() {
        String input = userInputTextField.getText();
        if (input.isEmpty()) {
            return;
        }

        String blitzResponse = blitz.getResponse(input);
        DialogBox userDialogBox = DialogBox.getUserDialog(input, userImage);
        DialogBox blitzDialogBox = DialogBox.getBlitzDialog(blitzResponse, blitzImage);
        dialogContainer.getChildren().addAll(userDialogBox, blitzDialogBox);
        userInputTextField.clear();
    }
}
