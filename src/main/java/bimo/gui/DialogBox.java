package bimo.gui;

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
 * Represents a controller that contains a profile picture and text label.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(
                    "/views/DialogBox.fxml"));
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
     * Flips the dialog box so that chat bot response is mirror image of
     * user text input.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates the user dialogBox with picture on the right and
     * label on the left.
     *
     * @param text Text input of user.
     * @param img Image picture of user.
     * @return DialogBox controller that contains user input and picture.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var userDialogBox = new DialogBox(text, img);
        return userDialogBox;
    }

    /**
     * Creates the Bimo dialogBox with picture on the left and
     * label on the right.
     *
     * @param text Text response of Bimo.
     * @param img Image picture of Bimo.
     * @return DialogBox controller that contains Bimo response and picture.
     */
    public static DialogBox getBimoDialog(String text, Image img) {
        var bimoDialogBox = new DialogBox(text, img);
        bimoDialogBox.flip();
        return bimoDialogBox;
    }
}
