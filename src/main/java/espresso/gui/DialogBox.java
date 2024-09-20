package espresso.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class DialogBox extends HBox {

    private Label message;
    private ImageView avatar;

    /**
     * Private constructor to create a DialogBox.
     * Initializes the message and avatar, sets up the layout, and configures text and image properties.
     *
     * @param message The message text to be displayed.
     * @param avatar The ImageView containing the avatar to be displayed.
     */
    private DialogBox(String message, ImageView avatar) {
        this.message = new Label(message);
        this.avatar = avatar;

        // Set text wrapping and maximum width to control layout
        this.message.setWrapText(true);
        this.message.setMaxWidth(280);
        this.message.setPadding(new Insets(5));

        // Adjust the avatar dimensions
        this.avatar.setFitHeight(50.0);
        this.avatar.setFitWidth(50.0);

        // Create a container for the message and display picture
        VBox messageContainer = new VBox(this.message);
        messageContainer.setMaxWidth(300);
        VBox.setVgrow(messageContainer, Priority.ALWAYS);

        this.setAlignment(Pos.TOP_RIGHT);
        getChildren().addAll(messageContainer, avatar);
    }

    /**
     * Creates a DialogBox representing the user.
     * Configures the avatar for the user and aligns the dialog to the right.
     *
     * @param message The message text to be displayed in the user dialog.
     * @return A DialogBox containing the user message and avatar.
     */
    public static DialogBox createUserDialog(String message) {
        ImageView userAvatar = new ImageView(new Image(DialogBox.class.getResourceAsStream("/images/EspressoUser.png")));
        DialogBox userDialog = new DialogBox(message, userAvatar);
        userDialog.setAlignment(Pos.TOP_RIGHT);  // Align user dialog to the right

        // Set margin
        HBox.setMargin(userDialog.avatar, new Insets(0, 0, 0, 3));
        HBox.setMargin(userDialog.message, new Insets(0, 3, 0, 0));
        userDialog.setMinHeight(Region.USE_PREF_SIZE);
        return userDialog;
    }

    /**
     * Creates a DialogBox representing the bot.
     * Configures the avatar for the bot and aligns the dialog to the left.
     * @param message The message text to be displayed in the bot dialog.
     * @return A DialogBox containing the bot message and avatar.
     */
    public static DialogBox createBotDialog(String message) {
        ImageView botAvatar = new ImageView(new Image(DialogBox.class.getResourceAsStream("/images/Espresso.png")));
        DialogBox botDialog = new DialogBox(message, botAvatar);
        botDialog.setAlignment(Pos.TOP_LEFT);  // Align bot dialog to the left

        // Set margin
        HBox.setMargin(botDialog.avatar, new Insets(0, 0, 0, 4));
        HBox.setMargin(botDialog.message, new Insets(0, 0, 0, 4));
        botDialog.setMinHeight(Region.USE_PREF_SIZE);
        return botDialog;
    }
}