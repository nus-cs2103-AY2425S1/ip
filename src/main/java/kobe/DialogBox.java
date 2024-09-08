package kobe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

/**
 * Custom control for dialog boxes used in the chatbot.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, ImageView img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setWrapText(true); // Enable text wrapping
        dialog.setMaxWidth(250); // Set max width to prevent overflow

        displayPicture.setImage(img.getImage());
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);

        // Apply circular clipping to the image
        Circle clip = new Circle(25, 25, 25);
        displayPicture.setClip(clip);

        this.getStyleClass().add("dialog-box");
        dialog.setStyle("-fx-text-fill: inherit;"); // Ensure dialog inherits text color from CSS
        this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(10); // Add spacing between the image and text
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
     * Creates a user dialog with user-specific styling.
     *
     * @param text Text to be displayed in the dialog box.
     * @param img  ImageView containing the user's image.
     * @return A dialog box configured for user input.
     */
    public static DialogBox getUserDialog(String text, ImageView img) {
        DialogBox db = new DialogBox(text, img);
        db.getStyleClass().add("user-dialog");
        return db;
    }

    /**
     * Creates a Kobe dialog with specific styling.
     *
     * @param text Text to be displayed in the dialog box.
     * @param img  ImageView containing Kobe's image.
     * @return A dialog box configured for Kobe's response.
     */
    public static DialogBox getKobeDialog(String text, ImageView img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.getStyleClass().add("kobe-dialog");
        return db;
    }
}
