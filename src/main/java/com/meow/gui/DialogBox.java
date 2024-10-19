package com.meow.gui;
import java.io.IOException;
import java.util.Collections;
import javafx.scene.shape.Circle;


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
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String styleClass) {
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
        Circle clip = new Circle(50, 50, 40); // Adjust the radius to fit your needs
        displayPicture.setClip(clip);
        displayPicture.setFitWidth(100); // Adjust to your desired width
        displayPicture.setFitHeight(100);

        dialog.getStyleClass().add(styleClass);

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_RIGHT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img, "sent-message");
        db.flip();
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, "received-message");
        return db;
    }
}
