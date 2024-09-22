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
 * A horizontal box for user's image and text.
 */
public class DialogBox extends HBox {

    @FXML
    private Label text;
    @FXML
    private ImageView image;

    /**
     * @param s {@code String} Dialog text.
     * @param i {@code Image} Image of user/server.
     */
    public DialogBox(String s, Image i) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        text.setText(s);
        image.setImage(i);
        image.setClip(new Circle(50, 50, 45.0));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
        text.getStyleClass().add("reply-label");
    }

    public static DialogBox getClientDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    public static DialogBox getServerDialog(String s, Image i, String commandType) {
        var db = new DialogBox(s, i);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }

    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "AddCommand":
            text.getStyleClass().add("add-label");
            break;
        case "ChangeMarkCommand":
            text.getStyleClass().add("marked-label");
            break;
        case "DeleteCommand":
            text.getStyleClass().add("delete-label");
            break;
        default:
            // Do nothing
        }
    }
}

