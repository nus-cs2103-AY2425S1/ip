package soju;

import java.io.IOException;
import java.util.Collections;

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
import javafx.scene.layout.Priority;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    static final String USER_STYLE = "-fx-font-family: 'Arial'; -fx-font-size: 14;";
    static final String SOJU_STYLE = "-fx-font-family: 'Courier New'; -fx-font-size: 14;";
    static final String ERROR_STYLE = "-fx-font-family: 'Courier New'; -fx-text-fill: red; -fx-font-weight:"
            + " bold; -fx-background-color: pink; -fx-background-radius: 10; -fx-padding: 10;";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

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
        dialog.setAlignment(Pos.TOP_RIGHT);
        displayPicture.setImage(img);
        displayPicture.setFitWidth(50);
        displayPicture.setFitHeight(50);
        displayPicture.setClip(new javafx.scene.shape.Circle(25, 25, 25));

        this.setSpacing(15);
        this.setPadding(new Insets(10, 10, 10, 10));

        // Make the dialog (Label) fill available width
        HBox.setHgrow(dialog, Priority.ALWAYS);
        dialog.setMaxWidth(Double.MAX_VALUE);
        dialog.setWrapText(true); // Enable text wrapping for larger dialogs
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        dialog.setAlignment(Pos.TOP_LEFT);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDialog = new DialogBox(text, img);
        userDialog.setStyle(USER_STYLE);
        return userDialog;
    }

    public static DialogBox getSojuDialog(String text, Image img) {
        DialogBox sojuDialog = new DialogBox(text, img);
        sojuDialog.dialog.setStyle(SOJU_STYLE);
        sojuDialog.flip();
        return sojuDialog;
    }

    public static DialogBox getErrorDialog(SojuException e, Image img) {
        DialogBox errorDialog = new DialogBox(e.getMessage(), img);
        errorDialog.dialog.setStyle(ERROR_STYLE);
        errorDialog.flip();
        return errorDialog;
    }
}

