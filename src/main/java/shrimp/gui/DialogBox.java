package shrimp.gui;

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
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String messageText, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(messageText);
        displayPicture.setImage(image);

        Circle clip = new Circle(30, 30, 30);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> childNode = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(childNode);
        getChildren().setAll(childNode);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    public static DialogBox getUserDialog(String messageText, Image userImage) {
        var db = new DialogBox(messageText, userImage);
        db.dialog.getStyleClass().add("label");
        return db;
    }

    public static DialogBox getShrimpDialog(String messageText, Image shrimpImage) {
        var db = new DialogBox(messageText, shrimpImage);
        db.flip();
        db.dialog.getStyleClass().add("reply-label");
        return db;
    }

    public static DialogBox getErrorDialog(String messageText, Image shrimpImage) {
        var db = new DialogBox(messageText, shrimpImage);
        db.flip();
        db.dialog.getStyleClass().add("error-label");
        return db;
    }
}
