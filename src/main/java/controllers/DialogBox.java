package controllers;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Controller class for the DialogBox GUI.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private Circle circle;

    /**
     * Constructs a new DialogBox element.
     *
     * @param text Text string within the element.
     * @param img Image within the element.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class
                    .getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(text);

        // Approach was adopted from https://www.youtube.com/watch?v=54fEFYx34vk
        // To display images as circles
        this.circle.setFill(new ImagePattern(img));
        this.circle.getStyleClass().add("styled-circle");
    }

    /**
     * Flips the contents within the DialogBox element.
     * Such that the ImageView is on the left and text is on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Changes the label color of Brock reply, if exception was caught.
     *
     * @param isException Indicator if exception was caught.
     */
    private void setErrorStyle(boolean isException) {
        if (isException) {
            dialog.getStyleClass().add("error-reply");
        }
    }

    /**
     * Constructs a user dialog box.
     *
     * @param text Text string within the dialog box.
     * @param img Image within the dialog box.
     * @return User dialog box element.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Constructs a Brock dialog box.
     *
     * @param text Text string within the dialog box.
     * @param isException Indicator if exception was caught.
     * @param img Image within the dialog box.
     * @return Brock dialog box element.
     */
    public static DialogBox getBrockDialog(String text, boolean isException, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setErrorStyle(isException);
        return db;
    }
}
