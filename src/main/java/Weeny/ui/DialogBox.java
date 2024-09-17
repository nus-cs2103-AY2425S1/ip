package weeny.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class DialogBox extends HBox {
    @FXML
    private Label text;
    @FXML
    private ImageView displayPicture;
    @FXML
    private HBox textBubble;

    private static final String USER_STYLE = "-fx-background-radius: 15; -fx-background-color: white; -fx-border-color: smokegray; -fx-border-width: 2; -fx-border-radius: 15;";
    private static final String WEENY_STYLE = "-fx-background-radius: 15; -fx-background-color: lightpink; -fx-border-color: smokegray; -fx-border-width: 2; -fx-border-radius: 15;";

    public DialogBox(String s, Image i) {
        try {
            FXMLLoader loader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        text.setText(s);
        displayPicture.setImage(i);
        clipDisplayPicture();
    }
    @FXML
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
    @FXML
    public static DialogBox getUserDialog(String s, Image i) {
        DialogBox userDialog = new DialogBox(s, i);
        userDialog.textBubble.setStyle(USER_STYLE);
        return userDialog;
    }
    @FXML
    public static DialogBox getWeenyDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        db.textBubble.setStyle(WEENY_STYLE);
        return db;
    }

    /**
     * Clips display picture into a circle
     */
    private void clipDisplayPicture() {
        double radius = displayPicture.getFitHeight() / 2;
        Circle clip = new Circle(radius, radius, radius);
        displayPicture.setClip(clip);
    }

}

