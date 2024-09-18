package meowmeow;

import javafx.scene.image.Image;

public class UserDialogBox extends DialogBox {
    public UserDialogBox(String text, Image img) {
        super(text, img, "/view/UserDialogBox.fxml");
    }

    public static DialogBox getDialog(String text, Image img) {
        return new UserDialogBox(text, img);
    }
}
