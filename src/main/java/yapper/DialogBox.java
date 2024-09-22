package yapper;

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
 * DialogBox represents a dialog box containing a speaker's message and avatar.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Label nameLabel;

    /**
     * Constructs a DialogBox with the given text, image, and speaker name.
     *
     * @param text The text to display.
     * @param img  The image to display.
     * @param name The name of the speaker.
     */
    private DialogBox(String text, Image img, String name) {
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
        nameLabel.setText(name); // Set the speaker's name
    }

    /**
     * Creates a DialogBox for the user.
     *
     * @param text The user's input.
     * @param img  The user's avatar.
     * @return A DialogBox containing the user's input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "User"); // Pass "User" as the speaker name
    }

    /**
     * Creates a DialogBox for the system (e.g., Yapper).
     *
     * @param text The system's response.
     * @param img  The system's avatar.
     * @return A DialogBox containing the system's response.
     */
    public static DialogBox getYapperDialog(String text, Image img) {
        var db = new DialogBox(text, img, "Yapper"); // Pass "Yapper" as the speaker name
        db.flip();
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and the text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
}
