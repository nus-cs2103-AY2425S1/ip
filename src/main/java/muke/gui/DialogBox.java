package muke.gui;

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
 * Represents a dialog box in the GUI for displaying messages and profile pictures in a chat-like interface.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor to initialize a DialogBox with the specified text and image.
     *
     * @param text The text to display in the dialog box.
     * @param img  The profile picture associated with the dialog.
     */
    private DialogBox(String text, Image img) {
        loadFXML();
        dialog.setText(text);
        displayPicture.setImage(img);
        setDisplayPictureClip();
    }

    /**
     * Loads the FXML layout for the DialogBox.
     */
    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets a circular clip on the display picture to shape it as a circle.
     */
    private void setDisplayPictureClip() {
        Circle clip = new Circle(30, 30, 30);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box horizontally to align with the left side.
     * This is typically used for displaying responses from different speakers.
     */
    private void flip() {
        ObservableList<Node> nodes = FXCollections.observableArrayList(getChildren());
        Collections.reverse(nodes);
        getChildren().setAll(nodes);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a DialogBox for Muke's introduction message with the image flipped.
     *
     * @param text The introduction text.
     * @param img  Muke's profile picture.
     * @return A flipped DialogBox instance for Muke's introduction.
     */
    public static DialogBox getMukeIntro(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Creates a DialogBox for the user's message.
     *
     * @param text The user's message.
     * @param img  The user's profile picture.
     * @return A DialogBox instance for the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a DialogBox for Muke's message with the image flipped.
     *
     * @param text Muke's message.
     * @param img  Muke's profile picture.
     * @return A flipped DialogBox instance for Muke's message.
     */
    public static DialogBox getMukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}