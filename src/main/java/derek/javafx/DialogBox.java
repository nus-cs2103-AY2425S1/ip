package derek.javafx;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;


/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Label speaker;

    private DialogBox(String speakerName, String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        speaker.setText(speakerName);
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flipDialogBox() {
        setAlignment(Pos.TOP_LEFT);
    }

    private void flipInternalComponents() {
        HBox innerHBox = (HBox) this.getChildren().get(0);

        ObservableList<Node> children = innerHBox.getChildren();

        if (children.size() == 2) {
            Node image = children.get(0);
            VBox textBox = (VBox) children.get(1);

            innerHBox.getChildren().clear();
            innerHBox.getChildren().addAll(textBox, image);
            textBox.setAlignment(Pos.CENTER_RIGHT);
            dialog.setTextAlignment(TextAlignment.RIGHT);
        }
    }

    public static DialogBox getUserDialog(String user, String text, Image img) {
        var db = new DialogBox(user, text, img);
        db.flipInternalComponents();
        return db;
    }

    public static DialogBox getDerekDialog(String text, Image img) {
        var db = new DialogBox("Derek", text, img);
        db.flipDialogBox();
        return db;
    }


}
