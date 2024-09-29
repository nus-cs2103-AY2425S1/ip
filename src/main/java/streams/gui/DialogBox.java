package streams.gui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

    private DialogBox(String text, Image img) {
        assert text != null : "Label text should not be null";
        assert img != null : "Image should not be null";
        dialog = new Label(text);
        dialog.setWrapText(true);
        dialog.setMaxWidth(250);
        dialog.setPadding(new Insets(8));
        dialog.setStyle("-fx-background-color: #c08ff3; -fx-background-radius: 10;");

        displayPicture = new ImageView(img);
        displayPicture.setFitHeight(50);
        displayPicture.setFitWidth(50);

        Circle clip = new Circle(25, 25, 25);
        displayPicture.setClip(clip);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(dialog, displayPicture);
        this.setSpacing(10);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and the text is on the right.
     * This is typically used to differentiate between user messages and system messages
     * in a chat interface.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        dialog.setStyle("-fx-background-color: #8794d7; -fx-background-radius: 10;");
        this.getChildren().setAll(displayPicture, dialog);
    }

    /**
     * Creates a dialog box for user input with the specified text and image.
     * The text will be displayed on the right and the image on the left.
     *
     * @param text The text to be displayed.
     * @param img  The image to be displayed.
     * @return A DialogBox object representing the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        assert text != null : "Label text should not be null";
        assert img != null : "Image should not be null";
        var db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_RIGHT);
        return db;
    }

    /**
     * Creates a dialog box for system messages with the specified text and image.
     * The text will be displayed on the left and the image on the right by flipping the dialog box.
     *
     * @param text The text to be displayed.
     * @param img  The image to be displayed.
     * @return A DialogBox object representing the system's message.
     */
    public static DialogBox getStreamsDialog(String text, Image img) {
        assert text != null : "Label text should not be null";
        assert img != null : "Image should not be null";
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
