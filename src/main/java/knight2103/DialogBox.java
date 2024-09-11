package knight2103;

import java.util.Collections;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;

/**
 * A HBox that contains dialog text and profile image.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a DialogBox object containing user input text and user profile image.
     * The text and user profile image are aligned right.
     *
     * @return DialogBox object containing the text and image.
     * @param text User input into the textfield.
     * @param img User profile image
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a DialogBox object containing the bot's response text and bot's profile image.
     * The response text and bot's profile image are aligned left.
     *
     * @return DialogBox object containing the text and image.
     * @param text Bot's response text.
     * @param img Bot's profile image
     */
    public static DialogBox getKnight2103Dialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

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
}
