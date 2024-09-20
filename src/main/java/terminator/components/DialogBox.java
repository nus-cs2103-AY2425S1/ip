package terminator.components;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import terminator.Main;

/**
 * Represent a dialog UI element.
 */
public class DialogBox extends HBox {

    @FXML
    private Label displayText;

    @FXML
    private AnchorPane labelContainer;

    /**
     * Creates a new DialogBox with the given text and image.
     */
    public DialogBox() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/DialogBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.idProperty().set("dialog-box");

        try {
            fxmlLoader.load();

        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("Error loading DialogBox component");
        }
    }

    /**
     * Returns a new dialog box representing user input.
     *
     * @param s The user's input.
     * @return A new dialog box containing the user's input, and the user image.
     */
    public static DialogBox getUserDialog(String s) {
        DialogBox db = new DialogBox();
        db.displayText.setText(s);
        db.setAlignment(Pos.CENTER_RIGHT);
        db.labelContainer.setStyle("-fx-background-radius: 12px 10px 0 12px;");
        return db;
    }

    /**
     * Returns a new dialog box representing the chatbot's response to the user's input.
     *
     * @param s The chatbot's response.
     * @return A new dialog box containing the chatbot's response, and the Terminator image.
     */
    public static DialogBox getTerminatorDialog(String s) {
        DialogBox db = new DialogBox();
        db.displayText.setText(s);
        db.setAlignment(Pos.CENTER_LEFT);
        db.labelContainer.setStyle("-fx-background-color: #fff");
        db.displayText.setStyle("-fx-text-fill: #000");
        return db;
    }
}
