package ipman.ui.javafx.components;

import ipman.ui.javafx.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MessageBox extends GridPane  {
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

    public static MessageBox createMessage(Message message) {
        return switch (message.author()) {
            case USER -> createUserBox(message.message());
            case SYSTEM -> createSystemBox(message.message());
        };
    }

    public static MessageBox createUserBox(String message) {
        Image image = new Image(MessageBox.class.getResourceAsStream("/images/messageUserAvatar.png"));
        MessageBox box = new MessageBox(message, image);
        box.message.setAlignment(Pos.CENTER_RIGHT);
        return box;
    }

    public static MessageBox createSystemBox(String message) {
        Image image = new Image(MessageBox.class.getResourceAsStream("/images/messageSystemAvatar.png"));
        MessageBox box = new MessageBox(message, image);
        GridPane.setColumnIndex(box.message, 1);
        GridPane.setColumnIndex(box.displayPicture, 0);
        return box;
    }
}
