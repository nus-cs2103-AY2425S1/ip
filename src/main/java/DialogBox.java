import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.Node;

import java.io.IOException;

public class DialogBox extends HBox{
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String s, Image i) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException err) {
            err.printStackTrace();
        }

        dialog.setText(s);
        displayPicture.setImage(i);
    }

    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    public static DialogBox getTalkyDialog(String s, Image i) {
        DialogBox talkyDialog = new DialogBox(s, i);
        talkyDialog.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(talkyDialog.getChildren());
        FXCollections.reverse(tmp);
        talkyDialog.getChildren().setAll(tmp);
        talkyDialog.dialog.getStyleClass().add("reply-label");
        return talkyDialog;
    }
}
