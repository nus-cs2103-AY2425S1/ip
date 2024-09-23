package mittens.ui.fx;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

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
import mittens.MittensException;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final Image USER_AVATAR = new Image(DialogBox.class.getResourceAsStream("/images/userAvatar.png"));
    private static final Image MITTENS_AVATAR = new Image(DialogBox.class.getResourceAsStream("/images/mittensAvatar.jpg"));
    private static final Image ERROR_AVATAR = new Image(DialogBox.class.getResourceAsStream("/images/errorAvatar.jpg"));

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
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
    }

    public static DialogBox ofUserMessage(String text) {
        return new DialogBox(text, USER_AVATAR);
    }

    public static DialogBox ofRegularMessage(List<String> text) {
        var db = new DialogBox(String.join("\n", text), MITTENS_AVATAR);
        db.flip();
        return db;
    }

    public static DialogBox ofMittensMessage(List<String> text) {
        return new DialogBox(String.join("\n", text), MITTENS_AVATAR);
    }

    public static DialogBox ofErrorMessage(MittensException e) {
        String message = "%s\n\nError: %s\n%s".formatted(e.getMittensMessage(), e.getMessage(), e.getHelpMessage());
        var db = new DialogBox(message, ERROR_AVATAR);
        db.flip();
        return db;
    }
}
