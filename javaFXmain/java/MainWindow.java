import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Duke duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/mista.png"));
    private Image brunoImage = new Image(this.getClass().getResourceAsStream("/images/bruno.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBruno(Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String brunoText = duke.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getBrunoDialog(brunoText, brunoImage)
        );
        userInput.clear();
    }
}
