package megamind.gui;

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
 * The `DialogBox` class represents a custom control for displaying dialog messages
 * with an associated image. It extends `HBox` and provides methods to create
 * user and cookie dialog boxes.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a `DialogBox` with the specified text and image
     *
     * @param text The text to be displayed in the dialog.
     * @param img  The image to be displayed alongside the dialog.
     */
    public DialogBox(String text, Image img) {
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
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a user dialog box with the specified text and image.
     *
     * @param text The text to be displayed in the dialog.
     * @param img  The image to be displayed alongside the dialog.
     * @return A `DialogBox` configured as a user dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a Megamind dialog box with the specified text and image.
     * The dialog box is flipped such that the image is on the left.
     *
     * @param text The text to be displayed in the dialog.
     * @param img  The image to be displayed alongside the dialog.
     * @return A `DialogBox` configured as a cookie dialog.
     */
    public static DialogBox getMegamindDialog(String text, Image img) {
        assert text != null : "Text should not be null";
        assert img != null : "Image should not be null";
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
