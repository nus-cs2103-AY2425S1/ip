package sigma;

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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import sigma.command.CommandType;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 * The dialog box can be flipped to change the layout of the ImageView and text.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     * The dialog box is initialized with the provided text and image, and is loaded from an FXML file.
     *
     * @param text the text to be displayed in the dialog box
     * @param img the image to be displayed as the speaker's face
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        setDisplayPicture(img);
    }

    /**
     * Sets the image of the displayPicture and clips it to a circular shape.
     *
     * @param img the image to be set as the display picture
     */
    private void setDisplayPicture(Image img) {
        displayPicture.setImage(img);
        Circle clip = new Circle(25.0, 25.0, 25.0);
        clip.setFill(new ImagePattern(img));
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box layout such that the ImageView is on the left and text is on the right.
     * Also aligns the dialog box to the top-left and adds a style class for the reply label.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a dialog box for the user with the specified text and image.
     *
     * @param text the text to be displayed in the dialog box
     * @param img the image to be displayed as the user's face
     * @return a new DialogBox instance with the user's text and image
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box for Sigma with the specified text, image, and command type.
     * The dialog box is flipped to change the layout.
     *
     * @param text the text to be displayed in the dialog box
     * @param img the image to be displayed as Sigma's face
     * @param commandType the type of command associated with the dialog
     * @return a new DialogBox instance with Sigma's text, image, and command type
     */
    public static DialogBox getSigmaDialog(String text, Image img, CommandType commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Creates a welcome dialog box for Sigma with a default welcome message and the specified image.
     * The dialog box is flipped to change the layout.
     *
     * @param img the image to be displayed as Sigma's face
     * @return a new DialogBox instance with a welcome message and Sigma's image
     */
    public static DialogBox getSigmaWelcome(Image img) {
        String welcome = "Skibidi bop bop! It's the legend27 himself, Sigma!\n"
                + "Ready to hit the grind and go sigma mode?";
        var db = new DialogBox(welcome, img);
        db.flip();
        return db;
    }
}
