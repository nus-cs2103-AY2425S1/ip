package alexer.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MessageBox extends HBox {
    private final Label text;
    private final ImageView picture;

    public MessageBox(String message, Image displayPicture) {
        text = new Label(message);
        picture = new ImageView(displayPicture);

        text.setWrapText(true);
        picture.setFitWidth(100.0);
        picture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);

        getChildren().addAll(text, picture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> nodes = FXCollections.observableArrayList(getChildren());
        FXCollections.reverse(nodes);
        getChildren().setAll(nodes);
    }

    public static MessageBox createUserDialog(String s, Image i) {
        return new MessageBox(s, i);
    }

    public static MessageBox createBotDialog(String s, Image i) {
        MessageBox msgBox = new MessageBox(s, i);
        msgBox.flip();
        return msgBox;
    }
}
