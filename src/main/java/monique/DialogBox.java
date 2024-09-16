package monique;

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
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box in the user interface that displays a text message and an associated image.
 * The dialog box is created with an image and text, and it can be styled differently for the user and for Monique.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Initializes a new {@code DialogBox} with the specified text and image.
     * This constructor loads the FXML layout and sets up the dialog box with the provided content.
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
        Circle clip = new Circle(50, 50, 50);
        displayPicture.setClip(clip);
        displayPicture.setImage(img);
    }

    /**
     * Reverses the order of the children nodes in the dialog box and aligns the box to the top left.
     * This method is used to flip the dialog box when styling the dialog for Monique.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a dialog box for the user with a light blue background.
     *
     * @param s the text to be displayed in the dialog box
     * @param i the image to be displayed in the dialog box
     * @return a {@code DialogBox} instance styled for the user
     */
    public static DialogBox getUserDialog(String s, Image i) {
        DialogBox db = new DialogBox(s, i);
        return db;
    }

    /**
     * Creates a dialog box for Monique with a light green background and flips the dialog box for correct alignment.
     *
     * @param s the text to be displayed in the dialog box
     * @param i the image to be displayed in the dialog box
     * @return a {@code DialogBox} instance styled for Monique
     */
    public static DialogBox getMoniqueDialog(String s, Image i, String commandType) {
        DialogBox db = new DialogBox(s, i);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }

    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "add":
            dialog.getStyleClass().add("add-label");
            break;
        case "bye":
            dialog.getStyleClass().add("bye-label");
            break;
        case "delete":
            dialog.getStyleClass().add("delete-label");
            break;
        case "find":
            dialog.getStyleClass().add("find-label");
            break;
        case "guide":
            dialog.getStyleClass().add("guide-label");
            break;
        case "list":
            dialog.getStyleClass().add("list-label");
            break;
        case "mark":
            dialog.getStyleClass().add("mark-label");
            break;
        case "unknown":
            dialog.getStyleClass().add("unknown-label");
            break;
        case "unmark":
            dialog.getStyleClass().add("unmark-label");
            break;
        default:
            dialog.getStyleClass().add("default-label");
            break;
        }
    }
}



