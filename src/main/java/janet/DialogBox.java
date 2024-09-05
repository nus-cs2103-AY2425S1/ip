package janet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    DialogBox(String text, Image img) {
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
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a DialogBox, containing a string of text and an image,
     * with a PAPAYAWHIP color background
     *
     * @param s A string of text to display inside the DialogBox
     * @param i An image to be rendered inside the DialogBox
     * @return A DialogBox object
     */
    public static DialogBox getUserDialog(String s, Image i) {
        // returns a new dialogBox with the String message and the image of the user
        DialogBox user = new DialogBox(s, i);
        user.setColor(Color.PAPAYAWHIP);
        return user;
    }

    /**
     * Returns a flipped DialogBox, containing a string of text and an image,
     * with a ALICEBLUE color background
     *
     * @param s A string of text to display inside the DialogBox
     * @param i An image to be rendered inside the DialogBox
     * @return A DialogBox object
     */
    public static DialogBox getJanetDialog(String s, Image i) {
        // returns a new FLIPPED dialogBox with the String message and the image of Duke
        var db = new DialogBox(s, i);
        db.flip();
        db.setColor(Color.ALICEBLUE);
        return db;
    }

    /**
     * Sets the background color of the dialogBox
     *
     * @param color The background color chosen for the dialogBox
     */
    public void setColor(Color color) {
        BackgroundFill bFill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(bFill);
        this.setBackground(background);
    }
}