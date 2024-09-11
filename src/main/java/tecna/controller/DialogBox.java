package tecna.controller;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private HBox messageBox;
    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        displayPicture.setFill(new ImagePattern(img));
        dialog.setText(text);
        this.getStylesheets().add(getClass().getResource("/style/DialogBox.css").toExternalForm());
        dialog.setMaxWidth(200);
        dialog.setWrapText(true);
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
     * Generates a dialog box for user.
     *
     * @param text User request entered by the user.
     * @param img Avatar of the user.
     * @return A DialogBox.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        assert text != null;
        assert img != null;

        var userDialog =  new DialogBox(text, img);
        userDialog.messageBox.getStyleClass().add("user-message-box");
        return userDialog;
    }

    /**
     * Generates a dialog box for Tecna.
     *
     * @param text Tecna's response to the user's request.
     * @param img Tecna's avatar.
     * @return A Dialog Box.
     */
    public static DialogBox getTecnaDialog(String text, Image img) {
        assert text != null;
        assert img != null;

        var db = new DialogBox(text, img);
        db.flip();
        db.messageBox.getStyleClass().add("duke-message-box");
        return db;
    }
}

