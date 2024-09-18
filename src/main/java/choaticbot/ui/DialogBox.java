package choaticbot.ui;

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
 * Represents a dialog box consisting of an {@link ImageView} to represent the speaker's face
 * and a {@link Label} containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new {@code DialogBox} with the specified text and image.
     * Initializes the dialog box layout using an FXML file.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed as the speaker's face.
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
     * Creates a {@code DialogBox} for the user with the specified text and image.
     * The dialog box is initialized with the image on the right and the text on the left.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed as the user's face.
     * @return A {@code DialogBox} for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a {@code DialogBox} for the ChoaticBot with the specified text and image.
     * The dialog box is initialized with the image on the left and the text on the right.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed as the ChoaticBot's face.
     * @return A {@code DialogBox} for the ChoaticBot.
     */
    public static DialogBox getChoaticBotDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
