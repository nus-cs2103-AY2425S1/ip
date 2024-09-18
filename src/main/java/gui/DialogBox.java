package gui;

import java.io.IOException;
import java.util.Collections;

import gui.MainWindow;
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

        dialog.setText(text);
        displayPicture.setImage(img);
    }
    private void changeDialogStyle(String commandType) {
        dialog.getStyleClass().clear();
        dialog.getStyleClass().add("dialog-box");
        switch (commandType.toLowerCase()) {
        case "event", "todo", "deadline":
            dialog.getStyleClass().add("yellow-dialog");
            break;
        case "mark", "find":
            dialog.getStyleClass().add("green-dialog");
            break;
        case "unmark", "delete":
            dialog.getStyleClass().add("red-dialog");
            break;
        case "user":
            dialog.getStyleClass().add("person-dialog");
            break;
        default:
            dialog.getStyleClass().add("bot-dialog");
            break;
        }
    }


    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.changeDialogStyle("user");
        return db;
    }

    public static DialogBox getPixyDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
}
