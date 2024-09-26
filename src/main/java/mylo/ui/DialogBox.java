package mylo.ui;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

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
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 * <p></p>
 * <p>
 * This class handles the creation of dialog boxes that are displayed in the application's UI.
 * Each dialog box includes the speaker's image and corresponding text.
 * </p>
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     * <p></p>
     * <p>
     * This constructor initializes the dialog box using an FXML file and sets the provided text
     * and image in the corresponding controls.
     * </p>
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed next to the text.
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

        Circle clip = new Circle(25, 25, 25);
        displayPicture.setClip(clip);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.BOTTOM_LEFT);

        // Find Label and change background colour
        List<Node> list = this.getChildren().stream().filter((Node node) -> node.getId().equals("dialog")).toList();
        assert list.get(0) instanceof Label : "Flip() found the wrong child";
        Label label = (Label) list.get(0);
        label.setStyle("-fx-background-color: gainsboro; -fx-background-radius: 10; -fx-label-padding: 8;");
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     * This is different from regular flip to highlight error message, particularly by setting the background red.
     */
    private void errorFlip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.BOTTOM_LEFT);

        // Find Label and change background colour
        List<Node> list = this.getChildren().stream().filter((Node node) -> node.getId().equals("dialog")).toList();
        assert list.get(0) instanceof Label : "errorFlip() found the wrong child";
        Label label = (Label) list.get(0);
        label.setStyle("-fx-background-color: palevioletred; -fx-background-radius: 10; -fx-label-padding: 8;");
    }

    /**
     * Creates a dialog box for the user.
     * <p>
     * This method returns a DialogBox with the user's text and image.
     * </p>
     *
     * @param text The user's input text.
     * @param img The user's display image.
     * @return A DialogBox displaying the user's text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box for Mylo's response.
     * <p>
     * This method returns a DialogBox with Mylo's response text and image,
     * and flips the layout to display the image on the left.
     * </p>
     *
     * @param text The system's response text.
     * @param img The system's display image.
     * @return A flipped DialogBox displaying the system's text and image.
     */
    public static DialogBox getMyloDialog(String text, Image img, boolean isError) {
        var db = new DialogBox(text, img);

        if (isError) {
            db.errorFlip();
        } else {
            db.flip();
        }

        return db;
    }
}
