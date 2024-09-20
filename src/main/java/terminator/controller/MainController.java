package terminator.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import terminator.Main;
import terminator.Terminator;
import terminator.components.DialogBox;

/**
 * The root controller class.
 */
public class MainController {

    private final Terminator terminator;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane titleBar;

    @FXML
    private ImageView titleImage;

    @FXML
    private Label appTitle;

    @FXML
    private MenuButton optionsButton;

    @FXML
    private VBox dialogContainer;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField userInput;

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            Platform.exit();
        }
        String response = terminator.getResponse(input);

        userInput.clear();

        DialogBox userDb = DialogBox.getUserDialog(input);
        DialogBox terminatorDb = DialogBox.getTerminatorDialog(response);

        // Set initial width to be scroll pane's viewport width
        userDb.setPrefWidth(scrollPane.viewportBoundsProperty().get().getWidth());
        terminatorDb.setPrefWidth(scrollPane.viewportBoundsProperty().get().getWidth());

        dialogContainer.getChildren().addAll(userDb, terminatorDb);

        // Add listener to update width of dialog boxes when user resizes the window
        scrollPane.viewportBoundsProperty().addListener(((observable, oldBounds, newBounds) -> {
            double newWidth = newBounds.getWidth();
            userDb.setPrefWidth(newWidth);
            terminatorDb.setPrefWidth(newWidth);
        }));

        userInput.clear();
    }

    @FXML
    private void showHelpMessage() {
        String response = terminator.getResponse("help");
        DialogBox terminatorDb = DialogBox.getTerminatorDialog(response);
        terminatorDb.setPrefWidth(scrollPane.viewportBoundsProperty().get().getWidth());
        dialogContainer.getChildren().add(terminatorDb);
        scrollPane.viewportBoundsProperty().addListener(((observable, oldBounds, newBounds) -> {
            terminatorDb.setPrefWidth(newBounds.getWidth());
        }));
    }

    /**
     * No-args constructor to allow JavaFX runtime to instantiate the controller.
     */
    public MainController() {
        this.terminator = new Terminator();
    }

    @FXML
    private void initialize() {
        // When new content is added to the chat, set scroll pane scroll position all the way down
        dialogContainer.heightProperty().addListener((observable) -> {
            scrollPane.setVvalue(1.0);
        });

        // Set custom font for app title
        appTitle.setFont(Font.loadFont(Main.class.getResource("/fonts/terminator.ttf").toExternalForm(),
                16));

        // Add help dialog
        DialogBox helpDb = DialogBox.getUserDialog(
                "Enter \"help\" to see available commands, or click the help button in the top right hand corner.");
        dialogContainer.getChildren().add(helpDb);
        scrollPane.viewportBoundsProperty().addListener(((observable, oldBounds, newBounds) -> {
            double newWidth = newBounds.getWidth();
            helpDb.setPrefWidth(newWidth);
        }));

        Image img = titleImage.getImage();
        Circle clip = new Circle(40, 40, 40);
        titleImage.setClip(clip);

    }
}
