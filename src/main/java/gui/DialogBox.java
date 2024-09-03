package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * Represents a Dialog Box in Main application
 */
public class DialogBox extends HBox {
    private static final Color USER_COLOR = Color.rgb(209, 211, 164, 0.9);
    private static final Color TALKER_COLOR = Color.rgb(164, 176, 211, 0.9);
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
        Circle c = new Circle(50.0, 50.0, 50.0);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        displayPicture.setClip(c);
        text.setPadding(new Insets(5.0));
        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(5.0));
        this.setSpacing(5.0);
    }
    private void setUserColor() {
        this.text.setBackground(
                new Background(new BackgroundFill(USER_COLOR, new CornerRadii(3.0), Insets.EMPTY)));
    }
    private void setTalkerColor() {
        this.text.setBackground(
                new Background(new BackgroundFill(TALKER_COLOR, new CornerRadii(3.0), Insets.EMPTY)));
    }
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
    public static DialogBox getUserDialog(String s, Image i) {
        DialogBox db = new DialogBox(s, i);
        db.setUserColor();
        return db;
    }
    public static DialogBox getTalkerDialog(String s, Image i) {
        DialogBox db = new DialogBox(s, i);
        db.flip();
        db.setTalkerColor();
        return db;
    }
}
