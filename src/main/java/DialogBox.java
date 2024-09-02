import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label label;
    private ImageView imageView;

    private DialogBox(String text, Image picture) {
        this.label = new Label(text);
        this.imageView = new ImageView(picture);
        this.getChildren().addAll(label, imageView);
    }

    public static DialogBox getHumanDialog(String text, Image picture) {
        DialogBox temp = new DialogBox(text, picture);
        temp.styleAsUser();
        return temp;
    }

    public static DialogBox getBotDialog(String text, Image picture) {
        DialogBox temp = new DialogBox(text, picture);
        temp.styleAsBot();
        return temp;
    }

    private void styleAsUser() {
        this.label.setWrapText(true);
        this.imageView.setFitWidth(100.0);
        this.imageView.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);
    }

    private void styleAsBot() {
        this.label.setWrapText(true);
        this.imageView.setFitWidth(100.0);
        this.imageView.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_LEFT);

        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
