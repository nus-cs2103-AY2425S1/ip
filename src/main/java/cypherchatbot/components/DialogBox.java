package cypherchatbot.components;

import java.io.IOException;
import java.util.Collections;

import cypherchatbot.controller.MainWindow;
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
        Circle clip = new Circle(50, 50, 50); // (centerX, centerY, radius)
        this.displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getCypherDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        for (var node : db.getChildren()) {
            if (node instanceof Label chatBox) {
                chatBox.setStyle("-fx-background-color: #ADD8E6; -fx-border-color: #cc79a7 #0072b2 #d55e00 #009e73; "
                        + "-fx-border-width: 2px; -fx-background-radius: 1em 1em 1em 0; "
                            + "-fx-border-radius: 1em 1em 1em 0;");
            }
        }
        return db;
    }

}

