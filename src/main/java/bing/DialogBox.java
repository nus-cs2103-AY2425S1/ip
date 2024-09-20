package bing;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;


public class DialogBox extends HBox{

    private Label text;
    private ImageView displayPicture;

    public DialogBox(String l, Image iv) {
        assert l != null : "Label text can not be null.";
        assert iv != null : "Image can not be null.";

        text = new Label(l);
        displayPicture = new ImageView(iv);
        text.setWrapText(true);
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);
        displayPicture.setClip(new Circle(25, 25, 25));
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    public static DialogBox getUserDialog(String input) {
        Image userImg = new Image("/images/user.png");
        if (userImg.isError()) {
            System.out.println("Error loading user image.");
        }
        DialogBox userDialog = new DialogBox(input, userImg);
        userDialog.setAlignment(Pos.TOP_RIGHT);
        return userDialog;
    }

    public static DialogBox getBingDialog(String response) {
        Image bingImg = new Image("/images/bot.png");
        if (bingImg.isError()) {
            System.out.println("Error loading bot image.");
        }
        DialogBox bingDialog = new DialogBox(response, bingImg);
        bingDialog.setAlignment(Pos.TOP_LEFT);
        return bingDialog;
    }

}
