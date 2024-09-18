package cstwooneohthree.glados.components;

import cstwooneohthree.glados.Glados;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
    private HistoryWindow historyWindow;
    private Scene historyScene;

    private Image gladosImage = new Image(this.getClass().getResourceAsStream("/images/GladosIcon.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserIcon.jpg"));
    private Image gladosBackgroundImage = new Image(this.getClass().getResourceAsStream("/images/GladosBackground.gif"));
    private String styleSheet = this.getClass().getResource("/css/main.css").toExternalForm();

    @FXML
    public void initialize() {
        getStylesheets().add(styleSheet);
        gladosBackground.setImage(gladosBackgroundImage);
    }

    /** Injects the Glados instance */
    public void setGlados(Glados g, HistoryWindow historyWindow, Scene historyScene) {
        this.glados = g;
        this.historyWindow = historyWindow;
        this.historyScene = historyScene;

        DialogBox gladosChatDialog = DialogBox.getGladosDialog(glados.greet(), gladosImage);
        DialogBox gladosHistoryDialog = DialogBox.getGladosDialog(glados.greet(), gladosImage);

        dialogContainer.getChildren().add(gladosChatDialog);
        historyWindow.getDialogContainer().getChildren().add(gladosHistoryDialog);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Glados's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = glados.executeCommand(input);

        // Create user and response dialog boxes
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox gladosHistoryDialog = DialogBox.getGladosDialog(response, gladosImage);
        DialogBox gladosChatDialog = DialogBox.getGladosDialog(response, gladosImage);

        dialogContainer.getChildren().clear();
        dialogContainer.getChildren().add(gladosChatDialog);
        historyWindow.getDialogContainer().getChildren().addAll(userDialog, gladosHistoryDialog);

        userInput.clear();
    }

    @FXML
    private void handleViewHistory() {
        Stage stage = (Stage) dialogContainer.getScene().getWindow();
        stage.setScene(historyScene);
        stage.show();
    }
}
