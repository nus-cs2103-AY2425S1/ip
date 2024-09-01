package jackson.graphics;

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

        Circle clip = new Circle(75/2, 75/2, 75/2);

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setFitHeight(75);
        displayPicture.setFitWidth(75);
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

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.changeDialogStyle("user");
        return db;
    }

    public static DialogBox getJacksonDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }

    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "modify":
            dialog.getStyleClass().add("modify-label");
            break;
        case "error":
            dialog.getStyleClass().add("error-label");
            break;
        case "intro", "exit":
            dialog.getStyleClass().add("intro-exit-label");
            break;
        case "task":
            dialog.getStyleClass().add("task-label");
            break;
        case "secret":
            dialog.getStyleClass().add("secret-label");
            break;
        case "normal":
            dialog.getStyleClass().add("jackson-label");
            break;
        default:
            // Do nothing
        }
    }
}
