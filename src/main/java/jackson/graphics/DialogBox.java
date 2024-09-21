package jackson.graphics;

import java.io.IOException;
import java.util.Collections;

import jackson.enums.Commands;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // use this circle dimension to clip the user/Jackson image
        float ideal = (float) 75 / 2;
        Circle clip = new Circle(ideal, ideal, ideal);

        // display text and clip image
        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setFitHeight(75);
        displayPicture.setFitWidth(75);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     * Used only for Jackson responses.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Returns DialogBox containing user input.
     * @param text User input string.
     * @param img {@code Image} User profile image.
     * @return {@code DialogBox} to be shown on the GUI.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.changeDialogStyle(Commands.CommandType.USER);
        return db;
    }

    /**
     * Returns DialogBox containing Jackson response.
     * @param text Jackson response string.
     * @param img {@code Image} Jackson profile image.
     * @return {@code DialogBox} to be shown on the GUI.
     */
    public static DialogBox getJacksonDialog(String text, Image img, Commands.CommandType commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }

    /**
     * Changes Jackson DialogBox Style.
     * Only used for Jackson.
     * @param commandType String containing what type of response Jackson returns.
     */
    private void changeDialogStyle(Commands.CommandType commandType) {
        switch(commandType) {
        case LIST:
            dialog.getStyleClass().add("list-label");
            break;
        case ERROR:
            dialog.getStyleClass().add("error-label");
            break;
        case INTRO, EXIT:
            dialog.getStyleClass().add("intro-exit-label");
            break;
        case TASK:
            dialog.getStyleClass().add("task-label");
            break;
        case SECRET:
            dialog.getStyleClass().add("secret-label");
            break;
        case NORMAL:
            dialog.getStyleClass().add("jackson-label");
            break;
        case USER:
            // Do nothing
            break;
        default:
            // Do nothing
        }
    }
}
