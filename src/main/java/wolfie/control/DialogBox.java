package wolfie.control;

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
import wolfie.MainWindow;

/**
 * A  dialog box consisting of an ImageView to represent the speaker's face
 * and a Label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, boolean isError) {
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
        displayPicture.setFitWidth(70); // Resize image width
        displayPicture.setFitHeight(70); // Resize image height
        displayPicture.setClip(new Circle(35, 35, 35)); // Rounded profile picture

        if (isError) {
            dialog.setStyle("-fx-background-color: red; -fx-padding: 10; -fx-border-radius: 10; "
                    + "-fx-background-radius: 10; -fx-text-fill: black;");
        } else {
            dialog.setStyle("-fx-background-color: #ffffff; -fx-padding: 10; -fx-border-radius: 10;"
                    + " -fx-background-radius: 10;");
        }
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }

    public static DialogBox getWolfieDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }

    public static DialogBox getErrorDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img, true);
        db.flip();
        return db;
    }
}
