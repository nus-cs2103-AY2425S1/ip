import java.io.IOException;
import java.util.Collections;

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

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("carine-label");
    }

    /**
     * Creates a dialog box that displays the user's input message with the user's image.
     *
     * @param text The message to be displayed in the dialog box.
     * @param img  The image representing the user.
     * @return A {@code DialogBox} instance containing the user's message and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.getStyleClass().add("user-label");
        return db;
    }

    /**
     * Creates a dialog box that displays Carine's input message with the user's image.
     *
     * @param text The message to be displayed in the dialog box.
     * @param img  The image representing Carine.
     * @return A {@code DialogBox} instance containing the Carine's message and image.
     */
    public static DialogBox getCarineDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        if (text.startsWith("ERROR:")) {
            db.dialog.getStyleClass().add("carine-error-label");
        }
        return db;
    }

    /**
     * Creates a dialog box that displays reminder message.
     *
     * @param text The message to be displayed in the dialog box.
     * @param img  The image representing Carine.
     * @return A {@code DialogBox} instance containing the Carine's message and image.
     */
    public static DialogBox getReminderDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.getStyleClass().add("reminder-label");
        return db;
    }
}
