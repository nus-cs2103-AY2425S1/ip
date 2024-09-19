package tayoo;

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
 * A class that encapsulates the dialog boxes and its properties within the GUI
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new dialog box with text and images, uses the DialogBox.fxml file to specify the properties of the
     * dialog box.
     *
     * @param text The text that is to be displayed within the dialog box.
     * @param img The image that is to be displayed next to the dialog box
     */
    public DialogBox(String text, Image img) {
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

        double radius = Math.min(displayPicture.getFitWidth(), displayPicture.getFitHeight() / 2);
        Circle clip = new Circle(radius, radius, radius);
        displayPicture.setClip(clip);
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
     * Receives the user's input as a string and creates a new dialog box with the text.
     *
     * @param text The text that is to be displayed in the dialog box
     * @param img The image accompanying the text in the dialog box
     * @return a dialog box instance
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "AddTaskCommand":
            dialog.getStyleClass().add("add-label");
            break;
        case "MarkTaskCommand":
            dialog.getStyleClass().add("marked-label");
            break;
        case "DeleteTaskCommand":
        case "DeleteAllCommand":
            dialog.getStyleClass().add("delete-label");
            break;
        default:
            dialog.getStyleClass().add("reply-label");
        }
    }

    /**
     * Creates a new, flipped dialog box with Tayoo's response inside.
     * @param text text returned by Tayoo chatbot
     * @param img The image accompanying the Tayoo chatbot reply
     * @return new dialogbox instance containing Tayoo chatbot's response
     */
    public static DialogBox getTayooDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        if (commandType == null) {
            commandType = "";
        }
        db.changeDialogStyle(commandType);
        return db;
    }
}
