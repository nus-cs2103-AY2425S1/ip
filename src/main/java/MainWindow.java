import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
    @FXML
    private Button sendButton;

    private Talky talky;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image talkyImage = new Image(this.getClass().getResourceAsStream("/images/TalkyBot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setTalky(Talky talky) {
        this.talky = talky;
    }

    @FXML
    private void processUserInput() {
        String input = userInput.getText();
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        userDialog.setPadding(new Insets(0, 0, 10, 0));
        dialogContainer.getChildren().add(userDialog);

        String output = talky.returnResponse(input);
        DialogBox talkyDialog = DialogBox.getTalkyDialog(output, talkyImage);
        talkyDialog.setPadding(new Insets(0, 0, 10, 0));
        dialogContainer.getChildren().add(talkyDialog);

        userInput.clear();
    }
}
