package meowmeow;

//solution below adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} with the specified text, image, and FXML file path.
     *
     * @param text The text to display in the dialog box.
     * @param img The image of the speaker to display.
     * @param path The path to the FXML file that defines the layout of the dialog box.
     */
    public DialogBox(String text, Image img, String path) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(path));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Creates and returns a new {@code DialogBox} initialized with the specified text and image.
     * The dialog box is constructed using the default FXML file located at {@code /view/DialogBox.fxml}.
     *
     * @param text The text to display in the dialog box.
     * @param img The image of the speaker to display.
     * @return A new instance of {@code DialogBox} containing the specified text and image.
     */
    public static DialogBox getDialog(String text, Image img) {
        return new DialogBox(text, img, "/view/DialogBox.fxml");
    }

}
