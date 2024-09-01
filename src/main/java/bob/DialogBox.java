package bob;

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
 * Represents a dialog box in the GUI with an image and text.
 * The dialog box consists of an {@link ImageView} to represent the speaker's face
 * and a {@link Label} containing the speaker's message.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox instance with the specified text and image.
     * This private constructor is called to create the dialog box with appropriate layout and styling.
     *
     * @param text The text message to be displayed in the dialog box.
     * @param img  The image to be displayed in the dialog box.
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
     * Flips the dialog box such that the image is on the left and the text is on the right.
     * This method is used to position the dialog box based on the speaker.
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
     * @param text The text message to be displayed in the dialog box.
     * @param img  The image to be displayed in the dialog box.
     * @return A DialogBox instance for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box for Bob (the chatbot) with the specified text and image.
     * This dialog box is flipped to position the image on the left and the text on the right.
     *
     * @param text The text message to be displayed in the dialog box.
     * @param img  The image to be displayed in the dialog box.
     * @return A DialogBox instance for Bob.
     */
    public static DialogBox getBobDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
