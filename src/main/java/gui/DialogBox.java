package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


/**
 * Represents a Dialog Box in Main application
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for a Dialog Box with text and image
     * @param s String text to be displayed
     * @param i Image representing user associated with DialogBox
     */
    public DialogBox(String s, Image i) {
        text = new Label(s);
        displayPicture = new ImageView(i);
        style();
        this.getChildren().addAll(text, displayPicture);
    }
    private void style() {
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);
    }
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }
    public static DialogBox getTalkerDialog(String s, Image i) {
        DialogBox db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}
