package donna.gui;

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
 * Represents a dialog box.
 * Contains a message and a picture.
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
        ObservableList<Node> temp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(temp);
        getChildren().setAll(temp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a dialog box for the user's messages.
     *
     * @param s The text to display in the dialog box.
     * @param i The image to display in the dialog box.
     * @return A DialogBox instance with the user's message and image.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Creates a dialog box for Donna's message.
     *
     * @param s The text to display in the dialog box.
     * @param i The image to display in the dialog box.
     * @return A DialogBox instance with Donna's image and message.
     */
    public static DialogBox getDonnaDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }

}
