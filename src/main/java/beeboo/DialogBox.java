package beeboo;

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
     * Constructs a DialogBox object with the specified text and image.
     *
     * @param text the text to be displayed in the dialog box
     * @param img  the image to be displayed as the speaker's picture
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
    }

    /**
     * Creates a DialogBox for the welcome message with BeeBoo's image.
     *
     * @return a DialogBox containing the welcome message
     */
    public static DialogBox welcomeMessage() {
        return new DialogBox("Hello! I'm BeeBoo. How can I help you today?", Beeboo.getBeebooImage());
    }

    /**
     * Creates a DialogBox for the user input with the specified text and image.
     *
     * @param text the user's input text
     * @param img  the user's image
     * @return a DialogBox containing the user's input
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a DialogBox for BeeBoo's response with the specified text and image.
     * The dialog box is flipped to display the image on the left and text on the right.
     *
     * @param text the text for BeeBoo's response
     * @param img  the image representing BeeBoo
     * @return a DialogBox containing BeeBoo's response
     */
    public static DialogBox getBeeBooDialogue(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
