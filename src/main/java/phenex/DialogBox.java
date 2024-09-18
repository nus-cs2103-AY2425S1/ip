package phenex;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final double IMAGE_WIDTH = 60.0;
    private static final double IMAGE_HEIGHT = 60.0;
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    protected DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setFont(Font.font("Times New Roman", 12));
        dialog.setFill(Color.BLUE);
        // try to set black border around dialog text
        displayPicture.setImage(img);
        displayPicture.setFitWidth(IMAGE_WIDTH);
        displayPicture.setFitHeight(IMAGE_HEIGHT);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Flips the image of the dialog box.
     */
    private void flipImage() {
        this.displayPicture.setScaleX(-1);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDialog = new DialogBox(text, img);
        return userDialog;
    }

    public static DialogBox getPhenexDialog(String text, Image img) {
        DialogBox phenexDialog = new DialogBox(text, img);
        phenexDialog.flipImage();
        phenexDialog.flip();
        phenexDialog.dialog.setFill(Color.BLUEVIOLET);
        return phenexDialog;
    }

}
