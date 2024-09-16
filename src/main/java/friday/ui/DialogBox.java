package friday.ui;

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

    /**
     * Creates a DialogBox with the specified text and image.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img  The image to be displayed in the dialog box.
     */
    private DialogBox(String text, Image img, boolean isUser) {
        assert text != null : "Text should not be null";
        assert img != null : "Image should not be null";
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
        if (isUser) {
            this.dialog.getStyleClass().add("user-label");
        } else {
            this.dialog.getStyleClass().add("app-label");
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a DialogBox for the user with the specified text and image.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img  The image to be displayed in the dialog box.
     * @return A DialogBox instance for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        assert text != null : "Text should not be null";
        assert img != null : "Image should not be null";
        return new DialogBox(text, img, true);
    }

    /**
     * Creates a DialogBox for Friday with the specified text and image.
     * The dialog box is flipped such that the ImageView is on the left and text on the right.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img  The image to be displayed in the dialog box.
     * @return A DialogBox instance for Friday.
     */
    public static DialogBox getFridayDialog(String text, Image img) {
        assert text != null : "Text should not be null";
        assert img != null : "Image should not be null";
        var db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }
}
