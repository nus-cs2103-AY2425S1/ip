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
    private Label dialog;
    @FXML
    private ImageView displayPicture;

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

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img, "#f6e0b5"); // Light pastel orange for Goku
        db.setAlignment(Pos.CENTER_RIGHT); // Align text to the right for user
        return db;
    }

    public static DialogBox getBuuDialog(String text, Image img) {
        var db = new DialogBox(text, img, "#eea990"); // Light lavender for Buu
        db.flip();
        db.setAlignment(Pos.CENTER_LEFT); // Align text to the left for Buu
        return db;
    }
}
