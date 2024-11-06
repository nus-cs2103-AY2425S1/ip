package joe.gui;

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

    /**
     * Constructor for DialogBox.
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
     * Changes the label color based on the command.
     * @param userInput the command to determine the label color
     */
    private void changeLabelColor(String userInput) {
        String command = userInput.split(" ")[0];
        removeCurrentDialogBoxStyle();
        switch (command) {
        case "bye":
            dialog.getStyleClass().add("bye-label");
            break;
        case "delete":
            dialog.getStyleClass().add("delete-label");
            break;
        case "find":
            dialog.getStyleClass().add("find-label");
            break;
        case "help":
            dialog.getStyleClass().add("help-label");
            break;
        case "list":
            dialog.getStyleClass().add("list-label");
            break;
        case "mark":
            dialog.getStyleClass().add("marked-label");
            break;
        case "query":
            dialog.getStyleClass().add("query-label");
            break;
        case "save":
            dialog.getStyleClass().add("save-label");
            break;
        case "unmark":
            dialog.getStyleClass().add("unmark-label");
            break;
        case "todo":
        case "deadline":
        case "event":
            dialog.getStyleClass().add("add-label");
            break;
        default:
            dialog.getStyleClass().add("label");
            break;
        }
    }

    /**
     * Removes the current dialog box style.
     */
    private void removeCurrentDialogBoxStyle() {
        dialog.getStyleClass().removeAll("label", "add-label", "bye-label", "delete-label", "find-label",
                "help-label", "list-label", "marked-label", "query-label", "save-label", "unmark-label");
    }

    /**
     * Returns a DialogBox object representing the user's dialog.
     * @param text the text to be displayed in the dialog box
     * @param img the image to be displayed in the dialog box
     * @return a DialogBox object representing the user's command
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a DialogBox object representing Joe's dialog.
     * It changes the label color based on the command.
     * @param text the text to be displayed in the dialog box
     * @param img the image to be displayed in the dialog box
     * @return a DialogBox object representing Joe's response
     */
    public static DialogBox getJoeDialog(String input, String text, Image img) {
        var db = new DialogBox(text, img);
        db.changeLabelColor(input);
        db.flip();
        return db;
    }
}
