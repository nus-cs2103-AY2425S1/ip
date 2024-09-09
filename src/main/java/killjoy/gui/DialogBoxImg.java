package killjoy.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box containing an ImageView to represent the user and KillJoy and containing another
 * ImageView that is displayed in the dialog box.
 */
public class DialogBoxImg extends HBox {
    @FXML
    private ImageView displayPicture;
    @FXML
    private ImageView contentImage;

    /**
     * Constructs a DialogBoxImg object with the specified images.
     *
     * @param img The image to be displayed in the dialog box.
     * @param img2 The image to be displayed in the dialog box.
     */
    private DialogBoxImg(Image img, Image img2) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBoxImg.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        displayPicture.setImage(img);
        contentImage.setImage(img2);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        contentImage.getStyleClass().add("reply-image");
    }

    /**
     * Returns a DialogBoxImg object with the specified images.
     *
     * @param img The image to be displayed in the dialog box.
     * @param img2 The image to be displayed in the dialog box.
     * @return A DialogBoxImg object with the specified images.
     */
    public static DialogBoxImg getKjDialog(Image img, Image img2) {
        var db = new DialogBoxImg(img, img2);
        db.flip();
        return db;
    }
}

