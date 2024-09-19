package hypebot.ui.gui;

import java.io.IOException;
import java.util.Collections;

import hypebot.command.Command;
import hypebot.main.HypeBot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a {@code UiGuiDialogBox} representing a dialog box on the GUI.
 * <p>A child of {@link HBox}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 */
public class UiGuiDialogBox extends HBox {
    /** A {@link Control} for the text being shown. */
    @FXML
    private Label dialog;

    /** A {@link Node} for the profile picture shown. */
    @FXML
    private ImageView displayPicture;

    /**
     * Takes in a {@link String} text and an {@link Image} to be set as text shown
     * for {@code dialog} and image shown for the {@code displayPicture} of the
     * dialog box respectively, and creates a new {@code UiGuiDialogBox}.
     *
     * @param text  {@link String} text to be shown in the dialog box.
     * @param image {@link Image} image to be shown in the profile picture icon of the dialog box.
     */
    public UiGuiDialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    UiGuiMainWindow.class.getResource("/view/UiGuiDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(image);
    }

    /**
     * Flips the direction of elements in the {@code UiGuiDialogBox} when
     * it is a dialog box representing {@link HypeBot}'s responses to user input.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Takes in the class name of the {@link Command} executed and triggers the
     * changing of the dialog box's background color accordingly using CSS labels.
     *
     * @param commandType Class name of the {@link Command} executed in {@link String} form.
     */
    private void changeDialogStyle(String commandType) {
        switch (commandType) {
        case "AddCommand":
            dialog.getStyleClass().add("add-label");
            break;
        case "DeleteAllCommand", "DeleteCommand":
            dialog.getStyleClass().add("delete-label");
            break;
        case "FindCommand", "HappeningCommand":
            dialog.getStyleClass().add("search-label");
            break;
        case "HelpCommand":
            dialog.getStyleClass().add("help-label");
            break;
        case "MarkCommand":
            dialog.getStyleClass().add("mark-label");
            break;
        case "UnmarkCommand":
            dialog.getStyleClass().add("unmark-label");
            break;
        case "UnknownCommand":
            dialog.getStyleClass().add("unknown-label");
            break;
        case "Error":
            dialog.getStyleClass().add("error-label");
            break;
        default:
            dialog.getStyleClass().add("general-label");
        }
    }

    /**
     * Takes in a {@link String} text and an {@link Image} to be set as text shown
     * as user input and image shown for the user profile pic of the user dialog box,
     * and creates a new {@code UiGuiDialogBox} for user input.
     *
     * @param s {@link String} user input.
     * @param i User's profile picture.
     * @return A new {@code UiGuiDialogBox} with the text and image above.
     */
    public static UiGuiDialogBox getUserDialog(String s, Image i) {
        return new UiGuiDialogBox(s, i);
    }

    /**
     * Takes in a {@link String} text and an {@link Image} to be set as text shown
     * as {@link HypeBot} response and image shown for {@link HypeBot}'s profile pic
     * for the {@link HypeBot} dialog box, and creates a new {@code UiGuiDialogBox}
     * for {@link HypeBot} response to user input.
     *
     * @param s {@link String} text response to user input.
     * @param i {@link HypeBot}'s profile picture.
     * @return A new {@code UiGuiDialogBox} with the text and image above.
     */
    public static UiGuiDialogBox getHypeBotDialog(String s, Image i) {
        var db = new UiGuiDialogBox(s, i);
        db.flip();
        return db;
    }

    /**
     * Takes in a {@link String} text and an {@link Image} to be set as text shown
     * as {@link HypeBot} response, an image shown for {@link HypeBot}'s profile pic,
     * and the {@link String} class name of the {@link Command} executed to format the
     * {@link HypeBot} dialog box, and creates a new {@code UiGuiDialogBox}
     * for {@link HypeBot} response to user input.
     *
     * @param s           {@link String} text response to user input.
     * @param i           {@link HypeBot}'s profile picture.
     * @param commandType {@link String} class name of the {@link Command} executed to
     *                    change the background color of the dialog box
     * @return A new {@code UiGuiDialogBox} with the text, image, and format specified by the
     *         command type.
     */
    public static UiGuiDialogBox getHypeBotDialog(String s, Image i, String commandType) {
        var db = new UiGuiDialogBox(s, i);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
}
