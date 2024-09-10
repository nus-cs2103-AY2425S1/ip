package ipman.ui.javafx.components;

import ipman.ui.javafx.Message;
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

import java.io.IOException;
import java.util.Collections;

public class MessageBox extends HBox {
    @FXML
    private ImageView displayPicture;
    @FXML
    private Label message;

    private MessageBox(String message, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MessageBox.class.getResource("/components/MessageBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.message.setText(message);
        this.displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static MessageBox createMessage(Message message) {
        return switch (message.author()) {
            case USER -> createUserBox(message.message());
            case SYSTEM -> createSystemBox(message.message());
        };
    }

    public static MessageBox createUserBox(String message) {
        Image image = new Image(MessageBox.class.getResourceAsStream("/images/messageUserAvatar.png"));
        return new MessageBox(message, image);
    }

    public static MessageBox createSystemBox(String message) {
        Image image = new Image(MessageBox.class.getResourceAsStream("/images/messageSystemAvatar.png"));
        MessageBox box = new MessageBox(message, image);
        box.flip();
        return box;
    }
}
