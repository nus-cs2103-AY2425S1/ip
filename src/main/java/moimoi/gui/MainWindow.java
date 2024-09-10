package moimoi.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import moimoi.MoiMoi;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends Stage {

    @FXML
    private Scene scene;
    @FXML
    private AnchorPane mainLayout;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Image icon = new Image(this.getClass().getResourceAsStream("/images/Icon.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image moiMoiImage = new Image(this.getClass().getResourceAsStream("/images/MoiMoi.jpg"));
    private MoiMoi moiMoi;

    /**
     * Constructs the main GUI.
     *
     * @param moiMoi The MoiMoi program.
     */
    public MainWindow(MoiMoi moiMoi) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.getIcons().add(this.icon);
        this.moiMoi = moiMoi;
        this.dialogContainer.getChildren().add(
                DialogBox.getMoiMoiDialog(this.moiMoi.getInitialMessage(), this.moiMoiImage));
    }

    /**
     * Initializes the scroll pane,
     * by binding its vertical scroll value to the height of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Exits the program immediately if the user input corresponds to an exit command.
     * Or else, creates two dialog boxes, one containing user input and the other containing MoiMoi's reply,
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = this.moiMoi.getResponse(input);

        if (response.isEmpty()) {
            javafx.application.Platform.exit();
        } else {
            this.dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, this.userImage),
                    DialogBox.getMoiMoiDialog(response, this.moiMoiImage));
            this.userInput.clear();
        }
    }

}
