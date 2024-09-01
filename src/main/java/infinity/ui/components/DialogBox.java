package infinity.ui.components;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

/**
 * DialogBox component for each Dialogue in the bot.
 */
public class DialogBox extends HBox {
    @FXML
    private Label text;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor of DialogBox.
     *
     * @param s Text for display.
     * @param i Image of user.
     * @param reverse To reverse the display order of Text and Image.
     */
    private DialogBox(String s, Image i, boolean reverse) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GraphicsMainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        text.setText(s);
        displayPicture.setImage(i);

        //Styling the dialog box
        if (reverse) {
            this.flip();
            this.text.setAlignment(Pos.BOTTOM_LEFT);
            this.text.setTextAlignment(TextAlignment.LEFT);
        } else {
            this.text.setAlignment(Pos.BOTTOM_RIGHT);
            this.text.setTextAlignment(TextAlignment.RIGHT);
        }
    }

    public static DialogBox createUserDialog(String s, Image i) {
        return new DialogBox(s, i, false);
    }

    public static DialogBox createBotDialog(String s, Image i) {
        return new DialogBox(s, i, true);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.BOTTOM_LEFT);
    }
}
