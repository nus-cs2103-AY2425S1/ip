package papadom;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(String message, Image img) {
        text = new Label(message);
        displayPicture = new ImageView(img);

        // Style the dialog box
        text.setWrapText(true); // Allow wrapping if the text exceeds the max width
        text.setMaxWidth(350); // Set max width to a reasonable size (adjust as needed)
        text.setMinHeight(Region.USE_PREF_SIZE); // Allow the label to grow vertically as needed
        displayPicture.setFitWidth(50.0); // Profile picture width
        displayPicture.setFitHeight(50.0); // Profile picture height

        // Align user's message (text on the left, image on the right)
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    // Method to flip the dialog box for bot messages (image on the left, text on the right)
    public void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        this.getChildren().setAll(displayPicture, text); // Swap image and text positions
    }
}
