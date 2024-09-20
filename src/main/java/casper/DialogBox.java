package casper;

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
 * Represents a dialog box used in the user interface.
 * This class extends {@link HBox} and is used to display a dialog with a text label and an image.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new {@code DialogBox} with the specified text and image.
     * This constructor initializes the dialog box by loading the FXML layout and setting the text
     * and image based on the provided parameters.
     *
     * @param text The text to be displayed in the dialog box.
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
     * Flips the dialog box such that the {@code ImageView} is on the left and the text label is on the right.
     * This method is used to switch the layout of the dialog box for different visual appearances.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a dialog box for the user with the specified text and image.
     *
     * @param s The text to be displayed in the dialog box.
     * @param i The image to be displayed in the dialog box.
     * @return A new {@code DialogBox} instance for the user.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Creates a dialog box for the Casper entity with the specified text and image,
     * and flips the dialog box to have the image on the left and text on the right.
     *
     * @param s The text to be displayed in the dialog box.
     * @param i The image to be displayed in the dialog box.
     * @return A new {@code DialogBox} instance for the Casper entity.
     */
    public static DialogBox getCasperDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}
