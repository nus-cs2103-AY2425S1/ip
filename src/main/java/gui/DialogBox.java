package gui;

import java.io.IOException;

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

/**
 * Represents a Dialog Box in Main application
 */
public class DialogBox extends HBox {
    @FXML
    private Label text;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for a Dialog Box with text and image
     * @param s String text to be displayed
     * @param i Image representing user associated with DialogBox
     */
    private DialogBox(String s, Image i) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        text.setText(s);
        displayPicture.setImage(i);
    }
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
        text.getStyleClass().add("reply-label");
    }
    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "AddCommand":
            text.getStyleClass().add("add-label");
            break;
        case "DeleteCommand":
            text.getStyleClass().add("delete-label");
            break;
        case "MarkCommand":
            text.getStyleClass().add("marked-label");
            break;
        case "UnmarkCommand":
            text.getStyleClass().add("unmarked-label");
            break;
        default:
            // Do nothing
        }
    }
    public static DialogBox getUserDialog(String s, Image i) {
        DialogBox db = new DialogBox(s, i);
        return db;
    }
    public static DialogBox getTalkerDialog(String s, Image i, String commandType) {
        DialogBox db = new DialogBox(s, i);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
}
