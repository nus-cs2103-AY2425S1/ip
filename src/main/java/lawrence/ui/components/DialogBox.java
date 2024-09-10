package lawrence.ui.components;

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
import lawrence.command.CommandType;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor.
     *
     * @param text the text to be displayed in the dialog box
     * @param img the image to be displayed in the dialog box
     */
    private DialogBox(String text, Image img) {
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
     * Method to create a dialog box originating from the user.
     *
     * @param text the text to be displayed in the dialog box
     * @param img the image associated with the user
     * @return a dialog box for the user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Method to create a dialog box originating from the bot.
     *
     * @param text the text to be displayed in the dialog box
     * @param img the image associated with the bot
     * @return a dialog box for the bot
     */
    public static DialogBox getBotDialog(String text, Image img, CommandType type) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(type);
        return db;
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
     * Sets the style of the dialog box depending on the type of command issued.
     *
     * @param type the type of command issued to the bot
     */
    private void changeDialogStyle(CommandType type) {
        if (type == null) {
            // do nothing
            return;
        }
        switch(type) {
        case ADD_EVENT:
            // Fallthrough
        case ADD_DEADLINE:
            // Fallthrough
        case ADD_TODO:
            dialog.getStyleClass().add("add-label");
            break;
        case DELETE:
            dialog.getStyleClass().add("delete-label");
            break;
        case MARK_COMPLETE:
            // Fallthrough
        case MARK_INCOMPLETE:
            dialog.getStyleClass().add("marked-label");
            break;
        default:
            // Do nothing
        }
    }
}
