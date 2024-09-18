package meowmeow;

import javafx.scene.image.Image;

/**
 * Represents a dialog box for the user that displays an image on the right and text on the left.
 */
public class UserDialogBox extends DialogBox {

    /**
     * Constructs a UserDialogBox with the specified text and image.
     *
     * @param text The text to display in the dialog box.
     * @param img The image to display in the dialog box.
     */
    public UserDialogBox(String text, Image img) {
        super(text, img, "/view/UserDialogBox.fxml");
    }

    /**
     * Creates and returns a UserDialogBox with the specified text and image.
     *
     * @param text The text to display in the dialog box.
     * @param img The image to display in the dialog box.
     * @return A new instance of UserDialogBox with the image on the left.
     */
    public static DialogBox getDialog(String text, Image img) {
        return new UserDialogBox(text, img);
    }
}
