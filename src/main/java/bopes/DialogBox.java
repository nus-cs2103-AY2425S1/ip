package bopes;

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

import java.io.IOException;

/**
 * DialogBox represents a dialog box containing a speaker's message and avatar.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the given text and image.
     *
     * @param text The text to display.
     * @param img  The image to display.
     */
    private DialogBox(String text, Image img, boolean isError) {
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
    
        if (isError) {
            this.getStyleClass().add("error-command");
        } else {
            this.getStyleClass().add("valid-command");
        }
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

    /**
     * Creates a DialogBox for the user.
     *
     * @param text The user's input.
     * @param img  The user's avatar.
     * @return A DialogBox containing the user's input.
     */
    public static DialogBox getUserDialog(String text, Image img, boolean isError) {
        return new DialogBox(text, img, isError);
    }

    /**
     * Creates a DialogBox for the system (e.g., Bopes).
     *
     * @param text The system's response.
     * @param img  The system's avatar.
     * @return A DialogBox containing the system's response.
     */
    public static DialogBox getBopesDialog(String text, Image img, boolean isError) {
        var db = new DialogBox(text, img, isError);
        db.flip();
        return db;
    }
}
