package sumode.ui;

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

    /**
     * Constructor for DialogBox.
     * @param text text by SumoDE/user.
     * @param img profile picture of SumoDE/user.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
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
     * Change background to red for attention.
     */
    private void changeBackgroundToRed() {
        dialog.getStyleClass().add("angry-reply-label");
    }

    /**
     * Change background to yellowish green for aesthetic.
     */
    private void changeBackgroundToYellowishGreen() {
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Returns a user dialog
     * @param text text by user.
     * @param img profile picture of user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a normal SumoDE dialog
     * @param text text by SumoDE.
     * @param img profile picture of SumoDE.
     */
    public static DialogBox getSumoDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeBackgroundToYellowishGreen();
        return db;
    }

    /**
     * Returns an angry SumoDE dialog
     * @param text text by SumoDE.
     * @param img profile picture of SumoDE.
     */
    public static DialogBox getSumoAngryDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeBackgroundToRed();
        return db;
    }
}
