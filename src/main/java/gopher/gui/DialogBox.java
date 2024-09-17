package gopher.gui;

import java.io.IOException;
import java.util.Collections;
import java.util.regex.Pattern;

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

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

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
        displayPicture.setFitHeight(64);
        displayPicture.setPreserveRatio(true);
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

    /**
     * Render the background of the gopher dialog box based whether the message
     * is a normal conversation or a warning
     *
     * @param text text within the gopher's dialog box
     */
    private void renderBackground(String text) {
        Pattern warningPattern = Pattern.compile("Please try again",
                Pattern.CASE_INSENSITIVE);
        if (warningPattern.matcher(text).find()) {
            dialog.getStyleClass().add("warning-color");
        } else {
            dialog.getStyleClass().add("reply-color");
        }
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        Circle clip = new Circle(32, 32, 32);
        db.displayPicture.setClip(clip);
        return db;
    }

    public static DialogBox getGopherDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.renderBackground(text);
        return db;
    }
}

