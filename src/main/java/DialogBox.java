import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView advatar;

    public DialogBox(String s, Image i) {
        text = new Label(s);
        advatar = new ImageView(i);

        text.setWrapText(true);
        advatar.setFitHeight(100.0);
        advatar.setFitWidth(100.0);

        this.setAlignment(Pos.TOP_RIGHT);

        this.getChildren().addAll(text, advatar);
    }
}

