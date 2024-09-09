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
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert dialog != null : "Dialog label should be initialized by FXML loader.";
        assert displayPicture != null : "Display picture ImageView should be initialized by FXML loader.";

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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getHandsomeDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        if (!commandType.isEmpty()) {
            db.changeDialogStyle(commandType);
        }
        return db;
    }

    private void changeDialogStyle(String commandType) {
        final String deadlineCommand = "DeadlineCommand";
        final String markCommand = "MarkCommand";
        final String eventCommand = "EventCommand";
        final String todoCommand = "TodoCommand";
        final String unmarkCommand = "UnmarkCommand";
        final String deleteCommand = "DeleteCommand";
        switch(commandType) {
        case deadlineCommand:
        case eventCommand:
        case todoCommand:
            dialog.getStyleClass().add("add-label");
            break;
        case markCommand:
        case unmarkCommand:
            dialog.getStyleClass().add("marked-label");
            break;
        case deleteCommand:
            dialog.getStyleClass().add("delete-label");
            break;
        default:
            // Do nothing
        }
    }
}
