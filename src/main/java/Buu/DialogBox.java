package Buu;

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
    private Label dialog; // The label containing the dialog text
    @FXML
    private ImageView displayPicture; // The image view displaying the speaker's picture

    /**
     * Constructs a DialogBox with the specified text and image.
     *
     * @param text the text to display in the dialog box
     * @param img the image to display as the speaker's picture
     * @param bgColor the background color of the dialog box in CSS format
     */
    private DialogBox(String text, Image img, String bgColor) {
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

        // Set background color and padding for the dialog
        this.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 10; -fx-padding: 10;");
        // Align the text to the center vertically
        dialog.setAlignment(Pos.CENTER);
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
     * Creates a dialog box for the user with the specified text and image.
     *
     * @param text the text to display in the user dialog
     * @param img the image to display as the user's picture
     * @return a new DialogBox instance representing the user's dialog
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img, "#f6e0b5"); // Light pastel orange for user
        db.setAlignment(Pos.CENTER_RIGHT); // Align text to the right for user
        return db;
    }

    /**
     * Creates a dialog box for Buu with the specified text and image.
     *
     * @param text the text to display in Buu's dialog
     * @param img the image to display as Buu's picture
     * @return a new DialogBox instance representing Buu's dialog
     */
    public static DialogBox getBuuDialog(String text, Image img) {
        var db = new DialogBox(text, img, "#eea990"); // Light lavender for Buu
        db.flip();
        db.setAlignment(Pos.CENTER_LEFT); // Align text to the left for Buu
        return db;
    }
}
