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
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        assert text != null : "Text should not be null"; // Assert that text is not null
        assert img != null : "Image should not be null"; // Assert that image is not null

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert dialog != null : "Dialog label should have been initialized"; // Assert that dialog label is initialized
        assert displayPicture != null : "Display picture should have been initialized";
        // Assert that image view is initialized

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        assert tmp != null : "DialogBox children list should not be null"; // Assert that children list is valid

        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    public static DialogBox getUserDialog(String text, Image img) {
        assert text != null && !text.isEmpty() : "User dialog text should not be null or empty"; // Assert text is valid
        assert img != null : "User image should not be null"; // Assert image is valid
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        assert text != null && !text.isEmpty() : "Duke dialog text should not be null or empty"; // Assert text is valid
        assert img != null : "Duke image should not be null"; // Assert image is valid
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
