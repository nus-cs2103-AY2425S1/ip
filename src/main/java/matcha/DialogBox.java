package matcha;

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
import javafx.scene.shape.Circle;

/**
 * A dialog box to represent the dialog between the user and Matcha.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    private DialogBox(String text, Image img) {
        assert text != null : "Text should not be null";
        assert img != null : "Image should not be null";
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
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);
        Circle clip = new Circle(23, 23, 23);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
        dialog.getStyleClass().add("reply-label");
    }
    /**
     * Returns a dialog box representing the user's dialog.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     * @return A dialog box representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        assert text != null : "Text should not be null";
        assert img != null : "Image should not be null";
        return new DialogBox(text, img);
    }
    /**
     * Returns a dialog box representing Matcha's dialog.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     * @return A dialog box representing Matcha's dialog.
     */
    public static DialogBox getMatchaDialog(String text, Image img) {
        assert text != null : "Text should not be null";
        assert img != null : "Image should not be null";
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
