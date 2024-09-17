package echobot.ui;

import echobot.command.Command.CommandType;
import echobot.command.CommandResponse;
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

import java.io.IOException;
import java.util.Collections;

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

    private void changeDialogStyle(CommandType commandType) {
        switch (commandType) {
        case ADD:
            dialog.getStyleClass().add("add-label");
            break;
        case MARK:
            dialog.getStyleClass().add("marked-label");
            break;
        case DELETE:
            dialog.getStyleClass().add("delete-label");
            break;
        case FIND:
            dialog.getStyleClass().add("find-label");
            break;
        default:
        }
    }

    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text, new Image(DialogBox.class.getResourceAsStream("/images/user.png")));
    }

    public static DialogBox getEchobotDialog(CommandResponse commandResponse) {
        DialogBox db = new DialogBox(commandResponse.getResponse(), new Image(DialogBox.class.getResourceAsStream("/images/echobot.png")));
        db.flip();
        db.changeDialogStyle(commandResponse.getCommandType());
        return db;
    }

    public static DialogBox getEchobotErrorDialog(String errorMsg) {
        DialogBox db = new DialogBox(errorMsg, new Image(DialogBox.class.getResourceAsStream("/images/error.png")));
        db.flip();
        db.dialog.getStyleClass().add("error-label");
        return db;
    }
}
