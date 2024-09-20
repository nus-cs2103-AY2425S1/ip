package asura.ui;

import java.io.IOException;
import java.util.Scanner;

import asura.Asura;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the UI of the program.
 */
public class Ui extends Application {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Asura asura = new Asura("data/asura.txt");
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/avatar1.png"));
    private Image asuraImage = new Image(this.getClass().getResourceAsStream("/images/avatar2.png"));

    /**
     * Creates a UI.
     */
    public Ui() {
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAsura(Asura asura) {
        this.asura = asura;
    }

    /**
     * Starts the GUI.
     * @param stage primary stage of JavaFX.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Asura.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<Ui>getController().setAsura(asura);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Outputs the error that is specified.
     * @param error The error specified.
     */
    public void showError(String error) {
        System.out.println(formatResponse(error));
    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        assert userText != null : "User input should not be null";
        String asuraText = asura.getResponse(userInput.getText());
        assert asuraText != null : "Asura text should not be null";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getAsuraDialog(asuraText, asuraImage)
        );
        userInput.clear();
    }

    /**
     * Formats the string to include heading and footing lines.
     * @param msg The string to be formatted.
     * @return The formatted string.
     */
    private String formatResponse(String msg) {
        String startBorder = "---------------------------------------\n";
        String endBorder = "\n---------------------------------------";
        String formattedMsg = startBorder + msg + endBorder;
        return formattedMsg.indent(3);
    }
}
