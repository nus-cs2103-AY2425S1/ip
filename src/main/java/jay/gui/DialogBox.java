package jay.gui;

import java.io.IOException;
import java.net.URL;
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

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String color) {
        try {
            URL fxmlResource = Gui.class.getResource("/view/DialogBox.fxml");
            assert fxmlResource != null : "FXML resource not found";

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlResource);
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String style = dialog.getStyle() + "-fx-background-color: " + color + ";";
        // Bold the text if it is lightRed, which mean it is an exception message
        if (color.equals("Red")) {
            style += "-fx-font-weight: bold;";
        }
        dialog.setText(text);
        dialog.setStyle(style);
        displayPicture.setImage(img);
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

    public static DialogBox getUserDialog(String text, Image img) {
        assert img != null : "Image cannot be null!";
        return new DialogBox(text, img, "lightGreen");
    }

    public static DialogBox getJayDialog(String text, Image img, boolean isException) {
        assert img != null : "Image cannot be null!";
        DialogBox dialogBox = null;

        if (isException) {
            dialogBox = new DialogBox(text, img, "Red");
        } else {
            dialogBox = new DialogBox(text, img, "lightBlue");
        }

        dialogBox.flip();
        return dialogBox;
    }
}


