package jeriel.ui;

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
 * Custom control that represents the dialog boxes for user and bot.
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    public DialogBox(String text, Image img) {
        this.text = new Label(text);
        this.displayPicture = new ImageView(img);

        // Customize the image view (optional)
        displayPicture.setFitWidth(50);
        displayPicture.setFitHeight(50);

        // Make the image circular (optional)
        Circle clip = new Circle(25, 25, 25);
        displayPicture.setClip(clip);

        this.getChildren().addAll(displayPicture, this.text);
    }

    /**
     * Creates a dialog box for the user message (aligned to the right).
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setAlignment(Pos.CENTER_RIGHT);  // Aligns to the right
        ObservableList<Node> nodes = FXCollections.observableArrayList(db.getChildren());
        FXCollections.reverse(nodes);  // Flip the order (text on the right, image on the left)
        db.getChildren().setAll(nodes);
        return db;
    }

    /**
     * Creates a dialog box for the bot message (aligned to the left).
     */
    public static DialogBox getBotDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setAlignment(Pos.CENTER_LEFT);  // Aligns to the left
        return db;
    }
}
