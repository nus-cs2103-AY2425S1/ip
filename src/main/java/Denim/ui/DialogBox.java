package denim.ui;

import java.io.IOException;
import java.util.Collections;

import denim.commands.Command;
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
 * A class representing a Dialog Box consisting of an avatar picture and a text message.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox.
     *
     * @param text The message to appear in the DialogBox.
     * @param img The image that will appear in the DialogBox
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
        this.setAlignment(Pos.TOP_RIGHT);
    }

    private DialogBox(String text, Image img, String styleClass) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.getStyleClass().add(styleClass);
        dialog.setText(text);
        displayPicture.setImage(img);
        this.setAlignment(Pos.TOP_RIGHT);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        dialog.getStyleClass().add("reply-label");
        getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDenimDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    public static DialogBox getDenimDialog(String text, Image img, Command.CommandStatus status) {
        String styleClass;
        switch (status) {
        case COMMAND_SUCCESSFUL:
            styleClass = "success";
            break;
        case COMMAND_PARTIAL_FAILURE:
            styleClass = "partial-failure";
            break;
        case COMMAND_FAILURE:
            styleClass = "failure";
            break;
        default:
            styleClass = "";
        }
        DialogBox db = new DialogBox(text, img, styleClass);
        db.flip();
        return db;
    }
}
