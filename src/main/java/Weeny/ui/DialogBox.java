package weeny.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.IOException;

/**
 * A custom dialog box for displaying text and an image.
 */
public class DialogBox extends HBox {
    @FXML
    private Label text;
    @FXML
    private ImageView displayPicture;
    @FXML
    private HBox textBubble;

    private static final String USER_STYLE = "-fx-background-radius: 15;" +
            " -fx-background-color: #ffffff;" +
            " -fx-border-color: #f5f5f5;" +
            " -fx-border-width: 2;" +
            " -fx-border-radius: 15;";
    private static final String WEENY_STYLE = "-fx-background-radius: 15;" +
            " -fx-background-color: #ffb6c1;" +
            " -fx-border-color: #f5f5f5;" +
            " -fx-border-width: 2;" +
            " -fx-border-radius: 15;";

    /**
     * Creates a new DialogBox with the given text and image.
     *
     * @param s The text to display.
     * @param i The image to display.
     */
    public DialogBox(String s, Image i) {
        loadFXML();
        text.setText(s);
        displayPicture.setImage(i);
        clipDisplayPicture();
    }

    /**
     * Loads the FXML file for this DialogBox.
     */
    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Flips the dialog box to align it to the top-left.
     */
    @FXML
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a DialogBox for user messages.
     *
     * @param s The text to display.
     * @param i The image to display.
     * @return A DialogBox styled for user messages.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        DialogBox userDialog = new DialogBox(s, i);
        userDialog.textBubble.setStyle(USER_STYLE);
        return userDialog;
    }

    /**
     * Creates a DialogBox for system messages.
     *
     * @param s The text to display.
     * @param i The image to display.
     * @return A DialogBox styled for system messages.
     */
    public static DialogBox getWeenyDialog(String s, Image i) {
        DialogBox db = new DialogBox(s, i);
        db.flip();
        db.textBubble.setStyle(WEENY_STYLE);
        return db;
    }

    /**
     * Clips the display picture to a circular shape.
     */
    private void clipDisplayPicture() {
        if (displayPicture != null) {
            double radius = displayPicture.getFitHeight() / 2;
            Circle clip = new Circle(radius, radius, radius);
            displayPicture.setClip(clip);
        }
    }
}
