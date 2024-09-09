package sigma.gui;

import java.io.IOException;

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
 * Represents a dialog box consisting of an ImageView to represent the speaker's face and a label containing text from
 * the speaker.
 */
public class DialogBox extends HBox {
    public static final String LOCATION = "/view/DialogBox.fxml";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(LOCATION));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        clipImageViewToCircle(displayPicture);
    }

    /**
     * Returns a DialogBox with the specified text and image.
     *
     * @param s The text to be displayed in the dialog box.
     * @param i The image to be displayed in the dialog box.
     * @return A DialogBox with the specified text and image.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Returns a DialogBox with the specified text, image and command type.
     * This DialogBox will be flipped.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     * @param commandType The type of command that the dialog box is displaying.
     * @return A DialogBox with the specified text, image and command type.
     */
    public static DialogBox getSigmaDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
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

    private void clipImageViewToCircle(ImageView imageView) {
        double radius = Math.min(imageView.getFitWidth(), imageView.getFitHeight()) / 2;
        assert radius > 0 : "Radius must be greater than 0";
        Circle clip = new Circle(radius, radius, radius);
        imageView.setClip(clip);
    }

    private void changeDialogStyle(String commandType) {
        assert commandType != null : "Command type cannot be null";
        switch (commandType) {
        case "TodoCommand":
        case "DeadlineCommand":
        case "EventCommand":
            dialog.getStyleClass().add("add-label");
            break;
        case "MarkCommand":
        case "UnmarkCommand":
            dialog.getStyleClass().add("marked-label");
            break;
        case "DeleteCommand":
            dialog.getStyleClass().add("delete-label");
            break;
        default:
            // Default style is used here.
        }
    }

}
