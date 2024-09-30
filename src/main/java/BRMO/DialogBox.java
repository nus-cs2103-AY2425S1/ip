package BRMO;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    private DialogBox(String text, ImageView displayPicture) {
        this.text = new Label(text);
        this.displayPicture = displayPicture;

        // Configure text wrapping and width
        this.text.setWrapText(true);
        this.text.setMaxWidth(280);  // Limit the width to prevent horizontal stretching
        this.text.setPadding(new Insets(5));  // Optional: padding for better readability

        // Set size for the display picture
        this.displayPicture.setFitWidth(50.0);
        this.displayPicture.setFitHeight(50.0);

        // Create a container for the text and display picture
        VBox textContainer = new VBox(this.text);
        textContainer.setMaxWidth(300);  // Adjust width based on layout
        VBox.setVgrow(textContainer, Priority.ALWAYS);  // Ensure it grows properly

        // Set alignment and add the elements to the HBox
        this.setAlignment(Pos.TOP_RIGHT);  // Align right for user, modify for bot
        this.getChildren().addAll(textContainer, this.displayPicture);
    }

    public static DialogBox getUserDialog(String text) {
        var db = new DialogBox(text, new ImageView(new Image(DialogBox.class.getResourceAsStream("/images/user.png"))));
        db.setAlignment(Pos.TOP_RIGHT);  // Align the user dialog to the right
        db.getChildren().setAll(db.text, db.displayPicture);

        // Set margins to ensure proper spacing
        HBox.setMargin(db.displayPicture, new Insets(0, 0, 0, 4));
        HBox.setMargin(db.text, new Insets(0, 4, 0, 0));

        // Ensure dialog box doesn't resize unpredictably
        db.setMinHeight(Region.USE_PREF_SIZE);  // Prevent resizing

        return db;
    }

    public static DialogBox getBotDialog(String text) {
        var db = new DialogBox(text, new ImageView(new Image(DialogBox.class.getResourceAsStream("/images/bot.png"))));
        db.setAlignment(Pos.TOP_LEFT);  // Align the bot dialog to the left
        db.getChildren().setAll(db.displayPicture, db.text);

        // Set margins to ensure proper spacing
        HBox.setMargin(db.displayPicture, new Insets(0, 0, 0, 4));
        HBox.setMargin(db.text, new Insets(0, 0, 0, 4));

        // Ensure dialog box doesn't resize unpredictably
        db.setMinHeight(Region.USE_PREF_SIZE);  // Prevent resizing

        return db;
    }
}