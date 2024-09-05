package velma;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(String s, Image i) {
        text = new Label(s);
        displayPicture = new ImageView(i);

        // Styling the dialog box
        text.setWrapText(true);
        Circle circle = new Circle(50, 50, 50);  // Circle with center (50, 50) and radius 50
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        displayPicture.setClip(circle);
        this.setAlignment(Pos.TOP_RIGHT);  // Align text and image to the right for user dialog

        // Add padding and spacing for dialog box
        this.setPadding(new Insets(10, 10, 10, 10));  // Add padding around the entire dialog box
        this.setSpacing(10);  // Increase spacing between the text and image

        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);  // Align text and image to the left for Duke dialog
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);  // Reverse the order of the label and image for the Duke dialog
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    public static DialogBox getDukeDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}
