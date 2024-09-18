package bestie;
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
 * This Javadoc comment was generated with some help from ChatGPT, but edited to better suit the context.
 * Represents a dialog box that contains a message and a display picture.
 * This class is used to create a chat-like interface where messages can be displayed alongside user or bot images.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Initialises a DialogBox with the specified text and image.
     *
     * @param text The message text to be displayed in the dialog box.
     * @param img The image to be displayed alongside the message.
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
     * This method is useful for changing the layout of the dialog box for different users
     * providing a clear distinction between user and bot messages.
     */
    private void flip() {
        dialog.getStyleClass().add("reply-label");
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * User's dialog box with user image and text.
     *
     * @param s User input command entered via the text field.
     * @param i User image.
     * @return Dialog box created for the user for display.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * User's dialog box with user image and text.
     *
     * @param s Bestie's response entered via the text field.
     * @param i Bestie's image.
     * @return Dialog box created for bestie for display.
     */
    public static DialogBox getBestieDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }

}
