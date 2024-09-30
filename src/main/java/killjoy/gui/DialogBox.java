package killjoy.gui;

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
 * Represents a dialog box consisting of an ImageView to represent the user and KillJoy and a Label containing
 * text from the user and KillJoy.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private ImageView welcomeImage;

    /**
     * Constructs a DialogBox object with the specified text and image.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     */
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
     * Returns a DialogBox object with the specified text and image.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     * @return A DialogBox object with the specified text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a DialogBox object with the specified text and image, with the ImageView on the left and text
     * on the right.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     * @return A DialogBox object with the specified text and image, with the ImageView on the left and
     *      text on the right.
     */
    public static DialogBox getKjDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}

