package Dawn.Gui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView profilePic;

    public DialogBox(String s, Image i) {
        text = new Label(s);
        profilePic = new ImageView(i);

        //Styling the dialog box
        text.setWrapText(true);
        profilePic.setFitWidth(100.0);
        profilePic.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);

        this.getChildren().addAll(text, profilePic);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a new dialog box which contains the text from the user
     *
     * @param s Input from the user
     * @param i Profile picture of the user
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Returns a new dialog box which contains the response from Dawn
     *
     * @param s Response from Dawn
     * @param i Profile picture of Dawn
     */
    public static DialogBox getDawnDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}

