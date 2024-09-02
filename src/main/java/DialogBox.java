import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label label;
    private ImageView imageView;

    public DialogBox(String text, Image picture) {
        this.label = new Label(text);
        this.imageView = new ImageView(picture);


        label.setWrapText(true);
        imageView.setFitWidth(100.0);
        imageView.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT.TOP_RIGHT);

        this.getChildren().addAll(label, imageView);
    }
}
