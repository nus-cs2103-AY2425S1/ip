package components;

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

import java.io.IOException;
import java.util.Collections;

public class DialogBox extends HBox {
    @FXML
    private ImageView profilePic;
    @FXML
    private Label message;

    private DialogBox(String message, Image profilePic) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            this.message.setText(message);
            this.profilePic.setImage(profilePic);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void initialize() {
        Circle clip = new Circle(25,25,25);
        profilePic.setClip(clip);
    }

    private void flip() {
        assert !this.getChildren().isEmpty() : "Every dialog box must have children";
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String userMessage, Image profilePic) {
        return new DialogBox(userMessage, profilePic);
    }

    public static DialogBox getBlitzDialog(String blitzResponse, Image profilePic) {
        DialogBox dukeDialogBox = new DialogBox(blitzResponse, profilePic);
        dukeDialogBox.flip();
        return dukeDialogBox;
    }
}


