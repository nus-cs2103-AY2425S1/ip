package kita;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class ChatBubble extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private Circle circleImage;

    private ChatBubble(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ChatBubble.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long lines = Arrays.stream(text.split("\n")).count();
        long heightToSet = 150 + (lines > 4 ? lines * 15 : 0);
        setMinHeight(heightToSet);
        setHeight(heightToSet);
        setMaxHeight(heightToSet);
        setAlignment(Pos.CENTER_RIGHT);
        dialog.setMaxHeight(50 + (lines * 20));
        dialog.setText(text);
        circleImage.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
        dialog.setAlignment(Pos.CENTER_LEFT);
    }

    public static ChatBubble getUserDialog(String text, Image img) {
        var db = new ChatBubble(text, img);
        db.flip();
        return new ChatBubble(text, img);
    }

    public static ChatBubble getDukeDialog(String text, Image img) {
        var db = new ChatBubble(text, img);
        db.flip();
        return db;
    }
}
