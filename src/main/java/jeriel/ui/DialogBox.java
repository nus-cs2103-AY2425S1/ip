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

    private DialogBox(String text, Image img) {
        this.text = new Label(text);
        this.displayPicture = new ImageView(img);

        // Customize the image view (rounded corners)
        displayPicture.setFitWidth(40);
        displayPicture.setFitHeight(40);

        // Crop the image to a circular shape
        Circle clip = new Circle(20, 20, 20);
        displayPicture.setClip(clip);

        // Styling and adding children
        this.getChildren().addAll(displayPicture, this.text);
        this.setSpacing(10); // Spacing between image and text
    }

    /**
     * Creates a dialog box for the user message (right-aligned).
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setStyle("-fx-background-color: #D9FDD3; -fx-padding: 10px; -fx-background-radius: 10;");
        db.setAlignment(Pos.CENTER_RIGHT);
        ObservableList<Node> children = FXCollections.observableArrayList(db.getChildren());
        FXCollections.reverse(children); // Ensure user image is on the right
        db.getChildren().setAll(children);
        return db;
    }

    /**
     * Creates a dialog box for the bot message (left-aligned).
     */
    public static DialogBox getBotDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setStyle("-fx-background-color: #EFEFEF; -fx-padding: 10px; -fx-background-radius: 10;");
        db.setAlignment(Pos.CENTER_LEFT);
        return db;
    }

    /**
     * Creates a dialog box for an error message (left-aligned, in red).
     */
    public static DialogBox getErrorDialog(String errorMessage) {
        DialogBox db = new DialogBox(errorMessage, null);
        db.setStyle("-fx-background-color: #FFB6B6; -fx-text-fill: red; -fx-padding: 10px; -fx-background-radius: 10;");
        db.setAlignment(Pos.CENTER_LEFT);
        return db;
    }
}
