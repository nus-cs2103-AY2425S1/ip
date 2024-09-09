package lama.gui;

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
 * The DialogBox class represents a custom control in JavaFX that displays a dialog box
 * containing an image and text. The class is designed using FXML and extends HBox.
 * It can be used to represent both user and system (Lama) dialog boxes in a chat interface.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     * The FXML file for this control is loaded and associated with this instance.
     *
     * @param text  The text to be displayed in the dialog box
     * @param image The image to be displayed alongside the text
     */
    public DialogBox(String text, Image image) {
        loadFxml();
        dialog.setText(text);
        displayPicture.setImage(image);
    }

    private void loadFxml() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            System.err.println("Error loading DialogBox FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Creates a DialogBox for user dialogs with the specified text and image.
     *
     * @param text  The text to be displayed in the user dialog box
     * @param image The image to be displayed alongside the text in the user dialog box
     * @return A DialogBox representing the user's dialog
     */
    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    /**
     * Creates a DialogBox for system (Lama) dialogs with the specified text and image.
     * The dialog box is flipped such that the image is on the left and text on the right.
     *
     * @param text  The text to be displayed in the system dialog box
     * @param image The image to be displayed alongside the text in the system dialog box
     * @return a flipped DialogBox representing the system's dialog
     */
    public static DialogBox getLamaDialog(String text, Image image) {
        var db = new DialogBox(text, image);
        db.flip();
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and the text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
}
