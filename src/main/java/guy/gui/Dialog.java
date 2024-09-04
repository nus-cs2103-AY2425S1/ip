package guy.gui;

import java.io.IOException;

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
 * Representation of a dialog box in the GUI version of ThatOneGuy.
 * Can be for the program, or its user.
 */
public class Dialog extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private ImageView display;

    /**
     * Constructs a dialog box with the given text and picture.
     * This constructor can't be called outside of this class.
     * @param text Text contents of the dialog box
     * @param picture A profile picture to use in the dialog box
     */
    private Dialog(String text, Image picture) {
        try {
            FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("/components/Dialog.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        display.setImage(picture);
    }

    /**
     * Flips a dialog box horizontally.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a dialog box for the user's message.
     *
     * @param text The user's message.
     * @param picture The user's profile picture.
     * @return A Dialog instance for the user's message.
     */
    public static Dialog getUserDialog(String text, Image picture) {
        return new Dialog(text, picture);
    }

    /**
     * Creates a dialog box for ThatOneGuy's message.
     *
     * @param text The user's message.
     * @param picture The user's profile picture.
     * @return A Dialog instance for the user's message.
     */
    public static Dialog getGuyDialog(String text, Image picture) {
        Dialog db = new Dialog(text, picture);
        db.flip();
        return db;
    }
}
