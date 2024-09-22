package hoodini;
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
 * Handles dialog box of the application
 */
public class HoodiniDialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    private HoodiniDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HoodiniMainWindow.class.getResource("/view/HoodiniDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);


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
     * Returns a dialog box with the user input and the user's image.
     * @param text The input of the user
     * @param img The image of the user
     * @return A dialog box with the user input and the user's image.
     */
    public static HoodiniDialogBox getUserDialog(String text, Image img) {
        return new HoodiniDialogBox(text, img);
    }

    /**
     * Returns a dialog box with the chatbot's response and the chatbot's image.
     * @param text The response of the chatbot
     * @param img The image of the chatbot
     * @return A dialog box with the chatbot's response and the chatbot's image.
     */
    public static HoodiniDialogBox getHoodiniDialog(String text, Image img) {
        var db = new HoodiniDialogBox(text, img);
        db.flip();
        return db;
    }
}
