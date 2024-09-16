package shoai;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.Collections;

/**
 * Represents a dialog box used in the ShoAI application, displaying messages
 * from the user or the chatbot.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a new DialogBox with the specified text and image.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     * @param styleClass The CSS style class to be applied to the dialog box.
     */
    private DialogBox(String text, Image img, String styleClass) {
        assert text != null && !text.isEmpty() : "Dialog text cannot be null or empty";
        assert img != null : "Image cannot be null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        dialog.getStyleClass().add(styleClass);

        // Apply DropShadow effect to the ImageView
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.GRAY);
        shadow.setRadius(5);
        shadow.setOffsetX(3);
        shadow.setOffsetY(3);
        displayPicture.setEffect(shadow);
    }

    /**
     * Flips the dialog box to swap the user and chatbot positions.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box for user messages.
     *
     * @param text The text to be displayed in the user dialog box.
     * @param img The image to be displayed in the user dialog box.
     * @return The created user dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "user-dialog");
    }

    /**
     * Creates a dialog box for chatbot messages.
     *
     * @param text The text to be displayed in the chatbot dialog box.
     * @param img The image to be displayed in the chatbot dialog box.
     * @return The created chatbot dialog box.
     */
    public static DialogBox getChatbotDialog(String text, Image img) {
        var db = new DialogBox(text, img, "chatbot-dialog");
        db.flip();
        return db;
    }

    /**
     * Creates a dialog box for error messages.
     *
     * @param text The error text to be displayed.
     * @param img The image to be displayed in the error dialog box.
     * @return The created error dialog box.
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img, "error-dialog"); // Use a new style class for error
        db.flip(); // Flip so it's on the chatbot side
        return db;
    }
}
