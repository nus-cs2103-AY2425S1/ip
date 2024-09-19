package chatbot.views;

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

/**
 * Represents the graphical rendering of a chat message, either from the user or from the bot
 */
public class DialogBox extends HBox {
    /** Label representing the message content */
    @FXML
    private Label dialog;
    /** ImageView representing the image of the sender */
    @FXML
    private ImageView displayImage;

    /**
     * Constructs a DialogBox object
     *
     * @param text String representing the message content
     * @param img Image representing the display image of the sender
     */
    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayImage.setImage(img);
    }

    /**
     * Flips the dialog box such that ImageView is on the left and text is on the right
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a new DialogBox object if the user were the sender
     *
     * @param s String representing the message content
     * @param i Image representing the display image of the sender
     * @return A DialogBox object supposing it was sent by the user
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Creates a new DialogBox object if the bot were the sender
     *
     * @param s String representing the message content
     * @param i Image representing the display image of the sender
     * @return A DialogBox object supposing it was sent by the bot
     */
    public static DialogBox getBotDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}
