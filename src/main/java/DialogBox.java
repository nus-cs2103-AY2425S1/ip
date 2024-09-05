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



/**
 * A simple dialog box for ChatterBox GUI.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Initialises a DialogBox with a picture and the relevant text.
     * @param s String containing text to be placed in the dialog box.
     * @param i Image for the profile picture.
     */
    public DialogBox(String s, Image i) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GraphicUi.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(s);
        displayPicture.setImage(i);
    }

    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    public static DialogBox getChatterboxDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
