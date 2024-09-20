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
 * Controls the graphical components.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        assert text != null : "Text must not be null";
        assert img != null : "Image must not be null";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            // To link the components to this object instance
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

    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "TodoCommand":
            dialog.getStyleClass().add("add-label");
            break;
        case "MarkCommand":
            dialog.getStyleClass().add("marked-label");
            break;
        case "DeleteCommand":
            dialog.getStyleClass().add("delete-label");
            break;
        default:
            // Do nothing
        }
    }

    public static DialogBox getUserDialog(String text, Image img) {
        assert text != null : "Text must not be null";
        assert img != null : "Image must not be null";

        return new DialogBox(text, img);
    }

    public static DialogBox getAstorDialog(String text, Image img, String commandType) {
        assert text != null : "Text must not be null";
        assert img != null : "Image must not be null";

        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
}
