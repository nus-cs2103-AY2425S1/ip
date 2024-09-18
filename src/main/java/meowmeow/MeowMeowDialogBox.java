package meowmeow;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;

public class MeowMeowDialogBox extends DialogBox {

    public MeowMeowDialogBox(String text, Image img) {
        super(text, img, "/view/MeowMeowDialogBox.fxml");
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

    public static DialogBox getDialog(String text, Image img) {
        var db = new MeowMeowDialogBox(text, img);
        db.flip();
        return db;
    }
}
