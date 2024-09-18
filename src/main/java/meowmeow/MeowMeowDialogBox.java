package meowmeow;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;

/**
 * Represents a dialog box for MeowMeow that displays an image on the left and text on the right.
 */
public class MeowMeowDialogBox extends DialogBox {

    /**
     * Constructs a MeowMeowDialogBox with the specified text and image.
     *
     * @param text The text to display in the dialog box.
     * @param img The image to display in the dialog box.
     */
    public MeowMeowDialogBox(String text, Image img) {
        super(text, img, "/view/MeowMeowDialogBox.fxml");
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
     * Creates and returns a flipped MeowMeowDialogBox with the specified text and image.
     *
     * @param text The text to display in the dialog box.
     * @param img The image to display in the dialog box.
     * @return A new instance of MeowMeowDialogBox with the image on the left.
     */
    public static DialogBox getDialog(String text, Image img) {
        var db = new MeowMeowDialogBox(text, img);
        db.flip();
        return db;
    }
}
