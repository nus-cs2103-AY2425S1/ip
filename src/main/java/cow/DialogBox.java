package cow;

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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Represents the dialog box.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Circle displayCircle;

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
        displayCircle.setFill(new ImagePattern(img));
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
     * Creates a dialog box for the user.
     *
     * @param text The text to be displayed.
     * @param img  The image to be displayed.
     * @return The dialog box for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box for the Cow.
     *
     * @param text        The text to be displayed.
     * @param img         The image to be displayed.
     * @param commandType The type of command.
     * @return The dialog box for the Cow.
     */
    public static DialogBox getCowDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }

    /**
     * Changes the dialog style based on the command type.
     *
     * @param commandType The type of the command.
     */
    private void changeDialogStyle(String commandType) {
        if (commandType.equals("IncorrectCommand")) {
            dialog.getStyleClass().add("error-label");
        }
    }
}
