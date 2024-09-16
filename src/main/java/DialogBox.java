import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

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
        displayPicture.setFitWidth(50);
        displayPicture.setFitHeight(50);

        // Create a circular clip for profile image
        Circle clip = new Circle(25, 25, 25);

        // Apply the clip to the ImageView
        displayPicture.setClip(clip);

        text.setStyle("-fx-padding: 0 10 0 10;");

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
    public static DialogBox getUserDialog(Image i, String... strs) {
        String s = "";
        for (String str : strs) {
            s += str;
        }
        DialogBox userDialog = new DialogBox(s, i);
        userDialog.setStyle(
                "-fx-background-color: #cce7ff;"
                + "-fx-padding: 10;"
                + "-fx-border-insets: 5px;"
                + "-fx-background-insets: 5px;"
                + "-fx-border-radius: 15;"
                + "-fx-background-radius: 15;"
                + "-fx-border-color: lightblue;"
                + "-fx-border-width: 1;");
        return userDialog;
    }

    /** Dialog box of chatbot */
    public static DialogBox getChatbotDialog(Image i, String... strs) {
        String s = "";
        for (String str : strs) {
            s += str;
        }

        var db = new DialogBox(s, i);
        db.setStyle(
                "-fx-background-color: #e0e0e0;"
                + "-fx-padding: 10;"
                + "-fx-border-insets: 5px;"
                + "-fx-background-insets: 5px;"
                + "-fx-border-radius: 15;"
                + "-fx-background-radius: 15;"
                + "-fx-border-color: lightgray;"
                + "-fx-border-width: 1;");
        db.flip();
        return db;
    }
}
