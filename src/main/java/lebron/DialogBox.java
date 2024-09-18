package lebron;

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
 * Represents a dialog box consisting of an ImageView to represent the speaker's
 * face and a label containing text from the speaker. This class handles the
 * layout and arrangement of dialog boxes for both user and Duke (chatbot)
 * dialogs.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image. Loads the FXML
     * layout for the dialog box and sets the provided text and image.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed alongside the text.
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
     * Flips the dialog box such that the ImageView is on the left and the text
     * on the right. This method is used to differentiate between user dialogs
     * and Duke dialogs visually.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box for the user with the specified text and image.
     *
     * @param text The text to be displayed in the user dialog box.
     * @param img The image to be displayed in the user dialog box.
     * @return A DialogBox object representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box for Duke (the chatbot) with the specified text and
     * image. The dialog box is flipped so that the image appears on the left.
     *
     * @param text The text to be displayed in the Duke dialog box.
     * @param img The image to be displayed in the Duke dialog box.
     * @return A DialogBox object representing Duke's dialog.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
