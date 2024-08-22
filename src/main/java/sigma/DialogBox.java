package sigma;

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
import sigma.command.CommandType;

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
        setDisplayPicture(img);
    }

    private void setDisplayPicture(Image img) {
        displayPicture.setImage(img);
        double radius = 25.0;
        Circle clip = new Circle(radius, radius, radius);
        displayPicture.setClip(clip);
        displayPicture.setFitWidth(radius * 2);
        displayPicture.setFitHeight(radius * 2);
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

    private void changeDialogStyle(CommandType commandType) {
        switch(commandType) {
        case TODO:
            dialog.getStyleClass().add("todo-label");
            break;
        case MARK:
            dialog.getStyleClass().add("marked-label");
            break;
        case DELETE:
            dialog.getStyleClass().add("delete-label");
            break;
        default:
            // Do nothing
        }
    }

    public static DialogBox getSigmaDialog(String text, Image img, CommandType commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
}