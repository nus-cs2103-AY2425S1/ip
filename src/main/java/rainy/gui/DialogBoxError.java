package rainy.gui;

import java.io.IOException;

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
 * Represents the DialogBox which appears when the chatbot responds to a user response which triggers and exception.
 */
public class DialogBoxError extends HBox {
    @FXML
    private Label text;

    @FXML
    private ImageView displayPicture;

    private DialogBoxError(String s, Image i) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/view/DialogBoxError.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        text.setText(s);
        displayPicture.setImage(i);
        displayPicture.setClip(new Circle(37.5, 37.5, 37.5));
        displayPicture.setFitHeight(75);
        displayPicture.setFitWidth(75);
    }

    /**
     * FLips the dialog box and it contents to the other side for the chatbot's response.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
        text.getStyleClass().add("reply-label");
    }

    public static DialogBoxError getUserDialog(String s, Image i) {
        return new DialogBoxError(s, i);
    }

    /**
     * Plays the appropriate chatbot response.
     * @param s  System-generated response.
     * @param i  Image of the chatbot.
     * @return   Returns the entire HBox which contains the message and image.
     */
    public static DialogBoxError getRainyDialog(String s, Image i) {
        var db = new DialogBoxError(s, i);
        db.flip();
        return db;
    }
}
