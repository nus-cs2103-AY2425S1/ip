package morgana.ui;

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
     * Creates a {@code DialogBox} with the given text and image.
     * The text is displayed on the left and the image is displayed on the right.
     *
     * @param text The text to display in the dialog box.
     * @param img The image to display alongside the text.
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
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a {@code DialogBox} for the user with the given text and image.
     *
     * @return A {@code DialogBox} containing the user's input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a flipped {@code DialogBox} for Morgana with the given text and image.
     * Additionally, a style class can be applied to the {@code dialog} to customize
     * its appearance.
     *
     * @return A flipped {@code DialogBox} containing Morgana's response.
     */
    public static DialogBox getMonaDialog(String text, Image img, String styleClass) {
        var db = new DialogBox(text, img);
        db.flip();
        if (styleClass != null) {
            db.dialog.getStyleClass().add(styleClass);
        }
        return db;
    }
}
