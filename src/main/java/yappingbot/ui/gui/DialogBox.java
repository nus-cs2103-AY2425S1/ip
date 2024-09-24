package yappingbot.ui.gui;

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
import javafx.scene.layout.VBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private VBox labelsVbox;
    private final boolean isReplyDialog;

    /**
     * Constructor for DialogBox that holds a user image and text.
     *
     * @param text String to be displayed
     * @param img Image of user
     * @throws IOException Exception if anything goes wrong trying to read fxml
     */
    private DialogBox(String text, Image img, boolean isReplyDialog) throws IOException {
        URL fxmlSource = MainWindow.class.getResource("/view/DialogBox.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlSource);

        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();

        assert text != null;
        dialog.setText(text);

        assert img != null;
        displayPicture.setImage(img);

        this.isReplyDialog = isReplyDialog;
        if (isReplyDialog) {
            dialog.getStyleClass().add("reply-label");
            labelsVbox.getStyleClass().add("labelsVbox-reply");
            flip();
        } else {
            dialog.getStyleClass().add("label");
            labelsVbox.getStyleClass().add("labelsVbox");
        }
    }

    public boolean isReplyDialog() {
        return this.isReplyDialog;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    /**
     * Adds new text into DialogBox.
     *
     * @param text String to be appended.
     */
    public void addLabel(String text) {
        assert text != null;
        Label l = new Label();
        l.setText(text);
        l.getStyleClass().add("label");
        if (isReplyDialog) {
            l.getStyleClass().add("reply-label");
        }
        l.setMaxWidth(dialog.getMaxWidth());
        l.setMinWidth(dialog.getMinWidth());
        l.setWrapText(dialog.isWrapText());
        labelsVbox.getChildren().add(l);
    }

    /**
     * Factory method to represent what the user said.
     *
     * @param text String inputted by user
     * @param img User's avatar
     * @return new DialogBox to be added to the scrollpane
     * @throws IOException if DialogBox constructor throws error
     */
    public static DialogBox getUserDialog(String text, Image img) throws IOException {
        return new DialogBox(text, img, false);
    }

    /**
     * Factory method to represent what bot replies.
     *
     * @param text String reply from bot
     * @param img bot's avatar
     * @return new DialogBox to be added to the scrollpane
     * @throws IOException if DialogBox constructor throws error
     */
    public static DialogBox getReplyDialog(String text, Image img) throws IOException {
        return new DialogBox(text, img, true);
    }

}
