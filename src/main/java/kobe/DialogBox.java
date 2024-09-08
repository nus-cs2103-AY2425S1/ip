package kobe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.Collections;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, ImageView img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ensure the dialog grows dynamically
        dialog.setText(text);
        dialog.setWrapText(true); // Enable text wrapping
        dialog.setMaxWidth(Double.MAX_VALUE); // Let dialog take up available space
        dialog.setPrefWidth(300);  // Ensure the width is large enough for long texts
        dialog.setMinHeight(Region.USE_PREF_SIZE); // Allow it to grow vertically as needed

        displayPicture.setImage(img.getImage());
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);

        // Apply circular clipping to the image
        Circle clip = new Circle(25, 25, 25);
        displayPicture.setClip(clip);

        this.getStyleClass().add("dialog-box");
        this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(10); // Add spacing between the image and text
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, ImageView img) {
        var db = new DialogBox(text, img);
        db.getStyleClass().add("user-dialog");
        return db;
    }

    public static DialogBox getKobeDialog(String text, ImageView img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.getStyleClass().add("kobe-dialog");
        return db;
    }
}

