package quirkbot.gui;

import java.io.IOException;
import java.util.Collections;

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
 * DialogBox class which represents the dialog boxes for User and Duke
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private double radius = 50.0;

    /**
     * Constructs a DialogBox object with the specified text and image.
     * This constructor loads the FXML layout for the dialog box and initializes
     * the dialog text and display picture.
     *
     * @param text to be displayed in the dialog box.
     * @param img  to be displayed alongside the text.
     */
    private DialogBox(String text, Image img) {
        assert text != null : "Dialog text should not be null";
        assert img != null : "Image should not be null";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        makeProfilePictureCircular();
    }

    /**
     * Creates and returns a DialogBox representing the user's dialog.
     * The dialog box will display the specified text and image without flipping the alignment.
     *
     * @param text to be displayed in the user's dialog box.
     * @param img  to be displayed alongside the user's dialog.
     * @return DialogBox object containing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Changes the dialog colour style for different user commands.
     *
     * @param commandType the type of the user command.
     */
    public void changeDialogStyle(String commandType) {
        switch (commandType) {
        case "AddCommand":
            dialog.getStyleClass().add("add-label");
            break;
        case "MarkCommand":
            dialog.getStyleClass().add("marked-label");
            break;
        case "DeleteCommand":
            dialog.getStyleClass().add("delete-label");
            break;
        default:
            break;
        }
    }

    /**
     * Creates and returns a DialogBox representing the bot's dialog.
     * The dialog box will display the specified text and image, with the alignment flipped
     * so that the image appears on the left side and the text on the right.
     *
     * @param text        to be displayed in the bot's dialog box.
     * @param img         to be displayed alongside the bot's dialog.
     * @param commandType indicates the type of user command for bot to process.
     * @return DialogBox object containing the bot's dialog.
     */
    public static DialogBox getBuddyDialog(String text, Image img, String commandType) {
        assert text != null : "Dialog text for buddy should not be null";
        assert img != null : "Image for buddy should not be null";

        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }

    /**
     * Flips the dialog box horizontally, reversing the order of the text and image.
     * This method is used to align the bot's dialog differently from the user's dialog.
     */
    public void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);

        assert getAlignment().equals(Pos.TOP_LEFT) : "Alignment should be TOP_LEFT after flip";

        ObservableList<Node> reversedChildren = FXCollections.observableArrayList(getChildren());
        Collections.reverse(reversedChildren);
        assert getChildren().equals(reversedChildren) : "Children should be reversed after flip";
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Makes the profile picture circular.
     */
    public void makeProfilePictureCircular() {
        double diameter = radius * 2;
        Circle clip = new Circle(radius, radius, radius);
        displayPicture.setClip(clip);

        displayPicture.setFitWidth(diameter);
        displayPicture.setFitHeight(diameter);
        displayPicture.setPreserveRatio(true);

        displayPicture.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            clip.setRadius(Math.min(newBounds.getWidth(), newBounds.getHeight()) / 2.0);
        });
    }
}