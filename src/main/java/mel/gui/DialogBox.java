package mel.gui;

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
 * DialogBox class represents the container box
 * displaying each dialogue text.
 * displaying each dialogue text.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for Mel GUI's dialog boxes.
     * @param text string label for dialog box.
     * @param img path to image file representing dialog source.
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
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /** Formats the dialog box to indicate warning from exceptions. */
    private void setExceptionDialog() {
        dialog.getStyleClass().add("exception-label");
    }

    /**
     * Generates dialog box for user inputs.
     * @param s string label for dialog box.
     * @param i path to image file representing dialog source.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Generates dialog box for Mel response during exceptions.
     * @param s string label for dialog box.
     * @param i path to image file representing dialog source.
     */
    public static DialogBox getMelExceptionDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        db.setExceptionDialog();
        return db;
    }

    /**
     * Generates dialog box for Mel responses.
     * @param s string label for dialog box.
     * @param i path to image file representing dialog source.
     */
    public static DialogBox getMelDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}
