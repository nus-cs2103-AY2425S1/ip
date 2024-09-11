package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Dialog box consisting of an ImageView to display the user's face, and a label
 * containing the message from the user.
 */
public class DialogBox extends HBox {
    private final Label text;
    private final ImageView displayPicture;

    /** Construct new DialogBox with given label, image and background color. */
    public DialogBox(Label l, ImageView iv, String color) {
        text = l;
        displayPicture = iv;

        //@@author {eugencsa}-reused
        //minor changes made for styling purposes to avoid using fxml
        text.setFont(Font.font("Monospaced", 12));
        text.setTextFill(Color.WHITE);
        text.setWrapText(true);
        text.setStyle("-fx-padding: 10;"
                + "-fx-border-color: white;"
                + "-fx-border-radius: 10px;");
        text.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(text, Priority.ALWAYS);
        text.setPrefWidth(0);

        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        setAlignment(Pos.TOP_RIGHT);
        getChildren().addAll(text, displayPicture);
        setSpacing(10);
        setStyle("-fx-background-color:" + color);
        //@@author
    }

    /** Flips the dialog box such that the ImageView is on the left and text on the right. */
    private void flip() {
        setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(getChildren());
        FXCollections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView, "#1e1e1e");
    }

    public static DialogBox getBotDialog(Label label, ImageView imageView) {
        DialogBox db = new DialogBox(label, imageView, "#2b2b2b");
        db.flip();
        return db;
    }
}
