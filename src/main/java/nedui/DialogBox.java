package nedui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The {@code DialogBox} class represents a custom control for displaying dialog messages in the GUI.
 * It consists of a {@code Label} to display text and an {@code ImageView} to display an image (such as an avatar).
 * This class extends {@code HBox} and is used to create dialog boxes for both the user and the application (Ned).
 *
 * <p>The class loads its layout from an FXML file and provides methods to flip the dialog box,
 * positioning the image on either the left or the right side of the text.
 * It also offers static factory methods to create dialog boxes with appropriate styling and alignment
 * for user inputs and application responses.
 *
 * <p>Key functionalities include:
 * <ul>
 *   <li>Loading the dialog box layout from an FXML resource.</li>
 *   <li>Displaying a message with an associated image.</li>
 *   <li>Flipping the dialog box orientation for different dialog types.</li>
 * </ul>uu
 *
 * @see javafx.scene.layout.HBox
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} with the specified text and image.
     * The FXML layout is loaded, and the dialog text and display picture are set accordingly.
     *
     * @param s The text to display in the dialog.
     * @param i The image to display alongside the text.
     */
    public DialogBox(String s, Image i) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(s);
        displayPicture.setImage(i);
        DisplayImage.applyCircularFilter(this.displayPicture, 80);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Changes the colour of the chatbot's dialog box if the message is meant to be an error to red.
     *
     * @param isErrorMessage Whether the message shown by the chatbot was an error message.
     */
    private void changeDialogStyle(boolean isErrorMessage) {
        if (isErrorMessage) {
            this.dialog.setStyle(""); // Clears the inline style
            this.dialog.getStyleClass().add("error-label");
        }
    }

    /**
     * Creates a {@code DialogBox} representing the user's input, with appropriate styling.
     *
     * @param userInput The user's input text.
     * @param userImage The user's avatar image.
     * @return A {@code DialogBox} configured for the user's input.
     */
    public static DialogBox getUserDialog(String userInput, Image userImage) {
        DialogBox userDialogBox = new DialogBox(userInput, userImage);
        userDialogBox.setPadding(new Insets(40, 10, 10, 10));
        userDialogBox.setSpacing(15); // Ensure spacing between image and text
        userDialogBox.dialog.setStyle("-fx-background-color: white; -fx-border-radius: 10;");
        return userDialogBox;
    }


    /**
     * Creates a {@code DialogBox} representing the application's response (Ned's reply),
     * with the dialog box flipped and appropriate styling.
     *
     * @param nedInput The application's response text.
     * @param nedImage The application's avatar image.
     * @return A {@code DialogBox} configured for the application's response.
     */
    public static DialogBox getNedDialog(String nedInput, Image nedImage, boolean isErrorMessage) {
        var nedDialogBox = new DialogBox(nedInput, nedImage);
        nedDialogBox.setPadding(new Insets(40, 10, 10, 10));
        nedDialogBox.setSpacing(15); // Ensure spacing between image and text
        nedDialogBox.dialog.setStyle("-fx-background-color: lightgrey; -fx-padding: 10; -fx-border-radius: 10;");
        nedDialogBox.flip();
        nedDialogBox.changeDialogStyle(isErrorMessage);
        return nedDialogBox;
    }
}
