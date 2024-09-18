package cstwooneohthree.glados.components;
import cstwooneohthree.glados.Glados;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView gladosBackground;

    private Glados glados;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image gladosImage = new Image(this.getClass().getResourceAsStream("/images/DaGlados.png"));
    private Image gladosBackgroundImage = new Image(this.getClass().getResourceAsStream("/images/GladosBackground.gif"));

    @FXML
    public void initialize() {
        gladosBackground.setImage(gladosBackgroundImage);
    }

    /** Injects the Glados instance */
    public void setGlados(Glados g) {
        glados = g;
        dialogContainer.getChildren().add(
            DialogBox.getGladosDialog(glados.greet(), gladosImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = glados.executeCommand(input);
        System.out.println(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGladosDialog(response, gladosImage)
        );
        userInput.clear();
    }
}
