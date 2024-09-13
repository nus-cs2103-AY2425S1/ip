package orion.ui;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import orion.Orion;

/**
 * The main window of the application.
 *
 * <p>
 * This class represents the main window of the application, which contains
 * the UI components for the user to interact with the application.
 * </p>
 *
 * @author Pradyumna
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

    private Orion orion;

    private Image userImage;
    private Image orionImage;

    /**
     * Creates a new instance of the main window.
     *
     * <p>
     * This constructor creates a new instance of the main window with the
     * default settings.
     * </p>
     */
    public MainWindow() {
        try {
            System.out.println("Loading user image...");
            userImage = new Image(getClass().getResourceAsStream("/images/user.png"));
            System.out.println("User image loaded: " + (userImage != null) + ", width: " + userImage.getWidth()
                    + ", height: " + userImage.getHeight());

            System.out.println("Loading orion image...");
            URL orionUrl = getClass().getResource("/images/orion.png");
            if (orionUrl != null) {
                orionImage = new Image(orionUrl.toExternalForm());
                System.out.println("Orion image loaded: " + (orionImage != null) + ", width: " + orionImage.getWidth()
                        + ", height: " + orionImage.getHeight());
            } else {
                System.out.println("Orion image URL is null");
            }
        } catch (Exception e) {
            System.out.println("Couldn't load images: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setOrion(Orion o) {
        orion = o;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = orion.getResponse(input);
        System.out.println("User input: " + input);
        System.out.println("Orion response: " + response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getOrionDialog(response, orionImage));
        userInput.clear();
    }
}
