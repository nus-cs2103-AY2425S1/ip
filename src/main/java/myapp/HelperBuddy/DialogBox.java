package myapp.helperbuddy;

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
 * DialogBox class which represents the dialog boxes for User and Duke
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox object with the specified text and image.
     * This constructor loads the FXML layout for the dialog box and initializes
     * the dialog text and display picture.
     *
     * @param text to be displayed in the dialog box.
     * @param img  to be displayed alongside the text.
     */
    private DialogBox(String text, Image img) {
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
    }

    /**
     * Creates and returns a DialogBox representing the user's dialog.
     * The dialog box will display the specified text and image without flipping the alignment.
     *
     * @param text to be displayed in the user's dialog box.
     * @param img  to be displayed alongside the user's dialog.
     * @return DialogBox object containing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates and returns a DialogBox representing the bot's dialog.
     * The dialog box will display the specified text and image, with the alignment flipped
     * so that the image appears on the left side and the text on the right.
     *
     * @param text to be displayed in the bot's dialog box.
     * @param img  to be displayed alongside the bot's dialog.
     * @return DialogBox object containing the bot's dialog.
     */
    public static DialogBox getBuddyDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Flips the dialog box horizontally, reversing the order of the text and image.
     * This method is used to align the bot's dialog differently from the user's dialog.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
}