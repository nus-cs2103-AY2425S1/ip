package gui;

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

import java.io.IOException;
import java.util.Collections;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        int radius = 60;
        // Create a circular clip for the image
        Circle clip = new Circle(radius);

        // Set the image to displayPicture
        displayPicture.setImage(img);
        displayPicture.setFitWidth(radius * 2);
        displayPicture.setFitHeight(radius * 2);

        // Center the clip on the image
        clip.setCenterX(radius);
        clip.setCenterY(radius);

        // Set the ImageView as the graphic for displayPicture
        displayPicture.setClip(clip);

    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle(
                "-fx-text-fill: #0000FF;" + // Text color blue
                        "-fx-background-color: #3ebcef; " + // Green background
                        "-fx-background-radius: 10px; " +   // Rounded corners
                        "-fx-border-radius: 10px; " +       // Rounded border
                        "-fx-border-color: #4557a0; " +     // Darker green border
                        "-fx-border-width: 2px; " +         // Border width
                        "-fx-padding: 10px;"                // Padding inside the bubble
        );
        return db;
    }

    public static DialogBox getDannyDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle(
                "-fx-text-fill: #724168;" +
                        "-fx-background-color: #e0abd7; " + // Light gray background
                        "-fx-background-radius: 10px; " +   // Rounded corners
                        "-fx-border-radius: 10px; " +       // Rounded border
                        "-fx-border-color: #724168; " +     // Darker gray border
                        "-fx-border-width: 2px; " +         // Border width
                        "-fx-padding: 10px;"                // Padding inside the bubble
        );
        db.flip();
        return db;
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
}
