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
import lawrence.app.Response;
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
     * @param image the image to be displayed in the dialog box
     */
    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(image);
    }

    /**
     * Returns a dialog box originating from the user.
     *
     * @param text the text to be displayed in the dialog box
     * @param image the image associated with the user
     * @return a dialog box for the user
     */
    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    /**
     * Returns a dialog box originating from the bot.
     *
     * @param response a {@link Response} object from the bot
     * @param image the image associated with the bot
     * @return a dialog box for the bot
     */
    public static DialogBox getBotDialog(Response response, Image image) {
        var db = new DialogBox(response.message(), image);
        db.flip();
        db.changeDialogStyle(response.commandType());
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
        case INVALID:
            // Fallthrough
        default:
            // Do nothing
        }
    }
}
