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

    private static final double IMAGE_SIZE = 50.0;
    private static final double CIRCLE_RADIUS = 25.0;

    private final Label text;
    private final ImageView displayPicture;

    /**
     * Constructor for the dialog box, initializes the text and image.
     * @param text The text to display.
     * @param img The image to display.
     */
    private DialogBox(String text, Image img) {
        this.text = new Label(text);
        this.displayPicture = createImageView(img);
        this.getChildren().addAll(displayPicture, this.text);
    }

    /**
     * Helper method to create an ImageView with predefined size and circular clip.
     * @param img The image to be displayed.
     * @return Configured ImageView.
     */
    private ImageView createImageView(Image img) {
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(IMAGE_SIZE);
        imageView.setFitHeight(IMAGE_SIZE);
        Circle clip = new Circle(CIRCLE_RADIUS, CIRCLE_RADIUS, CIRCLE_RADIUS);
        imageView.setClip(clip);
        return imageView;
    }

    /**
     * Creates a dialog box for the user message (aligned to the right).
     * @param text The user message text.
     * @param img The user's display picture.
     * @return Configured DialogBox aligned to the right.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.setAlignment(Pos.CENTER_RIGHT); // Align text to the right
        dialogBox.flip(); // Flip text and image position
        return dialogBox;
    }

    /**
     * Creates a dialog box for the bot message (aligned to the left).
     * @param text The bot message text.
     * @param img The bot's display picture.
     * @return Configured DialogBox aligned to the left.
     */
    public static DialogBox getBotDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.setAlignment(Pos.CENTER_LEFT); // Align text to the left
        return dialogBox;
    }

    /**
     * Flips the positions of text and image for the user dialog box.
     */
    private void flip() {
        ObservableList<Node> nodes = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(nodes); // Flip the order (text on the right, image on the left)
        this.getChildren().setAll(nodes);
    }
}
