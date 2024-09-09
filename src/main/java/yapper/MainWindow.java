package yapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class MainWindow {

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image yapperImage = new Image(this.getClass().getResourceAsStream("/images/yapper.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Yapper yapper;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setYapper(Yapper yapper) {
        this.yapper = yapper;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = yapper.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYapperDialog(response, yapperImage)
        );
        userInput.clear();
    }
}