import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Class that represents a chat dialog box.
 * Includes a display picture and dialog content.
 */
public class DialogBox extends HBox {

    /** Dialog text */
    private Label text;

    /** Display picture of sender */
    private ImageView displayPicture;

    /**
     * Initialises a dialog box.
     *
     * @param s Dialog content
     * @param i Display picture of sender
     */
    public DialogBox(String s, Image i) {
        text = new Label(s);
        displayPicture = new ImageView(i);

        // Styling the dialog box
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);

        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /** Dialogbox of user */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /** Dialog box of chatbot */
    public static DialogBox getChatbotDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}
