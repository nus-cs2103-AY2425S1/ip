package monobot;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Handles formatting of dialogues between user and bot.
 */
public class DialogBox extends HBox {
    @FXML
    private Text text;
    @FXML
    private VBox wrapper;
    @FXML
    private TextFlow textFlow;
    @FXML
    private ImageView profilePic;

    /**
     * Constructs a new dialogue box with given message.
     *
     * @param message message to be contained in dialogue box
     * @param isUser boolean to decipher if message is from user or bot
     */
    public DialogBox(String message, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        text.setText(message);
        wrapper.setStyle("-fx-background-color: " + (isUser ? "#DCF8C6" : "#FFFFFF") + "; "
                + "-fx-background-radius: 10;");

        textFlow.prefWidthProperty().bind(this.widthProperty().multiply(0.75));
        text.wrappingWidthProperty().bind(textFlow.widthProperty());

        String imagePath = isUser ? "/images/user.png" : "/images/bot.png";
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        profilePic.setImage(image);

        // Make the image view circular
        double radius = 20;
        profilePic.setFitWidth(radius * 2);
        profilePic.setFitHeight(radius * 2);
        profilePic.setClip(new javafx.scene.shape.Circle(radius, radius, radius));

        // Set alignment and order of elements
        if (isUser) {
            this.setAlignment(Pos.CENTER_RIGHT);
            this.getChildren().setAll(wrapper, profilePic);
            wrapper.setAlignment(Pos.CENTER_RIGHT);
            textFlow.setTextAlignment(javafx.scene.text.TextAlignment.RIGHT);
        } else {
            this.setAlignment(Pos.CENTER_LEFT);
            this.getChildren().setAll(profilePic, wrapper);
            wrapper.setAlignment(Pos.CENTER_LEFT);
            textFlow.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
        }
    }
}
