package jade.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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

    /**
     * Constructs a DialogBox object with specified text and image.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img  The image to represent the speaker's face.
     */
    private DialogBox(String text, Image img) {
        assert text != null && !text.trim().isEmpty() : "Text for dialog box should not be null or empty";
        assert img != null : "Image for dialog box should not be null";

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

        Circle clip = new Circle(25, 25, 25);
        displayPicture.setClip(clip);

        adjustPadding();
    }

    /**
     * Adjusts padding dynamically based on the height of the children.
     */
    private void adjustPadding() {
        this.heightProperty().addListener((observable, oldValue, newValue) -> {
            double currentHeight = newValue.doubleValue();
            if (currentHeight <= 60) {
                this.setPadding(new Insets(0, 5, 10, 5));
            } else {
                this.setPadding(new Insets(12, 5, 12, 5));
            }
        });
    }

    /**
     * Creates a dialog box for the user's input with the specified text and image.
     *
     * @param text The text to be displayed in the user's dialog box.
     * @param img  The image representing the user.
     * @return A DialogBox instance representing the user's input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        assert text != null && !text.trim().isEmpty() : "User dialog text should not be null or empty";
        assert img != null : "User image should not be null";

        var db = new DialogBox(text, img);
        db.setAlignment(Pos.BOTTOM_RIGHT);
        db.dialog.getStyleClass().add("label");
        return db;
    }

    /**
     * Creates a dialog box for Jade's response with the specified text and image.
     * The dialog box is flipped so that the image is on the left and the text is on the right.
     *
     * @param text The text of Jade's response.
     * @param img  The image representing Jade.
     * @param isError Indicates if the response is an error message.
     * @return A DialogBox instance for Jade's response.
     */
    public static DialogBox getJadeDialog(String text, Image img, boolean isError) {
        assert text != null && !text.trim().isEmpty() : "Jade dialog text should not be null or empty";
        assert img != null : "Jade image should not be null";

        var db = new DialogBox(text, img);
        db.flip();

        if (isError) {
            db.dialog.getStyleClass().add("error-label");
        }

        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and the text is on the right.
     * Used to differentiate between the user's input and Jade's response.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        assert tmp != null && !tmp.isEmpty() : "Dialog children nodes should not be null or empty";

        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.BOTTOM_LEFT);
        dialog.getStyleClass().add("reply-label");
    }
}
