package nayana;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.VBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(String s, Image i) {
        text = new Label(s);
        displayPicture = new ImageView(i);

        // Style the image to be circular
        Circle clip = new Circle(50, 50, 50); // CenterX, CenterY, Radius
        displayPicture.setClip(clip);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        // Style the text
        text.setWrapText(true);
        text.setPadding(new Insets(10)); // Add padding around the text

        // Set padding between image and text
        this.setSpacing(10);

        // Set background color
        this.setBackground(new Background(new BackgroundFill(
              Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY
        )));
        this.setPadding(new Insets(10)); // Padding inside the dialog box

        // Align the dialog box
        this.setAlignment(Pos.TOP_RIGHT);

        // Add children to the HBox
        this.getChildren().addAll(text, displayPicture);
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

    public static DialogBox getNayanaDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        db.setBackground(new Background(new BackgroundFill(
              Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY
        )));
        return db;
    }
}
