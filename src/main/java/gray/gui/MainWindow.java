package gray.gui;

import static gray.Constants.FILEPATH_VIEW_MAIN_WINDOW;

import java.io.IOException;
import java.util.function.Function;

import gray.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents the main window of the GUI
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogVBox;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Image userImage;
    private final Image grayImage;
    private final Function<String, String> userInputCallback;

    /**
     * Constructs the main window for the GUI.
     */
    public MainWindow(Image userImage, Image grayImage, Function<String, String> userInputCallback) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FILEPATH_VIEW_MAIN_WINDOW));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.userImage = userImage;
        this.grayImage = grayImage;
        this.userInputCallback = userInputCallback;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogVBox.heightProperty());
    }

    @FXML
    private void handleUserInput() {
        String user = userInput.getText();
        userInput.clear();
        addUserDialog(user);
        String response = userInputCallback.apply(user);
        addGrayDialog(response);
        if (user.equals("bye")) {
            Platform.exit();
        }
    }

    public void addUserDialog(String text) {
        addDialog(userImage, String.format("User:\n%s", text));
    }

    public void addGrayDialog(String text) {
        addDialog(grayImage, String.format("Gray:\n%s", text));
    }

    private void addDialog(Image image, String text) {
        DialogBox dialog = new DialogBox(image, text);
        dialogVBox.getChildren().add(dialog);
    }
}
