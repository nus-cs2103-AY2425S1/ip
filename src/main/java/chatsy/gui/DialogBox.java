package chatsy.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


/**
 * DialogBox represents a custom control for displaying a dialog bubble,
 * including a text message and a profile image, with options to style it based on the user or system.
 */
public class DialogBox extends HBox {

    @FXML
    private Text text;

    @FXML
    private Rectangle box;

    @FXML
    private ImageView profileImage;

    /**
     * Constructs a DialogBox object with the given text label and image.
     * This method loads the FXML layout for the dialog box.
     *
     * @param label The text message to be displayed in the dialog box.
     * @param image The profile image to be displayed alongside the message.
     */
    public DialogBox(String label, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        text.setText(label);
        profileImage.setImage(image);
        setBoxDimensions();
    }

    /**
     * Adjusts the dimensions of the dialog box based on the size of the text inside it.
     */
    private void setBoxDimensions() {
        box.setWidth(text.getLayoutBounds().getWidth() + 20);
        box.setHeight(text.getLayoutBounds().getHeight() + 20);
    }

    /**
     * Creates a DialogBox styled for the user.
     *
     * @param text  The text message to be displayed in the user's dialog box.
     * @param image The profile image of the user.
     * @return A new DialogBox representing the user's message.
     */
    public static DialogBox getUserDialogBox(String text, Image image) {
        return new DialogBox(text, image);
    }

    /**
     * Creates a DialogBox styled for the system (Chatsy), with the option to display an error message.
     *
     * @param text          The text message to be displayed in the Chatsy dialog box.
     * @param image         The profile image of Chatsy (system).
     * @param isErrorMessage A flag to indicate if the message is an error (true if error, false otherwise).
     * @return A new DialogBox representing Chatsy's message, styled accordingly.
     */
    public static DialogBox getChatsyDialogBox(String text, Image image, boolean isErrorMessage) {
        DialogBox db = new DialogBox(text, image);
        db.setBackgroundColor(isErrorMessage);
        db.flip();
        return db;
    }

    /**
     * Sets the background color of the dialog box. The color changes depending on whether the message is an error.
     *
     * @param isErrorMessage A boolean flag indicating if the message is an error.
     *                       If true, the background color is set to red; otherwise, it's orange.
     */
    private void setBackgroundColor(boolean isErrorMessage) {
        box.setFill(Paint.valueOf(isErrorMessage ? "#d55e00" : "#00ffbf"));
    }

    /**
     * Flips the dialog box orientation, aligning the text and image to the left, simulating a system message.
     */
    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);
        text.setTextAlignment(TextAlignment.LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(getChildren());
        FXCollections.reverse(tmp);
        getChildren().setAll(tmp);
    }
}
