package bob.gui;

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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

/**
 * Represents a dialog box containing an avatar and a speech bubble
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

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
        displayPicture.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        dialog.getStyleClass().add("reply-label");
    }

    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    public static DialogBox getBobDialog(String s, Image i, String commandType) {
        var db = new DialogBox(s, i);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }

    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "TodoCommand":
        case "DeadlineCommand":
        case "EventCommand":
            dialog.getStyleClass().add("add-label");
            break;
        case "MarkCommand":
            dialog.getStyleClass().add("marked-label");
            break;
        case "DeleteCommand":
            dialog.getStyleClass().add("delete-label");
            break;
        case "Error":
            dialog.getStyleClass().add("error-label");
            break;
        default:
            // Do nothing
        }
    }
}