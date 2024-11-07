package chatbuddy;

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
 * Represents a dialog box containing a label with text and an image for the speaker.
 * It is used in the ChatBuddy GUI.
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

    /**
     * Flips the dialog box, so the image is on the left and the text is on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates and returns a dialog box for the user.
     *
     * @param text The text spoken by the user.
     * @param img The user's display picture.
     * @return The created dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.getStyleClass().add("dialog-box");
        db.getStyleClass().add("user");
        return db;
    }

    /**
     * Creates and returns a dialog box for the chatbot.
     *
     * @param text The text spoken by ChatBuddy.
     * @param img The ChatBuddy display picture.
     * @return The created dialog box.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.getStyleClass().add("dialog-box");
        db.getStyleClass().add("chatbuddy");
        db.flip();
        return db;
    }
}
