package flychat.javafx;
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

public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    public DialogBox(String text, Image img) {
        assert text != null : "Text parameter is null";
        assert img != null : "Image parameter is null";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert dialog != null : "Dialog label is not initialized";
        assert displayPicture != null : "Display picture is not initialized";

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private void flip() {
        setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        assert !tmp.isEmpty() : "Children list is empty";
        FXCollections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String s, Image i) {
        return  new DialogBox(s, i);
    }

    public static DialogBox getFlyChatDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}
