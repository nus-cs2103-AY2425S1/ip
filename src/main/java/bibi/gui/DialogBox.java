package bibi.gui;

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

/** Represents a horizontal bibi.gui.DialogBox with an ImageView for icons and Label for text*/
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a new bibi.gui.DialogBox.
     *
     * @param s Text to represent the message.
     * @param i Image to represent the icon of person sending the message.
     */
    private DialogBox(String s, Image i) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(s);
        displayPicture.setImage(i);
    }

    public static DialogBox getUserDialogBox(String s, Image i) {
        return new DialogBox(s, i);
    }

    public static DialogBox getBotDialogBox(String s, Image i) {
        DialogBox temp = new DialogBox(s, i);
        temp.flip();
        return temp;
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> nodes = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(nodes);
        this.getChildren().setAll(nodes);
    }
}
