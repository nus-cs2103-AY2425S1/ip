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
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String USER_STYLE = "-fx-background-color: lightgreen;"
            + "-fx-text-fill: black; -fx-padding: 10; -fx-background-radius: 5;";
    private static final String BOT_STYLE = "-fx-background-color: lightblue;"
            + "-fx-text-fill: black; -fx-padding: 10; -fx-background-radius: 5;";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     *
     * @param text The text to display.
     * @param img  The image to display.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
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
     * Sets the style for a user dialog.
     */
    public void setUserStyle() {
        dialog.setStyle(USER_STYLE);
        setAlignment(Pos.TOP_RIGHT);
    }

    /**
     * Sets the style for a bot dialog.
     */
    public void setBotStyle() {
        dialog.setStyle(BOT_STYLE);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    /**
     * Creates a dialog box for the user.
     *
     * @param text The text to display.
     * @param img  The user's image.
     * @return A DialogBox configured for user input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setUserStyle();
        return db;
    }

    /**
     * Creates a dialog box for the bot.
     *
     * @param text The text to display.
     * @param img  The bot's image.
     * @return A DialogBox configured for bot response.
     */
    public static DialogBox getBotDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setBotStyle();
        db.flip();
        return db;
    }
}
