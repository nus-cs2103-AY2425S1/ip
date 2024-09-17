package weeny.ui;

import weeny.Weeny;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.io.IOException;

/**
 * GUI for Weeny application
 * Manages all GUI elements and actions
 */
public class WeenyGui extends AnchorPane {
    // Other class instances
    private final Ui ui = new Ui();

    // GUI instances
    private Weeny weenyApp;
    private final Image userImage;
    private final Image weenyImage;
    private final Image sendImage;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button sendButton;
    @FXML
    private AnchorPane mainLayout;
    @FXML
    private ImageView sendIcon;

    public WeenyGui() {
        this.weenyApp = new Weeny();
        userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
        weenyImage = new Image(this.getClass().getResourceAsStream("/images/Weeny.png"));
        sendImage = new Image(this.getClass().getResourceAsStream("/images/send.png"));
    }

    /**
     * Start the Application with stage
     * Initialise Scene and GUI elements
     *
     * @param stage Parsed from javafx.Application
     */
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Weeny.class.getResource("/view/WeenyGui.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<WeenyGui>getController().setWeeny(this.weenyApp);
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load FXML file: " + e.getMessage());
        }
    }

    /**
     *  Injects weeny into the Application
     *
     * @param weeny Instance of weeny
     */
    public void setWeeny(Weeny weeny) {
        weenyApp = weeny;
    }

    /**
     * Initialise welcome text and GUI frame
     * Displays initial look of GUI
     */
    @FXML
    public void initialize() {
        sendIcon.setImage(sendImage);
        sendIcon.setFitHeight(sendButton.getPrefHeight());
        sendIcon.setFitWidth(sendButton.getPrefWidth());
        dialogContainer.getChildren().add(DialogBox.getWeenyDialog(ui.showWelcomeMessage(), weenyImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Handles user input from the text field.
     * Sends the input to the Weeny application and updates the dialog container with the response.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String weenyText = weenyApp.executeWeeny(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getWeenyDialog(weenyText, weenyImage)
        );
        userInput.clear();
    }

    /**
     * Display default test on text field
     * Clears when user types
     */
    @FXML
    private void clearDefaultText() {
        if (userInput.getText().equals("Oink here")) {
            userInput.setText("");
        }
    }
}
