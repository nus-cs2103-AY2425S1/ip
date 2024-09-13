package thanos.ui.components;

import java.io.IOException;
import java.util.Collections;

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
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box in the application, displaying a text message and an image.
 * <p>
 * The {@code DialogBox} class extends {@code HBox} and is used to create a UI component
 * that shows a message along with a corresponding image, such as a user's profile picture or a bot's avatar.
 * </p>
 */
public class DialogBox extends HBox {
    private static final String USER_STYLE = "user-dialog";
    private static final String THANOS_STYLE = "thanos-dialog";

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new {@code DialogBox} with the specified text, image, and style class.
     * <p>
     * This constructor initializes the dialog box by loading the FXML layout,
     * setting the text of the {@code Label}, configuring the {@code ImageView} with the given image,
     * and applying the specified style class to the {@code Label}. The image is clipped to a circular shape.
     * </p>
     *
     * @param message the text message to be displayed in the dialog box.
     * @param image the image to be displayed in the dialog box.
     * @param styleClass the style class to be applied to the {@code Label} for styling the dialog box.
     */
    private DialogBox(String message, Image image, String styleClass) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        dialog.setText(message);
        dialog.getStyleClass().add(styleClass);
        Circle clip = new Circle(48, 48, 48);
        displayPicture.setClip(clip);
        displayPicture.setImage(image);
    }

    /**
     * Reverses the order of the children in the dialog box and aligns it to the top left.
     * <p>
     * This method is used to flip the dialog box, typically to switch between user and bot messages.
     * </p>
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a new {@code DialogBox} for the user with the specified text and image.
     *
     * @param message the text message to be displayed in the dialog box.
     * @param image the image to be displayed in the dialog box.
     * @return a {@code DialogBox} instance configured for user messages.
     */
    public static DialogBox getUserDialog(String message, Image image) {
        return new DialogBox(message, image, USER_STYLE);
    }

    /**
     * Creates a {@code DialogBox} instance for Thanos' messages with the specified text and image.
     *
     * @param message the text message to be displayed in the dialog box.
     * @param image the image to be displayed in the dialog box.
     * @return a {@code DialogBox} instance configured for Thanos' messages.
     */
    public static DialogBox getThanosDialog(String message, Image image) {
        var db = new DialogBox(message, image, THANOS_STYLE);
        db.flip();
        return db;
    }
}
