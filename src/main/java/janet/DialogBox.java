package janet;

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

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    private final Circle circle = new Circle(50, 50, 50);

    public DialogBox(String s, Image i) {
        text = new Label(s);
        text.setPadding(new Insets(10, 5, 0, 5));

        displayPicture = new ImageView(i);
        displayPicture.setClip(circle); // places the image into a circle

        // Styling the dialog box
        text.setWrapText(true);
        displayPicture.setFitWidth(100);
        displayPicture.setFitHeight(100);
        this.setAlignment(Pos.CENTER_RIGHT);   // aligns its children

        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String s, Image i) {
        // returns a new dialogBox with the String message and the image of the user
        return new DialogBox(s, i);
    }

    public static DialogBox getDukeDialog(String s, Image i) {
        // returns a new FLIPPED dialogBox with the String message and the image of Duke
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }

    /**
     * Sets the background color of the dialogBox
     *
     * @param color The background color chosen for the dialogBox
     */
    public void setColor(Color color) {
        BackgroundFill bFill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(bFill);
        this.setBackground(background);
    }
}