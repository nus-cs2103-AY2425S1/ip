package talkie;

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
import javafx.scene.text.Font;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 * <p>
 * The {@code DialogBox} class is a custom JavaFX {@code HBox} component that displays a dialog
 * with an associated image, such as a user's profile picture. The dialog can be flipped
 * to adjust the positioning of the image and text, depending on whether the dialog is from
 * the user or the application (Talkie).
 * </p>
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} instance with the specified text and image.
     * <p>
     * This constructor loads the FXML layout for the dialog box, sets the dialog text,
     * and assigns the provided image to the {@code ImageView}.
     * </p>
     *
     * @param text The text to be displayed in the dialog.
     * @param img  The image to be displayed in the {@code ImageView}.
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
        dialog.setFont(Font.font("Verdana"));
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the {@code ImageView} is on the left and the text on the right.
     * <p>
     * This method reorders the child nodes of the {@code HBox} to reverse their positions,
     * typically used for displaying the application's responses with the image on the left side.
     * </p>
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a {@code DialogBox} for the user with the specified text and image.
     * <p>
     * This method returns a {@code DialogBox} where the image is on the right side,
     * representing the user's input in the dialog.
     * </p>
     *
     * @param text The user's input text.
     * @param img  The user's profile image.
     * @return A {@code DialogBox} instance representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a {@code DialogBox} for Talkie with the specified text and image.
     * <p>
     * This method returns a {@code DialogBox} where the image is on the left side,
     * representing the application's response in the dialog.
     * </p>
     *
     * @param text The application's response text.
     * @param img  The application's profile image.
     * @return A {@code DialogBox} instance representing Talkie's dialog.
     */
    public static DialogBox getTalkieDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
