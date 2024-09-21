package alexer.ui;

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

public class MessageBox extends HBox {
    @FXML
    private Label text;

    @FXML
    private ImageView picture;

    private MessageBox(String message, Image displayPicture) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MessageBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        text.setText(message);
        picture.setImage(displayPicture);
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

    /**
     * Creates a message box originating from the user (user input).
     * @param s the string to display
     * @param i the user avatar image
     * @return a message box to be displayed
     */
    public static MessageBox createUserDialog(String s, Image i) {
        return new MessageBox(s, i);
    }

    /**
     * Creates a message box originating from the bot (bot response).
     * @param s the response text string
     * @param i the bot avatar image
     * @return a message box to be displayed
     */
    public static MessageBox createBotDialog(String s, Image i) {
        MessageBox msgBox = new MessageBox(s, i);
        msgBox.flip();
        return msgBox;
    }
}
