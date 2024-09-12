package controllers;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Controller class for the DialogBox GUI.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox element.
     *
     * @param text Text string within the element.
     * @param img Image within the element.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class
                    .getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(text);

        // Approach was adapted from https://stackoverflow.com/questions/42116313/how-to-set-an-image-in-a-circle
        Circle circle = new Circle(49, 49, 47);
        circle.setEffect(new DropShadow(+5d, +0d, +3d, Color.SLATEGREY));
        displayPicture.setImage(img);
        displayPicture.setClip(circle);
    }

    /**
     * Flips the contents within the DialogBox element.
     * Such that the ImageView is on the left and text is on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Constructs a user dialog box.
     *
     * @param text Text string within the dialog box.
     * @param img Image within the dialog box.
     * @return User dialog box element.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }


    /**
     * Constructs a Brock dialog box.
     *
     * @param text Text string within the dialog box.
     * @param img Image within the dialog box.
     * @return Brock dialog box element.
     */
    public static DialogBox getBrockDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
