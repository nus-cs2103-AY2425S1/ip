package duke.javafx;

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
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String styleClass) {
        assert text != null : "Text for dialog should not be null";
        assert img != null : "Image for dialog should not be null";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            assert fxmlLoader != null : "FXMLLoader should not be null"; // Assertion to ensure loader is valid
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();

            assert dialog != null : "Label 'dialog' should be initialized after loading FXML";
            assert displayPicture != null : "ImageView 'displayPicture' should be initialized after loading FXML";

            double radius = 49.5; // half of fitHeight/fitWidth of ImageView
            Circle clip = new Circle(radius, radius, radius);
            displayPicture.setClip(clip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        // Apply the style class to the HBox (this) instead of the Label
        dialog.getStyleClass().add(styleClass);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        assert !tmp.isEmpty() : "DialogBox children should not be empty when flipping";

        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img, String styleclass) {
        return new DialogBox(text, img, styleclass);
    }

    public static DialogBox getDukeDialog(String text, Image img, String styleclass) {
        var db = new DialogBox(text, img, styleclass);
        db.flip();
        return db;
    }
}
