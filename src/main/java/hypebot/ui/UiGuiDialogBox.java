package hypebot.ui;

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

import java.io.IOException;
import java.util.Collections;

public class UiGuiDialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

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

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

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

    public static UiGuiDialogBox getUserDialog(String s, Image i) {
        return new UiGuiDialogBox(s, i);
    }

    public static UiGuiDialogBox getHypeBotDialog(String s, Image i) {
        var db = new UiGuiDialogBox(s, i);
        db.flip();
        return db;
    }

    public static UiGuiDialogBox getHypeBotDialog(String s, Image i, String commandType) {
        var db = new UiGuiDialogBox(s, i);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
}
